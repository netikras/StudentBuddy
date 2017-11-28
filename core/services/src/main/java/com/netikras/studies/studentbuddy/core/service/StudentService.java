package com.netikras.studies.studentbuddy.core.service;

import com.netikras.studies.studentbuddy.core.data.api.model.LectureGuest;
import com.netikras.studies.studentbuddy.core.data.api.model.Lecture;
import com.netikras.studies.studentbuddy.core.data.api.model.Person;
import com.netikras.studies.studentbuddy.core.data.api.model.Student;
import com.netikras.studies.studentbuddy.core.data.api.model.StudentsGroup;

import java.util.List;

public interface StudentService {

    List<Student> getStudentsByPerson(String personId);

    Student getStudent(String id);

    Student createStudent(Student student);

    Student updateStudent(Student student);

    void deleteStudent(String id);

    void purgeStudent(String id);

    void deleteStudentByPerson(String personId);

    List<Student> getStudentsByGroupId(String groupId);

    void addStudentsToGroup(String groupId, List<String> studentIds);

    void removeStudentsFromGroup(String groupId, List<String> studentIds);

    List<Student> searchAllStudentsByFirstName(String query);

    List<Student> searchAllStudentsByLastName(String query);

    void addStudentToGroup(StudentsGroup group, Student student);

    void removeStudentFromGroup(StudentsGroup group, Student student);

    StudentsGroup getStudentsGroup(String id);

    StudentsGroup getStudentsGroupByTitle(String title);

    List<StudentsGroup> searchAllGroupsByTitle(String query);

    List<StudentsGroup> getAllStudentGroups();

    StudentsGroup createStudentsGroup(StudentsGroup group);

    void deleteStudentsGroup(String groupId);

    void purgeStudentsGroup(String groupId);

    LectureGuest createLectureGuest(LectureGuest student);

    LectureGuest createLectureGuest(Person person, Lecture lecture);

    LectureGuest updateLectureGuest(LectureGuest student);

    void deleteLectureGuest(String id);


    LectureGuest getLectureGuest(String id);

    void purgeLectureGuest(String id);

    List<LectureGuest> searchAllGuestsByFirstName(String query);

    List<LectureGuest> searchAllGuestsByLastName(String query);

    StudentsGroup updateGroup(StudentsGroup group);
}
