package com.netikras.studies.studentbuddy.api.comments;

import com.netikras.studies.studentbuddy.core.data.api.dto.meta.CommentDto;
import com.netikras.studies.studentbuddy.core.data.api.model.Comment;
import com.netikras.studies.studentbuddy.core.service.CommentsService;
import com.netikras.tools.common.model.mapper.ModelMapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentsController {

    @Resource
    private CommentsService commentsService;

    @RequestMapping(
            value = "/type/{typeName}/{typeId}",
            method = RequestMethod.GET
    )
    @ResponseBody
    public List<Comment> getForType(
            @PathVariable(name = "typeName") String typeName,
            @PathVariable(name = "typeId") String typeId
    ) {

        return commentsService.getComments(typeName, typeId);
    }

    @RequestMapping(
            value = "/type/{typeName}",
            method = RequestMethod.GET
    )
    @ResponseBody
    public List<Comment> getAllForType(
            @PathVariable(name = "typeName") String typeName
    ) {

        return commentsService.getCommentsByType(typeName);
    }


    @RequestMapping(
            value = "/type/{typeName}/{typeId}",
            method = RequestMethod.DELETE
    )
    public void deleteForType(
            @PathVariable(name = "typeName") String typeName,
            @PathVariable(name = "typeId") String typeId
    ) {

        commentsService.deleteCommentByType(typeName, typeId);

    }


    @RequestMapping(
            value = "/type/{typeName}",
            method = RequestMethod.DELETE
    )
    public void deleteAllForType(
            @PathVariable(name = "typeName") String typeName
    ) {
        commentsService.deleteCommentsByType(typeName);

    }

    @RequestMapping(
            value = "/id/{id}",
            method = RequestMethod.DELETE
    )
    public void deleteById(
            @PathVariable(name = "id") String id
    ) {

        commentsService.deleteComment(id);
    }

    @RequestMapping(
            value = "/id/{id}",
            method = RequestMethod.GET
    )
    @ResponseBody
    public Comment getById(
            @PathVariable(name = "id") String id
    ) {

        return commentsService.getComment(id);
    }

    @RequestMapping(
            value = "/",
            method = RequestMethod.POST
    )
    @ResponseBody
    public CommentDto createNew(
            @RequestBody(required = true) CommentDto commentDto
    ) {
        Comment comment = ModelMapper.apply(new Comment(), commentDto);

        commentsService.createComment(comment);
        commentDto.setId(comment.getId());

        return commentDto;
    }

    @RequestMapping(
            value = "/type/{typeName}/{typeId}",
            method = RequestMethod.POST
    )
    @ResponseBody
    public CommentDto createNewForType(
            @PathVariable(name = "typeName") String typeName,
            @PathVariable(name = "typeId") String typeId,
            @RequestBody(required = true) CommentDto commentDto
    ) {
        Comment comment = ModelMapper.apply(new Comment(), commentDto);
        comment.setEntityType(typeName);
        comment.setEntityId(typeId);

        commentsService.createComment(comment);
        commentDto.setId(comment.getId());

        return commentDto;
    }

    @RequestMapping(
            value = "/id/{id}",
            method = RequestMethod.PUT
    )
    @ResponseBody
    public CommentDto update(
            @PathVariable(name = "id") String id,
            @RequestBody(required = true) CommentDto commentDto
    ) {
        Comment comment = ModelMapper.apply(commentsService.getComment(id), commentDto);

        commentsService.updateComment(comment);

        return commentDto;
    }


}
