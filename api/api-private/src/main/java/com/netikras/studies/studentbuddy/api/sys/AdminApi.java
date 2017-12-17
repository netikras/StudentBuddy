package com.netikras.studies.studentbuddy.api.sys;

import com.netikras.studies.studentbuddy.core.data.api.dto.meta.PasswordRequirementDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.meta.RoleDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.meta.RolePermissionDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.meta.SystemSettingDto;
import com.netikras.tools.common.remote.http.HttpRequest.HttpMethod;
import com.netikras.tools.common.remote.http.rest.auto.CrudMethod;
import com.netikras.tools.common.remote.http.rest.auto.ExtendedMethod;
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
        @GenerateCrud(dtoType = PasswordRequirementDto.class, url = "/pwreq/stored"),
        @GenerateCrud(dtoType = RoleDto.class, url = "/role", methods = {CrudMethod.RETRIEVE, CrudMethod.DELETE}, extend = ExtendedMethod.GET_ALL)
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

    @RestEndpoint(url = "/role/permission/{roleName}/{resourceName}/{actionName}/{resourceId}", method = HttpMethod.POST, dtoType = RolePermissionDto.class, action = "create")
    public abstract RolePermissionDto createRolePermission(
            @MethodParam(type = Type.URL, name = "roleName") String roleName,
            @MethodParam(type = Type.URL, name = "resourceName") String resourceName,
            @MethodParam(type = Type.URL, name = "actionName") String actionName,
            @MethodParam(type = Type.URL, name = "resourceId") String resourceId,
            @MethodParam(type = Type.REQUEST, name = "strict", required = false) Boolean strict
    );

    @RestEndpoint(url = "/role/permission/{roleName}/{resourceName}/{actionName}/{resourceId}", method = HttpMethod.DELETE, dtoType = RolePermissionDto.class, action = "delete")
    public abstract void deleteRolePermission(
            @MethodParam(type = Type.URL, name = "roleName") String roleName,
            @MethodParam(type = Type.URL, name = "resourceName") String resourceName,
            @MethodParam(type = Type.URL, name = "actionName") String actionName,
            @MethodParam(type = Type.URL, name = "resourceId") String resourceId,
            @MethodParam(type = Type.REQUEST, name = "strict") Boolean strict
    );

    @RestEndpoint(url = "/role/name/{roleName}/permission/id/{id}", method = HttpMethod.DELETE, dtoType = RolePermissionDto.class, action = "deleteById")
    public abstract void deleteRolePermissionById(
            @MethodParam(type = Type.URL, name = "roleName") String roleName,
            @MethodParam(type = Type.URL, name = "id")String id);

    @RestEndpoint(url = "/role/permission/refresh", method = HttpMethod.POST, action = "refreshLiveRolePermissions")
    public abstract void refreshLiveRolePermissions();

    @RestEndpoint(url = "/role/{roleName}", method = HttpMethod.POST, dtoType = RoleDto.class, action = "create")
    public abstract RoleDto createRole(
            @MethodParam(type = Type.URL, name = "roleName") String roleName
    );

    @RestEndpoint(url = "/role/name/{roleName}", method = HttpMethod.GET, dtoType = RoleDto.class, action = "getByName")
    public abstract RoleDto getRoleByName(
            @MethodParam(type = Type.URL, name = "roleName") String roleName
    );

}
