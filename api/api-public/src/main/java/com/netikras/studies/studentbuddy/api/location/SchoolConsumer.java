package com.netikras.studies.studentbuddy.api.location;

import com.fasterxml.jackson.core.type.TypeReference;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.DisciplineDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.PersonnelMemberDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.SchoolDepartmentDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.SchoolDto;
import com.netikras.tools.common.remote.http.GenericRestConsumer;
import com.netikras.tools.common.remote.http.HttpRequest;
import com.netikras.tools.common.remote.http.impl.json.HttpResponseJsonImpl;

import java.util.List;

import static com.netikras.studies.studentbuddy.api.constants.SchoolConstants.endpointCreateDepartment;
import static com.netikras.studies.studentbuddy.api.constants.SchoolConstants.endpointCreateDiscipline;
import static com.netikras.studies.studentbuddy.api.constants.SchoolConstants.endpointCreatePersonnelMember;
import static com.netikras.studies.studentbuddy.api.constants.SchoolConstants.endpointCreateSchool;
import static com.netikras.studies.studentbuddy.api.constants.SchoolConstants.endpointDeleteById;
import static com.netikras.studies.studentbuddy.api.constants.SchoolConstants.endpointDeleteDepartmentById;
import static com.netikras.studies.studentbuddy.api.constants.SchoolConstants.endpointDeleteDisciplineById;
import static com.netikras.studies.studentbuddy.api.constants.SchoolConstants.endpointDeletePersonnelMemberById;
import static com.netikras.studies.studentbuddy.api.constants.SchoolConstants.endpointGetAll;
import static com.netikras.studies.studentbuddy.api.constants.SchoolConstants.endpointGetAllDisciplinesBySchoolId;
import static com.netikras.studies.studentbuddy.api.constants.SchoolConstants.endpointGetAllPersonnelBySchoolId;
import static com.netikras.studies.studentbuddy.api.constants.SchoolConstants.endpointGetById;
import static com.netikras.studies.studentbuddy.api.constants.SchoolConstants.endpointGetDepartmentById;
import static com.netikras.studies.studentbuddy.api.constants.SchoolConstants.endpointGetDisciplineById;
import static com.netikras.studies.studentbuddy.api.constants.SchoolConstants.endpointGetPersonnelMemberById;
import static com.netikras.studies.studentbuddy.api.constants.SchoolConstants.endpointSearchAllDepartmentsByTitle;
import static com.netikras.studies.studentbuddy.api.constants.SchoolConstants.endpointSearchAllDisciplinesByDescription;
import static com.netikras.studies.studentbuddy.api.constants.SchoolConstants.endpointSearchAllDisciplinesByTitle;
import static com.netikras.studies.studentbuddy.api.constants.SchoolConstants.endpointSearchAllPersonnelByFirstName;
import static com.netikras.studies.studentbuddy.api.constants.SchoolConstants.endpointSearchAllPersonnelByIdentifier;
import static com.netikras.studies.studentbuddy.api.constants.SchoolConstants.endpointSearchAllPersonnelByLastName;
import static com.netikras.studies.studentbuddy.api.constants.SchoolConstants.endpointSearchAllPersonnelByPersonalCode;
import static com.netikras.studies.studentbuddy.api.constants.SchoolConstants.endpointSearchAllPersonnelByTitle;
import static com.netikras.studies.studentbuddy.api.constants.SchoolConstants.endpointSearchAllSchoolsByTitle;
import static com.netikras.studies.studentbuddy.api.constants.SchoolConstants.endpointUpdateDepartment;
import static com.netikras.studies.studentbuddy.api.constants.SchoolConstants.endpointUpdateDiscipline;
import static com.netikras.studies.studentbuddy.api.constants.SchoolConstants.endpointUpdatePersonnelMember;
import static com.netikras.studies.studentbuddy.api.constants.SchoolConstants.endpointUpdateSchool;

public class SchoolConsumer extends GenericRestConsumer {

    private TypeReference schoolsListTypeRef = new TypeReference<List<SchoolDto>>() {
    };
    private TypeReference departmentsListTypeRef = new TypeReference<List<SchoolDepartmentDto>>() {
    };
    private TypeReference personnelListTypeRef = new TypeReference<List<PersonnelMemberDto>>() {
    };
    private TypeReference disciplineListTypeRef = new TypeReference<List<DisciplineDto>>() {
    };

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

    public List<SchoolDto> searchAllSchoolsByTitle(String title) {
        HttpRequest request = createRequest(endpointSearchAllSchoolsByTitle())
                .withTypeReference(schoolsListTypeRef)
                .setUrlProperty("title", title);

        List<SchoolDto> dtos = (List<SchoolDto>) sendRequest(request);
        return dtos;
    }


    // discipline

    public DisciplineDto createDiscipline(DisciplineDto disciplineDto) {
        HttpRequest<DisciplineDto> request = createRequest(endpointCreateDiscipline())
                .withExpectedType(DisciplineDto.class)
                .setObject(disciplineDto);

        DisciplineDto dto = (DisciplineDto) sendRequest(request);
        return dto;
    }

    public DisciplineDto updateDiscipline(DisciplineDto disciplineDto) {
        HttpRequest<DisciplineDto> request = createRequest(endpointUpdateDiscipline())
                .withExpectedType(DisciplineDto.class)
                .setObject(disciplineDto);

        DisciplineDto dto = (DisciplineDto) sendRequest(request);
        return dto;
    }

    public DisciplineDto getDisciplineById(String id) {
        HttpRequest request = createRequest(endpointGetDisciplineById())
                .withExpectedType(DisciplineDto.class)
                .setUrlProperty("id", id);

        DisciplineDto dto = (DisciplineDto) sendRequest(request);
        return dto;
    }

    public List<DisciplineDto> getDisciplinesBySchoolId(String id) {
        HttpRequest request = createRequest(endpointGetAllDisciplinesBySchoolId())
                .withTypeReference(disciplineListTypeRef)
                .setUrlProperty("id", id);

        List<DisciplineDto> dtos = (List<DisciplineDto>) sendRequest(request);
        return dtos;
    }

    public boolean deleteDisciplineById(String id) {
        HttpRequest request = createRequest(endpointDeleteDisciplineById())
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

    // personnel

    public PersonnelMemberDto createPersonnelMemberForSchool(PersonnelMemberDto personnelMemberDto) {
        HttpRequest<PersonnelMemberDto> request = createRequest(endpointCreatePersonnelMember())
                .withExpectedType(PersonnelMemberDto.class)
                .setObject(personnelMemberDto);

        PersonnelMemberDto dto = (PersonnelMemberDto) sendRequest(request);
        return dto;
    }

    public PersonnelMemberDto updatePersonnelMember(PersonnelMemberDto personnelMemberDto) {
        HttpRequest<PersonnelMemberDto> request = createRequest(endpointUpdatePersonnelMember())
                .withExpectedType(PersonnelMemberDto.class)
                .setObject(personnelMemberDto);

        PersonnelMemberDto dto = (PersonnelMemberDto) sendRequest(request);
        return dto;
    }

    public PersonnelMemberDto getPersonnelMemberById(String id) {
        HttpRequest request = createRequest(endpointGetPersonnelMemberById())
                .withExpectedType(PersonnelMemberDto.class)
                .setUrlProperty("id", id);

        PersonnelMemberDto dto = (PersonnelMemberDto) sendRequest(request);
        return dto;
    }

    public List<PersonnelMemberDto> getAllPersonnelMembersForSchool(String schoolId) {
        HttpRequest request = createRequest(endpointGetAllPersonnelBySchoolId())
                .withTypeReference(personnelListTypeRef)
                .setUrlProperty("id", schoolId);

        List<PersonnelMemberDto> dtos = (List<PersonnelMemberDto>) sendRequest(request);
        return dtos;
    }

    public boolean deletePersonnelMemberById(String id) {
        HttpRequest<PersonnelMemberDto> request = createRequest(endpointDeletePersonnelMemberById())
                .setUrlProperty("id", id);

        HttpResponseJsonImpl responseJson = new HttpResponseJsonImpl();
        sendRequest(request, responseJson);
        return responseJson.isResponseSuccess();
    }


    //
    //
    // search
    //
    //

    public List<DisciplineDto> searchAllDisciplinesByDescription(String descr) {
        HttpRequest request = createRequest(endpointSearchAllDisciplinesByDescription())
                .withTypeReference(disciplineListTypeRef)
                .setUrlProperty("descr", descr);

        List<DisciplineDto> dtos = (List<DisciplineDto>) sendRequest(request);
        return dtos;
    }

    public List<DisciplineDto> searchAllDisciplinesByTitle(String title) {
        HttpRequest request = createRequest(endpointSearchAllDisciplinesByTitle())
                .withTypeReference(disciplineListTypeRef)
                .setUrlProperty("title", title);

        List<DisciplineDto> dtos = (List<DisciplineDto>) sendRequest(request);
        return dtos;
    }

    public List<SchoolDepartmentDto> searchAllDepartmentsByTitle(String title) {
        HttpRequest request = createRequest(endpointSearchAllDepartmentsByTitle())
                .withTypeReference(departmentsListTypeRef)
                .setUrlProperty("title", title);

        List<SchoolDepartmentDto> dtos = (List<SchoolDepartmentDto>) sendRequest(request);
        return dtos;
    }

    public List<PersonnelMemberDto> searchAllPersonnelByTitle(String title) {
        HttpRequest request = createRequest(endpointSearchAllPersonnelByTitle())
                .withTypeReference(personnelListTypeRef)
                .setUrlProperty("title", title);

        List<PersonnelMemberDto> dtos = (List<PersonnelMemberDto>) sendRequest(request);
        return dtos;
    }

    public List<PersonnelMemberDto> searchAllPersonnelByPersonalCode(String code) {
        HttpRequest request = createRequest(endpointSearchAllPersonnelByPersonalCode())
                .withTypeReference(personnelListTypeRef)
                .setUrlProperty("code", code);

        List<PersonnelMemberDto> dtos = (List<PersonnelMemberDto>) sendRequest(request);
        return dtos;
    }

    public List<PersonnelMemberDto> searchAllPersonnelByIdentifier(String id) {
        HttpRequest request = createRequest(endpointSearchAllPersonnelByIdentifier())
                .withTypeReference(personnelListTypeRef)
                .setUrlProperty("id", id);

        List<PersonnelMemberDto> dtos = (List<PersonnelMemberDto>) sendRequest(request);
        return dtos;
    }

    public List<PersonnelMemberDto> searchAllPersonnelByFirstName(String firstName) {
        HttpRequest request = createRequest(endpointSearchAllPersonnelByFirstName())
                .withTypeReference(personnelListTypeRef)
                .setUrlProperty("fname", firstName);

        List<PersonnelMemberDto> dtos = (List<PersonnelMemberDto>) sendRequest(request);
        return dtos;
    }

    public List<PersonnelMemberDto> searchAllPersonnelByLastName(String lastName) {
        HttpRequest request = createRequest(endpointSearchAllPersonnelByLastName())
                .withTypeReference(personnelListTypeRef)
                .setUrlProperty("lname", lastName);

        List<PersonnelMemberDto> dtos = (List<PersonnelMemberDto>) sendRequest(request);
        return dtos;
    }

}
