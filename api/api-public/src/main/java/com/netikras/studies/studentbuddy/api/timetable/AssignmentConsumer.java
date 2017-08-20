package com.netikras.studies.studentbuddy.api.timetable;

import com.fasterxml.jackson.core.type.TypeReference;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.AssignmentDto;
import com.netikras.tools.common.remote.http.GenericRestConsumer;
import com.netikras.tools.common.remote.http.HttpRequest;
import com.netikras.tools.common.remote.http.impl.json.HttpResponseJsonImpl;

import java.util.List;

import static com.netikras.studies.studentbuddy.api.constants.AssignmentConstants.endpointCreateAssignment;
import static com.netikras.studies.studentbuddy.api.constants.AssignmentConstants.endpointCreateAssignmentByLectureId;
import static com.netikras.studies.studentbuddy.api.constants.AssignmentConstants.endpointDeleteAssignmentById;
import static com.netikras.studies.studentbuddy.api.constants.AssignmentConstants.endpointGetAllByDisciplineIdAndGroupIdDueBetween;
import static com.netikras.studies.studentbuddy.api.constants.AssignmentConstants.endpointGetAllByDisciplineIdAndStudentIdDueBetween;
import static com.netikras.studies.studentbuddy.api.constants.AssignmentConstants.endpointGetAllByDisciplineIdDueBetween;
import static com.netikras.studies.studentbuddy.api.constants.AssignmentConstants.endpointGetAllByGroupIdDueBetween;
import static com.netikras.studies.studentbuddy.api.constants.AssignmentConstants.endpointGetAllByLectureId;
import static com.netikras.studies.studentbuddy.api.constants.AssignmentConstants.endpointGetAllByStudentIdDueBetween;
import static com.netikras.studies.studentbuddy.api.constants.AssignmentConstants.endpointGetAssignmentById;
import static com.netikras.studies.studentbuddy.api.constants.AssignmentConstants.endpointUpdateAssignment;

public class AssignmentConsumer extends GenericRestConsumer {


    private TypeReference assignmentsListTypeRef = new TypeReference<List<AssignmentDto>>() {};

    public AssignmentDto create(AssignmentDto assignmentDto) {
        HttpRequest<AssignmentDto> request = createRequest(endpointCreateAssignment())
                .withExpectedType(AssignmentDto.class)
                .setObject(assignmentDto);

        AssignmentDto dto = (AssignmentDto) sendRequest(request);
        return dto;
    }

    public AssignmentDto createByLectureId(String lectureId, String description) {
        HttpRequest<String> request = createRequest(endpointCreateAssignmentByLectureId())
                .withExpectedType(AssignmentDto.class)
                .setUrlProperty("id", lectureId)
                .setObject(description);

        AssignmentDto dto = (AssignmentDto) sendRequest(request);
        return dto;
    }

    public AssignmentDto update(AssignmentDto assignmentDto) {
        HttpRequest<AssignmentDto> request = createRequest(endpointUpdateAssignment())
                .withExpectedType(AssignmentDto.class)
                .setObject(assignmentDto);

        AssignmentDto dto = (AssignmentDto) sendRequest(request);
        return dto;
    }

    public AssignmentDto getById(String id) {
        HttpRequest request = createRequest(endpointGetAssignmentById())
                .withExpectedType(AssignmentDto.class)
                .setUrlProperty("id", id);

        AssignmentDto dto = (AssignmentDto) sendRequest(request);
        return dto;
    }

    public boolean deleteById(String id) {
        HttpRequest request = createRequest(endpointDeleteAssignmentById())
                .setUrlProperty("id", id);
        HttpResponseJsonImpl responseJson = new HttpResponseJsonImpl();
        sendRequest(request, responseJson);

        return responseJson.isResponseSuccess();
    }

    public List<AssignmentDto> getAllByLectureId(String lectureId) {
        HttpRequest request = createRequest(endpointGetAllByLectureId())
                .withTypeReference(assignmentsListTypeRef)
                .setUrlProperty("lectureId", lectureId);

        List<AssignmentDto> dtos = (List<AssignmentDto>) sendRequest(request);
        return dtos;
    }

    public List<AssignmentDto> getAllByGroupIdAndDueBetween(String groupId, long afterTimestamp, long beforeTimestamp) {
        HttpRequest request = createRequest(endpointGetAllByGroupIdDueBetween())
                .withTypeReference(assignmentsListTypeRef)
                .setUrlProperty("groupId", groupId)
                .setUrlProperty("after", afterTimestamp)
                .setUrlProperty("before", beforeTimestamp);

        List<AssignmentDto> dtos = (List<AssignmentDto>) sendRequest(request);
        return dtos;
    }

    public List<AssignmentDto> getAllByDisciplineIdAndDueBetween(String disciplineId, long afterTimestamp, long beforeTimestamp) {
        HttpRequest request = createRequest(endpointGetAllByDisciplineIdDueBetween())
                .withTypeReference(assignmentsListTypeRef)
                .setUrlProperty("disciplineId", disciplineId)
                .setUrlProperty("after", afterTimestamp)
                .setUrlProperty("before", beforeTimestamp);

        List<AssignmentDto> dtos = (List<AssignmentDto>) sendRequest(request);
        return dtos;
    }

    public List<AssignmentDto> getAllByStudentIdAndDueBetween(String studentId, long afterTimestamp, long beforeTimestamp) {
        HttpRequest request = createRequest(endpointGetAllByStudentIdDueBetween())
                .withTypeReference(assignmentsListTypeRef)
                .setUrlProperty("studentId", studentId)
                .setUrlProperty("after", afterTimestamp)
                .setUrlProperty("before", beforeTimestamp);

        List<AssignmentDto> dtos = (List<AssignmentDto>) sendRequest(request);
        return dtos;
    }

    public List<AssignmentDto> getAllByDisciplineIdAndStudentIdAndDueBetween(String disciplineId, String studentId, long afterTimestamp, long beforeTimestamp) {
        HttpRequest request = createRequest(endpointGetAllByDisciplineIdAndStudentIdDueBetween())
                .withTypeReference(assignmentsListTypeRef)
                .setUrlProperty("disciplineId", disciplineId)
                .setUrlProperty("studentId", studentId)
                .setUrlProperty("after", afterTimestamp)
                .setUrlProperty("before", beforeTimestamp);

        List<AssignmentDto> dtos = (List<AssignmentDto>) sendRequest(request);
        return dtos;
    }

    public List<AssignmentDto> getAllByDisciplineIdAndGroupIdAndDueBetween(String disciplineId, String groupId, long afterTimestamp, long beforeTimestamp) {
        HttpRequest request = createRequest(endpointGetAllByDisciplineIdAndGroupIdDueBetween())
                .withTypeReference(assignmentsListTypeRef)
                .setUrlProperty("disciplineId", disciplineId)
                .setUrlProperty("groupId", groupId)
                .setUrlProperty("after", afterTimestamp)
                .setUrlProperty("before", beforeTimestamp);

        List<AssignmentDto> dtos = (List<AssignmentDto>) sendRequest(request);
        return dtos;
    }


}
