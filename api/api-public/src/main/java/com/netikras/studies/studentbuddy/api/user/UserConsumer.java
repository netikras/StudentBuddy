package com.netikras.studies.studentbuddy.api.user;

import com.netikras.studies.studentbuddy.api.comments.CommentConstants;
import com.netikras.studies.studentbuddy.api.comments.CommentsConsumer;
import com.netikras.studies.studentbuddy.core.data.api.dto.meta.CommentDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.meta.UserDto;
import com.netikras.tools.common.exception.HttpException;
import com.netikras.tools.common.remote.AuthenticationDetail;
import com.netikras.tools.common.remote.RemoteEndpoint;
import com.netikras.tools.common.remote.RemoteEndpointServer;
import com.netikras.tools.common.remote.http.HttpRequest;
import com.netikras.tools.common.remote.http.HttpResponse;
import com.netikras.tools.common.remote.http.RestClient;
import com.netikras.tools.common.remote.http.impl.json.HttpRequestJsonImpl;
import com.netikras.tools.common.remote.http.impl.json.HttpResponseJsonImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserConsumer {


    private final Logger logger = LoggerFactory.getLogger(this.getClass());

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

    public UserDto login(AuthenticationDetail authenticationDetail) {
        RemoteEndpoint preparedEndpoint = UserConstants.endpointLogin().applyServer(getServer());
        HttpRequest<AuthenticationDetail> request = new HttpRequestJsonImpl<>()
                .digestEndpointConfiguration(preparedEndpoint)
                .setObject(authenticationDetail)
                ;

        HttpResponse response = RestClient.sendRequest(request, new HttpResponseJsonImpl(UserDto.class));

        if (response.isResponseSuccess()) {
            return response.getObject(UserDto.class);
        }

        throw new HttpException(request, response);
    }


}
