package com.netikras.studies.studentbuddy.api.user.mgmt;

import com.netikras.studies.studentbuddy.core.data.api.dto.meta.UserDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.StudentDto;
import com.netikras.tools.common.remote.http.HttpRequest;
import com.netikras.tools.common.remote.http.rest.auto.CrudMethod;
import com.netikras.tools.common.remote.http.rest.auto.annotations.GenerateCrud;
import com.netikras.tools.common.remote.http.rest.auto.annotations.MethodParam;
import com.netikras.tools.common.remote.http.rest.auto.annotations.RestApiLocation;
import com.netikras.tools.common.remote.http.rest.auto.annotations.RestApiTemplate;
import com.netikras.tools.common.remote.http.rest.auto.annotations.RestEndpoint;
import com.netikras.tools.common.remote.http.rest.auto.generator.Param;

import static com.netikras.studies.studentbuddy.api.config.Initializer.API_URL;
import static com.netikras.tools.common.remote.http.rest.auto.ExtendedMethod.PURGE;

@RestApiTemplate(baseUrlPrefix = API_URL, baseUrl = "/mgmt/user")
@GenerateCrud(dtoType = UserDto.class, methods = {CrudMethod.CREATE, CrudMethod.DELETE}, extend = {PURGE})
@RestApiLocation(producer = "../api-private", consumer = "../api-public", constants = "../api-public")
public abstract class AdminUserApi {


    @RestEndpoint(dtoType = UserDto.class, url = "/id/{id}/role/name/{rolename}", method = HttpRequest.HttpMethod.PUT)
    public abstract UserDto assignRoleByName(
            @MethodParam(type = Param.Type.URL, name = "id") String userId,
            @MethodParam(type = Param.Type.URL, name = "rolename") String roleName
    );

    @RestEndpoint(dtoType = UserDto.class, url = "/id/{id}/role/name/{rolename}", method = HttpRequest.HttpMethod.DELETE)
    public abstract UserDto unassignRoleByName(
            @MethodParam(type = Param.Type.URL, name = "id") String userId,
            @MethodParam(type = Param.Type.URL, name = "rolename") String roleName
    );





}
