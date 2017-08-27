package com.netikras.studies.studentbuddy.core.service.impl;

import com.netikras.studies.studentbuddy.commons.exception.StudBudUncheckedException;
import com.netikras.studies.studentbuddy.core.data.api.dao.FloorDao;
import com.netikras.studies.studentbuddy.core.data.api.dao.FloorLayoutDao;
import com.netikras.studies.studentbuddy.core.data.api.dao.LectureRoomDao;
import com.netikras.studies.studentbuddy.core.data.api.model.BuildingFloor;
import com.netikras.studies.studentbuddy.core.data.api.model.FloorLayout;
import com.netikras.studies.studentbuddy.core.data.api.model.LectureRoom;
import com.netikras.studies.studentbuddy.core.service.FloorService;
import com.netikras.studies.studentbuddy.core.validator.LocationValidator;
import com.netikras.tools.common.exception.ErrorsCollection;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

import static com.netikras.tools.common.remote.http.HttpStatus.BAD_REQUEST;
import static com.netikras.tools.common.security.IntegrityUtils.isNullOrEmpty;

@Service
public class FloorServiceImpl implements FloorService {

    @Resource
    private FloorDao floorDao;

    @Resource
    private LectureRoomDao roomDao;

    @Resource
    private FloorLayoutDao floorLayoutDao;

    @Resource
    private LocationValidator locationValidator;


    @Override
    public BuildingFloor getFloor(String id) {
        return floorDao.findOne(id);
    }

    @Override
    public BuildingFloor updateFloor(BuildingFloor buildingFloor) {
        return floorDao.save(buildingFloor);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BuildingFloor createFloor(BuildingFloor buildingFloor) {
        List<FloorLayout> layouts = buildingFloor.getLayouts();
        buildingFloor.setLayouts(null);
        List<LectureRoom> rooms = buildingFloor.getRooms();
        buildingFloor.setRooms(null);

        ErrorsCollection errors = locationValidator.validateForCreation(buildingFloor, null);
        if (!errors.isEmpty()) {
            throw new StudBudUncheckedException()
                    .setMessage1("Cannot create new floor")
                    .setMessage2("Validation errors: " + errors.size())
                    .setErrors(errors)
                    .setStatusCode(BAD_REQUEST)
                    ;
        }

        buildingFloor = floorDao.save(buildingFloor);

        if (!isNullOrEmpty(layouts)) {
            for (FloorLayout layout : layouts) {
                layout.setFloor(buildingFloor);
                createFloorLayout(layout);
            }
        }

        if (!isNullOrEmpty(rooms)) {
            for (LectureRoom room : rooms) {
                room.setFloor(buildingFloor);
//                room.setSchool(buildingFloor.getBuilding().getDepartment().getSchool());
                createRoom(room);
            }
        }

        buildingFloor = floorDao.findOne(buildingFloor.getId());

        return buildingFloor;
    }

    @Override
    public void deleteFloor(String id) {
        floorDao.delete(id);
    }

    @Override
    public LectureRoom getRoom(String id) {
        return roomDao.findOne(id);
    }

    @Override
    public LectureRoom updateRoom(LectureRoom lectureRoom) {
        return roomDao.save(lectureRoom);
    }

    @Override
    @Transactional
    public LectureRoom createRoom(LectureRoom lectureRoom) {
        ErrorsCollection errors = locationValidator.validateForCreation(lectureRoom, null);
        if (!errors.isEmpty()) {
            throw new StudBudUncheckedException()
                    .setMessage1("Cannot create new room")
                    .setMessage2("Validation errors: " + errors.size())
                    .setErrors(errors)
                    .setStatusCode(BAD_REQUEST)
                    ;
        }

        return roomDao.save(lectureRoom);
    }

    @Override
    public void deleteRoom(String id) {
        roomDao.delete(id);
    }

    @Override
    public FloorLayout getFloorLayout(String id) {
        return floorLayoutDao.findOne(id);
    }

    @Override
    public FloorLayout updateFloorLayout(FloorLayout floorLayout) {
        return floorLayoutDao.save(floorLayout);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public FloorLayout createFloorLayout(FloorLayout floorLayout) {
        ErrorsCollection errors = locationValidator.validateForCreation(floorLayout, null);
        if (!errors.isEmpty()) {
            throw new StudBudUncheckedException()
                    .setMessage1("Cannot create new floor layout")
                    .setMessage2("Validation errors: " + errors.size())
                    .setErrors(errors)
                    .setStatusCode(BAD_REQUEST)
                    ;
        }

        return floorLayoutDao.save(floorLayout);
    }

    @Override
    public void deleteFloorLayout(String id) {
        floorLayoutDao.delete(id);
    }


    // search

    @Override
    public List<LectureRoom> searchAllRoomsByTitle(String query) {
        return roomDao.findAllByTitleLikeIgnoreCase(roomDao.wrapSearchString(query));
    }

    @Override
    public List<LectureRoom> searchAllRoomsByNumber(String query) {
        return roomDao.findAllByNumberLikeIgnoreCase(roomDao.wrapSearchString(query));
    }

    @Override
    public List<BuildingFloor> searchAllByTitle(String query) {
        return floorDao.findAllByTitleLikeIgnoreCase(floorDao.wrapSearchString(query));
    }
}
