package com.netikras.studies.studentbuddy.api.comments;

import com.fasterxml.jackson.core.type.TypeReference;
import com.netikras.studies.studentbuddy.core.data.api.dto.meta.CommentDto;
import com.netikras.tools.common.exception.FriendlyUncheckedException;
import com.netikras.tools.common.exception.HttpException;
import com.netikras.tools.common.remote.AuthenticationDetail;
import com.netikras.tools.common.remote.RemoteEndpoint;
import com.netikras.tools.common.remote.RemoteEndpointServer;
import com.netikras.tools.common.remote.http.HttpRequest;
import com.netikras.tools.common.remote.http.HttpResponse;
import com.netikras.tools.common.remote.http.RestClient;
import com.netikras.tools.common.remote.http.impl.json.HttpRequestJsonImpl;
import com.netikras.tools.common.remote.http.impl.json.HttpResponseJsonImpl;

import java.util.List;

public class CommentsConsumer {

    protected RemoteEndpointServer getServer() {
        return new RemoteEndpointServer()
                .setProtocol(HttpRequest.Protocol.HTTP)
                .setAddress("localhost")
                .setPort(8080)
                .setRootUrl("/stubu/api")
                .setAuth(new AuthenticationDetail()
                        .setUsername("system")
                        .setPassword("system")
                )
                ;
    }

    public CommentDto createComment(CommentDto commentDto) {
        RemoteEndpoint preparedEndpoint = CommentConstants.endpointCreateNew()
                .applyServer(getServer());
        HttpRequest<CommentDto> request = new HttpRequestJsonImpl<>()
                .digestEndpointConfiguration(preparedEndpoint)
                .setObject(commentDto);

        HttpResponse response = RestClient.sendRequest(request);

        if (response.isResponseSuccess()) {
            return response.getObject(CommentDto.class);
        }

        throw new HttpException(request, response);
    }


    public CommentDto getCommentById(String id) {
        RemoteEndpoint preparedEndpoint = CommentConstants.endpointGetForId()
                .applyServer(getServer());
        HttpRequest<CommentDto> request = new HttpRequestJsonImpl<>()
                .digestEndpointConfiguration(preparedEndpoint)
                .setUrlProperty("id", id);

        HttpResponse response = RestClient.sendRequest(request);

        if (response.isResponseSuccess()) {
            return response.getObject(CommentDto.class);
        }

        throw new HttpException(request, response);
    }

    public List<CommentDto> getCommentByType(String entityType, String entityId) {
        RemoteEndpoint preparedEndpoint = CommentConstants.endpointGetForType()
                .applyServer(getServer());
        HttpRequest<CommentDto> request = new HttpRequestJsonImpl<>()
                .digestEndpointConfiguration(preparedEndpoint)
                .setUrlProperty("typeName", entityType)
                .setUrlProperty("typeId", entityId);

        HttpResponse response = RestClient.sendRequest(request);

        if (response.isResponseSuccess()) {
            return response.getObject(CommentDto.class);
        }

        throw new HttpException(request, response);
    }

    public List<CommentDto> getAllCommentsByType(String entityType) {
        RemoteEndpoint preparedEndpoint = CommentConstants.endpointGetForType()
                .applyServer(getServer());
        HttpRequest<CommentDto> request = new HttpRequestJsonImpl<>()
                .digestEndpointConfiguration(preparedEndpoint)
                .setUrlProperty("typeName", entityType);

        HttpResponse response = RestClient.sendRequest(request, new HttpResponseJsonImpl(new TypeReference<List<CommentDto>>(){}));

        if (response.isResponseSuccess()) {
            return response.getObject(CommentDto.class);
        }

        throw new HttpException(request, response);
    }




}
