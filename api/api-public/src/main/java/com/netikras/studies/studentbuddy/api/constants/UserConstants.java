package com.netikras.studies.studentbuddy.api.constants;

import com.netikras.tools.common.remote.RemoteEndpoint;
import com.netikras.tools.common.remote.http.HttpRequest;

public class UserConstants {

    public static final String BASE_URL = "/user";

    public static final String USER_URL_LOGIN = "/login";

    public static final String USER_URL_LOGOUT = "/logout";

    public static final String USER_URL_PERMISSIONS = "/permissions";

    public static final String USER_URL_UPDATE_BY_ID = "/id/{id}";

    public static final String USER_URL_CHANGE_PASSWORD = "/id/{id}/password";

    public static final String USER_URL_GET_BY_ID = "/id/{id}";

    public static final String USER_URL_GET_BY_NAME = "/name/{name}";

    public static final String USER_URL_GET_USER_BY_PERSON_ID = "/person/id/{id}";

    public static final String USER_URL_SEARCH_ALL_USERS_BY_USERNAME = "/search/username/{username}";

    public static final String USER_URL_SEARCH_ALL_USERS_BY_FIRST_NAME = "/search/fname/{fname}";

    public static final String USER_URL_SEARCH_ALL_USERS_BY_LAST_NAME = "/search/lname/{lname}";


    private static RemoteEndpoint getBaseEndpoint() {
        return new RemoteEndpoint()
                .setBaseUrl("/api" + BASE_URL)
                .addHeader("content-type", "application/json")
                ;
    }

    /**
     * {@value USER_URL_LOGIN}
     */
    public static RemoteEndpoint endpointLogin() {
        return getBaseEndpoint()
                .setMethodUrl(USER_URL_LOGIN)
                .setMethod(HttpRequest.HttpMethod.POST)
                ;
    }

    /**
     * {@value USER_URL_LOGOUT}
     */
    public static RemoteEndpoint endpointLogout() {
        return getBaseEndpoint()
                .setMethodUrl(USER_URL_LOGOUT)
                .setMethod(HttpRequest.HttpMethod.POST)
                ;
    }

    /**
     * {@value USER_URL_PERMISSIONS}<br/>
     *  PARAM: userId
     */
    public static RemoteEndpoint endpointUserPermissions() {
        return getBaseEndpoint()
                .setMethodUrl(USER_URL_PERMISSIONS)
                .setMethod(HttpRequest.HttpMethod.GET)
                ;
    }

    /**
     * {@value USER_URL_UPDATE_BY_ID}
     */
    public static RemoteEndpoint endpointUpdateById() {
        return getBaseEndpoint()
                .setMethodUrl(USER_URL_UPDATE_BY_ID)
                .setMethod(HttpRequest.HttpMethod.PUT)
                ;
    }

    /**
     * {@value USER_URL_CHANGE_PASSWORD}
     */
    public static RemoteEndpoint endpointChangePassword() {
        return getBaseEndpoint()
                .setMethodUrl(USER_URL_CHANGE_PASSWORD)
                .setMethod(HttpRequest.HttpMethod.PUT)
                ;
    }

    /**
     * {@value USER_URL_GET_BY_ID}
     */
    public static RemoteEndpoint endpointGetById() {
        return getBaseEndpoint()
                .setMethodUrl(USER_URL_GET_BY_ID)
                .setMethod(HttpRequest.HttpMethod.GET)
                ;
    }

    /**
     * {@value USER_URL_GET_BY_NAME}
     */
    public static RemoteEndpoint endpointGetByName() {
        return getBaseEndpoint()
                .setMethodUrl(USER_URL_GET_BY_NAME)
                .setMethod(HttpRequest.HttpMethod.GET)
                ;
    }

    /**
     * {@value USER_URL_GET_USER_BY_PERSON_ID}
     */
    public static RemoteEndpoint endpointGetByPersonId() {
        return getBaseEndpoint()
                .setMethodUrl(USER_URL_GET_USER_BY_PERSON_ID)
                .setMethod(HttpRequest.HttpMethod.GET)
                ;
    }


    // search

    /**
     * {@value USER_URL_SEARCH_ALL_USERS_BY_USERNAME}
     */
    public static RemoteEndpoint endpointSearchAllUsersByUsername() {
        return getBaseEndpoint()
                .setMethodUrl(USER_URL_SEARCH_ALL_USERS_BY_USERNAME)
                .setMethod(HttpRequest.HttpMethod.GET)
                ;
    }

    /**
     * {@value USER_URL_SEARCH_ALL_USERS_BY_FIRST_NAME}
     */
    public static RemoteEndpoint endpointSearchAllUsersByFirstName() {
        return getBaseEndpoint()
                .setMethodUrl(USER_URL_SEARCH_ALL_USERS_BY_FIRST_NAME)
                .setMethod(HttpRequest.HttpMethod.GET)
                ;
    }

    /**
     * {@value USER_URL_SEARCH_ALL_USERS_BY_LAST_NAME}
     */
    public static RemoteEndpoint endpointSearchAllUsersByLastName() {
        return getBaseEndpoint()
                .setMethodUrl(USER_URL_SEARCH_ALL_USERS_BY_LAST_NAME)
                .setMethod(HttpRequest.HttpMethod.GET)
                ;
    }

}
