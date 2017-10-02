package com.netikras.studies.studentbuddy.api.location.producer;

import com.netikras.studies.studentbuddy.api.location.generated.FloorApiProducer;
import com.netikras.studies.studentbuddy.api.location.producer.impl.secured.FloorProducerImpl;
import com.netikras.studies.studentbuddy.core.data.api.dto.location.BuildingFloorDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.location.FloorLayoutDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.location.LectureRoomDto;
import com.netikras.studies.studentbuddy.core.data.api.model.BuildingFloor;
import com.netikras.studies.studentbuddy.core.data.api.model.FloorLayout;
import com.netikras.studies.studentbuddy.core.data.api.model.LectureRoom;
import com.netikras.studies.studentbuddy.core.data.meta.annotations.Authorizable;
import com.netikras.studies.studentbuddy.core.service.FloorService;
import com.netikras.tools.common.model.mapper.MappingSettings;
import com.netikras.tools.common.model.mapper.ModelMapper;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
public class FloorProducer extends FloorApiProducer {

    @Resource
    private FloorProducerImpl impl;

    @Override
    protected void onPurgeBuildingFloorDto(String id) {
        impl.purgeBuildingFloor(id);
    }

    @Override
    protected void onPurgeFloorLayoutDto(String id) {
        impl.purgeFloorLayout(id);
    }

    @Override
    protected void onPurgeLectureRoomDto(String id) {
        impl.purgeLectureRoom(id);
    }

    @Override
    protected BuildingFloorDto onUpdateBuildingFloorDto(BuildingFloorDto buildingFloorDto) {
        return impl.updateBuildingFloor(buildingFloorDto);
    }

    @Override
    protected void onDeleteBuildingFloorDto(String id) {
        impl.deleteBuildingFloor(id);
    }

    @Override
    protected BuildingFloorDto onRetrieveBuildingFloorDto(String id) {
        return impl.getBuildingFloor(id);
    }

    @Override
    protected BuildingFloorDto onCreateBuildingFloorDto(BuildingFloorDto buildingFloorDto) {
        return impl.createBuildingFloor(buildingFloorDto);
    }

    @Override
    protected FloorLayoutDto onUpdateFloorLayoutDto(FloorLayoutDto floorLayoutDto) {
        return impl.updateFloorLayout(floorLayoutDto);
    }

    @Override
    protected void onDeleteFloorLayoutDto(String id) {
        impl.deleteFloorLayout(id);
    }

    @Override
    protected FloorLayoutDto onRetrieveFloorLayoutDto(String id) {
        return impl.getFloorLayout(id);
    }

    @Override
    protected FloorLayoutDto onCreateFloorLayoutDto(FloorLayoutDto floorLayoutDto) {
        return impl.createFloorLayout(floorLayoutDto);
    }

    @Override
    protected LectureRoomDto onUpdateLectureRoomDto(LectureRoomDto lectureRoomDto) {
        return impl.updateLectureRoom(lectureRoomDto);
    }

    @Override
    protected void onDeleteLectureRoomDto(String id) {
        impl.deleteLectureRoom(id);
    }

    @Override
    protected LectureRoomDto onRetrieveLectureRoomDto(String id) {
        return impl.getLectureRoom(id);
    }

    @Override
    protected LectureRoomDto onCreateLectureRoomDto(LectureRoomDto lectureRoomDto) {
        return impl.createLectureRoom(lectureRoomDto);
    }

    @Override
    protected List<BuildingFloorDto> onSearchBuildingFloorDtoAllByTitle(String title) {
        return impl.searchAllBuildingFloorsByTitle(title);
    }

    @Override
    protected List<LectureRoomDto> onSearchLectureRoomDtoAllByTitle(String title) {
        return impl.searchAllLectureRoomsByTitle(title);
    }

    @Override
    protected List<LectureRoomDto> onSearchLectureRoomDtoAllByNumber(String number) {
        return impl.searchAllLectureRoomsByNumber(number);
    }
}
