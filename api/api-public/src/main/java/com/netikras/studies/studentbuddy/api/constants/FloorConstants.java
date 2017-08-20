package com.netikras.studies.studentbuddy.api.constants;

import com.netikras.tools.common.remote.RemoteEndpoint;
import com.netikras.tools.common.remote.http.HttpRequest;

public class FloorConstants {

    public static final String BASE_URL = "/floor";

    // floor

    public static final String FLOOR_URL_FLOOR_CREATE = "/";
    public static final String FLOOR_URL_FLOOR_UPDATE = "/";
    public static final String FLOOR_URL_FLOOR_GET_BY_ID = "/id/{id}";
    public static final String FLOOR_URL_FLOOR_DELETE_BY_ID = "/id/{id}";


    // room

    public static final String FLOOR_URL_ROOM_CREATE = "/";
    public static final String FLOOR_URL_ROOM_UPDATE = "/";
    public static final String FLOOR_URL_ROOM_GET_BY_ID = "/id/{id}";
    public static final String FLOOR_URL_ROOM_DELETE_BY_ID = "/id/{id}";


    // layout

    public static final String FLOOR_URL_LAYOUT_CREATE = "/";
    public static final String FLOOR_URL_LAYOUT_UPDATE = "/";
    public static final String FLOOR_URL_LAYOUT_GET_BY_ID = "/id/{id}";
    public static final String FLOOR_URL_LAYOUT_DELETE_BY_ID = "/id/{id}";



    private static RemoteEndpoint getBaseEndpoint() {
        return new RemoteEndpoint()
                .setBaseUrl("/api" + BASE_URL)
                .addHeader("content-type", "application/json")
                ;
    }

    // floor

    /**
     * {@value FLOOR_URL_FLOOR_CREATE}
     */
    public static RemoteEndpoint endpointCreateFloor() {
        return getBaseEndpoint()
                .setMethodUrl(FLOOR_URL_FLOOR_CREATE)
                .setMethod(HttpRequest.HttpMethod.POST)
                ;
    }

    /**
     * {@value FLOOR_URL_FLOOR_UPDATE}
     */
    public static RemoteEndpoint endpointUpdateFloor() {
        return getBaseEndpoint()
                .setMethodUrl(FLOOR_URL_FLOOR_UPDATE)
                .setMethod(HttpRequest.HttpMethod.PUT)
                ;
    }

    /**
     * {@value FLOOR_URL_FLOOR_GET_BY_ID}
     */
    public static RemoteEndpoint endpointGetFloorById() {
        return getBaseEndpoint()
                .setMethodUrl(FLOOR_URL_FLOOR_GET_BY_ID)
                .setMethod(HttpRequest.HttpMethod.GET)
                ;
    }

    /**
     * {@value FLOOR_URL_FLOOR_DELETE_BY_ID}
     */
    public static RemoteEndpoint endpointDeleteFloorById() {
        return getBaseEndpoint()
                .setMethodUrl(FLOOR_URL_FLOOR_DELETE_BY_ID)
                .setMethod(HttpRequest.HttpMethod.DELETE)
                ;
    }


    // room

    /**
     * {@value FLOOR_URL_ROOM_CREATE}
     */
    public static RemoteEndpoint endpointCreateRoom() {
        return getBaseEndpoint()
                .setMethodUrl(FLOOR_URL_ROOM_CREATE)
                .setMethod(HttpRequest.HttpMethod.POST)
                ;
    }

    /**
     * {@value FLOOR_URL_ROOM_UPDATE}
     */
    public static RemoteEndpoint endpointUpdateRoom() {
        return getBaseEndpoint()
                .setMethodUrl(FLOOR_URL_ROOM_UPDATE)
                .setMethod(HttpRequest.HttpMethod.PUT)
                ;
    }

    /**
     * {@value FLOOR_URL_ROOM_GET_BY_ID}
     */
    public static RemoteEndpoint endpointGetRoomById() {
        return getBaseEndpoint()
                .setMethodUrl(FLOOR_URL_ROOM_GET_BY_ID)
                .setMethod(HttpRequest.HttpMethod.GET)
                ;
    }

    /**
     * {@value FLOOR_URL_ROOM_DELETE_BY_ID}
     */
    public static RemoteEndpoint endpointDeleteRoomById() {
        return getBaseEndpoint()
                .setMethodUrl(FLOOR_URL_ROOM_DELETE_BY_ID)
                .setMethod(HttpRequest.HttpMethod.DELETE)
                ;
    }


    // layout

    /**
     * {@value FLOOR_URL_LAYOUT_CREATE}
     */
    public static RemoteEndpoint endpointCreateLayout() {
        return getBaseEndpoint()
                .setMethodUrl(FLOOR_URL_LAYOUT_CREATE)
                .setMethod(HttpRequest.HttpMethod.POST)
                ;
    }

    /**
     * {@value FLOOR_URL_LAYOUT_UPDATE}
     */
    public static RemoteEndpoint endpointUpdateLayout() {
        return getBaseEndpoint()
                .setMethodUrl(FLOOR_URL_LAYOUT_UPDATE)
                .setMethod(HttpRequest.HttpMethod.PUT)
                ;
    }

    /**
     * {@value FLOOR_URL_LAYOUT_GET_BY_ID}
     */
    public static RemoteEndpoint endpointGetLayoutById() {
        return getBaseEndpoint()
                .setMethodUrl(FLOOR_URL_LAYOUT_GET_BY_ID)
                .setMethod(HttpRequest.HttpMethod.GET)
                ;
    }

    /**
     * {@value FLOOR_URL_LAYOUT_DELETE_BY_ID}
     */
    public static RemoteEndpoint endpointDeleteLayoutById() {
        return getBaseEndpoint()
                .setMethodUrl(FLOOR_URL_LAYOUT_DELETE_BY_ID)
                .setMethod(HttpRequest.HttpMethod.DELETE)
                ;
    }
}
