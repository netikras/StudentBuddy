package com.netikras.studies.studentbuddy.api.comments.producer;

import com.netikras.studies.studentbuddy.api.comments.generated.CommentsApiProducer;
import com.netikras.studies.studentbuddy.api.filters.HttpThreadContext;
import com.netikras.studies.studentbuddy.commons.exception.StudBudUncheckedException;
import com.netikras.studies.studentbuddy.commons.model.PagedResults;
import com.netikras.studies.studentbuddy.core.data.api.dto.meta.CommentDto;
import com.netikras.studies.studentbuddy.core.data.api.model.Comment;
import com.netikras.studies.studentbuddy.core.data.api.model.Person;
import com.netikras.studies.studentbuddy.core.data.meta.Action;
import com.netikras.studies.studentbuddy.core.data.meta.annotations.Authorizable;
import com.netikras.studies.studentbuddy.core.data.sys.model.User;
import com.netikras.studies.studentbuddy.core.service.CommentsService;
import com.netikras.studies.studentbuddy.core.service.SystemService;
import com.netikras.tools.common.exception.ErrorsCollection;
import com.netikras.tools.common.exception.ValidationError;
import com.netikras.tools.common.model.mapper.MappingSettings;
import com.netikras.tools.common.model.mapper.ModelMapper;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

import static com.netikras.studies.studentbuddy.core.data.meta.Action.COMMENT_CREATE;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.COMMENT_DELETE;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.COMMENT_GET;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.COMMENT_MODIFY;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.MODERATE;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource._PARAM;
import static com.netikras.tools.common.remote.http.HttpStatus.UNAUTHORIZED;

@RestController
public class CommentsProducer extends CommentsApiProducer {

    @Resource
    private ModelMapper modelMapper;

    @Resource
    private CommentsService commentsService;

    @Resource
    private SystemService systemService;


    @Override
    protected CommentDto onRetrieveCommentDto(String id) {
        Comment comment = commentsService.getComment(id);
        throwIfActionNotAllowedForComment(comment, COMMENT_GET);
        CommentDto commentDto = modelMapper.transform(comment, new CommentDto());
        return commentDto;
    }

    @Override
    protected CommentDto onCreateCommentDto(CommentDto commentDto) {
        Comment comment = modelMapper.apply(new Comment(), commentDto, new MappingSettings().setForceUpdate(true));

        throwIfActionNotAllowedForComment(comment, COMMENT_CREATE);

        Person author = modelMapper.apply(new Person(), commentDto.getAuthor());
        author.setId(commentDto.getAuthor().getId()); // throw NPE if author is not supplied!

        comment.setAuthor(author);
        comment.setEntityType(commentDto.getEntityType());
        comment.setEntityId(commentDto.getEntityId());

        comment = commentsService.createComment(comment);
        modelMapper.transform(comment, commentDto);

        return commentDto;
    }

    @Override
    protected CommentDto onUpdateCommentDto(CommentDto commentDto) {
        Comment comment = modelMapper.apply(new Comment(), commentDto);

        throwIfActionNotAllowedForComment(comment, COMMENT_MODIFY);

        comment = modelMapper.apply(comment, commentDto);
        comment = commentsService.updateComment(comment);
        commentDto = modelMapper.transform(comment, new CommentDto());

        return commentDto;
    }

    @Override
    protected void onDeleteCommentDto(String id) {
        throwIfActionNotAllowedForCommentId(id, COMMENT_DELETE);
        commentsService.deleteComment(id);
    }

    @Override
    @Authorizable(resource = _PARAM, resourceParam = "typeName", action = COMMENT_GET)
    protected List<CommentDto> onGetCommentDtoForType(String typeName, String typeId) {
        List<Comment> comments = commentsService.findComments(typeName, typeId);
        List<CommentDto> commentDtos = (List<CommentDto>) modelMapper.transformAll(comments, CommentDto.class);

        return commentDtos;
    }

    @Override
    @Authorizable(resource = _PARAM, resourceParam = "typeName", action = COMMENT_GET)
    protected List<CommentDto> onGetCommentDtoAllForType(String typeName) {
        List<Comment> comments = commentsService.findCommentsByType(typeName);
        List<CommentDto> commentDtos = (List<CommentDto>) modelMapper.transformAll(comments, CommentDto.class);

        return commentDtos;
    }

    @Override
    @Authorizable(resource = _PARAM, resourceParam = "typeName", action = COMMENT_DELETE)
    protected void onDeleteCommentDtoForType(String typeName, String typeId) {
        commentsService.deleteCommentByType(typeName, typeId);
    }

    @Override
    @Authorizable(resource = _PARAM, resourceParam = "typeName", action = COMMENT_DELETE)
    protected void onDeleteCommentDtoAllForType(String typeName) {
        commentsService.deleteCommentsByType(typeName);
    }

    @Override
    @Authorizable(resource = _PARAM, resourceParam = "typeName", action = COMMENT_CREATE)
    protected CommentDto onCreateCommentDtoNewForType(String typeName, String typeId, CommentDto commentDto) {
        Comment comment = modelMapper.apply(new Comment(), commentDto);
        comment.setEntityType(typeName);
        comment.setEntityId(typeId);

        comment = commentsService.createComment(comment);
        commentDto = modelMapper.transform(comment, new CommentDto());

        return commentDto;
    }

    @Override
    protected List<CommentDto> onGetCommentDtoByTagValue(String value, Long pageno, Long pagesz) {
        List<CommentDto> commentDtos;

        List<Comment> comments = commentsService.findCommentsByTagValue(value);
        ErrorsCollection errors = new ErrorsCollection();
        comments.removeIf(comment -> !isUserAllowedTo(comment.getEntityType(), COMMENT_GET, errors));
        commentDtos = (List<CommentDto>) modelMapper.transformAll(comments, CommentDto.class);

        return commentDtos;
    }

    @Override
    protected List<CommentDto> onGetCommentDtoByTagId(String id, Long pageno, Long pageSize) {
        PagedResults<Comment> results = new PagedResults<>(pageno, pageSize);

        List<CommentDto> commentDtos = null;

        List<Comment> comments = commentsService.findCommentsByTagId(id);
        ErrorsCollection errors = new ErrorsCollection();
        comments.removeIf(comment -> !isUserAllowedTo(comment.getEntityType(), COMMENT_GET, errors));
        commentDtos = (List<CommentDto>) modelMapper.transformAll(comments, CommentDto.class);

        return commentDtos;
    }

    @Override
    protected List<CommentDto> onGetCommentDtoAllByPerson(String id) {
        List<Comment> comments = commentsService.findCommentsByPerson(id);
        ErrorsCollection errors = new ErrorsCollection();
        comments.removeIf(comment -> !isUserAllowedTo(comment.getEntityType(), COMMENT_GET, errors));
        List<CommentDto> commentDtos = (List<CommentDto>) modelMapper.transformAll(comments, CommentDto.class);

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
