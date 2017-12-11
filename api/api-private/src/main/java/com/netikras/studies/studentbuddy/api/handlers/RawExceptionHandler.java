package com.netikras.studies.studentbuddy.api.handlers;

import com.netikras.studies.studentbuddy.commons.exception.handler.AbstractStudBudExceptionHandler;
import com.netikras.tools.common.exception.ErrorBody;
import com.netikras.tools.common.exception.FriendlyExceptionBase;
import com.netikras.tools.common.remote.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class RawExceptionHandler extends AbstractStudBudExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    @Override
    protected ErrorBody processException(HttpServletResponse response, HttpServletRequest request, Exception exception) {
        ErrorBody errorBody;

        if (FriendlyExceptionBase.class.isAssignableFrom(exception.getClass())) {
            return super.processException(response, request, exception);
        }

        exception.printStackTrace();
        errorBody = new ErrorBody();
        errorBody.setMessage1("Generic error");
        errorBody.setMessage2("Unexpected error");
        errorBody.setDeveloperMessage("See log files: " + System.currentTimeMillis());
        errorBody.setCausedBy(request.getRequestURL().toString());

        errorBody.setStatus(getDefaultStatusCode());

        System.out.println("Returning errorBody: " + errorBody);

        request.removeAttribute(HandlerMapping.PRODUCIBLE_MEDIA_TYPES_ATTRIBUTE);
        response.setContentType("application/json");
        response.setStatus(errorBody.getStatus());

        System.out.println("Returning errorBody: " + errorBody);

        return errorBody;
    }


    @Override
    protected int getDefaultStatusCode() {
        return HttpStatus.INTERNAL_SERVER_ERROR.getCode();
    }
}
