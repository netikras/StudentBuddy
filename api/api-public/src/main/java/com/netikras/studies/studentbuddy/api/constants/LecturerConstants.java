package com.netikras.studies.studentbuddy.api.constants;

import com.netikras.tools.common.remote.RemoteEndpoint;
import com.netikras.tools.common.remote.http.HttpRequest;

public class LecturerConstants {

    public static final String BASE_URL = "/lecturer";

    public static final String LECTURER_URL_GET_BY_ID = "/id/{id}";

    public static final String LECTURER_URL_GET_ALL_BY_DISCIPLINE_ID = "/discipline/id/{id}";

    public static final String LECTURER_URL_GET_BY_PERSON_ID = "/person/id/{id}";

    public static final String LECTURER_URL_UPDATE = "/";


    private static RemoteEndpoint getBaseEndpoint() {
        return new RemoteEndpoint()
                .setBaseUrl("/api" + BASE_URL)
                .addHeader("content-type", "application/json")
                ;
    }

    /**
     * {@value #LECTURER_URL_GET_BY_ID}
     */
    public static RemoteEndpoint endpointGetById() {
        return getBaseEndpoint()
                .setMethodUrl(LECTURER_URL_GET_BY_ID)
                .setMethod(HttpRequest.HttpMethod.GET)
                ;
    }

    /**
     * {@value #LECTURER_URL_GET_ALL_BY_DISCIPLINE_ID}
     */
    public static RemoteEndpoint endpointGetAllByDisciplineId() {
        return getBaseEndpoint()
                .setMethodUrl(LECTURER_URL_GET_ALL_BY_DISCIPLINE_ID)
                .setMethod(HttpRequest.HttpMethod.GET)
                ;
    }

    /**
     * {@value #LECTURER_URL_GET_BY_PERSON_ID}
     */
    public static RemoteEndpoint endpointGetByPersonId() {
        return getBaseEndpoint()
                .setMethodUrl(LECTURER_URL_GET_BY_PERSON_ID)
                .setMethod(HttpRequest.HttpMethod.GET)
                ;
    }

    /**
     * {@value #LECTURER_URL_UPDATE}
     */
    public static RemoteEndpoint endpointUpdate() {
        return getBaseEndpoint()
                .setMethodUrl(LECTURER_URL_UPDATE)
                .setMethod(HttpRequest.HttpMethod.PUT)
                ;
    }
}
