package com.netikras.studies.studentbuddy.core.service;

import com.netikras.studies.studentbuddy.core.data.api.model.BuildingFloor;
import com.netikras.studies.studentbuddy.core.data.api.model.FloorLayout;
import com.netikras.studies.studentbuddy.core.data.api.model.LectureRoom;

import java.util.List;

public interface FloorService {


    BuildingFloor getFloor(String id);

    BuildingFloor updateFloor(BuildingFloor buildingFloor);

    BuildingFloor createFloor(BuildingFloor buildingFloor);

    void deleteFloor(String id);

    void purgeFloor(String id);

    LectureRoom getRoom(String id);

    LectureRoom updateRoom(LectureRoom lectureRoom);

    LectureRoom createRoom(LectureRoom lectureRoom);

    void deleteRoom(String id);

    void purgeRoom(String id);

    FloorLayout getFloorLayout(String id);

    FloorLayout updateFloorLayout(FloorLayout floorLayout);

    FloorLayout createFloorLayout(FloorLayout floorLayout);

    void deleteFloorLayout(String id);

    List<LectureRoom> searchAllRoomsByTitle(String query);

    List<LectureRoom> searchAllRoomsByNumber(String query);

    List<BuildingFloor> searchAllByTitle(String query);

    void purgeFloorLayout(String id);

    List<FloorLayout> getAllLayoutsByFloor(String id);

    List<FloorLayout> getAllLayoutsByBuildingSection(String id);

    List<FloorLayout> getAllLayoutsByBuilding(String id);

    List<BuildingFloor> getAllFloorsByBuildingSection(String id);

    List<BuildingFloor> getAllFloorsByBuilding(String id);

    List<LectureRoom> getAllRoomsBySection(String id);

    List<LectureRoom> getAllRoomsByBuilding(String id);

    List<LectureRoom> getRoomsByLayout(String id);

    List<LectureRoom> getRoomsByFloor(String id);
}
