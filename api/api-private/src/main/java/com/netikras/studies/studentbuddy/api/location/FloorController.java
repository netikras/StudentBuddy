package com.netikras.studies.studentbuddy.api.location;

import com.netikras.studies.studentbuddy.core.data.api.dto.location.BuildingFloorDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.location.FloorLayoutDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.location.LectureRoomDto;
import com.netikras.studies.studentbuddy.core.data.api.model.BuildingFloor;
import com.netikras.studies.studentbuddy.core.data.api.model.FloorLayout;
import com.netikras.studies.studentbuddy.core.data.api.model.LectureRoom;
import com.netikras.studies.studentbuddy.core.meta.Action;
import com.netikras.studies.studentbuddy.core.meta.annotations.Authorizable;
import com.netikras.tools.common.model.mapper.ModelMapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static com.netikras.studies.studentbuddy.core.meta.Resource.FLOOR;
import static com.netikras.studies.studentbuddy.core.meta.Resource.FLOOR_MAP;
import static com.netikras.studies.studentbuddy.core.meta.Resource.ROOM;

@RestController
@RequestMapping(value = "/floor")
public class FloorController {



    @RequestMapping(
            value = "/id/{id}",
            method = RequestMethod.GET
    )
    @ResponseBody
    @Authorizable(resource = FLOOR, action = Action.GET)
    public BuildingFloorDto getBuildingFloor(@PathVariable(name = "id") String id) {
        BuildingFloor buildingFloor = locationService.getFloor(id);
        BuildingFloorDto dto = ModelMapper.transform(buildingFloor, new BuildingFloorDto());
        return dto;
    }

    @RequestMapping(
            value = "/",
            method = RequestMethod.PUT
    )
    @ResponseBody
    @Authorizable(resource = FLOOR, action = Action.MODIFY)
    public BuildingFloorDto updateBuildingFloor(
            @RequestBody BuildingFloorDto buildingFloorDto
    ) {
        BuildingFloor buildingFloor = ModelMapper.apply(new BuildingFloor(), buildingFloorDto);
        buildingFloor = locationService.updateFloor(buildingFloor);
        BuildingFloorDto dto = ModelMapper.transform(buildingFloor, new BuildingFloorDto());
        return dto;
    }

    @RequestMapping(
            value = "/",
            method = RequestMethod.POST
    )
    @ResponseBody
    @Authorizable(resource = FLOOR, action = Action.CREATE)
    public BuildingFloorDto createBuildingFloor(
            @RequestBody BuildingFloorDto buildingFloorDto
    ) {
        BuildingFloor buildingFloor = ModelMapper.apply(new BuildingFloor(), buildingFloorDto);
        buildingFloor = locationService.createFloor(buildingFloor);
        BuildingFloorDto dto = ModelMapper.transform(buildingFloor, new BuildingFloorDto());
        return dto;
    }

    @RequestMapping(
            value = "/id/{id}",
            method = RequestMethod.DELETE
    )
    @Authorizable(resource = FLOOR, action = Action.DELETE)
    public void deleteBuildingFloor(@PathVariable(name = "id") String id) {
        locationService.deleteFloor(id);
    }

    @RequestMapping(
            value = "/room/id/{id}",
            method = RequestMethod.GET
    )
    @ResponseBody
    @Authorizable(resource = ROOM, action = Action.GET)
    public LectureRoomDto getLectureRoom(@PathVariable(name = "id") String id) {
        LectureRoom lectureRoom = locationService.getRoom(id);
        LectureRoomDto dto = ModelMapper.transform(lectureRoom, new LectureRoomDto());
        return dto;
    }

    @RequestMapping(
            value = "/room",
            method = RequestMethod.PUT
    )
    @ResponseBody
    @Authorizable(resource = ROOM, action = Action.MODIFY)
    public LectureRoomDto updateLectureRoom(
            @RequestBody LectureRoomDto lectureRoomDto
    ) {
        LectureRoom lectureRoom = ModelMapper.apply(new LectureRoom(), lectureRoomDto);
        lectureRoom = locationService.updateRoom(lectureRoom);
        LectureRoomDto dto = ModelMapper.transform(lectureRoom, new LectureRoomDto());
        return dto;
    }

    @RequestMapping(
            value = "/room",
            method = RequestMethod.POST
    )
    @ResponseBody
    @Authorizable(resource = ROOM, action = Action.CREATE)
    public LectureRoomDto createLectureRoom(
            @RequestBody LectureRoomDto lectureRoomDto
    ) {
        LectureRoom lectureRoom = ModelMapper.apply(new LectureRoom(), lectureRoomDto);
        lectureRoom = locationService.createRoom(lectureRoom);
        LectureRoomDto dto = ModelMapper.transform(lectureRoom, new LectureRoomDto());
        return dto;
    }

    @RequestMapping(
            value = "/room/id/{id}",
            method = RequestMethod.DELETE
    )
    @Authorizable(resource = ROOM, action = Action.DELETE)
    public void deleteLectureRoom(@PathVariable(name = "id") String id) {
        locationService.deleteRoom(id);
    }






    @RequestMapping(
            value = "/layout/id/{id}",
            method = RequestMethod.GET
    )
    @ResponseBody
    @Authorizable(resource = FLOOR_MAP, action = Action.GET)
    public FloorLayoutDto getFloorLayout(@PathVariable(name = "id") String id) {
        FloorLayout floorLayout = locationService.getFloorLayout(id);
        FloorLayoutDto dto = ModelMapper.transform(floorLayout, new FloorLayoutDto());
        return dto;
    }

    @RequestMapping(
            value = "/layout",
            method = RequestMethod.PUT
    )
    @ResponseBody
    @Authorizable(resource = FLOOR_MAP, action = Action.MODIFY)
    public FloorLayoutDto updateFloorLayout(
            @RequestBody FloorLayoutDto floorLayoutDto
    ) {
        FloorLayout floorLayout = ModelMapper.apply(new FloorLayout(), floorLayoutDto);
        floorLayout = locationService.updateFloorLayout(floorLayout);
        FloorLayoutDto dto = ModelMapper.transform(floorLayout, new FloorLayoutDto());
        return dto;
    }

    @RequestMapping(
            value = "/layout",
            method = RequestMethod.POST
    )
    @ResponseBody
    @Authorizable(resource = FLOOR_MAP, action = Action.CREATE)
    public FloorLayoutDto createFloorLayout(
            @RequestBody FloorLayoutDto floorLayoutDto
    ) {
        FloorLayout floorLayout = ModelMapper.apply(new FloorLayout(), floorLayoutDto);
        floorLayout = locationService.createFloorLayout(floorLayout);
        FloorLayoutDto dto = ModelMapper.transform(floorLayout, new FloorLayoutDto());
        return dto;
    }

    @RequestMapping(
            value = "/layout/id/{id}",
            method = RequestMethod.DELETE
    )
    @Authorizable(resource = FLOOR_MAP, action = Action.DELETE)
    public void deleteFloorLayout(@PathVariable(name = "id") String id) {
        locationService.deleteFloorLayout(id);
    }

}