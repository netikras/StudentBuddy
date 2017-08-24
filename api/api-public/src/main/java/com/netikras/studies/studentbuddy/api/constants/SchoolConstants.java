package com.netikras.studies.studentbuddy.api.constants;

import com.netikras.tools.common.remote.RemoteEndpoint;
import com.netikras.tools.common.remote.http.HttpRequest;

public class SchoolConstants {

    public static final String BASE_URL = "/school";

    public static final String SCHOOL_URL_GET_BY_ID = "/id/{id}";
    public static final String SCHOOL_URL_GET_ALL = "/all";

    public static final String SCHOOL_URL_CREATE = "/";
    public static final String SCHOOL_URL_UPDATE = "/";

    public static final String SCHOOL_URL_DELETE_BY_ID = "/id/{id}";

    // department
    public static final String SCHOOL_URL_GET_DEPARTMENT_BY_ID = "/department/id/{id}";

    public static final String SCHOOL_URL_CREATE_DEPARTMENT = "/department";
    public static final String SCHOOL_URL_UPDATE_DEPARTMENT = "/department";

    public static final String SCHOOL_URL_DELETE_DEPARTMENT_BY_ID = "/department/id/{id}";


    // personnel

    public static final String SCHOOL_URL_GET_ALL_PERSONNEL_BY_SCHOOL_ID = "/personnel/all/school/id/{id}";
    public static final String SCHOOL_URL_GET_PERSONNEL_BY_ID = "/personnel/id/{id}";
    public static final String SCHOOL_URL_DELETE_PERSONNEL_BY_ID = "/personnel/id/{id}";
    public static final String SCHOOL_URL_CREATE_PERSONNEL = "/personnel";
    public static final String SCHOOL_URL_UPDATE_PERSONNEL = "/personnel";

    // discipline

    public static final String SCHOOL_URL_GET_ALL_DISCIPLINES_BY_SCHOOL_ID = "/discipline/all/school/id/{id}";
    public static final String SCHOOL_URL_GET_DISCIPLINE_BY_ID = "/discipline/id/{id}";
    public static final String SCHOOL_URL_DELETE_DISCIPLINE_BY_ID = "/discipline/id/{id}";
    public static final String SCHOOL_URL_CREATE_DISCIPLINE = "/discipline";
    public static final String SCHOOL_URL_UPDATE_DISCIPLINE = "/discipline";

    // search

    public static final String SCHOOL_URL_SEARCH_ALL_BY_TITLE = "/search/title/{title}";
    public static final String SCHOOL_URL_SEARCH_ALL_DEPARTMENTS_BY_TITLE = "/department/search/title/{title}";
    public static final String SCHOOL_URL_SEARCH_ALL_PERSONNEL_BY_TITLE = "/personnel/search/title/{title}";
    public static final String SCHOOL_URL_SEARCH_ALL_PERSONNEL_BY_FIRST_NAME = "/personnel/search/fname/{fname}";
    public static final String SCHOOL_URL_SEARCH_ALL_PERSONNEL_BY_LAST_NAME = "/personnel/search/lname/{lname}";
    public static final String SCHOOL_URL_SEARCH_ALL_PERSONNEL_BY_PERSONAL_CODE = "/personnel/search/code/{code}";
    public static final String SCHOOL_URL_SEARCH_ALL_PERSONNEL_BY_IDENTIFIER = "/personnel/search/id2/{id}";
    public static final String SCHOOL_URL_SEARCH_ALL_DISCIPLINES_BY_TITLE = "/discipline/search/title/{title}";
    public static final String SCHOOL_URL_SEARCH_ALL_DISCIPLINES_BY_DESCRIPTION = "/discipline/search/description/{descr}";


    private static RemoteEndpoint getBaseEndpoint() {
        return new RemoteEndpoint()
                .setBaseUrl("/api" + BASE_URL)
                .addHeader("content-type", "application/json")
                ;
    }

    /**
     * {@value #SCHOOL_URL_GET_BY_ID}
     */
    public static RemoteEndpoint endpointGetById() {
        return getBaseEndpoint()
                .setMethodUrl(SCHOOL_URL_GET_BY_ID)
                .setMethod(HttpRequest.HttpMethod.GET)
                ;
    }

    /**
     * {@value #SCHOOL_URL_GET_ALL}
     */
    public static RemoteEndpoint endpointGetAll() {
        return getBaseEndpoint()
                .setMethodUrl(SCHOOL_URL_GET_ALL)
                .setMethod(HttpRequest.HttpMethod.GET)
                ;
    }

    /**
     * {@value #SCHOOL_URL_DELETE_BY_ID}
     */
    public static RemoteEndpoint endpointDeleteById() {
        return getBaseEndpoint()
                .setMethodUrl(SCHOOL_URL_DELETE_BY_ID)
                .setMethod(HttpRequest.HttpMethod.DELETE)
                ;
    }

    /**
     * {@value #SCHOOL_URL_UPDATE}
     */
    public static RemoteEndpoint endpointUpdateSchool() {
        return getBaseEndpoint()
                .setMethodUrl(SCHOOL_URL_UPDATE)
                .setMethod(HttpRequest.HttpMethod.PUT)
                ;
    }

    /**
     * {@value #SCHOOL_URL_CREATE}
     */
    public static RemoteEndpoint endpointCreateSchool() {
        return getBaseEndpoint()
                .setMethodUrl(SCHOOL_URL_CREATE)
                .setMethod(HttpRequest.HttpMethod.POST)
                ;
    }


    // department

    /**
     * {@value #SCHOOL_URL_GET_DEPARTMENT_BY_ID}
     */
    public static RemoteEndpoint endpointGetDepartmentById() {
        return getBaseEndpoint()
                .setMethodUrl(SCHOOL_URL_GET_DEPARTMENT_BY_ID)
                .setMethod(HttpRequest.HttpMethod.GET)
                ;
    }

    /**
     * {@value #SCHOOL_URL_DELETE_DEPARTMENT_BY_ID}
     */
    public static RemoteEndpoint endpointDeleteDepartmentById() {
        return getBaseEndpoint()
                .setMethodUrl(SCHOOL_URL_DELETE_DEPARTMENT_BY_ID)
                .setMethod(HttpRequest.HttpMethod.DELETE)
                ;
    }

    /**
     * {@value #SCHOOL_URL_CREATE_DEPARTMENT}
     */
    public static RemoteEndpoint endpointCreateDepartment() {
        return getBaseEndpoint()
                .setMethodUrl(SCHOOL_URL_CREATE_DEPARTMENT)
                .setMethod(HttpRequest.HttpMethod.POST)
                ;
    }

    /**
     * {@value #SCHOOL_URL_UPDATE_DEPARTMENT}
     */
    public static RemoteEndpoint endpointUpdateDepartment() {
        return getBaseEndpoint()
                .setMethodUrl(SCHOOL_URL_UPDATE_DEPARTMENT)
                .setMethod(HttpRequest.HttpMethod.PUT)
                ;
    }

    // discipline

    /**
     * {@value #SCHOOL_URL_CREATE_DISCIPLINE}
     */
    public static RemoteEndpoint endpointCreateDiscipline() {
        return getBaseEndpoint()
                .setMethodUrl(SCHOOL_URL_CREATE_DISCIPLINE)
                .setMethod(HttpRequest.HttpMethod.POST)
                ;
    }

    /**
     * {@value #SCHOOL_URL_UPDATE_DISCIPLINE}
     */
    public static RemoteEndpoint endpointUpdateDiscipline() {
        return getBaseEndpoint()
                .setMethodUrl(SCHOOL_URL_UPDATE_DISCIPLINE)
                .setMethod(HttpRequest.HttpMethod.PUT)
                ;
    }

    /**
     * {@value #SCHOOL_URL_GET_DISCIPLINE_BY_ID}
     */
    public static RemoteEndpoint endpointGetDisciplineById() {
        return getBaseEndpoint()
                .setMethodUrl(SCHOOL_URL_GET_DISCIPLINE_BY_ID)
                .setMethod(HttpRequest.HttpMethod.GET)
                ;
    }

    /**
     * {@value #SCHOOL_URL_GET_ALL_DISCIPLINES_BY_SCHOOL_ID}
     */
    public static RemoteEndpoint endpointGetAllDisciplinesBySchoolId() {
        return getBaseEndpoint()
                .setMethodUrl(SCHOOL_URL_GET_ALL_DISCIPLINES_BY_SCHOOL_ID)
                .setMethod(HttpRequest.HttpMethod.GET)
                ;
    }

    /**
     * {@value #SCHOOL_URL_DELETE_DISCIPLINE_BY_ID}
     */
    public static RemoteEndpoint endpointDeleteDisciplineById() {
        return getBaseEndpoint()
                .setMethodUrl(SCHOOL_URL_DELETE_DISCIPLINE_BY_ID)
                .setMethod(HttpRequest.HttpMethod.DELETE)
                ;
    }

    // personnel

    /**
     * {@value #SCHOOL_URL_CREATE_PERSONNEL}
     */
    public static RemoteEndpoint endpointCreatePersonnelMember() {
        return getBaseEndpoint()
                .setMethodUrl(SCHOOL_URL_CREATE_PERSONNEL)
                .setMethod(HttpRequest.HttpMethod.POST)
                ;
    }

    /**
     * {@value #SCHOOL_URL_UPDATE_PERSONNEL}
     */
    public static RemoteEndpoint endpointUpdatePersonnelMember() {
        return getBaseEndpoint()
                .setMethodUrl(SCHOOL_URL_UPDATE_PERSONNEL)
                .setMethod(HttpRequest.HttpMethod.PUT)
                ;
    }

    /**
     * {@value #SCHOOL_URL_GET_PERSONNEL_BY_ID}
     */
    public static RemoteEndpoint endpointGetPersonnelMemberById() {
        return getBaseEndpoint()
                .setMethodUrl(SCHOOL_URL_GET_PERSONNEL_BY_ID)
                .setMethod(HttpRequest.HttpMethod.GET)
                ;
    }

    /**
     * {@value #SCHOOL_URL_DELETE_PERSONNEL_BY_ID}
     */
    public static RemoteEndpoint endpointDeletePersonnelMemberById() {
        return getBaseEndpoint()
                .setMethodUrl(SCHOOL_URL_DELETE_PERSONNEL_BY_ID)
                .setMethod(HttpRequest.HttpMethod.DELETE)
                ;
    }

    /**
     * {@value #SCHOOL_URL_GET_ALL_PERSONNEL_BY_SCHOOL_ID}
     */
    public static RemoteEndpoint endpointGetAllPersonnelBySchoolId() {
        return getBaseEndpoint()
                .setMethodUrl(SCHOOL_URL_GET_ALL_PERSONNEL_BY_SCHOOL_ID)
                .setMethod(HttpRequest.HttpMethod.GET)
                ;
    }


    // search

    /**
     * {@value #SCHOOL_URL_SEARCH_ALL_BY_TITLE}
     */
    public static RemoteEndpoint endpointSearchAllSchoolsByTitle() {
        return getBaseEndpoint()
                .setMethodUrl(SCHOOL_URL_SEARCH_ALL_BY_TITLE)
                .setMethod(HttpRequest.HttpMethod.GET)
                ;
    }

    /**
     * {@value #SCHOOL_URL_SEARCH_ALL_DEPARTMENTS_BY_TITLE}
     */
    public static RemoteEndpoint endpointSearchAllDepartmentsByTitle() {
        return getBaseEndpoint()
                .setMethodUrl(SCHOOL_URL_SEARCH_ALL_DEPARTMENTS_BY_TITLE)
                .setMethod(HttpRequest.HttpMethod.GET)
                ;
    }

    /**
     * {@value #SCHOOL_URL_SEARCH_ALL_DISCIPLINES_BY_DESCRIPTION}
     */
    public static RemoteEndpoint endpointSearchAllDisciplinesByDescription() {
        return getBaseEndpoint()
                .setMethodUrl(SCHOOL_URL_SEARCH_ALL_DISCIPLINES_BY_DESCRIPTION)
                .setMethod(HttpRequest.HttpMethod.GET)
                ;
    }

    /**
     * {@value #SCHOOL_URL_SEARCH_ALL_DISCIPLINES_BY_TITLE}
     */
    public static RemoteEndpoint endpointSearchAllDisciplinesByTitle() {
        return getBaseEndpoint()
                .setMethodUrl(SCHOOL_URL_SEARCH_ALL_DISCIPLINES_BY_TITLE)
                .setMethod(HttpRequest.HttpMethod.GET)
                ;
    }

    /**
     * {@value #SCHOOL_URL_SEARCH_ALL_PERSONNEL_BY_TITLE}
     */
    public static RemoteEndpoint endpointSearchAllPersonnelByTitle() {
        return getBaseEndpoint()
                .setMethodUrl(SCHOOL_URL_SEARCH_ALL_PERSONNEL_BY_TITLE)
                .setMethod(HttpRequest.HttpMethod.GET)
                ;
    }

    /**
     * {@value #SCHOOL_URL_SEARCH_ALL_PERSONNEL_BY_IDENTIFIER}
     */
    public static RemoteEndpoint endpointSearchAllPersonnelByIdentifier() {
        return getBaseEndpoint()
                .setMethodUrl(SCHOOL_URL_SEARCH_ALL_PERSONNEL_BY_IDENTIFIER)
                .setMethod(HttpRequest.HttpMethod.GET)
                ;
    }

    /**
     * {@value #SCHOOL_URL_SEARCH_ALL_PERSONNEL_BY_PERSONAL_CODE}
     */
    public static RemoteEndpoint endpointSearchAllPersonnelByPersonalCode() {
        return getBaseEndpoint()
                .setMethodUrl(SCHOOL_URL_SEARCH_ALL_PERSONNEL_BY_PERSONAL_CODE)
                .setMethod(HttpRequest.HttpMethod.GET)
                ;
    }


    /**
     * {@value #SCHOOL_URL_SEARCH_ALL_PERSONNEL_BY_FIRST_NAME}
     */
    public static RemoteEndpoint endpointSearchAllPersonnelByFirstName() {
        return getBaseEndpoint()
                .setMethodUrl(SCHOOL_URL_SEARCH_ALL_PERSONNEL_BY_FIRST_NAME)
                .setMethod(HttpRequest.HttpMethod.GET)
                ;
    }

    /**
     * {@value #SCHOOL_URL_SEARCH_ALL_PERSONNEL_BY_LAST_NAME}
     */
    public static RemoteEndpoint endpointSearchAllPersonnelByLastName() {
        return getBaseEndpoint()
                .setMethodUrl(SCHOOL_URL_SEARCH_ALL_PERSONNEL_BY_LAST_NAME)
                .setMethod(HttpRequest.HttpMethod.GET)
                ;
    }


}
