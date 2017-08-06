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

import static com.netikras.studies.studentbuddy.core.meta.Resource.ADDRESS;
import static com.netikras.studies.studentbuddy.core.meta.Resource.BUILDING;
import static com.netikras.studies.studentbuddy.core.meta.Resource.BUILDING_SECTION;

@RestController
@RequestMapping(value = "/location")
public class LocationController {

    @Resource
    private LocationService locationService;

    @RequestMapping(
            value = "/building/id/{id}",
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
            value = "/building",
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
            value = "/building",
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
            value = "/building/id/{id}",
            method = RequestMethod.DELETE
    )
    @Authorizable(resource = BUILDING, action = Action.DELETE)
    public void deleteBuilding(@PathVariable(name = "id") String id) {
        locationService.deleteBuilding(id);
    }


    @RequestMapping(
            value = "/building/section/id/{id}",
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
            value = "/building/section",
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
            value = "/building/section",
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
            value = "/building/section/id/{id}",
            method = RequestMethod.DELETE
    )
    @Authorizable(resource = BUILDING_SECTION, action = Action.DELETE)
    public void deleteBuildingSection(@PathVariable(name = "id") String id) {
        locationService.deleteBuildingSection(id);
    }


    @RequestMapping(
            value = "/address/id/{id}",
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
            value = "/address",
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
            value = "/address",
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
            value = "/address/id/{id}",
            method = RequestMethod.DELETE
    )
    @Authorizable(resource = ADDRESS, action = Action.DELETE)
    public void deleteAddress(@PathVariable(name = "id") String id) {
        locationService.deleteAddress(id);
    }

}
