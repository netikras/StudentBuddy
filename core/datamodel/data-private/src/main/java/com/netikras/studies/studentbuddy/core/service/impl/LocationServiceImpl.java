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
        return null;
    }

    @Override
    public Building updateBuilding(Building building) {
        return null;
    }

    @Override
    public Building createBuilding(Building building) {
        return null;
    }

    @Override
    public void deleteBuilding(String id) {

    }

    @Override
    public BuildingSection getBuildingSection(String id) {
        return null;
    }

    @Override
    public BuildingSection updateBuildingSection(BuildingSection buildingSection) {
        return null;
    }

    @Override
    public BuildingSection createBuildingSection(BuildingSection buildingSection) {
        return null;
    }

    @Override
    public void deleteBuildingSection(String id) {

    }

    @Override
    public Address getAddress(String id) {
        return null;
    }

    @Override
    public Address updateAddress(Address address) {
        return null;
    }

    @Override
    public Address createAddress(Address address) {
        return null;
    }

    @Override
    public void deleteAddress(String id) {

    }
}
