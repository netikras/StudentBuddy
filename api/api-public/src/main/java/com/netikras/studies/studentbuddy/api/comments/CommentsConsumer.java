package com.netikras.studies.studentbuddy.api.comments;

import com.fasterxml.jackson.core.type.TypeReference;
import com.netikras.studies.studentbuddy.core.data.api.dto.meta.CommentDto;
import com.netikras.tools.common.remote.http.GenericRestConsumer;
import com.netikras.tools.common.remote.http.HttpRequest;
import com.netikras.tools.common.remote.http.impl.json.HttpResponseJsonImpl;

import java.util.List;

import static com.netikras.studies.studentbuddy.api.constants.CommentConstants.endpointCreateNew;
import static com.netikras.studies.studentbuddy.api.constants.CommentConstants.endpointCreateNewForType;
import static com.netikras.studies.studentbuddy.api.constants.CommentConstants.endpointDeleteAllByPersonId;
import static com.netikras.studies.studentbuddy.api.constants.CommentConstants.endpointDeleteAllForType;
import static com.netikras.studies.studentbuddy.api.constants.CommentConstants.endpointDeleteForId;
import static com.netikras.studies.studentbuddy.api.constants.CommentConstants.endpointDeleteForType;
import static com.netikras.studies.studentbuddy.api.constants.CommentConstants.endpointGetAllByPersonId;
import static com.netikras.studies.studentbuddy.api.constants.CommentConstants.endpointGetAllForType;
import static com.netikras.studies.studentbuddy.api.constants.CommentConstants.endpointGetByTagId;
import static com.netikras.studies.studentbuddy.api.constants.CommentConstants.endpointGetByTagValue;
import static com.netikras.studies.studentbuddy.api.constants.CommentConstants.endpointGetForId;
import static com.netikras.studies.studentbuddy.api.constants.CommentConstants.endpointGetForType;
import static com.netikras.studies.studentbuddy.api.constants.CommentConstants.endpointUpdateById;

public class CommentsConsumer extends GenericRestConsumer {


    public CommentDto createComment(CommentDto commentDto) {
        HttpRequest<CommentDto> request = createRequest(endpointCreateNew())
                .withExpectedType(CommentDto.class)
                .setObject(commentDto);

        CommentDto comment = (CommentDto) sendRequest(request);
        return comment;
    }

    public CommentDto createCommentForType(CommentDto commentDto, String entityType, String entityId) {
        HttpRequest<CommentDto> request = createRequest(endpointCreateNewForType())
                .withExpectedType(CommentDto.class)
                .setObject(commentDto)
                .setUrlProperty("typeName", entityType)
                .setUrlProperty("typeId", entityId);

        CommentDto comment = (CommentDto) sendRequest(request);
        return comment;
    }

    public CommentDto updateCommentById(CommentDto commentDto) {
        HttpRequest<CommentDto> request = createRequest(endpointUpdateById())
                .withExpectedType(CommentDto.class)
                .setUrlProperty("id", commentDto.getId())
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

    public List<CommentDto> getAllCommentsByTagId(String id) {
        HttpRequest<CommentDto> request = createRequest(endpointGetByTagId())
                .setUrlProperty("id", id);

        List<CommentDto> comments = (List<CommentDto>) sendRequest(request, new HttpResponseJsonImpl(new TypeReference<List<CommentDto>>() {}));

        return comments;
    }

    public List<CommentDto> getAllCommentsByTagValue(String value) {
        HttpRequest<CommentDto> request = createRequest(endpointGetByTagValue())
                .setUrlProperty("value", value);

        List<CommentDto> comments = (List<CommentDto>) sendRequest(request, new HttpResponseJsonImpl(new TypeReference<List<CommentDto>>() {}));

        return comments;
    }


    public List<CommentDto> getAllCommentsByPersonId(String id) {
        HttpRequest<CommentDto> request = createRequest(endpointGetAllByPersonId())
                .setUrlProperty("id", id);

        List<CommentDto> comments = (List<CommentDto>) sendRequest(request, new HttpResponseJsonImpl(new TypeReference<List<CommentDto>>() {}));

        return comments;
    }

    public List<CommentDto> getAllCommentsByType(String entityType, String typeId) {
        HttpRequest<CommentDto> request = createRequest(endpointGetForType())
                .setUrlProperty("typeName", entityType)
                .setUrlProperty("typeId", typeId);

        List<CommentDto> comments = (List<CommentDto>) sendRequest(request, new HttpResponseJsonImpl(new TypeReference<List<CommentDto>>() {}));

        return comments;
    }

    public List<CommentDto> getAllCommentsByType(String entityType) {
        HttpRequest<CommentDto> request = createRequest(endpointGetAllForType())
                .setUrlProperty("typeName", entityType);

        List<CommentDto> comments = (List<CommentDto>) sendRequest(request, new HttpResponseJsonImpl(new TypeReference<List<CommentDto>>() {}));

        return comments;
    }

    public void deleteById(String commentId) {
        HttpRequest<CommentDto> request = createRequest(endpointDeleteForId())
                .setUrlProperty("id", commentId);

        sendRequest(request);
    }

    public void deleteByType(String entityType, String typeId) {
        HttpRequest<CommentDto> request = createRequest(endpointDeleteForType())
                .setUrlProperty("typeName", entityType)
                .setUrlProperty("typeId", typeId);

        sendRequest(request);
    }

    public void deleteAllByType(String entityType) {
        HttpRequest<CommentDto> request = createRequest(endpointDeleteAllForType())
                .setUrlProperty("typeName", entityType);

        sendRequest(request);
    }

    public void deleteAllByPersonId(String id) {
        HttpRequest<CommentDto> request = createRequest(endpointDeleteAllByPersonId())
                .setUrlProperty("id", id);

        sendRequest(request);
    }




}
