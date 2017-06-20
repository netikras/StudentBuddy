package com.netikras.studies.studentbuddy.commons.tools.http;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by netikras on 16.11.22.
 */
public class HttpResponse {

    private int status;
    private String message;
    private Object object;

    private Map<String, List<String>> headers;

    public HttpResponse() {
        setStatus(0);
        setObject(null);
        setHeaders(new HashMap<>());
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObject() {
        return object;
    }

    public <T> T getObject(Class T) {
        return (T) getObject();
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Map<String, List<String>> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, List<String>> headers) {
        this.headers = headers;
    }

    public void addHeader(String name, String value) {
        List<String> headersList = getHeaders(name);
        if (headersList == null) {
            headersList = new ArrayList<>();
            getHeaders().put(name, headersList);
        }
        headersList.add(value);
    }

    public List<String> getHeaders(String name) {
        return getHeaders().get(name);
    }

}
