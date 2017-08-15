package com.netikras.studies.studentbuddy.api.comments;

import com.fasterxml.jackson.core.type.TypeReference;
import com.netikras.studies.studentbuddy.core.data.api.dto.meta.CommentDto;
import com.netikras.tools.common.remote.http.GenericRestConsumer;
import com.netikras.tools.common.remote.http.HttpRequest;
import com.netikras.tools.common.remote.http.impl.json.HttpResponseJsonImpl;

import java.util.List;

import static com.netikras.studies.studentbuddy.api.comments.CommentConstants.endpointCreateNew;
import static com.netikras.studies.studentbuddy.api.comments.CommentConstants.endpointGetForId;
import static com.netikras.studies.studentbuddy.api.comments.CommentConstants.endpointGetForType;

public class CommentsConsumer extends GenericRestConsumer {


    public CommentDto createComment(CommentDto commentDto) {
        HttpRequest<CommentDto> request = createRequest(endpointCreateNew())
                .withExpectedType(CommentDto.class)
                .setObject(commentDto);

        CommentDto comment = (CommentDto) sendRequest(request);
        return comment;
    }


    public CommentDto getCommentById(String id) {
        HttpRequest<CommentDto> request = createRequest(endpointGetForId())
                .setUrlProperty("id", id);

        CommentDto commentDto = (CommentDto) sendRequest(request);

        return commentDto;
    }

    public List<CommentDto> getCommentByType(String entityType, String entityId) {
        HttpRequest<CommentDto> request = createRequest(endpointGetForType())
                .setUrlProperty("typeName", entityType)
                .setUrlProperty("typeId", entityId);

        List<CommentDto> comments = (List<CommentDto>) sendRequest(request, new HttpResponseJsonImpl(new TypeReference<List<CommentDto>>() {}));

        return comments;
    }

    public List<CommentDto> getAllCommentsByType(String entityType, String typeId) {
        HttpRequest<CommentDto> request = createRequest(endpointGetForType())
                .setUrlProperty("typeName", entityType)
                .setUrlProperty("typeId", typeId);

        List<CommentDto> comments = (List<CommentDto>) sendRequest(request, new HttpResponseJsonImpl(new TypeReference<List<CommentDto>>() {
        }));

        return comments;
    }


}
