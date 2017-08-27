package com.netikras.studies.studentbuddy.core.service.impl;

import com.netikras.studies.studentbuddy.commons.exception.StudBudUncheckedException;
import com.netikras.studies.studentbuddy.core.data.api.dao.PersonDao;
import com.netikras.studies.studentbuddy.core.data.api.model.Person;
import com.netikras.studies.studentbuddy.core.data.api.model.Tag;
import com.netikras.studies.studentbuddy.core.service.PersonService;
import com.netikras.studies.studentbuddy.core.validator.PersonValidator;
import com.netikras.tools.common.exception.ErrorsCollection;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

import static com.netikras.tools.common.remote.http.HttpStatus.BAD_REQUEST;
import static com.netikras.tools.common.remote.http.HttpStatus.NOT_FOUND;

@Service
public class PersonServiceImpl implements PersonService {

    @Resource
    private PersonDao personDao;
    @Resource
    private PersonValidator personValidator;

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
    public List<Person> searchAllByFirstName(String query) {
        return personDao.findAllByFirstNameLikeIgnoreCase(personDao.wrapSearchString(query));
    }

    @Override
    public List<Person> searchAllByLastName(String query) {
        return personDao.findAllByLastNameLikeIgnoreCase(personDao.wrapSearchString(query));
    }

    @Override
    public List<Person> searchAllByIdentification(String query) {
        return personDao.findAllByIdentificationLikeIgnoreCase(personDao.wrapSearchString(query));
    }

    @Override
    public List<Person> searchAllByPersonalCode(String query) {
        return personDao.findAllByPersonalCodeLikeIgnoreCase(personDao.wrapSearchString(query));
    }


    @Override
    public Person createPerson(Person person) {
        ErrorsCollection errors = personValidator.validatePersonForCreation(person, null);
        if (!errors.isEmpty()) {
            throw new StudBudUncheckedException()
                    .setMessage1("Cannot create new person")
                    .setMessage2("Validation errors: " + errors.size())
                    .setErrors(errors)
                    .setStatusCode(BAD_REQUEST)
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
