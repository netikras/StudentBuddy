package com.netikras.studies.studentbuddy.api.comments;

import com.netikras.studies.studentbuddy.commons.model.PagedResults;
import com.netikras.studies.studentbuddy.core.data.api.dto.meta.CommentDto;
import com.netikras.studies.studentbuddy.core.data.api.model.Comment;
import com.netikras.studies.studentbuddy.core.data.api.model.Person;
import com.netikras.studies.studentbuddy.core.service.CommentsService;
import com.netikras.tools.common.model.mapper.ModelMapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
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
    public List<CommentDto> getForType(
            @PathVariable(name = "typeName") String typeName,
            @PathVariable(name = "typeId") String typeId
    ) {
        List<Comment> comments = commentsService.findComments(typeName, typeId);
        List<CommentDto> commentDtos = convertToCommentDtos(comments);
        return commentDtos;
    }


    @RequestMapping(
            value = "/type/{typeName}",
            method = RequestMethod.GET
    )
    @ResponseBody
    public List<CommentDto> getAllForType(
            @PathVariable(name = "typeName") String typeName
    ) {
        List<Comment> comments = commentsService.findCommentsByType(typeName);
        List<CommentDto> commentDtos = convertToCommentDtos(comments);
        return commentDtos;
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
    public CommentDto getById(
            @PathVariable(name = "id") String id
    ) {
        Comment comment = commentsService.findComment(id);
        CommentDto commentDto = ModelMapper.transform(comment, new CommentDto());
        return commentDto;
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

        comment = commentsService.createComment(comment);
        ModelMapper.transform(comment, commentDto);

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
        Comment comment = ModelMapper.apply(commentsService.findComment(id), commentDto);

        commentsService.updateComment(comment);

        return commentDto;
    }


    @RequestMapping(
            value = "/tag/value/{value}",
            method = RequestMethod.GET
    )
    @ResponseBody
    public List<CommentDto> getByTagValue(
            @PathVariable(name = "value") String value,
            @RequestParam(name = "pageno", required = false) Long pageNumber,
            @RequestParam(name = "pagesz", required = false) Long pageSize
    ) {
        List<CommentDto> commentDtos = new ArrayList<>();

        List<Comment> comments = commentsService.findCommentsByTagValue(value);
        commentDtos = convertToCommentDtos(comments);

        return commentDtos;
    }


    @RequestMapping(
            value = "/tag/id/{id}",
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
        commentDtos = convertToCommentDtos(comments);

        return commentDtos;
    }





    private List<CommentDto> convertToCommentDtos(List<Comment> comments) {
        List<CommentDto> commentDtos = new ArrayList<>();
        if (comments != null) {
            for (Comment comment : comments) {
                CommentDto dto = ModelMapper.transform(comment, new CommentDto());
                commentDtos.add(dto);
            }
        }
        return commentDtos;
    }

}
