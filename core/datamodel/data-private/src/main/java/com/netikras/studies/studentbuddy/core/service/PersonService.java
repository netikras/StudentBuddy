package com.netikras.studies.studentbuddy.core.service;

import com.netikras.studies.studentbuddy.core.data.api.model.Person;

import java.util.List;

public interface PersonService {

    Person findPerson(String id);

    Person findPersonByIdentifier(String identifier);

    Person findPersonByPersonalCode(String code);

    List<Person> findByFirstName(String firstName);

    List<Person> findAll();

    List<Person> findByLastName(String lastName);

    List<Person> findByFirstAndLastName(String firstName, String lastName);

    Person createPerson(Person person);

    void deletePerson(String id);

    Person updatePerson(Person person);




}
