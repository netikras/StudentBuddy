package com.netikras.studies.studentbuddy.api;

import com.netikras.tools.common.remote.http.HttpRequest;
import com.netikras.tools.common.remote.http.HttpResponse;
import com.netikras.tools.common.remote.http.HttpStatus;
import com.netikras.tools.common.remote.http.RequestListener;
import com.netikras.tools.common.remote.http.impl.json.HttpResponseJsonImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResponseListener extends RequestListener {

    private Logger logger;

    public ResponseListener() {
        this.logger = LoggerFactory.getLogger(this.getClass());
    }

    public ResponseListener(Logger logger) {
        this.logger = logger;
    }



    @Override
    public Object onBeforeSend(HttpRequest request, HttpResponse response) {
        logger.info("Before SEND {} to {}", request.getMethod(), request.getUrl());
        return null;
    }

    @Override
    public Object onResponse(HttpRequest request, HttpResponse response) {
        HttpResponseJsonImpl responseJson = (HttpResponseJsonImpl) response;
        logger.info("Response received: {} ({})", HttpStatus.resolveStatusCode(responseJson.getStatus()), responseJson.getStatus());
        if (responseJson.getErrorBody() != null) {
            logger.warn("Error body: {}", responseJson.getErrorBody());
        }
        if (responseJson.getErrorMessage() != null) {
            logger.warn("Error message: {}", responseJson.getErrorMessage());
        }
        return null;
    }

    @Override
    public Object onServerErrorResponse(HttpRequest request, HttpResponse response) {
        return super.onServerErrorResponse(request, response);
    }

    @Override
    public Object onClientErrorResponse(HttpRequest request, HttpResponse response) {
        return super.onClientErrorResponse(request, response);
    }

    @Override
    public Object onSuccessResponse(HttpRequest request, HttpResponse response) {
        return super.onSuccessResponse(request, response);
    }

    @Override
    public Object onInfoResponse(HttpRequest request, HttpResponse response) {
        return super.onInfoResponse(request, response);
    }

    @Override
    public Object onRedirectResponse(HttpRequest request, HttpResponse response) {
        return super.onRedirectResponse(request, response);
    }
}