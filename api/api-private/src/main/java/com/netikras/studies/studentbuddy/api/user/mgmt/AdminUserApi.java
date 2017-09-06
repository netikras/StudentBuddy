package com.netikras.studies.studentbuddy.api.user.mgmt;

import com.netikras.studies.studentbuddy.core.data.api.dto.meta.UserDto;
import com.netikras.tools.common.remote.http.rest.auto.CrudMethod;
import com.netikras.tools.common.remote.http.rest.auto.annotations.GenerateCrud;
import com.netikras.tools.common.remote.http.rest.auto.annotations.RestApiLocation;
import com.netikras.tools.common.remote.http.rest.auto.annotations.RestApiTemplate;

import static com.netikras.studies.studentbuddy.api.config.Initializer.API_URL;

@RestApiTemplate(baseUrlPrefix = API_URL, baseUrl = "/mgmt/user")
@GenerateCrud(dtoType = UserDto.class, methods = {CrudMethod.CREATE, CrudMethod.DELETE})
@RestApiLocation(producer = "../api-private", consumer = "../api-public", constants = "../api-public")
public class AdminUserApi {
}
