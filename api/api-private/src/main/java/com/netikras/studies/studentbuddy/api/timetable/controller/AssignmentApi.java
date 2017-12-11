package com.netikras.studies.studentbuddy.api.timetable.controller;

import com.netikras.studies.studentbuddy.core.data.api.dto.school.AssignmentDto;
import com.netikras.tools.common.remote.http.HttpRequest.HttpMethod;
import com.netikras.tools.common.remote.http.rest.auto.ExtendedMethod;
import com.netikras.tools.common.remote.http.rest.auto.annotations.GenerateCrud;
import com.netikras.tools.common.remote.http.rest.auto.annotations.MethodParam;
import com.netikras.tools.common.remote.http.rest.auto.annotations.RestApiLocation;
import com.netikras.tools.common.remote.http.rest.auto.annotations.RestApiTemplate;
import com.netikras.tools.common.remote.http.rest.auto.annotations.RestEndpoint;
import com.netikras.tools.common.remote.http.rest.auto.generator.Param.Type;

import java.util.List;

import static com.netikras.studies.studentbuddy.api.config.Initializer.API_URL;
import static com.netikras.tools.common.remote.http.rest.auto.ExtendedMethod.PURGE;

@RestApiTemplate(baseUrlPrefix = API_URL, baseUrl = "/assignment", cruds = {
        @GenerateCrud(dtoType = AssignmentDto.class, extend = {PURGE})
})
@RestApiLocation(producer = "../api-private", consumer = "../api-public", constants = "../api-public")
public abstract class AssignmentApi {


    @RestEndpoint(url = "/all/discipline/id/{disciplineId}/due/between/{after}/{before}",
            method = HttpMethod.GET, dtoType = AssignmentDto.class, action = "getAllByDisciplineId")
    public abstract List<AssignmentDto> getAllForDisciplineId(
            @MethodParam(type = Type.URL, name = "disciplineId") String disciplineId,
            @MethodParam(type = Type.URL, name = "after") long after,
            @MethodParam(type = Type.URL, name = "before") long before
    );

    @RestEndpoint(url = "/all/course/id/{id}/due/between/{after}/{before}",
            method = HttpMethod.GET, dtoType = AssignmentDto.class, action = "getAllByCourseIdStartingBetween")
    public abstract List<AssignmentDto> getAllForCourseIdStartingBetween(
            @MethodParam(type = Type.URL, name = "id") String id,
            @MethodParam(type = Type.URL, name = "after") long after,
            @MethodParam(type = Type.URL, name = "before") long before
    );


    @RestEndpoint(url = "/all/group/id/{groupId}/due/between/{after}/{before}",
            method = HttpMethod.GET, dtoType = AssignmentDto.class, action = "getAllByGroupId")
    public abstract List<AssignmentDto> getAllForGroupId(
            @MethodParam(type = Type.URL, name = "groupId") String groupId,
            @MethodParam(type = Type.URL, name = "after") long after,
            @MethodParam(type = Type.URL, name = "before") long before
    );


    @RestEndpoint(url = "/all/student/id/{studentId}/due/between/{after}/{before}",
            method = HttpMethod.GET, dtoType = AssignmentDto.class, action = "getAllByStudentId")
    public abstract List<AssignmentDto> getAllForStudentId(
            @MethodParam(type = Type.URL, name = "studentId") String studentId,
            @MethodParam(type = Type.URL, name = "after") long after,
            @MethodParam(type = Type.URL, name = "before") long before
    );


    @RestEndpoint(url = "/all/discipline/id/{disciplineId}/group/id/{groupId}/due/between/{after}/{before}",
            method = HttpMethod.GET, dtoType = AssignmentDto.class, action = "getAllByDisciplineIdAndGroupId")
    public abstract List<AssignmentDto> getAllForDisciplineIdAndGroupId(
            @MethodParam(type = Type.URL, name = "disciplineId") String disciplineId,
            @MethodParam(type = Type.URL, name = "groupId") String groupId,
            @MethodParam(type = Type.URL, name = "after") long after,
            @MethodParam(type = Type.URL, name = "before") long before
    );


    @RestEndpoint(url = "/all/discipline/id/{disciplineId}/student/id/{studentId}/due/between/{after}/{before}",
            method = HttpMethod.GET, dtoType = AssignmentDto.class, action = "getAllByDisciplineIdAndStudentId")
    public abstract List<AssignmentDto> getAllForDisciplineIdAndStudentId(
            @MethodParam(type = Type.URL, name = "disciplineId") String disciplineId,
            @MethodParam(type = Type.URL, name = "studentId") String studentId,
            @MethodParam(type = Type.URL, name = "after") long after,
            @MethodParam(type = Type.URL, name = "before") long before
    );


    @RestEndpoint(url = "/all/lecture/id/{lectureId}", method = HttpMethod.GET, dtoType = AssignmentDto.class, action = "getAllByLectureId")
    public abstract List<AssignmentDto> getAllForLectureId(
            @MethodParam(type = Type.URL, name = "lectureId") String lectureId
    );

    @RestEndpoint(url = "/all/course/id/{id}", method = HttpMethod.GET, dtoType = AssignmentDto.class, action = "getAllByCourseId")
    public abstract List<AssignmentDto> getAllForCourseId(
            @MethodParam(type = Type.URL, name = "id") String id
    );


    @RestEndpoint(url = "/lecture/id/{lectureId}", method = HttpMethod.POST, dtoType = AssignmentDto.class)
    public abstract AssignmentDto createNew(
            @MethodParam(type = Type.URL, name = "lectureId") String dueLectureId,
            @MethodParam(type = Type.REQUEST, name = "description", required = true) String description
    );


}
