package com.netikras.studies.studentbuddy.api.location;

import com.netikras.studies.studentbuddy.core.data.api.dto.location.BuildingFloorDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.location.FloorLayoutDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.location.LectureRoomDto;
import com.netikras.tools.common.remote.http.GenericRestConsumer;
import com.netikras.tools.common.remote.http.HttpRequest;
import com.netikras.tools.common.remote.http.impl.json.HttpResponseJsonImpl;

import static com.netikras.studies.studentbuddy.api.constants.FloorConstants.endpointCreateFloor;
import static com.netikras.studies.studentbuddy.api.constants.FloorConstants.endpointCreateLayout;
import static com.netikras.studies.studentbuddy.api.constants.FloorConstants.endpointCreateRoom;
import static com.netikras.studies.studentbuddy.api.constants.FloorConstants.endpointDeleteFloorById;
import static com.netikras.studies.studentbuddy.api.constants.FloorConstants.endpointDeleteLayoutById;
import static com.netikras.studies.studentbuddy.api.constants.FloorConstants.endpointDeleteRoomById;
import static com.netikras.studies.studentbuddy.api.constants.FloorConstants.endpointGetFloorById;
import static com.netikras.studies.studentbuddy.api.constants.FloorConstants.endpointGetLayoutById;
import static com.netikras.studies.studentbuddy.api.constants.FloorConstants.endpointGetRoomById;
import static com.netikras.studies.studentbuddy.api.constants.FloorConstants.endpointUpdateFloor;
import static com.netikras.studies.studentbuddy.api.constants.FloorConstants.endpointUpdateLayout;
import static com.netikras.studies.studentbuddy.api.constants.FloorConstants.endpointUpdateRoom;

public class FloorConsumer extends GenericRestConsumer {

    // floor

    public BuildingFloorDto createFloor(BuildingFloorDto floorDto) {
        HttpRequest<BuildingFloorDto> request = createRequest(endpointCreateFloor())
                .withExpectedType(BuildingFloorDto.class)
                .setObject(floorDto);

        BuildingFloorDto dto = (BuildingFloorDto) sendRequest(request);
        return dto;
    }

    public BuildingFloorDto updateFloor(BuildingFloorDto floorDto) {
        HttpRequest<BuildingFloorDto> request = createRequest(endpointUpdateFloor())
                .withExpectedType(BuildingFloorDto.class)
                .setObject(floorDto);

        BuildingFloorDto dto = (BuildingFloorDto) sendRequest(request);
        return dto;
    }

    public BuildingFloorDto getFloorById(String id) {
        HttpRequest request = createRequest(endpointGetFloorById())
                .withExpectedType(BuildingFloorDto.class)
                .setUrlProperty("id", id);

        BuildingFloorDto dto = (BuildingFloorDto) sendRequest(request);
        return dto;
    }

    public boolean deleteFloorById(String id) {
        HttpRequest request = createRequest(endpointDeleteFloorById())
                .setUrlProperty("id", id);

        HttpResponseJsonImpl responseJson = new HttpResponseJsonImpl();
        sendRequest(request, responseJson);
        return responseJson.isResponseSuccess();
    }



    // room

    public LectureRoomDto createRoom(LectureRoomDto roomDto) {
        HttpRequest<LectureRoomDto> request = createRequest(endpointCreateRoom())
                .withExpectedType(LectureRoomDto.class)
                .setObject(roomDto);

        LectureRoomDto dto = (LectureRoomDto) sendRequest(request);
        return dto;
    }

    public LectureRoomDto updateRoom(LectureRoomDto roomDto) {
        HttpRequest<LectureRoomDto> request = createRequest(endpointUpdateRoom())
                .withExpectedType(LectureRoomDto.class)
                .setObject(roomDto);

        LectureRoomDto dto = (LectureRoomDto) sendRequest(request);
        return dto;
    }

    public LectureRoomDto getRoomById(String id) {
        HttpRequest request = createRequest(endpointGetRoomById())
                .withExpectedType(LectureRoomDto.class)
                .setUrlProperty("id", id);

        LectureRoomDto dto = (LectureRoomDto) sendRequest(request);
        return dto;
    }

    public boolean deleteRoomById(String id) {
        HttpRequest request = createRequest(endpointDeleteRoomById())
                .setUrlProperty("id", id);

        HttpResponseJsonImpl responseJson = new HttpResponseJsonImpl();
        sendRequest(request, responseJson);
        return responseJson.isResponseSuccess();
    }



    // layout

    public FloorLayoutDto createFloorLayout(FloorLayoutDto layoutDto) {
        HttpRequest<FloorLayoutDto> request = createRequest(endpointCreateLayout())
                .withExpectedType(FloorLayoutDto.class)
                .setObject(layoutDto);

        FloorLayoutDto dto = (FloorLayoutDto) sendRequest(request);
        return dto;
    }

    public FloorLayoutDto updateFloorLayout(FloorLayoutDto layoutDto) {
        HttpRequest<FloorLayoutDto> request = createRequest(endpointUpdateLayout())
                .withExpectedType(FloorLayoutDto.class)
                .setObject(layoutDto);

        FloorLayoutDto dto = (FloorLayoutDto) sendRequest(request);
        return dto;
    }

    public FloorLayoutDto getFloorLayoutById(String id) {
        HttpRequest request = createRequest(endpointGetLayoutById())
                .withExpectedType(FloorLayoutDto.class)
                .setUrlProperty("id", id);

        FloorLayoutDto dto = (FloorLayoutDto) sendRequest(request);
        return dto;
    }

    public boolean deleteFloorLayoutById(String id) {
        HttpRequest request = createRequest(endpointDeleteLayoutById())
                .setUrlProperty("id", id);

        HttpResponseJsonImpl responseJson = new HttpResponseJsonImpl();
        sendRequest(request, responseJson);
        return responseJson.isResponseSuccess();
    }
}
