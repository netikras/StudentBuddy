package com.netikras.studies.studentbuddy.api.location;

import com.fasterxml.jackson.core.type.TypeReference;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.SchoolDepartmentDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.SchoolDto;
import com.netikras.tools.common.remote.http.GenericRestConsumer;
import com.netikras.tools.common.remote.http.HttpRequest;
import com.netikras.tools.common.remote.http.impl.json.HttpResponseJsonImpl;

import java.util.List;

import static com.netikras.studies.studentbuddy.api.constants.SchoolConstants.endpointCreateDepartment;
import static com.netikras.studies.studentbuddy.api.constants.SchoolConstants.endpointCreateSchool;
import static com.netikras.studies.studentbuddy.api.constants.SchoolConstants.endpointDeleteById;
import static com.netikras.studies.studentbuddy.api.constants.SchoolConstants.endpointDeleteDepartmentById;
import static com.netikras.studies.studentbuddy.api.constants.SchoolConstants.endpointGetAll;
import static com.netikras.studies.studentbuddy.api.constants.SchoolConstants.endpointGetById;
import static com.netikras.studies.studentbuddy.api.constants.SchoolConstants.endpointGetDepartmentById;
import static com.netikras.studies.studentbuddy.api.constants.SchoolConstants.endpointUpdateDepartment;
import static com.netikras.studies.studentbuddy.api.constants.SchoolConstants.endpointUpdateSchool;

public class SchoolConsumer extends GenericRestConsumer {

    private TypeReference<SchoolDto> schoolsListTypeRef = new TypeReference<SchoolDto>() {};

    public SchoolDto createSchool(SchoolDto schoolDto) {
        HttpRequest<SchoolDto> request = createRequest(endpointCreateSchool())
                .withExpectedType(SchoolDto.class)
                .setObject(schoolDto);

        SchoolDto dto = (SchoolDto) sendRequest(request);
        return dto;
    }

    public SchoolDto updateSchool(SchoolDto schoolDto) {
        HttpRequest<SchoolDto> request = createRequest(endpointUpdateSchool())
                .withExpectedType(SchoolDto.class)
                .setObject(schoolDto);

        SchoolDto dto = (SchoolDto) sendRequest(request);
        return dto;
    }

    public SchoolDto getSchoolById(String id) {
        HttpRequest request = createRequest(endpointGetById())
                .withExpectedType(SchoolDto.class)
                .setUrlProperty("id", id);

        SchoolDto dto = (SchoolDto) sendRequest(request);
        return dto;
    }

    public List<SchoolDto> getAllSchools() {
        HttpRequest request = createRequest(endpointGetAll())
                .withTypeReference(schoolsListTypeRef);

        List<SchoolDto> dtos = (List<SchoolDto>) sendRequest(request);
        return dtos;
    }

    public boolean deletetSchoolById(String id) {
        HttpRequest request = createRequest(endpointDeleteById())
                .setUrlProperty("id", id);

        HttpResponseJsonImpl responseJson = new HttpResponseJsonImpl();
        sendRequest(request, responseJson);
        return responseJson.isResponseSuccess();
    }



    // departments

    public SchoolDepartmentDto createDepartment(SchoolDepartmentDto departmentDto) {
        HttpRequest<SchoolDepartmentDto> request = createRequest(endpointCreateDepartment())
                .withExpectedType(SchoolDepartmentDto.class)
                .setObject(departmentDto);

        SchoolDepartmentDto dto = (SchoolDepartmentDto) sendRequest(request);
        return dto;
    }

    public SchoolDepartmentDto updateDepartment(SchoolDepartmentDto departmentDto) {
        HttpRequest<SchoolDepartmentDto> request = createRequest(endpointUpdateDepartment())
                .withExpectedType(SchoolDepartmentDto.class)
                .setObject(departmentDto);

        SchoolDepartmentDto dto = (SchoolDepartmentDto) sendRequest(request);
        return dto;
    }


    public SchoolDepartmentDto getDepartmentById(String id) {
        HttpRequest request = createRequest(endpointGetDepartmentById())
                .withExpectedType(SchoolDepartmentDto.class)
                .setUrlProperty("id", id);

        SchoolDepartmentDto dto = (SchoolDepartmentDto) sendRequest(request);
        return dto;
    }

    public boolean deletetDepartmentById(String id) {
        HttpRequest request = createRequest(endpointDeleteDepartmentById())
                .setUrlProperty("id", id);

        HttpResponseJsonImpl responseJson = new HttpResponseJsonImpl();
        sendRequest(request, responseJson);
        return responseJson.isResponseSuccess();
    }

}
