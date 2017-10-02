package com.netikras.studies.studentbuddy.api.location;


import com.netikras.studies.studentbuddy.core.data.api.dto.school.CourseDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.DisciplineDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.PersonnelMemberDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.SchoolDepartmentDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.SchoolDto;
import com.netikras.studies.studentbuddy.core.data.meta.annotations.Authorizable;
import com.netikras.tools.common.remote.http.HttpRequest.HttpMethod;
import com.netikras.tools.common.remote.http.rest.auto.annotations.GenerateCrud;
import com.netikras.tools.common.remote.http.rest.auto.annotations.MethodParam;
import com.netikras.tools.common.remote.http.rest.auto.annotations.RestApiLocation;
import com.netikras.tools.common.remote.http.rest.auto.annotations.RestApiTemplate;
import com.netikras.tools.common.remote.http.rest.auto.annotations.RestEndpoint;
import com.netikras.tools.common.remote.http.rest.auto.generator.Param.Type;

import java.util.List;

import static com.netikras.studies.studentbuddy.api.config.Initializer.API_URL;
import static com.netikras.tools.common.remote.http.rest.auto.ExtendedMethod.GET_ALL;
import static com.netikras.tools.common.remote.http.rest.auto.ExtendedMethod.PURGE;

@RestApiTemplate(baseUrlPrefix = API_URL, baseUrl = "/school", cruds = {
        @GenerateCrud(dtoType = SchoolDto.class, extend = {PURGE, GET_ALL}),
        @GenerateCrud(dtoType = SchoolDepartmentDto.class, url = "/department", extend = {PURGE}),
        @GenerateCrud(dtoType = DisciplineDto.class, url = "/discipline", extend = {PURGE}),
        @GenerateCrud(dtoType = CourseDto.class, url = "/course", extend = {PURGE, GET_ALL}),
        @GenerateCrud(dtoType = PersonnelMemberDto.class, url = "/personnel", extend = {PURGE})
})
@RestApiLocation(producer = "../api-private", consumer = "../api-public", constants = "../api-public")
public abstract class SchoolApi {


    @RestEndpoint(url = "/search/title/{title}", method = HttpMethod.GET, dtoType = SchoolDto.class, action = "searchAllByTitle")
    public abstract List<SchoolDto> searchAllSchoolsByTitle(
            @MethodParam(type = Type.URL, name = "title") String query
    );


    @RestEndpoint(url = "/id/{id}/discipline", method = HttpMethod.GET, dtoType = DisciplineDto.class, action = "getAllBySchoolId")
    public abstract List<DisciplineDto> getAllDisciplinesBySchoolId(
            @MethodParam(type = Type.URL, name = "id") String id
    );

    @RestEndpoint(url = "/id/{id}/course", method = HttpMethod.GET, dtoType = CourseDto.class, action = "getAllBySchoolId")
    public abstract List<CourseDto> getAllCoursesBySchoolId(
            @MethodParam(type = Type.URL, name = "id") String id
    );

    @RestEndpoint(url = "/course/{courseId}/lecture/{lectureId}", method = HttpMethod.PUT, dtoType = CourseDto.class, action = "assignLecture")
    public abstract CourseDto assignCourseLecture(
            @MethodParam(type = Type.URL, name = "courseId") String courseId,
            @MethodParam(type = Type.URL, name = "lectureId") String lectureId
    );

    @RestEndpoint(url = "/course/{courseId}/lecture/{lectureId}", method = HttpMethod.DELETE, dtoType = CourseDto.class, action = "unassignLecture")
    public abstract CourseDto unassignCourseLecture(
            @MethodParam(type = Type.URL, name = "courseId") String courseId,
            @MethodParam(type = Type.URL, name = "lectureId") String lectureId
    );


    @RestEndpoint(url = "/id/{id}/personnel", method = HttpMethod.GET, dtoType = PersonnelMemberDto.class, action = "searchAllBySchoolId")
    public abstract List<PersonnelMemberDto> getAllPersonnelBySchoolId(
            @MethodParam(type = Type.URL, name = "id") String schoolId
    );

    @RestEndpoint(url = "/discipline/id/{id}/course", method = HttpMethod.GET, dtoType = CourseDto.class, action = "getAllByDisciplineId")
    public abstract List<CourseDto> getAllCoursesByDisciplineId(
            @MethodParam(type = Type.URL, name = "id") String id
    );

    @RestEndpoint(url = "/course/search/title/{title}", method = HttpMethod.GET, dtoType = CourseDto.class, action = "searchAllByTitle")
    public abstract List<CourseDto> searchAllCoursesByTitle(
            @MethodParam(type = Type.URL, name = "title") String titleSubstring
    );


    @RestEndpoint(url = "/department/search/title/{title}", method = HttpMethod.GET, dtoType = SchoolDepartmentDto.class, action = "searchAllByTitle")
    public abstract List<SchoolDepartmentDto> searchAllDepartmentsByTitle(
            @MethodParam(type = Type.URL, name = "title") String titleSubstring
    );


    @RestEndpoint(url = "/personnel/search/title/{title}", method = HttpMethod.GET, dtoType = PersonnelMemberDto.class, action = "searchAllByTitle")
    public abstract List<PersonnelMemberDto> searchAllPersonnelByTitle(
            @MethodParam(type = Type.URL, name = "title") String titleSubstring
    );


    @RestEndpoint(url = "/personnel/search/fname/{fname}", method = HttpMethod.GET, dtoType = PersonnelMemberDto.class, action = "searchAllByFirstName")
    public abstract List<PersonnelMemberDto> searchAllPersonnelByFirstName(
            @MethodParam(type = Type.URL, name = "fname") String fnameSubstring
    );


    @RestEndpoint(url = "/personnel/search/lname/{lname}", method = HttpMethod.GET, dtoType = PersonnelMemberDto.class, action = "searchAllByLastName")
    public abstract List<PersonnelMemberDto> searchAllPersonnelByLastName(
            @MethodParam(type = Type.URL, name = "lname") String lnameSubstring
    );


    @RestEndpoint(url = "/personnel/search/code/{code}", method = HttpMethod.GET, dtoType = PersonnelMemberDto.class, action = "searchAllByCode")
    public abstract List<PersonnelMemberDto> searchAllPersonnelByPersonalCode(
            @MethodParam(type = Type.URL, name = "code") String code
    );


    @RestEndpoint(url = "/personnel/search/id2/{id}", method = HttpMethod.GET, dtoType = PersonnelMemberDto.class, action = "searchAllByIdentifier")
    public abstract List<PersonnelMemberDto> searchAllPersonnelByIdentifier(
            @MethodParam(type = Type.URL, name = "id") String identifier
    );


    @RestEndpoint(url = "/discipline/search/title/{title}", method = HttpMethod.GET, dtoType = DisciplineDto.class, action = "searchAllByTitle")
    public abstract List<DisciplineDto> searchAllDisciplinesByTitle(
            @MethodParam(type = Type.URL, name = "title") String titleSubstring
    );


    @RestEndpoint(url = "/discipline/search/descr/{descr}", method = HttpMethod.GET, dtoType = DisciplineDto.class, action = "searchAllByDescription")
    public abstract List<DisciplineDto> searchAllDisciplinesByDescription(
            @MethodParam(type = Type.URL, name = "descr") String descriptionSubstring
    );

}
