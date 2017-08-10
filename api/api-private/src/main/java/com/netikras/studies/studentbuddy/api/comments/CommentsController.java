package com.netikras.studies.studentbuddy.api.comments;

import com.netikras.studies.studentbuddy.api.filters.ThreadContext;
import com.netikras.studies.studentbuddy.commons.exception.StudBudUncheckedException;
import com.netikras.studies.studentbuddy.commons.model.PagedResults;
import com.netikras.studies.studentbuddy.core.data.api.dto.meta.CommentDto;
import com.netikras.studies.studentbuddy.core.data.api.model.Comment;
import com.netikras.studies.studentbuddy.core.data.api.model.Person;
import com.netikras.studies.studentbuddy.core.data.sys.SystemService;
import com.netikras.studies.studentbuddy.core.data.sys.model.User;
import com.netikras.studies.studentbuddy.core.meta.Action;
import com.netikras.studies.studentbuddy.core.meta.annotations.Authorizable;
import com.netikras.studies.studentbuddy.core.service.CommentsService;
import com.netikras.tools.common.model.mapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

import static com.netikras.studies.studentbuddy.core.meta.Action.COMMENT_CREATE;
import static com.netikras.studies.studentbuddy.core.meta.Action.COMMENT_DELETE;
import static com.netikras.studies.studentbuddy.core.meta.Action.COMMENT_GET;
import static com.netikras.studies.studentbuddy.core.meta.Action.COMMENT_MODIFY;
import static com.netikras.studies.studentbuddy.core.meta.Action.GET;
import static com.netikras.studies.studentbuddy.core.meta.Resource._PARAM;
import static com.netikras.tools.common.remote.http.HttpStatus.UNAUTHORIZED;

@RestController
@RequestMapping(CommentConstants.BASE_URL)
public class CommentsController {

    @Resource
    private CommentsService commentsService;

    @Resource
    private SystemService systemService;

    @RequestMapping(
            value = CommentConstants.COMMENT_URL_GET_FOR_TYPE,
            method = RequestMethod.GET
    )
    @ResponseBody
    @Authorizable(resource = _PARAM, resourceParam = "typeName", action = COMMENT_GET)
    public List<CommentDto> getForType(
            @PathVariable(name = "typeName") String typeName,
            @PathVariable(name = "typeId") String typeId
    ) {

        List<Comment> comments = commentsService.findComments(typeName, typeId);
        List<CommentDto> commentDtos = (List<CommentDto>) ModelMapper.transformAll(comments, CommentDto.class);

        return commentDtos;
    }


    @RequestMapping(
            value = CommentConstants.COMMENT_URL_GET_ALL_FOR_TYPE,
            method = RequestMethod.GET
    )
    @ResponseBody
    @Authorizable(resource = _PARAM, resourceParam = "typeName", action = COMMENT_GET)
    public List<CommentDto> getAllForType(
            @PathVariable(name = "typeName") String typeName
    ) {
        List<Comment> comments = commentsService.findCommentsByType(typeName);
        List<CommentDto> commentDtos = (List<CommentDto>) ModelMapper.transformAll(comments, CommentDto.class);

        return commentDtos;
    }


    @RequestMapping(
            value = CommentConstants.COMMENT_URL_DELETE_FOR_TYPE,
            method = RequestMethod.DELETE
    )
    @ResponseStatus(code = HttpStatus.OK, reason = "Comment has been deleted")
    @Authorizable(resource = _PARAM, resourceParam = "typeName", action = COMMENT_DELETE)
    public void deleteForType(
            @PathVariable(name = "typeName") String typeName,
            @PathVariable(name = "typeId") String typeId
    ) {
        commentsService.deleteCommentByType(typeName, typeId);
    }


    @RequestMapping(
            value = CommentConstants.COMMENT_URL_DELETE_ALL_FOR_TYPE,
            method = RequestMethod.DELETE
    )
    @ResponseStatus(code = HttpStatus.OK, reason = "Comments have been deleted")
    @Authorizable(resource = _PARAM, resourceParam = "typeName", action = COMMENT_DELETE)
    public void deleteAllForType(
            @PathVariable(name = "typeName") String typeName
    ) {
        commentsService.deleteCommentsByType(typeName);
    }

    @RequestMapping(
            value = CommentConstants.COMMENT_URL_DELETE_BY_ID,
            method = RequestMethod.DELETE
    )
    @ResponseStatus(code = HttpStatus.OK, reason = "Comment has been deleted")
    public void deleteById(
            @PathVariable(name = "id") String id
    ) {
        throwIfActionNotAllowedForCommentId(id, COMMENT_DELETE);
        commentsService.deleteComment(id);
    }

    @RequestMapping(
            value = CommentConstants.COMMENT_URL_GET_BY_ID,
            method = RequestMethod.GET
    )
    @ResponseBody
    public CommentDto getById(
            @PathVariable(name = "id") String id
    ) {
        Comment comment = commentsService.findComment(id);

        throwIfActionNotAllowedForComment(comment, COMMENT_GET);

        CommentDto commentDto = ModelMapper.transform(comment, new CommentDto());

        return commentDto;
    }

    @RequestMapping(
            value = CommentConstants.COMMENT_URL_CREATE,
            method = RequestMethod.POST
    )
    @ResponseBody
    public CommentDto createNew(
            @RequestBody(required = true) CommentDto commentDto
    ) {
        Comment comment = ModelMapper.apply(new Comment(), commentDto);

        throwIfActionNotAllowedForComment(comment, COMMENT_CREATE);

        Person author = ModelMapper.apply(new Person(), commentDto.getAuthor());
        author.setId(commentDto.getAuthor().getId()); // throw NPE if author is not supplied!

        comment.setAuthor(author);
        comment.setEntityType(commentDto.getEntityType());
        comment.setEntityId(commentDto.getEntityId());

        comment = commentsService.createComment(comment);
        ModelMapper.transform(comment, commentDto);

        return commentDto;
    }

    @RequestMapping(
            value = CommentConstants.COMMENT_URL_CREATE_FOR_TYPE,
            method = RequestMethod.POST
    )
    @ResponseBody
    @Authorizable(resource = _PARAM, resourceParam = "typeName", action = COMMENT_CREATE)
    public CommentDto createNewForType(
            @PathVariable(name = "typeName") String typeName,
            @PathVariable(name = "typeId") String typeId,
            @RequestBody(required = true) CommentDto commentDto
    ) {
        Comment comment = ModelMapper.apply(new Comment(), commentDto);
        comment.setEntityType(typeName);
        comment.setEntityId(typeId);

        comment = commentsService.createComment(comment);
        commentDto = ModelMapper.transform(comment, new CommentDto());

        return commentDto;
    }

    @RequestMapping(
            value = CommentConstants.COMMENT_URL_UPDATE_BY_ID,
            method = RequestMethod.PUT
    )
    @ResponseBody
    @Transactional
    public CommentDto update(
            @PathVariable(name = "id") String id,
            @RequestBody(required = true) CommentDto commentDto
    ) {
        Comment comment = commentsService.findComment(id);

        throwIfActionNotAllowedForComment(comment, COMMENT_MODIFY);

        comment = ModelMapper.apply(comment, commentDto);
        comment = commentsService.updateComment(comment);
        commentDto = ModelMapper.transform(comment, new CommentDto());

        return commentDto;
    }


    @RequestMapping(
            value = CommentConstants.COMMENT_URL_GET_BY_TAG_VALUE,
            method = RequestMethod.GET
    )
    @ResponseBody
    public List<CommentDto> getByTagValue(
            @PathVariable(name = "value") String value,
            @RequestParam(name = "pageno", required = false) Long pageNumber,
            @RequestParam(name = "pagesz", required = false) Long pageSize
    ) {
        List<CommentDto> commentDtos;

        List<Comment> comments = commentsService.findCommentsByTagValue(value);
        comments.removeIf(comment -> isUserAllowedTo(comment.getEntityType(), COMMENT_GET));
        commentDtos = (List<CommentDto>) ModelMapper.transformAll(comments, CommentDto.class);

        return commentDtos;
    }


    @RequestMapping(
            value = CommentConstants.COMMENT_URL_GET_BY_TAG_ID,
            method = RequestMethod.GET
    )
    @ResponseBody
    public List<CommentDto> getByTagId(
            @PathVariable(name = "id") String id,
            @RequestParam(name = "pageno", required = false) Long pageNumber,
            @RequestParam(name = "pagesz", required = false) Long pageSize
    ) {

        PagedResults<Comment> results = new PagedResults<>(pageNumber, pageSize);

        List<CommentDto> commentDtos = null;

        List<Comment> comments = commentsService.findCommentsByTagId(id);
        comments.removeIf(comment -> isUserAllowedTo(comment.getEntityType(), COMMENT_GET));
        commentDtos = (List<CommentDto>) ModelMapper.transformAll(comments, CommentDto.class);

        return commentDtos;
    }

    @RequestMapping(
            value = CommentConstants.COMMENT_URL_GET_BY_PERSON_ID,
            method = RequestMethod.GET
    )
    @ResponseBody
    public List<CommentDto> getAllByPerson(
            @PathVariable(name = "id") String id
    ) {
        List<Comment> comments = commentsService.findCommentsByPerson(id);
        comments.removeIf(comment -> isUserAllowedTo(comment.getEntityType(), COMMENT_GET));
        List<CommentDto> commentDtos = (List<CommentDto>) ModelMapper.transformAll(comments, CommentDto.class);

        return commentDtos;
    }

    @RequestMapping(
            value = CommentConstants.COMMENT_URL_DELETE_BY_PERSON_ID,
            method = RequestMethod.DELETE
    )
    @ResponseBody
    @Transactional
    public void deleteAllByPerson(
            @PathVariable(name = "id") String id
    ) {
        List<Comment> comments = commentsService.findCommentsByPerson(id);
        comments.removeIf(comment -> isUserAllowedTo(comment.getEntityType(), COMMENT_DELETE));

        for (Comment comment : comments) {
            commentsService.deleteComment(comment.getId());
        }
    }









    public boolean isUserAllowedTo(String entity, Action action) {
        boolean allowed = false;

        if (entity != null && !entity.isEmpty()) {
            try {
                com.netikras.studies.studentbuddy.core.meta.Resource resource =
                        com.netikras.studies.studentbuddy.core.meta.Resource.valueOf(entity);
                User user = ThreadContext.current().getUser();
                allowed = systemService.isUserAllowedToPerformAction(user, resource.name(), action.name());
            } catch (Exception ignore) {
            }
        }

        return allowed;
    }

    private void throwIfActionNotAllowedForComment(Comment comment, Action action) {
        String entity = comment.getEntityType();

        if (!isUserAllowedTo(entity, action)) {
            throw new StudBudUncheckedException()
                    .setMessage1("Cannot " + action.name().toLowerCase() + " a comment")
                    .setMessage2("User is not allowed to perform this action")
                    .setProbableCause(comment.getId())
                    .setStatusCode(UNAUTHORIZED)
                    ;
        }
    }

    private void throwIfActionNotAllowedForCommentId(String commentId, Action action) {
        Comment comment = commentsService.findComment(commentId);
        throwIfActionNotAllowedForComment(comment, action);
    }


}
