package com.netikras.studies.studentbuddy.core.service;

import com.netikras.studies.studentbuddy.core.data.api.model.Address;
import com.netikras.studies.studentbuddy.core.data.api.model.Building;
import com.netikras.studies.studentbuddy.core.data.api.model.BuildingSection;

import java.util.List;

public interface LocationService {


    Building getBuilding(String id);

    Building updateBuilding(Building building);

    Building createBuilding(Building building);

    void deleteBuilding(String id);

    BuildingSection getBuildingSection(String id);

    BuildingSection updateBuildingSection(BuildingSection buildingSection);

    BuildingSection createBuildingSection(BuildingSection buildingSection);

    void deleteBuildingSection(String id);

    Address getAddress(String id);

    Address updateAddress(Address address);

    Address createAddress(Address address);

    void deleteAddress(String id);

    List<Building> searchAllBuildingsByTitle(String query);

    List<BuildingSection> searchAllSectionsByTitle(String query);
}
