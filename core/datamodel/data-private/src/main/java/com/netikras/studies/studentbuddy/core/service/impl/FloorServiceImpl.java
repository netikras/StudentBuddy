package com.netikras.studies.studentbuddy.core.service.impl;

import com.netikras.studies.studentbuddy.core.data.api.dao.FloorDao;
import com.netikras.studies.studentbuddy.core.data.api.dao.FloorLayoutDao;
import com.netikras.studies.studentbuddy.core.data.api.dao.LectureRoomDao;
import com.netikras.studies.studentbuddy.core.data.api.model.BuildingFloor;
import com.netikras.studies.studentbuddy.core.data.api.model.FloorLayout;
import com.netikras.studies.studentbuddy.core.data.api.model.LectureRoom;
import com.netikras.studies.studentbuddy.core.service.FloorService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class FloorServiceImpl implements FloorService {

    @Resource
    private FloorDao floorDao;

    @Resource
    private LectureRoomDao roomDao;

    @Resource
    private FloorLayoutDao floorLayoutDao;


    @Override
    public BuildingFloor getFloor(String id) {
        return null;
    }

    @Override
    public BuildingFloor updateFloor(BuildingFloor buildingFloor) {
        return null;
    }

    @Override
    public BuildingFloor createFloor(BuildingFloor buildingFloor) {
        return null;
    }

    @Override
    public void deleteFloor(String id) {

    }

    @Override
    public LectureRoom getRoom(String id) {
        return null;
    }

    @Override
    public LectureRoom updateRoom(LectureRoom lectureRoom) {
        return null;
    }

    @Override
    public LectureRoom createRoom(LectureRoom lectureRoom) {
        return null;
    }

    @Override
    public void deleteRoom(String id) {

    }

    @Override
    public FloorLayout getFloorLayout(String id) {
        return null;
    }

    @Override
    public FloorLayout updateFloorLayout(FloorLayout floorLayout) {
        return null;
    }

    @Override
    public FloorLayout createFloorLayout(FloorLayout floorLayout) {
        return null;
    }

    @Override
    public void deleteFloorLayout(String id) {

    }
}
