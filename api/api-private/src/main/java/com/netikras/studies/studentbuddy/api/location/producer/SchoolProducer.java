package com.netikras.studies.studentbuddy.api.location.producer;

import com.netikras.studies.studentbuddy.api.location.generated.SchoolApiProducer;
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
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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
public class SchoolProducer extends SchoolApiProducer {

    @Resource
    private SchoolService schoolService;


    @Override
    @Authorizable(resource = SCHOOL, action = DELETE)
    protected void onDeleteSchoolDto(String id) {
        schoolService.deleteSchool(id);
    }

    @Override
    @Authorizable(resource = SCHOOL, action = MODIFY)
    protected SchoolDto onUpdateSchoolDto(SchoolDto schoolDto) {
        School school = ModelMapper.apply(new School(), schoolDto);
        school = schoolService.updateSchool(school);
        schoolDto = ModelMapper.transform(school, new SchoolDto(), new MappingSettings().setDepthMax(3));

        return schoolDto;
    }

    @Override
    @Authorizable(resource = SCHOOL, action = CREATE)
    protected SchoolDto onCreateSchoolDto(SchoolDto schoolDto) {
        School school = ModelMapper.apply(new School(), schoolDto, new MappingSettings().setForceUpdate(true));
        if (school != null) school.setId(null);
        school = schoolService.createSchool(school);
        schoolDto = ModelMapper.transform(school, new SchoolDto(), new MappingSettings().setDepthMax(3));

        return schoolDto;
    }

    @Override
    @Authorizable(resource = SCHOOL, action = GET)
    protected SchoolDto onRetrieveSchoolDto(String id) {
        School school = schoolService.getSchool(id);
        if (school != null) school.getDepartments();
        SchoolDto schoolDto = ModelMapper.transform(school, new SchoolDto(), new MappingSettings().setDepthMax(3));
        return schoolDto;
    }

    @Override
    @Authorizable(resource = SCHOOL_DEPARTMENT, action = DELETE)
    protected void onDeleteSchoolDepartmentDto(String id) {
        schoolService.deleteSchoolDepartment(id);
    }

    @Override
    @Authorizable(resource = SCHOOL_DEPARTMENT, action = MODIFY)
    protected SchoolDepartmentDto onUpdateSchoolDepartmentDto(SchoolDepartmentDto departmentDto) {
        SchoolDepartment department = ModelMapper.apply(new SchoolDepartment(), departmentDto);
        department = schoolService.updateSchoolDepartment(department);
        departmentDto = ModelMapper.transform(department, new SchoolDepartmentDto(), new MappingSettings().setDepthMax(3));

        return departmentDto;
    }

    @Override
    @Authorizable(resource = SCHOOL_DEPARTMENT, action = CREATE)
    protected SchoolDepartmentDto onCreateSchoolDepartmentDto(SchoolDepartmentDto departmentDto) {
        SchoolDepartment department = ModelMapper.apply(new SchoolDepartment(), departmentDto, new MappingSettings().setForceUpdate(true));
        if (department != null) department.setId(null);
        department = schoolService.createSchoolDepartment(department);
        departmentDto = ModelMapper.transform(department, new SchoolDepartmentDto(), new MappingSettings().setDepthMax(3));

        return departmentDto;
    }

    @Override
    @Authorizable(resource = SCHOOL_DEPARTMENT, action = GET)
    protected SchoolDepartmentDto onRetrieveSchoolDepartmentDto(String id) {
        SchoolDepartment department = schoolService.getSchoolDepartment(id);
        if (department != null) {
            department.getBuildings();
        }
        SchoolDepartmentDto departmentDto = ModelMapper.transform(department, new SchoolDepartmentDto(), new MappingSettings().setDepthMax(3));

        return departmentDto;
    }

    @Override
    @Authorizable(resource = DISCIPLINE, action = DELETE)
    protected void onDeleteDisciplineDto(String id) {
        schoolService.deleteDiscipline(id);
    }

    @Override
    @Authorizable(resource = DISCIPLINE, action = MODIFY)
    protected DisciplineDto onUpdateDisciplineDto(DisciplineDto disciplineDto) {
        Discipline discipline = ModelMapper.apply(new Discipline(), disciplineDto, new MappingSettings().setForceUpdate(true));
        discipline = schoolService.updateDiscipline(discipline);
        disciplineDto = ModelMapper.transform(discipline, new DisciplineDto(), new MappingSettings().setDepthMax(2));
        return disciplineDto;
    }

    @Override
    @Authorizable(resource = DISCIPLINE, action = CREATE)
    protected DisciplineDto onCreateDisciplineDto(DisciplineDto disciplineDto) {
        Discipline discipline = ModelMapper.apply(new Discipline(), disciplineDto, new MappingSettings().setForceUpdate(true));
        if (discipline != null) discipline.setId(null);
        discipline = schoolService.createDiscipline(discipline);
        disciplineDto = ModelMapper.transform(discipline, new DisciplineDto(), new MappingSettings().setDepthMax(2));
        return disciplineDto;
    }

    @Override
    @Authorizable(resource = DISCIPLINE, action = GET)
    protected DisciplineDto onRetrieveDisciplineDto(String id) {
        Discipline discipline = schoolService.getDiscipline(id);
        DisciplineDto disciplineDto = ModelMapper.transform(discipline, new DisciplineDto(), new MappingSettings().setDepthMax(2));
        return disciplineDto;
    }

    @Override
    @Authorizable(resource = PERSONNEL, action = DELETE)
    protected void onDeletePersonnelMemberDto(String id) {
        schoolService.deletePersonnelMember(id);
    }

    @Override
    @Authorizable(resource = PERSONNEL, action = MODIFY)
    protected PersonnelMemberDto onUpdatePersonnelMemberDto(PersonnelMemberDto personnelMemberDto) {
        PersonnelMember personnelMember = ModelMapper.apply(new PersonnelMember(), personnelMemberDto, new MappingSettings().setForceUpdate(true));
        personnelMember = schoolService.updatePersonnelMember(personnelMember);
        personnelMemberDto = ModelMapper.transform(personnelMember, new PersonnelMemberDto(), new MappingSettings().setDepthMax(2));
        return personnelMemberDto;
    }

    @Override
    @Authorizable(resource = PERSONNEL, action = CREATE)
    protected PersonnelMemberDto onCreatePersonnelMemberDto(PersonnelMemberDto personnelMemberDto) {
        PersonnelMember personnelMember = ModelMapper.apply(new PersonnelMember(), personnelMemberDto, new MappingSettings().setForceUpdate(true));
        if (personnelMember != null) personnelMember.setId(null);
        personnelMember = schoolService.createPersonnelMember(personnelMember);
        personnelMemberDto = ModelMapper.transform(personnelMember, new PersonnelMemberDto(), new MappingSettings().setDepthMax(2));
        return personnelMemberDto;
    }

    @Override
    @Authorizable(resource = PERSONNEL, action = GET)
    protected PersonnelMemberDto onRetrievePersonnelMemberDto(String id) {
        PersonnelMember personnelMember = schoolService.getPersonnelMember(id);
        PersonnelMemberDto personnelMemberDto = ModelMapper.transform(personnelMember, new PersonnelMemberDto(), new MappingSettings().setDepthMax(2));
        return personnelMemberDto;
    }

    @Override
    @Authorizable(resource = SCHOOL, action = GET_ALL)
    protected List<SchoolDto> onGetSchoolDtoAll() {
        List<School> schools = schoolService.getAllSchools();
        List<SchoolDto> schoolDtos = (List<SchoolDto>) ModelMapper.transformAll(schools, SchoolDto.class, new MappingSettings().setDepthMax(3));
        return schoolDtos;
    }

    @Override
    @Authorizable(resource = SCHOOL, action = SEARCH)
    protected List<SchoolDto> onSearchSchoolDtoAllByTitle(String title) {
        List<School> schools = schoolService.searchAllSchoolsByTitle(title);
        List<SchoolDto> schoolDtos = (List<SchoolDto>) ModelMapper.transformAll(schools, SchoolDto.class, new MappingSettings().setDepthMax(3));
        return schoolDtos;
    }

    @Override
    @Authorizable(resource = DISCIPLINE, action = SEARCH)
    protected List<DisciplineDto> onGetDisciplineDtoAllBySchoolId(String id) {
        List<Discipline> disciplines = schoolService.getAllDisciplinesBySchoolId(id);
        List<DisciplineDto> disciplineDtos = (List<DisciplineDto>) ModelMapper.transformAll(disciplines, DisciplineDto.class, new MappingSettings().setDepthMax(2));
        return disciplineDtos;
    }

    @Override
    @Authorizable(resource = PERSONNEL, action = SEARCH)
    protected List<PersonnelMemberDto> onSearchPersonnelMemberDtoAllBySchoolId(String id) {
        List<PersonnelMember> personnelMembers = schoolService.getAllPersonnelBySchool(id);
        List<PersonnelMemberDto> memberDtos =
                (List<PersonnelMemberDto>) ModelMapper.transformAll(personnelMembers, PersonnelMemberDto.class, new MappingSettings().setDepthMax(3));

        return memberDtos;
    }

    @Override
    @Authorizable(resource = SCHOOL_DEPARTMENT, action = SEARCH)
    protected List<SchoolDepartmentDto> onSearchSchoolDepartmentDtoAllByTitle(String title) {
        List<SchoolDepartment> departments = schoolService.searchAllDepartmentsByTitle(title);
        List<SchoolDepartmentDto> departmentDtos =
                (List<SchoolDepartmentDto>) ModelMapper.transformAll(departments, SchoolDepartmentDto.class, new MappingSettings().setDepthMax(3));

        return departmentDtos;
    }

    @Override
    @Authorizable(resource = PERSONNEL, action = SEARCH)
    protected List<PersonnelMemberDto> onSearchPersonnelMemberDtoAllByTitle(String title) {
        List<PersonnelMember> personnelMembers = schoolService.searchAllPersonnelByTitle(title);
        List<PersonnelMemberDto> memberDtos =
                (List<PersonnelMemberDto>) ModelMapper.transformAll(personnelMembers, PersonnelMemberDto.class, new MappingSettings().setDepthMax(3));

        return memberDtos;
    }

    @Override
    @Authorizable(resource = PERSONNEL, action = SEARCH)
    protected List<PersonnelMemberDto> onSearchPersonnelMemberDtoAllByFirstName(String fname) {
        List<PersonnelMember> personnelMembers = schoolService.searchAllPersonnelByFirstName(fname);
        List<PersonnelMemberDto> memberDtos =
                (List<PersonnelMemberDto>) ModelMapper.transformAll(personnelMembers, PersonnelMemberDto.class, new MappingSettings().setDepthMax(3));

        return memberDtos;
    }

    @Override
    @Authorizable(resource = PERSONNEL, action = SEARCH)
    protected List<PersonnelMemberDto> onSearchPersonnelMemberDtoAllByLastName(String lname) {
        List<PersonnelMember> personnelMembers = schoolService.searchAllPersonnelByLastName(lname);
        List<PersonnelMemberDto> memberDtos =
                (List<PersonnelMemberDto>) ModelMapper.transformAll(personnelMembers, PersonnelMemberDto.class, new MappingSettings().setDepthMax(3));

        return memberDtos;
    }

    @Override
    @Authorizable(resource = PERSONNEL, action = SEARCH)
    protected List<PersonnelMemberDto> onSearchPersonnelMemberDtoAllByCode(String code) {
        List<PersonnelMember> personnelMembers = schoolService.searchAllPersonnelByPersonalCode(code);
        List<PersonnelMemberDto> memberDtos =
                (List<PersonnelMemberDto>) ModelMapper.transformAll(personnelMembers, PersonnelMemberDto.class, new MappingSettings().setDepthMax(3));

        return memberDtos;
    }

    @Override
    @Authorizable(resource = PERSONNEL, action = SEARCH)
    protected List<PersonnelMemberDto> onSearchPersonnelMemberDtoAllByIdentifier(String id) {
        List<PersonnelMember> personnelMembers = schoolService.searchAllPersonnelByIdentifier(id);
        List<PersonnelMemberDto> memberDtos =
                (List<PersonnelMemberDto>) ModelMapper.transformAll(personnelMembers, PersonnelMemberDto.class, new MappingSettings().setDepthMax(3));

        return memberDtos;
    }

    @Override
    @Authorizable(resource = DISCIPLINE, action = SEARCH)
    protected List<DisciplineDto> onSearchDisciplineDtoAllByTitle(String title) {
        List<Discipline> disciplines = schoolService.searchAllDisciplinesByTitle(title);
        List<DisciplineDto> memberDtos =
                (List<DisciplineDto>) ModelMapper.transformAll(disciplines, DisciplineDto.class, new MappingSettings().setDepthMax(3));

        return memberDtos;
    }

    @Override
    @Authorizable(resource = DISCIPLINE, action = SEARCH)
    protected List<DisciplineDto> onSearchDisciplineDtoAllByDescription(String descr) {
        List<Discipline> disciplines = schoolService.searchAllDisciplinesByDescription(descr);
        List<DisciplineDto> memberDtos =
                (List<DisciplineDto>) ModelMapper.transformAll(disciplines, DisciplineDto.class, new MappingSettings().setDepthMax(3));

        return memberDtos;
    }
}
