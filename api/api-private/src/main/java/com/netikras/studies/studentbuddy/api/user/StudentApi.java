package com.netikras.studies.studentbuddy.api.user;

import com.netikras.studies.studentbuddy.core.data.api.dto.school.LectureGuestDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.StudentDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.StudentsGroupDto;
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

@RestApiTemplate(baseUrlPrefix = API_URL, baseUrl = "/student", cruds = {
        @GenerateCrud(dtoType = StudentDto.class, methods = {CrudMethod.RETRIEVE, CrudMethod.UPDATE}),
        @GenerateCrud(dtoType = StudentsGroupDto.class, methods = {CrudMethod.RETRIEVE}, url = "/group"),
        @GenerateCrud(dtoType = LectureGuestDto.class, methods = {CrudMethod.RETRIEVE, CrudMethod.UPDATE}, url = "/guest"),
})
@RestApiLocation(producer = "../api-private", consumer = "../api-public", constants = "../api-public")
public abstract class StudentApi {

    @RestEndpoint(url = "/person/id/{id}", method = HttpMethod.GET)
    public abstract StudentDto getByPersonId(@MethodParam(type = Type.URL, name = "id") String id);

    @RestEndpoint(url = "/group/title/{title}", method = HttpMethod.GET)
    public abstract StudentsGroupDto getByTitle(@MethodParam(type = Type.URL, name = "title") String title);

    @RestEndpoint(url = "/group/all", method = HttpMethod.GET, dtoType = StudentsGroupDto.class)
    public abstract List<StudentsGroupDto> getAll();


    @RestEndpoint(url = "/all/group/id/{id}", method = HttpMethod.GET, dtoType = StudentDto.class)
    public abstract List<StudentDto> getAllByGroup(@MethodParam(type = Type.URL, name = "id") String groupId);

    @RestEndpoint(url = "/search/fname/{fname}", method = HttpMethod.GET, dtoType = StudentDto.class)
    public abstract List<StudentDto> searchAllByFirstName(@MethodParam(type = Type.URL, name = "fname") String fnameSubstring);

    @RestEndpoint(url = "/search/lname/{fname}", method = HttpMethod.GET, dtoType = StudentDto.class)
    public abstract List<StudentDto> searchAllByLastName(@MethodParam(type = Type.URL, name = "lname") String lnameSubstring);

    @RestEndpoint(url = "/group/search/title/{title}", method = HttpMethod.GET, dtoType = StudentsGroupDto.class)
    public abstract List<StudentsGroupDto> searchAllByTitle(@MethodParam(type = Type.URL, name = "title") String titleSubstring);

    @RestEndpoint(url = "/guest/search/lname/{lname}", method = HttpMethod.GET, dtoType = LectureGuestDto.class)
    public abstract List<LectureGuestDto> searchByLastName(@MethodParam(type = Type.URL, name = "lname") String lnameSubstring);

    @RestEndpoint(url = "/guest/search/fname/{fname}", method = HttpMethod.GET, dtoType = LectureGuestDto.class)
    public abstract List<LectureGuestDto> searchByFirstName(@MethodParam(type = Type.URL, name = "fname") String fnameSubstring);


}
