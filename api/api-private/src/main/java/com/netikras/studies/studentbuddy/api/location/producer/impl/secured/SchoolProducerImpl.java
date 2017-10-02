package com.netikras.studies.studentbuddy.api.location.producer.impl.secured;

import com.netikras.studies.studentbuddy.core.data.api.dto.school.*;
import com.netikras.studies.studentbuddy.core.data.api.model.*;
import com.netikras.studies.studentbuddy.core.data.meta.annotations.Authorizable;
import com.netikras.studies.studentbuddy.core.service.SchoolService;
import com.netikras.tools.common.model.mapper.MappingSettings;
import com.netikras.tools.common.model.mapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

import static com.netikras.studies.studentbuddy.core.data.meta.Action.*;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.*;

@Component
public class SchoolProducerImpl {

    @Resource
    private SchoolService schoolService;


    @Authorizable(resource = SCHOOL, action = PURGE)
    public void purgeSchool(String id) {
        try {
            schoolService.purgeSchool(id);
        } catch (Exception e) {
            e.printStackTrace();
//            schoolService.purgeSchool(id);
        }
    }

    @Authorizable(resource = SCHOOL_DEPARTMENT, action = PURGE)
    public void purgeSchoolDepartment(String id) {
        schoolService.purgeSchoolDepartment(id);
    }

    @Authorizable(resource = DISCIPLINE, action = PURGE)
    public void purgeDiscipline(String id) {
        schoolService.purgeDiscipline(id);
    }

    @Authorizable(resource = PERSONNEL, action = PURGE)
    public void purgePersonnelMember(String id) {
        schoolService.purgePersonnelMember(id);
    }

    @Authorizable(resource = SCHOOL, action = DELETE)
    public void deleteSchool(String id) {
        schoolService.deleteSchool(id);
    }

    @Authorizable(resource = SCHOOL, action = MODIFY)
    public SchoolDto updateSchool(SchoolDto schoolDto) {
        School school = ModelMapper.apply(new School(), schoolDto);
        school = schoolService.updateSchool(school);
        schoolDto = ModelMapper.transform(school, new SchoolDto(), new MappingSettings().setDepthMax(3));

        return schoolDto;
    }

    @Authorizable(resource = SCHOOL, action = CREATE)
    public SchoolDto createSchool(SchoolDto schoolDto) {
        School school = ModelMapper.apply(new School(), schoolDto, new MappingSettings().setForceUpdate(true));
        if (school != null) school.setId(null);
        school = schoolService.createSchool(school);
        schoolDto = ModelMapper.transform(school, new SchoolDto(), new MappingSettings().setDepthMax(3));

        return schoolDto;
    }

    @Authorizable(resource = SCHOOL, action = GET)
    public SchoolDto getSchool(String id) {
        School school = schoolService.getSchool(id);
        if (school != null) school.getDepartments();
        SchoolDto schoolDto = ModelMapper.transform(school, new SchoolDto(), new MappingSettings().setDepthMax(3));
        return schoolDto;
    }

    @Authorizable(resource = SCHOOL_DEPARTMENT, action = DELETE)
    public void deleteSchoolDepartment(String id) {
        schoolService.deleteSchoolDepartment(id);
    }

    @Authorizable(resource = SCHOOL_DEPARTMENT, action = MODIFY)
    public SchoolDepartmentDto updateSchoolDepartment(SchoolDepartmentDto departmentDto) {
        SchoolDepartment department = ModelMapper.apply(new SchoolDepartment(), departmentDto);
        department = schoolService.updateSchoolDepartment(department);
        departmentDto = ModelMapper.transform(department, new SchoolDepartmentDto(), new MappingSettings().setDepthMax(3));

        return departmentDto;
    }

    @Authorizable(resource = SCHOOL_DEPARTMENT, action = CREATE)
    public SchoolDepartmentDto createSchoolDepartment(SchoolDepartmentDto departmentDto) {
        SchoolDepartment department = ModelMapper.apply(new SchoolDepartment(), departmentDto, new MappingSettings().setForceUpdate(true));
        if (department != null) department.setId(null);
        department = schoolService.createSchoolDepartment(department);
        departmentDto = ModelMapper.transform(department, new SchoolDepartmentDto(), new MappingSettings().setDepthMax(3));

        return departmentDto;
    }

    @Authorizable(resource = SCHOOL_DEPARTMENT, action = GET)
    public SchoolDepartmentDto getSchoolDepartment(String id) {
        SchoolDepartment department = schoolService.getSchoolDepartment(id);
        if (department != null) {
            department.getBuildings();
        }
        SchoolDepartmentDto departmentDto = ModelMapper.transform(department, new SchoolDepartmentDto(), new MappingSettings().setDepthMax(3));

        return departmentDto;
    }

    @Authorizable(resource = DISCIPLINE, action = DELETE)
    public void deleteDiscipline(String id) {
        schoolService.deleteDiscipline(id);
    }

    @Authorizable(resource = DISCIPLINE, action = MODIFY)
    public DisciplineDto updateDiscipline(DisciplineDto disciplineDto) {
        Discipline discipline = ModelMapper.apply(new Discipline(), disciplineDto, new MappingSettings().setForceUpdate(true));
        discipline = schoolService.updateDiscipline(discipline);
        disciplineDto = ModelMapper.transform(discipline, new DisciplineDto(), new MappingSettings().setDepthMax(2));
        return disciplineDto;
    }

    @Authorizable(resource = DISCIPLINE, action = CREATE)
    public DisciplineDto createDiscipline(DisciplineDto disciplineDto) {
        Discipline discipline = ModelMapper.apply(new Discipline(), disciplineDto, new MappingSettings().setForceUpdate(true));
        if (discipline != null) discipline.setId(null);
        discipline = schoolService.createDiscipline(discipline);
        disciplineDto = ModelMapper.transform(discipline, new DisciplineDto(), new MappingSettings().setDepthMax(2));
        return disciplineDto;
    }

    @Authorizable(resource = DISCIPLINE, action = GET)
    public DisciplineDto getDiscipline(String id) {
        Discipline discipline = schoolService.getDiscipline(id);
        DisciplineDto disciplineDto = ModelMapper.transform(discipline, new DisciplineDto(), new MappingSettings().setDepthMax(2));
        return disciplineDto;
    }


    @Authorizable(resource = COURSE, action = CREATE)
    public CourseDto createCourse(CourseDto item) {
        Course course = ModelMapper.apply(new Course(), item, new MappingSettings().setForceUpdate(true));
        course = schoolService.createCourse(course);
        return ModelMapper.transform(course, new CourseDto());
    }

    @Authorizable(resource = COURSE, action = DELETE)
    public void deleteCourse(String id) {
        schoolService.deleteCourse(id);
    }

    @Authorizable(resource = COURSE, action = MODIFY)
    public CourseDto updateCourse(CourseDto item) {
        Course course = ModelMapper.apply(new Course(), item, new MappingSettings().setForceUpdate(true));
        course = schoolService.updateCourse(course);
        return ModelMapper.transform(course, new CourseDto());
    }

    @Authorizable(resource = COURSE, action = GET)
    public CourseDto getCourse(String id) {
        Course course = schoolService.getCourse(id);
        return ModelMapper.transform(course, new CourseDto());
    }

    @Authorizable(resource = COURSE, action = GET_ALL)
    public List<CourseDto> getAllCourses() {
        List<Course> courses = schoolService.getAllCourses();
        return (List<CourseDto>) ModelMapper.transformAll(courses, CourseDto.class);
    }

    @Authorizable(resource = COURSE, action = PURGE)
    public void purgeCourse(String id) {
        schoolService.purgeCourse(id);
    }

    @Authorizable(resource = COURSE, action = GET_ALL)
    public List<CourseDto> getAllCoursesBySchoolId(String id) {
        List<Course> courses = schoolService.getAllCoursesBySchool(id);
        return (List<CourseDto>) ModelMapper.transformAll(courses, CourseDto.class);
    }

    @Authorizable(resource = COURSE, action = GET_ALL)
    public List<CourseDto> getAllCoursesByDisciplineId(String id) {
        List<Course> courses = schoolService.getAllCoursesByDiscipline(id);
        return (List<CourseDto>) ModelMapper.transformAll(courses, CourseDto.class);
    }

    @Authorizable(resource = COURSE, action = SEARCH)
    public List<CourseDto> searchAllCoursesByTitle(String title) {
        List<Course> courses = schoolService.searchAllCoursesByTitle(title);
        return (List<CourseDto>) ModelMapper.transformAll(courses, CourseDto.class);
    }

    @Authorizable(resource = COURSE, action = MODIFY)
    public CourseDto assignCourseLecture(String courseId, String lectureId) {
        Course course = schoolService.assignCourseLecture(courseId, lectureId);
        return ModelMapper.transform(course, new CourseDto());
    }

    @Authorizable(resource = COURSE, action = MODIFY)
    public CourseDto unassignCourseLecture(String courseId, String lectureId) {
        Course course = schoolService.unassignCourseLecture(courseId, lectureId);
        return ModelMapper.transform(course, new CourseDto());
    }

    @Authorizable(resource = PERSONNEL, action = DELETE)
    public void deletePersonnelMember(String id) {
        schoolService.deletePersonnelMember(id);
    }

    @Authorizable(resource = PERSONNEL, action = MODIFY)
    public PersonnelMemberDto updatePersonnelMember(PersonnelMemberDto personnelMemberDto) {
        PersonnelMember personnelMember = ModelMapper.apply(new PersonnelMember(), personnelMemberDto, new MappingSettings().setForceUpdate(true));
        personnelMember = schoolService.updatePersonnelMember(personnelMember);
        personnelMemberDto = ModelMapper.transform(personnelMember, new PersonnelMemberDto(), new MappingSettings().setDepthMax(2));
        return personnelMemberDto;
    }

    @Authorizable(resource = PERSONNEL, action = CREATE)
    public PersonnelMemberDto createPersonnelMember(PersonnelMemberDto personnelMemberDto) {
        PersonnelMember personnelMember = ModelMapper.apply(new PersonnelMember(), personnelMemberDto, new MappingSettings().setForceUpdate(true));
        if (personnelMember != null) personnelMember.setId(null);
        personnelMember = schoolService.createPersonnelMember(personnelMember);
        personnelMemberDto = ModelMapper.transform(personnelMember, new PersonnelMemberDto(), new MappingSettings().setDepthMax(2));
        return personnelMemberDto;
    }

    @Authorizable(resource = PERSONNEL, action = GET)
    public PersonnelMemberDto getPersonnelMember(String id) {
        PersonnelMember personnelMember = schoolService.getPersonnelMember(id);
        PersonnelMemberDto personnelMemberDto = ModelMapper.transform(personnelMember, new PersonnelMemberDto(), new MappingSettings().setDepthMax(2));
        return personnelMemberDto;
    }

    @Authorizable(resource = SCHOOL, action = GET_ALL)
    public List<SchoolDto> getAllSchools() {
        List<School> schools = schoolService.getAllSchools();
        List<SchoolDto> schoolDtos = (List<SchoolDto>) ModelMapper.transformAll(schools, SchoolDto.class, new MappingSettings().setDepthMax(3));
        return schoolDtos;
    }

    @Authorizable(resource = SCHOOL, action = SEARCH)
    public List<SchoolDto> searchAllSchoolsByTitle(String title) {
        List<School> schools = schoolService.searchAllSchoolsByTitle(title);
        List<SchoolDto> schoolDtos = (List<SchoolDto>) ModelMapper.transformAll(schools, SchoolDto.class, new MappingSettings().setDepthMax(3));
        return schoolDtos;
    }

    @Authorizable(resource = DISCIPLINE, action = SEARCH)
    public List<DisciplineDto> getAllDisciplinesBySchoolId(String id) {
        List<Discipline> disciplines = schoolService.getAllDisciplinesBySchoolId(id);
        List<DisciplineDto> disciplineDtos = (List<DisciplineDto>) ModelMapper.transformAll(disciplines, DisciplineDto.class, new MappingSettings().setDepthMax(2));
        return disciplineDtos;
    }

    @Authorizable(resource = PERSONNEL, action = SEARCH)
    public List<PersonnelMemberDto> searchAllPersonnelMembersBySchoolId(String id) {
        List<PersonnelMember> personnelMembers = schoolService.getAllPersonnelBySchool(id);
        List<PersonnelMemberDto> memberDtos =
                (List<PersonnelMemberDto>) ModelMapper.transformAll(personnelMembers, PersonnelMemberDto.class, new MappingSettings().setDepthMax(3));

        return memberDtos;
    }

    @Authorizable(resource = SCHOOL_DEPARTMENT, action = SEARCH)
    public List<SchoolDepartmentDto> searchAllSchoolDepartmentsByTitle(String title) {
        List<SchoolDepartment> departments = schoolService.searchAllDepartmentsByTitle(title);
        List<SchoolDepartmentDto> departmentDtos =
                (List<SchoolDepartmentDto>) ModelMapper.transformAll(departments, SchoolDepartmentDto.class, new MappingSettings().setDepthMax(3));

        return departmentDtos;
    }

    @Authorizable(resource = PERSONNEL, action = SEARCH)
    public List<PersonnelMemberDto> searchAllPersonnelMembersByTitle(String title) {
        List<PersonnelMember> personnelMembers = schoolService.searchAllPersonnelByTitle(title);
        List<PersonnelMemberDto> memberDtos =
                (List<PersonnelMemberDto>) ModelMapper.transformAll(personnelMembers, PersonnelMemberDto.class, new MappingSettings().setDepthMax(3));

        return memberDtos;
    }

    @Authorizable(resource = PERSONNEL, action = SEARCH)
    public List<PersonnelMemberDto> searchAllPersonnelMembersByFirstName(String fname) {
        List<PersonnelMember> personnelMembers = schoolService.searchAllPersonnelByFirstName(fname);
        List<PersonnelMemberDto> memberDtos =
                (List<PersonnelMemberDto>) ModelMapper.transformAll(personnelMembers, PersonnelMemberDto.class, new MappingSettings().setDepthMax(3));

        return memberDtos;
    }

    @Authorizable(resource = PERSONNEL, action = SEARCH)
    public List<PersonnelMemberDto> searchAllPersonnelMembersByLastName(String lname) {
        List<PersonnelMember> personnelMembers = schoolService.searchAllPersonnelByLastName(lname);
        List<PersonnelMemberDto> memberDtos =
                (List<PersonnelMemberDto>) ModelMapper.transformAll(personnelMembers, PersonnelMemberDto.class, new MappingSettings().setDepthMax(3));

        return memberDtos;
    }

    @Authorizable(resource = PERSONNEL, action = SEARCH)
    public List<PersonnelMemberDto> searchAllPersonnelMembersByCode(String code) {
        List<PersonnelMember> personnelMembers = schoolService.searchAllPersonnelByPersonalCode(code);
        List<PersonnelMemberDto> memberDtos =
                (List<PersonnelMemberDto>) ModelMapper.transformAll(personnelMembers, PersonnelMemberDto.class, new MappingSettings().setDepthMax(3));

        return memberDtos;
    }

    @Authorizable(resource = PERSONNEL, action = SEARCH)
    public List<PersonnelMemberDto> searchAllPersonnelMembersByIdentifier(String id) {
        List<PersonnelMember> personnelMembers = schoolService.searchAllPersonnelByIdentifier(id);
        List<PersonnelMemberDto> memberDtos =
                (List<PersonnelMemberDto>) ModelMapper.transformAll(personnelMembers, PersonnelMemberDto.class, new MappingSettings().setDepthMax(3));

        return memberDtos;
    }

    @Authorizable(resource = DISCIPLINE, action = SEARCH)
    public List<DisciplineDto> searchAllDisciplinesByTitle(String title) {
        List<Discipline> disciplines = schoolService.searchAllDisciplinesByTitle(title);
        List<DisciplineDto> memberDtos =
                (List<DisciplineDto>) ModelMapper.transformAll(disciplines, DisciplineDto.class, new MappingSettings().setDepthMax(3));

        return memberDtos;
    }

    @Authorizable(resource = DISCIPLINE, action = SEARCH)
    public List<DisciplineDto> searchAllDisciplinesByDescription(String descr) {
        List<Discipline> disciplines = schoolService.searchAllDisciplinesByDescription(descr);
        List<DisciplineDto> memberDtos =
                (List<DisciplineDto>) ModelMapper.transformAll(disciplines, DisciplineDto.class, new MappingSettings().setDepthMax(3));

        return memberDtos;
    }
}
