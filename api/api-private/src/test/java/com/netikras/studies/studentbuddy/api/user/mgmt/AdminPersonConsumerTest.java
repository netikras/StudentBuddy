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
        PersonDto personDto = personConsumer.getPersonDtoByIdentifier(buildPerson().getIdentification());
        if (personDto != null) {
            adminPersonConsumer.deletePersonDto(personDto.getId());
            personDto = personConsumer.getPersonDtoByIdentifier(buildPerson().getIdentification());
        }

        Assert.assertNull("There should be no such person created yet", personDto);

        personDto = adminPersonConsumer.createPersonDto(buildPerson());
        logger.info("New person created: {}", personDto);
        adminPersonConsumer.deletePersonDto(personDto.getId());
    }

    @Test
    public void deletePersonByIdTest() throws Exception {
        loginSystem();
        PersonDto personDto = getPersonForTesting();

        assertNotNull("There should be a person created already", personDto);

        adminPersonConsumer.deletePersonDto(personDto.getId());
    }

    @Test
    public void updatePersonTest() throws Exception {
    }

    @Test
    public void getAllTest() {
        loginSystem();
        List<PersonDto> people = personConsumer.getPersonDtoAll();
        PersonDto personDto = null;
        if (people == null || people.isEmpty()) {
            personDto = getPersonForTesting();
            people = personConsumer.getPersonDtoAll();
        }

        assertNotNull("There should be at least one person created already", people);
        assertFalse("There should be at least one person in the list", people.isEmpty());

        if (personDto != null) {
            adminPersonConsumer.deletePersonDto(personDto.getId());
        }
    }

    @Test
    public void getAllByFirstNameTest() {
        loginSystem();
        List<PersonDto> people = personConsumer.getPersonDtoAllByFirstName(buildPerson().getFirstName());
        PersonDto personDto = null;
        if (people == null || people.isEmpty()) {
            personDto = getPersonForTesting();
            people = personConsumer.getPersonDtoAllByFirstName(buildPerson().getFirstName());
        }

        assertNotNull("There should be at least one person created already", people);
        assertFalse("There should be at least one person in the list", people.isEmpty());

        if (personDto != null) {
            adminPersonConsumer.deletePersonDto(personDto.getId());
        }
    }

    @Test
    public void getAllByLastNameTest() {
        loginSystem();
        List<PersonDto> people = personConsumer.getPersonDtoAllByLastName(buildPerson().getLastName());
        PersonDto personDto = null;
        if (people == null || people.isEmpty()) {
            personDto = getPersonForTesting();
            people = personConsumer.getPersonDtoAllByLastName(buildPerson().getLastName());
        }

        assertNotNull("There should be at least one person created already", people);
        assertFalse("There should be at least one person in the list", people.isEmpty());

        if (personDto != null) {
            adminPersonConsumer.deletePersonDto(personDto.getId());
        }
    }


    @Test
    public void getAllByFirstAndLastNameTest() {
        loginSystem();
        List<PersonDto> people = personConsumer.getPersonDtoAllByFirstAndLastName(buildPerson().getFirstName(), buildPerson().getLastName());
        PersonDto personDto = null;
        if (people == null || people.isEmpty()) {
            personDto = adminPersonConsumer.createPersonDto(buildPerson());
            people = personConsumer.getPersonDtoAllByFirstAndLastName(buildPerson().getFirstName(), buildPerson().getLastName());
        }

        assertNotNull("There should be at least one person created already", people);
        assertFalse("There should be at least one person in the list", people.isEmpty());

        if (personDto != null) {
            adminPersonConsumer.deletePersonDto(personDto.getId());
        }
    }

    @Test
    public void deleteAllPeople() {
        loginSystem();
        List<PersonDto> people = personConsumer.getPersonDtoAll();
        PersonDto personDto = null;
        if (people == null || people.isEmpty()) {
            personDto = adminPersonConsumer.createPersonDto(buildPerson());
            people = personConsumer.getPersonDtoAll();
        }

        assertNotNull("There should be at least one person created already", people);
        assertFalse("There should be at least one person in the list", people.isEmpty());

        for (PersonDto dto : people) {
            adminPersonConsumer.deletePersonDto(dto.getId());
        }

    }
}
