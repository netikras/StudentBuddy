package com.netikras.studies.studentbuddy.api.location;

import com.netikras.studies.studentbuddy.core.data.api.dto.school.DisciplineDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.PersonelMemberDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.SchoolDepartmentDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.SchoolDto;
import com.netikras.studies.studentbuddy.core.data.api.model.Discipline;
import com.netikras.studies.studentbuddy.core.data.api.model.PersonnelMember;
import com.netikras.studies.studentbuddy.core.data.api.model.School;
import com.netikras.studies.studentbuddy.core.data.api.model.SchoolDepartment;
import com.netikras.studies.studentbuddy.core.meta.annotations.Authorizable;
import com.netikras.studies.studentbuddy.core.service.SchoolService;
import com.netikras.tools.common.model.mapper.MappingSettings;
import com.netikras.tools.common.model.mapper.ModelMapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import java.util.List;

import static com.netikras.studies.studentbuddy.api.constants.SchoolConstants.BASE_URL;
import static com.netikras.studies.studentbuddy.api.constants.SchoolConstants.SCHOOL_URL_CREATE;
import static com.netikras.studies.studentbuddy.api.constants.SchoolConstants.SCHOOL_URL_CREATE_DEPARTMENT;
import static com.netikras.studies.studentbuddy.api.constants.SchoolConstants.SCHOOL_URL_DELETE_BY_ID;
import static com.netikras.studies.studentbuddy.api.constants.SchoolConstants.SCHOOL_URL_DELETE_DEPARTMENT_BY_ID;
import static com.netikras.studies.studentbuddy.api.constants.SchoolConstants.SCHOOL_URL_GET_ALL;
import static com.netikras.studies.studentbuddy.api.constants.SchoolConstants.SCHOOL_URL_GET_BY_ID;
import static com.netikras.studies.studentbuddy.api.constants.SchoolConstants.SCHOOL_URL_GET_DEPARTMENT_BY_ID;
import static com.netikras.studies.studentbuddy.api.constants.SchoolConstants.SCHOOL_URL_UPDATE;
import static com.netikras.studies.studentbuddy.api.constants.SchoolConstants.SCHOOL_URL_UPDATE_DEPARTMENT;
import static com.netikras.studies.studentbuddy.core.meta.Action.CREATE;
import static com.netikras.studies.studentbuddy.core.meta.Action.DELETE;
import static com.netikras.studies.studentbuddy.core.meta.Action.GET;
import static com.netikras.studies.studentbuddy.core.meta.Action.GET_ALL;
import static com.netikras.studies.studentbuddy.core.meta.Action.MODIFY;
import static com.netikras.studies.studentbuddy.core.meta.Action.SEARCH;
import static com.netikras.studies.studentbuddy.core.meta.Resource.DISCIPLINE;
import static com.netikras.studies.studentbuddy.core.meta.Resource.PERSONNEL;
import static com.netikras.studies.studentbuddy.core.meta.Resource.SCHOOL;
import static com.netikras.studies.studentbuddy.core.meta.Resource.SCHOOL_DEPARTMENT;

@RestController
@RequestMapping(value = BASE_URL)
public class SchoolController {

    @Resource
    private SchoolService schoolService;

    @RequestMapping(
            value = SCHOOL_URL_GET_BY_ID,
            method = RequestMethod.GET
    )
    @ResponseBody
    @Authorizable(resource = SCHOOL, action = GET)
    public SchoolDto getSchoolById(
            @PathVariable(name = "id") String id
    ) {
        School school = schoolService.getSchool(id);
        SchoolDto schoolDto = ModelMapper.transform(school, new SchoolDto(), new MappingSettings().setDepthMax(3));
        return schoolDto;
    }

    @RequestMapping(
            value = SCHOOL_URL_GET_ALL,
            method = RequestMethod.GET
    )
    @ResponseBody
    @Authorizable(resource = SCHOOL, action = GET_ALL)
    public List<SchoolDto> getAllSchools() {
        List<School> schools = schoolService.getAllSchools();
        List<SchoolDto> schoolDtos = (List<SchoolDto>) ModelMapper.transformAll(schools, SchoolDto.class, new MappingSettings().setDepthMax(3));
        return schoolDtos;
    }

    @RequestMapping(
            value = SCHOOL_URL_CREATE,
            method = RequestMethod.POST
    )
    @ResponseBody
    @Authorizable(resource = SCHOOL, action = CREATE)
    public SchoolDto createSchool(
            @RequestBody SchoolDto schoolDto
    ) {
        School school = ModelMapper.apply(new School(), schoolDto, new MappingSettings().setForceUpdate(true));
        school = schoolService.createSchool(school);
        schoolDto = ModelMapper.transform(school, new SchoolDto(), new MappingSettings().setDepthMax(3));

        return schoolDto;
    }

    @RequestMapping(
            value = SCHOOL_URL_DELETE_BY_ID,
            method = RequestMethod.DELETE
    )
    @ResponseBody
    @Authorizable(resource = SCHOOL, action = DELETE)
    public void deleteSchoolById(
            @PathVariable(name = "id") String id
    ) {
        schoolService.deleteSchool(id);
    }

    @RequestMapping(
            value = SCHOOL_URL_UPDATE,
            method = RequestMethod.PUT
    )
    @ResponseBody
    @Authorizable(resource = SCHOOL, action = MODIFY)
    public SchoolDto updateSchool(
            @RequestBody SchoolDto schoolDto
    ) {
        School school = ModelMapper.apply(new School(), schoolDto);
        school = schoolService.updateSchool(school);
        schoolDto = ModelMapper.transform(school, new SchoolDto(), new MappingSettings().setDepthMax(3));

        return schoolDto;
    }

    @RequestMapping(
            value = "/search/title/{query}",
            method = RequestMethod.GET
    )
    @ResponseBody
    @Authorizable(resource = SCHOOL, action = SEARCH)
    public List<SchoolDto> searchAllSchoolsByTitle(
            @PathVariable(name = "query") String query
    ) {
        List<School> schools = schoolService.searchAllSchoolsByTitle(query);
        List<SchoolDto> schoolDtos = (List<SchoolDto>) ModelMapper.transformAll(schools, SchoolDto.class, new MappingSettings().setDepthMax(3));
        return schoolDtos;
    }


    // school department

    @RequestMapping(
            value = SCHOOL_URL_CREATE_DEPARTMENT,
            method = RequestMethod.POST
    )
    @ResponseBody
    @Authorizable(resource = SCHOOL_DEPARTMENT, action = CREATE)
    public SchoolDepartmentDto createSchoolDepartment(
            @RequestBody SchoolDepartmentDto departmentDto
    ) {
        SchoolDepartment department = ModelMapper.apply(new SchoolDepartment(), departmentDto, new MappingSettings().setForceUpdate(true));
        department = schoolService.createSchoolDepartment(department);
        departmentDto = ModelMapper.transform(department, new SchoolDepartmentDto(), new MappingSettings().setDepthMax(3));

        return departmentDto;
    }

    @RequestMapping(
            value = SCHOOL_URL_UPDATE_DEPARTMENT,
            method = RequestMethod.PUT
    )
    @ResponseBody
    @Authorizable(resource = SCHOOL_DEPARTMENT, action = MODIFY)
    public SchoolDepartmentDto updateSchoolDepartment(
            @RequestBody SchoolDepartmentDto departmentDto
    ) {
        SchoolDepartment department = ModelMapper.apply(new SchoolDepartment(), departmentDto);
        department = schoolService.updateSchoolDepartment(department);
        departmentDto = ModelMapper.transform(department, new SchoolDepartmentDto(), new MappingSettings().setDepthMax(3));

        return departmentDto;
    }

    @RequestMapping(
            value = SCHOOL_URL_GET_DEPARTMENT_BY_ID,
            method = RequestMethod.GET
    )
    @ResponseBody
    @Authorizable(resource = SCHOOL_DEPARTMENT, action = GET)
    public SchoolDepartmentDto getSchoolDepartmentById(
            @PathVariable(name = "id") String id
    ) {
        SchoolDepartment department = schoolService.getSchoolDepartment(id);
        SchoolDepartmentDto departmentDto = ModelMapper.transform(department, new SchoolDepartmentDto(), new MappingSettings().setDepthMax(3));

        return departmentDto;
    }

    @RequestMapping(
            value = SCHOOL_URL_DELETE_DEPARTMENT_BY_ID,
            method = RequestMethod.DELETE
    )
    @ResponseBody
    @Authorizable(resource = SCHOOL_DEPARTMENT, action = DELETE)
    public void deleteSchoolDepartmentById(
            @PathVariable(name = "id") String id
    ) {
        schoolService.deleteSchoolDepartment(id);
    }

    @RequestMapping(
            value = "/department/search/title/{title}",
            method = RequestMethod.GET
    )
    @ResponseBody
    @Authorizable(resource = SCHOOL_DEPARTMENT, action = SEARCH)
    public List<SchoolDepartmentDto> searchAllDepartmentsByTitle(
            @PathVariable(name = "title") String titleSubstring
    ) {
        List<SchoolDepartment> departments = schoolService.searchAllDepartmentsByTitle(titleSubstring);
        List<SchoolDepartmentDto> departmentDtos =
                (List<SchoolDepartmentDto>) ModelMapper.transformAll(departments, SchoolDepartmentDto.class, new MappingSettings().setDepthMax(3));

        return departmentDtos;
    }


    @RequestMapping(
            value = "/personnel/search/title/{title}",
            method = RequestMethod.GET
    )
    @ResponseBody
    @Authorizable(resource = PERSONNEL, action = SEARCH)
    public List<PersonelMemberDto> searchAllPersonnelByTitle(
            @PathVariable(name = "title") String titleSubstring
    ) {
        List<PersonnelMember> personnelMembers = schoolService.searchAllPersonnelByTitle(titleSubstring);
        List<PersonelMemberDto> memberDtos =
                (List<PersonelMemberDto>) ModelMapper.transformAll(personnelMembers, PersonelMemberDto.class, new MappingSettings().setDepthMax(3));

        return memberDtos;
    }

    @RequestMapping(
            value = "/personnel/search/firstName/{fname}",
            method = RequestMethod.GET
    )
    @ResponseBody
    @Authorizable(resource = PERSONNEL, action = SEARCH)
    public List<PersonelMemberDto> searchAllPersonnelByFirstName(
            @PathVariable(name = "fname") String fnameSubstring
    ) {
        List<PersonnelMember> personnelMembers = schoolService.searchAllPersonnelByFirstName(fnameSubstring);
        List<PersonelMemberDto> memberDtos =
                (List<PersonelMemberDto>) ModelMapper.transformAll(personnelMembers, PersonelMemberDto.class, new MappingSettings().setDepthMax(3));

        return memberDtos;
    }

    @RequestMapping(
            value = "/personnel/search/lastName/{lname}",
            method = RequestMethod.GET
    )
    @ResponseBody
    @Authorizable(resource = PERSONNEL, action = SEARCH)
    public List<PersonelMemberDto> searchAllPersonnelByLastName(
            @PathVariable(name = "lname") String lnameSubstring
    ) {
        List<PersonnelMember> personnelMembers = schoolService.searchAllPersonnelByLastName(lnameSubstring);
        List<PersonelMemberDto> memberDtos =
                (List<PersonelMemberDto>) ModelMapper.transformAll(personnelMembers, PersonelMemberDto.class, new MappingSettings().setDepthMax(3));

        return memberDtos;
    }

    @RequestMapping(
            value = "/discipline/search/title/{title}",
            method = RequestMethod.GET
    )
    @ResponseBody
    @Authorizable(resource = DISCIPLINE, action = SEARCH)
    public List<DisciplineDto> searchAllDisciplinesByTitle(
            @PathVariable(name = "title") String titleSubstring
    ) {
        List<Discipline> disciplines = schoolService.searchAllDisciplinesByTitle(titleSubstring);
        List<DisciplineDto> memberDtos =
                (List<DisciplineDto>) ModelMapper.transformAll(disciplines, DisciplineDto.class, new MappingSettings().setDepthMax(3));

        return memberDtos;
    }

    @RequestMapping(
            value = "/discipline/search/description/{descr}",
            method = RequestMethod.GET
    )
    @ResponseBody
    @Authorizable(resource = DISCIPLINE, action = SEARCH)
    public List<DisciplineDto> searchAllDisciplinesByDesctiotion(
            @PathVariable(name = "descr") String descriptionSubstring
    ) {
        List<Discipline> disciplines = schoolService.searchAllDisciplinesByDescription(descriptionSubstring);
        List<DisciplineDto> memberDtos =
                (List<DisciplineDto>) ModelMapper.transformAll(disciplines, DisciplineDto.class, new MappingSettings().setDepthMax(3));

        return memberDtos;
    }

}
