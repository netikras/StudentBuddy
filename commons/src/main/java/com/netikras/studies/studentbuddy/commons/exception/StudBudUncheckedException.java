package com.netikras.studies.studentbuddy.commons.exception;


/**
 * Created by netikras on 17.3.7.
 */

import com.netikras.studies.studentbuddy.commons.tools.http.HttpStatus;

/**
 * Normally should not get out to userspace...
 */
public class StudBudUncheckedException extends RuntimeException implements StudBudExceptionBase {

    private static final long serialVersionUID = -7777687824412011184L;


    private int statusCode;
    private int errorCode;
    private String message1;
    private String message2;
    private String probableCause;
    private String url;
    private String developerMessage;
    private Throwable wrappedException;




    public StudBudUncheckedException(){}

    public StudBudUncheckedException(String message){
        setMessage1(message);
    }

    public StudBudUncheckedException(int statusCode){
        setStatusCode(statusCode);
    }

    public StudBudUncheckedException(String message, int statusCode){
        setMessage1(message);
        setStatusCode(statusCode);
    }

    public StudBudUncheckedException(String message1, String message2, int statusCode, String probableCause){
        setMessage1(message1);
        setMessage2(message2);
        setStatusCode(statusCode);
        setProbableCause(probableCause);
    }

    public StudBudUncheckedException(String message1, String message2, int statusCode, String probableCause,
                                     String url, String developerMessage, int errorCode){
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

    public StudBudUncheckedException setWrappedException(Throwable wrappedException) {
        this.wrappedException = wrappedException;
        if (wrappedException != null) {
            wrappedException.printStackTrace();
        }
        return this;
    }



    public StudBudUncheckedException setStatusCode(HttpStatus code) {
        this.statusCode = code.getCode();
        return this;
    }

    public StudBudUncheckedException setStatusCode(org.springframework.http.HttpStatus code) {
        this.statusCode = code.value();
        return this;
    }

    public StudBudUncheckedException setStatusCode(int code) {
        this.statusCode = code;
        return this;
    }

    public StudBudUncheckedException setMessage1(String message) {

        this.message1 = message;
        return this;
    }

    public StudBudUncheckedException setMessage2(String message) {
        this.message2 = message;
        return this;
    }

    public StudBudUncheckedException setProbableCause(String cause) {
        this.probableCause = cause;
        return this;
    }


    public StudBudUncheckedException setUrl(String url) {
        this.url = url;
        return this;
    }


    public StudBudUncheckedException setDeveloperMessage(String message) {
        this.developerMessage = message;
        return this;
    }

    public StudBudUncheckedException setErrorCode(int code){
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

    public int getErrorCode(){
        return errorCode;
    }


    @Override
    public String toString() {
        return "StudBudUncheckedException{" +
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
