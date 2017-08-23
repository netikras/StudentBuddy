package com.netikras.studies.studentbuddy.api.constants;

import com.netikras.tools.common.remote.RemoteEndpoint;
import com.netikras.tools.common.remote.http.HttpRequest;

public class SchoolConstants {

    public static final String BASE_URL = "/school";

    public static final String SCHOOL_URL_GET_BY_ID = "/id/{id}";
    public static final String SCHOOL_URL_GET_ALL= "/all";

    public static final String SCHOOL_URL_CREATE = "/";
    public static final String SCHOOL_URL_UPDATE = "/";

    public static final String SCHOOL_URL_DELETE_BY_ID = "/id/{id}";

    // department
    public static final String SCHOOL_URL_GET_DEPARTMENT_BY_ID = "/department/id/{id}";

    public static final String SCHOOL_URL_CREATE_DEPARTMENT = "/department";
    public static final String SCHOOL_URL_UPDATE_DEPARTMENT = "/department";

    public static final String SCHOOL_URL_DELETE_DEPARTMENT_BY_ID = "/department/id/{id}";


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


}
