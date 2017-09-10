package com.netikras.studies.studentbuddy.core.service;

import com.netikras.studies.studentbuddy.core.data.api.model.Person;

import java.util.List;

public interface PersonService {

    Person getPerson(String id);

    Person findPersonByIdentifier(String identifier);

    Person findPersonByPersonalCode(String code);

    List<Person> findByFirstName(String firstName);

    List<Person> findAll();

    List<Person> findByLastName(String lastName);

    List<Person> findByFirstAndLastName(String firstName, String lastName);

    List<Person> searchAllByFirstName(String query);

    List<Person> searchAllByLastName(String query);

    List<Person> searchAllByIdentification(String query);

    List<Person> searchAllByPersonalCode(String query);

    Person createPerson(Person person);

    void deletePerson(String id);

    void purgePerson(String id);

    Person updatePerson(Person person);




}
