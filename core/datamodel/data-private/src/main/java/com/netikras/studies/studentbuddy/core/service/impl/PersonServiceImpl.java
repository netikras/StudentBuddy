package com.netikras.studies.studentbuddy.core.service.impl;

import com.netikras.studies.studentbuddy.commons.exception.StudBudUncheckedException;
import com.netikras.studies.studentbuddy.core.data.api.dao.PersonDao;
import com.netikras.studies.studentbuddy.core.data.api.model.Person;
import com.netikras.studies.studentbuddy.core.data.api.model.Tag;
import com.netikras.studies.studentbuddy.core.service.PersonService;
import com.netikras.tools.common.remote.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static com.netikras.tools.common.remote.http.HttpStatus.CONFLICT;
import static com.netikras.tools.common.remote.http.HttpStatus.NOT_FOUND;

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
    public List<Person> findByFirstName(String firstName) {
        return personDao.findAllByFirstName(firstName);
    }

    @Override
    public List<Person> findAll() {
        return personDao.findAll();
    }

    @Override
    public List<Person> findByLastName(String lastName) {
        return personDao.findAllByLastName(lastName);
    }

    @Override
    public List<Person> findByFirstAndLastName(String firstName, String lastName) {
        return personDao.findAllByFirstNameAndLastName(firstName, lastName);
    }


    @Override
    public Person createPerson(Person person) {
        List<Person> existing = new ArrayList<>();
        Person existingPerson = null;

        if (person.getId() != null) {
            existingPerson = personDao.findOne(person.getId());
            if (existingPerson != null) existing.add(existingPerson);
        }

        if (person.getIdentification() != null) {
            existingPerson = personDao.findByIdentification(person.getIdentification());
            if (existingPerson != null) existing.add(existingPerson);
        }

        if (person.getPersonalCode() != null) {
            existingPerson = personDao.findByPersonalCode(person.getPersonalCode());
            if (existingPerson != null) existing.add(existingPerson);
        }

        if (!existing.isEmpty()) {
            throw new StudBudUncheckedException()
                    .setMessage1("Unable to create a new person")
                    .setMessage2("People with "+existing.size()+" criteria out of given already exist")
                    .setProbableCause(
                            "id=" + person.getId()
                                    + ", identification=" + person.getIdentification()
                                    + ", code=" + person.getPersonalCode())
                    .setStatusCode(CONFLICT)
                    ;
        }

        return personDao.save(person);
    }

    @Override
    @Transactional
    public void deletePerson(String id) {
        Person person = personDao.findOne(id);
        if (person == null) {
            throw new StudBudUncheckedException()
                    .setMessage1("Cannot delete a person")
                    .setMessage2("Such person does not exist")
                    .setProbableCause(id)
                    .setStatusCode(NOT_FOUND);
        }
        if (person.getUserTags() != null) {
            for (Tag tag : person.getUserTags()) {
                tag.setCreatedBy(null);
            }
            personDao.saveAndFlush(person);
        }
        personDao.delete(id);
    }

    @Override
    public Person updatePerson(Person person) {
        return personDao.save(person);
    }

}
