package com.netikras.studies.studentbuddy.core.validator;

import com.netikras.studies.studentbuddy.core.data.api.dao.BuildingDao;
import com.netikras.studies.studentbuddy.core.data.api.dao.BuildingSectionDao;
import com.netikras.studies.studentbuddy.core.data.api.dao.FloorDao;
import com.netikras.studies.studentbuddy.core.data.api.dao.LectureRoomDao;
import com.netikras.studies.studentbuddy.core.data.api.dao.SchoolDepartmentDao;
import com.netikras.studies.studentbuddy.core.data.api.model.Address;
import com.netikras.studies.studentbuddy.core.data.api.model.Building;
import com.netikras.studies.studentbuddy.core.data.api.model.BuildingFloor;
import com.netikras.studies.studentbuddy.core.data.api.model.BuildingSection;
import com.netikras.studies.studentbuddy.core.data.api.model.FloorLayout;
import com.netikras.studies.studentbuddy.core.data.api.model.LectureRoom;
import com.netikras.studies.studentbuddy.core.data.api.model.SchoolDepartment;
import com.netikras.tools.common.exception.ErrorsCollection;
import com.netikras.tools.common.exception.ValidationError;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

import static com.netikras.tools.common.remote.http.HttpStatus.CONFLICT;
import static com.netikras.tools.common.remote.http.HttpStatus.NOT_FOUND;
import static com.netikras.tools.common.security.IntegrityUtils.ensureValue;
import static com.netikras.tools.common.security.IntegrityUtils.isNullOrEmpty;

@SuppressWarnings("Duplicates")
@Component
public class LocationValidator {


    @Resource
    private SchoolDepartmentDao schoolDepartmentDao;
    @Resource
    private BuildingDao buildingDao;
    @Resource
    private BuildingSectionDao sectionDao;
    @Resource
    private LectureRoomDao roomDao;
    @Resource
    private FloorDao floorDao;


    @Transactional
    public ErrorsCollection validateForCreation(Building building, ErrorsCollection errors) {
        errors = ensure(errors);

        if (building == null) {
            errors.add(new ValidationError()
                    .setSuggestion("Cannot create a non-existing building")
                    .setMessage1("Building is not given")
                    .setStatus(NOT_FOUND.getCode())
            );
            return errors;
        }

        List<BuildingSection> sections = building.getSections();
        if (sections != null && !sections.isEmpty()) {
            sections.forEach(section -> section.setBuilding(building));
        }


        validateForCreation(building.getAddress(), errors);

        SchoolDepartment department = building.getDepartment();

        if (department != null) {
            if (isNullOrEmpty(department.getId())) {
                building.setDepartment(null);
            } else {
                department = schoolDepartmentDao.getOne(department.getId());
                building.setDepartment(department);
                if (department == null) {
                    errors.add(new ValidationError()
                            .setSuggestion("If school department is specified it must be a valid, already existing entry (with particular ID)")
                            .setMessage1("School department missing")
                            .setStatus(NOT_FOUND.getCode())
                    );
                }
            }
        }

        List<Building> existingBuildings = buildingDao.findAllByTitleLikeIgnoreCase("^" + building.getTitle() + "$");
        if (!isNullOrEmpty(existingBuildings)) {
            errors.add(new ValidationError()
                    .setSuggestion("If a building has its title it must be a unique title")
                    .setMessage1("Building already exists")
                    .setStatus(CONFLICT.getCode())
            );
        }
        building.setFloors(null);
        building.setId(null);

        return errors;
    }



    @Transactional
    public ErrorsCollection validateForCreation(BuildingSection section, ErrorsCollection errors) {
        errors = ensure(errors);

        if (section == null) {
            errors.add(new ValidationError()
                    .setSuggestion("Cannot create a non-existing building section")
                    .setMessage1("Section is not given")
                    .setStatus(NOT_FOUND.getCode())
            );
            return errors;
        }

        Building building = section.getBuilding();
        if (building != null) {
            Building existingBuilding = null;
            if (!isNullOrEmpty(building.getId())) {
                existingBuilding = buildingDao.findOne(building.getId());
            }
            section.setBuilding(existingBuilding);
        }

        if (section.getBuilding() == null) {
            errors.add(new ValidationError()
                    .setSuggestion("Building section must be linked to some already existing building")
                    .setMessage1("Building is missing")
                    .setStatus(NOT_FOUND.getCode())
            );
        } else {
            BuildingSection existingSection = sectionDao.findByTitleAndBuilding_Id(section.getTitle(), section.getBuilding().getId());
            if (existingSection != null) {
                errors.add(new ValidationError()
                        .setSuggestion("Every building section must have a unique title in building context")
                        .setMessage1("Building section already exists")
                        .setStatus(CONFLICT.getCode())
                );
            }
        }

        section.setFloors(null);
        section.setId(null);
        return errors;
    }


    @Transactional
    public ErrorsCollection validateForCreation(Address address, ErrorsCollection errors) {
        errors = ensure(errors);

        if (address == null) {
            errors.add(new ValidationError()
                    .setSuggestion("Cannot create a non-existing address")
                    .setMessage1("Address is not given")
                    .setStatus(NOT_FOUND.getCode())
            );
            return errors;
        }

        if (isNullOrEmpty(address.getLatitude())
                || isNullOrEmpty(address.getLongitude())) {

            if (isNullOrEmpty(address.getCountry())
                    || isNullOrEmpty(address.getCity())
                    || isNullOrEmpty(address.getStreet())) {

                errors.add(new ValidationError()
                        .setSuggestion("Address lacks mandatory fields: country, city and street. " +
                                "GPS coordinates could be set as either additional property or instead of mandatory fields")
                        .setMessage1("Address leads to nowhere")
                        .setStatus(NOT_FOUND.getCode())
                );
            }
        }
        address.setId(null);
        return errors;
    }


    @Transactional
    public ErrorsCollection validateForCreation(BuildingFloor floor, ErrorsCollection errors) {
        errors = ensure(errors);

        if (floor == null) {
            errors.add(new ValidationError()
                    .setSuggestion("Cannot create a non-existing floor")
                    .setMessage1("Floor is not given")
                    .setStatus(NOT_FOUND.getCode())
            );
            return errors;
        }


        Building building = floor.getBuilding();
        BuildingSection section = floor.getSection();

        if (building != null) {
            Building existingBuilding = null;
            if (!isNullOrEmpty(building.getId())) {
                existingBuilding = buildingDao.findOne(building.getId());
            }
            floor.setBuilding(existingBuilding);
        }

        if (section != null) {
            BuildingSection existingSection = null;
            if (!isNullOrEmpty(section.getId())) {
                existingSection = sectionDao.findOne(section.getId());
            }
            floor.setSection(existingSection);
        }


        if (floor.getBuilding() == null && floor.getSection() == null) {
            errors.add(new ValidationError()
                    .setSuggestion("Floor must be in some building or building section")
                    .setMessage1("Floor building or section missing")
                    .setStatus(NOT_FOUND.getCode())
            );
        }

        floor.setId(null);
        floor.setRooms(null);

        return errors;
    }


    @Transactional
    public ErrorsCollection validateForCreation(FloorLayout layout, ErrorsCollection errors) {
        errors = ensure(errors);

        if (layout == null) {
            errors.add(new ValidationError()
                    .setSuggestion("Cannot create a non-existing floor layout")
                    .setMessage1("Layout is not given")
                    .setStatus(NOT_FOUND.getCode())
            );
            return errors;
        }

        if (layout.getFloorMap() == null) {
            errors.add(new ValidationError()
                    .setSuggestion("Floor map is useless if there is only information without the actual map. Hence the map is required")
                    .setMessage1("Map data is missing")
                    .setStatus(NOT_FOUND.getCode())
            );
        }

        BuildingFloor floor = layout.getFloor();
        if (floor != null) {
            BuildingFloor existingFloor = null;
            if (!isNullOrEmpty(floor.getId())) {
                existingFloor = floorDao.findOne(floor.getId());
            }
            layout.setFloor(existingFloor);
        }

        if (layout.getFloor() == null) {
            errors.add(new ValidationError()
                    .setSuggestion("Floor layout must be linked to some already existing floor")
                    .setMessage1("Floor not provided")
                    .setStatus(NOT_FOUND.getCode())
            );
        }

        layout.setId(null);
        return errors;
    }


    @Transactional
    public ErrorsCollection validateForCreation(LectureRoom room, ErrorsCollection errors) {
        errors = ensure(errors);

        if (room == null) {
            errors.add(new ValidationError()
                    .setSuggestion("Cannot create a non-existing room")
                    .setMessage1("Room is not given")
                    .setStatus(NOT_FOUND.getCode())
            );
            return errors;
        }

        if (isNullOrEmpty(room.getNumber())
                && isNullOrEmpty(room.getTitle())) {
            errors.add(new ValidationError()
                    .setSuggestion("Every room must have either a number or a title. Neither has to be unique globally but should be unique per-floor")
                    .setMessage1("Room is unnamed")
                    .setStatus(NOT_FOUND.getCode())
            );
        }

        BuildingFloor floor = room.getFloor();
        if (floor != null) {
            BuildingFloor existingFloor = null;

            if (!isNullOrEmpty(floor.getId())) {
                existingFloor = floorDao.findOne(floor.getId());
            }
            room.setFloor(existingFloor);
        }

        if (room.getFloor() == null) {
            errors.add(new ValidationError()
                    .setSuggestion("Every room must be on some floor")
                    .setMessage1("Room floor not provided")
                    .setStatus(NOT_FOUND.getCode())
            );
        } else {
            LectureRoom existingRoom = roomDao.findLectureRoomByTitleAndNumberAndFloor_Id(room.getTitle(), room.getNumber(), room.getFloor().getId());
            if (existingRoom != null) {
                errors.add(new ValidationError()
                        .setSuggestion("Every room must have a unique collection of FLOOR, NUMBER and TITLE")
                        .setMessage1("Room already exists")
                        .setStatus(CONFLICT.getCode())
                );
            }
        }

        room.setId(null);
        room.setComments(null);
        room.setSchool(null);

        return errors;
    }


    private ErrorsCollection ensure(ErrorsCollection errors) {
        return ensureValue(errors, ErrorsCollection.class);
    }

}
