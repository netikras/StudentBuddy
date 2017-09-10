package com.netikras.studies.studentbuddy.commons.exception.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netikras.tools.common.exception.ErrorBody;
import com.netikras.tools.common.exception.FriendlyExceptionBase;
import com.netikras.tools.common.remote.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by netikras on 16.11.23.
 */

public abstract class AbstractStudBudExceptionHandler {

    protected abstract int getDefaultStatusCode();

    protected ObjectMapper objectMapper = new ObjectMapper();

    protected ErrorBody processException(HttpServletResponse response, HttpServletRequest request, Exception exception) {
        ErrorBody errorBody;

        errorBody = FriendlyExceptionBase.digestToErrorBody(exception);
        request.removeAttribute(HandlerMapping.PRODUCIBLE_MEDIA_TYPES_ATTRIBUTE);

        response.setContentType("application/json");

        int defaultStatusCode = getDefaultStatusCode();

        if (defaultStatusCode < 100) defaultStatusCode = HttpStatus.INTERNAL_SERVER_ERROR.getCode();

        response.setStatus(errorBody.getStatus() < 100 ? defaultStatusCode : errorBody.getStatus());
        System.out.println("Returning errorBody: " + errorBody);


        return errorBody;
    }

}
