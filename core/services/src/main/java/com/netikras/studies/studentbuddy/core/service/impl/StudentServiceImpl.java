package com.netikras.studies.studentbuddy.core.service.impl;

import com.netikras.studies.studentbuddy.commons.exception.StudBudUncheckedException;
import com.netikras.studies.studentbuddy.core.data.api.dao.LectureDao;
import com.netikras.studies.studentbuddy.core.data.api.dao.LectureGuestDao;
import com.netikras.studies.studentbuddy.core.data.api.dao.StudentDao;
import com.netikras.studies.studentbuddy.core.data.api.dao.StudentsGroupDao;
import com.netikras.studies.studentbuddy.core.data.api.model.Lecture;
import com.netikras.studies.studentbuddy.core.data.api.model.LectureGuest;
import com.netikras.studies.studentbuddy.core.data.api.model.Person;
import com.netikras.studies.studentbuddy.core.data.api.model.Student;
import com.netikras.studies.studentbuddy.core.data.api.model.StudentsGroup;
import com.netikras.studies.studentbuddy.core.service.LectureService;
import com.netikras.studies.studentbuddy.core.service.StudentService;
import com.netikras.studies.studentbuddy.core.validator.PersonValidator;
import com.netikras.tools.common.exception.ErrorsCollection;
import com.netikras.tools.common.remote.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static com.netikras.tools.common.remote.http.HttpStatus.BAD_REQUEST;
import static com.netikras.tools.common.remote.http.HttpStatus.NOT_FOUND;
import static com.netikras.tools.common.security.IntegrityUtils.isNullOrEmpty;

@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentDao studentDao;
    @Resource
    private StudentsGroupDao groupDao;
    @Resource
    private LectureGuestDao lectureGuestDao;
    @Resource
    private PersonValidator personValidator;
    @Resource
    private LectureService lectureService;
    @Resource
    private LectureDao lectureDao;


    @Override
    @Transactional
    public List<Student> getStudentsByPerson(String personId) {
        return studentDao.findByPerson_Id(personId);
    }

    @Override
    @Transactional
    public Student getStudent(String id) {
        return studentDao.findOne(id);
    }

    @Override
    @Transactional
    public Student createStudent(Student student) {
        ErrorsCollection errors = personValidator.validateForCreation(student, null);
        if (!errors.isEmpty()) {
            throw new StudBudUncheckedException()
                    .setErrors(errors)
                    .setMessage1("Cannot create a new student")
                    .setMessage2("new student validation failed")
                    .setStatusCode(HttpStatus.BAD_REQUEST);
        }
        student.setId(null);
        return studentDao.save(student);
    }

    @Override
    @Transactional
    public Student updateStudent(Student student) {
        return studentDao.save(student);
    }

    @Override
    @Transactional
    public void deleteStudent(String id) {
        Student student = getStudent(id);
        ErrorsCollection errors = personValidator.validateForRemoval(student, null);
        if (!errors.isEmpty()) {
            throw new StudBudUncheckedException()
                    .setErrors(errors)
                    .setMessage1("Cannot remove student")
                    .setMessage2("Validation errors: " + errors.size())
                    .setErrors(errors)
                    .setStatusCode(BAD_REQUEST);
        }

        studentDao.delete(id);
    }

    @Override
    @Transactional
    public void purgeStudent(String id) {
        Student student = getStudent(id);
        if (student == null) {
            return;
        }

        studentDao.delete(id);
    }

    @Override
    @Transactional
    public void deleteStudentByPerson(String personId) {
        studentDao.deleteAllByPerson_Id(personId);
    }

    @Override
    @Transactional
    public List<Student> getStudentsByGroupId(String groupId) {
        return studentDao.findAllByGroup_Id(groupId);
    }

    @Override
    @Transactional
    public void addStudentsToGroup(String groupId, List<String> studentIds) {
        List<Student> students = studentDao.findAllByIdIsIn(studentIds);
        StudentsGroup group = groupDao.findOne(groupId);

        if (group == null
                || students == null
                || students.isEmpty()) {
            return;
        }

        for (Student student : students) {
            student.setGroup(group);
        }
    }

    @Override
    @Transactional
    public void removeStudentsFromGroup(String groupId, List<String> studentIds) {
        List<Student> students = studentDao.findAllByIdIsIn(studentIds);
        StudentsGroup group = groupDao.findOne(groupId);

        if (group == null
                || students == null
                || students.isEmpty()) {
            return;
        }

        for (Student student : students) {
            student.setGroup(null);
            group.removeMember(student);
            studentDao.save(student);
        }

    }

    @Override
    public List<Student> searchAllStudentsByFirstName(String query) {
        return studentDao.findAllByPerson_FirstNameLikeIgnoreCase(studentDao.wrapSearchString(query));
    }

    @Override
    public List<Student> searchAllStudentsByLastName(String query) {
        return studentDao.findAllByPerson_LastNameLikeIgnoreCase(studentDao.wrapSearchString(query));
    }

    @Override
    @Transactional
    public void addStudentToGroup(StudentsGroup group, Student student) {
        student.setGroup(group);
        studentDao.save(student);
    }

    @Override
    @Transactional
    public void removeStudentFromGroup(StudentsGroup group, Student student) {
        if (group.removeMember(student)) {
            groupDao.save(group);
        }

    }

    @Override
    @Transactional
    public StudentsGroup getStudentsGroup(String id) {
        StudentsGroup group = groupDao.findOne(id);
        return group;
    }

    @Override
    @Transactional
    public StudentsGroup getStudentsGroupByTitle(String title) {
        StudentsGroup group = groupDao.findByTitle(title);
        return group;
    }

    @Override
    public List<StudentsGroup> searchAllGroupsByTitle(String query) {
        return groupDao.findAllByTitleLikeIgnoreCase(groupDao.wrapSearchString(query));
    }

    @Override
    @Transactional
    public List<StudentsGroup> getAllStudentGroups() {
        return groupDao.findAll();
    }

    @Override
    @Transactional
    public StudentsGroup createStudentsGroup(StudentsGroup group) {
        ErrorsCollection errors = new ErrorsCollection();
        errors = personValidator.validateForCreation(group, errors);
        if (!errors.isEmpty()) {
            throw new StudBudUncheckedException()
                    .setErrors(errors)
                    .setMessage1("Cannot create a new students group")
                    .setMessage2("New group validation failed")
                    .setStatusCode(BAD_REQUEST);
        }
        group.setId(null);
        return groupDao.save(group);
    }

    @Override
    @Transactional
    public StudentsGroup updateGroup(StudentsGroup group) {
        ErrorsCollection errors = personValidator.validateForUpdate(group, null);
        if (!errors.isEmpty()) {
            throw new StudBudUncheckedException()
                    .setMessage1("Cannot delete group")
                    .setMessage2("Validation errors: " + errors.size())
                    .setErrors(errors)
                    .setStatusCode(BAD_REQUEST)
                    ;
        }

        group = groupDao.save(group);
        return group;
    }

    @Override
    @Transactional
    public void deleteStudentsGroup(String groupId) {
        StudentsGroup group = groupDao.findOne(groupId);
        ErrorsCollection errors = personValidator.validateForRemoval(group, null);
        if (!errors.isEmpty()) {
            throw new StudBudUncheckedException()
                    .setMessage1("Cannot delete group")
                    .setMessage2("Validation errors: " + errors.size())
                    .setErrors(errors)
                    .setStatusCode(BAD_REQUEST)
                    ;
        }

        groupDao.delete(groupId);
    }

    @Override
    @Transactional
    public void purgeStudentsGroup(String groupId) {
        StudentsGroup group = getStudentsGroup(groupId);
        if (group == null) {
            return;
        }

        List<Student> students = group.getMembers();

        List<String> ids = new ArrayList<>();
        students.forEach(student -> ids.add(student.getId()));
        removeStudentsFromGroup(groupId, ids);

        group.setMembers(null);

        List<Lecture> lectures = lectureDao.findAllByStudentsGroup_Id(groupId);
        if (!isNullOrEmpty(lectures)) {
            List<String> lectureIds = new ArrayList<>();
            lectures.forEach(lecture -> lectureIds.add(lecture.getId()));
            lectureService.purgeLectures(lectureIds);
        }

        groupDao.delete(groupId);
    }

    @Override
    @Transactional
    public LectureGuest createLectureGuest(LectureGuest lectureGuest) {
        return lectureGuestDao.save(lectureGuest);
    }

    @Override
    @Transactional
    public LectureGuest createLectureGuest(Person person, Lecture lecture) {
        LectureGuest guest = new LectureGuest();
        guest.setLecture(lecture);
        guest.setPerson(person);
        return createLectureGuest(guest);
    }

    @Override
    @Transactional
    public LectureGuest updateLectureGuest(LectureGuest lectureGuest) {
        return lectureGuestDao.save(lectureGuest);
    }

    @Override
    @Transactional
    public LectureGuest getLectureGuest(String id) {
        return lectureGuestDao.findOne(id);
    }

    @Override
    @Transactional
    public void deleteLectureGuest(String id) {
        LectureGuest guest = lectureGuestDao.findOne(id);
        ErrorsCollection errors = personValidator.validateForRemoval(guest, null);
        if (!errors.isEmpty()) {
            throw new StudBudUncheckedException()
                    .setMessage1("Cannot delete guest")
                    .setMessage2("Validation errors: " + errors.size())
                    .setErrors(errors)
                    .setStatusCode(BAD_REQUEST)
                    ;
        }

        lectureGuestDao.delete(id);
    }

    @Override
    @Transactional
    public void purgeLectureGuest(String id) {
        LectureGuest guest = getLectureGuest(id);
        if (guest == null) {
            return;
        }

        lectureGuestDao.delete(guest);
    }

    @Override
    public List<LectureGuest> searchAllGuestsByFirstName(String query) {
        return lectureGuestDao.findAllByPerson_FirstNameLikeIgnoreCase(lectureGuestDao.wrapSearchString(query));
    }

    @Override
    public List<LectureGuest> searchAllGuestsByLastName(String query) {
        return lectureGuestDao.findAllByPerson_LastNameLikeIgnoreCase(lectureGuestDao.wrapSearchString(query));
    }


}
