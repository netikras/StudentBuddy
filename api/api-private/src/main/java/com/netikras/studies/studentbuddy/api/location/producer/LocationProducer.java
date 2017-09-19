package com.netikras.studies.studentbuddy.api.location.producer;

import com.netikras.studies.studentbuddy.api.location.generated.LocationApiProducer;
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
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

import static com.netikras.studies.studentbuddy.core.data.meta.Action.CREATE;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.DELETE;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.GET;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.MODIFY;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.PURGE;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.SEARCH;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.ADDRESS;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.BUILDING;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.BUILDING_SECTION;

@RestController
public class LocationProducer extends LocationApiProducer {

    @Resource
    private LocationService locationService;

    @Override
    @Authorizable(resource = BUILDING, action = PURGE)
    protected void onPurgeBuildingDto(String id) {
        locationService.purgeBuilding(id);
    }

    @Override
    @Authorizable(resource = BUILDING_SECTION, action = PURGE)
    protected void onPurgeBuildingSectionDto(String id) {
        locationService.purgeBuildingSection(id);
    }

    @Override
    @Authorizable(resource = ADDRESS, action = PURGE)
    protected void onPurgeAddressDto(String id) {
        locationService.purgeAddress(id);
    }



    @Override
    @Authorizable(resource = BUILDING, action = GET)
    protected BuildingDto onRetrieveBuildingDto(String id) {
        Building building = locationService.getBuilding(id);
        if (building != null) building.getSections();
        BuildingDto dto = ModelMapper.transform(building, new BuildingDto());
        return dto;
    }

    @Override
    @Authorizable(resource = BUILDING, action = CREATE)
    protected BuildingDto onCreateBuildingDto(BuildingDto buildingDto) {
        Building building = ModelMapper.apply(new Building(), buildingDto, new MappingSettings().setForceUpdate(true));
        if (building != null) building.setId(null);
        building = locationService.createBuilding(building);
        BuildingDto dto = ModelMapper.transform(building, new BuildingDto());
        return dto;
    }

    @Override
    @Authorizable(resource = BUILDING, action = DELETE)
    protected void onDeleteBuildingDto(String id) {
        locationService.deleteBuilding(id);
    }

    @Override
    @Authorizable(resource = BUILDING, action = MODIFY)
    protected BuildingDto onUpdateBuildingDto(BuildingDto buildingDto) {
        Building building = ModelMapper.apply(new Building(), buildingDto);
        building = locationService.updateBuilding(building);
        BuildingDto dto = ModelMapper.transform(building, new BuildingDto());
        return dto;
    }

    @Override
    @Authorizable(resource = BUILDING_SECTION, action = GET)
    protected BuildingSectionDto onRetrieveBuildingSectionDto(String id) {
        BuildingSection buildingSection = locationService.getBuildingSection(id);
        BuildingSectionDto dto = ModelMapper.transform(buildingSection, new BuildingSectionDto());
        return dto;
    }

    @Override
    @Authorizable(resource = BUILDING_SECTION, action = CREATE)
    protected BuildingSectionDto onCreateBuildingSectionDto(BuildingSectionDto buildingSectionDto) {
        BuildingSection buildingSection = ModelMapper.apply(new BuildingSection(), buildingSectionDto, new MappingSettings().setForceUpdate(true));
        if (buildingSection != null) buildingSection.setId(null);
        buildingSection = locationService.createBuildingSection(buildingSection);
        BuildingSectionDto dto = ModelMapper.transform(buildingSection, new BuildingSectionDto());
        return dto;
    }

    @Override
    @Authorizable(resource = BUILDING_SECTION, action = DELETE)
    protected void onDeleteBuildingSectionDto(String id) {
        locationService.deleteBuildingSection(id);
    }

    @Override
    @Authorizable(resource = BUILDING_SECTION, action = MODIFY)
    protected BuildingSectionDto onUpdateBuildingSectionDto(BuildingSectionDto buildingSectionDto) {
        BuildingSection buildingSection = ModelMapper.apply(new BuildingSection(), buildingSectionDto);
        buildingSection = locationService.updateBuildingSection(buildingSection);
        BuildingSectionDto dto = ModelMapper.transform(buildingSection, new BuildingSectionDto());
        return dto;
    }

    @Override
    @Authorizable(resource = ADDRESS, action = GET)
    protected AddressDto onRetrieveAddressDto(String id) {
        Address address = locationService.getAddress(id);
        AddressDto dto = ModelMapper.transform(address, new AddressDto());
        return dto;
    }

    @Override
    @Authorizable(resource = ADDRESS, action = CREATE)
    protected AddressDto onCreateAddressDto(AddressDto addressDto) {
        Address address = ModelMapper.apply(new Address(), addressDto, new MappingSettings().setForceUpdate(true));
        if (address != null) address.setId(null);
        address = locationService.createAddress(address);
        AddressDto dto = ModelMapper.transform(address, new AddressDto());
        return dto;
    }

    @Override
    @Authorizable(resource = ADDRESS, action = DELETE)
    protected void onDeleteAddressDto(String id) {
        locationService.deleteAddress(id);
    }

    @Override
    @Authorizable(resource = ADDRESS, action = MODIFY)
    protected AddressDto onUpdateAddressDto(AddressDto addressDto) {
        Address address = ModelMapper.apply(new Address(), addressDto);
        address = locationService.updateAddress(address);
        AddressDto dto = ModelMapper.transform(address, new AddressDto());
        return dto;
    }

    @Override
    @Authorizable(resource = BUILDING_SECTION, action = SEARCH)
    protected List<BuildingSectionDto> onSearchBuildingSectionDtoAllByTitle(String title) {
        List<BuildingSection> rooms = locationService.searchAllSectionsByTitle(title);
        List<BuildingSectionDto> dtos = (List<BuildingSectionDto>) ModelMapper.transformAll(rooms, BuildingSectionDto.class);
        return dtos;
    }

    @Override
    @Authorizable(resource = BUILDING, action = SEARCH)
    protected List<BuildingDto> onSearchBuildingDtoAllByTitle(String title) {
        List<Building> rooms = locationService.searchAllBuildingsByTitle(title);
        List<BuildingDto> dtos = (List<BuildingDto>) ModelMapper.transformAll(rooms, BuildingDto.class);
        return dtos;
    }
}
