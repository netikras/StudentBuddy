package com.netikras.studies.studentbuddy.api.constants;

import com.netikras.tools.common.remote.RemoteEndpoint;
import com.netikras.tools.common.remote.http.HttpRequest;

public class AdminConstants {

    public static final String BASE_URL = "/admin";

    public static final String ADM_URL_GET_SETTINGS_LIVE = "/settings/live";
    public static final String ADM_URL_GET_SETTINGS = "/settings";
    public static final String ADM_URL_GET_SETTING_LIVE_BY_NAME = "/settings/live/name/{name}";
    public static final String ADM_URL_GET_SETTING_STORED_BY_NAME = "/settings/name/{name}";
    public static final String ADM_URL_CREATE_SETTING = "/settings";
    public static final String ADM_URL_UPDATE_SETTING = "/settings";
    public static final String ADM_URL_DELETE_SETTING_BY_NAME = "/settings/name/{name}";
    public static final String ADM_URL_REFRESH_SETTINGS = "/settings/refresh";

    public static final String ADM_URL_GET_PASSWORD_REQS_LIVE = "/pwrq/live";
    public static final String ADM_URL_GET_PASSWORD_REQS = "/pwrq";
    public static final String ADM_URL_CREATE_PASSWORD_REQ = "/pwrq";
    public static final String ADM_URL_UPDATE_PASSWORD_REQ = "/pwrq";
    public static final String ADM_URL_DELETE_PASSWORD_REQ_BY_ID = "/pwrq/id/{id}";
    public static final String ADM_URL_REFRESH_PASSWORD_REQS = "/pwrq/refresh";


    private static RemoteEndpoint getBaseEndpoint() {
        return new RemoteEndpoint()
                .setBaseUrl("/api" + BASE_URL)
                .addHeader("content-type", "application/json")
                ;
    }

    /**
     * {@value #ADM_URL_GET_SETTINGS_LIVE}
     */
    public static RemoteEndpoint endpointGetSettingsLive() {
        return getBaseEndpoint()
                .setMethodUrl(ADM_URL_GET_SETTINGS_LIVE)
                .setMethod(HttpRequest.HttpMethod.GET)
                ;
    }

    /**
     * {@value #ADM_URL_GET_SETTINGS}
     */
    public static RemoteEndpoint endpointGetSettings() {
        return getBaseEndpoint()
                .setMethodUrl(ADM_URL_GET_SETTINGS)
                .setMethod(HttpRequest.HttpMethod.GET)
                ;
    }

    /**
     * {@value #ADM_URL_GET_SETTING_LIVE_BY_NAME}
     */
    public static RemoteEndpoint endpointGetSettingLiveByName() {
        return getBaseEndpoint()
                .setMethodUrl(ADM_URL_GET_SETTING_LIVE_BY_NAME)
                .setMethod(HttpRequest.HttpMethod.GET)
                ;
    }

    /**
     * {@value #ADM_URL_GET_SETTING_STORED_BY_NAME}
     */
    public static RemoteEndpoint endpointGetSettingStoredByName() {
        return getBaseEndpoint()
                .setMethodUrl(ADM_URL_GET_SETTING_STORED_BY_NAME)
                .setMethod(HttpRequest.HttpMethod.GET)
                ;
    }

    /**
     * {@value #ADM_URL_CREATE_SETTING}
     */
    public static RemoteEndpoint endpointCreateSetting() {
        return getBaseEndpoint()
                .setMethodUrl(ADM_URL_CREATE_SETTING)
                .setMethod(HttpRequest.HttpMethod.POST)
                ;
    }

    /**
     * {@value #ADM_URL_UPDATE_SETTING}
     */
    public static RemoteEndpoint endpointUpdateSetting() {
        return getBaseEndpoint()
                .setMethodUrl(ADM_URL_UPDATE_SETTING)
                .setMethod(HttpRequest.HttpMethod.PUT)
                ;
    }

    /**
     * {@value #ADM_URL_DELETE_SETTING_BY_NAME}
     */
    public static RemoteEndpoint endpointDeleteSettingByName() {
        return getBaseEndpoint()
                .setMethodUrl(ADM_URL_DELETE_SETTING_BY_NAME)
                .setMethod(HttpRequest.HttpMethod.DELETE)
                ;
    }

    /**
     * {@value #ADM_URL_REFRESH_SETTINGS}
     */
    public static RemoteEndpoint endpointRefreshSettings() {
        return getBaseEndpoint()
                .setMethodUrl(ADM_URL_REFRESH_SETTINGS)
                .setMethod(HttpRequest.HttpMethod.POST)
                ;
    }



    /**
     * {@value #ADM_URL_GET_PASSWORD_REQS_LIVE}
     */
    public static RemoteEndpoint endpointGetPwReqsLive() {
        return getBaseEndpoint()
                .setMethodUrl(ADM_URL_GET_PASSWORD_REQS_LIVE)
                .setMethod(HttpRequest.HttpMethod.GET)
                ;
    }

    /**
     * {@value #ADM_URL_GET_PASSWORD_REQS}
     */
    public static RemoteEndpoint endpointGetPwReqs() {
        return getBaseEndpoint()
                .setMethodUrl(ADM_URL_GET_PASSWORD_REQS)
                .setMethod(HttpRequest.HttpMethod.GET)
                ;
    }

    /**
     * {@value #ADM_URL_CREATE_PASSWORD_REQ}
     */
    public static RemoteEndpoint endpointCreatePwReq() {
        return getBaseEndpoint()
                .setMethodUrl(ADM_URL_CREATE_PASSWORD_REQ)
                .setMethod(HttpRequest.HttpMethod.POST)
                ;
    }

    /**
     * {@value #ADM_URL_UPDATE_PASSWORD_REQ}
     */
    public static RemoteEndpoint endpointUpdatePwReq() {
        return getBaseEndpoint()
                .setMethodUrl(ADM_URL_UPDATE_PASSWORD_REQ)
                .setMethod(HttpRequest.HttpMethod.PUT)
                ;
    }

    /**
     * {@value #ADM_URL_DELETE_PASSWORD_REQ_BY_ID}
     */
    public static RemoteEndpoint endpointDeletePwReqById() {
        return getBaseEndpoint()
                .setMethodUrl(ADM_URL_DELETE_PASSWORD_REQ_BY_ID)
                .setMethod(HttpRequest.HttpMethod.DELETE)
                ;
    }

    /**
     * {@value #ADM_URL_REFRESH_PASSWORD_REQS}
     */
    public static RemoteEndpoint endpointRefreshPasswordRequirements() {
        return getBaseEndpoint()
                .setMethodUrl(ADM_URL_REFRESH_PASSWORD_REQS)
                .setMethod(HttpRequest.HttpMethod.POST)
                ;
    }

}
