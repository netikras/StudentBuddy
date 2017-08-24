package com.netikras.studies.studentbuddy.api.location;

import com.fasterxml.jackson.core.type.TypeReference;
import com.netikras.studies.studentbuddy.core.data.api.dto.location.AddressDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.location.BuildingDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.location.BuildingSectionDto;
import com.netikras.tools.common.remote.http.GenericRestConsumer;
import com.netikras.tools.common.remote.http.HttpRequest;
import com.netikras.tools.common.remote.http.impl.json.HttpResponseJsonImpl;

import java.util.List;

import static com.netikras.studies.studentbuddy.api.constants.LocationConstants.endpointCreateAddress;
import static com.netikras.studies.studentbuddy.api.constants.LocationConstants.endpointCreateBuilding;
import static com.netikras.studies.studentbuddy.api.constants.LocationConstants.endpointCreateBuildingSection;
import static com.netikras.studies.studentbuddy.api.constants.LocationConstants.endpointDeleteAddressById;
import static com.netikras.studies.studentbuddy.api.constants.LocationConstants.endpointDeleteBuildingById;
import static com.netikras.studies.studentbuddy.api.constants.LocationConstants.endpointDeleteBuildingSectionById;
import static com.netikras.studies.studentbuddy.api.constants.LocationConstants.endpointGetAddressById;
import static com.netikras.studies.studentbuddy.api.constants.LocationConstants.endpointGetBuildingById;
import static com.netikras.studies.studentbuddy.api.constants.LocationConstants.endpointGetBuildingSectionById;
import static com.netikras.studies.studentbuddy.api.constants.LocationConstants.endpointSearchAllBuildingSectionsByTitle;
import static com.netikras.studies.studentbuddy.api.constants.LocationConstants.endpointSearchAllBuildingsByTitle;
import static com.netikras.studies.studentbuddy.api.constants.LocationConstants.endpointUpdateAddress;
import static com.netikras.studies.studentbuddy.api.constants.LocationConstants.endpointUpdateBuilding;
import static com.netikras.studies.studentbuddy.api.constants.LocationConstants.endpointUpdateBuildingSection;

public class LocationConsumer extends GenericRestConsumer {


    private TypeReference buildingsListTypeRef = new TypeReference<List<BuildingDto>>() {};
    private TypeReference buildingSectionsListTypeRef = new TypeReference<List<BuildingSectionDto>>() {};

    // building

    public BuildingDto createBuilding(BuildingDto buildingDto) {
        HttpRequest<BuildingDto> request = createRequest(endpointCreateBuilding())
                .withExpectedType(BuildingDto.class)
                .setObject(buildingDto);

        BuildingDto dto = (BuildingDto) sendRequest(request);
        return dto;
    }

    public BuildingDto updateBuilding(BuildingDto buildingDto) {
        HttpRequest<BuildingDto> request = createRequest(endpointUpdateBuilding())
                .withExpectedType(BuildingDto.class)
                .setObject(buildingDto);

        BuildingDto dto = (BuildingDto) sendRequest(request);
        return dto;
    }

    public BuildingDto getBuildingById(String id) {
        HttpRequest request = createRequest(endpointGetBuildingById())
                .withExpectedType(BuildingDto.class)
                .setUrlProperty("id", id);

        BuildingDto dto = (BuildingDto) sendRequest(request);
        return dto;
    }

    public boolean deleteBuildingById(String id) {
        HttpRequest request = createRequest(endpointDeleteBuildingById())
                .setUrlProperty("id", id);

        HttpResponseJsonImpl responseJson = new HttpResponseJsonImpl();
        sendRequest(request, responseJson);
        return responseJson.isResponseSuccess();
    }

    public List<BuildingDto> searchAllBuildingsByTitle(String title) {
        HttpRequest request = createRequest(endpointSearchAllBuildingsByTitle())
                .withTypeReference(buildingsListTypeRef)
                .setUrlProperty("title", title);

        List<BuildingDto> dtos = (List<BuildingDto>) sendRequest(request);
        return dtos;
    }


    // building section

    public BuildingSectionDto createBuildingSection(BuildingSectionDto sectionDto) {
        HttpRequest<BuildingSectionDto> request = createRequest(endpointCreateBuildingSection())
                .withExpectedType(BuildingSectionDto.class)
                .setObject(sectionDto);

        BuildingSectionDto dto = (BuildingSectionDto) sendRequest(request);
        return dto;
    }

    public BuildingSectionDto updateBuildingSection(BuildingSectionDto sectionDto) {
        HttpRequest<BuildingSectionDto> request = createRequest(endpointUpdateBuildingSection())
                .withExpectedType(BuildingSectionDto.class)
                .setObject(sectionDto);

        BuildingSectionDto dto = (BuildingSectionDto) sendRequest(request);
        return dto;
    }

    public BuildingSectionDto getBuildingSectionById(String id) {
        HttpRequest request = createRequest(endpointGetBuildingSectionById())
                .withExpectedType(BuildingSectionDto.class)
                .setUrlProperty("id", id);

        BuildingSectionDto dto = (BuildingSectionDto) sendRequest(request);
        return dto;
    }

    public boolean deleteBuildingSectionById(String id) {
        HttpRequest request = createRequest(endpointDeleteBuildingSectionById())
                .setUrlProperty("id", id);

        HttpResponseJsonImpl responseJson = new HttpResponseJsonImpl();
        sendRequest(request, responseJson);
        return responseJson.isResponseSuccess();
    }


    public List<BuildingSectionDto> searchAllBuildingSectionsByTitle(String title) {
        HttpRequest request = createRequest(endpointSearchAllBuildingSectionsByTitle())
                .withTypeReference(buildingSectionsListTypeRef)
                .setUrlProperty("title", title);

        List<BuildingSectionDto> dtos = (List<BuildingSectionDto>) sendRequest(request);
        return dtos;
    }

    // address

    public AddressDto createAddress(AddressDto addressDto) {
        HttpRequest<AddressDto> request = createRequest(endpointCreateAddress())
                .withExpectedType(AddressDto.class)
                .setObject(addressDto);

        AddressDto dto = (AddressDto) sendRequest(request);
        return dto;
    }

    public AddressDto updateAddress(AddressDto addressDto) {
        HttpRequest<AddressDto> request = createRequest(endpointUpdateAddress())
                .withExpectedType(AddressDto.class)
                .setObject(addressDto);

        AddressDto dto = (AddressDto) sendRequest(request);
        return dto;
    }

    public AddressDto getAddressById(String id) {
        HttpRequest request = createRequest(endpointGetAddressById())
                .withExpectedType(AddressDto.class)
                .setUrlProperty("id", id);

        AddressDto dto = (AddressDto) sendRequest(request);
        return dto;
    }

    public boolean deleteAddressById(String id) {
        HttpRequest request = createRequest(endpointDeleteAddressById())
                .setUrlProperty("id", id);

        HttpResponseJsonImpl responseJson = new HttpResponseJsonImpl();
        sendRequest(request, responseJson);
        return responseJson.isResponseSuccess();
    }
}
