package com.netikras.studies.studentbuddy.core.service.impl;

import com.netikras.studies.studentbuddy.core.data.api.dao.LecturerDao;
import com.netikras.studies.studentbuddy.core.data.api.dao.PersonDao;
import com.netikras.studies.studentbuddy.core.data.api.dao.StudentDao;
import com.netikras.studies.studentbuddy.core.data.api.model.Lecturer;
import com.netikras.studies.studentbuddy.core.data.api.model.Person;
import com.netikras.studies.studentbuddy.core.service.PersonService;
import com.netikras.tools.common.exception.FriendlyUncheckedException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Resource
    private PersonDao personDao;

    @Resource
    private StudentDao studentDao;

    @Resource
    private LecturerDao lecturerDao;


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
            throw new FriendlyUncheckedException()
                    .setMessage1("Unable to create a new person")
                    .setMessage2("Such person already exists")
                    .setProbableCause(
                            "id=" + person.getId()
                                    + ", identification=" + person.getIdentification()
                                    + ", code=" + person.getPersonalCode())
                    .setDeveloperMessage("ids[0]: " + existing.get(0).getId())
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

    @Override
    public List<Lecturer> getLecturersByPerson(String personId) {
        return lecturerDao.findByPerson_Id(personId);
    }

    @Override
    public Lecturer getLecturer(String id) {
        return lecturerDao.findOne(id);
    }

    @Override
    public Lecturer createLecturer(Lecturer lecturer) {
        return lecturerDao.save(lecturer);
    }

    @Override
    public Lecturer updateLecturer(Lecturer lecturer) {
        return lecturerDao.save(lecturer);
    }

    @Override
    public void deleteLecturer(String id) {
        lecturerDao.delete(id);
    }

    @Override
    public void deleteLecturerByPerson(String personId) {
        lecturerDao.deleteAllByPerson_Id(personId);
    }
}
