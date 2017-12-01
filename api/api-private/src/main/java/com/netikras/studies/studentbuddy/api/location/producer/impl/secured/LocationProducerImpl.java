package com.netikras.studies.studentbuddy.api.location.producer.impl.secured;

import com.netikras.studies.studentbuddy.core.data.api.dto.location.AddressDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.location.BuildingDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.location.BuildingSectionDto;
import com.netikras.studies.studentbuddy.core.data.api.model.Address;
import com.netikras.studies.studentbuddy.core.data.api.model.Building;
import com.netikras.studies.studentbuddy.core.data.api.model.BuildingSection;
import com.netikras.studies.studentbuddy.core.data.meta.annotations.Authorizable;
import com.netikras.studies.studentbuddy.core.service.LocationService;
import com.netikras.studies.studentbuddy.core.validator.EntityProvider;
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
    @Resource
    private EntityProvider entityProvider;

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
        BuildingDto dto = modelMapper.transform(building, new BuildingDto(), new MappingSettings().setDepthMax(3));
        return dto;
    }

    @Authorizable(resource = BUILDING, action = CREATE)
    public BuildingDto createBuilding(BuildingDto buildingDto) {
        Building building = modelMapper.apply(new Building(), buildingDto, new MappingSettings().setForceUpdate(true));
        if (building != null) building.setId(null);
        building = locationService.createBuilding(building);
        BuildingDto dto = modelMapper.transform(building, new BuildingDto(), new MappingSettings().setDepthMax(3));
        return dto;
    }

    @Authorizable(resource = BUILDING, action = DELETE)
    public void deleteBuilding(String id) {
        locationService.deleteBuilding(id);
    }

    @Authorizable(resource = BUILDING, action = MODIFY)
    public BuildingDto updateBuilding(BuildingDto buildingDto) {
        Building building = modelMapper.apply(entityProvider.fetch(buildingDto), buildingDto, new MappingSettings().setForceUpdate(true));
        building = locationService.updateBuilding(building);
        BuildingDto dto = modelMapper.transform(building, new BuildingDto(), new MappingSettings().setDepthMax(3));
        return dto;
    }

    @Authorizable(resource = BUILDING_SECTION, action = GET)
    public BuildingSectionDto getBuildingSection(String id) {
        BuildingSection buildingSection = locationService.getBuildingSection(id);
        BuildingSectionDto dto = modelMapper.transform(buildingSection, new BuildingSectionDto(), new MappingSettings().setDepthMax(3));
        return dto;
    }

    @Authorizable(resource = BUILDING_SECTION, action = CREATE)
    public BuildingSectionDto createBuildingSection(BuildingSectionDto buildingSectionDto) {
        BuildingSection buildingSection = modelMapper.apply(new BuildingSection(), buildingSectionDto, new MappingSettings().setForceUpdate(true));
        if (buildingSection != null) buildingSection.setId(null);
        buildingSection = locationService.createBuildingSection(buildingSection);
        BuildingSectionDto dto = modelMapper.transform(buildingSection, new BuildingSectionDto(), new MappingSettings().setDepthMax(3));
        return dto;
    }

    @Authorizable(resource = BUILDING_SECTION, action = DELETE)
    public void deleteBuildingSection(String id) {
        locationService.deleteBuildingSection(id);
    }

    @Authorizable(resource = BUILDING_SECTION, action = MODIFY)
    public BuildingSectionDto updateBuildingSection(BuildingSectionDto buildingSectionDto) {
        BuildingSection buildingSection = modelMapper.apply(entityProvider.fetch(buildingSectionDto), buildingSectionDto);
        buildingSection = locationService.updateBuildingSection(buildingSection);
        BuildingSectionDto dto = modelMapper.transform(buildingSection, new BuildingSectionDto(), new MappingSettings().setDepthMax(3));
        return dto;
    }

    @Authorizable(resource = ADDRESS, action = GET)
    public AddressDto getAddress(String id) {
        Address address = locationService.getAddress(id);
        AddressDto dto = modelMapper.transform(address, new AddressDto(), new MappingSettings().setDepthMax(3));
        return dto;
    }

    @Authorizable(resource = ADDRESS, action = CREATE)
    public AddressDto createAddress(AddressDto addressDto) {
        Address address = modelMapper.apply(new Address(), addressDto, new MappingSettings().setForceUpdate(true));
        if (address != null) address.setId(null);
        address = locationService.createAddress(address);
        AddressDto dto = modelMapper.transform(address, new AddressDto(), new MappingSettings().setDepthMax(3));
        return dto;
    }

    @Authorizable(resource = ADDRESS, action = DELETE)
    public void deleteAddress(String id) {
        locationService.deleteAddress(id);
    }

    @Authorizable(resource = ADDRESS, action = MODIFY)
    public AddressDto updateAddress(AddressDto addressDto) {
        Address address = modelMapper.apply(entityProvider.fetch(addressDto), addressDto);
        address = locationService.updateAddress(address);
        AddressDto dto = modelMapper.transform(address, new AddressDto(), new MappingSettings().setDepthMax(3));
        return dto;
    }

    @Authorizable(resource = BUILDING_SECTION, action = SEARCH)
    public List<BuildingSectionDto> searchAllBuildingSectionsByTitle(String title) {
        List<BuildingSection> sections = locationService.searchAllSectionsByTitle(title);
        List<BuildingSectionDto> dtos = (List<BuildingSectionDto>) modelMapper.transformAll(sections, BuildingSectionDto.class, new MappingSettings().setDepthMax(3));
        return dtos;
    }

    @Authorizable(resource = BUILDING, action = SEARCH)
    public List<BuildingDto> searchAllBuildingsByTitle(String title) {
        List<Building> buildings = locationService.searchAllBuildingsByTitle(title);
        List<BuildingDto> dtos = (List<BuildingDto>) modelMapper.transformAll(buildings, BuildingDto.class, new MappingSettings().setDepthMax(3));
        return dtos;
    }

    @Authorizable(resource = BUILDING, action = GET_ALL)
    public List<BuildingDto> getAllBuildings() {
        List<Building> rooms = locationService.getAllBuildings();
        List<BuildingDto> dtos = (List<BuildingDto>) modelMapper.transformAll(rooms, BuildingDto.class, new MappingSettings().setDepthMax(3));
        return dtos;
    }

    @Authorizable(resource = ADDRESS, action = GET_ALL)
    public List<AddressDto> getAllAddresses() {
        List<Address> addresses = locationService.getAllAddresses();
        List<AddressDto> dtos = (List<AddressDto>) modelMapper.transformAll(addresses, AddressDto.class, new MappingSettings().setDepthMax(3));
        return dtos;
    }

    @Authorizable(resource = BUILDING_SECTION, action = GET)
    public List<BuildingSectionDto> getSectionsByBuildingId(String id) {
        List<BuildingSection> sections = locationService.getAllSectionsByBuildingId(id);
        List<BuildingSectionDto> dtos = (List<BuildingSectionDto>) modelMapper.transformAll(sections, BuildingSectionDto.class, new MappingSettings().setDepthMax(3));
        return dtos;
    }



    @Authorizable(resource = BUILDING_SECTION, action = GET)
    public List<BuildingSectionDto> getSectionsByAddressId(String id) {
        List<BuildingSection> sections = locationService.getAllSectionsByAddressId(id);
        List<BuildingSectionDto> dtos = (List<BuildingSectionDto>) modelMapper.transformAll(sections, BuildingSectionDto.class, new MappingSettings().setDepthMax(3));
        return dtos;
    }

    @Authorizable(resource = BUILDING, action = GET)
    public BuildingDto getBuildingByAddressId(String id) {
        Building building = locationService.getBuildingByAddressId(id);
        BuildingDto dto = modelMapper.transform(building, new BuildingDto(), new MappingSettings().setDepthMax(3));
        return dto;
    }
}
