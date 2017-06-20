package com.netikras.studies.studentbuddy.commons.model;


import com.netikras.studies.studentbuddy.commons.tools.AuthenticationDetail;
import com.netikras.studies.studentbuddy.commons.tools.http.HttpRequest;

import java.io.Serializable;
import java.util.*;

public class RemoteEndpoint implements Identifiable, Serializable, Cloneable {


    public static final String DEFAULT_CHARSET = "UTF-8";
    public static final String DEFAULT_CONTENT_TYPE = "application/json";
    public static final String DEFAULT_ACCEPT = "application/json";

    public static final String DEFAULT_PRODUCES = "" + DEFAULT_CONTENT_TYPE + ";charset=" + DEFAULT_CHARSET;
    public static final String DEFAULT_CONSUMES = "" + DEFAULT_ACCEPT + ";charset=" + DEFAULT_CHARSET;

    private String id;

    private String protocol;
    private String address;
    private int port;
    private String rootUrl;
    private String baseUrl;
    private String methodUrl;
    private HttpRequest.HttpMethods method;
    private double priority;
    private Map<String, List<String>> headers;
    private Map<String, String> params;
    private Properties additionalProperties;
    private AuthenticationDetail authenticationDetail;

    public RemoteEndpoint() {
        this.headers = new HashMap<>();
        this.params = new HashMap<>();

        addHeader("Content-Type", DEFAULT_CONTENT_TYPE);
        addHeader("Charset", DEFAULT_CHARSET);
        addHeader("Accept", DEFAULT_ACCEPT);

        this.additionalProperties = new Properties();

        setProtocol("http");
        setMethod(HttpRequest.HttpMethods.GET);
        setAddress("");
        setRootUrl("");
        setBaseUrl("");
        setMethodUrl("");
    }


    public RemoteEndpoint copy() {
        try {
            return (RemoteEndpoint) clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }


    public RemoteEndpoint copy(RemoteEndpointServer server) {
        RemoteEndpoint endpoint = copy();
        if (endpoint == null) return null;
        if (server == null) return endpoint;

        endpoint.applyServer(server);
        return endpoint;
    }

    public RemoteEndpoint applyServer(RemoteEndpointServer server) {
        RemoteEndpoint endpoint = this;
        endpoint.setProtocol(server.getProtocol().name());
        endpoint.setAddress(server.getAddress());
        endpoint.setPort(server.getPort());
        endpoint.setRootUrl(server.getRootUrl());

        endpoint.setAuthenticationDetail(server.getAuth());

        return endpoint;
    }

    @Override
    public String getItemId() {
        return id;
    }

    @Override
    public void setItemId(String id) {
        this.id = id;
    }

    public HttpRequest.HttpMethods getMethod() {
        return method;
    }

    public RemoteEndpoint setMethod(HttpRequest.HttpMethods method) {
        this.method = method;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public RemoteEndpoint setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getRootUrl() {
        return rootUrl;
    }

    public void setRootUrl(String rootUrl) {
        this.rootUrl = rootUrl;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public RemoteEndpoint setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    public int getPort() {
        return this.port;
    }

    public RemoteEndpoint setPort(int port) {
        this.port = port;
        return this;
    }

    public String getProtocol() {
        return protocol;
    }

    public RemoteEndpoint setProtocol(String protocol) {
        this.protocol = protocol;
        return this;
    }

    public String getMethodUrl() {
        return methodUrl;
    }

    public RemoteEndpoint setMethodUrl(String methodUrl) {
        this.methodUrl = methodUrl;
        return this;
    }

    public Map<String, List<String>> getHeaders() {
        return headers;
    }

    public RemoteEndpoint setHeaders(Map<String, List<String>> headers) {
        this.headers = headers;
        return this;
    }

    public RemoteEndpoint addHeader(String name, String value) {
        if (name == null || name.isEmpty()) return this;

        List<String> values = getHeaders().get(name);

        if (values == null) {
            values = new ArrayList<>(2);
            getHeaders().put(name, values);
        }

        values.add(value);
        return this;
    }

    public List<String> getHeaderValues(String name) {
        return getHeaders().get(name);
    }

    public String getHeaderValue(String name) {
        List<String> headers = getHeaders().get(name);
        if (headers != null && headers.size() > 0) {
            return headers.get(0);
        }
        return null;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public RemoteEndpoint setParams(Map<String, String> params) {
        this.params = params;
        return this;
    }

    public RemoteEndpoint addParam(String name, String value) {
        if (name == null || name.isEmpty()) return this;

        getParams().put(name, value);
        return this;
    }

    public String getParam(String name) {
        return getParams().get(name);
    }

    public String getId() {
        return id;
    }

    public RemoteEndpoint setId(String id) {
        this.id = id;
        return this;
    }

    public Properties getAdditionalProperties() {
        return additionalProperties;
    }

    public String getAdditionalProerty(String name) {
        String value = null;
        if (getAdditionalProperties() != null) {
            value = getAdditionalProperties().getProperty(name);
        }

        return value;
    }

    public RemoteEndpoint setAdditionalProperties(Properties additionalProperties) {
        this.additionalProperties = additionalProperties;
        return this;
    }

    public RemoteEndpoint addProperty(String name, String value) {
        if (getAdditionalProperties() == null) {
            setAdditionalProperties(new Properties());
        }

        getAdditionalProperties().setProperty(name, value);
        return this;
    }

    public String getUrlString() {
        StringBuilder urlStringBuilder = new StringBuilder();

        urlStringBuilder.append(getProtocol()).append("://");
        urlStringBuilder.append(getAddress());
        if (getPort() > 0)
            urlStringBuilder.append(":").append(getPort());
        urlStringBuilder.append(getRootUrl());
        urlStringBuilder.append(getBaseUrl());

        return urlStringBuilder.toString();
    }

    public double getPriority() {
        return priority;
    }

    public RemoteEndpoint setPriority(double priority) {
        this.priority = priority;
        return this;
    }

    public AuthenticationDetail getAuthenticationDetail() {
        return authenticationDetail;
    }

    public RemoteEndpoint setAuthenticationDetail(AuthenticationDetail authenticationDetail) {
        this.authenticationDetail = authenticationDetail;
        return this;
    }


    @Override
    public String toString() {
        return "RemoteEndpoint{" +
                "id='" + id + '\'' +
                ", protocol='" + protocol + '\'' +
                ", address='" + address + '\'' +
                ", port=" + port +
                ", rootUrl='" + rootUrl + '\'' +
                ", baseUrl='" + baseUrl + '\'' +
                ", methodUrl='" + methodUrl + '\'' +
                ", method=" + method +
                ", priority=" + priority +
                ", headers=" + headers +
                ", params=" + params +
                ", additionalProperties=" + additionalProperties +
                ", authenticationDetail=" + authenticationDetail +
                '}';
    }
}
