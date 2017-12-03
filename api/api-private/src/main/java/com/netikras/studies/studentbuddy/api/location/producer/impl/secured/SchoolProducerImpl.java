package com.netikras.studies.studentbuddy.api.location.producer.impl.secured;

import com.netikras.studies.studentbuddy.api.handlers.DtoMapper;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.CourseDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.DisciplineDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.PersonnelMemberDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.SchoolDepartmentDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.SchoolDto;
import com.netikras.studies.studentbuddy.core.data.api.model.Course;
import com.netikras.studies.studentbuddy.core.data.api.model.Discipline;
import com.netikras.studies.studentbuddy.core.data.api.model.PersonnelMember;
import com.netikras.studies.studentbuddy.core.data.api.model.School;
import com.netikras.studies.studentbuddy.core.data.api.model.SchoolDepartment;
import com.netikras.studies.studentbuddy.core.data.meta.TransactionLauncher;
import com.netikras.studies.studentbuddy.core.data.meta.annotations.Authorizable;
import com.netikras.studies.studentbuddy.core.service.SchoolService;
import com.netikras.studies.studentbuddy.core.validator.EntityProvider;
import com.netikras.tools.common.model.mapper.MappingSettings;
import com.netikras.tools.common.model.mapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

import static com.netikras.studies.studentbuddy.core.data.meta.Action.CREATE;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.DELETE;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.GET;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.GET_ALL;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.MODIFY;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.PURGE;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.SEARCH;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.COURSE;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.DISCIPLINE;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.PERSONNEL;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.SCHOOL;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.SCHOOL_DEPARTMENT;

@Component
public class SchoolProducerImpl {

    @Resource
    private ModelMapper modelMapper;

    @Resource
    private SchoolService schoolService;

    @Resource
    private DtoMapper dtoMapper;
    @Resource
    private EntityProvider entityProvider;
    @Resource
    private TransactionLauncher transactionLauncher;


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
    @Transactional
    public SchoolDto updateSchool(SchoolDto schoolDto) {
        School school = modelMapper.apply(entityProvider.fetch(schoolDto), schoolDto);
        school = schoolService.updateSchool(school);
        schoolDto = (SchoolDto) dtoMapper.toDto(school);

        return schoolDto;
    }

    @Authorizable(resource = SCHOOL, action = CREATE)
    @Transactional
    public SchoolDto createSchool(SchoolDto schoolDto) {
        School school = modelMapper.apply(new School(), schoolDto, new MappingSettings().setForceUpdate(true));
        if (school != null) school.setId(null);
        school = schoolService.createSchool(school);
        schoolDto = (SchoolDto) dtoMapper.toDto(school);

        return schoolDto;
    }

    @Authorizable(resource = SCHOOL, action = GET)
    @Transactional
    public SchoolDto getSchool(String id) {
        School school = schoolService.getSchool(id);
        if (school != null) school.getDepartments();
        SchoolDto schoolDto = (SchoolDto) dtoMapper.toDto(school);
        return schoolDto;
    }

    @Authorizable(resource = SCHOOL_DEPARTMENT, action = DELETE)
    public void deleteSchoolDepartment(String id) {
        schoolService.deleteSchoolDepartment(id);
    }

    @Authorizable(resource = SCHOOL_DEPARTMENT, action = MODIFY)
    @Transactional
    public SchoolDepartmentDto updateSchoolDepartment(SchoolDepartmentDto departmentDto) {
        SchoolDepartment department = modelMapper.apply(entityProvider.fetch(departmentDto), departmentDto);
        department = schoolService.updateSchoolDepartment(department);
        departmentDto = (SchoolDepartmentDto) dtoMapper.toDto(department);

        return departmentDto;
    }

    @Authorizable(resource = SCHOOL_DEPARTMENT, action = CREATE)
    @Transactional
    public SchoolDepartmentDto createSchoolDepartment(SchoolDepartmentDto departmentDto) {
        SchoolDepartment department = modelMapper.apply(new SchoolDepartment(), departmentDto, new MappingSettings().setForceUpdate(true));
        if (department != null) department.setId(null);
        department = schoolService.createSchoolDepartment(department);
        departmentDto = (SchoolDepartmentDto) dtoMapper.toDto(department);

        return departmentDto;
    }

    @Authorizable(resource = SCHOOL_DEPARTMENT, action = GET)
    @Transactional
    public SchoolDepartmentDto getSchoolDepartment(String id) {
        SchoolDepartment department = schoolService.getSchoolDepartment(id);
        if (department != null) {
            department.getBuildings();
        }
        SchoolDepartmentDto departmentDto = (SchoolDepartmentDto) dtoMapper.toDto(department);

        return departmentDto;
    }

    @Authorizable(resource = DISCIPLINE, action = DELETE)
    public void deleteDiscipline(String id) {
        schoolService.deleteDiscipline(id);
    }

    @Authorizable(resource = DISCIPLINE, action = MODIFY)
    @Transactional
    public DisciplineDto updateDiscipline(DisciplineDto disciplineDto) {
        Discipline discipline = modelMapper.apply(entityProvider.fetch(disciplineDto), disciplineDto);
        discipline = schoolService.updateDiscipline(discipline);
        disciplineDto = (DisciplineDto) dtoMapper.toDto(discipline);
        return disciplineDto;
    }

    @Authorizable(resource = DISCIPLINE, action = CREATE)
    @Transactional
    public DisciplineDto createDiscipline(DisciplineDto disciplineDto) {
        Discipline discipline = modelMapper.apply(new Discipline(), disciplineDto, new MappingSettings().setForceUpdate(true));
        if (discipline != null) discipline.setId(null);
        discipline = schoolService.createDiscipline(discipline);
        disciplineDto = (DisciplineDto) dtoMapper.toDto(discipline);
        return disciplineDto;
    }

    @Authorizable(resource = DISCIPLINE, action = GET)
    @Transactional
    public DisciplineDto getDiscipline(String id) {
        Discipline discipline = schoolService.getDiscipline(id);
        DisciplineDto disciplineDto = (DisciplineDto) dtoMapper.toDto(discipline);
        return disciplineDto;
    }


    @Authorizable(resource = COURSE, action = CREATE)
    @Transactional
    public CourseDto createCourse(CourseDto item) {
        Course course = modelMapper.apply(new Course(), item, new MappingSettings().setForceUpdate(true));
        course = schoolService.createCourse(course);
        return (CourseDto) dtoMapper.toDto(course);
    }

    @Authorizable(resource = COURSE, action = DELETE)
    @Transactional
    public void deleteCourse(String id) {
        schoolService.deleteCourse(id);
    }

    @Authorizable(resource = COURSE, action = MODIFY)
    @Transactional
    public CourseDto updateCourse(CourseDto item) {
        Course course = modelMapper.apply(entityProvider.fetch(item), item);
        course = schoolService.updateCourse(course);
        return (CourseDto) dtoMapper.toDto(course);
    }

    @Authorizable(resource = COURSE, action = GET)
    @Transactional
    public CourseDto getCourse(String id) {
        Course course = schoolService.getCourse(id);
        return (CourseDto) dtoMapper.toDto(course);
    }

    @Authorizable(resource = COURSE, action = GET_ALL)
    @Transactional
    public List<CourseDto> getAllCourses() {
        List<Course> courses = schoolService.getAllCourses();
        return (List<CourseDto>) dtoMapper.toDtos(courses);
    }

    @Authorizable(resource = COURSE, action = PURGE)
    public void purgeCourse(String id) {
        schoolService.purgeCourse(id);
    }

    @Authorizable(resource = COURSE, action = GET_ALL)
    @Transactional
    public List<CourseDto> getAllCoursesBySchoolId(String id) {
        List<Course> courses = schoolService.getAllCoursesBySchool(id);
        return (List<CourseDto>) dtoMapper.toDtos(courses);
    }

    @Authorizable(resource = COURSE, action = GET_ALL)
    @Transactional
    public List<CourseDto> getAllCoursesByDisciplineId(String id) {
        List<Course> courses = schoolService.getAllCoursesByDiscipline(id);
        return (List<CourseDto>) dtoMapper.toDtos(courses);
    }

    @Authorizable(resource = COURSE, action = SEARCH)
    @Transactional
    public List<CourseDto> searchAllCoursesByTitle(String title) {
        List<Course> courses = schoolService.searchAllCoursesByTitle(title);
        return (List<CourseDto>) dtoMapper.toDtos(courses);
    }

    @Authorizable(resource = COURSE, action = MODIFY)
    @Transactional
    public CourseDto assignCourseLecture(String courseId, String lectureId) {
        Course course = schoolService.assignCourseLecture(courseId, lectureId);
        return (CourseDto) dtoMapper.toDto(course);
    }

    @Authorizable(resource = COURSE, action = MODIFY)
    @Transactional
    public CourseDto assignCourseLecturer(String courseId, String lecturerId) {
        Course course = schoolService.assignCourseLecturer(courseId, lecturerId);
        return (CourseDto) dtoMapper.toDto(course);
    }

    @Authorizable(resource = COURSE, action = MODIFY)
    @Transactional
    public CourseDto unassignCourseLecturer(String courseId, String lecturerId) {
        Course course = schoolService.unassignCourseLecturer(courseId, lecturerId);
        return (CourseDto) dtoMapper.toDto(course);
    }

    @Authorizable(resource = COURSE, action = MODIFY)
    @Transactional
    public CourseDto unassignCourseLecture(String courseId, String lectureId) {
        Course course = schoolService.unassignCourseLecture(courseId, lectureId);
        return (CourseDto) dtoMapper.toDto(course);
    }

    @Authorizable(resource = PERSONNEL, action = DELETE)
    public void deletePersonnelMember(String id) {
        schoolService.deletePersonnelMember(id);
    }

    @Authorizable(resource = PERSONNEL, action = MODIFY)
    @Transactional
    public PersonnelMemberDto updatePersonnelMember(PersonnelMemberDto personnelMemberDto) {
        PersonnelMember personnelMember = modelMapper.apply(entityProvider.fetch(personnelMemberDto), personnelMemberDto);
        personnelMember = schoolService.updatePersonnelMember(personnelMember);
        personnelMemberDto = (PersonnelMemberDto) dtoMapper.toDto(personnelMember);
        return personnelMemberDto;
    }

    @Authorizable(resource = PERSONNEL, action = CREATE)
    @Transactional
    public PersonnelMemberDto createPersonnelMember(PersonnelMemberDto personnelMemberDto) {
        PersonnelMember personnelMember = modelMapper.apply(new PersonnelMember(), personnelMemberDto, new MappingSettings().setForceUpdate(true));
        if (personnelMember != null) personnelMember.setId(null);
        personnelMember = schoolService.createPersonnelMember(personnelMember);
        personnelMemberDto = (PersonnelMemberDto) dtoMapper.toDto(personnelMember);
        return personnelMemberDto;
    }

    @Authorizable(resource = PERSONNEL, action = GET)
    @Transactional
    public PersonnelMemberDto getPersonnelMember(String id) {
        PersonnelMember personnelMember = schoolService.getPersonnelMember(id);
        PersonnelMemberDto personnelMemberDto = (PersonnelMemberDto) dtoMapper.toDto(personnelMember);
        return personnelMemberDto;
    }

    @Authorizable(resource = SCHOOL, action = GET_ALL)
    @Transactional
    public List<SchoolDto> getAllSchools() {
        List<School> schools = schoolService.getAllSchools();
        List<SchoolDto> schoolDtos = (List<SchoolDto>) dtoMapper.toDtos(schools);
        return schoolDtos;
    }

    @Authorizable(resource = SCHOOL, action = SEARCH)
    @Transactional
    public List<SchoolDto> searchAllSchoolsByTitle(String title) {
        List<School> schools = schoolService.searchAllSchoolsByTitle(title);
        List<SchoolDto> schoolDtos = (List<SchoolDto>) dtoMapper.toDtos(schools);
        return schoolDtos;
    }

    @Authorizable(resource = DISCIPLINE, action = SEARCH)
    @Transactional
    public List<DisciplineDto> getAllDisciplinesBySchoolId(String id) {
        List<Discipline> disciplines = schoolService.getAllDisciplinesBySchoolId(id);
        List<DisciplineDto> disciplineDtos = (List<DisciplineDto>) dtoMapper.toDtos(disciplines);
        return disciplineDtos;
    }

    @Authorizable(resource = PERSONNEL, action = SEARCH)
    @Transactional
    public List<PersonnelMemberDto> searchAllPersonnelMembersBySchoolId(String id) {
        List<PersonnelMember> personnelMembers = schoolService.getAllPersonnelBySchool(id);
        List<PersonnelMemberDto> memberDtos = (List<PersonnelMemberDto>) dtoMapper.toDtos(personnelMembers);

        return memberDtos;
    }

    @Authorizable(resource = SCHOOL_DEPARTMENT, action = SEARCH)
    @Transactional
    public List<SchoolDepartmentDto> searchAllSchoolDepartmentsByTitle(String title) {
        List<SchoolDepartment> departments = schoolService.searchAllDepartmentsByTitle(title);
        List<SchoolDepartmentDto> departmentDtos = (List<SchoolDepartmentDto>) dtoMapper.toDtos(departments);

        return departmentDtos;
    }

    @Authorizable(resource = PERSONNEL, action = SEARCH)
    @Transactional
    public List<PersonnelMemberDto> searchAllPersonnelMembersByTitle(String title) {
        List<PersonnelMember> personnelMembers = schoolService.searchAllPersonnelByTitle(title);
        List<PersonnelMemberDto> memberDtos = (List<PersonnelMemberDto>) dtoMapper.toDtos(personnelMembers);

        return memberDtos;
    }

    @Authorizable(resource = PERSONNEL, action = SEARCH)
    @Transactional
    public List<PersonnelMemberDto> searchAllPersonnelMembersByFirstName(String fname) {
        List<PersonnelMember> personnelMembers = schoolService.searchAllPersonnelByFirstName(fname);
        List<PersonnelMemberDto> memberDtos = (List<PersonnelMemberDto>) dtoMapper.toDtos(personnelMembers);

        return memberDtos;
    }

    @Authorizable(resource = PERSONNEL, action = SEARCH)
    @Transactional
    public List<PersonnelMemberDto> searchAllPersonnelMembersByLastName(String lname) {
        List<PersonnelMember> personnelMembers = schoolService.searchAllPersonnelByLastName(lname);
        List<PersonnelMemberDto> memberDtos = (List<PersonnelMemberDto>) dtoMapper.toDtos(personnelMembers);

        return memberDtos;
    }

    @Authorizable(resource = PERSONNEL, action = SEARCH)
    @Transactional
    public List<PersonnelMemberDto> searchAllPersonnelMembersByCode(String code) {
        List<PersonnelMember> personnelMembers = schoolService.searchAllPersonnelByPersonalCode(code);
        List<PersonnelMemberDto> memberDtos = (List<PersonnelMemberDto>) dtoMapper.toDtos(personnelMembers);

        return memberDtos;
    }

    @Authorizable(resource = PERSONNEL, action = SEARCH)
    @Transactional
    public List<PersonnelMemberDto> searchAllPersonnelMembersByIdentifier(String id) {
        List<PersonnelMember> personnelMembers = schoolService.searchAllPersonnelByIdentifier(id);
        List<PersonnelMemberDto> memberDtos = (List<PersonnelMemberDto>) dtoMapper.toDtos(personnelMembers);

        return memberDtos;
    }

    @Authorizable(resource = DISCIPLINE, action = SEARCH)
    @Transactional
    public List<DisciplineDto> searchAllDisciplinesByTitle(String title) {
        List<Discipline> disciplines = schoolService.searchAllDisciplinesByTitle(title);
        List<DisciplineDto> disciplineDtos = (List<DisciplineDto>) dtoMapper.toDtos(disciplines);

        return disciplineDtos;
    }

    @Authorizable(resource = DISCIPLINE, action = SEARCH)
    @Transactional
    public List<DisciplineDto> searchAllDisciplinesByDescription(String descr) {
        List<Discipline> disciplines = schoolService.searchAllDisciplinesByDescription(descr);
        List<DisciplineDto> disciplineDtos = (List<DisciplineDto>) dtoMapper.toDtos(disciplines);

        return disciplineDtos;
    }

    @Authorizable(resource = DISCIPLINE, action = GET)
    @Transactional
    public List<DisciplineDto> getAllDisciplinesByLecturerId(String id) {
        List<Discipline> disciplines = schoolService.getAllDisciplinesByLecturerId(id);
        List<DisciplineDto> disciplineDtos = (List<DisciplineDto>) dtoMapper.toDtos(disciplines);

        return disciplineDtos;
    }

    @Authorizable(resource = DISCIPLINE, action = GET)
    @Transactional
    public List<CourseDto> getAllCoursesByLecturerId(String id) {
        List<Course> courses = schoolService.getAllCoursesByLecturerId(id);
        List<CourseDto> courseDtos = (List<CourseDto>) dtoMapper.toDtos(courses);

        return courseDtos;
    }

    @Authorizable(resource = DISCIPLINE, action = GET)
    @Transactional
    public List<CourseDto> getCoursesByGroupId(String id) {
        List<Course> courses = schoolService.getAllCoursesByGroupId(id);
        List<CourseDto> courseDtos = (List<CourseDto>) dtoMapper.toDtos(courses);

        return courseDtos;
    }
}
