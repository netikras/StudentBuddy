package com.netikras.studies.studentbuddy.api.timetable;

import com.fasterxml.jackson.core.type.TypeReference;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.DisciplineTestDto;
import com.netikras.tools.common.remote.http.GenericRestConsumer;
import com.netikras.tools.common.remote.http.HttpRequest;
import com.netikras.tools.common.remote.http.impl.json.HttpResponseJsonImpl;


import java.util.Date;
import java.util.List;

import static com.netikras.studies.studentbuddy.api.constants.TestsConstants.endpointCreateTest;
import static com.netikras.studies.studentbuddy.api.constants.TestsConstants.endpointCreateTestByLectureId;
import static com.netikras.studies.studentbuddy.api.constants.TestsConstants.endpointDeleteTestById;
import static com.netikras.studies.studentbuddy.api.constants.TestsConstants.endpointGetAllTestsByDisciplineId;
import static com.netikras.studies.studentbuddy.api.constants.TestsConstants.endpointGetAllTestsByDisciplineIdAndGroupId;
import static com.netikras.studies.studentbuddy.api.constants.TestsConstants.endpointGetAllTestsByDisciplineIdAndGroupIdStartingBetween;
import static com.netikras.studies.studentbuddy.api.constants.TestsConstants.endpointGetAllTestsByDisciplineIdStartingBetween;
import static com.netikras.studies.studentbuddy.api.constants.TestsConstants.endpointGetTestById;
import static com.netikras.studies.studentbuddy.api.constants.TestsConstants.endpointUpdate;

public class TestsConsumer extends GenericRestConsumer {


    private TypeReference testsListReference = new TypeReference<List<DisciplineTestDto>>(){};

    public DisciplineTestDto createTest(DisciplineTestDto testDto) {
        HttpRequest<DisciplineTestDto> request = createRequest(endpointCreateTest())
                .withExpectedType(DisciplineTestDto.class)
                .setObject(testDto);
        DisciplineTestDto dto = (DisciplineTestDto) sendRequest(request);
        return dto;
    }


    public DisciplineTestDto createTest(String lectureId, String description) {
        HttpRequest<String> request = createRequest(endpointCreateTestByLectureId())
                .withExpectedType(DisciplineTestDto.class)
                .setUrlProperty("id", lectureId)
                .setObject(description);
        DisciplineTestDto dto = (DisciplineTestDto) sendRequest(request);
        return dto;
    }

    public DisciplineTestDto updateTest(DisciplineTestDto testDto) {
        HttpRequest<DisciplineTestDto> request = createRequest(endpointUpdate())
                .withExpectedType(DisciplineTestDto.class)
                .setObject(testDto);
        DisciplineTestDto dto = (DisciplineTestDto) sendRequest(request);
        return dto;
    }

    public DisciplineTestDto getTestById(String id) {
        HttpRequest request = createRequest(endpointGetTestById())
                .withExpectedType(DisciplineTestDto.class)
                .setUrlProperty("id", id);
        DisciplineTestDto dto = (DisciplineTestDto) sendRequest(request);
        return dto;
    }

    public boolean deleteTestById(String id) {
        HttpRequest request = createRequest(endpointDeleteTestById())
                .setUrlProperty("id", id);
        HttpResponseJsonImpl responseJson = new HttpResponseJsonImpl();
        sendRequest(request, responseJson);

        return responseJson.isResponseSuccess();
    }

    public List<DisciplineTestDto> getAllTestsByDisciplineId(String id) {
        HttpRequest request = createRequest(endpointGetAllTestsByDisciplineId())
                .withTypeReference(testsListReference)
                .setUrlProperty("id", id);

        List<DisciplineTestDto> testDtos = (List<DisciplineTestDto>) sendRequest(request);
        return testDtos;
    }

    public List<DisciplineTestDto> getAllTestsByDisciplineIdStartingBetween(String id, Date startsAfter, Date startsBefore) {
        HttpRequest request = createRequest(endpointGetAllTestsByDisciplineIdStartingBetween())
                .withTypeReference(testsListReference)
                .setUrlProperty("id", id)
                .setUrlProperty("after", startsAfter.getTime())
                .setUrlProperty("before", startsBefore.getTime());

        List<DisciplineTestDto> testDtos = (List<DisciplineTestDto>) sendRequest(request);
        return testDtos;
    }

    public List<DisciplineTestDto> getAllTestsByDisciplineIdAndGroupId(String disciplineId, String groupId) {
        HttpRequest request = createRequest(endpointGetAllTestsByDisciplineIdAndGroupId())
                .withTypeReference(testsListReference)
                .setUrlProperty("disciplineId", disciplineId)
                .setUrlProperty("groupId", groupId);

        List<DisciplineTestDto> testDtos = (List<DisciplineTestDto>) sendRequest(request);
        return testDtos;
    }

    public List<DisciplineTestDto> getAllTestsByDisciplineIdAndGroupIdStartingBetween(String disciplineId, String groupId,
                                                                              Date startsAfter, Date startsBefore) {
        HttpRequest request = createRequest(endpointGetAllTestsByDisciplineIdAndGroupIdStartingBetween())
                .withTypeReference(testsListReference)
                .setUrlProperty("disciplineId", disciplineId)
                .setUrlProperty("groupId", groupId)
                .setUrlProperty("after", startsAfter.getTime())
                .setUrlProperty("before", startsBefore.getTime());

        List<DisciplineTestDto> testDtos = (List<DisciplineTestDto>) sendRequest(request);
        return testDtos;
    }

}
