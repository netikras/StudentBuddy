package com.netikras.studies.studentbuddy.api.user;

import com.netikras.studies.studentbuddy.core.data.api.dto.meta.RolePermissionDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.meta.UserDto;
import com.netikras.tools.common.remote.AuthenticationDetail;
import com.netikras.tools.common.remote.http.HttpRequest.HttpMethod;
import com.netikras.tools.common.remote.http.rest.auto.annotations.GenerateCrud;
import com.netikras.tools.common.remote.http.rest.auto.annotations.MethodParam;
import com.netikras.tools.common.remote.http.rest.auto.annotations.RestApiLocation;
import com.netikras.tools.common.remote.http.rest.auto.annotations.RestApiTemplate;
import com.netikras.tools.common.remote.http.rest.auto.annotations.RestEndpoint;
import com.netikras.tools.common.remote.http.rest.auto.generator.Param.Type;

import java.util.List;

import static com.netikras.studies.studentbuddy.api.config.Initializer.API_URL;

@RestApiTemplate(baseUrlPrefix = API_URL, baseUrl = "/user")
@GenerateCrud(dtoType = UserDto.class)
@RestApiLocation(producer = "../api-private", consumer = "../api-public", constants = "../api-public")
public abstract class UserApi {

    @RestEndpoint(dtoType = UserDto.class, url = "/login", method = HttpMethod.POST)
    public abstract UserDto login(
            @MethodParam String username,
            @MethodParam String password,
            @MethodParam(type = Type.BODY, required = false) AuthenticationDetail auth
    );

    @RestEndpoint(dtoType = UserDto.class, url = "/login/auth", method = HttpMethod.POST)
    public abstract UserDto loginAuth(
            @MethodParam(type = Type.BODY, required = false) AuthenticationDetail auth
    );


    @RestEndpoint(dtoType = UserDto.class, url = "/logout", method = HttpMethod.POST)
    public abstract void logout();

    @RestEndpoint(dtoType = UserDto.class, url = "/current", method = HttpMethod.GET)
    public abstract UserDto getCurrent();


    @RestEndpoint(dtoType = RolePermissionDto.class, url = "/id/{id}/permissions", method = HttpMethod.GET)
    public abstract List<RolePermissionDto> getPermittedActions(@MethodParam(type = Type.URL, name = "id") String id);


    @RestEndpoint(dtoType = UserDto.class, url = "/id/{id}/password", method = HttpMethod.PUT)
    public abstract void changePassword(
            @MethodParam(type = Type.URL, name = "id") String userId,
            @MethodParam(type = Type.REQUEST, name = "password") String password
    );


    @RestEndpoint(dtoType = UserDto.class, url = "/name/{name}", method = HttpMethod.GET)
    public abstract UserDto getByName(@MethodParam(type = Type.URL, name = "name") String name);


    @RestEndpoint(dtoType = UserDto.class, url = "/person/id/{id}", method = HttpMethod.GET)
    public abstract UserDto getByPerson(@MethodParam(type = Type.URL, name = "id") String userId);


    // search

    @RestEndpoint(dtoType = UserDto.class, url = "/search/username/{username}", method = HttpMethod.GET)
    public abstract List<UserDto> searchAllByUsername(@MethodParam(type = Type.URL, name = "username") String query);

    @RestEndpoint(dtoType = UserDto.class, url = "/search/fname/{fname}", method = HttpMethod.GET)
    public abstract List<UserDto> searchAllByFirstName(@MethodParam(type = Type.URL, name = "fname") String query);

    @RestEndpoint(dtoType = UserDto.class, url = "/search/lname/{lname}", method = HttpMethod.GET)
    public abstract List<UserDto> searchAllByLastName(@MethodParam(type = Type.URL, name = "lname") String query);
}
