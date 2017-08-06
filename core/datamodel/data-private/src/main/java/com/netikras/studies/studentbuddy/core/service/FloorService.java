package com.netikras.studies.studentbuddy.core.service;

import com.netikras.studies.studentbuddy.core.data.api.model.BuildingFloor;
import com.netikras.studies.studentbuddy.core.data.api.model.FloorLayout;
import com.netikras.studies.studentbuddy.core.data.api.model.LectureRoom;

public interface FloorService {


    BuildingFloor getFloor(String id);

    BuildingFloor updateFloor(BuildingFloor buildingFloor);

    BuildingFloor createFloor(BuildingFloor buildingFloor);

    void deleteFloor(String id);

    LectureRoom getRoom(String id);

    LectureRoom updateRoom(LectureRoom lectureRoom);

    LectureRoom createRoom(LectureRoom lectureRoom);

    void deleteRoom(String id);

    FloorLayout getFloorLayout(String id);

    FloorLayout updateFloorLayout(FloorLayout floorLayout);

    FloorLayout createFloorLayout(FloorLayout floorLayout);

    void deleteFloorLayout(String id);
}
