package com.netikras.studies.studentbuddy.api.user.generated;

import com.netikras.tools.common.remote.RemoteEndpoint;
import com.netikras.tools.common.remote.http.HttpRequest;


/**
 * This class is generated automatically with Rest API preprocessor (netikras' commons).<br/>
 * It is not recommended to modify this file as it will be overwritten on next compile.<br/>
 * In case one needs to modify this class either modify its template or override its methods.
 */

public class StudentApiConstants {

    public static final String BASE_URL = "/student";
    public static final String CTX_URL = "/api";


    private static RemoteEndpoint getBaseEndpoint() {
        return new RemoteEndpoint()
            .setBaseUrl(CTX_URL + BASE_URL)
            .addHeader("content-type", "application/json");
    }


    public static final String URL_STUDENT_DTO_RETRIEVE = "/id/{id}";

    /**
     * {@value #URL_STUDENT_DTO_RETRIEVE}
     */
    public static RemoteEndpoint endpointRetrieveStudentDto() {
        return getBaseEndpoint()
            .setMethodUrl(URL_STUDENT_DTO_RETRIEVE)
            .setMethod(HttpRequest.HttpMethod.GET)
        ;
    }

    public static final String URL_STUDENT_DTO_UPDATE = "/";

    /**
     * {@value #URL_STUDENT_DTO_UPDATE}
     */
    public static RemoteEndpoint endpointUpdateStudentDto() {
        return getBaseEndpoint()
            .setMethodUrl(URL_STUDENT_DTO_UPDATE)
            .setMethod(HttpRequest.HttpMethod.PUT)
        ;
    }

    public static final String URL_STUDENTS_GROUP_DTO_RETRIEVE = "/group/id/{id}";

    /**
     * {@value #URL_STUDENTS_GROUP_DTO_RETRIEVE}
     */
    public static RemoteEndpoint endpointRetrieveStudentsGroupDto() {
        return getBaseEndpoint()
            .setMethodUrl(URL_STUDENTS_GROUP_DTO_RETRIEVE)
            .setMethod(HttpRequest.HttpMethod.GET)
        ;
    }

    public static final String URL_LECTURE_GUEST_DTO_RETRIEVE = "/guest/id/{id}";

    /**
     * {@value #URL_LECTURE_GUEST_DTO_RETRIEVE}
     */
    public static RemoteEndpoint endpointRetrieveLectureGuestDto() {
        return getBaseEndpoint()
            .setMethodUrl(URL_LECTURE_GUEST_DTO_RETRIEVE)
            .setMethod(HttpRequest.HttpMethod.GET)
        ;
    }

    public static final String URL_LECTURE_GUEST_DTO_UPDATE = "/guest/";

    /**
     * {@value #URL_LECTURE_GUEST_DTO_UPDATE}
     */
    public static RemoteEndpoint endpointUpdateLectureGuestDto() {
        return getBaseEndpoint()
            .setMethodUrl(URL_LECTURE_GUEST_DTO_UPDATE)
            .setMethod(HttpRequest.HttpMethod.PUT)
        ;
    }

    public static final String URL_STUDENT_DTO_GET_BY_PERSON_ID = "/person/id/{id}";

    /**
     * {@value #URL_STUDENT_DTO_GET_BY_PERSON_ID}
     */
    public static RemoteEndpoint endpointGetByPersonIdStudentDto() {
        return getBaseEndpoint()
            .setMethodUrl(URL_STUDENT_DTO_GET_BY_PERSON_ID)
            .setMethod(HttpRequest.HttpMethod.GET)
        ;
    }

    public static final String URL_STUDENTS_GROUP_DTO_GET_BY_TITLE = "/group/title/{title}";

    /**
     * {@value #URL_STUDENTS_GROUP_DTO_GET_BY_TITLE}
     */
    public static RemoteEndpoint endpointGetByTitleStudentsGroupDto() {
        return getBaseEndpoint()
            .setMethodUrl(URL_STUDENTS_GROUP_DTO_GET_BY_TITLE)
            .setMethod(HttpRequest.HttpMethod.GET)
        ;
    }

    public static final String URL_STUDENTS_GROUP_DTO_GET_ALL = "/group/all";

    /**
     * {@value #URL_STUDENTS_GROUP_DTO_GET_ALL}
     */
    public static RemoteEndpoint endpointGetAllStudentsGroupDto() {
        return getBaseEndpoint()
            .setMethodUrl(URL_STUDENTS_GROUP_DTO_GET_ALL)
            .setMethod(HttpRequest.HttpMethod.GET)
        ;
    }

    public static final String URL_STUDENT_DTO_GET_ALL_BY_GROUP = "/all/group/id/{id}";

    /**
     * {@value #URL_STUDENT_DTO_GET_ALL_BY_GROUP}
     */
    public static RemoteEndpoint endpointGetAllByGroupStudentDto() {
        return getBaseEndpoint()
            .setMethodUrl(URL_STUDENT_DTO_GET_ALL_BY_GROUP)
            .setMethod(HttpRequest.HttpMethod.GET)
        ;
    }

    public static final String URL_STUDENT_DTO_SEARCH_ALL_BY_FIRST_NAME = "/search/fname/{fname}";

    /**
     * {@value #URL_STUDENT_DTO_SEARCH_ALL_BY_FIRST_NAME}
     */
    public static RemoteEndpoint endpointSearchAllByFirstNameStudentDto() {
        return getBaseEndpoint()
            .setMethodUrl(URL_STUDENT_DTO_SEARCH_ALL_BY_FIRST_NAME)
            .setMethod(HttpRequest.HttpMethod.GET)
        ;
    }

    public static final String URL_STUDENT_DTO_SEARCH_ALL_BY_LAST_NAME = "/search/lname/{fname}";

    /**
     * {@value #URL_STUDENT_DTO_SEARCH_ALL_BY_LAST_NAME}
     */
    public static RemoteEndpoint endpointSearchAllByLastNameStudentDto() {
        return getBaseEndpoint()
            .setMethodUrl(URL_STUDENT_DTO_SEARCH_ALL_BY_LAST_NAME)
            .setMethod(HttpRequest.HttpMethod.GET)
        ;
    }

    public static final String URL_STUDENTS_GROUP_DTO_SEARCH_ALL_BY_TITLE = "/group/search/title/{title}";

    /**
     * {@value #URL_STUDENTS_GROUP_DTO_SEARCH_ALL_BY_TITLE}
     */
    public static RemoteEndpoint endpointSearchAllByTitleStudentsGroupDto() {
        return getBaseEndpoint()
            .setMethodUrl(URL_STUDENTS_GROUP_DTO_SEARCH_ALL_BY_TITLE)
            .setMethod(HttpRequest.HttpMethod.GET)
        ;
    }

    public static final String URL_LECTURE_GUEST_DTO_SEARCH_BY_LAST_NAME = "/guest/search/lname/{lname}";

    /**
     * {@value #URL_LECTURE_GUEST_DTO_SEARCH_BY_LAST_NAME}
     */
    public static RemoteEndpoint endpointSearchByLastNameLectureGuestDto() {
        return getBaseEndpoint()
            .setMethodUrl(URL_LECTURE_GUEST_DTO_SEARCH_BY_LAST_NAME)
            .setMethod(HttpRequest.HttpMethod.GET)
        ;
    }

    public static final String URL_LECTURE_GUEST_DTO_SEARCH_BY_FIRST_NAME = "/guest/search/fname/{fname}";

    /**
     * {@value #URL_LECTURE_GUEST_DTO_SEARCH_BY_FIRST_NAME}
     */
    public static RemoteEndpoint endpointSearchByFirstNameLectureGuestDto() {
        return getBaseEndpoint()
            .setMethodUrl(URL_LECTURE_GUEST_DTO_SEARCH_BY_FIRST_NAME)
            .setMethod(HttpRequest.HttpMethod.GET)
        ;
    }

}
