package com.netikras.studies.studentbuddy.api.constants;

import com.netikras.tools.common.remote.RemoteEndpoint;
import com.netikras.tools.common.remote.http.HttpRequest;

public class AdminPersonConstants {

    public static final String BASE_URL = "/mgmt/person";

    public static final String ADM_PERS_URL_CREATE = "/";
    public static final String ADM_PERS_URL_DELETE_BY_ID = "/id/{id}";
    public static final String ADM_PERS_URL_UPDATE = "/";

    private static RemoteEndpoint getBaseEndpoint() {
        return new RemoteEndpoint()
                .setBaseUrl("/api" + BASE_URL)
                .addHeader("content-type", "application/json")
                ;
    }

    /**
     * {@value #ADM_PERS_URL_CREATE}
     */
    public static RemoteEndpoint endpointCreatePerson() {
        return getBaseEndpoint()
                .setMethodUrl(ADM_PERS_URL_CREATE)
                .setMethod(HttpRequest.HttpMethod.POST)
                ;
    }

    /**
     * {@value #ADM_PERS_URL_UPDATE}
     */
    public static RemoteEndpoint endpointUpdatePerson() {
        return getBaseEndpoint()
                .setMethodUrl(ADM_PERS_URL_UPDATE)
                .setMethod(HttpRequest.HttpMethod.PUT)
                ;
    }

    /**
     * {@value #ADM_PERS_URL_DELETE_BY_ID}
     */
    public static RemoteEndpoint endpointDeletePersonById() {
        return getBaseEndpoint()
                .setMethodUrl(ADM_PERS_URL_DELETE_BY_ID)
                .setMethod(HttpRequest.HttpMethod.DELETE)
                ;
    }

}
