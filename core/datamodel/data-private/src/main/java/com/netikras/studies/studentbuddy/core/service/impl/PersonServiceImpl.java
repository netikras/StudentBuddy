package com.netikras.studies.studentbuddy.core.service.impl;

import com.netikras.studies.studentbuddy.commons.exception.StudBudUncheckedException;
import com.netikras.studies.studentbuddy.core.data.api.dao.PersonDao;
import com.netikras.studies.studentbuddy.core.data.api.model.Person;
import com.netikras.studies.studentbuddy.core.service.PersonService;
import com.netikras.tools.common.remote.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static com.netikras.tools.common.remote.http.HttpStatus.CONFLICT;

@Service
public class PersonServiceImpl implements PersonService {

    @Resource
    private PersonDao personDao;

    @Override
    public Person findPerson(String id) {
        return personDao.findOne(id);
    }

    @Override
    public Person findPersonByIdentifier(String identifier) {
        return personDao.findByIdentification(identifier);
    }

    @Override
    public Person findPersonByPersonalCode(String code) {
        return personDao.findByPersonalCode(code);
    }

    @Override
    public Person createPerson(Person person) {
        List<Person> existing = personDao
                .findAllByIdOrIdentificationOrPersonalCode(person.getId(), person.getIdentification(), person.getPersonalCode());

        if (existing != null && !existing.isEmpty()) {
            throw new StudBudUncheckedException()
                    .setMessage1("Unable to create a new person")
                    .setMessage2("Such person already exists")
                    .setProbableCause(
                            "id=" + person.getId()
                                    + ", identification=" + person.getIdentification()
                                    + ", code=" + person.getPersonalCode())
                    .setDeveloperMessage("ids[0]: " + existing.get(0).getId())
                    .setStatusCode(CONFLICT)
                    ;
        }

        return personDao.save(person);
    }

    @Override
    public void deletePerson(String id) {
        personDao.delete(id);
    }

    @Override
    public Person updatePerson(Person person) {
        return personDao.save(person);
    }

}
