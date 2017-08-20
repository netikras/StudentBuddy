package com.netikras.studies.studentbuddy.api.constants;

import com.netikras.tools.common.remote.RemoteEndpoint;
import com.netikras.tools.common.remote.http.HttpRequest;

public class AssignmentConstants {

    public static final String BASE_URL = "/assignment";

    public static final String ASSIGN_URL_CREATE = "/";
    public static final String ASSIGN_URL_UPDATE = "/";
    public static final String ASSIGN_URL_GET_BY_ID = "/id/{id}";
    public static final String ASSIGN_URL_DELETE_BY_ID = "/id/{id}";

    public static final String ASSIGN_URL_GET_ALL_BY_GROUP_ID_BETWEEN = "/group/id/{groupId}/between/{after}/{before}";
    public static final String ASSIGN_URL_GET_ALL_BY_DISCIPLINE_ID_BETWEEN = "/disc/id/{disciplineId}/between/{after}/{before}";
    public static final String ASSIGN_URL_GET_ALL_BY_STUDENT_ID_BETWEEN = "/student/id/{studentId}/between/{after}/{before}";

    public static final String ASSIGN_URL_GET_ALL_BY_DISCIPLINE_ID_AND_GROUP_ID_BETWEEN =
            "/disc/id/{disciplineId}/group/id/{groupId}/between/{after}/{before}";
    public static final String ASSIGN_URL_GET_ALL_BY_DISCIPLINE_ID_AND_STUDENT_ID_BETWEEN =
            "/disc/id/{disciplineId}/student/id/{studentId}/between/{after}/{before}";

    public static final String ASSIGN_URL_GET_ALL_BY_LECTURE_ID = "/lecture/id/{lectureId}";


    public static final String ASSIGN_URL_CREATE_BY_LECTURE_ID = "/due/lecture/id/{id}";

    private static RemoteEndpoint getBaseEndpoint() {
        return new RemoteEndpoint()
                .setBaseUrl("/api" + BASE_URL)
                .addHeader("content-type", "application/json")
                ;
    }

    /**
     * {@value ASSIGN_URL_CREATE}
     */
    public static RemoteEndpoint endpointCreateAssignment() {
        return getBaseEndpoint()
                .setMethodUrl(ASSIGN_URL_CREATE)
                .setMethod(HttpRequest.HttpMethod.POST)
                ;
    }

    /**
     * {@value ASSIGN_URL_UPDATE}
     */
    public static RemoteEndpoint endpointUpdateAssignment() {
        return getBaseEndpoint()
                .setMethodUrl(ASSIGN_URL_UPDATE)
                .setMethod(HttpRequest.HttpMethod.PUT)
                ;
    }

    /**
     * {@value ASSIGN_URL_GET_BY_ID}
     */
    public static RemoteEndpoint endpointGetAssignmentById() {
        return getBaseEndpoint()
                .setMethodUrl(ASSIGN_URL_GET_BY_ID)
                .setMethod(HttpRequest.HttpMethod.GET)
                ;
    }

    /**
     * {@value ASSIGN_URL_DELETE_BY_ID}
     */
    public static RemoteEndpoint endpointDeleteAssignmentById() {
        return getBaseEndpoint()
                .setMethodUrl(ASSIGN_URL_DELETE_BY_ID)
                .setMethod(HttpRequest.HttpMethod.DELETE)
                ;
    }

    /**
     * {@value ASSIGN_URL_CREATE_BY_LECTURE_ID}
     */
    public static RemoteEndpoint endpointCreateAssignmentByLectureId() {
        return getBaseEndpoint()
                .setMethodUrl(ASSIGN_URL_CREATE_BY_LECTURE_ID)
                .setMethod(HttpRequest.HttpMethod.POST)
                ;
    }

    /**
     * {@value ASSIGN_URL_GET_ALL_BY_LECTURE_ID}
     */
    public static RemoteEndpoint endpointGetAllByLectureId() {
        return getBaseEndpoint()
                .setMethodUrl(ASSIGN_URL_GET_ALL_BY_LECTURE_ID)
                .setMethod(HttpRequest.HttpMethod.GET)
                ;
    }

    /**
     * {@value ASSIGN_URL_GET_ALL_BY_GROUP_ID_BETWEEN}
     */
    public static RemoteEndpoint endpointGetAllByGroupIdDueBetween() {
        return getBaseEndpoint()
                .setMethodUrl(ASSIGN_URL_GET_ALL_BY_GROUP_ID_BETWEEN)
                .setMethod(HttpRequest.HttpMethod.GET)
                ;
    }

    /**
     * {@value ASSIGN_URL_GET_ALL_BY_DISCIPLINE_ID_BETWEEN}
     */
    public static RemoteEndpoint endpointGetAllByDisciplineIdDueBetween() {
        return getBaseEndpoint()
                .setMethodUrl(ASSIGN_URL_GET_ALL_BY_DISCIPLINE_ID_BETWEEN)
                .setMethod(HttpRequest.HttpMethod.GET)
                ;
    }

    /**
     * {@value ASSIGN_URL_GET_ALL_BY_STUDENT_ID_BETWEEN}
     */
    public static RemoteEndpoint endpointGetAllByStudentIdDueBetween() {
        return getBaseEndpoint()
                .setMethodUrl(ASSIGN_URL_GET_ALL_BY_STUDENT_ID_BETWEEN)
                .setMethod(HttpRequest.HttpMethod.GET)
                ;
    }

    /**
     * {@value ASSIGN_URL_GET_ALL_BY_DISCIPLINE_ID_AND_GROUP_ID_BETWEEN}
     */
    public static RemoteEndpoint endpointGetAllByDisciplineIdAndGroupIdDueBetween() {
        return getBaseEndpoint()
                .setMethodUrl(ASSIGN_URL_GET_ALL_BY_DISCIPLINE_ID_AND_GROUP_ID_BETWEEN)
                .setMethod(HttpRequest.HttpMethod.GET)
                ;
    }

    /**
     * {@value ASSIGN_URL_GET_ALL_BY_DISCIPLINE_ID_AND_STUDENT_ID_BETWEEN}
     */
    public static RemoteEndpoint endpointGetAllByDisciplineIdAndStudentIdDueBetween() {
        return getBaseEndpoint()
                .setMethodUrl(ASSIGN_URL_GET_ALL_BY_DISCIPLINE_ID_AND_STUDENT_ID_BETWEEN)
                .setMethod(HttpRequest.HttpMethod.GET)
                ;
    }

}
