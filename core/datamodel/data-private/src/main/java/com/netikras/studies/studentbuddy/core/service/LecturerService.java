package com.netikras.studies.studentbuddy.core.service;


import com.netikras.studies.studentbuddy.core.data.api.model.Discipline;
import com.netikras.studies.studentbuddy.core.data.api.model.Lecturer;
import com.netikras.studies.studentbuddy.core.data.api.model.Person;

import java.util.List;

public interface LecturerService {

    Lecturer getLecturerByPerson(String personId);

    Lecturer getLecturer(String id);

    Lecturer createLecturer(Lecturer Lecturer);

    Lecturer createLecturer(Person person);

    Lecturer updateLecturer(Lecturer Lecturer);

    void deleteLecturer(String id);

    void deleteLecturerByPerson(String personId);

    List<Lecturer> findLecturersByDiscipline(String disciplineId);

    void attachToDiscipline(Lecturer lecturer, Discipline discipline);

    void detatchFromDiscipline(Lecturer lecturer, Discipline discipline);

}
