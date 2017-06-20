package com.netikras.studies.studentbuddy.commons.model;


import com.netikras.studies.studentbuddy.commons.tools.AuthenticationDetail;
import com.netikras.studies.studentbuddy.commons.tools.http.HttpRequest;

/**
 * Created by netikras on 17.4.23.
 */
public class RemoteEndpointServer implements Identifiable {

    private String itemId;

    private String category;

    private HttpRequest.Protocol protocol = HttpRequest.Protocol.HTTP;
    private String address = "";
    private int port = 80;
    private String rootUrl = "";

    private AuthenticationDetail auth;


    @Override
    public String getItemId() {
        return this.itemId;
    }

    @Override
    public void setItemId(String id) {
        this.itemId = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public HttpRequest.Protocol getProtocol() {
        return protocol;
    }

    public RemoteEndpointServer setProtocol(HttpRequest.Protocol protocol) {
        this.protocol = protocol;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public RemoteEndpointServer setAddress(String address) {
        this.address = address;
        return this;
    }

    public int getPort() {
        return port;
    }

    public RemoteEndpointServer setPort(int port) {
        this.port = port;
        return this;
    }

    public String getRootUrl() {
        return rootUrl;
    }

    public RemoteEndpointServer setRootUrl(String rootUrl) {
        this.rootUrl = rootUrl;
        return this;
    }

    public AuthenticationDetail getAuth() {
        return auth;
    }

    public RemoteEndpointServer setAuth(AuthenticationDetail auth) {
        this.auth = auth;
        return this;
    }

    @Override
    public String toString() {
        return "RemoteEndpointServer{" +
                "itemId='" + itemId + '\'' +
                ", category='" + category + '\'' +
                ", protocol=" + protocol +
                ", address='" + address + '\'' +
                ", port=" + port +
                ", rootUrl='" + rootUrl + '\'' +
                ", auth=" + auth +
                '}';
    }
}
