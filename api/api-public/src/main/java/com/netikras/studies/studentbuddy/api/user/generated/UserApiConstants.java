package com.netikras.studies.studentbuddy.api.user.generated;

import com.netikras.tools.common.remote.RemoteEndpoint;
import com.netikras.tools.common.remote.http.HttpRequest;


/**
 * This class is generated automatically with Rest API preprocessor (netikras' commons).<br/>
 * It is not recommended to modify this file as it will be overwritten on next compile.<br/>
 * In case one needs to modify this class either modify its template or override its methods.
 */

public class UserApiConstants {

    public static final String BASE_URL = "/user";
    public static final String CTX_URL = "/api";


    private static RemoteEndpoint getBaseEndpoint() {
        return new RemoteEndpoint()
            .setBaseUrl(CTX_URL + BASE_URL)
            .addHeader("content-type", "application/json");
    }


    public static final String URL_USER_DTO_RETRIEVE = "/id/{id}";

    /**
     * {@value #URL_USER_DTO_RETRIEVE}
     */
    public static RemoteEndpoint endpointRetrieveUserDto() {
        return getBaseEndpoint()
            .setMethodUrl(URL_USER_DTO_RETRIEVE)
            .setMethod(HttpRequest.HttpMethod.GET)
        ;
    }

    public static final String URL_USER_DTO_UPDATE = "/";

    /**
     * {@value #URL_USER_DTO_UPDATE}
     */
    public static RemoteEndpoint endpointUpdateUserDto() {
        return getBaseEndpoint()
            .setMethodUrl(URL_USER_DTO_UPDATE)
            .setMethod(HttpRequest.HttpMethod.PUT)
        ;
    }

    public static final String URL_USER_DTO_DELETE = "/id/{id}";

    /**
     * {@value #URL_USER_DTO_DELETE}
     */
    public static RemoteEndpoint endpointDeleteUserDto() {
        return getBaseEndpoint()
            .setMethodUrl(URL_USER_DTO_DELETE)
            .setMethod(HttpRequest.HttpMethod.DELETE)
        ;
    }

    public static final String URL_USER_DTO_CREATE = "/";

    /**
     * {@value #URL_USER_DTO_CREATE}
     */
    public static RemoteEndpoint endpointCreateUserDto() {
        return getBaseEndpoint()
            .setMethodUrl(URL_USER_DTO_CREATE)
            .setMethod(HttpRequest.HttpMethod.POST)
        ;
    }

    public static final String URL_USER_DTO_LOGIN = "/login";

    /**
     * {@value #URL_USER_DTO_LOGIN}
     */
    public static RemoteEndpoint endpointLoginUserDto() {
        return getBaseEndpoint()
            .setMethodUrl(URL_USER_DTO_LOGIN)
            .setMethod(HttpRequest.HttpMethod.POST)
        ;
    }

    public static final String URL_USER_DTO_LOGOUT = "/logout";

    /**
     * {@value #URL_USER_DTO_LOGOUT}
     */
    public static RemoteEndpoint endpointLogoutUserDto() {
        return getBaseEndpoint()
            .setMethodUrl(URL_USER_DTO_LOGOUT)
            .setMethod(HttpRequest.HttpMethod.POST)
        ;
    }

    public static final String URL_RESOURCE_ACTION_DTO_GET_PERMITTED_ACTIONS = "/permissions";

    /**
     * {@value #URL_RESOURCE_ACTION_DTO_GET_PERMITTED_ACTIONS}
     */
    public static RemoteEndpoint endpointGetPermittedActionsResourceActionDto() {
        return getBaseEndpoint()
            .setMethodUrl(URL_RESOURCE_ACTION_DTO_GET_PERMITTED_ACTIONS)
            .setMethod(HttpRequest.HttpMethod.GET)
        ;
    }

    public static final String URL_USER_DTO_CHANGE_PASSWORD = "/id/{id}/password";

    /**
     * {@value #URL_USER_DTO_CHANGE_PASSWORD}
     */
    public static RemoteEndpoint endpointChangePasswordUserDto() {
        return getBaseEndpoint()
            .setMethodUrl(URL_USER_DTO_CHANGE_PASSWORD)
            .setMethod(HttpRequest.HttpMethod.PUT)
        ;
    }

    public static final String URL_USER_DTO_GET_BY_NAME = "/name/{name}";

    /**
     * {@value #URL_USER_DTO_GET_BY_NAME}
     */
    public static RemoteEndpoint endpointGetByNameUserDto() {
        return getBaseEndpoint()
            .setMethodUrl(URL_USER_DTO_GET_BY_NAME)
            .setMethod(HttpRequest.HttpMethod.GET)
        ;
    }

    public static final String URL_USER_DTO_GET_BY_PERSON = "/person/id/{id}";

    /**
     * {@value #URL_USER_DTO_GET_BY_PERSON}
     */
    public static RemoteEndpoint endpointGetByPersonUserDto() {
        return getBaseEndpoint()
            .setMethodUrl(URL_USER_DTO_GET_BY_PERSON)
            .setMethod(HttpRequest.HttpMethod.GET)
        ;
    }

    public static final String URL_USER_DTO_SEARCH_ALL_BY_USERNAME = "/search/username/{username}";

    /**
     * {@value #URL_USER_DTO_SEARCH_ALL_BY_USERNAME}
     */
    public static RemoteEndpoint endpointSearchAllByUsernameUserDto() {
        return getBaseEndpoint()
            .setMethodUrl(URL_USER_DTO_SEARCH_ALL_BY_USERNAME)
            .setMethod(HttpRequest.HttpMethod.GET)
        ;
    }

    public static final String URL_USER_DTO_SEARCH_ALL_BY_FIRST_NAME = "/search/fname/{fname}";

    /**
     * {@value #URL_USER_DTO_SEARCH_ALL_BY_FIRST_NAME}
     */
    public static RemoteEndpoint endpointSearchAllByFirstNameUserDto() {
        return getBaseEndpoint()
            .setMethodUrl(URL_USER_DTO_SEARCH_ALL_BY_FIRST_NAME)
            .setMethod(HttpRequest.HttpMethod.GET)
        ;
    }

    public static final String URL_USER_DTO_SEARCH_ALL_BY_LAST_NAME = "/search/lname/{lname}";

    /**
     * {@value #URL_USER_DTO_SEARCH_ALL_BY_LAST_NAME}
     */
    public static RemoteEndpoint endpointSearchAllByLastNameUserDto() {
        return getBaseEndpoint()
            .setMethodUrl(URL_USER_DTO_SEARCH_ALL_BY_LAST_NAME)
            .setMethod(HttpRequest.HttpMethod.GET)
        ;
    }

}
