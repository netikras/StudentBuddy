package com.netikras.studies.studentbuddy.core.service.impl;

import com.netikras.studies.studentbuddy.commons.exception.StudBudUncheckedException;
import com.netikras.studies.studentbuddy.core.data.api.dao.CourseDao;
import com.netikras.studies.studentbuddy.core.data.api.dao.DisciplineDao;
import com.netikras.studies.studentbuddy.core.data.api.dao.PersonnelDao;
import com.netikras.studies.studentbuddy.core.data.api.dao.SchoolDao;
import com.netikras.studies.studentbuddy.core.data.api.dao.SchoolDepartmentDao;
import com.netikras.studies.studentbuddy.core.data.api.model.Course;
import com.netikras.studies.studentbuddy.core.data.api.model.CourseLecturer;
import com.netikras.studies.studentbuddy.core.data.api.model.Discipline;
import com.netikras.studies.studentbuddy.core.data.api.model.Lecture;
import com.netikras.studies.studentbuddy.core.data.api.model.PersonnelMember;
import com.netikras.studies.studentbuddy.core.data.api.model.School;
import com.netikras.studies.studentbuddy.core.data.api.model.SchoolDepartment;
import com.netikras.studies.studentbuddy.core.service.LectureService;
import com.netikras.studies.studentbuddy.core.service.LecturerService;
import com.netikras.studies.studentbuddy.core.service.SchoolService;
import com.netikras.studies.studentbuddy.core.service.StudentService;
import com.netikras.studies.studentbuddy.core.validator.SchoolValidator;
import com.netikras.tools.common.exception.ErrorsCollection;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static com.netikras.tools.common.remote.http.HttpStatus.BAD_REQUEST;
import static com.netikras.tools.common.security.IntegrityUtils.isNullOrEmpty;

@Service
public class SchoolServiceImpl implements SchoolService {

    @Resource
    private SchoolDao schoolDao;

    @Resource
    private DisciplineDao disciplineDao;

    @Resource
    private CourseDao courseDao;

    @Resource
    private SchoolDepartmentDao departmentDao;

    @Resource
    private PersonnelDao personnelDao;

    @Resource
    private SchoolValidator schoolValidator;

    @Resource
    private LectureService lectureService;
    @Resource
    private LecturerService lecturerService;
    @Resource
    private StudentService studentService;

    @Resource
    private EntityManager entityManager;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public School createSchool(School school) {
        List<SchoolDepartment> departments = school.getDepartments();
        school.setDepartments(null);

        ErrorsCollection errors = schoolValidator.validateForCreation(school, null);
        if (!errors.isEmpty()) {
            throw new StudBudUncheckedException()
                    .setMessage1("Cannot create new school")
                    .setMessage2("Validation errors: " + errors.size())
                    .setErrors(errors)
                    .setStatusCode(BAD_REQUEST)
                    ;
        }

        school = schoolDao.save(school);

        if (!isNullOrEmpty(departments)) {
            for (SchoolDepartment department : departments) {
                department.setSchool(school);
                createSchoolDepartment(department);
            }
        }

        school = schoolDao.findOne(school.getId());

        return school;
    }

    @Override
    @Transactional
    public School getSchool(String id) {
        return schoolDao.findOne(id);
    }

    @Override
    @Transactional
    public School updateSchool(School school) {
        return schoolDao.save(school);
    }

    @Override
    @Transactional
    public void deleteSchool(String id) {
        School school = getSchool(id);
        ErrorsCollection errors = schoolValidator.validateForRemoval(school, null);
        if (!errors.isEmpty()) {
            throw new StudBudUncheckedException()
                    .setMessage1("Cannot remove school")
                    .setMessage2("Validation errors: " + errors.size())
                    .setProbableCause(id)
                    .setErrors(errors)
                    .setStatusCode(BAD_REQUEST)
                    ;
        }
        schoolDao.delete(id);
    }

    @Override
    @Transactional
    public void purgeSchool(String id) {
        School school = getSchool(id);
        if (school == null) {
            return;
        }

        if (!isNullOrEmpty(school.getDepartments())) {
            school.getDepartments().forEach(department -> purgeSchoolDepartment(department.getId()));
            school.getDepartments().clear();
        }

        if (!isNullOrEmpty(school.getDisciplines())) {
            school.getDisciplines().forEach(discipline -> purgeDiscipline(discipline.getId()));
            school.getDisciplines().clear();
        }

        if (!isNullOrEmpty(school.getLecturers())) {
            school.getLecturers().forEach(lecturer -> lecturerService.purgeLecturer(lecturer.getId()));
            school.getLecturers().clear();
        }

        if (!isNullOrEmpty(school.getPersonnel())) {
            school.getPersonnel().forEach(member -> purgePersonnelMember(member.getId()));
            school.getPersonnel().clear();
        }

        if (!isNullOrEmpty(school.getStudents())) {
            school.getStudents().forEach(student -> studentService.purgeStudent(student.getId()));
            school.getStudents().clear();
        }

        if (!isNullOrEmpty(school.getGroups())) {
            school.getGroups().forEach(group -> studentService.purgeStudentsGroup(group.getId()));
            school.getGroups().clear();
        }

        school = getSchool(id);

        schoolDao.delete(id);
    }

    @Override
    @Transactional
    public Course getCourse(String id) {
        return courseDao.findOne(id);
    }

    @Override
    @Transactional
    public List<Course> getAllCourses() {
        return courseDao.findAll();
    }

    @Override
    @Transactional
    public List<Course> getAllCoursesByDiscipline(String disciplineId) {
        return courseDao.findAllByDiscipline_Id(disciplineId);
    }

    @Override
    @Transactional
    public List<Course> getAllCoursesBySchool(String schoolId) {
        return courseDao.findAllByDiscipline_School_Id(schoolId);
    }

    @Override
    @Transactional
    public Course createCourse(Course course) {
        ErrorsCollection errors = schoolValidator.validateForCreation(course, null);
        if (!errors.isEmpty()) {
            throw new StudBudUncheckedException()
                    .setMessage1("Cannot create new course")
                    .setMessage2("Validation errors: " + errors.size())
                    .setErrors(errors)
                    .setStatusCode(BAD_REQUEST)
                    ;
        }

        return courseDao.save(course);
    }

    @Override
    @Transactional
    public Course updateCourse(Course course) {
        return courseDao.save(course);
    }

    @Override
    @Transactional
    public void deleteCourse(String id) {
        courseDao.delete(id);
    }

    @Override
    @Transactional
    public void purgeCourse(String id) {
        Course course = getCourse(id);
        if (course == null) return;

        if (!isNullOrEmpty(course.getLecturers())) {
            for (CourseLecturer courseLecturer : course.getLecturers()) {
                courseLecturer.getLecturer().setCourseLecturers(null);
                lecturerService.updateLecturer(courseLecturer.getLecturer());
            }
            course.getLecturers().clear();
        }

        if (!isNullOrEmpty(course.getLectures())) {
            for (Lecture lecture : course.getLectures()) {
                lecture.setCourse(null);
//                lectureService.updateLecture(lecture);
            }
            course.getLectures().clear();
        }

        courseDao.delete(course.getId());
    }

    @Override
    @Transactional
    public Course assignCourseLecture(String courseId, String lectureId) {
        Course course = getCourse(courseId);
        if (course == null) {
            return null;
        }

        Lecture lecture = lectureService.getLecture(lectureId);
        if (lecture == null) {
            return null;
        }

        if (course.getLectures().contains(lecture)) {
            return course;
        }

        lecture.setCourse(course);

        lecture = lectureService.updateLecture(lecture);
        if (lecture == null
                || lecture.getCourse() == null
                || !courseId.equals(lecture.getCourse().getId())) {
            throw new StudBudUncheckedException()
                    .setMessage1("Cannot assign lecture to a course")
                    .setStatusCode(BAD_REQUEST)
                    ;
        }

        course.addLecture(lecture);

        return course;
    }

    @Override
    @Transactional
    public Course unassignCourseLecture(String courseId, String lectureId) {
        Course course = getCourse(courseId);
        if (course == null) {
            return null;
        }

        if (isNullOrEmpty(course.getLectures())) {
            return course;
        }

        course.getLectures().removeIf(lecture -> lecture.getId().equals(lectureId));
        course = updateCourse(course);
        return course;
    }

    @Override
    @Transactional
    public SchoolDepartment createSchoolDepartment(SchoolDepartment department) {
        ErrorsCollection errors = schoolValidator.validateForCreation(department, null);
        if (!errors.isEmpty()) {
            throw new StudBudUncheckedException()
                    .setMessage1("Cannot create new department")
                    .setMessage2("Validation errors: " + errors.size())
                    .setErrors(errors)
                    .setStatusCode(BAD_REQUEST)
                    ;
        }
        return departmentDao.save(department);
    }

    @Override
    @Transactional
    public SchoolDepartment getSchoolDepartment(String id) {
        return departmentDao.findOne(id);
    }

    @Override
    @Transactional
    public SchoolDepartment updateSchoolDepartment(SchoolDepartment department) {
        return departmentDao.save(department);
    }

    @Override
    @Transactional
    public void deleteSchoolDepartment(String id) {
        SchoolDepartment department = getSchoolDepartment(id);
        ErrorsCollection errors = schoolValidator.validateForRemoval(department, null);
        if (!errors.isEmpty()) {
            throw new StudBudUncheckedException()
                    .setMessage1("Cannot remove school department")
                    .setMessage2("Validation errors: " + errors.size())
                    .setProbableCause(id)
                    .setErrors(errors)
                    .setStatusCode(BAD_REQUEST)
                    ;
        }
        departmentDao.delete(id);
    }

    @Override
    @Transactional
    public void purgeSchoolDepartment(String id) {
        SchoolDepartment department = getSchoolDepartment(id);
        if (department == null) {
            return;
        }

        departmentDao.delete(id);
    }

    @Override
    @Transactional
    public PersonnelMember createPersonnelMember(PersonnelMember member) {
        ErrorsCollection errors = schoolValidator.validateForCreation(member, null);
        if (!errors.isEmpty()) {
            throw new StudBudUncheckedException()
                    .setMessage1("Cannot create new personnel member")
                    .setMessage2("Validation errors: " + errors.size())
                    .setErrors(errors)
                    .setStatusCode(BAD_REQUEST)
                    ;
        }
        return personnelDao.save(member);
    }

    @Override
    @Transactional
    public PersonnelMember getPersonnelMember(String id) {
        return personnelDao.findOne(id);
    }

    @Override
    @Transactional
    public PersonnelMember updatePersonnelMember(PersonnelMember member) {
        return personnelDao.save(member);
    }

    @Override
    @Transactional
    public void deletePersonnelMember(String id) {
        PersonnelMember personnelMember = getPersonnelMember(id);
        ErrorsCollection errors = schoolValidator.validateForRemoval(personnelMember, null);
        if (!errors.isEmpty()) {
            throw new StudBudUncheckedException()
                    .setMessage1("Cannot remove personnel member")
                    .setMessage2("Validation errors: " + errors.size())
                    .setProbableCause(id)
                    .setErrors(errors)
                    .setStatusCode(BAD_REQUEST)
                    ;
        }
        personnelDao.delete(id);
    }

    @Override
    @Transactional
    public void purgePersonnelMember(String id) {
        PersonnelMember personnelMember = getPersonnelMember(id);
        if (personnelMember == null) {
            return;
        }

        personnelDao.delete(id);
    }

    @Override
    @Transactional
    public List<PersonnelMember> getAllPersonnelBySchool(String schoolId) {
        return personnelDao.findAllBySchool_Id(schoolId);
    }

    @Override
    @Transactional
    public Discipline createDiscipline(Discipline discipline) {
        ErrorsCollection errors = schoolValidator.validateForCreation(discipline, null);
        if (!errors.isEmpty()) {
            throw new StudBudUncheckedException()
                    .setMessage1("Cannot create discipline")
                    .setMessage2("Validation errors: " + errors.size())
//                    .setProbableCause()
                    .setErrors(errors)
                    .setStatusCode(BAD_REQUEST)
                    ;
        }
        return disciplineDao.save(discipline);
    }

    @Override
    @Transactional
    public Discipline getDiscipline(String id) {
        return disciplineDao.findOne(id);
    }

    @Override
    @Transactional
    public Discipline updateDiscipline(Discipline discipline) {
        ErrorsCollection errors = schoolValidator.validateForUpdate(discipline, null);
        if (!errors.isEmpty()) {
            throw new StudBudUncheckedException()
                    .setMessage1("Cannot update discipline")
                    .setMessage2("Validation errors: " + errors.size())
//                    .setProbableCause()
                    .setErrors(errors)
                    .setStatusCode(BAD_REQUEST)
                    ;
        }
        return disciplineDao.save(discipline);
    }

    @Override
    @Transactional
    public void deleteDiscipline(String id) {
        Discipline discipline = getDiscipline(id);
        ErrorsCollection errors = schoolValidator.validateForRemoval(discipline, null);
        if (!errors.isEmpty()) {
            throw new StudBudUncheckedException()
                    .setMessage1("Cannot remove discipline")
                    .setMessage2("Validation errors: " + errors.size())
                    .setProbableCause(id)
                    .setErrors(errors)
                    .setStatusCode(BAD_REQUEST)
                    ;
        }
        disciplineDao.delete(id);
    }

    @Override
    @Transactional
    public void purgeDiscipline(String id) {
        Discipline discipline = getDiscipline(id);
        if (discipline == null) {
            return;
        }

        if (!isNullOrEmpty(discipline.getLectures())) {
            List<String> ids = new ArrayList<>();
            discipline.getLectures().forEach(lecture -> ids.add(lecture.getId()));
            lectureService.purgeLectures(ids);
            discipline.getLectures().clear();
        }

        if (!isNullOrEmpty(discipline.getAssignments())) {
            discipline.getAssignments().forEach(assignment -> lectureService.purgeLectureAssignment(assignment.getId()));
            discipline.getAssignments().clear();
        }

        if (!isNullOrEmpty(discipline.getTests())) {
            discipline.getTests().forEach(test -> lectureService.purgeLectureTest(test.getId()));
            discipline.getTests().clear();
        }

        if (!isNullOrEmpty(discipline.getCourses())) {
            discipline.getCourses().forEach(course -> purgeCourse(course.getId()));
            discipline.getCourses().clear();
        }

        if (discipline.getSchool() != null && !isNullOrEmpty(discipline.getSchool().getDisciplines())) {
            discipline.getSchool().getDisciplines().removeIf(d -> d != null && id.equals(d.getId()));
        }

        disciplineDao.delete(id);
    }

    @Override
    @Transactional
    public List<Discipline> getAllDisciplinesBySchoolId(String id) {
        return disciplineDao.findAllBySchool_Id(id);
    }

    @Override
    @Transactional
    public List<School> getAllSchools() {
        return schoolDao.findAll();
    }


    // search


    @Override
    @Transactional
    public List<School> searchAllSchoolsByTitle(String query) {
        return schoolDao.findAllByTitleLikeIgnoreCase(schoolDao.wrapSearchString(query));
    }

    @Override
    @Transactional
    public List<SchoolDepartment> searchAllDepartmentsByTitle(String query) {
        return departmentDao.findAllByTitleLikeIgnoreCase(schoolDao.wrapSearchString(query));
    }

    @Override
    @Transactional
    public List<PersonnelMember> searchAllPersonnelByTitle(String query) {
        return personnelDao.findAllByTitleLikeIgnoreCase(personnelDao.wrapSearchString(query));
    }

    @Override
    @Transactional
    public List<PersonnelMember> getAllPersonnelByTitle(String title) {
        return personnelDao.findAllByTitle(title);
    }

    @Override
    @Transactional
    public List<PersonnelMember> getAllPersonnelByPerson(String personId) {
        return personnelDao.findAllByPerson_Id(personId);
    }

    @Override
    @Transactional
    public List<PersonnelMember> searchAllPersonnelByFirstName(String query) {
        return personnelDao.findAllByPerson_FirstNameLikeIgnoreCase(personnelDao.wrapSearchString(query));
    }

    @Override
    @Transactional
    public List<PersonnelMember> searchAllPersonnelByLastName(String query) {
        return personnelDao.findAllByPerson_LastNameLikeIgnoreCase(personnelDao.wrapSearchString(query));
    }

    @Override
    @Transactional
    public List<PersonnelMember> searchAllPersonnelByIdentifier(String identifier) {
        return personnelDao.findAllByPerson_IdentificationIgnoreCase(personnelDao.wrapSearchString(identifier));
    }

    @Override
    @Transactional
    public List<PersonnelMember> searchAllPersonnelByPersonalCode(String code) {
        return personnelDao.findAllByPerson_PersonalCodeLikeIgnoreCase(personnelDao.wrapSearchString(code));
    }

    @Override
    @Transactional
    public List<Discipline> searchAllDisciplinesByTitle(String query) {
        return disciplineDao.findAllByTitleLikeIgnoreCase(disciplineDao.wrapSearchString(query));
    }

    @Override
    @Transactional
    public List<Discipline> searchAllDisciplinesByDescription(String query) {
        return disciplineDao.findAllByDescriptionLikeIgnoreCase(disciplineDao.wrapSearchString(query));
    }

    @Override
    @Transactional
    public List<Course> searchAllCoursesByTitle(String query) {
        return courseDao.findAllByTitleLikeIgnoreCase(courseDao.wrapSearchString(query));
    }

    @Override
    @Transactional
    public List<Course> getAllCoursesByGroupId(String id) {
        return courseDao.findAllByGroup_Id(id);
    }

    @Override
    @Transactional
    public List<Course> getAllCoursesByLecturerId(String id) {
        return courseDao.findAllByLecturers_IdContains(id);
    }

    @Override
    @Transactional
    public List<Discipline> getAllDisciplinesByLecturerId(String id) {
        return disciplineDao.findAllByLecturers_IdContains(id);
    }
}
