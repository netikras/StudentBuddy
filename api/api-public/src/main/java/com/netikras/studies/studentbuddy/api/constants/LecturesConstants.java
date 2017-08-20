package com.netikras.studies.studentbuddy.api.constants;

import com.netikras.tools.common.remote.RemoteEndpoint;
import com.netikras.tools.common.remote.http.HttpRequest;

/**
 * {@value TIME_UNITS_LEGEND}
 */

public class LecturesConstants {

    private static final String TIME_UNITS_LEGEND = "Time units (ISO): M(onths), d(ays), H(ours), m(inutes), s(econds)";

    public static final String BASE_URL = "/lecture";

    public static final String LECTURE_URL_CREATE = "/";
    public static final String LECTURE_URL_UPDATE = "/";
    public static final String LECTURE_URL_DELETE_BY_ID = "/id/{id}";
    public static final String LECTURE_URL_GET_BY_ID = "/id/{id}";

    /** {@value TIME_UNITS_LEGEND} */
    public static final String LECTURE_URL_GET_ALL_BY_GROUP_ID_STARTING_IN = "/group/id/{groupId}/in/t/{timeUnits}/{value}";

    /** {@value TIME_UNITS_LEGEND} */
    public static final String LECTURE_URL_GET_ALL_BY_STUDENT_ID_STARTING_IN = "/student/id/{studentId}/in/t/{timeUnits}/{value}";

    /** {@value TIME_UNITS_LEGEND} */
    public static final String LECTURE_URL_GET_ALL_BY_LECTURER_ID_STARTING_IN = "/lecturer/id/{lecturerId}/in/t/{timeUnits}/{value}";

    /** {@value TIME_UNITS_LEGEND} */
    public static final String LECTURE_URL_GET_ALL_BY_ROOM_ID_STARTING_IN = "/room/id/{roomId}/in/t/{timeUnits}/{value}";

    public static final String LECTURE_URL_GET_ALL_BY_GROUP_ID_STARTING_BETWEEN = "/group/id/{groupId}/between/{after}/{before}";
    public static final String LECTURE_URL_GET_ALL_BY_STUDENT_ID_STARTING_BETWEEN = "/student/id/{studentId}/between/{after}/{before}";
    public static final String LECTURE_URL_GET_ALL_BY_LECTURER_ID_STARTING_BETWEEN = "/lecturer/id/{lecturerId}/between/{after}/{before}";
    public static final String LECTURE_URL_GET_ALL_BY_ROOM_ID_STARTING_BETWEEN = "/room/id/{roomId}/between/{after}/{before}";

    private static RemoteEndpoint getBaseEndpoint() {
        return new RemoteEndpoint()
                .setBaseUrl("/api" + BASE_URL)
                .addHeader("content-type", "application/json")
                ;
    }

    /**
     * {@value LECTURE_URL_CREATE}
     */
    public static RemoteEndpoint endpointCreateLecture() {
        return getBaseEndpoint()
                .setMethodUrl(LECTURE_URL_CREATE)
                .setMethod(HttpRequest.HttpMethod.POST)
                ;
    }

    /**
     * {@value LECTURE_URL_UPDATE}
     */
    public static RemoteEndpoint endpointUpdateLecture() {
        return getBaseEndpoint()
                .setMethodUrl(LECTURE_URL_UPDATE)
                .setMethod(HttpRequest.HttpMethod.PUT)
                ;
    }

    /**
     * {@value LECTURE_URL_DELETE_BY_ID}
     */
    public static RemoteEndpoint endpointDeleteById() {
        return getBaseEndpoint()
                .setMethodUrl(LECTURE_URL_DELETE_BY_ID)
                .setMethod(HttpRequest.HttpMethod.DELETE)
                ;
    }

    /**
     * {@value LECTURE_URL_GET_BY_ID}
     */
    public static RemoteEndpoint endpointGetById() {
        return getBaseEndpoint()
                .setMethodUrl(LECTURE_URL_GET_BY_ID)
                .setMethod(HttpRequest.HttpMethod.GET)
                ;
    }

    /**
     * {@value LECTURE_URL_GET_ALL_BY_LECTURER_ID_STARTING_BETWEEN}
     */
    public static RemoteEndpoint endpointGetByLecturerIdStartingBetween() {
        return getBaseEndpoint()
                .setMethodUrl(LECTURE_URL_GET_ALL_BY_LECTURER_ID_STARTING_BETWEEN)
                .setMethod(HttpRequest.HttpMethod.GET)
                ;
    }

    /**
     * {@value LECTURE_URL_GET_ALL_BY_LECTURER_ID_STARTING_IN}
     */
    public static RemoteEndpoint endpointGetByLecturerIdStartingIn() {
        return getBaseEndpoint()
                .setMethodUrl(LECTURE_URL_GET_ALL_BY_LECTURER_ID_STARTING_IN)
                .setMethod(HttpRequest.HttpMethod.GET)
                ;
    }

    /**
     * {@value LECTURE_URL_GET_ALL_BY_GROUP_ID_STARTING_BETWEEN}
     */
    public static RemoteEndpoint endpointGetByGroupIdStartingBetween() {
        return getBaseEndpoint()
                .setMethodUrl(LECTURE_URL_GET_ALL_BY_GROUP_ID_STARTING_BETWEEN)
                .setMethod(HttpRequest.HttpMethod.GET)
                ;
    }

    /**
     * {@value LECTURE_URL_GET_ALL_BY_GROUP_ID_STARTING_IN}
     */
    public static RemoteEndpoint endpointGetByGroupIdStartingIn() {
        return getBaseEndpoint()
                .setMethodUrl(LECTURE_URL_GET_ALL_BY_GROUP_ID_STARTING_IN)
                .setMethod(HttpRequest.HttpMethod.GET)
                ;
    }

    /**
     * {@value LECTURE_URL_GET_ALL_BY_STUDENT_ID_STARTING_IN}
     */
    public static RemoteEndpoint endpointGetByStudentIdStartingIn() {
        return getBaseEndpoint()
                .setMethodUrl(LECTURE_URL_GET_ALL_BY_STUDENT_ID_STARTING_IN)
                .setMethod(HttpRequest.HttpMethod.GET)
                ;
    }

    /**
     * {@value LECTURE_URL_GET_ALL_BY_STUDENT_ID_STARTING_BETWEEN}
     */
    public static RemoteEndpoint endpointGetByStudentIdStartingBetween() {
        return getBaseEndpoint()
                .setMethodUrl(LECTURE_URL_GET_ALL_BY_STUDENT_ID_STARTING_BETWEEN)
                .setMethod(HttpRequest.HttpMethod.GET)
                ;
    }

    /**
     * {@value LECTURE_URL_GET_ALL_BY_ROOM_ID_STARTING_IN}
     */
    public static RemoteEndpoint endpointGetByRoomIdStartingIn() {
        return getBaseEndpoint()
                .setMethodUrl(LECTURE_URL_GET_ALL_BY_ROOM_ID_STARTING_IN)
                .setMethod(HttpRequest.HttpMethod.GET)
                ;
    }

    /**
     * {@value LECTURE_URL_GET_ALL_BY_ROOM_ID_STARTING_BETWEEN}
     */
    public static RemoteEndpoint endpointGetByRoomIdStartingBetween() {
        return getBaseEndpoint()
                .setMethodUrl(LECTURE_URL_GET_ALL_BY_ROOM_ID_STARTING_BETWEEN)
                .setMethod(HttpRequest.HttpMethod.GET)
                ;
    }

}
