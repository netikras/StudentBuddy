package com.netikras.studies.studentbuddy.api.location;

import com.netikras.studies.studentbuddy.core.data.api.dto.school.DisciplineDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.PersonnelMemberDto;
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
import static com.netikras.studies.studentbuddy.api.constants.SchoolConstants.SCHOOL_URL_CREATE_PERSONNEL;
import static com.netikras.studies.studentbuddy.api.constants.SchoolConstants.SCHOOL_URL_DELETE_BY_ID;
import static com.netikras.studies.studentbuddy.api.constants.SchoolConstants.SCHOOL_URL_DELETE_DEPARTMENT_BY_ID;
import static com.netikras.studies.studentbuddy.api.constants.SchoolConstants.SCHOOL_URL_GET_ALL;
import static com.netikras.studies.studentbuddy.api.constants.SchoolConstants.SCHOOL_URL_GET_ALL_DISCIPLINES_BY_SCHOOL_ID;
import static com.netikras.studies.studentbuddy.api.constants.SchoolConstants.SCHOOL_URL_GET_ALL_PERSONNEL_BY_SCHOOL_ID;
import static com.netikras.studies.studentbuddy.api.constants.SchoolConstants.SCHOOL_URL_GET_BY_ID;
import static com.netikras.studies.studentbuddy.api.constants.SchoolConstants.SCHOOL_URL_GET_DEPARTMENT_BY_ID;
import static com.netikras.studies.studentbuddy.api.constants.SchoolConstants.SCHOOL_URL_SEARCH_ALL_BY_TITLE;
import static com.netikras.studies.studentbuddy.api.constants.SchoolConstants.SCHOOL_URL_SEARCH_ALL_DEPARTMENTS_BY_TITLE;
import static com.netikras.studies.studentbuddy.api.constants.SchoolConstants.SCHOOL_URL_SEARCH_ALL_DISCIPLINES_BY_DESCRIPTION;
import static com.netikras.studies.studentbuddy.api.constants.SchoolConstants.SCHOOL_URL_SEARCH_ALL_DISCIPLINES_BY_TITLE;
import static com.netikras.studies.studentbuddy.api.constants.SchoolConstants.SCHOOL_URL_SEARCH_ALL_PERSONNEL_BY_FIRST_NAME;
import static com.netikras.studies.studentbuddy.api.constants.SchoolConstants.SCHOOL_URL_SEARCH_ALL_PERSONNEL_BY_IDENTIFIER;
import static com.netikras.studies.studentbuddy.api.constants.SchoolConstants.SCHOOL_URL_SEARCH_ALL_PERSONNEL_BY_LAST_NAME;
import static com.netikras.studies.studentbuddy.api.constants.SchoolConstants.SCHOOL_URL_SEARCH_ALL_PERSONNEL_BY_PERSONAL_CODE;
import static com.netikras.studies.studentbuddy.api.constants.SchoolConstants.SCHOOL_URL_SEARCH_ALL_PERSONNEL_BY_TITLE;
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
            value = SCHOOL_URL_SEARCH_ALL_BY_TITLE,
            method = RequestMethod.GET
    )
    @ResponseBody
    @Authorizable(resource = SCHOOL, action = SEARCH)
    public List<SchoolDto> searchAllSchoolsByTitle(
            @PathVariable(name = "title") String query
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
            value = "/discipline",
            method = RequestMethod.POST
    )
    @ResponseBody
    @Authorizable(resource = DISCIPLINE, action = CREATE)
    public DisciplineDto createNewDiscipline(
            @RequestBody DisciplineDto disciplineDto
    ) {
        Discipline discipline = ModelMapper.apply(new Discipline(), disciplineDto, new MappingSettings().setForceUpdate(true));
        discipline = schoolService.createDiscipline(discipline);
        disciplineDto = ModelMapper.transform(discipline, new DisciplineDto(), new MappingSettings().setDepthMax(2));
        return disciplineDto;
    }

    @RequestMapping(
            value = "/discipline",
            method = RequestMethod.PUT
    )
    @ResponseBody
    @Authorizable(resource = DISCIPLINE, action = MODIFY)
    public DisciplineDto updateDiscipline(
            @RequestBody DisciplineDto disciplineDto
    ) {
        Discipline discipline = ModelMapper.apply(new Discipline(), disciplineDto, new MappingSettings().setForceUpdate(true));
        discipline = schoolService.updateDiscipline(discipline);
        disciplineDto = ModelMapper.transform(discipline, new DisciplineDto(), new MappingSettings().setDepthMax(2));
        return disciplineDto;
    }

    @RequestMapping(
            value = "/discipline/id/{id}",
            method = RequestMethod.GET
    )
    @ResponseBody
    @Authorizable(resource = DISCIPLINE, action = GET)
    public DisciplineDto getDisciplineById(
            @PathVariable(value = "id") String id
    ) {
        Discipline discipline = schoolService.getDiscipline(id);
        DisciplineDto disciplineDto = ModelMapper.transform(discipline, new DisciplineDto(), new MappingSettings().setDepthMax(2));
        return disciplineDto;
    }

    @RequestMapping(
            value = SCHOOL_URL_GET_ALL_DISCIPLINES_BY_SCHOOL_ID,
            method = RequestMethod.GET
    )
    @ResponseBody
    @Authorizable(resource = DISCIPLINE, action = GET)
    public List<DisciplineDto> getAllDisciplinesBySchoolId(
            @PathVariable(value = "id") String id
    ) {
        List<Discipline> disciplines = schoolService.getAllDisciplinesBySchoolId(id);
        List<DisciplineDto> disciplineDtos = (List<DisciplineDto>) ModelMapper.transformAll(disciplines, DisciplineDto.class, new MappingSettings().setDepthMax(2));
        return disciplineDtos;
    }

    @RequestMapping(
            value = "/discipline/id/{id}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    @Authorizable(resource = DISCIPLINE, action = DELETE)
    public void deleteDisciplineById(
            @PathVariable(value = "id") String id
    ) {
        schoolService.deleteDiscipline(id);
    }


    //
    //
    // personnel
    //
    //


    @RequestMapping(
            value = "/personnel/id/{id}",
            method = RequestMethod.GET
    )
    @ResponseBody
    @Authorizable(resource = PERSONNEL, action = GET)
    public PersonnelMemberDto getPersonnelMemberById(
            @PathVariable(value = "id") String id
    ) {
        PersonnelMember personnelMember = schoolService.getPersonnelMember(id);
        PersonnelMemberDto personnelMemberDto = ModelMapper.transform(personnelMember, new PersonnelMemberDto(), new MappingSettings().setDepthMax(2));
        return personnelMemberDto;
    }

    @RequestMapping(
            value = "/personnel/id/{id}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    @Authorizable(resource = PERSONNEL, action = DELETE)
    public void deletePersonnelMemberById(
            @PathVariable(value = "id") String id
    ) {
        schoolService.deletePersonnelMember(id);
    }

    @RequestMapping(
            value = SCHOOL_URL_CREATE_PERSONNEL,
            method = RequestMethod.POST
    )
    @ResponseBody
    @Authorizable(resource = PERSONNEL, action = CREATE)
    public PersonnelMemberDto createPersonnelMember(
            @RequestBody PersonnelMemberDto personnelMemberDto
    ) {
        PersonnelMember personnelMember = ModelMapper.apply(new PersonnelMember(), personnelMemberDto, new MappingSettings().setForceUpdate(true));
        personnelMember = schoolService.createPersonnelMember(personnelMember);
        personnelMemberDto = ModelMapper.transform(personnelMember, new PersonnelMemberDto(), new MappingSettings().setDepthMax(2));
        return personnelMemberDto;
    }

    @RequestMapping(
            value = "/personnel",
            method = RequestMethod.PUT
    )
    @ResponseBody
    @Authorizable(resource = PERSONNEL, action = MODIFY)
    public PersonnelMemberDto updatePersonnelMember(
            @RequestBody PersonnelMemberDto personnelMemberDto
    ) {
        PersonnelMember personnelMember = ModelMapper.apply(new PersonnelMember(), personnelMemberDto, new MappingSettings().setForceUpdate(true));
        personnelMember = schoolService.updatePersonnelMember(personnelMember);
        personnelMemberDto = ModelMapper.transform(personnelMember, new PersonnelMemberDto(), new MappingSettings().setDepthMax(2));
        return personnelMemberDto;
    }

    @RequestMapping(
            value = SCHOOL_URL_GET_ALL_PERSONNEL_BY_SCHOOL_ID,
            method = RequestMethod.GET
    )
    @ResponseBody
    @Authorizable(resource = PERSONNEL, action = SEARCH)
    public List<PersonnelMemberDto> getAllPersonnelBySchoolId(
            @PathVariable(name = "id") String schoolId
    ) {
        List<PersonnelMember> personnelMembers = schoolService.getAllPersonnelBySchool(schoolId);
        List<PersonnelMemberDto> memberDtos =
                (List<PersonnelMemberDto>) ModelMapper.transformAll(personnelMembers, PersonnelMemberDto.class, new MappingSettings().setDepthMax(3));

        return memberDtos;
    }



    //
    //
    // search
    //
    //

    @RequestMapping(
            value = SCHOOL_URL_SEARCH_ALL_DEPARTMENTS_BY_TITLE,
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
            value = SCHOOL_URL_SEARCH_ALL_PERSONNEL_BY_TITLE,
            method = RequestMethod.GET
    )
    @ResponseBody
    @Authorizable(resource = PERSONNEL, action = SEARCH)
    public List<PersonnelMemberDto> searchAllPersonnelByTitle(
            @PathVariable(name = "title") String titleSubstring
    ) {
        List<PersonnelMember> personnelMembers = schoolService.searchAllPersonnelByTitle(titleSubstring);
        List<PersonnelMemberDto> memberDtos =
                (List<PersonnelMemberDto>) ModelMapper.transformAll(personnelMembers, PersonnelMemberDto.class, new MappingSettings().setDepthMax(3));

        return memberDtos;
    }

    @RequestMapping(
            value = SCHOOL_URL_SEARCH_ALL_PERSONNEL_BY_FIRST_NAME,
            method = RequestMethod.GET
    )
    @ResponseBody
    @Authorizable(resource = PERSONNEL, action = SEARCH)
    public List<PersonnelMemberDto> searchAllPersonnelByFirstName(
            @PathVariable(name = "fname") String fnameSubstring
    ) {
        List<PersonnelMember> personnelMembers = schoolService.searchAllPersonnelByFirstName(fnameSubstring);
        List<PersonnelMemberDto> memberDtos =
                (List<PersonnelMemberDto>) ModelMapper.transformAll(personnelMembers, PersonnelMemberDto.class, new MappingSettings().setDepthMax(3));

        return memberDtos;
    }

    @RequestMapping(
            value = SCHOOL_URL_SEARCH_ALL_PERSONNEL_BY_LAST_NAME,
            method = RequestMethod.GET
    )
    @ResponseBody
    @Authorizable(resource = PERSONNEL, action = SEARCH)
    public List<PersonnelMemberDto> searchAllPersonnelByLastName(
            @PathVariable(name = "lname") String lnameSubstring
    ) {
        List<PersonnelMember> personnelMembers = schoolService.searchAllPersonnelByLastName(lnameSubstring);
        List<PersonnelMemberDto> memberDtos =
                (List<PersonnelMemberDto>) ModelMapper.transformAll(personnelMembers, PersonnelMemberDto.class, new MappingSettings().setDepthMax(3));

        return memberDtos;
    }

    @RequestMapping(
            value = SCHOOL_URL_SEARCH_ALL_PERSONNEL_BY_PERSONAL_CODE,
            method = RequestMethod.GET
    )
    @ResponseBody
    @Authorizable(resource = PERSONNEL, action = SEARCH)
    public List<PersonnelMemberDto> searchAllPersonnelByPersonalCode(
            @PathVariable(name = "code") String code
    ) {
        List<PersonnelMember> personnelMembers = schoolService.searchAllPersonnelByPersonalCode(code);
        List<PersonnelMemberDto> memberDtos =
                (List<PersonnelMemberDto>) ModelMapper.transformAll(personnelMembers, PersonnelMemberDto.class, new MappingSettings().setDepthMax(3));

        return memberDtos;
    }

    @RequestMapping(
            value = SCHOOL_URL_SEARCH_ALL_PERSONNEL_BY_IDENTIFIER,
            method = RequestMethod.GET
    )
    @ResponseBody
    @Authorizable(resource = PERSONNEL, action = SEARCH)
    public List<PersonnelMemberDto> searchAllPersonnelByIdentifier(
            @PathVariable(name = "id") String identifier
    ) {
        List<PersonnelMember> personnelMembers = schoolService.searchAllPersonnelByIdentifier(identifier);
        List<PersonnelMemberDto> memberDtos =
                (List<PersonnelMemberDto>) ModelMapper.transformAll(personnelMembers, PersonnelMemberDto.class, new MappingSettings().setDepthMax(3));

        return memberDtos;
    }

    @RequestMapping(
            value = SCHOOL_URL_SEARCH_ALL_DISCIPLINES_BY_TITLE,
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
            value = SCHOOL_URL_SEARCH_ALL_DISCIPLINES_BY_DESCRIPTION,
            method = RequestMethod.GET
    )
    @ResponseBody
    @Authorizable(resource = DISCIPLINE, action = SEARCH)
    public List<DisciplineDto> searchAllDisciplinesByDescription(
            @PathVariable(name = "descr") String descriptionSubstring
    ) {
        List<Discipline> disciplines = schoolService.searchAllDisciplinesByDescription(descriptionSubstring);
        List<DisciplineDto> memberDtos =
                (List<DisciplineDto>) ModelMapper.transformAll(disciplines, DisciplineDto.class, new MappingSettings().setDepthMax(3));

        return memberDtos;
    }

}
