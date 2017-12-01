package com.netikras.studies.studentbuddy.api.location;

import com.netikras.studies.studentbuddy.core.data.api.dto.location.AddressDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.location.BuildingDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.location.BuildingSectionDto;
import com.netikras.tools.common.remote.http.HttpRequest.HttpMethod;
import com.netikras.tools.common.remote.http.rest.auto.ExtendedMethod;
import com.netikras.tools.common.remote.http.rest.auto.annotations.GenerateCrud;
import com.netikras.tools.common.remote.http.rest.auto.annotations.MethodParam;
import com.netikras.tools.common.remote.http.rest.auto.annotations.RestApiLocation;
import com.netikras.tools.common.remote.http.rest.auto.annotations.RestApiTemplate;
import com.netikras.tools.common.remote.http.rest.auto.annotations.RestEndpoint;
import com.netikras.tools.common.remote.http.rest.auto.generator.Param.Type;

import java.util.List;

import static com.netikras.studies.studentbuddy.api.config.Initializer.API_URL;
import static com.netikras.tools.common.remote.http.rest.auto.ExtendedMethod.GET_ALL;
import static com.netikras.tools.common.remote.http.rest.auto.ExtendedMethod.PURGE;

@RestApiTemplate(baseUrlPrefix = API_URL, baseUrl = "/location", cruds = {
        @GenerateCrud(dtoType = BuildingDto.class, url = "/building", extend = {PURGE, GET_ALL}),
        @GenerateCrud(dtoType = BuildingSectionDto.class, url = "/building/section", extend = {PURGE}),
        @GenerateCrud(dtoType = AddressDto.class, url = "/address", extend = {PURGE, GET_ALL})
})
@RestApiLocation(producer = "../api-private", consumer = "../api-public", constants = "../api-public")
public abstract class LocationApi {

    @RestEndpoint(url = "/building/id/{id}/section", method = HttpMethod.GET, dtoType = BuildingSectionDto.class, action = "getAllByBuildingId")
    public abstract List<BuildingSectionDto> getAllSectionsByBuildingId(
            @MethodParam(type = Type.URL, name = "id") String id
    );

    @RestEndpoint(url = "/address/id/{id}/building", method = HttpMethod.GET, dtoType = BuildingDto.class, action = "getByAddressId")
    public abstract BuildingDto getBuildingByAddressId(
            @MethodParam(type = Type.URL, name = "id") String id
    );

    @RestEndpoint(url = "/address/id/{id}/building/section", method = HttpMethod.GET, dtoType = BuildingSectionDto.class, action = "getAllByAddressId")
    public abstract List<BuildingSectionDto> getAllBuildingSectionsByAddressId(
            @MethodParam(type = Type.URL, name = "id") String id
    );

    @RestEndpoint(url = "/building/section/title/{title}", method = HttpMethod.GET, dtoType = BuildingSectionDto.class, action = "searchAllByTitle")
    public abstract List<BuildingSectionDto> searchBuildingSectionByTitle(
            @MethodParam(type = Type.URL, name = "title") String titleSubstring
    );


    @RestEndpoint(url = "/building/title/{title}", method = HttpMethod.GET, dtoType = BuildingDto.class, action = "searchAllByTitle")
    public abstract List<BuildingDto> searchBuildingByTitle(
            @MethodParam(type = Type.URL, name = "title") String titleSubstring
    );

}
