package com.netikras.studies.studentbuddy.api.user.mgmt;

import com.netikras.studies.studentbuddy.core.data.api.dto.PersonDto;
import com.netikras.tools.common.remote.http.rest.auto.CrudMethod;
import com.netikras.tools.common.remote.http.rest.auto.annotations.GenerateCrud;
import com.netikras.tools.common.remote.http.rest.auto.annotations.RestApiLocation;
import com.netikras.tools.common.remote.http.rest.auto.annotations.RestApiTemplate;

import static com.netikras.studies.studentbuddy.api.config.Initializer.API_URL;

@RestApiTemplate(baseUrlPrefix = API_URL, baseUrl = "/mgmt/person")
@GenerateCrud(dtoType = PersonDto.class, methods = {CrudMethod.CREATE, CrudMethod.DELETE, CrudMethod.UPDATE})
@RestApiLocation(producer = "../api-private", consumer = "../api-public", constants = "../api-public")
public class AdminPersonApi {
}
