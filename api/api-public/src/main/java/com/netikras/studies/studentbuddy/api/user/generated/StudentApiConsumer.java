package com.netikras.studies.studentbuddy.api.user.generated;

import java.util.List;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.StudentDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.StudentsGroupDto;
import com.netikras.studies.studentbuddy.api.user.generated.StudentApiConstants;
import com.netikras.tools.common.remote.http.GenericRestConsumer;
import java.lang.String;
import com.fasterxml.jackson.core.type.TypeReference;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.LectureGuestDto;
import com.netikras.tools.common.remote.http.HttpRequest;
import com.netikras.tools.common.remote.http.impl.json.HttpResponseJsonImpl;


/**
 * This class is generated automatically with Rest API preprocessor (netikras' commons).<br/>
 * It is not recommended to modify this file as it will be overwritten on next compile.<br/>
 * In case one needs to modify this class either modify its template or override its methods.
 */

public class StudentApiConsumer extends GenericRestConsumer {

    public StudentDto retrieveStudentDto(
                        String id) {
        HttpRequest request = createRequest(StudentApiConstants.endpointRetrieveStudentDto())
            .withExpectedType(StudentDto.class)
            
            .setUrlProperty("id", id);

        StudentDto result = (StudentDto) sendRequest(request);
        return result;
    }

    public StudentDto updateStudentDto(
                        StudentDto item) {
        HttpRequest<StudentDto> request = createRequest(StudentApiConstants.endpointUpdateStudentDto())
            .withExpectedType(StudentDto.class)
            
            .setObject(item);

        StudentDto result = (StudentDto) sendRequest(request);
        return result;
    }

    public StudentsGroupDto retrieveStudentsGroupDto(
                        String id) {
        HttpRequest request = createRequest(StudentApiConstants.endpointRetrieveStudentsGroupDto())
            .withExpectedType(StudentsGroupDto.class)
            
            .setUrlProperty("id", id);

        StudentsGroupDto result = (StudentsGroupDto) sendRequest(request);
        return result;
    }

    public LectureGuestDto retrieveLectureGuestDto(
                        String id) {
        HttpRequest request = createRequest(StudentApiConstants.endpointRetrieveLectureGuestDto())
            .withExpectedType(LectureGuestDto.class)
            
            .setUrlProperty("id", id);

        LectureGuestDto result = (LectureGuestDto) sendRequest(request);
        return result;
    }

    public LectureGuestDto updateLectureGuestDto(
                        LectureGuestDto item) {
        HttpRequest<LectureGuestDto> request = createRequest(StudentApiConstants.endpointUpdateLectureGuestDto())
            .withExpectedType(LectureGuestDto.class)
            
            .setObject(item);

        LectureGuestDto result = (LectureGuestDto) sendRequest(request);
        return result;
    }

    public StudentDto getByPersonIdStudentDto(
                        String id) {
        HttpRequest request = createRequest(StudentApiConstants.endpointGetByPersonIdStudentDto())
            .withExpectedType(StudentDto.class)
            
            .setUrlProperty("id", id);

        StudentDto result = (StudentDto) sendRequest(request);
        return result;
    }

    public StudentsGroupDto getByTitleStudentsGroupDto(
                        String title) {
        HttpRequest request = createRequest(StudentApiConstants.endpointGetByTitleStudentsGroupDto())
            .withExpectedType(StudentsGroupDto.class)
            
            .setUrlProperty("title", title);

        StudentsGroupDto result = (StudentsGroupDto) sendRequest(request);
        return result;
    }

    public List<StudentsGroupDto> getAllStudentsGroupDto() {
        HttpRequest request = createRequest(StudentApiConstants.endpointGetAllStudentsGroupDto())
            .withTypeReference(new TypeReference<List<StudentsGroupDto>>() {})
            ;

        List<StudentsGroupDto> result = (List<StudentsGroupDto>) sendRequest(request);
        return result;
    }

    public List<StudentDto> getAllByGroupStudentDto(
                        String id) {
        HttpRequest request = createRequest(StudentApiConstants.endpointGetAllByGroupStudentDto())
            .withTypeReference(new TypeReference<List<StudentDto>>() {})
            
            .setUrlProperty("id", id);

        List<StudentDto> result = (List<StudentDto>) sendRequest(request);
        return result;
    }

    public List<StudentDto> searchAllByFirstNameStudentDto(
                        String fname) {
        HttpRequest request = createRequest(StudentApiConstants.endpointSearchAllByFirstNameStudentDto())
            .withTypeReference(new TypeReference<List<StudentDto>>() {})
            
            .setUrlProperty("fname", fname);

        List<StudentDto> result = (List<StudentDto>) sendRequest(request);
        return result;
    }

    public List<StudentDto> searchAllByLastNameStudentDto(
                        String lname) {
        HttpRequest request = createRequest(StudentApiConstants.endpointSearchAllByLastNameStudentDto())
            .withTypeReference(new TypeReference<List<StudentDto>>() {})
            
            .setUrlProperty("lname", lname);

        List<StudentDto> result = (List<StudentDto>) sendRequest(request);
        return result;
    }

    public List<StudentsGroupDto> searchAllByTitleStudentsGroupDto(
                        String title) {
        HttpRequest request = createRequest(StudentApiConstants.endpointSearchAllByTitleStudentsGroupDto())
            .withTypeReference(new TypeReference<List<StudentsGroupDto>>() {})
            
            .setUrlProperty("title", title);

        List<StudentsGroupDto> result = (List<StudentsGroupDto>) sendRequest(request);
        return result;
    }

    public List<LectureGuestDto> searchByLastNameLectureGuestDto(
                        String lname) {
        HttpRequest request = createRequest(StudentApiConstants.endpointSearchByLastNameLectureGuestDto())
            .withTypeReference(new TypeReference<List<LectureGuestDto>>() {})
            
            .setUrlProperty("lname", lname);

        List<LectureGuestDto> result = (List<LectureGuestDto>) sendRequest(request);
        return result;
    }

    public List<LectureGuestDto> searchByFirstNameLectureGuestDto(
                        String fname) {
        HttpRequest request = createRequest(StudentApiConstants.endpointSearchByFirstNameLectureGuestDto())
            .withTypeReference(new TypeReference<List<LectureGuestDto>>() {})
            
            .setUrlProperty("fname", fname);

        List<LectureGuestDto> result = (List<LectureGuestDto>) sendRequest(request);
        return result;
    }


}
