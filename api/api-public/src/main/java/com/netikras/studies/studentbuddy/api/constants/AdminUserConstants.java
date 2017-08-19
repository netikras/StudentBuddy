package com.netikras.studies.studentbuddy.api.constants;

import com.netikras.tools.common.remote.RemoteEndpoint;
import com.netikras.tools.common.remote.http.HttpRequest;

public class AdminUserConstants {

    public static final String BASE_URL = "/mgmt/user";

    public static final String ADM_USR_URL_CREATE = "/new";
    public static final String ADM_USR_URL_DELETE_BY_ID = "/id/{id}";

    private static RemoteEndpoint getBaseEndpoint() {
        return new RemoteEndpoint()
                .setBaseUrl("/api" + BASE_URL)
                .addHeader("content-type", "application/json")
                ;
    }

    /**
     * {@value #ADM_USR_URL_CREATE}
     */
    public static RemoteEndpoint endpointCreateUser() {
        return getBaseEndpoint()
                .setMethodUrl(ADM_USR_URL_CREATE)
                .setMethod(HttpRequest.HttpMethod.POST)
                ;
    }

    /**
     * {@value #ADM_USR_URL_DELETE_BY_ID}
     */
    public static RemoteEndpoint endpointDeleteUserById() {
        return getBaseEndpoint()
                .setMethodUrl(ADM_USR_URL_DELETE_BY_ID)
                .setMethod(HttpRequest.HttpMethod.DELETE)
                ;
    }

}
