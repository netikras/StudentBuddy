package com.netikras.studies.studentbuddy.core.service;

import com.netikras.studies.studentbuddy.core.data.api.dto.school.CourseDto;
import com.netikras.studies.studentbuddy.core.data.api.model.Course;
import com.netikras.studies.studentbuddy.core.data.api.model.Discipline;
import com.netikras.studies.studentbuddy.core.data.api.model.PersonnelMember;
import com.netikras.studies.studentbuddy.core.data.api.model.School;
import com.netikras.studies.studentbuddy.core.data.api.model.SchoolDepartment;

import java.util.List;

public interface SchoolService {

    School createSchool(School school);

    School getSchool(String id);

    School updateSchool(School school);

    void deleteSchool(String id);


    void purgeSchool(String id);

    Course getCourse(String id);

    List<Course> getAllCourses();

    List<Course> getAllCoursesByDiscipline(String disciplineId);

    List<Course> getAllCoursesBySchool(String schoolId);

    Course createCourse(Course course);

    Course updateCourse(Course course);

    void deleteCourse(String id);

    void purgeCourse(String id);

    Course assignCourseLecture(String courseId, String lectureId);

    Course unassignCourseLecture(String courseId, String lectureId);

    SchoolDepartment createSchoolDepartment(SchoolDepartment department);

    SchoolDepartment getSchoolDepartment(String id);

    SchoolDepartment updateSchoolDepartment(SchoolDepartment department);

    void deleteSchoolDepartment(String id);


    void purgeSchoolDepartment(String id);

    PersonnelMember createPersonnelMember(PersonnelMember member);

    PersonnelMember getPersonnelMember(String id);

    PersonnelMember updatePersonnelMember(PersonnelMember member);

    void deletePersonnelMember(String id);


    Discipline createDiscipline(Discipline discipline);

    Discipline getDiscipline(String id);

    Discipline updateDiscipline(Discipline discipline);

    void deleteDiscipline(String id);

    List<School> getAllSchools();

    List<School> searchAllSchoolsByTitle(String query);

    List<SchoolDepartment> searchAllDepartmentsByTitle(String query);

    List<PersonnelMember> searchAllPersonnelByTitle(String query);

    List<PersonnelMember> getAllPersonnelByTitle(String title);

    List<PersonnelMember> getAllPersonnelByPerson(String personId);

    List<PersonnelMember> searchAllPersonnelByFirstName(String query);

    List<PersonnelMember> searchAllPersonnelByLastName(String query);

    List<Discipline> searchAllDisciplinesByTitle(String query);

    List<Discipline> searchAllDisciplinesByDescription(String query);

    List<PersonnelMember> searchAllPersonnelByIdentifier(String identifier);

    List<PersonnelMember> searchAllPersonnelByPersonalCode(String code);

    void purgePersonnelMember(String id);

    List<PersonnelMember> getAllPersonnelBySchool(String schoolId);

    void purgeDiscipline(String id);

    List<Discipline> getAllDisciplinesBySchoolId(String id);

    List<Course> searchAllCoursesByTitle(String query);
}
