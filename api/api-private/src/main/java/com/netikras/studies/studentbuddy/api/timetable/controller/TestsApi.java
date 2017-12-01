package com.netikras.studies.studentbuddy.api.timetable.controller;


import com.netikras.studies.studentbuddy.core.data.api.dto.school.DisciplineTestDto;
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

@RestApiTemplate(baseUrlPrefix = API_URL, baseUrl = "/test", cruds = {
        @GenerateCrud(dtoType = DisciplineTestDto.class, extend = PURGE)
})
@RestApiLocation(producer = "../api-private", consumer = "../api-public", constants = "../api-public")
public abstract class TestsApi {

    @RestEndpoint(url = "/lecture/id/{id}", method = HttpMethod.POST, dtoType = DisciplineTestDto.class, action = "createNew")
    public abstract DisciplineTestDto createTest(
            @MethodParam(type = Type.URL, name = "id") String dueLectureId,
            @MethodParam(type = Type.BODY) String description
    );


    @RestEndpoint(url = "/all/discipline/id/{id}/timeframe/{after}/{before}",
            method = HttpMethod.GET, dtoType = DisciplineTestDto.class, action = "getAllForDisciplineInTimeframe")
    public abstract List<DisciplineTestDto> getForDiscipline(
            @MethodParam(type = Type.URL, name = "id") String disciplineId,
            @MethodParam(type = Type.URL, name = "after") long afterTimestamp,
            @MethodParam(type = Type.URL, name = "before") long beforeTimestamp
    );

    @RestEndpoint(url = "/all/discipline/id/{id}", method = HttpMethod.GET, dtoType = DisciplineTestDto.class, action = "getAllForDiscipline")
    public abstract List<DisciplineTestDto> getForDiscipline(
            @MethodParam(type = Type.URL, name = "id") String disciplineId
    );

    @RestEndpoint(url = "/all/course/id/{id}", method = HttpMethod.GET, dtoType = DisciplineTestDto.class, action = "getAllForCourse")
    public abstract List<DisciplineTestDto> getForCourse(
            @MethodParam(type = Type.URL, name = "id") String id
    );

    @RestEndpoint(url = "/all/course/id/{id}/timeframe/{after}/{before}", method = HttpMethod.GET, dtoType = DisciplineTestDto.class, action = "getAllForCourseInTimeframe")
    public abstract List<DisciplineTestDto> getForCourseInTimeframe(
            @MethodParam(type = Type.URL, name = "id") String id,
            @MethodParam(type = Type.URL, name = "after") long afterTimestamp,
            @MethodParam(type = Type.URL, name = "before") long beforeTimestamp
    );


    @RestEndpoint(url = "/all/discipline/id/{disciplineId}/group/id/{groupId}/timeframe/{after}/{before}",
            method = HttpMethod.GET, dtoType = DisciplineTestDto.class, action = "getAllForGroupInTimeframe")
    public abstract List<DisciplineTestDto> getAllForDisciplineAndGroup(
            @MethodParam(type = Type.URL, name = "disciplineId") String disciplineId,
            @MethodParam(type = Type.URL, name = "groupId") String groupId,
            @MethodParam(type = Type.URL, name = "after") long afterTimestamp,
            @MethodParam(type = Type.URL, name = "before") long beforeTimestamp
    );



    @RestEndpoint(url = "/all/discipline/id/{disciplineId}/group/id/{groupId}",
            method = HttpMethod.GET, dtoType = DisciplineTestDto.class, action = "getAllForGroup")
    public abstract List<DisciplineTestDto> getForDiscipline(
            @MethodParam(type = Type.URL, name = "disciplineId") String disciplineId,
            @MethodParam(type = Type.URL, name = "groupId") String groupId
    );


    @RestEndpoint(url = "/search/description/{descr}",
            method = HttpMethod.GET, dtoType = DisciplineTestDto.class, action = "searchAllByDescription")
    public abstract List<DisciplineTestDto> searchAllTestsByDescription(
            @MethodParam(type = Type.URL, name = "descr") String descriptionSubstring
    );

}
