package com.netikras.studies.studentbuddy.api.location.producer.impl.secured;

import com.netikras.studies.studentbuddy.core.data.api.dto.location.AddressDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.location.BuildingDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.location.BuildingSectionDto;
import com.netikras.studies.studentbuddy.core.data.api.model.Address;
import com.netikras.studies.studentbuddy.core.data.api.model.Building;
import com.netikras.studies.studentbuddy.core.data.api.model.BuildingSection;
import com.netikras.studies.studentbuddy.core.data.meta.annotations.Authorizable;
import com.netikras.studies.studentbuddy.core.service.LocationService;
import com.netikras.tools.common.model.mapper.MappingSettings;
import com.netikras.tools.common.model.mapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

import static com.netikras.studies.studentbuddy.core.data.meta.Action.*;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.*;

@Component
public class LocationProducerImpl {

    @Resource
    private ModelMapper modelMapper;
    
    @Resource
    private LocationService locationService;

    @Authorizable(resource = BUILDING, action = PURGE)
    public void purgeBuilding(String id) {
        locationService.purgeBuilding(id);
    }

    @Authorizable(resource = BUILDING_SECTION, action = PURGE)
    public void purgeBuildingSection(String id) {
        locationService.purgeBuildingSection(id);
    }

    @Authorizable(resource = ADDRESS, action = PURGE)
    public void purgeAddress(String id) {
        locationService.purgeAddress(id);
    }


    @Authorizable(resource = BUILDING, action = GET)
    public BuildingDto getBuilding(String id) {
        Building building = locationService.getBuilding(id);
        if (building != null) building.getSections();
        BuildingDto dto = modelMapper.transform(building, new BuildingDto());
        return dto;
    }

    @Authorizable(resource = BUILDING, action = CREATE)
    public BuildingDto createBuilding(BuildingDto buildingDto) {
        Building building = modelMapper.apply(new Building(), buildingDto, new MappingSettings().setForceUpdate(true));
        if (building != null) building.setId(null);
        building = locationService.createBuilding(building);
        BuildingDto dto = modelMapper.transform(building, new BuildingDto());
        return dto;
    }

    @Authorizable(resource = BUILDING, action = DELETE)
    public void deleteBuilding(String id) {
        locationService.deleteBuilding(id);
    }

    @Authorizable(resource = BUILDING, action = MODIFY)
    public BuildingDto updateBuilding(BuildingDto buildingDto) {
        Building building = modelMapper.apply(new Building(), buildingDto);
        building = locationService.updateBuilding(building);
        BuildingDto dto = modelMapper.transform(building, new BuildingDto());
        return dto;
    }

    @Authorizable(resource = BUILDING_SECTION, action = GET)
    public BuildingSectionDto getBuildingSection(String id) {
        BuildingSection buildingSection = locationService.getBuildingSection(id);
        BuildingSectionDto dto = modelMapper.transform(buildingSection, new BuildingSectionDto());
        return dto;
    }

    @Authorizable(resource = BUILDING_SECTION, action = CREATE)
    public BuildingSectionDto createBuildingSection(BuildingSectionDto buildingSectionDto) {
        BuildingSection buildingSection = modelMapper.apply(new BuildingSection(), buildingSectionDto, new MappingSettings().setForceUpdate(true));
        if (buildingSection != null) buildingSection.setId(null);
        buildingSection = locationService.createBuildingSection(buildingSection);
        BuildingSectionDto dto = modelMapper.transform(buildingSection, new BuildingSectionDto());
        return dto;
    }

    @Authorizable(resource = BUILDING_SECTION, action = DELETE)
    public void deleteBuildingSection(String id) {
        locationService.deleteBuildingSection(id);
    }

    @Authorizable(resource = BUILDING_SECTION, action = MODIFY)
    public BuildingSectionDto updateBuildingSection(BuildingSectionDto buildingSectionDto) {
        BuildingSection buildingSection = modelMapper.apply(new BuildingSection(), buildingSectionDto);
        buildingSection = locationService.updateBuildingSection(buildingSection);
        BuildingSectionDto dto = modelMapper.transform(buildingSection, new BuildingSectionDto());
        return dto;
    }

    @Authorizable(resource = ADDRESS, action = GET)
    public AddressDto getAddress(String id) {
        Address address = locationService.getAddress(id);
        AddressDto dto = modelMapper.transform(address, new AddressDto());
        return dto;
    }

    @Authorizable(resource = ADDRESS, action = CREATE)
    public AddressDto createAddress(AddressDto addressDto) {
        Address address = modelMapper.apply(new Address(), addressDto, new MappingSettings().setForceUpdate(true));
        if (address != null) address.setId(null);
        address = locationService.createAddress(address);
        AddressDto dto = modelMapper.transform(address, new AddressDto());
        return dto;
    }

    @Authorizable(resource = ADDRESS, action = DELETE)
    public void deleteAddress(String id) {
        locationService.deleteAddress(id);
    }

    @Authorizable(resource = ADDRESS, action = MODIFY)
    public AddressDto updateAddress(AddressDto addressDto) {
        Address address = modelMapper.apply(new Address(), addressDto);
        address = locationService.updateAddress(address);
        AddressDto dto = modelMapper.transform(address, new AddressDto());
        return dto;
    }

    @Authorizable(resource = BUILDING_SECTION, action = SEARCH)
    public List<BuildingSectionDto> searchAllBuildingSectionsByTitle(String title) {
        List<BuildingSection> rooms = locationService.searchAllSectionsByTitle(title);
        List<BuildingSectionDto> dtos = (List<BuildingSectionDto>) modelMapper.transformAll(rooms, BuildingSectionDto.class);
        return dtos;
    }

    @Authorizable(resource = BUILDING, action = SEARCH)
    public List<BuildingDto> searchAllBuildingsByTitle(String title) {
        List<Building> rooms = locationService.searchAllBuildingsByTitle(title);
        List<BuildingDto> dtos = (List<BuildingDto>) modelMapper.transformAll(rooms, BuildingDto.class);
        return dtos;
    }
}
