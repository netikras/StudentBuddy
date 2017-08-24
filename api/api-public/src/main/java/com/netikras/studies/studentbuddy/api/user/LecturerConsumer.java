package com.netikras.studies.studentbuddy.api.user;

import com.fasterxml.jackson.core.type.TypeReference;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.LecturerDto;
import com.netikras.tools.common.remote.http.GenericRestConsumer;
import com.netikras.tools.common.remote.http.HttpRequest;

import java.util.List;

import static com.netikras.studies.studentbuddy.api.constants.LecturerConstants.endpointGetAllByDisciplineId;
import static com.netikras.studies.studentbuddy.api.constants.LecturerConstants.endpointGetById;
import static com.netikras.studies.studentbuddy.api.constants.LecturerConstants.endpointGetByPersonId;
import static com.netikras.studies.studentbuddy.api.constants.LecturerConstants.endpointSearchAllByDegree;
import static com.netikras.studies.studentbuddy.api.constants.LecturerConstants.endpointSearchAllByFirstName;
import static com.netikras.studies.studentbuddy.api.constants.LecturerConstants.endpointSearchAllByLastName;
import static com.netikras.studies.studentbuddy.api.constants.LecturerConstants.endpointUpdate;

@SuppressWarnings({"unchecked", "UnnecessaryLocalVariable"})
public class LecturerConsumer extends GenericRestConsumer {

    private TypeReference lecturersListTypeRef = new TypeReference<List<LecturerDto>>() {};

    public LecturerDto getById(String id) {
        HttpRequest<LecturerDto> request = createRequest(endpointGetById())
                .withExpectedType(LecturerDto.class)
                .setUrlProperty("id", id);

        LecturerDto lecturerDto = (LecturerDto) sendRequest(request);
        return lecturerDto;
    }

    public List<LecturerDto> getAllByDisciplineId(String id) {
        HttpRequest<LecturerDto> request = createRequest(endpointGetAllByDisciplineId())
                .withTypeReference(lecturersListTypeRef)
                .setUrlProperty("id", id);

        List<LecturerDto> lectures = (List<LecturerDto>) sendRequest(request);
        return lectures;
    }

    public LecturerDto getByPersonId(String id) {
        HttpRequest<LecturerDto> request = createRequest(endpointGetByPersonId())
                .withExpectedType(LecturerDto.class)
                .setUrlProperty("id", id);

        LecturerDto lecturerDto = (LecturerDto) sendRequest(request);
        return lecturerDto;
    }


    public LecturerDto updateLecturer(LecturerDto lecturerDto) {
        HttpRequest<LecturerDto> request = createRequest(endpointUpdate())
                .withExpectedType(LecturerDto.class)
                .setObject(lecturerDto);

        LecturerDto dto = (LecturerDto) sendRequest(request);
        return dto;
    }


    public List<LecturerDto> searchAllByDegree(String degree) {
        HttpRequest<LecturerDto> request = createRequest(endpointSearchAllByDegree())
                .withTypeReference(lecturersListTypeRef)
                .setUrlProperty("degree", degree);

        List<LecturerDto> lectures = (List<LecturerDto>) sendRequest(request);
        return lectures;
    }

    public List<LecturerDto> searchAllByFirstName(String firstName) {
        HttpRequest<LecturerDto> request = createRequest(endpointSearchAllByFirstName())
                .withTypeReference(lecturersListTypeRef)
                .setUrlProperty("fname", firstName);

        List<LecturerDto> lectures = (List<LecturerDto>) sendRequest(request);
        return lectures;
    }

    public List<LecturerDto> searchAllByLastName(String lastName) {
        HttpRequest<LecturerDto> request = createRequest(endpointSearchAllByLastName())
                .withTypeReference(lecturersListTypeRef)
                .setUrlProperty("lname", lastName);

        List<LecturerDto> lectures = (List<LecturerDto>) sendRequest(request);
        return lectures;
    }

}
