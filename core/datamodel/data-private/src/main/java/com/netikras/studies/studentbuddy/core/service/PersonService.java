package com.netikras.studies.studentbuddy.core.service;

import com.netikras.studies.studentbuddy.core.data.api.model.Person;

public interface PersonService {

    Person findPerson(String id);

    Person findPersonByIdentifier(String identifier);

    Person findPersonByPersonalCode(String code);

    Person createPerson(Person person);

    void deletePerson(String id);

    Person updatePerson(Person person);




}
