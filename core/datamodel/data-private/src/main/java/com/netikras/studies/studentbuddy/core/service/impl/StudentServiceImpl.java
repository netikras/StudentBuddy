package com.netikras.studies.studentbuddy.core.service.impl;

import com.netikras.studies.studentbuddy.commons.exception.StudBudUncheckedException;
import com.netikras.studies.studentbuddy.core.data.api.dao.LectureGuestDao;
import com.netikras.studies.studentbuddy.core.data.api.dao.StudentDao;
import com.netikras.studies.studentbuddy.core.data.api.dao.StudentsGroupDao;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.StudentsGroupDto;
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
    public Student getStudentByPerson(String personId) {
        return studentDao.findByPerson_Id(personId);
    }

    @Override
    public Student getStudent(String id) {
        return studentDao.findOne(id);
    }

    @Override
    public Student createStudent(Student student) {
        ErrorsCollection errors = personValidator.validateStudentForCreation(student, null);
        if (!errors.isEmpty()) {
            throw new StudBudUncheckedException()
                    .setErrors(errors)
                    .setMessage1("Cannot create a new student")
                    .setMessage2("new student validation failed")
                    .setStatusCode(HttpStatus.BAD_REQUEST);
        }
        return studentDao.save(student);
    }

    @Override
    public Student updateStudent(Student student) {
        return studentDao.save(student);
    }

    @Override
    public void deleteStudent(String id) {
        studentDao.delete(id);
    }

    @Override
    public void deleteStudentByPerson(String personId) {
        studentDao.deleteAllByPerson_Id(personId);
    }

    @Override
    public List<Student> getStudentsByGroupId(String groupId) {
        return studentDao.findAllByGroup_Id(groupId);
    }

    @Override
    public void addStudentsToGroup(String groupId, List<String> studentIds) {
        List<Student> students = studentDao.findAllByIdIsIn(studentIds);
        StudentsGroup group = groupDao.findOne(groupId);

        if (group == null
                || students == null
                || students.isEmpty()) {
            return;
        }

        boolean dirty = false;
        for (Student student : students) {
            dirty = dirty || group.addMember(student);
        }

        if (dirty) {
            groupDao.save(group);
        }
    }

    @Override
    public void removeStudentsFromGroup(String groupId, List<String> studentIds) {
        List<Student> students = studentDao.findAllByIdIsIn(studentIds);
        StudentsGroup group = groupDao.findOne(groupId);

        if (group == null
                || students == null
                || students.isEmpty()) {
            return;
        }

        boolean dirty = false;
        for (Student student : students) {
            dirty = dirty || group.removeMember(student);
        }

        if (dirty) {
            groupDao.save(group);
        }
    }

    @Override
    public void addStudentToGroup(StudentsGroup group, Student student) {
        group.addMember(student);
        groupDao.save(group);
    }

    @Override
    public void removeStudentFromGroup(StudentsGroup group, Student student) {
        if (group.removeMember(student)) {
            groupDao.save(group);
        }

    }

    @Override
    public StudentsGroup getStudentsGroup(String id) {
        return groupDao.findOne(id);
    }

    @Override
    public StudentsGroup getStudentsGroupByTitle(String title) {
        return groupDao.findByTitle(title);
    }

    @Override
    public List<StudentsGroup> getAllStudentGroups() {
        return groupDao.findAll();
    }

    @Override
    public StudentsGroup createStudentsGroup(StudentsGroup group) {
        return null;
    }

    @Override
    public void deleteStudentsGroup(String groupId) {
        groupDao.delete(groupId);
    }

    @Override
    public LectureGuest createLectureGuest(LectureGuest lectureGuest) {
        return lectureGuestDao.save(lectureGuest);
    }

    @Override
    public LectureGuest createLectureGuest(Person person, Lecture lecture) {
        LectureGuest guest = new LectureGuest();
        guest.setLecture(lecture);
        guest.setPerson(person);
        return lectureGuestDao.save(guest);
    }

    @Override
    public LectureGuest updateLectureGuest(LectureGuest lectureGuest) {
        return lectureGuestDao.save(lectureGuest);
    }

    @Override
    public LectureGuest getLectureGuest(String id) {
        return lectureGuestDao.findOne(id);
    }

    @Override
    public void deleteLectureGuest(String id) {
        lectureGuestDao.delete(id);
    }



}
