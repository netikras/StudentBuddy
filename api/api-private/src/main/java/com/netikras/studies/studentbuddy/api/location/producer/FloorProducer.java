package com.netikras.studies.studentbuddy.api.location.producer;

import com.netikras.studies.studentbuddy.api.location.generated.FloorApiProducer;
import com.netikras.studies.studentbuddy.core.data.api.dto.location.BuildingFloorDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.location.FloorLayoutDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.location.LectureRoomDto;
import com.netikras.studies.studentbuddy.core.data.api.model.BuildingFloor;
import com.netikras.studies.studentbuddy.core.data.api.model.FloorLayout;
import com.netikras.studies.studentbuddy.core.data.api.model.LectureRoom;
import com.netikras.studies.studentbuddy.core.meta.annotations.Authorizable;
import com.netikras.studies.studentbuddy.core.service.FloorService;
import com.netikras.tools.common.model.mapper.MappingSettings;
import com.netikras.tools.common.model.mapper.ModelMapper;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

import static com.netikras.studies.studentbuddy.core.meta.Action.CREATE;
import static com.netikras.studies.studentbuddy.core.meta.Action.DELETE;
import static com.netikras.studies.studentbuddy.core.meta.Action.GET;
import static com.netikras.studies.studentbuddy.core.meta.Action.MODIFY;
import static com.netikras.studies.studentbuddy.core.meta.Action.SEARCH;
import static com.netikras.studies.studentbuddy.core.meta.Resource.FLOOR;
import static com.netikras.studies.studentbuddy.core.meta.Resource.FLOOR_MAP;
import static com.netikras.studies.studentbuddy.core.meta.Resource.ROOM;

@RestController
public class FloorProducer extends FloorApiProducer {

    @Resource
    private FloorService floorService;


    @Override
    @Authorizable(resource = FLOOR, action = MODIFY)
    protected BuildingFloorDto onUpdateBuildingFloorDto(BuildingFloorDto buildingFloorDto) {
        BuildingFloor buildingFloor = ModelMapper.apply(new BuildingFloor(), buildingFloorDto);
        buildingFloor = floorService.updateFloor(buildingFloor);
        BuildingFloorDto dto = ModelMapper.transform(buildingFloor, new BuildingFloorDto());
        return dto;
    }

    @Override
    @Authorizable(resource = FLOOR, action = DELETE)
    protected void onDeleteBuildingFloorDto(String id) {
        floorService.deleteFloor(id);
    }

    @Override
    @Authorizable(resource = FLOOR, action = GET)
    protected BuildingFloorDto onRetrieveBuildingFloorDto(String id) {
        BuildingFloor buildingFloor = floorService.getFloor(id);
        BuildingFloorDto dto = ModelMapper.transform(buildingFloor, new BuildingFloorDto());
        return dto;
    }

    @Override
    @Authorizable(resource = FLOOR, action = CREATE)
    protected BuildingFloorDto onCreateBuildingFloorDto(BuildingFloorDto buildingFloorDto) {
        BuildingFloor buildingFloor = ModelMapper.apply(new BuildingFloor(), buildingFloorDto, new MappingSettings().setForceUpdate(true));
        if (buildingFloor != null) buildingFloor.setId(null);
        buildingFloor = floorService.createFloor(buildingFloor);
        BuildingFloorDto dto = ModelMapper.transform(buildingFloor, new BuildingFloorDto());
        return dto;
    }

    @Override
    @Authorizable(resource = FLOOR_MAP, action = MODIFY)
    protected FloorLayoutDto onUpdateFloorLayoutDto(FloorLayoutDto floorLayoutDto) {
        FloorLayout floorLayout = ModelMapper.apply(new FloorLayout(), floorLayoutDto);
        floorLayout = floorService.updateFloorLayout(floorLayout);
        FloorLayoutDto dto = ModelMapper.transform(floorLayout, new FloorLayoutDto());
        return dto;
    }

    @Override
    @Authorizable(resource = FLOOR_MAP, action = DELETE)
    protected void onDeleteFloorLayoutDto(String id) {
        floorService.deleteFloorLayout(id);
    }

    @Override
    @Authorizable(resource = FLOOR_MAP, action = GET)
    protected FloorLayoutDto onRetrieveFloorLayoutDto(String id) {
        FloorLayout floorLayout = floorService.getFloorLayout(id);
        FloorLayoutDto dto = ModelMapper.transform(floorLayout, new FloorLayoutDto());
        return dto;
    }

    @Override
    @Authorizable(resource = FLOOR_MAP, action = CREATE)
    protected FloorLayoutDto onCreateFloorLayoutDto(FloorLayoutDto floorLayoutDto) {
        FloorLayout floorLayout = ModelMapper.apply(new FloorLayout(), floorLayoutDto, new MappingSettings().setForceUpdate(true));
        if (floorLayout != null) floorLayout.setId(null);
        floorLayout = floorService.createFloorLayout(floorLayout);
        FloorLayoutDto dto = ModelMapper.transform(floorLayout, new FloorLayoutDto());
        return dto;
    }

    @Override
    @Authorizable(resource = ROOM, action = MODIFY)
    protected LectureRoomDto onUpdateLectureRoomDto(LectureRoomDto lectureRoomDto) {
        LectureRoom lectureRoom = ModelMapper.apply(new LectureRoom(), lectureRoomDto);
        lectureRoom = floorService.updateRoom(lectureRoom);
        LectureRoomDto dto = ModelMapper.transform(lectureRoom, new LectureRoomDto());
        return dto;
    }

    @Override
    @Authorizable(resource = ROOM, action = DELETE)
    protected void onDeleteLectureRoomDto(String id) {
        floorService.deleteRoom(id);
    }

    @Override
    @Authorizable(resource = ROOM, action = GET)
    protected LectureRoomDto onRetrieveLectureRoomDto(String id) {
        LectureRoom lectureRoom = floorService.getRoom(id);
        LectureRoomDto dto = ModelMapper.transform(lectureRoom, new LectureRoomDto());
        return dto;
    }

    @Override
    @Authorizable(resource = ROOM, action = CREATE)
    protected LectureRoomDto onCreateLectureRoomDto(LectureRoomDto lectureRoomDto) {
        LectureRoom lectureRoom = ModelMapper.apply(new LectureRoom(), lectureRoomDto, new MappingSettings().setForceUpdate(true));
        if (lectureRoom != null) lectureRoom.setId(null);
        lectureRoom = floorService.createRoom(lectureRoom);
        LectureRoomDto dto = ModelMapper.transform(lectureRoom, new LectureRoomDto());
        return dto;
    }

    @Override
    @Authorizable(resource = FLOOR, action = SEARCH)
    protected List<BuildingFloorDto> onSearchBuildingFloorDtoAllByTitle(String title) {
        List<BuildingFloor> buildingFloors = floorService.searchAllByTitle(title);
        List<BuildingFloorDto> dtos = (List<BuildingFloorDto>) ModelMapper.transformAll(buildingFloors, BuildingFloorDto.class);
        return dtos;
    }

    @Override
    @Authorizable(resource = ROOM, action = SEARCH)
    protected List<LectureRoomDto> onSearchLectureRoomDtoAllByTitle(String title) {
        List<LectureRoom> rooms = floorService.searchAllRoomsByTitle(title);
        List<LectureRoomDto> dtos = (List<LectureRoomDto>) ModelMapper.transformAll(rooms, LectureRoomDto.class);
        return dtos;
    }

    @Override
    @Authorizable(resource = ROOM, action = SEARCH)
    protected List<LectureRoomDto> onSearchLectureRoomDtoAllByNumber(String number) {
        List<LectureRoom> rooms = floorService.searchAllRoomsByNumber(number);
        List<LectureRoomDto> dtos = (List<LectureRoomDto>) ModelMapper.transformAll(rooms, LectureRoomDto.class);
        return dtos;
    }
}
