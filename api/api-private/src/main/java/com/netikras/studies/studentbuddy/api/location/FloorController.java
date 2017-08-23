package com.netikras.studies.studentbuddy.api.location;

import com.netikras.studies.studentbuddy.core.data.api.dto.location.BuildingFloorDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.location.FloorLayoutDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.location.LectureRoomDto;
import com.netikras.studies.studentbuddy.core.data.api.model.BuildingFloor;
import com.netikras.studies.studentbuddy.core.data.api.model.FloorLayout;
import com.netikras.studies.studentbuddy.core.data.api.model.LectureRoom;
import com.netikras.studies.studentbuddy.core.meta.Action;
import com.netikras.studies.studentbuddy.core.meta.annotations.Authorizable;
import com.netikras.studies.studentbuddy.core.service.FloorService;
import com.netikras.tools.common.model.mapper.ModelMapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import java.util.List;

import static com.netikras.studies.studentbuddy.api.constants.FloorConstants.BASE_URL;
import static com.netikras.studies.studentbuddy.api.constants.FloorConstants.FLOOR_URL_FLOOR_CREATE;
import static com.netikras.studies.studentbuddy.api.constants.FloorConstants.FLOOR_URL_FLOOR_DELETE_BY_ID;
import static com.netikras.studies.studentbuddy.api.constants.FloorConstants.FLOOR_URL_FLOOR_GET_BY_ID;
import static com.netikras.studies.studentbuddy.api.constants.FloorConstants.FLOOR_URL_FLOOR_UPDATE;
import static com.netikras.studies.studentbuddy.api.constants.FloorConstants.FLOOR_URL_LAYOUT_CREATE;
import static com.netikras.studies.studentbuddy.api.constants.FloorConstants.FLOOR_URL_LAYOUT_DELETE_BY_ID;
import static com.netikras.studies.studentbuddy.api.constants.FloorConstants.FLOOR_URL_LAYOUT_GET_BY_ID;
import static com.netikras.studies.studentbuddy.api.constants.FloorConstants.FLOOR_URL_LAYOUT_UPDATE;
import static com.netikras.studies.studentbuddy.api.constants.FloorConstants.FLOOR_URL_ROOM_CREATE;
import static com.netikras.studies.studentbuddy.api.constants.FloorConstants.FLOOR_URL_ROOM_DELETE_BY_ID;
import static com.netikras.studies.studentbuddy.api.constants.FloorConstants.FLOOR_URL_ROOM_GET_BY_ID;
import static com.netikras.studies.studentbuddy.api.constants.FloorConstants.FLOOR_URL_ROOM_UPDATE;
import static com.netikras.studies.studentbuddy.core.meta.Resource.FLOOR;
import static com.netikras.studies.studentbuddy.core.meta.Resource.FLOOR_MAP;
import static com.netikras.studies.studentbuddy.core.meta.Resource.ROOM;

@RestController
@RequestMapping(value = BASE_URL)
public class FloorController {

    
    @Resource
    private FloorService floorService;


    // floor

    @RequestMapping(
            value = FLOOR_URL_FLOOR_GET_BY_ID,
            method = RequestMethod.GET
    )
    @ResponseBody
    @Authorizable(resource = FLOOR, action = Action.GET)
    public BuildingFloorDto getBuildingFloor(
            @PathVariable(name = "id") String id
    ) {
        BuildingFloor buildingFloor = floorService.getFloor(id);
        BuildingFloorDto dto = ModelMapper.transform(buildingFloor, new BuildingFloorDto());
        return dto;
    }

    @RequestMapping(
            value = FLOOR_URL_FLOOR_UPDATE,
            method = RequestMethod.PUT
    )
    @ResponseBody
    @Authorizable(resource = FLOOR, action = Action.MODIFY)
    public BuildingFloorDto updateBuildingFloor(
            @RequestBody BuildingFloorDto buildingFloorDto
    ) {
        BuildingFloor buildingFloor = ModelMapper.apply(new BuildingFloor(), buildingFloorDto);
        buildingFloor = floorService.updateFloor(buildingFloor);
        BuildingFloorDto dto = ModelMapper.transform(buildingFloor, new BuildingFloorDto());
        return dto;
    }

    @RequestMapping(
            value = FLOOR_URL_FLOOR_CREATE,
            method = RequestMethod.POST
    )
    @ResponseBody
    @Authorizable(resource = FLOOR, action = Action.CREATE)
    public BuildingFloorDto createBuildingFloor(
            @RequestBody BuildingFloorDto buildingFloorDto
    ) {
        BuildingFloor buildingFloor = ModelMapper.apply(new BuildingFloor(), buildingFloorDto);
        buildingFloor = floorService.createFloor(buildingFloor);
        BuildingFloorDto dto = ModelMapper.transform(buildingFloor, new BuildingFloorDto());
        return dto;
    }

    @RequestMapping(
            value = FLOOR_URL_FLOOR_DELETE_BY_ID,
            method = RequestMethod.DELETE
    )
    @Authorizable(resource = FLOOR, action = Action.DELETE)
    public void deleteBuildingFloor(@PathVariable(name = "id") String id) {
        floorService.deleteFloor(id);
    }

    @RequestMapping(
            value = "/search/title/{title}",
            method = RequestMethod.GET
    )
    @ResponseBody
    @Authorizable(resource = FLOOR, action = Action.SEARCH)
    public List<BuildingFloorDto> searchFloorsByTitle(
            @PathVariable(name = "title") String titleSubstring
    ) {
        List<BuildingFloor> buildingFloors = floorService.searchAllByTitle(titleSubstring);
        List<BuildingFloorDto> dtos = (List<BuildingFloorDto>) ModelMapper.transformAll(buildingFloors, BuildingFloorDto.class);
        return dtos;
    }



    // room

    @RequestMapping(
            value = FLOOR_URL_ROOM_GET_BY_ID,
            method = RequestMethod.GET
    )
    @ResponseBody
    @Authorizable(resource = ROOM, action = Action.GET)
    public LectureRoomDto getLectureRoom(@PathVariable(name = "id") String id) {
        LectureRoom lectureRoom = floorService.getRoom(id);
        LectureRoomDto dto = ModelMapper.transform(lectureRoom, new LectureRoomDto());
        return dto;
    }

    @RequestMapping(
            value = FLOOR_URL_ROOM_UPDATE,
            method = RequestMethod.PUT
    )
    @ResponseBody
    @Authorizable(resource = ROOM, action = Action.MODIFY)
    public LectureRoomDto updateLectureRoom(
            @RequestBody LectureRoomDto lectureRoomDto
    ) {
        LectureRoom lectureRoom = ModelMapper.apply(new LectureRoom(), lectureRoomDto);
        lectureRoom = floorService.updateRoom(lectureRoom);
        LectureRoomDto dto = ModelMapper.transform(lectureRoom, new LectureRoomDto());
        return dto;
    }

    @RequestMapping(
            value = FLOOR_URL_ROOM_CREATE,
            method = RequestMethod.POST
    )
    @ResponseBody
    @Authorizable(resource = ROOM, action = Action.CREATE)
    public LectureRoomDto createLectureRoom(
            @RequestBody LectureRoomDto lectureRoomDto
    ) {
        LectureRoom lectureRoom = ModelMapper.apply(new LectureRoom(), lectureRoomDto);
        lectureRoom = floorService.createRoom(lectureRoom);
        LectureRoomDto dto = ModelMapper.transform(lectureRoom, new LectureRoomDto());
        return dto;
    }

    @RequestMapping(
            value = FLOOR_URL_ROOM_DELETE_BY_ID,
            method = RequestMethod.DELETE
    )
    @Authorizable(resource = ROOM, action = Action.DELETE)
    public void deleteLectureRoom(@PathVariable(name = "id") String id) {
        floorService.deleteRoom(id);
    }

    @RequestMapping(
            value = "/floor/search/title/{title}",
            method = RequestMethod.GET
    )
    @ResponseBody
    @Authorizable(resource = ROOM, action = Action.SEARCH)
    public List<LectureRoomDto> searchRoomsByTitle(
            @PathVariable(name = "title") String titleSubstring
    ) {
        List<LectureRoom> rooms = floorService.searchAllRoomsByTitle(titleSubstring);
        List<LectureRoomDto> dtos = (List<LectureRoomDto>) ModelMapper.transformAll(rooms, LectureRoomDto.class);
        return dtos;
    }

    @RequestMapping(
            value = "/floor/search/number/{number}",
            method = RequestMethod.GET
    )
    @ResponseBody
    @Authorizable(resource = ROOM, action = Action.SEARCH)
    public List<LectureRoomDto> searchRoomsByNumber(
            @PathVariable(name = "number") String numberSubstring
    ) {
        List<LectureRoom> rooms = floorService.searchAllRoomsByNumber(numberSubstring);
        List<LectureRoomDto> dtos = (List<LectureRoomDto>) ModelMapper.transformAll(rooms, LectureRoomDto.class);
        return dtos;
    }



    // layout

    @RequestMapping(
            value = FLOOR_URL_LAYOUT_GET_BY_ID,
            method = RequestMethod.GET
    )
    @ResponseBody
    @Authorizable(resource = FLOOR_MAP, action = Action.GET)
    public FloorLayoutDto getFloorLayout(
            @PathVariable(name = "id") String id
    ) {
        FloorLayout floorLayout = floorService.getFloorLayout(id);
        FloorLayoutDto dto = ModelMapper.transform(floorLayout, new FloorLayoutDto());
        return dto;
    }

    @RequestMapping(
            value = FLOOR_URL_LAYOUT_UPDATE,
            method = RequestMethod.PUT
    )
    @ResponseBody
    @Authorizable(resource = FLOOR_MAP, action = Action.MODIFY)
    public FloorLayoutDto updateFloorLayout(
            @RequestBody FloorLayoutDto floorLayoutDto
    ) {
        FloorLayout floorLayout = ModelMapper.apply(new FloorLayout(), floorLayoutDto);
        floorLayout = floorService.updateFloorLayout(floorLayout);
        FloorLayoutDto dto = ModelMapper.transform(floorLayout, new FloorLayoutDto());
        return dto;
    }

    @RequestMapping(
            value = FLOOR_URL_LAYOUT_CREATE,
            method = RequestMethod.POST
    )
    @ResponseBody
    @Authorizable(resource = FLOOR_MAP, action = Action.CREATE)
    public FloorLayoutDto createFloorLayout(
            @RequestBody FloorLayoutDto floorLayoutDto
    ) {
        FloorLayout floorLayout = ModelMapper.apply(new FloorLayout(), floorLayoutDto);
        floorLayout = floorService.createFloorLayout(floorLayout);
        FloorLayoutDto dto = ModelMapper.transform(floorLayout, new FloorLayoutDto());
        return dto;
    }

    @RequestMapping(
            value = FLOOR_URL_LAYOUT_DELETE_BY_ID,
            method = RequestMethod.DELETE
    )
    @Authorizable(resource = FLOOR_MAP, action = Action.DELETE)
    public void deleteFloorLayout(@PathVariable(name = "id") String id) {
        floorService.deleteFloorLayout(id);
    }

}
