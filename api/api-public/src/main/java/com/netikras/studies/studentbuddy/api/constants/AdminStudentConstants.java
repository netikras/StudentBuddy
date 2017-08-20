package com.netikras.studies.studentbuddy.api.constants;

import com.netikras.tools.common.remote.RemoteEndpoint;
import com.netikras.tools.common.remote.http.HttpRequest;

public class AdminStudentConstants {

    public static final String BASE_URL = "/mgmt/student";

    public static final String ADM_STUD_CREATE = "/";
    public static final String ADM_STUD_DELETE_BY_ID = "/id/{id}";
    public static final String ADM_STUD_CREATE_GROUP = "/group";
    public static final String ADM_STUD_DELETE_GROUP_BY_ID = "/group/id/{id}";
    public static final String ADM_STUD_ADD_ALL_STUDENTS_TO_GROUP = "/group/add/all/{groupId}/{studentIds}";
    public static final String ADM_STUD_ADD_STUDENT_TO_GROUP = "/group/add/single/{groupId}/{studentId}";
    public static final String ADM_STUD_REMOVE_STUDENT_FROM_GROUP = "/group/remove/single/{groupId}/{studentId}";
    public static final String ADM_STUD_REMOVE_ALL_STUDENTS_FROM_GROUP = "/group/remove/all/{groupId}/{studentIds}";
    public static final String ADM_STUD_CREATE_GUEST = "/guest";
    public static final String ADM_STUD_CREATE_GUEST_BY_PERSON_ID_AND_LECTURE_ID = "/guest/person/id/{personId}/lecture/id/{lectureId}";
    public static final String ADM_STUD_CREATE_GUEST_BY_PERSON_IDENT_AND_LECTURE_ID = "/guest/person/identifier/{personId}/lecture/id/{lectureId}";
    public static final String ADM_STUD_DELETE_GUEST_BY_ID = "/guest/id/{id}";

    private static RemoteEndpoint getBaseEndpoint() {
        return new RemoteEndpoint()
                .setBaseUrl("/api" + BASE_URL)
                .addHeader("content-type", "application/json")
                ;
    }

    /**
     * {@value ADM_STUD_CREATE}
     */
    public static RemoteEndpoint endpointCreateStudent() {
        return getBaseEndpoint()
                .setMethodUrl(ADM_STUD_CREATE)
                .setMethod(HttpRequest.HttpMethod.POST)
                ;
    }

    /**
     * {@value ADM_STUD_DELETE_BY_ID}
     */
    public static RemoteEndpoint endpointDeleteStudentById() {
        return getBaseEndpoint()
                .setMethodUrl(ADM_STUD_DELETE_BY_ID)
                .setMethod(HttpRequest.HttpMethod.DELETE)
                ;
    }

    /**
     * {@value ADM_STUD_CREATE_GROUP}
     */
    public static RemoteEndpoint endpointCreateGroup() {
        return getBaseEndpoint()
                .setMethodUrl(ADM_STUD_CREATE_GROUP)
                .setMethod(HttpRequest.HttpMethod.POST)
                ;
    }

    /**
     * {@value ADM_STUD_DELETE_GROUP_BY_ID}
     */
    public static RemoteEndpoint endpointDeleteGroupById() {
        return getBaseEndpoint()
                .setMethodUrl(ADM_STUD_DELETE_GROUP_BY_ID)
                .setMethod(HttpRequest.HttpMethod.DELETE)
                ;
    }

    /**
     * {@value ADM_STUD_ADD_ALL_STUDENTS_TO_GROUP}
     */
    public static RemoteEndpoint endpointAddAllStudentsToGroup() {
        return getBaseEndpoint()
                .setMethodUrl(ADM_STUD_ADD_ALL_STUDENTS_TO_GROUP)
                .setMethod(HttpRequest.HttpMethod.PUT)
                ;
    }

    /**
     * {@value ADM_STUD_ADD_STUDENT_TO_GROUP}
     */
    public static RemoteEndpoint endpointAddStudentToGroup() {
        return getBaseEndpoint()
                .setMethodUrl(ADM_STUD_ADD_STUDENT_TO_GROUP)
                .setMethod(HttpRequest.HttpMethod.PUT)
                ;
    }

    /**
     * {@value ADM_STUD_REMOVE_STUDENT_FROM_GROUP}
     */
    public static RemoteEndpoint endpointRemoveStudentFromGroup() {
        return getBaseEndpoint()
                .setMethodUrl(ADM_STUD_REMOVE_STUDENT_FROM_GROUP)
                .setMethod(HttpRequest.HttpMethod.DELETE)
                ;
    }

    /**
     * {@value ADM_STUD_REMOVE_ALL_STUDENTS_FROM_GROUP}
     */
    public static RemoteEndpoint endpointRemoveAllStudentsFromGroup() {
        return getBaseEndpoint()
                .setMethodUrl(ADM_STUD_REMOVE_ALL_STUDENTS_FROM_GROUP)
                .setMethod(HttpRequest.HttpMethod.DELETE)
                ;
    }

    /**
     * {@value ADM_STUD_CREATE_GUEST}
     */
    public static RemoteEndpoint endpointCreateGuest() {
        return getBaseEndpoint()
                .setMethodUrl(ADM_STUD_CREATE_GUEST)
                .setMethod(HttpRequest.HttpMethod.POST)
                ;
    }

    /**
     * {@value ADM_STUD_CREATE_GUEST_BY_PERSON_ID_AND_LECTURE_ID}
     */
    public static RemoteEndpoint endpointCreateGuestByPersonIdAndLectureId() {
        return getBaseEndpoint()
                .setMethodUrl(ADM_STUD_CREATE_GUEST_BY_PERSON_ID_AND_LECTURE_ID)
                .setMethod(HttpRequest.HttpMethod.POST)
                ;
    }

    /**
     * {@value ADM_STUD_CREATE_GUEST_BY_PERSON_IDENT_AND_LECTURE_ID}
     */
    public static RemoteEndpoint endpointCreateGuestByPersonIdentifierAndLectureId() {
        return getBaseEndpoint()
                .setMethodUrl(ADM_STUD_CREATE_GUEST_BY_PERSON_IDENT_AND_LECTURE_ID)
                .setMethod(HttpRequest.HttpMethod.POST)
                ;
    }

    /**
     * {@value ADM_STUD_DELETE_GUEST_BY_ID}
     */
    public static RemoteEndpoint endpointDeleteGuestById() {
        return getBaseEndpoint()
                .setMethodUrl(ADM_STUD_DELETE_GUEST_BY_ID)
                .setMethod(HttpRequest.HttpMethod.DELETE)
                ;
    }
}
