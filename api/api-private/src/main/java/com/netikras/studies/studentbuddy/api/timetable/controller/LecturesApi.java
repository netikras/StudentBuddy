package com.netikras.studies.studentbuddy.api.timetable.controller;


import com.netikras.studies.studentbuddy.core.data.api.dto.school.LectureDto;
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

@RestApiTemplate(baseUrlPrefix = API_URL, baseUrl = "/lectures")
@GenerateCrud(dtoType = LectureDto.class, extend = {PURGE})
@RestApiLocation(producer = "../api-private", consumer = "../api-public", constants = "../api-public")
public abstract class LecturesApi {

    /**
     * @param groupId   Students' group ID, e.g. PIN13
     * @param timeUnits Time units (ISO): M(onths), d(ays), H(ours), m(inutes), s(econds)
     * @param value     Time value
     */

    @RestEndpoint(url = "/course/id/{id}/starts/in/{timeUnits}/{value}",
            method = HttpMethod.GET, dtoType = LectureDto.class, action = "getAllByCourseIdStartingIn")
    public abstract List<LectureDto> getLecturesByCourseStartingIn(
            @MethodParam(type = Type.URL, name = "id") String id,
            @MethodParam(type = Type.URL, name = "timeUnits") String timeUnits,
            @MethodParam(type = Type.URL, name = "value") long value
    );

    @RestEndpoint(url = "/building/id/{id}/starts/in/{timeUnits}/{value}",
            method = HttpMethod.GET, dtoType = LectureDto.class, action = "getAllByBuildingIdStartingIn")
    public abstract List<LectureDto> getLecturesByBuildingStartingIn(
            @MethodParam(type = Type.URL, name = "id") String id,
            @MethodParam(type = Type.URL, name = "timeUnits") String timeUnits,
            @MethodParam(type = Type.URL, name = "value") long value
    );

    @RestEndpoint(url = "/building/section/id/{id}/starts/in/{timeUnits}/{value}",
            method = HttpMethod.GET, dtoType = LectureDto.class, action = "getAllBySectionIdStartingIn")
    public abstract List<LectureDto> getLecturesBySectionStartingIn(
            @MethodParam(type = Type.URL, name = "id") String id,
            @MethodParam(type = Type.URL, name = "timeUnits") String timeUnits,
            @MethodParam(type = Type.URL, name = "value") long value
    );

    @RestEndpoint(url = "/floor/id/{id}/starts/in/{timeUnits}/{value}",
            method = HttpMethod.GET, dtoType = LectureDto.class, action = "getAllByFloorIdStartingIn")
    public abstract List<LectureDto> getLecturesByFloorStartingIn(
            @MethodParam(type = Type.URL, name = "id") String id,
            @MethodParam(type = Type.URL, name = "timeUnits") String timeUnits,
            @MethodParam(type = Type.URL, name = "value") long value
    );

    @RestEndpoint(url = "/group/id/{groupId}/starts/in/{timeUnits}/{value}",
            method = HttpMethod.GET, dtoType = LectureDto.class, action = "getAllByGroupIdStartingIn")
    public abstract List<LectureDto> getLecturesByGroupStartingIn(
            @MethodParam(type = Type.URL, name = "groupId") String groupId,
            @MethodParam(type = Type.URL, name = "timeUnits") String timeUnits,
            @MethodParam(type = Type.URL, name = "value") long value
    );


    @RestEndpoint(url = "/student/id/{studentId}/starts/in/{timeUnits}/{value}",
            method = HttpMethod.GET, dtoType = LectureDto.class, action = "getAllByStudentIdStartingIn")
    public abstract List<LectureDto> getLecturesByStudentStartingIn(
            @MethodParam(type = Type.URL, name = "studentId") String studentId,
            @MethodParam(type = Type.URL, name = "timeUnits") String timeUnits,
            @MethodParam(type = Type.URL, name = "value") long value
    );


    @RestEndpoint(url = "/lecturer/id/{lecturerId}/starts/in/{timeUnits}/{value}",
            method = HttpMethod.GET, dtoType = LectureDto.class, action = "getAllByLecturerIdStartingIn")
    public abstract List<LectureDto> getLecturesByLecturerStartingIn(
            @MethodParam(type = Type.URL, name = "lecturerId") String lecturerId,
            @MethodParam(type = Type.URL, name = "timeUnits") String timeUnits,
            @MethodParam(type = Type.URL, name = "value") long value
    );


    @RestEndpoint(url = "/room/id/{roomId}/starts/in/{timeUnits}/{value}",
            method = HttpMethod.GET, dtoType = LectureDto.class, action = "getAllByRoomIdStartingIn")
    public abstract List<LectureDto> getLecturesByRoomStartingIn(
            @MethodParam(type = Type.URL, name = "roomId") String roomId,
            @MethodParam(type = Type.URL, name = "timeUnits") String timeUnits,
            @MethodParam(type = Type.URL, name = "value") long value
    );



    @RestEndpoint(url = "/course/id/{id}/starts/between/{after}/{before}",
            method = HttpMethod.GET, dtoType = LectureDto.class, action = "getAllByCourseIdStartingBetween")
    public abstract List<LectureDto> getLecturesByCourseStartingBetween(
            @MethodParam(type = Type.URL, name = "id") String id,
            @MethodParam(type = Type.URL, name = "after") long afterTimestamp,
            @MethodParam(type = Type.URL, name = "before") long beforeTimestamp
    );

    @RestEndpoint(url = "/building/id/{id}/starts/between/{after}/{before}",
            method = HttpMethod.GET, dtoType = LectureDto.class, action = "getAllByBuildingIdStartingBetween")
    public abstract List<LectureDto> getLecturesByBuildingStartingBetween(
            @MethodParam(type = Type.URL, name = "id") String id,
            @MethodParam(type = Type.URL, name = "after") long afterTimestamp,
            @MethodParam(type = Type.URL, name = "before") long beforeTimestamp
    );

    @RestEndpoint(url = "/building/section/id/{id}/starts/between/{after}/{before}",
            method = HttpMethod.GET, dtoType = LectureDto.class, action = "getAllBySectionIdStartingBetween")
    public abstract List<LectureDto> getLecturesBySectionStartingBetween(
            @MethodParam(type = Type.URL, name = "id") String id,
            @MethodParam(type = Type.URL, name = "after") long afterTimestamp,
            @MethodParam(type = Type.URL, name = "before") long beforeTimestamp
    );

    @RestEndpoint(url = "/floor/id/{id}/starts/between/{after}/{before}",
            method = HttpMethod.GET, dtoType = LectureDto.class, action = "getAllByFloorIdStartingBetween")
    public abstract List<LectureDto> getLecturesByFloorStartingBetween(
            @MethodParam(type = Type.URL, name = "id") String id,
            @MethodParam(type = Type.URL, name = "after") long afterTimestamp,
            @MethodParam(type = Type.URL, name = "before") long beforeTimestamp
    );

    @RestEndpoint(url = "/group/id/{groupId}/starts/between/{after}/{before}",
            method = HttpMethod.GET, dtoType = LectureDto.class, action = "getAllByGroupIdStartingBetween")
    public abstract List<LectureDto> getLecturesByGroupStartingBetween(
            @MethodParam(type = Type.URL, name = "groupId") String groupId,
            @MethodParam(type = Type.URL, name = "after") long afterTimestamp,
            @MethodParam(type = Type.URL, name = "before") long beforeTimestamp
    );


    @RestEndpoint(url = "/student/id/{studentId}/starts/between/{after}/{before}",
            method = HttpMethod.GET, dtoType = LectureDto.class, action = "getAllByStudentIdStartingBetween")
    public abstract List<LectureDto> getLecturesByStudentStartingBetween(
            @MethodParam(type = Type.URL, name = "studentId") String studentId,
            @MethodParam(type = Type.URL, name = "after") long afterTimestamp,
            @MethodParam(type = Type.URL, name = "before") long beforeTimestamp
    );


    @RestEndpoint(url = "/lecturer/id/{lecturerId}/starts/between/{after}/{before}",
            method = HttpMethod.GET, dtoType = LectureDto.class, action = "getAllByLecturerIdStartingBetween")
    public abstract List<LectureDto> getLecturesByLecturerStartingBetween(
            @MethodParam(type = Type.URL, name = "lecturerId") String lecturerId,
            @MethodParam(type = Type.URL, name = "after") long afterTimestamp,
            @MethodParam(type = Type.URL, name = "before") long beforeTimestamp
    );


    @RestEndpoint(url = "/room/id/{roomId}/starts/between/{after}/{before}",
            method = HttpMethod.GET, dtoType = LectureDto.class, action = "getAllByRoomIdStartingBetween")
    public abstract List<LectureDto> getLecturesByRoomStartingBetween(
            @MethodParam(type = Type.URL, name = "roomId") String roomId,
            @MethodParam(type = Type.URL, name = "after") long afterTimestamp,
            @MethodParam(type = Type.URL, name = "before") long beforeTimestamp
    );

}
