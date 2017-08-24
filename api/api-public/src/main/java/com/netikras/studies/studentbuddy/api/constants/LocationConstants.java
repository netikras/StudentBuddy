package com.netikras.studies.studentbuddy.api.constants;

import com.netikras.tools.common.remote.RemoteEndpoint;
import com.netikras.tools.common.remote.http.HttpRequest;

public class LocationConstants {

    public static final String BASE_URL = "/location";

    // building
    public static final String LOC_URL_CREATE_BUILDING = "/building";
    public static final String LOC_URL_UPDATE_BUILDING = "/building";
    public static final String LOC_URL_GET_BUILDING_BY_ID = "/building/id/{id}";
    public static final String LOC_URL_DELETE_BUILDING_BY_ID = "/building/id/{id}";

    // building section
    public static final String LOC_URL_CREATE_BUILDING_SECTION = "/building/section";
    public static final String LOC_URL_UPDATE_BUILDING_SECTION = "/building/section";
    public static final String LOC_URL_GET_BUILDING_SECTION_BY_ID = "/building/section/id/{id}";
    public static final String LOC_URL_DELETE_BUILDING_SECTION_BY_ID = "/building/section/id/{id}";

    // address
    public static final String LOC_URL_CREATE_ADDRESS = "/address";
    public static final String LOC_URL_UPDATE_ADDRESS = "/address";
    public static final String LOC_URL_GET_ADDRESS_BY_ID = "/address/id/{id}";
    public static final String LOC_URL_DELETE_ADDRESS_BY_ID = "/address/id/{id}";

    // search
    public static final String LOC_URL_SEARCH_ALL_BUILDINGS_BY_TITLE = "/building/title/{title}";
    public static final String LOC_URL_SEARCH_ALL_BUILDING_SECTIONS_BY_TITLE = "/building/section/title/{title}";


    private static RemoteEndpoint getBaseEndpoint() {
        return new RemoteEndpoint()
                .setBaseUrl("/api" + BASE_URL)
                .addHeader("content-type", "application/json")
                ;
    }


    // building

    /**
     * {@value LOC_URL_CREATE_BUILDING}
     */
    public static RemoteEndpoint endpointCreateBuilding() {
        return getBaseEndpoint()
                .setMethodUrl(LOC_URL_CREATE_BUILDING)
                .setMethod(HttpRequest.HttpMethod.POST)
                ;
    }

    /**
     * {@value LOC_URL_UPDATE_BUILDING}
     */
    public static RemoteEndpoint endpointUpdateBuilding() {
        return getBaseEndpoint()
                .setMethodUrl(LOC_URL_UPDATE_BUILDING)
                .setMethod(HttpRequest.HttpMethod.PUT)
                ;
    }

    /**
     * {@value LOC_URL_GET_BUILDING_BY_ID}
     */
    public static RemoteEndpoint endpointGetBuildingById() {
        return getBaseEndpoint()
                .setMethodUrl(LOC_URL_GET_BUILDING_BY_ID)
                .setMethod(HttpRequest.HttpMethod.GET)
                ;
    }

    /**
     * {@value LOC_URL_DELETE_BUILDING_BY_ID}
     */
    public static RemoteEndpoint endpointDeleteBuildingById() {
        return getBaseEndpoint()
                .setMethodUrl(LOC_URL_DELETE_BUILDING_BY_ID)
                .setMethod(HttpRequest.HttpMethod.DELETE)
                ;
    }

    // building section

    /**
     * {@value LOC_URL_CREATE_BUILDING_SECTION}
     */
    public static RemoteEndpoint endpointCreateBuildingSection() {
        return getBaseEndpoint()
                .setMethodUrl(LOC_URL_CREATE_BUILDING_SECTION)
                .setMethod(HttpRequest.HttpMethod.POST)
                ;
    }

    /**
     * {@value LOC_URL_UPDATE_BUILDING_SECTION
     */
    public static RemoteEndpoint endpointUpdateBuildingSection() {
        return getBaseEndpoint()
                .setMethodUrl(LOC_URL_UPDATE_BUILDING_SECTION)
                .setMethod(HttpRequest.HttpMethod.PUT)
                ;
    }

    /**
     * {@value LOC_URL_GET_BUILDING_SECTION_BY_ID}
     */
    public static RemoteEndpoint endpointGetBuildingSectionById() {
        return getBaseEndpoint()
                .setMethodUrl(LOC_URL_GET_BUILDING_SECTION_BY_ID)
                .setMethod(HttpRequest.HttpMethod.GET)
                ;
    }

    /**
     * {@value LOC_URL_DELETE_BUILDING_SECTION_BY_ID}
     */
    public static RemoteEndpoint endpointDeleteBuildingSectionById() {
        return getBaseEndpoint()
                .setMethodUrl(LOC_URL_DELETE_BUILDING_SECTION_BY_ID)
                .setMethod(HttpRequest.HttpMethod.DELETE)
                ;
    }


    // address

    /**
     * {@value LOC_URL_CREATE_ADDRESS}
     */
    public static RemoteEndpoint endpointCreateAddress() {
        return getBaseEndpoint()
                .setMethodUrl(LOC_URL_CREATE_ADDRESS)
                .setMethod(HttpRequest.HttpMethod.POST)
                ;
    }

    /**
     * {@value LOC_URL_UPDATE_ADDRESS}
     */
    public static RemoteEndpoint endpointUpdateAddress() {
        return getBaseEndpoint()
                .setMethodUrl(LOC_URL_UPDATE_ADDRESS)
                .setMethod(HttpRequest.HttpMethod.PUT)
                ;
    }

    /**
     * {@value LOC_URL_GET_ADDRESS_BY_ID}
     */
    public static RemoteEndpoint endpointGetAddressById() {
        return getBaseEndpoint()
                .setMethodUrl(LOC_URL_GET_ADDRESS_BY_ID)
                .setMethod(HttpRequest.HttpMethod.GET)
                ;
    }

    /**
     * {@value LOC_URL_DELETE_ADDRESS_BY_ID}
     */
    public static RemoteEndpoint endpointDeleteAddressById() {
        return getBaseEndpoint()
                .setMethodUrl(LOC_URL_DELETE_ADDRESS_BY_ID)
                .setMethod(HttpRequest.HttpMethod.DELETE)
                ;
    }

    /**
     * {@value LOC_URL_SEARCH_ALL_BUILDINGS_BY_TITLE}
     */
    public static RemoteEndpoint endpointSearchAllBuildingsByTitle() {
        return getBaseEndpoint()
                .setMethodUrl(LOC_URL_SEARCH_ALL_BUILDINGS_BY_TITLE)
                .setMethod(HttpRequest.HttpMethod.GET)
                ;
    }

    /**
     * {@value LOC_URL_SEARCH_ALL_BUILDING_SECTIONS_BY_TITLE}
     */
    public static RemoteEndpoint endpointSearchAllBuildingSectionsByTitle() {
        return getBaseEndpoint()
                .setMethodUrl(LOC_URL_SEARCH_ALL_BUILDING_SECTIONS_BY_TITLE)
                .setMethod(HttpRequest.HttpMethod.GET)
                ;
    }

}
