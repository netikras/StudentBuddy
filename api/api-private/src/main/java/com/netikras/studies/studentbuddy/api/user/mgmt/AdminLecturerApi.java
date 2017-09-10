package com.netikras.studies.studentbuddy.api.user.mgmt;

import com.netikras.studies.studentbuddy.core.data.api.dto.school.LecturerDto;
import com.netikras.studies.studentbuddy.core.data.api.model.Lecturer;
import com.netikras.tools.common.remote.http.HttpRequest.HttpMethod;
import com.netikras.tools.common.remote.http.rest.auto.CrudMethod;
import com.netikras.tools.common.remote.http.rest.auto.ExtendedMethod;
import com.netikras.tools.common.remote.http.rest.auto.annotations.GenerateCrud;
import com.netikras.tools.common.remote.http.rest.auto.annotations.MethodParam;
import com.netikras.tools.common.remote.http.rest.auto.annotations.RestApiLocation;
import com.netikras.tools.common.remote.http.rest.auto.annotations.RestApiTemplate;
import com.netikras.tools.common.remote.http.rest.auto.annotations.RestEndpoint;
import com.netikras.tools.common.remote.http.rest.auto.generator.Param.Type;

import static com.netikras.studies.studentbuddy.api.config.Initializer.API_URL;
import static com.netikras.tools.common.remote.http.rest.auto.ExtendedMethod.PURGE;

@RestApiTemplate(baseUrlPrefix = API_URL, baseUrl = "/mgmt/lecturer")
@GenerateCrud(dtoType = LecturerDto.class, methods = {CrudMethod.CREATE, CrudMethod.DELETE}, extend = {PURGE})
@RestApiLocation(producer = "../api-private", consumer = "../api-public", constants = "../api-public")
public abstract class AdminLecturerApi {

    @RestEndpoint(dtoType = LecturerDto.class, url = "/assign/{lecturerId}/discipline/{disciplineId}", method = HttpMethod.PUT)
    public abstract void assignToDiscipline(
            @MethodParam(type = Type.URL, name = "lecturerId") String lecturerId,
            @MethodParam(type = Type.URL, name = "disciplineId") String disciplineId
    );


    @RestEndpoint(dtoType = LecturerDto.class, url = "/unassign/{lecturerId}/discipline/{disciplineId}", method = HttpMethod.PUT)
    public abstract void unassignFromDiscipline(
            @MethodParam(type = Type.URL, name = "lecturerId") String lecturerId,
            @MethodParam(type = Type.URL, name = "disciplineId") String disciplineId
    );

}
