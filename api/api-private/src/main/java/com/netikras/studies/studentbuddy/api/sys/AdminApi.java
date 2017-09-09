package com.netikras.studies.studentbuddy.api.sys;

import com.netikras.studies.studentbuddy.core.data.api.dto.meta.PasswordRequirementDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.meta.SystemSettingDto;
import com.netikras.tools.common.remote.http.HttpRequest.HttpMethod;
import com.netikras.tools.common.remote.http.rest.auto.annotations.GenerateCrud;
import com.netikras.tools.common.remote.http.rest.auto.annotations.MethodParam;
import com.netikras.tools.common.remote.http.rest.auto.annotations.RestApiLocation;
import com.netikras.tools.common.remote.http.rest.auto.annotations.RestApiTemplate;
import com.netikras.tools.common.remote.http.rest.auto.annotations.RestEndpoint;
import com.netikras.tools.common.remote.http.rest.auto.generator.Param.Type;

import java.util.List;

import static com.netikras.studies.studentbuddy.api.config.Initializer.API_URL;

@RestApiTemplate(baseUrlPrefix = API_URL, baseUrl = "/admin", cruds = {
        @GenerateCrud(dtoType = SystemSettingDto.class, url = "/settings/stored"),
        @GenerateCrud(dtoType = PasswordRequirementDto.class, url = "/pwreq/stored")
})
@RestApiLocation(producer = "../api-private", consumer = "../api-public", constants = "../api-public")
public abstract class AdminApi {


    @RestEndpoint(url = "/settings/stored", method = HttpMethod.GET, dtoType = SystemSettingDto.class, action = "getAllStored")
    public abstract List<SystemSettingDto> getStoredSettings();


    @RestEndpoint(url = "/settings/live", method = HttpMethod.GET, dtoType = SystemSettingDto.class, action = "getAllLive")
    public abstract List<SystemSettingDto> getLiveSettings();


    @RestEndpoint(url = "/settings/live/name/{name}", method = HttpMethod.GET, dtoType = SystemSettingDto.class, action = "getLive")
    public abstract SystemSettingDto getLiveSettingByName(
            @MethodParam(type = Type.URL, name = "name") String name
    );


    @RestEndpoint(url = "/settings/stored/name/{name}", method = HttpMethod.GET, dtoType = SystemSettingDto.class, action = "getStored")
    public abstract SystemSettingDto getStoredSettingByName(
            @MethodParam(type = Type.URL, name = "name") String name
    );

    @RestEndpoint(url = "/settings/stored/name/{name}", method = HttpMethod.DELETE, dtoType = SystemSettingDto.class, action = "deleteStoredByName")
    public abstract void deleteStoredSettingByName(
            @MethodParam(type = Type.URL, name = "name") String name
    );


    @RestEndpoint(url = "/pwreq/live", method = HttpMethod.GET, dtoType = PasswordRequirementDto.class, action = "getAllLive")
    public abstract List<PasswordRequirementDto> getLivePasswordRequirements();

    @RestEndpoint(url = "/pwreq/stored", method = HttpMethod.GET, dtoType = PasswordRequirementDto.class, action = "getAllStored")
    public abstract List<PasswordRequirementDto> getStoredPasswordRequirements();


    @RestEndpoint(url = "/pwreq/refresh", method = HttpMethod.PUT, dtoType = PasswordRequirementDto.class, action = "refreshLive")
    public abstract void refreshPasswordRequirements();


    @RestEndpoint(url = "/settings/refresh", method = HttpMethod.PUT, dtoType = SystemSettingDto.class, action = "refreshLive")
    public abstract void refreshSystemSettings();

}
