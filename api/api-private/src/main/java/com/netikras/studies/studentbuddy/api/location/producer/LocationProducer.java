package com.netikras.studies.studentbuddy.api.location.producer;

import com.netikras.studies.studentbuddy.api.location.generated.LocationApiProducer;
import com.netikras.studies.studentbuddy.api.location.producer.impl.secured.LocationProducerImpl;
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
    private LocationProducerImpl impl;

    @Override
    protected void onPurgeBuildingDto(String id) {
        impl.purgeBuilding(id);
    }

    @Override
    protected void onPurgeBuildingSectionDto(String id) {
        impl.purgeBuildingSection(id);
    }

    @Override
    protected void onPurgeAddressDto(String id) {
        impl.purgeAddress(id);
    }



    @Override
    protected BuildingDto onRetrieveBuildingDto(String id) {
        return impl.getBuilding(id);
    }

    @Override
    protected BuildingDto onCreateBuildingDto(BuildingDto buildingDto) {
        return impl.createBuilding(buildingDto);
    }

    @Override
    protected void onDeleteBuildingDto(String id) {
        impl.deleteBuilding(id);
    }

    @Override
    protected BuildingDto onUpdateBuildingDto(BuildingDto buildingDto) {
        return impl.updateBuilding(buildingDto);
    }

    @Override
    protected BuildingSectionDto onRetrieveBuildingSectionDto(String id) {
        return impl.getBuildingSection(id);
    }

    @Override
    protected BuildingSectionDto onCreateBuildingSectionDto(BuildingSectionDto buildingSectionDto) {
        return impl.createBuildingSection(buildingSectionDto);
    }

    @Override
    protected void onDeleteBuildingSectionDto(String id) {
        impl.deleteBuildingSection(id);
    }

    @Override
    protected BuildingSectionDto onUpdateBuildingSectionDto(BuildingSectionDto buildingSectionDto) {
        return impl.updateBuildingSection(buildingSectionDto);
    }

    @Override
    protected AddressDto onRetrieveAddressDto(String id) {
        return impl.getAddress(id);
    }

    @Override
    protected AddressDto onCreateAddressDto(AddressDto addressDto) {
        return impl.createAddress(addressDto);
    }

    @Override
    protected void onDeleteAddressDto(String id) {
        impl.deleteAddress(id);
    }

    @Override
    protected AddressDto onUpdateAddressDto(AddressDto addressDto) {
        return impl.updateAddress(addressDto);
    }

    @Override
    protected List<BuildingSectionDto> onSearchBuildingSectionDtoAllByTitle(String title) {
        return impl.searchAllBuildingSectionsByTitle(title);
    }

    @Override
    protected List<BuildingDto> onSearchBuildingDtoAllByTitle(String title) {
        return impl.searchAllBuildingsByTitle(title);
    }
}
