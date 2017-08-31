package com.netikras.studies.studentbuddy.core.service.impl;

import com.netikras.studies.studentbuddy.commons.exception.StudBudUncheckedException;
import com.netikras.studies.studentbuddy.core.data.api.dao.LectureGuestDao;
import com.netikras.studies.studentbuddy.core.data.api.dao.StudentDao;
import com.netikras.studies.studentbuddy.core.data.api.dao.StudentsGroupDao;
import com.netikras.studies.studentbuddy.core.data.api.model.Lecture;
import com.netikras.studies.studentbuddy.core.data.api.model.LectureGuest;
import com.netikras.studies.studentbuddy.core.data.api.model.Person;
import com.netikras.studies.studentbuddy.core.data.api.model.Student;
import com.netikras.studies.studentbuddy.core.data.api.model.StudentsGroup;
import com.netikras.studies.studentbuddy.core.service.StudentService;
import com.netikras.studies.studentbuddy.core.validator.PersonValidator;
import com.netikras.tools.common.exception.ErrorsCollection;
import com.netikras.tools.common.remote.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

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


    @Override
    @Transactional
    public Student getStudentByPerson(String personId) {
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
        ErrorsCollection errors = personValidator.validateStudentForCreation(student, null);
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
        errors = personValidator.validateGroupForCreation(group, errors);
        if (!errors.isEmpty()) {
            throw new StudBudUncheckedException()
                    .setErrors(errors)
                    .setMessage1("Cannot create a new students group")
                    .setMessage2("New group validation failed")
                    .setStatusCode(HttpStatus.BAD_REQUEST);
        }
        group.setId(null);
        return groupDao.save(group);
    }

    @Override
    @Transactional
    public void deleteStudentsGroup(String groupId) {

        StudentsGroup group = groupDao.findOne(groupId);
        if (group != null) {

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
        lectureGuestDao.delete(id);
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
