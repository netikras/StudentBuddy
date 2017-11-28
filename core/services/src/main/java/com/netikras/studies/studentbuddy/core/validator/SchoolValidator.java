package com.netikras.studies.studentbuddy.core.validator;

import com.netikras.studies.studentbuddy.core.data.api.dao.CourseDao;
import com.netikras.studies.studentbuddy.core.data.api.dao.DisciplineDao;
import com.netikras.studies.studentbuddy.core.data.api.dao.LectureDao;
import com.netikras.studies.studentbuddy.core.data.api.dao.LecturerDao;
import com.netikras.studies.studentbuddy.core.data.api.dao.PersonnelDao;
import com.netikras.studies.studentbuddy.core.data.api.dao.ResourceRepoProvider;
import com.netikras.studies.studentbuddy.core.data.api.dao.SchoolDao;
import com.netikras.studies.studentbuddy.core.data.api.model.Building;
import com.netikras.studies.studentbuddy.core.data.api.model.Course;
import com.netikras.studies.studentbuddy.core.data.api.model.Discipline;
import com.netikras.studies.studentbuddy.core.data.api.model.DisciplineLecturer;
import com.netikras.studies.studentbuddy.core.data.api.model.Lecture;
import com.netikras.studies.studentbuddy.core.data.api.model.Lecturer;
import com.netikras.studies.studentbuddy.core.data.api.model.PersonnelMember;
import com.netikras.studies.studentbuddy.core.data.api.model.School;
import com.netikras.studies.studentbuddy.core.data.api.model.SchoolDepartment;
import com.netikras.studies.studentbuddy.core.data.api.model.Student;
import com.netikras.studies.studentbuddy.core.data.api.model.StudentsGroup;
import com.netikras.tools.common.exception.ErrorsCollection;
import com.netikras.tools.common.exception.ValidationError;
import com.netikras.tools.common.model.mapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static com.netikras.tools.common.remote.http.HttpStatus.BAD_REQUEST;
import static com.netikras.tools.common.remote.http.HttpStatus.CONFLICT;
import static com.netikras.tools.common.remote.http.HttpStatus.NOT_FOUND;
import static com.netikras.tools.common.security.IntegrityUtils.ensureValue;
import static com.netikras.tools.common.security.IntegrityUtils.isNullOrEmpty;

@Component
public class SchoolValidator {

    @Resource
    private SchoolDao schoolDao;
    @Resource
    private PersonnelDao personnelDao;
    @Resource
    private PersonValidator personValidator;
    @Resource
    private LecturerDao lecturerDao;
    @Resource
    private LectureDao lectureDao;
    @Resource
    private DisciplineDao disciplineDao;
    @Resource
    private CourseDao courseDao;
    @Resource
    private LectureValidator lectureValidator;
    @Resource
    private ResourceRepoProvider repoProvider;
    @Resource
    private EntityProvider entityProvider;
    @Resource
    private ModelMapper modelMapper;


    @Transactional
    public ErrorsCollection validateForCreation(School school, ErrorsCollection errors) {
        errors = ensure(errors);

        if (school == null) {
            errors.add(new ValidationError()
                    .setSuggestion("Cannot create a non-existing school")
                    .setMessage1("School is not given")
                    .setStatus(NOT_FOUND.getCode())
            );
            return errors;
        }

        if (isNullOrEmpty(school.getTitle())) {
            errors.add(new ValidationError()
                    .setSuggestion("Every school must have a title")
                    .setMessage1("School title missing")
                    .setStatus(NOT_FOUND.getCode())
            );
        }

        school.setId(null);
        return errors;
    }


    @Transactional
    public ErrorsCollection validateForCreation(SchoolDepartment department, ErrorsCollection errors) {
        errors = ensure(errors);

        if (department == null) {
            errors.add(new ValidationError()
                    .setSuggestion("Cannot create a non-existing school department")
                    .setMessage1("Department is not given")
                    .setStatus(NOT_FOUND.getCode())
            );
            return errors;
        }

        if (isNullOrEmpty(department.getTitle())) {
            errors.add(new ValidationError()
                    .setSuggestion("Title is mandatory for all school departments")
                    .setMessage1("Department title is missing")
                    .setStatus(NOT_FOUND.getCode())
            );
        }

        department.setSchool(fetch(department.getSchool()));

        if (!isNullOrEmpty(department.getBuildings())) {
            List<Building> buildings = new ArrayList<>(department.getBuildings().size());
            for (Building building : department.getBuildings()) {
                if (building == null) {
                    continue;
                }

                if (!isNullOrEmpty(building.getId())) {
                    building = entityProvider.fetch(building);
                    if (building != null) {
                        buildings.add(building);
                    }
                } else {
                    buildings.add(building);
                }
            }

            department.setBuildings(buildings);
        }

        if (department.getSchool() == null) {
            errors.add(new ValidationError()
                    .setSuggestion("New department must be linked to an already existing school")
                    .setMessage1("School is missing")
                    .setStatus(NOT_FOUND.getCode())
            );
        }

        department.setId(null);
        return errors;
    }

    @Transactional
    public ErrorsCollection validateForUpdate(SchoolDepartment department, ErrorsCollection errors) {
        errors = ensure(errors);

        if (department == null) {
            errors.add(new ValidationError()
                    .setSuggestion("Cannot update a non-existing school department")
                    .setMessage1("Department is not given")
                    .setStatus(NOT_FOUND.getCode())
            );
            return errors;
        }


        return errors;
    }

    @Transactional
    public ErrorsCollection validateForCreation(PersonnelMember personnelMember, ErrorsCollection errors) {
        errors = ensure(errors);

        if (personnelMember == null) {
            errors.add(new ValidationError()
                    .setSuggestion("Cannot create a non-existing personnel member")
                    .setMessage1("Personnel member is not given")
                    .setStatus(NOT_FOUND.getCode())
            );
            return errors;
        }

        String title = personnelMember.getTitle();

        if (isNullOrEmpty(title)) {
            errors.add(new ValidationError()
                    .setSuggestion("Personnel member must have some title, e.g. director, CTO, etc.")
                    .setMessage1("Personnel member title is missing")
                    .setStatus(NOT_FOUND.getCode())
            );
        }

        personnelMember.setSchool(fetch(personnelMember.getSchool()));

        if (personnelMember.getSchool() == null) {
            errors.add(new ValidationError()
                    .setSuggestion("Personnel member must be linked to an already existing school")
                    .setMessage1("Personnel member school is missing")
                    .setStatus(NOT_FOUND.getCode())
            );
        }

        personnelMember.setPerson(personValidator.fetchPerson(personnelMember.getPerson()));

        if (personnelMember.getPerson() == null) {
            errors.add(new ValidationError()
                    .setSuggestion("Personnel member must be linked to an already existing person")
                    .setMessage1("Personnel member person is missing")
                    .setStatus(NOT_FOUND.getCode())
            );
        }

        personnelMember.setId(null);
        return errors;
    }

    @Transactional
    public ErrorsCollection validateForCreation(Lecturer lecturer, ErrorsCollection errors) {
        errors = ensure(errors);

        if (lecturer == null) {
            errors.add(new ValidationError()
                    .setSuggestion("Cannot create a non-existing lecturer")
                    .setMessage1("Lecturer is not given")
                    .setStatus(NOT_FOUND.getCode())
            );
            return errors;
        }

        lecturer.setPerson(personValidator.fetchPerson(lecturer.getPerson()));

        if (lecturer.getPerson() == null) {
            errors.add(new ValidationError()
                    .setSuggestion("Lecturer must be linked to an already existing person")
                    .setMessage1("Lecturer person is missing")
                    .setStatus(NOT_FOUND.getCode())
            );
        }

        List<DisciplineLecturer> disciplineLecturers = lecturer.getDisciplineLecturers();
        if (disciplineLecturers != null) {
            for (DisciplineLecturer disciplineLecturer : disciplineLecturers) {
                disciplineLecturer.setId(null);
                Discipline discipline = fetch(disciplineLecturer.getDiscipline());
                if (discipline == null) {
                    discipline = disciplineLecturer.getDiscipline();
                    errors.add(new ValidationError()
                            .setSuggestion("Lecturer must be linked to an existing discipline")
                            .setMessage1("Lecturer discipline is missing")
                            .setCausedBy(discipline == null ? "" : discipline.getTitle())
                            .setStatus(NOT_FOUND.getCode())
                    );
                }
            }
        }

        lecturer.setId(null);
        return errors;
    }


    @Transactional
    public ErrorsCollection validateForCreation(Discipline discipline, ErrorsCollection errors) {
        errors = ensure(errors);

        if (discipline == null) {
            errors.add(new ValidationError()
                    .setSuggestion("Cannot create a non-existing discipline")
                    .setMessage1("Discipline is not given")
                    .setStatus(NOT_FOUND.getCode())
            );
            return errors;
        }

        discipline.setSchool(entityProvider.fetch(discipline.getSchool()));

        if (discipline.getSchool() == null) {
            errors.add(new ValidationError()
                    .setSuggestion("Discipline must be linked to an existing school entry")
                    .setMessage1("Discipline school is missing")
                    .setStatus(NOT_FOUND.getCode())
            );
        }

        if (isNullOrEmpty(discipline.getTitle())) {
            errors.add(new ValidationError()
                    .setSuggestion("Discipline must have a title - something one would recognize what that is, smth like: Math, History, etc.")
                    .setMessage1("Discipline school is missing")
                    .setStatus(NOT_FOUND.getCode())
            );
        }


        discipline.setId(null);
        return errors;
    }

    @Transactional
    public ErrorsCollection validateForUpdate(Discipline discipline, ErrorsCollection errors) {
        errors = ensure(errors);

        if (discipline == null) {
            errors.add(new ValidationError()
                    .setSuggestion("Cannot update a non-existing discipline")
                    .setMessage1("Discipline is not given")
                    .setStatus(NOT_FOUND.getCode())
            );
            return errors;
        }

        discipline.setSchool(entityProvider.fetch(discipline.getSchool()));

        if (discipline.getSchool() == null) {
            errors.add(new ValidationError()
                    .setSuggestion("Discipline must be always linked to a school")
                    .setMessage1("School missing")
                    .setStatus(NOT_FOUND.getCode())
            );
            return errors;
        }

        return errors;
    }

    public ErrorsCollection validateForCreation(Course course, ErrorsCollection errors) {
        errors = ensure(errors);

        if (course == null) {
            errors.add(new ValidationError()
                    .setSuggestion("Cannot create a non-existing course")
                    .setMessage1("Course is not given")
                    .setStatus(NOT_FOUND.getCode())
            );
            return errors;
        }

        Discipline discipline = course.getDiscipline();

        if (discipline == null || isNullOrEmpty(discipline.getId()) || disciplineDao.findOne(discipline.getId()) == null) {
            errors.add(new ValidationError()
                    .setSuggestion("A course represents a set of discipline lectures hence discipline association is mandatory")
                    .setMessage1("Course discipline is missing")
                    .setStatus(NOT_FOUND.getCode())
            );
        }

        if (course.getTitle() != null) {
            Course existingCourse = courseDao.findByTitle(course.getTitle());
            if (existingCourse != null) {
                errors.add(new ValidationError()
                        .setSuggestion("Each course must have a unique title or no title at all")
                        .setMessage1("Course with such title already exists")
                        .setCausedBy(existingCourse.getTitle())
                        .setStatus(CONFLICT.getCode())
                );
            }
        }

        course.setId(null);
        return errors;
    }

    @Transactional
    public ErrorsCollection validateForMultiplication(List<Lecture> lectures, ErrorsCollection errors) {
        errors = ensure(errors);

        if (isNullOrEmpty(lectures)) {
            errors.add(new ValidationError()
                    .setSuggestion("Cannot multiply non-existing lectures")
                    .setMessage1("Lectures are not given")
                    .setStatus(NOT_FOUND.getCode())
            );
            return errors;
        }

        Lecture lecture = lectures.get(0);
        ErrorsCollection moreErrors = lectureValidator.validateForCreation(lecture, null);
        if (!moreErrors.isEmpty()) {
            errors.addAll(moreErrors);
            return errors;
        }

        boolean lctrrOk = true;
        boolean discOk = true;
        boolean groupOk = true;
        boolean roomOk = true;


        for (Lecture lect : lectures) {
            if (lctrrOk && !lecture.getDiscipline().equals(lect.getDiscipline())) {
                errors.add(new ValidationError()
                        .setSuggestion("At least one lecture in the batch has different properties than others. " +
                                "All lectures processed in a batch must be alike with the exception for timing")
                        .setMessage1("Different lecturer")
                        .setStatus(BAD_REQUEST.getCode()));
                lctrrOk = false;
                if (!and(lctrrOk, discOk, groupOk, roomOk)) break;
            }
            if (discOk && !lecture.getLecturer().equals(lect.getLecturer())) {
                errors.add(new ValidationError()
                        .setSuggestion("At least one lecture in the batch has different properties than others. " +
                                "All lectures processed in a batch must be alike with the exception for timing")
                        .setMessage1("Different discipline")
                        .setStatus(BAD_REQUEST.getCode()));
                discOk = false;
                if (!and(lctrrOk, discOk, groupOk, roomOk)) break;
            }
            if (groupOk && !lecture.getStudentsGroup().equals(lect.getStudentsGroup())) {
                errors.add(new ValidationError()
                        .setSuggestion("At least one lecture in the batch has different properties than others. " +
                                "All lectures processed in a batch must be alike with the exception for timing")
                        .setMessage1("Different group")
                        .setStatus(BAD_REQUEST.getCode()));
                groupOk = false;
                if (!and(lctrrOk, discOk, groupOk, roomOk)) break;
            }
            if (roomOk && !lecture.getRoom().equals(lect.getRoom())) {
                errors.add(new ValidationError()
                        .setSuggestion("At least one lecture in the batch has different properties than others. " +
                                "All lectures processed in a batch must be alike with the exception for timing")
                        .setMessage1("Different room")
                        .setStatus(BAD_REQUEST.getCode()));
                roomOk = false;
                if (!and(lctrrOk, discOk, groupOk, roomOk)) break;
            }

            lect.setId(null);
        }

        return errors;
    }




    @Transactional
    public ErrorsCollection validateForRemoval(Discipline discipline, ErrorsCollection errors) {
        errors = ensure(errors);

        if (discipline == null) {
            errors.add(new ValidationError()
                    .setSuggestion("Cannot delete a non-existing discipline")
                    .setMessage1("Discipline is not given")
                    .setStatus(NOT_FOUND.getCode())
            );
            return errors;
        }

        List<DisciplineLecturer> lecturers = discipline.getLecturers();
        int lecturesCount = lectureDao.countAllByDiscipline_Id(discipline.getId());


        if (!isNullOrEmpty(lecturers)) {
            StringBuilder ids = new StringBuilder();
            lecturers.forEach(lecturer -> ids.append(lecturer.getId()).append(" "));
            errors.add(new ValidationError()
                    .setSuggestion("Cannot delete discipline linked to other downstream entities, such as websites, lecturers, lectures, etc.")
                    .setMessage1("Discipline is linked to lecturers")
                    .setCausedBy(ids.toString())
                    .setStatus(CONFLICT.getCode())
            );
        }

        if (lecturesCount > 0) {
            errors.add(new ValidationError()
                    .setSuggestion("Cannot delete discipline linked to other downstream entities, such as websites, lecturers, lectures, etc.")
                    .setMessage1("Discipline is linked to lectures")
                    .setCausedBy("" + lecturesCount)
                    .setStatus(CONFLICT.getCode())
            );
        }

        return errors;
    }


    @Transactional
    public ErrorsCollection validateForRemoval(Lecturer lecturer, ErrorsCollection errors) {
        errors = ensure(errors);

        if (lecturer == null) {
            errors.add(new ValidationError()
                    .setSuggestion("Cannot delete a non-existing lecturer")
                    .setMessage1("Lecturer is not given")
                    .setStatus(NOT_FOUND.getCode())
            );
            return errors;
        }

        List<Lecture> lectures = lecturer.getLectures();

        if (!isNullOrEmpty(lectures)) {
            StringBuilder ids = new StringBuilder();
            lectures.forEach(lecture -> ids.append(lecture.getId()).append(" "));
            errors.add(new ValidationError()
                    .setSuggestion("Cannot delete lecturer linked to other downstream entities, such as lectures, etc.")
                    .setMessage1("School is linked to lectures")
                    .setCausedBy(ids.toString())
                    .setStatus(CONFLICT.getCode())
            );
        }

        return errors;
    }

    @Transactional
    public ErrorsCollection validateForRemoval(PersonnelMember member, ErrorsCollection errors) {
        errors = ensure(errors);

        if (member == null) {
            errors.add(new ValidationError()
                    .setSuggestion("Cannot delete a non-existing personnel member")
                    .setMessage1("Personnel member is not given")
                    .setStatus(NOT_FOUND.getCode())
            );
            return errors;
        }

        return errors;
    }


    @Transactional
    public ErrorsCollection validateForRemoval(SchoolDepartment department, ErrorsCollection errors) {
        errors = ensure(errors);

        if (department == null) {
            errors.add(new ValidationError()
                    .setSuggestion("Cannot delete a non-existing school department")
                    .setMessage1("School department is not given")
                    .setStatus(NOT_FOUND.getCode())
            );
            return errors;
        }


        return errors;
    }


    @Transactional
    public ErrorsCollection validateForRemoval(School school, ErrorsCollection errors) {
        errors = ensure(errors);

        if (school == null) {
            errors.add(new ValidationError()
                    .setSuggestion("Cannot delete a non-existing school")
                    .setMessage1("School is not given")
                    .setStatus(NOT_FOUND.getCode())
            );
            return errors;
        }

        List<SchoolDepartment> departments = school.getDepartments();
        List<Discipline> disciplines = school.getDisciplines();
        List<Lecturer> lecturers = school.getLecturers();
        List<PersonnelMember> personnelMembers = school.getPersonnel();
        List<Student> students = school.getStudents();
        List<StudentsGroup> groups = school.getGroups();

        if (!isNullOrEmpty(departments)) {
            StringBuilder ids = new StringBuilder();
            departments.forEach(department -> ids.append(department.getId()).append(" "));
            errors.add(new ValidationError()
                    .setSuggestion("Cannot delete school linked to other downstream entities, such as departments, disciplines, lecturers, student groups, personnel, etc.")
                    .setMessage1("School is linked to departments")
                    .setCausedBy(ids.toString())
                    .setStatus(CONFLICT.getCode())
            );
        }

        if (!isNullOrEmpty(disciplines)) {
            StringBuilder ids = new StringBuilder();
            disciplines.forEach(discipline -> ids.append(discipline.getId()).append(" "));
            errors.add(new ValidationError()
                    .setSuggestion("Cannot delete school linked to other downstream entities, such as departments, disciplines, lecturers, student groups, personnel, etc.")
                    .setMessage1("School is linked to disciplines")
                    .setCausedBy(ids.toString())
                    .setStatus(CONFLICT.getCode())
            );
        }

        if (!isNullOrEmpty(lecturers)) {
            StringBuilder ids = new StringBuilder();
            lecturers.forEach(lecturer -> ids.append(lecturer.getId()).append(" "));
            errors.add(new ValidationError()
                    .setSuggestion("Cannot delete school linked to other downstream entities, such as departments, disciplines, lecturers, student groups, personnel, etc.")
                    .setMessage1("School is linked to lecturers")
                    .setCausedBy(ids.toString())
                    .setStatus(CONFLICT.getCode())
            );
        }

        if (!isNullOrEmpty(personnelMembers)) {
            StringBuilder ids = new StringBuilder();
            personnelMembers.forEach(member -> ids.append(member.getId()).append(" "));
            errors.add(new ValidationError()
                    .setSuggestion("Cannot delete school linked to other downstream entities, such as departments, disciplines, lecturers, student groups, personnel, etc.")
                    .setMessage1("School is linked to personnel")
                    .setCausedBy(ids.toString())
                    .setStatus(CONFLICT.getCode())
            );
        }

        if (!isNullOrEmpty(students)) {
            StringBuilder ids = new StringBuilder();
            students.forEach(student -> ids.append(student.getId()).append(" "));
            errors.add(new ValidationError()
                    .setSuggestion("Cannot delete school linked to other downstream entities, such as departments, disciplines, lecturers, student groups, personnel, etc.")
                    .setMessage1("School is linked to students")
                    .setCausedBy(ids.toString())
                    .setStatus(CONFLICT.getCode())
            );
        }

        if (!isNullOrEmpty(groups)) {
            StringBuilder ids = new StringBuilder();
            groups.forEach(group -> ids.append(group.getId()).append(" "));
            errors.add(new ValidationError()
                    .setSuggestion("Cannot delete school linked to other downstream entities, such as departments, disciplines, lecturers, student groups, personnel, etc.")
                    .setMessage1("School is linked to student groups")
                    .setCausedBy(ids.toString())
                    .setStatus(CONFLICT.getCode())
            );
        }

        return errors;
    }

    private boolean or(boolean... all) {
        for (boolean b : all) {
            if (b) {
                return true;
            }
        }
        return false;
    }

    private boolean and(boolean... all) {
        for (boolean b : all) {
            if (!b) {
                return false;
            }
        }
        return true;
    }


    @Transactional
    protected School fetch(School school) {
        School existingSchool = null;

        if (school == null) return null;

        if (!isNullOrEmpty(school.getId())) {
            existingSchool = schoolDao.findOne(school.getId());
        }

        return existingSchool;
    }

    @Transactional
    protected Discipline fetch(Discipline discipline) {
        Discipline existingDiscipline = null;

        if (discipline == null) return null;

        if (!isNullOrEmpty(discipline.getId())) {
            existingDiscipline = disciplineDao.findOne(discipline.getId());
        }

        return existingDiscipline;
    }


    private ErrorsCollection ensure(ErrorsCollection errors) {
        return ensureValue(errors, ErrorsCollection.class);
    }

}
