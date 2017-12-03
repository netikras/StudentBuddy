package com.netikras.studies.studentbuddy.api.comments.producer;

import com.netikras.studies.studentbuddy.api.comments.generated.CommentsApiProducer;
import com.netikras.studies.studentbuddy.api.filters.HttpThreadContext;
import com.netikras.studies.studentbuddy.api.handlers.DtoMapper;
import com.netikras.studies.studentbuddy.commons.exception.StudBudUncheckedException;
import com.netikras.studies.studentbuddy.commons.model.PagedResults;
import com.netikras.studies.studentbuddy.core.data.api.dto.meta.CommentDto;
import com.netikras.studies.studentbuddy.core.data.api.model.Comment;
import com.netikras.studies.studentbuddy.core.data.api.model.Person;
import com.netikras.studies.studentbuddy.core.data.meta.Action;
import com.netikras.studies.studentbuddy.core.data.sys.model.User;
import com.netikras.studies.studentbuddy.core.service.CommentsService;
import com.netikras.studies.studentbuddy.core.service.SystemService;
import com.netikras.studies.studentbuddy.core.validator.EntityProvider;
import com.netikras.tools.common.exception.ErrorsCollection;
import com.netikras.tools.common.exception.ValidationError;
import com.netikras.tools.common.model.mapper.MappingSettings;
import com.netikras.tools.common.model.mapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

import static com.netikras.studies.studentbuddy.core.data.meta.Action.COMMENT_CREATE;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.COMMENT_DELETE;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.COMMENT_GET;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.COMMENT_MODIFY;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.MODERATE;
import static com.netikras.tools.common.remote.http.HttpStatus.UNAUTHORIZED;
import static com.netikras.tools.common.security.IntegrityUtils.isNullOrEmpty;

@RestController
public class CommentsProducer extends CommentsApiProducer {

    @Resource
    private ModelMapper modelMapper;
    @Resource
    private DtoMapper dtoMapper;

    @Resource
    private CommentsService commentsService;

    @Resource
    private SystemService systemService;
    @Resource
    EntityProvider entityProvider;


    @Override
    @Transactional
    protected CommentDto onRetrieveCommentDto(String id) {
        Comment comment = commentsService.getComment(id);
        throwIfActionNotAllowedForComment(comment, COMMENT_GET);
        CommentDto commentDto = (CommentDto) dtoMapper.toDto(comment);
        return commentDto;
    }

    @Override
    @Transactional
    protected CommentDto onCreateCommentDto(CommentDto commentDto) {
        Comment comment = modelMapper.apply(new Comment(), commentDto, new MappingSettings().setForceUpdate(true));

        throwIfActionNotAllowedForComment(comment, COMMENT_CREATE);

        if (comment.getAuthor() == null) {
            comment.setAuthor(HttpThreadContext.current().getUser().getPerson());
        } else {
            comment.setAuthor(entityProvider.fetch(commentDto.getAuthor()));
        }

        comment.setEntityType(commentDto.getEntityType());
        comment.setEntityId(commentDto.getEntityId());

        comment = commentsService.createComment(comment);
        CommentDto dto = (CommentDto) dtoMapper.toDto(comment);

        return dto;
    }

    @Override
    @Transactional
    protected CommentDto onUpdateCommentDto(CommentDto commentDto) {
        Comment comment = modelMapper.apply(entityProvider.fetch(commentDto), commentDto);

        throwIfActionNotAllowedForComment(comment, COMMENT_MODIFY);

        comment = modelMapper.apply(comment, commentDto);
        comment = commentsService.updateComment(comment);
        CommentDto dto = (CommentDto) dtoMapper.toDto(comment);

        return dto;
    }

    @Override
    @Transactional
    protected void onDeleteCommentDto(String id) {
        throwIfActionNotAllowedForCommentId(id, COMMENT_DELETE);
        commentsService.deleteComment(id);
    }

    @Override
    @Transactional
    protected List<CommentDto> onGetCommentDtoForType(String typeName, String typeId) {
        List<Comment> comments = commentsService.findComments(typeName, typeId);
        if (!isNullOrEmpty(comments)) {
            comments.forEach(c -> throwIfActionNotAllowedForComment(c, COMMENT_GET));
        }
        List<CommentDto> commentDtos = (List<CommentDto>) dtoMapper.toDtos(comments);

        return commentDtos;
    }

    @Override
    @Transactional
    protected List<CommentDto> onGetCommentDtoAllForType(String typeName) {
        List<Comment> comments = commentsService.findCommentsByType(typeName);
        if (!isNullOrEmpty(comments)) {
            comments.forEach(c -> throwIfActionNotAllowedForComment(c, COMMENT_GET));
        }
        List<CommentDto> commentDtos = (List<CommentDto>) dtoMapper.toDtos(comments);

        return commentDtos;
    }

    @Override
    protected void onDeleteCommentDtoForType(String typeName, String typeId) {
        List<Comment> comments = commentsService.findComments(typeName, typeId);
        if (!isNullOrEmpty(comments)) {
            comments.forEach(c -> throwIfActionNotAllowedForComment(c, COMMENT_DELETE));
        }
        commentsService.deleteCommentByType(typeName, typeId);
    }

    @Override
    protected void onDeleteCommentDtoAllForType(String typeName) {
        List<Comment> comments = commentsService.findCommentsByType(typeName);
        if (!isNullOrEmpty(comments)) {
            comments.forEach(c -> throwIfActionNotAllowedForComment(c, COMMENT_DELETE));
        }
        commentsService.deleteCommentsByType(typeName);
    }

    @Override
    @Transactional
    protected CommentDto onCreateCommentDtoNewForType(String typeName, String typeId, CommentDto commentDto) {
        Comment comment = modelMapper.apply(new Comment(), commentDto, new MappingSettings().setForceUpdate(true));
        comment.setEntityType(typeName);
        comment.setEntityId(typeId);

        if (comment.getAuthor() == null) {
            comment.setAuthor(HttpThreadContext.current().getUser().getPerson());
        } else {
            comment.setAuthor(entityProvider.fetch(commentDto.getAuthor()));
        }

        throwIfActionNotAllowedForComment(comment, COMMENT_CREATE);

        comment = commentsService.createComment(comment);
        commentDto = (CommentDto) dtoMapper.toDto(comment);

        return commentDto;
    }

    @Override
    @Transactional
    protected List<CommentDto> onGetCommentDtoByTagValue(String value, Long pageno, Long pagesz) {
        List<CommentDto> commentDtos;

        List<Comment> comments = commentsService.findCommentsByTagValue(value);
        ErrorsCollection errors = new ErrorsCollection();
        comments.removeIf(comment -> !isUserAllowedTo(comment.getEntityType(), COMMENT_GET, errors));
        commentDtos = (List<CommentDto>) dtoMapper.toDtos(comments);

        return commentDtos;
    }

    @Override
    @Transactional
    protected List<CommentDto> onGetCommentDtoByTagId(String id, Long pageno, Long pageSize) {
        PagedResults<Comment> results = new PagedResults<>(pageno, pageSize);

        List<CommentDto> commentDtos = null;

        List<Comment> comments = commentsService.findCommentsByTagId(id);
        ErrorsCollection errors = new ErrorsCollection();
        comments.removeIf(comment -> !isUserAllowedTo(comment.getEntityType(), COMMENT_GET, errors));
        commentDtos = (List<CommentDto>) dtoMapper.toDtos(commentDtos);

        return commentDtos;
    }

    @Override
    @Transactional
    protected List<CommentDto> onGetCommentDtoAllByPerson(String id) {
        List<Comment> comments = commentsService.findCommentsByPerson(id);
        ErrorsCollection errors = new ErrorsCollection();
        comments.removeIf(comment -> !isUserAllowedTo(comment.getEntityType(), COMMENT_GET, errors));
        List<CommentDto> commentDtos = (List<CommentDto>) dtoMapper.toDtos(comments);

        return commentDtos;
    }

    @Override
    protected void onDeleteCommentDtoAllByPerson(String id) {
        List<Comment> comments = commentsService.findCommentsByPerson(id);
        ErrorsCollection errors = new ErrorsCollection();
        comments.removeIf(comment -> !isUserAllowedTo(comment.getEntityType(), COMMENT_DELETE, errors));

        for (Comment comment : comments) {
            commentsService.deleteComment(comment.getId());
        }
    }


    public boolean isUserAllowedTo(String entity, Action action, ErrorsCollection errors) {
        boolean allowed = false;

        User user = null;

        if (entity == null || entity.isEmpty()) {
            errors.add(new ValidationError().setMessage1("Entity type is mandatory"));
        }

        if (errors.isEmpty()) {
            try {
                com.netikras.studies.studentbuddy.core.data.meta.Resource resource =
                        com.netikras.studies.studentbuddy.core.data.meta.Resource.valueOf(entity.toUpperCase());
                user = HttpThreadContext.current().getUser();
                allowed = systemService.isUserAllowedToPerformAction(user, resource.name(), null, action.name());
            } catch (IllegalArgumentException ilae) {
                errors.add(new ValidationError().setMessage1("Cannot determine entity type for value " + entity));
            }

            if (!allowed) {
                errors.add(new ValidationError().setMessage1("User is unauthorized to perform action " + action + " on entity " + entity));
            }
        }

        return allowed;
    }

    private void throwIfActionNotAllowedForComment(Comment comment, Action action) {
        ErrorsCollection errors = new ErrorsCollection();
        String entity = null;
        boolean allowed = false;

        if (comment == null) {
            errors.add(new ValidationError().setMessage1("Comment is not provided"));
        }

        if (errors.isEmpty()) {
            entity = comment.getEntityType();
            allowed = isUserAllowedTo(entity, action, errors);
        }

        if (allowed) {
            User currentUser = HttpThreadContext.current().getUser();
            if (currentUser != null && currentUser.getPerson() != null) {
                Person currentPerson = currentUser.getPerson();
                Person authorPerson = comment.getAuthor();
                if (!currentPerson.equals(authorPerson)) {
                    if (!systemService.isUserAllowedToPerformAction(currentUser, entity, null, MODERATE.name()))
                        errors.add(new ValidationError().setMessage1("User is not allowed to manipulate records as another person"));
                }
            }
        }

        if (!errors.isEmpty()) {
            StudBudUncheckedException exception = new StudBudUncheckedException();
            exception.setMessage1("Unable to perform action " + action + " on comment");
            exception.setMessage2(errors.buildSingleMessage());
            exception.setStatusCode(UNAUTHORIZED);
            throw exception;
        }

    }

    private void throwIfActionNotAllowedForCommentId(String commentId, Action action) {
        Comment comment = commentsService.getComment(commentId);
        throwIfActionNotAllowedForComment(comment, action);
    }
}
