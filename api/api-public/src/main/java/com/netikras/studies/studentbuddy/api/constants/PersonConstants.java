package com.netikras.studies.studentbuddy.api.constants;

import com.netikras.tools.common.remote.RemoteEndpoint;
import com.netikras.tools.common.remote.http.HttpRequest;

public class PersonConstants {

    public static final String BASE_URL = "/person";

    public static final String PERSON_URL_GET_BY_ID = "/id/{id}";
    public static final String PERSON_URL_GET_BY_CODE = "/code/{code}";
    public static final String PERSON_URL_GET_BY_IDENTIFIER = "/id2/{id}";

    public static final String PERSON_URL_GET_ALL = "/all";
    public static final String PERSON_URL_GET_ALL_BY_FIRST_NAME = "/fname/{firstName}";
    public static final String PERSON_URL_GET_ALL_BY_LAST_NAME = "/lname/{lastName}";
    public static final String PERSON_URL_GET_ALL_BY_FIRST_AND_LAST_NAME = "/fname/{firstName}/lname/{lastName}";

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

    /**
     * {@value #PERSON_URL_GET_ALL}
     */
    public static RemoteEndpoint endpointGetAll() {
        return getBaseEndpoint()
                .setMethodUrl(PERSON_URL_GET_ALL)
                .setMethod(HttpRequest.HttpMethod.GET)
                ;
    }

    /**
     * {@value #PERSON_URL_GET_ALL_BY_FIRST_NAME}
     */
    public static RemoteEndpoint endpointGetAllByFirstName() {
        return getBaseEndpoint()
                .setMethodUrl(PERSON_URL_GET_ALL_BY_FIRST_NAME)
                .setMethod(HttpRequest.HttpMethod.GET)
                ;
    }

    /**
     * {@value #PERSON_URL_GET_ALL_BY_LAST_NAME}
     */
    public static RemoteEndpoint endpointGetAllByLastName() {
        return getBaseEndpoint()
                .setMethodUrl(PERSON_URL_GET_ALL_BY_LAST_NAME)
                .setMethod(HttpRequest.HttpMethod.GET)
                ;
    }

    /**
     * {@value #PERSON_URL_GET_ALL_BY_FIRST_AND_LAST_NAME}
     */
    public static RemoteEndpoint endpointGetAllByFirstAndLastName() {
        return getBaseEndpoint()
                .setMethodUrl(PERSON_URL_GET_ALL_BY_FIRST_AND_LAST_NAME)
                .setMethod(HttpRequest.HttpMethod.GET)
                ;
    }

}
