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
    public static final String FLOOR_URL_SEARCH_ALL_FLOOR_BY_TITLE = "/search/title/{title}";


    // room

    public static final String FLOOR_URL_ROOM_CREATE = "/room";
    public static final String FLOOR_URL_ROOM_UPDATE = "/room";
    public static final String FLOOR_URL_ROOM_GET_BY_ID = "/room/id/{id}";
    public static final String FLOOR_URL_ROOM_DELETE_BY_ID = "/room/id/{id}";
    public static final String FLOOR_URL_SEARCH_ALL_ROOMS_BY_TITLE = "/room/search/title/{title}";
    public static final String FLOOR_URL_SEARCH_ALL_ROOMS_BY_NUMBER = "/room/search/number/{number}";

    // layout

    public static final String FLOOR_URL_LAYOUT_CREATE = "/layout";
    public static final String FLOOR_URL_LAYOUT_UPDATE = "/layout";
    public static final String FLOOR_URL_LAYOUT_GET_BY_ID = "/layout/id/{id}";
    public static final String FLOOR_URL_LAYOUT_DELETE_BY_ID = "/layout/id/{id}";


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

    /**
     * {@value FLOOR_URL_SEARCH_ALL_FLOOR_BY_TITLE}
     */
    public static RemoteEndpoint endpointSearchAllFloorsByTitle() {
        return getBaseEndpoint()
                .setMethodUrl(FLOOR_URL_SEARCH_ALL_FLOOR_BY_TITLE)
                .setMethod(HttpRequest.HttpMethod.GET)
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

    /**
     * {@value FLOOR_URL_SEARCH_ALL_ROOMS_BY_TITLE}
     */
    public static RemoteEndpoint endpointSearchAllRoomsByTitle() {
        return getBaseEndpoint()
                .setMethodUrl(FLOOR_URL_SEARCH_ALL_ROOMS_BY_TITLE)
                .setMethod(HttpRequest.HttpMethod.GET)
                ;
    }

    /**
     * {@value FLOOR_URL_SEARCH_ALL_ROOMS_BY_NUMBER}
     */
    public static RemoteEndpoint endpointSearchAllRoomsByNumber() {
        return getBaseEndpoint()
                .setMethodUrl(FLOOR_URL_SEARCH_ALL_ROOMS_BY_NUMBER)
                .setMethod(HttpRequest.HttpMethod.GET)
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
