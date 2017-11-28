package com.netikras.studies.studentbuddy.api.location.producer.impl.secured;

import com.netikras.studies.studentbuddy.core.data.api.dto.location.BuildingFloorDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.location.FloorLayoutDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.location.LectureRoomDto;
import com.netikras.studies.studentbuddy.core.data.api.model.BuildingFloor;
import com.netikras.studies.studentbuddy.core.data.api.model.FloorLayout;
import com.netikras.studies.studentbuddy.core.data.api.model.LectureRoom;
import com.netikras.studies.studentbuddy.core.data.meta.annotations.Authorizable;
import com.netikras.studies.studentbuddy.core.service.FloorService;
import com.netikras.studies.studentbuddy.core.validator.EntityProvider;
import com.netikras.tools.common.model.mapper.MappingSettings;
import com.netikras.tools.common.model.mapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

import static com.netikras.studies.studentbuddy.core.data.meta.Action.*;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.*;

@Component
public class FloorProducerImpl {

    @Resource
    private ModelMapper modelMapper;

    @Resource
    private FloorService floorService;

    @Resource
    private EntityProvider entityProvider;

    @Authorizable(resource = FLOOR, action = PURGE)
    public void purgeBuildingFloor(String id) {
        floorService.purgeFloor(id);
    }

    @Authorizable(resource = FLOOR_MAP, action = PURGE)
    public void purgeFloorLayout(String id) {
        floorService.purgeFloorLayout(id);
    }

    @Authorizable(resource = ROOM, action = PURGE)
    public void purgeLectureRoom(String id) {
        floorService.purgeRoom(id);
    }

    @Authorizable(resource = FLOOR, action = MODIFY)
    public BuildingFloorDto updateBuildingFloor(BuildingFloorDto buildingFloorDto) {
        BuildingFloor buildingFloor = modelMapper.apply(entityProvider.fetch(buildingFloorDto), buildingFloorDto);
        buildingFloor = floorService.updateFloor(buildingFloor);
        BuildingFloorDto dto = modelMapper.transform(buildingFloor, new BuildingFloorDto(), new MappingSettings().setDepthMax(3));
        return dto;
    }

    @Authorizable(resource = FLOOR, action = DELETE)
    public void deleteBuildingFloor(String id) {
        floorService.deleteFloor(id);
    }

    @Authorizable(resource = FLOOR, action = GET)
    public BuildingFloorDto getBuildingFloor(String id) {
        BuildingFloor buildingFloor = floorService.getFloor(id);
        BuildingFloorDto dto = modelMapper.transform(buildingFloor, new BuildingFloorDto(), new MappingSettings().setDepthMax(3));
        return dto;
    }

    @Authorizable(resource = FLOOR, action = CREATE)
    public BuildingFloorDto createBuildingFloor(BuildingFloorDto buildingFloorDto) {
        BuildingFloor buildingFloor = modelMapper.apply(new BuildingFloor(), buildingFloorDto, new MappingSettings().setForceUpdate(true));
        if (buildingFloor != null) buildingFloor.setId(null);
        buildingFloor = floorService.createFloor(buildingFloor);
        BuildingFloorDto dto = modelMapper.transform(buildingFloor, new BuildingFloorDto(), new MappingSettings().setDepthMax(3));
        return dto;
    }

    @Authorizable(resource = FLOOR_MAP, action = MODIFY)
    public FloorLayoutDto updateFloorLayout(FloorLayoutDto floorLayoutDto) {
        FloorLayout floorLayout = modelMapper.apply(entityProvider.fetch(floorLayoutDto), floorLayoutDto);
        floorLayout = floorService.updateFloorLayout(floorLayout);
        FloorLayoutDto dto = modelMapper.transform(floorLayout, new FloorLayoutDto(), new MappingSettings().setDepthMax(3));
        return dto;
    }

    @Authorizable(resource = FLOOR_MAP, action = DELETE)
    public void deleteFloorLayout(String id) {
        floorService.deleteFloorLayout(id);
    }

    @Authorizable(resource = FLOOR_MAP, action = GET)
    public FloorLayoutDto getFloorLayout(String id) {
        FloorLayout floorLayout = floorService.getFloorLayout(id);
        FloorLayoutDto dto = modelMapper.transform(floorLayout, new FloorLayoutDto(), new MappingSettings().setDepthMax(3));
        return dto;
    }

    @Authorizable(resource = FLOOR_MAP, action = CREATE)
    public FloorLayoutDto createFloorLayout(FloorLayoutDto floorLayoutDto) {
        FloorLayout floorLayout = modelMapper.apply(new FloorLayout(), floorLayoutDto, new MappingSettings().setForceUpdate(true));
        if (floorLayout != null) floorLayout.setId(null);
        floorLayout = floorService.createFloorLayout(floorLayout);
        FloorLayoutDto dto = modelMapper.transform(floorLayout, new FloorLayoutDto(), new MappingSettings().setDepthMax(3));
        return dto;
    }

    @Authorizable(resource = ROOM, action = MODIFY)
    public LectureRoomDto updateLectureRoom(LectureRoomDto lectureRoomDto) {
        LectureRoom lectureRoom = modelMapper.apply(entityProvider.fetch(lectureRoomDto), lectureRoomDto);
        lectureRoom = floorService.updateRoom(lectureRoom);
        LectureRoomDto dto = modelMapper.transform(lectureRoom, new LectureRoomDto(), new MappingSettings().setDepthMax(3));
        return dto;
    }

    @Authorizable(resource = ROOM, action = DELETE)
    public void deleteLectureRoom(String id) {
        floorService.deleteRoom(id);
    }

    @Authorizable(resource = ROOM, action = GET)
    public LectureRoomDto getLectureRoom(String id) {
        LectureRoom lectureRoom = floorService.getRoom(id);
        LectureRoomDto dto = modelMapper.transform(lectureRoom, new LectureRoomDto(), new MappingSettings().setDepthMax(3));
        return dto;
    }

    @Authorizable(resource = ROOM, action = CREATE)
    public LectureRoomDto createLectureRoom(LectureRoomDto lectureRoomDto) {
        LectureRoom lectureRoom = modelMapper.apply(new LectureRoom(), lectureRoomDto, new MappingSettings().setForceUpdate(true));
        if (lectureRoom != null) lectureRoom.setId(null);
        lectureRoom = floorService.createRoom(lectureRoom);
        LectureRoomDto dto = modelMapper.transform(lectureRoom, new LectureRoomDto(), new MappingSettings().setDepthMax(3));
        return dto;
    }

    @Authorizable(resource = FLOOR, action = SEARCH)
    public List<BuildingFloorDto> searchAllBuildingFloorsByTitle(String title) {
        List<BuildingFloor> buildingFloors = floorService.searchAllByTitle(title);
        List<BuildingFloorDto> dtos = (List<BuildingFloorDto>) modelMapper.transformAll(buildingFloors, BuildingFloorDto.class, new MappingSettings().setDepthMax(3));
        return dtos;
    }

    @Authorizable(resource = ROOM, action = SEARCH)
    public List<LectureRoomDto> searchAllLectureRoomsByTitle(String title) {
        List<LectureRoom> rooms = floorService.searchAllRoomsByTitle(title);
        List<LectureRoomDto> dtos = (List<LectureRoomDto>) modelMapper.transformAll(rooms, LectureRoomDto.class, new MappingSettings().setDepthMax(3));
        return dtos;
    }

    @Authorizable(resource = ROOM, action = SEARCH)
    public List<LectureRoomDto> searchAllLectureRoomsByNumber(String number) {
        List<LectureRoom> rooms = floorService.searchAllRoomsByNumber(number);
        List<LectureRoomDto> dtos = (List<LectureRoomDto>) modelMapper.transformAll(rooms, LectureRoomDto.class, new MappingSettings().setDepthMax(3));
        return dtos;
    }
}
