package com.netikras.studies.studentbuddy.core.service.impl;

import com.netikras.studies.studentbuddy.core.data.api.dao.AddressDao;
import com.netikras.studies.studentbuddy.core.data.api.dao.BuildingDao;
import com.netikras.studies.studentbuddy.core.data.api.dao.BuildingSectionDao;
import com.netikras.studies.studentbuddy.core.data.api.model.Address;
import com.netikras.studies.studentbuddy.core.data.api.model.Building;
import com.netikras.studies.studentbuddy.core.data.api.model.BuildingSection;
import com.netikras.studies.studentbuddy.core.service.LocationService;
import com.netikras.studies.studentbuddy.core.validator.LocationValidator;
import com.netikras.tools.common.exception.ErrorsCollection;
import com.netikras.tools.common.exception.FriendlyUncheckedException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static com.netikras.tools.common.remote.http.HttpStatus.BAD_REQUEST;

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



    @Override
    public Building getBuilding(String id) {
        return buildingDao.findOne(id);
    }

    @Override
    public Building updateBuilding(Building building) {
        return buildingDao.save(building);
    }

    @Override
    public Building createBuilding(Building building) {
        ErrorsCollection errors = locationValidator.validateForCreation(building, null);
        if (!errors.isEmpty()) {
            throw new FriendlyUncheckedException()
                    .setMessage1("Cannot create new building")
                    .setMessage2("Validation errors: " + errors.size())
                    .setErrors(errors)
                    .setStatusCode(BAD_REQUEST)
                    ;
        }
        return buildingDao.save(building);
    }

    @Override
    public void deleteBuilding(String id) {
        buildingDao.delete(id);
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
    public BuildingSection createBuildingSection(BuildingSection buildingSection) {
        ErrorsCollection errors = locationValidator.validateForCreation(buildingSection, null);
        if (!errors.isEmpty()) {
            throw new FriendlyUncheckedException()
                    .setMessage1("Cannot create new building section")
                    .setMessage2("Validation errors: " + errors.size())
                    .setErrors(errors)
                    .setStatusCode(BAD_REQUEST)
                    ;
        }
        return buildingSectionDao.save(buildingSection);
    }

    @Override
    public void deleteBuildingSection(String id) {
        buildingSectionDao.delete(id);
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
            throw new FriendlyUncheckedException()
                    .setMessage1("Cannot create new address")
                    .setMessage2("Validation errors: " + errors.size())
                    .setErrors(errors)
                    .setStatusCode(BAD_REQUEST)
                    ;
        }
        return addressDao.save(address);
    }

    @Override
    public void deleteAddress(String id) {
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
