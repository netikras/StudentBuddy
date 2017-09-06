package com.netikras.studies.studentbuddy.api.user.mgmt;

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

@RestApiTemplate(baseUrlPrefix = API_URL, baseUrl = "/mgmt/student", cruds = {
        @GenerateCrud(dtoType = StudentDto.class, methods = {CrudMethod.CREATE, CrudMethod.DELETE}),
        @GenerateCrud(dtoType = StudentsGroupDto.class, methods = {CrudMethod.CREATE, CrudMethod.DELETE}, url = "/group"),
        @GenerateCrud(dtoType = LectureGuestDto.class, methods = {CrudMethod.CREATE, CrudMethod.DELETE}, url = "/guest")
})
@RestApiLocation(producer = "../api-private", consumer = "../api-public", constants = "../api-public")
public abstract class AdminStudentApi {


    @RestEndpoint(url = "/add/group/{groupId}/students/{studentIds}", method = HttpMethod.PUT, dtoType = StudentDto.class)
    public abstract void addAllToGroup(
            @MethodParam(type = Type.URL, name = "groupId") String groupId,
            @MethodParam(type = Type.URL, name = "studentIds") List<String> studentIds
    );


    @RestEndpoint(url = "/group/{groupId}/student/{studentId}", method = HttpMethod.PUT, dtoType = StudentDto.class)
    public abstract void addToGroup(
            @MethodParam(type = Type.URL, name = "groupId") String groupId,
            @MethodParam(type = Type.URL, name = "studentId") String studentId
    );


    @RestEndpoint(url = "/group/{groupId}/student/{studentId}", method = HttpMethod.DELETE, dtoType = StudentDto.class)
    public abstract void removeFromGroup(
            @MethodParam(type = Type.URL, name = "groupId") String groupId,
            @MethodParam(type = Type.URL, name = "studentId") String studentId
    );


    @RestEndpoint(url = "/group/{groupId}/students/{studentId}", method = HttpMethod.DELETE, dtoType = StudentDto.class)
    public abstract void removeAllFromGroup(
            @MethodParam(type = Type.URL, name = "groupId") String groupId,
            @MethodParam(type = Type.URL, name = "studentIds") List<String> studentIds
    );


    @RestEndpoint(url = "/guest/person/id/{personId}/lecture/{lectureId}", method = HttpMethod.POST, dtoType = LectureGuestDto.class, action = "createByPersonId")
    public abstract LectureGuestDto createGuest(
            @MethodParam(type = Type.URL, name = "personId") String personId,
            @MethodParam(type = Type.URL, name = "lectureId") String lectureId
    );


    @RestEndpoint(url = "/guest/person/id2/{personId}/lecture/{lectureId}", method = HttpMethod.POST, dtoType = LectureGuestDto.class, action = "createByPersonIdentifier")
    public abstract LectureGuestDto createGuestByPersonIdentifier(
            @MethodParam(type = Type.URL, name = "personId") String personId,
            @MethodParam(type = Type.URL, name = "lectureId") String lectureId
    );




}
