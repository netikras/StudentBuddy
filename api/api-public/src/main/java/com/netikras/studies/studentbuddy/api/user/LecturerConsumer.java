package com.netikras.studies.studentbuddy.api.user;

import com.fasterxml.jackson.core.type.TypeReference;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.LecturerDto;
import com.netikras.tools.common.remote.http.GenericRestConsumer;
import com.netikras.tools.common.remote.http.HttpRequest;
import com.netikras.tools.common.remote.http.impl.json.HttpResponseJsonImpl;

import java.util.List;

import static com.netikras.studies.studentbuddy.api.constants.LecturerConstants.endpointGetAllByDisciplineId;
import static com.netikras.studies.studentbuddy.api.constants.LecturerConstants.endpointGetById;
import static com.netikras.studies.studentbuddy.api.constants.LecturerConstants.endpointGetByPersonId;
import static com.netikras.studies.studentbuddy.api.constants.LecturerConstants.endpointUpdate;

public class LecturerConsumer extends GenericRestConsumer {

    public LecturerDto getById(String id) {
        HttpRequest<LecturerDto> request = createRequest(endpointGetById())
                .withExpectedType(LecturerDto.class)
                .setUrlProperty("id", id);

        LecturerDto lecturerDto = (LecturerDto) sendRequest(request);
        return lecturerDto;
    }

    public List<LecturerDto> getAllByDisciplineId(String id) {
        HttpRequest<LecturerDto> request = createRequest(endpointGetAllByDisciplineId())
                .setUrlProperty("id", id);

        List<LecturerDto> lectures = (List<LecturerDto>) sendRequest(request, new HttpResponseJsonImpl(new TypeReference<List<LecturerDto>>() {
        }));
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

}
