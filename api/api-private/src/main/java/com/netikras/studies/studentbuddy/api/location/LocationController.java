package com.netikras.studies.studentbuddy.api.location;

import com.netikras.studies.studentbuddy.core.data.api.dto.location.AddressDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.location.BuildingDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.location.BuildingSectionDto;
import com.netikras.studies.studentbuddy.core.data.api.model.Address;
import com.netikras.studies.studentbuddy.core.data.api.model.Building;
import com.netikras.studies.studentbuddy.core.data.api.model.BuildingSection;
import com.netikras.studies.studentbuddy.core.meta.Action;
import com.netikras.studies.studentbuddy.core.meta.annotations.Authorizable;
import com.netikras.studies.studentbuddy.core.service.LocationService;
import com.netikras.tools.common.model.mapper.ModelMapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import java.util.List;

import static com.netikras.studies.studentbuddy.api.constants.LocationConstants.BASE_URL;
import static com.netikras.studies.studentbuddy.api.constants.LocationConstants.LOC_URL_CREATE_ADDRESS;
import static com.netikras.studies.studentbuddy.api.constants.LocationConstants.LOC_URL_CREATE_BUILDING;
import static com.netikras.studies.studentbuddy.api.constants.LocationConstants.LOC_URL_CREATE_BUILDING_SECTION;
import static com.netikras.studies.studentbuddy.api.constants.LocationConstants.LOC_URL_DELETE_ADDRESS_BY_ID;
import static com.netikras.studies.studentbuddy.api.constants.LocationConstants.LOC_URL_DELETE_BUILDING_BY_ID;
import static com.netikras.studies.studentbuddy.api.constants.LocationConstants.LOC_URL_DELETE_BUILDING_SECTION_BY_ID;
import static com.netikras.studies.studentbuddy.api.constants.LocationConstants.LOC_URL_GET_ADDRESS_BY_ID;
import static com.netikras.studies.studentbuddy.api.constants.LocationConstants.LOC_URL_GET_BUILDING_BY_ID;
import static com.netikras.studies.studentbuddy.api.constants.LocationConstants.LOC_URL_GET_BUILDING_SECTION_BY_ID;
import static com.netikras.studies.studentbuddy.api.constants.LocationConstants.LOC_URL_SEARCH_ALL_BUILDINGS_BY_TITLE;
import static com.netikras.studies.studentbuddy.api.constants.LocationConstants.LOC_URL_SEARCH_ALL_BUILDING_SECTIONS_BY_TITLE;
import static com.netikras.studies.studentbuddy.api.constants.LocationConstants.LOC_URL_UPDATE_ADDRESS;
import static com.netikras.studies.studentbuddy.api.constants.LocationConstants.LOC_URL_UPDATE_BUILDING;
import static com.netikras.studies.studentbuddy.api.constants.LocationConstants.LOC_URL_UPDATE_BUILDING_SECTION;
import static com.netikras.studies.studentbuddy.core.meta.Resource.ADDRESS;
import static com.netikras.studies.studentbuddy.core.meta.Resource.BUILDING;
import static com.netikras.studies.studentbuddy.core.meta.Resource.BUILDING_SECTION;

@RestController
@RequestMapping(value = BASE_URL)
public class LocationController {

    @Resource
    private LocationService locationService;


    // building

    @RequestMapping(
            value = LOC_URL_GET_BUILDING_BY_ID,
            method = RequestMethod.GET
    )
    @ResponseBody
    @Authorizable(resource = BUILDING, action = Action.GET)
    public BuildingDto getBuilding(@PathVariable(name = "id") String id) {
        Building building = locationService.getBuilding(id);
        BuildingDto dto = ModelMapper.transform(building, new BuildingDto());
        return dto;
    }

    @RequestMapping(
            value = LOC_URL_UPDATE_BUILDING,
            method = RequestMethod.PUT
    )
    @ResponseBody
    @Authorizable(resource = BUILDING, action = Action.MODIFY)
    public BuildingDto updateBuilding(
            @RequestBody BuildingDto buildingDto
    ) {
        Building building = ModelMapper.apply(new Building(), buildingDto);
        building = locationService.updateBuilding(building);
        BuildingDto dto = ModelMapper.transform(building, new BuildingDto());
        return dto;
    }

    @RequestMapping(
            value = LOC_URL_CREATE_BUILDING,
            method = RequestMethod.POST
    )
    @ResponseBody
    @Authorizable(resource = BUILDING, action = Action.CREATE)
    public BuildingDto createBuilding(
            @RequestBody BuildingDto buildingDto
    ) {
        Building building = ModelMapper.apply(new Building(), buildingDto);
        building = locationService.createBuilding(building);
        BuildingDto dto = ModelMapper.transform(building, new BuildingDto());
        return dto;
    }

    @RequestMapping(
            value = LOC_URL_DELETE_BUILDING_BY_ID,
            method = RequestMethod.DELETE
    )
    @Authorizable(resource = BUILDING, action = Action.DELETE)
    public void deleteBuilding(@PathVariable(name = "id") String id) {
        locationService.deleteBuilding(id);
    }

    @RequestMapping(
            value = LOC_URL_SEARCH_ALL_BUILDINGS_BY_TITLE,
            method = RequestMethod.GET
    )
    @ResponseBody
    @Authorizable(resource = BUILDING, action = Action.SEARCH)
    public List<BuildingDto> searchBuildingByTitle(
            @PathVariable(name = "title") String titleSubstring
    ) {
        List<Building> rooms = locationService.searchAllBuildingsByTitle(titleSubstring);
        List<BuildingDto> dtos = (List<BuildingDto>) ModelMapper.transformAll(rooms, BuildingDto.class);
        return dtos;
    }



    // building section

    @RequestMapping(
            value = LOC_URL_GET_BUILDING_SECTION_BY_ID,
            method = RequestMethod.GET
    )
    @ResponseBody
    @Authorizable(resource = BUILDING_SECTION, action = Action.GET)
    public BuildingSectionDto getBuildingSection(@PathVariable(name = "id") String id) {
        BuildingSection buildingSection = locationService.getBuildingSection(id);
        BuildingSectionDto dto = ModelMapper.transform(buildingSection, new BuildingSectionDto());
        return dto;
    }

    @RequestMapping(
            value = LOC_URL_UPDATE_BUILDING_SECTION,
            method = RequestMethod.PUT
    )
    @ResponseBody
    @Authorizable(resource = BUILDING_SECTION, action = Action.MODIFY)
    public BuildingSectionDto updateBuildingSection(
            @RequestBody BuildingSectionDto buildingSectionDto
    ) {
        BuildingSection buildingSection = ModelMapper.apply(new BuildingSection(), buildingSectionDto);
        buildingSection = locationService.updateBuildingSection(buildingSection);
        BuildingSectionDto dto = ModelMapper.transform(buildingSection, new BuildingSectionDto());
        return dto;
    }

    @RequestMapping(
            value = LOC_URL_CREATE_BUILDING_SECTION,
            method = RequestMethod.POST
    )
    @ResponseBody
    @Authorizable(resource = BUILDING_SECTION, action = Action.CREATE)
    public BuildingSectionDto createBuildingSection(
            @RequestBody BuildingSectionDto buildingSectionDto
    ) {
        BuildingSection buildingSection = ModelMapper.apply(new BuildingSection(), buildingSectionDto);
        buildingSection = locationService.createBuildingSection(buildingSection);
        BuildingSectionDto dto = ModelMapper.transform(buildingSection, new BuildingSectionDto());
        return dto;
    }

    @RequestMapping(
            value = LOC_URL_DELETE_BUILDING_SECTION_BY_ID,
            method = RequestMethod.DELETE
    )
    @Authorizable(resource = BUILDING_SECTION, action = Action.DELETE)
    public void deleteBuildingSection(@PathVariable(name = "id") String id) {
        locationService.deleteBuildingSection(id);
    }

    @RequestMapping(
            value = LOC_URL_SEARCH_ALL_BUILDING_SECTIONS_BY_TITLE,
            method = RequestMethod.GET
    )
    @ResponseBody
    @Authorizable(resource = BUILDING_SECTION, action = Action.SEARCH)
    public List<BuildingSectionDto> searchBuildingSectionByTitle(
            @PathVariable(name = "title") String titleSubstring
    ) {
        List<BuildingSection> rooms = locationService.searchAllSectionsByTitle(titleSubstring);
        List<BuildingSectionDto> dtos = (List<BuildingSectionDto>) ModelMapper.transformAll(rooms, BuildingSectionDto.class);
        return dtos;
    }


    // address

    @RequestMapping(
            value = LOC_URL_GET_ADDRESS_BY_ID,
            method = RequestMethod.GET
    )
    @ResponseBody
    @Authorizable(resource = ADDRESS, action = Action.GET)
    public AddressDto getAddress(@PathVariable(name = "id") String id) {
        Address address = locationService.getAddress(id);
        AddressDto dto = ModelMapper.transform(address, new AddressDto());
        return dto;
    }

    @RequestMapping(
            value = LOC_URL_UPDATE_ADDRESS,
            method = RequestMethod.PUT
    )
    @ResponseBody
    @Authorizable(resource = ADDRESS, action = Action.MODIFY)
    public AddressDto updateAddress(
            @RequestBody AddressDto addressDto
    ) {
        Address address = ModelMapper.apply(new Address(), addressDto);
        address = locationService.updateAddress(address);
        AddressDto dto = ModelMapper.transform(address, new AddressDto());
        return dto;
    }

    @RequestMapping(
            value = LOC_URL_CREATE_ADDRESS,
            method = RequestMethod.POST
    )
    @ResponseBody
    @Authorizable(resource = ADDRESS, action = Action.CREATE)
    public AddressDto createAddress(
            @RequestBody AddressDto addressDto
    ) {
        Address address = ModelMapper.apply(new Address(), addressDto);
        address = locationService.createAddress(address);
        AddressDto dto = ModelMapper.transform(address, new AddressDto());
        return dto;
    }

    @RequestMapping(
            value = LOC_URL_DELETE_ADDRESS_BY_ID,
            method = RequestMethod.DELETE
    )
    @Authorizable(resource = ADDRESS, action = Action.DELETE)
    public void deleteAddress(@PathVariable(name = "id") String id) {
        locationService.deleteAddress(id);
    }

}
