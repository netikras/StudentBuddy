package com.netikras.studies.studentbuddy.api.constants;

import com.netikras.tools.common.remote.RemoteEndpoint;
import com.netikras.tools.common.remote.http.HttpRequest;

public class StudentConstants {

    public static final String BASE_URL = "/student";

    public static final String STUD_URL_GET_BY_ID = "/id/{id}";
    public static final String STUD_URL_UPDATE = "/";

    public static final String STUD_URL_GET_GROUP_BY_ID = "/group/id/{id}";
    public static final String STUD_URL_GET_ALL_GROUPS = "/group";
    public static final String STUD_URL_GET_ALL_STUDENTS_BY_GROUP_ID = "/all/group/id/{id}";

    public static final String STUD_URL_GET_GUEST_BY_ID = "/guest/id/{id}";
    public static final String STUD_URL_UPDATE_GUEST = "/guest";

    private static RemoteEndpoint getBaseEndpoint() {
        return new RemoteEndpoint()
                .setBaseUrl("/api" + BASE_URL)
                .addHeader("content-type", "application/json")
                ;
    }

    /**
     * {@value STUD_URL_GET_BY_ID}
     */
    public static RemoteEndpoint endpointGetStudentById() {
        return getBaseEndpoint()
                .setMethodUrl(STUD_URL_GET_BY_ID)
                .setMethod(HttpRequest.HttpMethod.GET)
                ;
    }

    /**
     * {@value STUD_URL_UPDATE}
     */
    public static RemoteEndpoint endpointUpdateStudent() {
        return getBaseEndpoint()
                .setMethodUrl(STUD_URL_UPDATE)
                .setMethod(HttpRequest.HttpMethod.PUT)
                ;
    }

    /**
     * {@value STUD_URL_GET_GROUP_BY_ID}
     */
    public static RemoteEndpoint endpointGetGroupById() {
        return getBaseEndpoint()
                .setMethodUrl(STUD_URL_GET_GROUP_BY_ID)
                .setMethod(HttpRequest.HttpMethod.GET)
                ;
    }

    /**
     * {@value STUD_URL_GET_ALL_GROUPS}
     */
    public static RemoteEndpoint endpointGetAllGroups() {
        return getBaseEndpoint()
                .setMethodUrl(STUD_URL_GET_ALL_GROUPS)
                .setMethod(HttpRequest.HttpMethod.GET)
                ;
    }

    /**
     * {@value STUD_URL_GET_ALL_STUDENTS_BY_GROUP_ID}
     */
    public static RemoteEndpoint endpointGetAllStudentsByGroupId() {
        return getBaseEndpoint()
                .setMethodUrl(STUD_URL_GET_ALL_STUDENTS_BY_GROUP_ID)
                .setMethod(HttpRequest.HttpMethod.GET)
                ;
    }

    /**
     * {@value STUD_URL_GET_GUEST_BY_ID}
     */
    public static RemoteEndpoint endpointGetGuestById() {
        return getBaseEndpoint()
                .setMethodUrl(STUD_URL_GET_GUEST_BY_ID)
                .setMethod(HttpRequest.HttpMethod.GET)
                ;
    }

    /**
     * {@value STUD_URL_UPDATE_GUEST}
     */
    public static RemoteEndpoint endpointUpdateGuest() {
        return getBaseEndpoint()
                .setMethodUrl(STUD_URL_UPDATE_GUEST)
                .setMethod(HttpRequest.HttpMethod.PUT)
                ;
    }

}
