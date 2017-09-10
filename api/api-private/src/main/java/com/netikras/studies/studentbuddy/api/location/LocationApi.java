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
import static com.netikras.tools.common.remote.http.rest.auto.ExtendedMethod.PURGE;

@RestApiTemplate(baseUrlPrefix = API_URL, baseUrl = "/location", cruds = {
        @GenerateCrud(dtoType = BuildingDto.class, url = "/building", extend = {PURGE}),
        @GenerateCrud(dtoType = BuildingSectionDto.class, url = "/building/section", extend = {PURGE}),
        @GenerateCrud(dtoType = AddressDto.class, url = "/address", extend = {PURGE})
})
@RestApiLocation(producer = "../api-private", consumer = "../api-public", constants = "../api-public")
public abstract class LocationApi {

    @RestEndpoint(url = "/building/section/title/{title}", method = HttpMethod.GET, dtoType = BuildingSectionDto.class, action = "searchAllByTitle")
    public abstract List<BuildingSectionDto> searchBuildingSectionByTitle(
            @MethodParam(type = Type.URL, name = "title") String titleSubstring
    );


    @RestEndpoint(url = "/building/title/{title}", method = HttpMethod.GET, dtoType = BuildingDto.class, action = "searchAllByTitle")
    public abstract List<BuildingDto> searchBuildingByTitle(
            @MethodParam(type = Type.URL, name = "title") String titleSubstring
    );

}
