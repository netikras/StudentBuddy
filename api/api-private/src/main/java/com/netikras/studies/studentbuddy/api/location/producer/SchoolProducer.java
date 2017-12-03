package com.netikras.studies.studentbuddy.api.location.producer;

import com.netikras.studies.studentbuddy.api.location.generated.SchoolApiProducer;
import com.netikras.studies.studentbuddy.api.location.producer.impl.secured.SchoolProducerImpl;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.CourseDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.DisciplineDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.PersonnelMemberDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.SchoolDepartmentDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.SchoolDto;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class SchoolProducer extends SchoolApiProducer {

    @Resource
    private SchoolProducerImpl impl;


    @Override
    protected void onPurgeSchoolDto(String id) {
        impl.purgeSchool(id);
    }

    @Override
    protected void onPurgeSchoolDepartmentDto(String id) {
        impl.purgeSchoolDepartment(id);
    }

    @Override
    protected void onPurgeDisciplineDto(String id) {
        impl.purgeDiscipline(id);
    }

    @Override
    protected void onPurgePersonnelMemberDto(String id) {
        impl.purgePersonnelMember(id);
    }

    @Override
    protected void onDeleteSchoolDto(String id) {
        impl.deleteSchool(id);
    }

    @Override
    protected SchoolDto onUpdateSchoolDto(SchoolDto schoolDto) {
        return impl.updateSchool(schoolDto);
    }

    @Override
    protected SchoolDto onCreateSchoolDto(SchoolDto schoolDto) {
        return impl.createSchool(schoolDto);
    }

    @Override
    protected SchoolDto onRetrieveSchoolDto(String id) {
        return impl.getSchool(id);
    }

    @Override
    protected void onDeleteSchoolDepartmentDto(String id) {
        impl.deleteSchoolDepartment(id);
    }

    @Override
    protected SchoolDepartmentDto onUpdateSchoolDepartmentDto(SchoolDepartmentDto departmentDto) {
        return impl.updateSchoolDepartment(departmentDto);
    }

    @Override
    protected SchoolDepartmentDto onCreateSchoolDepartmentDto(SchoolDepartmentDto departmentDto) {
        return impl.createSchoolDepartment(departmentDto);
    }

    @Override
    protected SchoolDepartmentDto onRetrieveSchoolDepartmentDto(String id) {
        return impl.getSchoolDepartment(id);
    }

    @Override
    protected void onDeleteDisciplineDto(String id) {
        impl.deleteDiscipline(id);
    }

    @Override
    protected DisciplineDto onUpdateDisciplineDto(DisciplineDto disciplineDto) {
        return impl.updateDiscipline(disciplineDto);
    }

    @Override
    protected DisciplineDto onCreateDisciplineDto(DisciplineDto disciplineDto) {
        return impl.createDiscipline(disciplineDto);
    }

    @Override
    protected DisciplineDto onRetrieveDisciplineDto(String id) {
        return impl.getDiscipline(id);
    }


    @Override
    protected CourseDto onCreateCourseDto(CourseDto item) {
        return impl.createCourse(item);
    }

    @Override
    protected void onDeleteCourseDto(String id) {
        impl.deleteCourse(id);
    }

    @Override
    protected CourseDto onUpdateCourseDto(CourseDto item) {
        return impl.updateCourse(item);
    }

    @Override
    protected CourseDto onRetrieveCourseDto(String id) {
        return impl.getCourse(id);
    }

    @Override
    protected List<CourseDto> onGetCourseDtoAll() {
        return impl.getAllCourses();
    }

    @Override
    protected void onPurgeCourseDto(String id) {
        impl.purgeCourse(id);
    }

    @Override
    protected List<CourseDto> onGetCourseDtoAllBySchoolId(String id) {
        return impl.getAllCoursesBySchoolId(id);
    }

    @Override
    protected List<CourseDto> onGetCourseDtoAllByDisciplineId(String id) {
        return impl.getAllCoursesByDisciplineId(id);
    }

    @Override
    protected List<CourseDto> onSearchCourseDtoAllByTitle(String title) {
        return impl.searchAllCoursesByTitle(title);
    }

    @Override
    protected CourseDto onAssignCourseDtoLecture(String courseId, String lectureId) {
        return impl.assignCourseLecture(courseId, lectureId);
    }

    @Override
    protected CourseDto onUnassignCourseDtoLecture(String courseId, String lectureId) {
        return impl.unassignCourseLecture(courseId, lectureId);
    }

    @Override
    protected CourseDto onAssignCourseDtoLecturer(String courseId, String lecturerId) {
        return impl.assignCourseLecturer(courseId, lecturerId);
    }

    @Override
    protected CourseDto onUnassignCourseDtoLecturer(String courseId, String lecturerId) {
        return impl.unassignCourseLecturer(courseId, lecturerId);
    }

    @Override
    protected void onDeletePersonnelMemberDto(String id) {
        impl.deletePersonnelMember(id);
    }

    @Override
    protected PersonnelMemberDto onUpdatePersonnelMemberDto(PersonnelMemberDto personnelMemberDto) {
        return impl.updatePersonnelMember(personnelMemberDto);
    }

    @Override
    protected PersonnelMemberDto onCreatePersonnelMemberDto(PersonnelMemberDto personnelMemberDto) {
        return impl.createPersonnelMember(personnelMemberDto);
    }

    @Override
    protected PersonnelMemberDto onRetrievePersonnelMemberDto(String id) {
        return impl.getPersonnelMember(id);
    }

    @Override
    protected List<SchoolDto> onGetSchoolDtoAll() {
        return impl.getAllSchools();
    }


    @Override
    protected List<DisciplineDto> onGetDisciplineDtoAllByLecturerId(String id) {
        return impl.getAllDisciplinesByLecturerId(id);
    }

    @Override
    protected List<CourseDto> onGetCourseDtoAllByLecturerId(String id) {
        return impl.getAllCoursesByLecturerId(id);
    }

    @Override
    protected List<CourseDto> onGetCourseDtoAllByGroupId(String id) {
        return impl.getCoursesByGroupId(id);
    }

    @Override
    protected List<SchoolDto> onSearchSchoolDtoAllByTitle(String title) {
        return impl.searchAllSchoolsByTitle(title);
    }

    @Override
    protected List<DisciplineDto> onGetDisciplineDtoAllBySchoolId(String id) {
        return impl.getAllDisciplinesBySchoolId(id);
    }

    @Override
    protected List<PersonnelMemberDto> onSearchPersonnelMemberDtoAllBySchoolId(String id) {
        return impl.searchAllPersonnelMembersBySchoolId(id);
    }

    @Override
    protected List<SchoolDepartmentDto> onSearchSchoolDepartmentDtoAllByTitle(String title) {
        return impl.searchAllSchoolDepartmentsByTitle(title);
    }

    @Override
    protected List<PersonnelMemberDto> onSearchPersonnelMemberDtoAllByTitle(String title) {
        return impl.searchAllPersonnelMembersByTitle(title);
    }

    @Override
    protected List<PersonnelMemberDto> onSearchPersonnelMemberDtoAllByFirstName(String fname) {
        return impl.searchAllPersonnelMembersByFirstName(fname);
    }

    @Override
    protected List<PersonnelMemberDto> onSearchPersonnelMemberDtoAllByLastName(String lname) {
        return impl.searchAllPersonnelMembersByLastName(lname);
    }

    @Override
    protected List<PersonnelMemberDto> onSearchPersonnelMemberDtoAllByCode(String code) {
        return impl.searchAllPersonnelMembersByCode(code);
    }

    @Override
    protected List<PersonnelMemberDto> onSearchPersonnelMemberDtoAllByIdentifier(String id) {
        return impl.searchAllPersonnelMembersByIdentifier(id);
    }

    @Override
    protected List<DisciplineDto> onSearchDisciplineDtoAllByTitle(String title) {
        return impl.searchAllDisciplinesByTitle(title);
    }

    @Override
    protected List<DisciplineDto> onSearchDisciplineDtoAllByDescription(String descr) {
        return impl.searchAllDisciplinesByDescription(descr);
    }
}
