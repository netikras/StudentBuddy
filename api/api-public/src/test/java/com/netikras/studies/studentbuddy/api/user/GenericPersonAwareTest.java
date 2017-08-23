package com.netikras.studies.studentbuddy.api.user;

import com.netikras.studies.studentbuddy.api.GenericConsumerTest;
import com.netikras.studies.studentbuddy.api.user.mgmt.AdminPersonConsumer;
import com.netikras.studies.studentbuddy.core.data.api.dto.PersonDto;
import org.junit.Before;

public class GenericPersonAwareTest extends GenericConsumerTest {

    protected PersonConsumer personConsumer;
    protected AdminPersonConsumer adminPersonConsumer;

    @Before
    public void initPersonAware() {
        super.initGenericConsumer();
        adminPersonConsumer = attachConsumer(new AdminPersonConsumer());
        personConsumer = attachConsumer(new PersonConsumer());
    }


    protected PersonDto buildPerson() {
        PersonDto personDto = new PersonDto();

        personDto.setFirstName("Andrew");
        personDto.setLastName("Hopkins");
        personDto.setEmail("andhop@yahoo.co.uk");
        personDto.setIdentification("s0934825");
        personDto.setPersonalCode("38811014275");
        personDto.setPhoneNo("+37062346284");

        return personDto;
    }

    protected PersonDto getPersonForTesting() {
        loginSystem();
        PersonDto personDto = personConsumer.getByCode(buildPerson().getPersonalCode());
        if (personDto == null) {
            personDto = adminPersonConsumer.createPerson(buildPerson());
        }

        return personDto;
    }

    protected void removeTestPerson() {
        loginSystem();
        PersonDto personDto = personConsumer.getByCode(buildPerson().getPersonalCode());

        if (personDto != null) {
            adminPersonConsumer.deletePersonById(personDto.getId());
        }
    }


}
