package com.netikras.studies.studentbuddy.api.handlers;

import com.netikras.studies.studentbuddy.commons.exception.StudBudUncheckedException;
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
public class StudBudUnchechedExceptionHandler extends AbstractStudBudExceptionHandler {

    public StudBudUnchechedExceptionHandler() {

    }

    @ExceptionHandler(StudBudUncheckedException.class)
    @ResponseBody
    @Override
    public ErrorBody processException(HttpServletResponse response, HttpServletRequest request, Exception exception) {
        return super.processException(response, request, exception);
    }



    @Override
    protected int getDefaultStatusCode() {
        return HttpStatus.INTERNAL_SERVER_ERROR.getCode();
    }
}
