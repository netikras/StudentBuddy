package com.netikras.studies.studentbuddy.api.handlers;

import com.netikras.studies.studentbuddy.commons.exception.StudBudException;
import com.netikras.studies.studentbuddy.commons.exception.StudBudUncheckedException;
import com.netikras.tools.common.exception.ErrorBody;
import com.netikras.tools.common.remote.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class StudBudExceptionHandler {
    @ExceptionHandler(StudBudUncheckedException.class)
    public @ResponseBody
    ErrorBody limitationException(HttpServletResponse response, HttpServletRequest request, Exception exception) {
        ErrorBody errorBody;

        errorBody = StudBudException.digestToErrorBody(exception);
        request.removeAttribute(HandlerMapping.PRODUCIBLE_MEDIA_TYPES_ATTRIBUTE);

        response.setContentType("application/json");

        //response.setStatus(errorBody.getStatus() < 100 ? HttpStatus.TOO_MANY_REQUESTS.getCode() : errorBody.getStatus());
        response.setStatus(errorBody.getStatus() < 100 ? HttpStatus.INTERNAL_SERVER_ERROR.getCode() : errorBody.getStatus());
        System.out.println("Returning errorBody: "+errorBody);


        return errorBody;
    }


}
