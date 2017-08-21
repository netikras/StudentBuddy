package com.netikras.studies.studentbuddy.api.user.mgmt;

import com.netikras.studies.studentbuddy.api.user.GenericPersonAwareTest;
import com.netikras.studies.studentbuddy.core.data.api.dto.PersonDto;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class AdminPersonConsumerTest extends GenericPersonAwareTest {

    private static final Logger logger = LoggerFactory.getLogger(AdminPersonConsumerTest.class);



    @Test
    public void createPersonTest() throws Exception {
        loginSystem();
        PersonDto personDto = personConsumer.getByIdentifier(buildPerson().getIdentification());
        if (personDto != null) {
            adminPersonConsumer.deletePersonById(personDto.getId());
            personDto = personConsumer.getByIdentifier(buildPerson().getIdentification());
        }

        Assert.assertNull("There should be no such person created yet", personDto);

        personDto = adminPersonConsumer.createPerson(buildPerson());
        logger.info("New person created: {}", personDto);
        adminPersonConsumer.deletePersonById(personDto.getId());
    }

    @Test
    public void deletePersonByIdTest() throws Exception {
        loginSystem();
        PersonDto personDto = getPersonForTesting();

        assertNotNull("There should be a person created already", personDto);

        adminPersonConsumer.deletePersonById(personDto.getId());
    }

    @Test
    public void updatePersonTest() throws Exception {
    }

    @Test
    public void getAllTest() {
        loginSystem();
        List<PersonDto> people = personConsumer.getAll();
        PersonDto personDto = null;
        if (people == null || people.isEmpty()) {
            personDto = getPersonForTesting();
            people = personConsumer.getAll();
        }

        assertNotNull("There should be at least one person created already", people);
        assertFalse("There should be at least one person in the list", people.isEmpty());

        if (personDto != null) {
            adminPersonConsumer.deletePersonById(personDto.getId());
        }
    }

    @Test
    public void getAllByFirstNameTest() {
        loginSystem();
        List<PersonDto> people = personConsumer.getAllByFirstName(buildPerson().getFirstName());
        PersonDto personDto = null;
        if (people == null || people.isEmpty()) {
            personDto = getPersonForTesting();
            people = personConsumer.getAllByFirstName(buildPerson().getFirstName());
        }

        assertNotNull("There should be at least one person created already", people);
        assertFalse("There should be at least one person in the list", people.isEmpty());

        if (personDto != null) {
            adminPersonConsumer.deletePersonById(personDto.getId());
        }
    }

    @Test
    public void getAllByLastNameTest() {
        loginSystem();
        List<PersonDto> people = personConsumer.getAllByLastName(buildPerson().getLastName());
        PersonDto personDto = null;
        if (people == null || people.isEmpty()) {
            personDto = getPersonForTesting();
            people = personConsumer.getAllByLastName(buildPerson().getLastName());
        }

        assertNotNull("There should be at least one person created already", people);
        assertFalse("There should be at least one person in the list", people.isEmpty());

        if (personDto != null) {
            adminPersonConsumer.deletePersonById(personDto.getId());
        }
    }


    @Test
    public void getAllByFirstAndLastNameTest() {
        loginSystem();
        List<PersonDto> people = personConsumer.getAllByFirstAndLastName(buildPerson().getFirstName(), buildPerson().getLastName());
        PersonDto personDto = null;
        if (people == null || people.isEmpty()) {
            personDto = adminPersonConsumer.createPerson(buildPerson());
            people = personConsumer.getAllByFirstAndLastName(buildPerson().getFirstName(), buildPerson().getLastName());
        }

        assertNotNull("There should be at least one person created already", people);
        assertFalse("There should be at least one person in the list", people.isEmpty());

        if (personDto != null) {
            adminPersonConsumer.deletePersonById(personDto.getId());
        }
    }

    @Test
    public void deleteAllPeople() {
        loginSystem();
        List<PersonDto> people = personConsumer.getAll();
        PersonDto personDto = null;
        if (people == null || people.isEmpty()) {
            personDto = adminPersonConsumer.createPerson(buildPerson());
            people = personConsumer.getAll();
        }

        assertNotNull("There should be at least one person created already", people);
        assertFalse("There should be at least one person in the list", people.isEmpty());

        for (PersonDto dto : people) {
            adminPersonConsumer.deletePersonById(dto.getId());
        }

    }
}
