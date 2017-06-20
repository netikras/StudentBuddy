package com.netikras.studies.studentbuddy.commons.exception;


import com.netikras.studies.studentbuddy.commons.tools.http.HttpStatus;

/**
 * Created by netikras on 17.3.7.
 */
public interface StudBudExceptionBase {


    Throwable getWrappedException();

    StudBudExceptionBase setWrappedException(Throwable wrappedException);



    StudBudExceptionBase setStatusCode(HttpStatus code);

    StudBudExceptionBase setStatusCode(org.springframework.http.HttpStatus code);

    StudBudExceptionBase setStatusCode(int code);

    StudBudExceptionBase setMessage1(String message);

    StudBudExceptionBase setMessage2(String message);

    StudBudExceptionBase setProbableCause(String cause);

    StudBudExceptionBase setUrl(String url);

    StudBudExceptionBase setDeveloperMessage(String message);

    StudBudExceptionBase setErrorCode(int code);







    String getUrl();

    int getStatusCode();

    String getMessage1();

    String getMessage2();

    String getProbableCause();

    String getDeveloperMessage();

    int getErrorCode();


}
