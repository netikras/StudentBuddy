package com.netikras.studies.studentbuddy.api.constants;

import com.netikras.tools.common.remote.RemoteEndpoint;
import com.netikras.tools.common.remote.http.HttpRequest;

public class AdminLecturerConstants {

    public static final String BASE_URL = "/mgmt/lecturer";

    public static final String ADM_LECTURER_URL_CREATE = "/";

    public static final String ADM_LECTURER_URL_DELETE_BY_ID = "/id/{id}";

    public static final String ADM_LECTURER_URL_ASSIGN_TO_DISCIPLINE = "/id/{lecturerId}/discipline/{disciplineId}";

    public static final String ADM_LECTURER_URL_UNASSIGN_FROM_DISCIPLINE = "/id/{lecturerId}/discipline/{disciplineId}";

    private static RemoteEndpoint getBaseEndpoint() {
        return new RemoteEndpoint()
                .setBaseUrl("/api" + BASE_URL)
                .addHeader("content-type", "application/json")
                ;
    }

    /**
     * {@value ADM_LECTURER_URL_CREATE}
     */
    public static RemoteEndpoint endpointCreateNew() {
        return getBaseEndpoint()
                .setMethodUrl(ADM_LECTURER_URL_CREATE)
                .setMethod(HttpRequest.HttpMethod.POST)
                ;
    }

    /**
     * {@value ADM_LECTURER_URL_DELETE_BY_ID}
     */
    public static RemoteEndpoint endpointDeleteById() {
        return getBaseEndpoint()
                .setMethodUrl(ADM_LECTURER_URL_DELETE_BY_ID)
                .setMethod(HttpRequest.HttpMethod.DELETE)
                ;
    }

    /**
     * {@value ADM_LECTURER_URL_ASSIGN_TO_DISCIPLINE}
     */
    public static RemoteEndpoint endpointAssignToDiscipline() {
        return getBaseEndpoint()
                .setMethodUrl(ADM_LECTURER_URL_ASSIGN_TO_DISCIPLINE)
                .setMethod(HttpRequest.HttpMethod.PUT)
                ;
    }

    /**
     * {@value ADM_LECTURER_URL_UNASSIGN_FROM_DISCIPLINE}
     */
    public static RemoteEndpoint endpointUnassignFromDiscipline() {
        return getBaseEndpoint()
                .setMethodUrl(ADM_LECTURER_URL_UNASSIGN_FROM_DISCIPLINE)
                .setMethod(HttpRequest.HttpMethod.PUT)
                ;
    }

}
