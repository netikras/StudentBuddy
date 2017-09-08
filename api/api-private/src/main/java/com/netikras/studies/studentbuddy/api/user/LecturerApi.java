package com.netikras.studies.studentbuddy.api.user;

import com.netikras.studies.studentbuddy.core.data.api.dto.school.LecturerDto;
import com.netikras.tools.common.remote.http.HttpRequest.HttpMethod;
import com.netikras.tools.common.remote.http.rest.auto.CrudMethod;
import com.netikras.tools.common.remote.http.rest.auto.annotations.GenerateCrud;
import com.netikras.tools.common.remote.http.rest.auto.annotations.MethodParam;
import com.netikras.tools.common.remote.http.rest.auto.annotations.RestApiLocation;
import com.netikras.tools.common.remote.http.rest.auto.annotations.RestApiTemplate;
import com.netikras.tools.common.remote.http.rest.auto.annotations.RestEndpoint;
import com.netikras.tools.common.remote.http.rest.auto.generator.Param.Type;

import java.util.List;

import static com.netikras.studies.studentbuddy.api.config.Initializer.API_URL;

@RestApiTemplate(baseUrlPrefix = API_URL, baseUrl = "/lecturer")
@GenerateCrud(dtoType = LecturerDto.class, methods = {CrudMethod.RETRIEVE, CrudMethod.UPDATE})
@RestApiLocation(producer = "../api-private", consumer = "../api-public", constants = "../api-public")
public abstract class LecturerApi {


    @RestEndpoint(url = "/discipline/id/{id}", method = HttpMethod.GET)
    public abstract List<LecturerDto> getByDiscipline(
            @MethodParam(type = Type.URL, name = "id") String disciplineId);


    @RestEndpoint(url = "/person/id/{id}", method = HttpMethod.GET)
    public abstract LecturerDto getByPersonId(
            @MethodParam(type = Type.URL, name = "id") String personId);


    @RestEndpoint(url = "/search/degree/{degree}", method = HttpMethod.GET)
    public abstract List<LecturerDto> searchAllLecturersByDegree(
            @MethodParam(type = Type.URL, name = "degree") String degreeSubstring);

    @RestEndpoint(url = "/search/fname/{fname}", method = HttpMethod.GET)
    public abstract List<LecturerDto> searchByFirstName(
            @MethodParam(type = Type.URL, name = "fname") String fnameSubstring);


    @RestEndpoint(url = "/search/lname/{lname}", method = HttpMethod.GET)
    public abstract List<LecturerDto> searchAllLecturersByLastName(
            @MethodParam(type = Type.URL, name = "lname") String lnameSubstring);


}
