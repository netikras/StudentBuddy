package com.netikras.studies.studentbuddy.api.user.mgmt;

import com.netikras.studies.studentbuddy.core.data.api.dto.school.LecturerDto;
import com.netikras.tools.common.remote.http.GenericRestConsumer;
import com.netikras.tools.common.remote.http.HttpRequest;
import com.netikras.tools.common.remote.http.impl.json.HttpResponseJsonImpl;

import static com.netikras.studies.studentbuddy.api.constants.AdminLecturerConstants.endpointAssignToDiscipline;
import static com.netikras.studies.studentbuddy.api.constants.AdminLecturerConstants.endpointCreateNew;
import static com.netikras.studies.studentbuddy.api.constants.AdminLecturerConstants.endpointDeleteById;
import static com.netikras.studies.studentbuddy.api.constants.AdminLecturerConstants.endpointUnassignFromDiscipline;

@SuppressWarnings({"unchecked", "UnnecessaryLocalVariable"})
public class AdminLecturerConsumer extends GenericRestConsumer {

    public LecturerDto createLecturer(LecturerDto lecturerDto) {
        HttpRequest<LecturerDto> request = createRequest(endpointCreateNew())
                .withExpectedType(LecturerDto.class)
                .setObject(lecturerDto);

        LecturerDto dto = (LecturerDto) sendRequest(request);
        return dto;
    }

    public boolean deleteById(String id) {
        HttpRequest<LecturerDto> request = createRequest(endpointDeleteById())
                .withExpectedType(LecturerDto.class)
                .setUrlProperty("id", id);

        HttpResponseJsonImpl responseJson = new HttpResponseJsonImpl(LecturerDto.class);
        sendRequest(request, responseJson);

        return responseJson.isResponseSuccess();
    }

    public boolean assignLecturerToDiscipline(String lecturerId, String disciplineId) {
        HttpRequest<LecturerDto> request = createRequest(endpointAssignToDiscipline())
                .withExpectedType(LecturerDto.class)
                .setUrlProperty("lecturerId", lecturerId)
                .setUrlProperty("disciplineId", disciplineId);

        HttpResponseJsonImpl responseJson = new HttpResponseJsonImpl(LecturerDto.class);
        sendRequest(request, responseJson);

        return responseJson.isResponseSuccess();
    }

    public boolean unassignLecturerFromDiscipline(String lecturerId, String disciplineId) {
        HttpRequest<LecturerDto> request = createRequest(endpointUnassignFromDiscipline())
                .withExpectedType(LecturerDto.class)
                .setUrlProperty("lecturerId", lecturerId)
                .setUrlProperty("disciplineId", disciplineId);

        HttpResponseJsonImpl responseJson = new HttpResponseJsonImpl(LecturerDto.class);
        sendRequest(request, responseJson);

        return responseJson.isResponseSuccess();
    }

}
