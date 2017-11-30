package com.netikras.studies.studentbuddy.core.service.impl;

import com.netikras.studies.studentbuddy.commons.exception.StudBudUncheckedException;
import com.netikras.studies.studentbuddy.core.data.api.dao.AddressDao;
import com.netikras.studies.studentbuddy.core.data.api.dao.BuildingDao;
import com.netikras.studies.studentbuddy.core.data.api.dao.BuildingSectionDao;
import com.netikras.studies.studentbuddy.core.data.api.model.Address;
import com.netikras.studies.studentbuddy.core.data.api.model.Building;
import com.netikras.studies.studentbuddy.core.data.api.model.BuildingFloor;
import com.netikras.studies.studentbuddy.core.data.api.model.BuildingSection;
import com.netikras.studies.studentbuddy.core.service.FloorService;
import com.netikras.studies.studentbuddy.core.service.LocationService;
import com.netikras.studies.studentbuddy.core.validator.LocationValidator;
import com.netikras.tools.common.exception.ErrorsCollection;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static com.netikras.tools.common.remote.http.HttpStatus.BAD_REQUEST;
import static com.netikras.tools.common.security.IntegrityUtils.isNullOrEmpty;

@Service
public class LocationServiceImpl implements LocationService {


    @Resource
    private BuildingDao buildingDao;

    @Resource
    private AddressDao addressDao;

    @Resource
    private BuildingSectionDao buildingSectionDao;

    @Resource
    private LocationValidator locationValidator;

    @Resource
    private FloorService floorService;


    @Override
    public Building getBuilding(String id) {
        return buildingDao.findOne(id);
    }

    @Override
    public Building updateBuilding(Building building) {
        ErrorsCollection errors = locationValidator.validateForUpdate(building, null);
        if (!errors.isEmpty()) {
            throw new StudBudUncheckedException()
                    .setMessage1("Cannot update building")
                    .setMessage2("Validation errors: " + errors.size())
                    .setErrors(errors)
                    .setStatusCode(BAD_REQUEST)
                    ;
        }
        return buildingDao.save(building);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Building createBuilding(Building building) {

        List<BuildingFloor> floors = building.getFloors();
        building.setFloors(null);

        List<BuildingSection> sections = building.getSections();
        building.setSections(null);

        ErrorsCollection errors = locationValidator.validateForCreation(building, null);
        if (!errors.isEmpty()) {
            throw new StudBudUncheckedException()
                    .setMessage1("Cannot create new building")
                    .setMessage2("Validation errors: " + errors.size())
                    .setErrors(errors)
                    .setStatusCode(BAD_REQUEST)
                    ;
        }

        building = buildingDao.save(building);

        if (!isNullOrEmpty(floors)) {
            List<BuildingFloor> createdFloors = new ArrayList<>();
            for (BuildingFloor floor : floors) {
                floor.setBuilding(building);
                floor = floorService.createFloor(floor);
                if (floor != null) {
                    createdFloors.add(floor);
                }
            }
            building.setFloors(createdFloors);
        }

        if (!isNullOrEmpty(sections)) {
            List<BuildingSection> createdSections = new ArrayList<>();
            for (BuildingSection section : sections) {
                section.setBuilding(building);
                if (!isNullOrEmpty(section.getFloors())) {
                    for (BuildingFloor floor : section.getFloors()) {
                        floor.setBuilding(building);
                    }
                }
                section = createBuildingSection(section);
                if (section != null) {
                    createdSections.add(section);
                }
            }
            building.setSections(createdSections);
        }

        building = buildingDao.findOne(building.getId());

        return building;
    }

    @Override
    @Transactional
    public void deleteBuilding(String id) {
        Building building = getBuilding(id);
        ErrorsCollection errors = locationValidator.validateForRemoval(building, null);
        if (!errors.isEmpty()) {
            throw new StudBudUncheckedException()
                    .setMessage1("Cannot remove building")
                    .setMessage2("Validation errors: " + errors.size())
                    .setProbableCause(id)
                    .setErrors(errors)
                    .setStatusCode(BAD_REQUEST)
                    ;
        }
        buildingDao.delete(id);
    }

    @Override
    @Transactional
    public void purgeBuilding(String id) {
        Building building = getBuilding(id);
        if (building == null) {
            return;
        }

        List<BuildingSection> sections = building.getSections();
        List<BuildingFloor> floors = building.getFloors();

        if (!isNullOrEmpty(sections)) {
            sections.forEach(section -> purgeBuildingSection(section.getId()));
            building.getSections().clear();
        }

        if (!isNullOrEmpty(floors)) {
            floors.forEach(floor -> floorService.purgeFloor(floor.getId()));
            building.getFloors().clear();
        }

        buildingDao.delete(building);
    }

    @Override
    public BuildingSection getBuildingSection(String id) {
        return buildingSectionDao.findOne(id);
    }

    @Override
    public BuildingSection updateBuildingSection(BuildingSection buildingSection) {
        return buildingSectionDao.save(buildingSection);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BuildingSection createBuildingSection(BuildingSection buildingSection) {
        List<BuildingFloor> floors = buildingSection.getFloors();
        buildingSection.setFloors(null);

        ErrorsCollection errors = locationValidator.validateForCreation(buildingSection, null);
        if (!errors.isEmpty()) {
            throw new StudBudUncheckedException()
                    .setMessage1("Cannot create new building section")
                    .setMessage2("Validation errors: " + errors.size())
                    .setErrors(errors)
                    .setStatusCode(BAD_REQUEST)
                    ;
        }

        buildingSection = buildingSectionDao.save(buildingSection);

        if (!isNullOrEmpty(floors)) {
            for (BuildingFloor floor : floors) {
                floor.setSection(buildingSection);
                floorService.createFloor(floor);
            }
        }

        buildingSection = buildingSectionDao.findOne(buildingSection.getId());

        return buildingSection;
    }

    @Override
    @Transactional
    public void deleteBuildingSection(String id) {
        BuildingSection section = getBuildingSection(id);
        ErrorsCollection errors = locationValidator.validateForRemoval(section, null);
        if (!errors.isEmpty()) {
            throw new StudBudUncheckedException()
                    .setMessage1("Cannot remove building section")
                    .setMessage2("Validation errors: " + errors.size())
                    .setProbableCause(id)
                    .setErrors(errors)
                    .setStatusCode(BAD_REQUEST)
                    ;
        }
        buildingSectionDao.delete(id);
    }

    @Override
    @Transactional
    public void purgeBuildingSection(String id) {
        BuildingSection section = getBuildingSection(id);
        if (section == null) {
            return;
        }

        List<BuildingFloor> floors = section.getFloors();
        if (!isNullOrEmpty(floors)) {
            floors.forEach(floor -> floorService.purgeFloor(floor.getId()));
            section.setFloors(null);
        }

        if (section.getBuilding() != null && !isNullOrEmpty(section.getBuilding().getSections())) {
            section.getBuilding().getSections().removeIf(s -> s != null && id.equals(s.getId()));
        }

        buildingSectionDao.delete(section);
    }

        @Override
    public Address getAddress(String id) {
        return addressDao.findOne(id);
    }

    @Override
    public Address updateAddress(Address address) {
        return addressDao.save(address);
    }

    @Override
    public Address createAddress(Address address) {
        ErrorsCollection errors = locationValidator.validateForCreation(address, null);
        if (!errors.isEmpty()) {
            throw new StudBudUncheckedException()
                    .setMessage1("Cannot create new address")
                    .setMessage2("Validation errors: " + errors.size())
                    .setErrors(errors)
                    .setStatusCode(BAD_REQUEST)
                    ;
        }
        return addressDao.save(address);
    }

    @Override
    @Transactional
    public void deleteAddress(String id) {
        Address address = getAddress(id);
        ErrorsCollection errors = locationValidator.validateForRemoval(address, null);
        if (!errors.isEmpty()) {
            throw new StudBudUncheckedException()
                    .setMessage1("Cannot remove address")
                    .setMessage2("Validation errors: " + errors.size())
                    .setProbableCause(id)
                    .setErrors(errors)
                    .setStatusCode(BAD_REQUEST)
                    ;
        }
        addressDao.delete(id);
    }

    @Override
    @Transactional
    public void purgeAddress(String id) {
        Address address = getAddress(id);
        if (address == null) {
            return;
        }

        Building building = buildingDao.findByAddress_Id(id);
        BuildingSection section = buildingSectionDao.findByAddress_Id(id);

        if (building != null) {
            purgeBuilding(building.getId());
        }

        if (section != null) {
            purgeBuildingSection(section.getId());
        }

        addressDao.delete(id);
    }

    @Override
    public List<Building> searchAllBuildingsByTitle(String query) {
        return buildingDao.findAllByTitleLikeIgnoreCase(buildingSectionDao.wrapSearchString(query));
    }

    @Override
    public List<BuildingSection> searchAllSectionsByTitle(String query) {
        return buildingSectionDao.findAllByTitleLikeIgnoreCase(buildingSectionDao.wrapSearchString(query));
    }

}
