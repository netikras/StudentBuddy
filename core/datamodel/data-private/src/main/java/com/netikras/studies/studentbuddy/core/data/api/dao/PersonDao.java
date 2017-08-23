package com.netikras.studies.studentbuddy.core.data.api.dao;

import com.netikras.studies.studentbuddy.core.data.api.model.Person;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonDao extends JpaRepo<Person> {


    Person findByIdentification(String identification);

    Person findByPersonalCode(String personalCode);

    List<Person> findAllByIdOrIdentificationOrPersonalCode(String id, String identification, String personalCode);

    List<Person> findAllByFirstNameAndLastName(String firstName, String lastName);
    List<Person> findAllByFirstName(String firstName);
    List<Person> findAllByLastName(String lastName);

    List<Person> findAllByLastNameLikeIgnoreCase(String lastName);

    List<Person> findAllByFirstNameLikeIgnoreCase(String firstName);

    List<Person> findAllByIdentificationLikeIgnoreCase(String identification);

    List<Person> findAllByPersonalCodeLikeIgnoreCase(String personalCode);
}
