package com.netikras.studies.studentbuddy.core.service;

import com.netikras.studies.studentbuddy.core.data.api.model.Lecturer;
import com.netikras.studies.studentbuddy.core.data.api.model.Person;

import java.util.List;

public interface PersonService {

    Person findPerson(String id);

    Person findPersonByIdentifier(String identifier);

    Person findPersonByPersonalCode(String code);

    Person createPerson(Person person);

    void deletePerson(String id);

    Person updatePerson(Person person);


    List<Lecturer> getLecturersByPerson(String personId);

    Lecturer getLecturer(String id);

    Lecturer createLecturer(Lecturer lecturer);

    Lecturer updateLecturer(Lecturer lecturer);

    void deleteLecturer(String id);

    void deleteLecturerByPerson(String personId);


}
