package com.netikras.studies.studentbuddy.api.handlers;

import com.netikras.studies.studentbuddy.commons.exception.handler.AbstractStudBudExceptionHandler;
import com.netikras.tools.common.exception.ErrorBody;
import com.netikras.tools.common.remote.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class RawExceptionHandler extends AbstractStudBudExceptionHandler {

    public RawExceptionHandler() {

    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    @Override
    public ErrorBody processException(HttpServletResponse response, HttpServletRequest request, Exception exception) {

        ErrorBody errorBody = super.processException(response, request, exception);
//        try {
//            objectMapper.writeValue(response.getOutputStream(), errorBody);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        return errorBody;
    }



    @Override
    protected int getDefaultStatusCode() {
        return HttpStatus.INTERNAL_SERVER_ERROR.getCode();
    }
}
