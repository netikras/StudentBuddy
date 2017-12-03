package com.netikras.studies.studentbuddy.api.location.producer.impl.secured;

import com.netikras.studies.studentbuddy.api.handlers.DtoMapper;
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
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

import static com.netikras.studies.studentbuddy.core.data.meta.Action.CREATE;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.DELETE;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.GET;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.MODIFY;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.PURGE;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.SEARCH;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.FLOOR;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.FLOOR_MAP;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.ROOM;

@Component
public class FloorProducerImpl {

    @Resource
    private ModelMapper modelMapper;
    @Resource
    private DtoMapper dtoMapper;

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
    @Transactional
    public BuildingFloorDto updateBuildingFloor(BuildingFloorDto buildingFloorDto) {
        BuildingFloor buildingFloor = modelMapper.apply(entityProvider.fetch(buildingFloorDto), buildingFloorDto);
        buildingFloor = floorService.updateFloor(buildingFloor);
        BuildingFloorDto dto = (BuildingFloorDto) dtoMapper.toDto(buildingFloor);
        return dto;
    }

    @Authorizable(resource = FLOOR, action = DELETE)
    public void deleteBuildingFloor(String id) {
        floorService.deleteFloor(id);
    }

    @Authorizable(resource = FLOOR, action = GET)
    @Transactional
    public BuildingFloorDto getBuildingFloor(String id) {
        BuildingFloor buildingFloor = floorService.getFloor(id);
        BuildingFloorDto dto = (BuildingFloorDto) dtoMapper.toDto(buildingFloor);
        return dto;
    }

    @Authorizable(resource = FLOOR, action = CREATE)
    @Transactional
    public BuildingFloorDto createBuildingFloor(BuildingFloorDto buildingFloorDto) {
        BuildingFloor buildingFloor = modelMapper.apply(new BuildingFloor(), buildingFloorDto, new MappingSettings().setForceUpdate(true));
        if (buildingFloor != null) buildingFloor.setId(null);
        buildingFloor = floorService.createFloor(buildingFloor);
        BuildingFloorDto dto = (BuildingFloorDto) dtoMapper.toDto(buildingFloor);
        return dto;
    }

    @Authorizable(resource = FLOOR_MAP, action = MODIFY)
    @Transactional
    public FloorLayoutDto updateFloorLayout(FloorLayoutDto floorLayoutDto) {
        FloorLayout floorLayout = modelMapper.apply(entityProvider.fetch(floorLayoutDto), floorLayoutDto);
        floorLayout = floorService.updateFloorLayout(floorLayout);
        FloorLayoutDto dto = (FloorLayoutDto) dtoMapper.toDto(floorLayout);
        return dto;
    }

    @Authorizable(resource = FLOOR_MAP, action = DELETE)
    public void deleteFloorLayout(String id) {
        floorService.deleteFloorLayout(id);
    }

    @Authorizable(resource = FLOOR_MAP, action = GET)
    @Transactional
    public FloorLayoutDto getFloorLayout(String id) {
        FloorLayout floorLayout = floorService.getFloorLayout(id);
        FloorLayoutDto dto = (FloorLayoutDto) dtoMapper.toDto(floorLayout);
        return dto;
    }

    @Authorizable(resource = FLOOR_MAP, action = CREATE)
    @Transactional
    public FloorLayoutDto createFloorLayout(FloorLayoutDto floorLayoutDto) {
        FloorLayout floorLayout = modelMapper.apply(new FloorLayout(), floorLayoutDto, new MappingSettings().setForceUpdate(true));
        if (floorLayout != null) floorLayout.setId(null);
        floorLayout = floorService.createFloorLayout(floorLayout);
        FloorLayoutDto dto = (FloorLayoutDto) dtoMapper.toDto(floorLayout);
        return dto;
    }

    @Authorizable(resource = ROOM, action = MODIFY)
    @Transactional
    public LectureRoomDto updateLectureRoom(LectureRoomDto lectureRoomDto) {
        LectureRoom lectureRoom = modelMapper.apply(entityProvider.fetch(lectureRoomDto), lectureRoomDto);
        lectureRoom = floorService.updateRoom(lectureRoom);
        LectureRoomDto dto = (LectureRoomDto) dtoMapper.toDto(lectureRoom);
        return dto;
    }

    @Authorizable(resource = ROOM, action = DELETE)
    public void deleteLectureRoom(String id) {
        floorService.deleteRoom(id);
    }

    @Authorizable(resource = ROOM, action = GET)
    @Transactional
    public LectureRoomDto getLectureRoom(String id) {
        LectureRoom lectureRoom = floorService.getRoom(id);
        LectureRoomDto dto = (LectureRoomDto) dtoMapper.toDto(lectureRoom);
        return dto;
    }

    @Authorizable(resource = ROOM, action = CREATE)
    @Transactional
    public LectureRoomDto createLectureRoom(LectureRoomDto lectureRoomDto) {
        LectureRoom lectureRoom = modelMapper.apply(new LectureRoom(), lectureRoomDto, new MappingSettings().setForceUpdate(true));
        if (lectureRoom != null) lectureRoom.setId(null);
        lectureRoom = floorService.createRoom(lectureRoom);
        LectureRoomDto dto = (LectureRoomDto) dtoMapper.toDto(lectureRoom);
        return dto;
    }

    @Authorizable(resource = FLOOR, action = SEARCH)
    @Transactional
    public List<BuildingFloorDto> searchAllBuildingFloorsByTitle(String title) {
        List<BuildingFloor> buildingFloors = floorService.searchAllByTitle(title);
        List<BuildingFloorDto> dtos = (List<BuildingFloorDto>) dtoMapper.toDtos(buildingFloors);
        return dtos;
    }

    @Authorizable(resource = ROOM, action = SEARCH)
    @Transactional
    public List<LectureRoomDto> searchAllLectureRoomsByTitle(String title) {
        List<LectureRoom> rooms = floorService.searchAllRoomsByTitle(title);
        List<LectureRoomDto> dtos = (List<LectureRoomDto>) dtoMapper.toDtos(rooms);
        return dtos;
    }

    @Authorizable(resource = ROOM, action = SEARCH)
    @Transactional
    public List<LectureRoomDto> searchAllLectureRoomsByNumber(String number) {
        List<LectureRoom> rooms = floorService.searchAllRoomsByNumber(number);
        List<LectureRoomDto> dtos = (List<LectureRoomDto>) dtoMapper.toDtos(rooms);
        return dtos;
    }

    @Authorizable(resource = FLOOR_MAP, action = GET)
    @Transactional
    public List<FloorLayoutDto> getFloorLayoutsByFloorId(String id) {
        List<FloorLayout> layouts = floorService.getAllLayoutsByFloor(id);
        List<FloorLayoutDto> dtos = (List<FloorLayoutDto>) dtoMapper.toDtos(layouts);
        return dtos;
    }

    @Authorizable(resource = FLOOR_MAP, action = GET)
    @Transactional
    public List<FloorLayoutDto> getFloorLayoutsBySectionId(String id) {
        List<FloorLayout> layouts = floorService.getAllLayoutsByBuildingSection(id);
        List<FloorLayoutDto> dtos = (List<FloorLayoutDto>) dtoMapper.toDtos(layouts);
        return dtos;
    }

    @Authorizable(resource = FLOOR_MAP, action = GET)
    @Transactional
    public List<FloorLayoutDto> getFloorLayoutsByBuildingId(String id) {
        List<FloorLayout> layouts = floorService.getAllLayoutsByBuilding(id);
        List<FloorLayoutDto> dtos = (List<FloorLayoutDto>) dtoMapper.toDtos(layouts);
        return dtos;
    }

    @Authorizable(resource = FLOOR, action = GET)
    @Transactional
    public List<BuildingFloorDto> getFloorsBySectionId(String id) {
        List<BuildingFloor> buildingFloors = floorService.getAllFloorsByBuildingSection(id);
        List<BuildingFloorDto> dtos = (List<BuildingFloorDto>) dtoMapper.toDtos(buildingFloors);
        return dtos;
    }

    @Authorizable(resource = FLOOR, action = GET)
    @Transactional
    public List<BuildingFloorDto> getFloorsByBuildingId(String id) {
        List<BuildingFloor> buildingFloors = floorService.getAllFloorsByBuilding(id);
        List<BuildingFloorDto> dtos = (List<BuildingFloorDto>) dtoMapper.toDtos(buildingFloors);
        return dtos;
    }

    @Authorizable(resource = ROOM, action = GET)
    @Transactional
    public List<LectureRoomDto> getRoomsBySectionId(String id) {
        List<LectureRoom> rooms = floorService.getAllRoomsBySection(id);
        List<LectureRoomDto> dtos = (List<LectureRoomDto>) dtoMapper.toDtos(rooms);
        return dtos;
    }

    @Authorizable(resource = ROOM, action = GET)
    @Transactional
    public List<LectureRoomDto> getRoomsByBuildingId(String id) {
        List<LectureRoom> rooms = floorService.getAllRoomsByBuilding(id);
        List<LectureRoomDto> dtos = (List<LectureRoomDto>) dtoMapper.toDtos(rooms);
        return dtos;
    }

    @Authorizable(resource = ROOM, action = GET)
    @Transactional
    public List<LectureRoomDto> getRoomsByLayoutId(String id) {
        List<LectureRoom> rooms = floorService.getRoomsByLayout(id);
        List<LectureRoomDto> dtos = (List<LectureRoomDto>) dtoMapper.toDtos(rooms);
        return dtos;
    }

    @Authorizable(resource = ROOM, action = GET)
    @Transactional
    public List<LectureRoomDto> getRoomsByFloorId(String id) {
        List<LectureRoom> rooms = floorService.getRoomsByFloor(id);
        List<LectureRoomDto> dtos = (List<LectureRoomDto>) dtoMapper.toDtos(rooms);
        return dtos;
    }
}
