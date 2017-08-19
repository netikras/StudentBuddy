package com.netikras.studies.studentbuddy.api.constants;

import com.netikras.tools.common.remote.RemoteEndpoint;
import com.netikras.tools.common.remote.http.HttpRequest;

public class PersonConstants {

    public static final String BASE_URL = "/person";

    public static final String PERSON_URL_GET_BY_ID = "/id/{id}";
    public static final String PERSON_URL_GET_BY_CODE = "/code/{code}";
    public static final String PERSON_URL_GET_BY_IDENTIFIER = "/id2/{id}";

    private static RemoteEndpoint getBaseEndpoint() {
        return new RemoteEndpoint()
                .setBaseUrl("/api" + BASE_URL)
                .addHeader("content-type", "application/json")
                ;
    }

    /**
     * {@value #PERSON_URL_GET_BY_ID}
     */
    public static RemoteEndpoint endpointGetById() {
        return getBaseEndpoint()
                .setMethodUrl(PERSON_URL_GET_BY_ID)
                .setMethod(HttpRequest.HttpMethod.GET)
                ;
    }

    /**
     * {@value #PERSON_URL_GET_BY_IDENTIFIER}
     */
    public static RemoteEndpoint endpointGetByIdetifier() {
        return getBaseEndpoint()
                .setMethodUrl(PERSON_URL_GET_BY_IDENTIFIER)
                .setMethod(HttpRequest.HttpMethod.GET)
                ;
    }

    /**
     * {@value #PERSON_URL_GET_BY_CODE}
     */
    public static RemoteEndpoint endpointGetByCode() {
        return getBaseEndpoint()
                .setMethodUrl(PERSON_URL_GET_BY_CODE)
                .setMethod(HttpRequest.HttpMethod.GET)
                ;
    }

}
