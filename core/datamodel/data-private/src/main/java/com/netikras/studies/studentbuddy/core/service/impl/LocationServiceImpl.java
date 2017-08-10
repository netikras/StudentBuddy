package com.netikras.studies.studentbuddy.core.service.impl;

import com.netikras.studies.studentbuddy.core.data.api.dao.AddressDao;
import com.netikras.studies.studentbuddy.core.data.api.dao.BuildingDao;
import com.netikras.studies.studentbuddy.core.data.api.dao.BuildingSectionDao;
import com.netikras.studies.studentbuddy.core.data.api.model.Address;
import com.netikras.studies.studentbuddy.core.data.api.model.Building;
import com.netikras.studies.studentbuddy.core.data.api.model.BuildingSection;
import com.netikras.studies.studentbuddy.core.service.LocationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LocationServiceImpl implements LocationService {


    @Resource
    private BuildingDao buildingDao;

    @Resource
    private AddressDao addressDao;

    @Resource
    private BuildingSectionDao buildingSectionDao;



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
        return addressDao.save(address);
    }

    @Override
    public void deleteAddress(String id) {
        addressDao.delete(id);
    }
}
