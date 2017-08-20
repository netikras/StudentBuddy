package com.netikras.studies.studentbuddy.api.user.mgmt;

import com.netikras.studies.studentbuddy.core.data.api.dto.school.LectureGuestDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.StudentDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.StudentsGroupDto;
import com.netikras.tools.common.remote.http.GenericRestConsumer;
import com.netikras.tools.common.remote.http.HttpRequest;
import com.netikras.tools.common.remote.http.impl.json.HttpResponseJsonImpl;

import static com.netikras.studies.studentbuddy.api.constants.AdminStudentConstants.endpointAddAllStudentsToGroup;
import static com.netikras.studies.studentbuddy.api.constants.AdminStudentConstants.endpointAddStudentToGroup;
import static com.netikras.studies.studentbuddy.api.constants.AdminStudentConstants.endpointCreateGroup;
import static com.netikras.studies.studentbuddy.api.constants.AdminStudentConstants.endpointCreateGuest;
import static com.netikras.studies.studentbuddy.api.constants.AdminStudentConstants.endpointCreateGuestByPersonIdAndLectureId;
import static com.netikras.studies.studentbuddy.api.constants.AdminStudentConstants.endpointCreateGuestByPersonIdentifierAndLectureId;
import static com.netikras.studies.studentbuddy.api.constants.AdminStudentConstants.endpointCreateStudent;
import static com.netikras.studies.studentbuddy.api.constants.AdminStudentConstants.endpointDeleteGroupById;
import static com.netikras.studies.studentbuddy.api.constants.AdminStudentConstants.endpointDeleteGuestById;
import static com.netikras.studies.studentbuddy.api.constants.AdminStudentConstants.endpointDeleteStudentById;
import static com.netikras.studies.studentbuddy.api.constants.AdminStudentConstants.endpointRemoveAllStudentsFromGroup;
import static com.netikras.studies.studentbuddy.api.constants.AdminStudentConstants.endpointRemoveStudentFromGroup;

@SuppressWarnings({"unchecked", "UnnecessaryLocalVariable"})
public class AdminStudentConsumer extends GenericRestConsumer {


    // Student

    public StudentDto createStudent(StudentDto studentDto) {
        HttpRequest<StudentDto> request = createRequest(endpointCreateStudent())
                .withExpectedType(StudentDto.class)
                .setObject(studentDto);

        StudentDto dto = (StudentDto) sendRequest(request);
        return dto;
    }

    public boolean deleteStudentById(String id) {
        HttpRequest request = createRequest(endpointDeleteStudentById())
                .setUrlProperty("id", id);

        HttpResponseJsonImpl responseJson = new HttpResponseJsonImpl(StudentDto.class);
        sendRequest(request, responseJson);

        return responseJson.isResponseSuccess();
    }



    // Students group

    public StudentsGroupDto createStudentGroup(StudentsGroupDto groupDto) {
        HttpRequest<StudentsGroupDto> request = createRequest(endpointCreateGroup())
                .withExpectedType(StudentsGroupDto.class)
                .setObject(groupDto);

        StudentsGroupDto dto = (StudentsGroupDto) sendRequest(request);
        return dto;
    }

    public boolean deleteStudentGroupById(String id) {
        HttpRequest request = createRequest(endpointDeleteGroupById())
                .setUrlProperty("id", id);

        HttpResponseJsonImpl responseJson = new HttpResponseJsonImpl(StudentsGroupDto.class);
        sendRequest(request, responseJson);

        return responseJson.isResponseSuccess();
    }



    // Group membership

    public boolean addStudentToGroup(String groupId, String studentId) {
        HttpRequest request = createRequest(endpointAddStudentToGroup())
                .setUrlProperty("studentId", studentId)
                .setUrlProperty("groupId", groupId);

        HttpResponseJsonImpl responseJson = new HttpResponseJsonImpl(StudentsGroupDto.class);
        sendRequest(request, responseJson);

        return responseJson.isResponseSuccess();
    }

    public boolean addAllStudentsToGroup(String groupId, String ... studentIds) {
        HttpRequest request = createRequest(endpointAddAllStudentsToGroup())
                .setUrlProperty("studentId", studentIds)
                .setUrlProperty("groupId", groupId);

        HttpResponseJsonImpl responseJson = new HttpResponseJsonImpl(StudentsGroupDto.class);
        sendRequest(request, responseJson);

        return responseJson.isResponseSuccess();
    }

    public boolean removeStudentFromGroup(String groupId, String studentId) {
        HttpRequest request = createRequest(endpointRemoveStudentFromGroup())
                .setUrlProperty("studentId", studentId)
                .setUrlProperty("groupId", groupId);

        HttpResponseJsonImpl responseJson = new HttpResponseJsonImpl(StudentsGroupDto.class);
        sendRequest(request, responseJson);

        return responseJson.isResponseSuccess();
    }

    public boolean removeAllStudentsFromGroup(String groupId, String ... studentId) {
        HttpRequest request = createRequest(endpointRemoveAllStudentsFromGroup())
                .setUrlProperty("studentId", studentId)
                .setUrlProperty("groupId", groupId);

        HttpResponseJsonImpl responseJson = new HttpResponseJsonImpl(StudentsGroupDto.class);
        sendRequest(request, responseJson);

        return responseJson.isResponseSuccess();
    }



    // Lecture guest

    public LectureGuestDto createLectureGuest(LectureGuestDto guestDto) {
        HttpRequest<LectureGuestDto> request = createRequest(endpointCreateGuest())
                .withExpectedType(LectureGuestDto.class)
                .setObject(guestDto);

        LectureGuestDto dto = (LectureGuestDto) sendRequest(request);
        return dto;
    }

    public LectureGuestDto createLectureGuestByPersonIdAndLectureId(String personId, String lectureId) {
        HttpRequest<LectureGuestDto> request = createRequest(endpointCreateGuestByPersonIdAndLectureId())
                .withExpectedType(LectureGuestDto.class)
                .setUrlProperty("personId", personId)
                .setUrlProperty("lectureId", lectureId);

        LectureGuestDto dto = (LectureGuestDto) sendRequest(request);
        return dto;
    }

    public LectureGuestDto createLectureGuestByPersonIdAndLectureIdentifier(String personIdentifier, String lectureId) {
        HttpRequest<LectureGuestDto> request = createRequest(endpointCreateGuestByPersonIdentifierAndLectureId())
                .withExpectedType(LectureGuestDto.class)
                .setUrlProperty("personId", personIdentifier)
                .setUrlProperty("lectureId", lectureId);

        LectureGuestDto dto = (LectureGuestDto) sendRequest(request);
        return dto;
    }

    public boolean deleteGuestById(String id) {
        HttpRequest request = createRequest(endpointDeleteGuestById())
                .setUrlProperty("id", id);

        HttpResponseJsonImpl responseJson = new HttpResponseJsonImpl(LectureGuestDto.class);
        sendRequest(request, responseJson);

        return responseJson.isResponseSuccess();
    }
}
