package com.netikras.studies.studentbuddy.api.constants;

import com.netikras.tools.common.remote.RemoteEndpoint;
import com.netikras.tools.common.remote.http.HttpRequest;

public class TestsConstants {

    public static final String BASE_URL = "/tests";

    public static final String TESTS_URL_CREATE = "/";
    public static final String TESTS_URL_CREATE_BY_LECTURE_ID = "/due/lecture/id/{id}";
    public static final String TESTS_URL_GET_BY_ID = "/id/{id}";
    public static final String TESTS_URL_UPDATE = "/";
    public static final String TESTS_URL_DELETE_BY_ID = "/id/{id}";

    public static final String TESTS_URL_GET_ALL_FOR_DISCIPLINE_ID = "/discipline/id/{id}";
    public static final String TESTS_URL_GET_ALL_FOR_DISCIPLINE_ID_BETWEEN = "/discipline/id/{id}/starts/between/{after}/{before}";

    public static final String TESTS_URL_GET_ALL_FOR_DISCIPLINE_ID_AND_GROUP_ID = "/discipline/id/{disciplineId}/group/id/{groupId}";
    public static final String TESTS_URL_GET_ALL_FOR_DISCIPLINE_ID_AND_GROUP_ID_BETWEEN =
            "/discipline/id/{disciplineId}/group/id/{groupId}/starts/between/{after}/{before}";

    private static RemoteEndpoint getBaseEndpoint() {
        return new RemoteEndpoint()
                .setBaseUrl("/api" + BASE_URL)
                .addHeader("content-type", "application/json")
                ;
    }

    /**
     * {@value TESTS_URL_CREATE}
     */
    public static RemoteEndpoint endpointCreateTest() {
        return getBaseEndpoint()
                .setMethodUrl(TESTS_URL_CREATE)
                .setMethod(HttpRequest.HttpMethod.POST)
                ;
    }

    /**
     * {@value TESTS_URL_CREATE_BY_LECTURE_ID}
     */
    public static RemoteEndpoint endpointCreateTestByLectureId() {
        return getBaseEndpoint()
                .setMethodUrl(TESTS_URL_CREATE_BY_LECTURE_ID)
                .setMethod(HttpRequest.HttpMethod.POST)
                ;
    }

    /**
     * {@value TESTS_URL_GET_BY_ID}
     */
    public static RemoteEndpoint endpointGetTestById() {
        return getBaseEndpoint()
                .setMethodUrl(TESTS_URL_GET_BY_ID)
                .setMethod(HttpRequest.HttpMethod.GET)
                ;
    }

    /**
     * {@value TESTS_URL_UPDATE}
     */
    public static RemoteEndpoint endpointUpdate() {
        return getBaseEndpoint()
                .setMethodUrl(TESTS_URL_UPDATE)
                .setMethod(HttpRequest.HttpMethod.PUT)
                ;
    }

    /**
     * {@value TESTS_URL_DELETE_BY_ID}
     */
    public static RemoteEndpoint endpointDeleteTestById() {
        return getBaseEndpoint()
                .setMethodUrl(TESTS_URL_DELETE_BY_ID)
                .setMethod(HttpRequest.HttpMethod.DELETE)
                ;
    }

    /**
     * {@value TESTS_URL_GET_ALL_FOR_DISCIPLINE_ID}
     */
    public static RemoteEndpoint endpointGetAllTestsByDisciplineId() {
        return getBaseEndpoint()
                .setMethodUrl(TESTS_URL_GET_ALL_FOR_DISCIPLINE_ID)
                .setMethod(HttpRequest.HttpMethod.GET)
                ;
    }

    /**
     * {@value TESTS_URL_GET_ALL_FOR_DISCIPLINE_ID_BETWEEN}
     */
    public static RemoteEndpoint endpointGetAllTestsByDisciplineIdStartingBetween() {
        return getBaseEndpoint()
                .setMethodUrl(TESTS_URL_GET_ALL_FOR_DISCIPLINE_ID_BETWEEN)
                .setMethod(HttpRequest.HttpMethod.GET)
                ;
    }

    /**
     * {@value TESTS_URL_GET_ALL_FOR_DISCIPLINE_ID_AND_GROUP_ID}
     */
    public static RemoteEndpoint endpointGetAllTestsByDisciplineIdAndGroupId() {
        return getBaseEndpoint()
                .setMethodUrl(TESTS_URL_GET_ALL_FOR_DISCIPLINE_ID_AND_GROUP_ID)
                .setMethod(HttpRequest.HttpMethod.GET)
                ;
    }

    /**
     * {@value TESTS_URL_GET_ALL_FOR_DISCIPLINE_ID_AND_GROUP_ID_BETWEEN}
     */
    public static RemoteEndpoint endpointGetAllTestsByDisciplineIdAndGroupIdStartingBetween() {
        return getBaseEndpoint()
                .setMethodUrl(TESTS_URL_GET_ALL_FOR_DISCIPLINE_ID_AND_GROUP_ID_BETWEEN)
                .setMethod(HttpRequest.HttpMethod.GET)
                ;
    }

}
