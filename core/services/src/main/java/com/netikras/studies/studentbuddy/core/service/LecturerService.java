package com.netikras.studies.studentbuddy.core.service;


import com.netikras.studies.studentbuddy.core.data.api.model.Discipline;
import com.netikras.studies.studentbuddy.core.data.api.model.Lecturer;
import com.netikras.studies.studentbuddy.core.data.api.model.Person;

import java.util.List;

public interface LecturerService {

    List<Lecturer> getLecturersByPerson(String personId);

    Lecturer getLecturer(String id);

    Lecturer createLecturer(Lecturer Lecturer);

    Lecturer createLecturer(Person person);

    Lecturer updateLecturer(Lecturer Lecturer);

    void deleteLecturer(String id);

    void purgeLecturer(String id);

    void deleteLecturerByPerson(String personId);

    List<Lecturer> findLecturersByDiscipline(String disciplineId);

    List<Lecturer> searchAllByDegree(String query);

    List<Lecturer> searchAllByFirstName(String query);

    List<Lecturer> searchAllByLastName(String query);

    void attachToDiscipline(Lecturer lecturer, Discipline discipline);

    void detatchFromDiscipline(Lecturer lecturer, Discipline discipline);

}
