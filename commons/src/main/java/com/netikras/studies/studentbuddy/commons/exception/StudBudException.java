package com.netikras.studies.studentbuddy.commons.exception;


import com.netikras.studies.studentbuddy.commons.tools.Converter;
import com.netikras.studies.studentbuddy.commons.tools.http.HttpStatus;

public class StudBudException extends Exception implements StudBudExceptionBase {

    private static final long serialVersionUID = -7777687824412011184L;


    private int statusCode;
    private int errorCode;
    private String message1;
    private String message2;
    private String probableCause;
    private String url;
    private String developerMessage;
    private Throwable wrappedException;


    public StudBudException() {
    }

    public StudBudException(String message) {
        setMessage1(message);
    }

    public StudBudException(int statusCode) {
        setStatusCode(statusCode);
    }

    public StudBudException(String message, int statusCode) {
        setMessage1(message);
        setStatusCode(statusCode);
    }

    public StudBudException(String message1, String message2, int statusCode, String probableCause) {
        setMessage1(message1);
        setMessage2(message2);
        setStatusCode(statusCode);
        setProbableCause(probableCause);
    }

    public StudBudException(String message1, String message2, int statusCode, String probableCause, String url, String developerMessage, int errorCode) {
        setMessage1(message1);
        setMessage2(message2);
        setStatusCode(statusCode);
        setProbableCause(probableCause);
        setUrl(url);
        setDeveloperMessage(developerMessage);
        setErrorCode(errorCode);
    }


    public Throwable getWrappedException() {
        return wrappedException;
    }

    public StudBudException setWrappedException(Throwable wrappedException) {
        this.wrappedException = wrappedException;
        wrappedException.printStackTrace();
        return this;
    }


    public StudBudException setStatusCode(HttpStatus code) {
        this.statusCode = code.getCode();
        return this;
    }

    public StudBudException setStatusCode(org.springframework.http.HttpStatus code) {
        this.statusCode = code.value();
        return this;
    }

    public StudBudException setStatusCode(int code) {
        this.statusCode = code;
        return this;
    }

    public StudBudException setMessage1(String message) {

        this.message1 = message;
        return this;
    }

    public StudBudException setMessage2(String message) {
        this.message2 = message;
        return this;
    }

    public StudBudException setProbableCause(String cause) {
        this.probableCause = cause;
        return this;
    }


    public StudBudException setUrl(String url) {
        this.url = url;
        return this;
    }


    public StudBudException setDeveloperMessage(String message) {
        this.developerMessage = message;
        return this;
    }

    public StudBudException setErrorCode(int code) {
        this.errorCode = code;
        return this;
    }


    public String getUrl() {
        return url;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getMessage1() {
        return message1;
    }

    public String getMessage2() {
        return message2;
    }

    public String getProbableCause() {
        return probableCause;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }


    public static final ErrorBody digestToErrorBody(Exception exception) {
        ErrorBody errorBody = new ErrorBody();
        StudBudException customException;


        if (exception == null) return null;

        try {
            customException = (StudBudException) exception;

            errorBody.setCausedBy(customException.getProbableCause());

            errorBody.setStatus(customException.getStatusCode());
            errorBody.setMessage1(customException.getMessage1());
            errorBody.setMessage2(customException.getMessage2());
            errorBody.setDeveloperMessage(customException.getDeveloperMessage());
            errorBody.setCode(customException.getErrorCode());
            errorBody.setUrl(customException.getUrl());


        } catch (ClassCastException e) {
            System.out.println(e.getLocalizedMessage());

            errorBody.setMessage1(exception.getLocalizedMessage());
            errorBody.setDeveloperMessage(Converter.throwableToString(exception));

        } catch (Exception e) {
            System.out.println("Cannot digest exception to ErrorBody: " + e.getLocalizedMessage());
            errorBody.setMessage1(exception.getLocalizedMessage());
        }


        return errorBody;
    }

    @Override
    public String toString() {
        return "StudBudException{" +
                "statusCode=" + statusCode +
                ", errorCode=" + errorCode +
                ", message1='" + message1 + '\'' +
                ", message2='" + message2 + '\'' +
                ", probableCause='" + probableCause + '\'' +
                ", url='" + url + '\'' +
                ", developerMessage='" + developerMessage + '\'' +
                ", wrappedException=" + wrappedException +
                '}';
    }
}
