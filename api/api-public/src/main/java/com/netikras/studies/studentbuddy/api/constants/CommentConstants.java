package com.netikras.studies.studentbuddy.api.constants;

import com.netikras.tools.common.remote.RemoteEndpoint;
import com.netikras.tools.common.remote.http.HttpRequest;

public class CommentConstants {

    public static final String BASE_URL = "/comments";

    public static final String COMMENT_URL_GET_FOR_TYPE = "/type/{typeName}/{typeId}";

    public static final String COMMENT_URL_GET_ALL_FOR_TYPE = "/type/{typeName}";

    public static final String COMMENT_URL_DELETE_FOR_TYPE = "/type/{typeName}/{typeId}";

    public static final String COMMENT_URL_DELETE_ALL_FOR_TYPE = "/type/{typeName}";

    public static final String COMMENT_URL_DELETE_BY_ID = "/id/{id}";

    public static final String COMMENT_URL_GET_BY_ID = "/id/{id}";

    public static final String COMMENT_URL_CREATE = "/";

    public static final String COMMENT_URL_CREATE_FOR_TYPE = "/type/{typeName}/{typeId}";

    public static final String COMMENT_URL_UPDATE_BY_ID = "/id/{id}";

    public static final String COMMENT_URL_GET_BY_TAG_VALUE = "/tag/value/{value}";

    public static final String COMMENT_URL_GET_BY_TAG_ID = "/tag/id/{id}";

    public static final String COMMENT_URL_GET_BY_PERSON_ID = "/person/id/{id}";

    public static final String COMMENT_URL_DELETE_BY_PERSON_ID = "/person/id/{id}";


    private static RemoteEndpoint getBaseEndpoint() {
        return new RemoteEndpoint()
                .setBaseUrl("/api" + BASE_URL)
                .addHeader("content-type", "application/json")
                ;
    }

    /**
     * {@value #COMMENT_URL_GET_FOR_TYPE}
     */
    public static RemoteEndpoint endpointGetForType() {
        return getBaseEndpoint()
                .setMethodUrl(COMMENT_URL_GET_FOR_TYPE)
                .setMethod(HttpRequest.HttpMethod.GET)
                ;
    }

    /**
     * {@value #COMMENT_URL_GET_ALL_FOR_TYPE}
     */
    public static RemoteEndpoint endpointGetAllForType() {
        return getBaseEndpoint()
                .setMethodUrl(COMMENT_URL_GET_ALL_FOR_TYPE)
                .setMethod(HttpRequest.HttpMethod.GET)
                ;
    }

    /**
     * {@value #COMMENT_URL_DELETE_FOR_TYPE}
     */
    public static RemoteEndpoint endpointDeleteForType() {
        return getBaseEndpoint()
                .setMethodUrl(COMMENT_URL_DELETE_FOR_TYPE)
                .setMethod(HttpRequest.HttpMethod.DELETE)
                ;
    }

    /**
     * {@value #COMMENT_URL_DELETE_ALL_FOR_TYPE}
     */
    public static RemoteEndpoint endpointDeleteAllForType() {
        return getBaseEndpoint()
                .setMethodUrl(COMMENT_URL_DELETE_ALL_FOR_TYPE)
                .setMethod(HttpRequest.HttpMethod.DELETE)
                ;
    }

    /**
     * {@value #COMMENT_URL_DELETE_BY_ID}
     */
    public static RemoteEndpoint endpointDeleteForId() {
        return getBaseEndpoint()
                .setMethodUrl(COMMENT_URL_DELETE_BY_ID)
                .setMethod(HttpRequest.HttpMethod.DELETE)
                ;
    }

    /**
     * {@value #COMMENT_URL_GET_BY_ID}
     */
    public static RemoteEndpoint endpointGetForId() {
        return getBaseEndpoint()
                .setMethodUrl(COMMENT_URL_GET_BY_ID)
                .setMethod(HttpRequest.HttpMethod.GET)
                ;
    }

    /**
     * {@value #COMMENT_URL_CREATE}
     */
    public static RemoteEndpoint endpointCreateNew() {
        return getBaseEndpoint()
                .setMethodUrl(COMMENT_URL_CREATE)
                .setMethod(HttpRequest.HttpMethod.POST)
                ;
    }

    /**
     * {@value #COMMENT_URL_CREATE_FOR_TYPE}
     */
    public static RemoteEndpoint endpointCreateNewForType() {
        return getBaseEndpoint()
                .setMethodUrl(COMMENT_URL_CREATE_FOR_TYPE)
                .setMethod(HttpRequest.HttpMethod.POST)
                ;
    }

    /**
     * {@value #COMMENT_URL_UPDATE_BY_ID}
     */
    public static RemoteEndpoint endpointUpdateById() {
        return getBaseEndpoint()
                .setMethodUrl(COMMENT_URL_UPDATE_BY_ID)
                .setMethod(HttpRequest.HttpMethod.PUT)
                ;
    }

    /**
     * {@value #COMMENT_URL_GET_BY_TAG_VALUE}
     */
    public static RemoteEndpoint endpointGetByTagValue() {
        return getBaseEndpoint()
                .setMethodUrl(COMMENT_URL_GET_BY_TAG_VALUE)
                .setMethod(HttpRequest.HttpMethod.GET)
                ;
    }

    /**
     * {@value #COMMENT_URL_GET_BY_TAG_ID}
     */
    public static RemoteEndpoint endpointGetByTagId() {
        return getBaseEndpoint()
                .setMethodUrl(COMMENT_URL_GET_BY_TAG_ID)
                .setMethod(HttpRequest.HttpMethod.GET)
                ;
    }

    /**
     * {@value #COMMENT_URL_GET_BY_PERSON_ID}
     */
    public static RemoteEndpoint endpointGetAllByPersonId() {
        return getBaseEndpoint()
                .setMethodUrl(COMMENT_URL_GET_BY_PERSON_ID)
                .setMethod(HttpRequest.HttpMethod.GET)
                ;
    }

    /**
     * {@value #COMMENT_URL_DELETE_BY_PERSON_ID}
     */
    public static RemoteEndpoint endpointDeleteAllByPersonId() {
        return getBaseEndpoint()
                .setMethodUrl(COMMENT_URL_DELETE_BY_PERSON_ID)
                .setMethod(HttpRequest.HttpMethod.DELETE)
                ;
    }

}
