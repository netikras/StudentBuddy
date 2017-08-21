package com.netikras.studies.studentbuddy.api.user;

import com.fasterxml.jackson.core.type.TypeReference;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.LectureGuestDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.StudentDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.StudentsGroupDto;
import com.netikras.tools.common.remote.http.GenericRestConsumer;
import com.netikras.tools.common.remote.http.HttpRequest;
import com.netikras.tools.common.remote.http.impl.json.HttpResponseJsonImpl;

import java.util.List;

import static com.netikras.studies.studentbuddy.api.constants.StudentConstants.endpointGetAllGroups;
import static com.netikras.studies.studentbuddy.api.constants.StudentConstants.endpointGetAllStudentsByGroupId;
import static com.netikras.studies.studentbuddy.api.constants.StudentConstants.endpointGetGroupById;
import static com.netikras.studies.studentbuddy.api.constants.StudentConstants.endpointGetGroupByTitle;
import static com.netikras.studies.studentbuddy.api.constants.StudentConstants.endpointGetGuestById;
import static com.netikras.studies.studentbuddy.api.constants.StudentConstants.endpointGetStudentById;
import static com.netikras.studies.studentbuddy.api.constants.StudentConstants.endpointGetStudentByPersonId;
import static com.netikras.studies.studentbuddy.api.constants.StudentConstants.endpointUpdateGuest;
import static com.netikras.studies.studentbuddy.api.constants.StudentConstants.endpointUpdateStudent;

@SuppressWarnings({"unchecked", "UnnecessaryLocalVariable"})
public class StudentConsumer extends GenericRestConsumer {

    public StudentDto getStudentById(String id) {
        HttpRequest request = createRequest(endpointGetStudentById())
                .withExpectedType(StudentDto.class)
                .setUrlProperty("id", id);

        StudentDto studentDto = (StudentDto) sendRequest(request);
        return studentDto;
    }

    public StudentDto getStudentByPersonId(String id) {
        HttpRequest request = createRequest(endpointGetStudentByPersonId())
                .withExpectedType(StudentDto.class)
                .setUrlProperty("id", id);

        StudentDto studentDto = (StudentDto) sendRequest(request);
        return studentDto;
    }

    public boolean updateStudent(StudentDto studentDto) {
        HttpRequest<StudentDto> request = createRequest(endpointUpdateStudent())
                .setObject(studentDto);

        HttpResponseJsonImpl responseJson = new HttpResponseJsonImpl(StudentDto.class);
        sendRequest(request, responseJson);
        return responseJson.isResponseSuccess();
    }

    public StudentsGroupDto getGroupById(String id) {
        HttpRequest request = createRequest(endpointGetGroupById())
                .withExpectedType(StudentsGroupDto.class)
                .setUrlProperty("id", id);

        StudentsGroupDto dto = (StudentsGroupDto) sendRequest(request);
        return dto;
    }

    public StudentsGroupDto getGroupByTitle(String title) {
        HttpRequest request = createRequest(endpointGetGroupByTitle())
                .withExpectedType(StudentsGroupDto.class)
                .setUrlProperty("title", title);

        StudentsGroupDto dto = (StudentsGroupDto) sendRequest(request);
        return dto;
    }

    public List<StudentsGroupDto> getAllGroups() {
        HttpRequest request = createRequest(endpointGetAllGroups())
                .withTypeReference(new TypeReference<List<StudentsGroupDto>>() {});

        List<StudentsGroupDto> dtos = (List<StudentsGroupDto>) sendRequest(request);
        return dtos;
    }

    public List<StudentDto> getAllStudentsByGroupId(String groupId) {
        HttpRequest request = createRequest(endpointGetAllStudentsByGroupId())
                .withTypeReference(new TypeReference<List<StudentDto>>() {})
                .setUrlProperty("id", groupId);

        List<StudentDto> dtos = (List<StudentDto>) sendRequest(request);
        return dtos;
    }

    public LectureGuestDto getGuestById(String id) {
        HttpRequest request = createRequest(endpointGetGuestById())
                .withExpectedType(LectureGuestDto.class)
                .setUrlProperty("id", id);

        LectureGuestDto dto = (LectureGuestDto) sendRequest(request);
        return dto;
    }

    public boolean updateGuest(LectureGuestDto guestDto) {
        HttpRequest<LectureGuestDto> request = createRequest(endpointUpdateGuest())
                .setObject(guestDto);

        HttpResponseJsonImpl responseJson = new HttpResponseJsonImpl(LectureGuestDto.class);
        sendRequest(request, responseJson);
        return responseJson.isResponseSuccess();
    }

}
