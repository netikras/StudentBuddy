package com.netikras.studies.studentbuddy.api.location.producer.impl.secured;

import com.netikras.studies.studentbuddy.api.handlers.DtoMapper;
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
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

import static com.netikras.studies.studentbuddy.core.data.meta.Action.CREATE;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.DELETE;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.GET;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.GET_ALL;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.MODIFY;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.PURGE;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.SEARCH;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.ADDRESS;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.BUILDING;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.BUILDING_SECTION;

@Component
public class LocationProducerImpl {

    @Resource
    private ModelMapper modelMapper;
    @Resource
    private DtoMapper dtoMapper;

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
    @Transactional
    public BuildingDto getBuilding(String id) {
        Building building = locationService.getBuilding(id);
        if (building != null) building.getSections();
        BuildingDto dto = (BuildingDto) dtoMapper.toDto(building);
        return dto;
    }

    @Authorizable(resource = BUILDING, action = CREATE)
    @Transactional
    public BuildingDto createBuilding(BuildingDto buildingDto) {
        Building building = modelMapper.apply(new Building(), buildingDto, new MappingSettings().setForceUpdate(true));
        if (building != null) building.setId(null);
        building = locationService.createBuilding(building);
        BuildingDto dto = (BuildingDto) dtoMapper.toDto(building);
        return dto;
    }

    @Authorizable(resource = BUILDING, action = DELETE)
    public void deleteBuilding(String id) {
        locationService.deleteBuilding(id);
    }

    @Authorizable(resource = BUILDING, action = MODIFY)
    @Transactional
    public BuildingDto updateBuilding(BuildingDto buildingDto) {
        Building building = modelMapper.apply(entityProvider.fetch(buildingDto), buildingDto, new MappingSettings().setForceUpdate(true));
        building = locationService.updateBuilding(building);
        BuildingDto dto = (BuildingDto) dtoMapper.toDto(building);
        return dto;
    }

    @Authorizable(resource = BUILDING_SECTION, action = GET)
    @Transactional
    public BuildingSectionDto getBuildingSection(String id) {
        BuildingSection buildingSection = locationService.getBuildingSection(id);
        BuildingSectionDto dto = (BuildingSectionDto) dtoMapper.toDto(buildingSection);
        return dto;
    }

    @Authorizable(resource = BUILDING_SECTION, action = CREATE)
    @Transactional
    public BuildingSectionDto createBuildingSection(BuildingSectionDto buildingSectionDto) {
        BuildingSection buildingSection = modelMapper.apply(new BuildingSection(), buildingSectionDto, new MappingSettings().setForceUpdate(true));
        if (buildingSection != null) buildingSection.setId(null);
        buildingSection = locationService.createBuildingSection(buildingSection);
        BuildingSectionDto dto = (BuildingSectionDto) dtoMapper.toDto(buildingSection);
        return dto;
    }

    @Authorizable(resource = BUILDING_SECTION, action = DELETE)
    public void deleteBuildingSection(String id) {
        locationService.deleteBuildingSection(id);
    }

    @Authorizable(resource = BUILDING_SECTION, action = MODIFY)
    @Transactional
    public BuildingSectionDto updateBuildingSection(BuildingSectionDto buildingSectionDto) {
        BuildingSection buildingSection = modelMapper.apply(entityProvider.fetch(buildingSectionDto), buildingSectionDto);
        buildingSection = locationService.updateBuildingSection(buildingSection);
        BuildingSectionDto dto = (BuildingSectionDto) dtoMapper.toDto(buildingSection);
        return dto;
    }

    @Authorizable(resource = ADDRESS, action = GET)
    @Transactional
    public AddressDto getAddress(String id) {
        Address address = locationService.getAddress(id);
        AddressDto dto = (AddressDto) dtoMapper.toDto(address);
        return dto;
    }

    @Authorizable(resource = ADDRESS, action = CREATE)
    @Transactional
    public AddressDto createAddress(AddressDto addressDto) {
        Address address = modelMapper.apply(new Address(), addressDto, new MappingSettings().setForceUpdate(true));
        if (address != null) address.setId(null);
        address = locationService.createAddress(address);
        AddressDto dto = (AddressDto) dtoMapper.toDto(address);
        return dto;
    }

    @Authorizable(resource = ADDRESS, action = DELETE)
    public void deleteAddress(String id) {
        locationService.deleteAddress(id);
    }

    @Authorizable(resource = ADDRESS, action = MODIFY)
    @Transactional
    public AddressDto updateAddress(AddressDto addressDto) {
        Address address = modelMapper.apply(entityProvider.fetch(addressDto), addressDto);
        address = locationService.updateAddress(address);
        AddressDto dto = (AddressDto) dtoMapper.toDto(address);
        return dto;
    }

    @Authorizable(resource = BUILDING_SECTION, action = SEARCH)
    @Transactional
    public List<BuildingSectionDto> searchAllBuildingSectionsByTitle(String title) {
        List<BuildingSection> sections = locationService.searchAllSectionsByTitle(title);
        List<BuildingSectionDto> dtos = (List<BuildingSectionDto>) dtoMapper.toDtos(sections);
        return dtos;
    }

    @Authorizable(resource = BUILDING, action = SEARCH)
    @Transactional
    public List<BuildingDto> searchAllBuildingsByTitle(String title) {
        List<Building> buildings = locationService.searchAllBuildingsByTitle(title);
        List<BuildingDto> dtos = (List<BuildingDto>) dtoMapper.toDtos(buildings);
        return dtos;
    }

    @Authorizable(resource = BUILDING, action = GET_ALL)
    @Transactional
    public List<BuildingDto> getAllBuildings() {
        List<Building> rooms = locationService.getAllBuildings();
        List<BuildingDto> dtos = (List<BuildingDto>) dtoMapper.toDtos(rooms);
        return dtos;
    }

    @Authorizable(resource = ADDRESS, action = GET_ALL)
    @Transactional
    public List<AddressDto> getAllAddresses() {
        List<Address> addresses = locationService.getAllAddresses();
        List<AddressDto> dtos = (List<AddressDto>) dtoMapper.toDtos(addresses);
        return dtos;
    }

    @Authorizable(resource = BUILDING_SECTION, action = GET)
    @Transactional
    public List<BuildingSectionDto> getSectionsByBuildingId(String id) {
        List<BuildingSection> sections = locationService.getAllSectionsByBuildingId(id);
        List<BuildingSectionDto> dtos = (List<BuildingSectionDto>) dtoMapper.toDtos(sections);
        return dtos;
    }


    @Authorizable(resource = BUILDING_SECTION, action = GET)
    @Transactional
    public List<BuildingSectionDto> getSectionsByAddressId(String id) {
        List<BuildingSection> sections = locationService.getAllSectionsByAddressId(id);
        List<BuildingSectionDto> dtos = (List<BuildingSectionDto>) dtoMapper.toDtos(sections);
        return dtos;
    }

    @Authorizable(resource = BUILDING, action = GET)
    @Transactional
    public BuildingDto getBuildingByAddressId(String id) {
        Building building = locationService.getBuildingByAddressId(id);
        BuildingDto dto = (BuildingDto) dtoMapper.toDto(building);
        return dto;
    }
}
