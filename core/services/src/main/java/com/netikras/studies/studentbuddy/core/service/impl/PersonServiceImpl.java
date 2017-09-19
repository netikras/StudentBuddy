package com.netikras.studies.studentbuddy.core.service.impl;

import com.netikras.studies.studentbuddy.commons.exception.StudBudUncheckedException;
import com.netikras.studies.studentbuddy.core.data.api.dao.LectureGuestDao;
import com.netikras.studies.studentbuddy.core.data.api.dao.LecturerDao;
import com.netikras.studies.studentbuddy.core.data.api.dao.PersonDao;
import com.netikras.studies.studentbuddy.core.data.api.dao.PersonnelDao;
import com.netikras.studies.studentbuddy.core.data.api.dao.StudentDao;
import com.netikras.studies.studentbuddy.core.data.api.model.LectureGuest;
import com.netikras.studies.studentbuddy.core.data.api.model.Lecturer;
import com.netikras.studies.studentbuddy.core.data.api.model.Person;
import com.netikras.studies.studentbuddy.core.data.api.model.PersonnelMember;
import com.netikras.studies.studentbuddy.core.data.api.model.Student;
import com.netikras.studies.studentbuddy.core.data.api.model.Tag;
import com.netikras.studies.studentbuddy.core.service.LecturerService;
import com.netikras.studies.studentbuddy.core.service.PersonService;
import com.netikras.studies.studentbuddy.core.service.SchoolService;
import com.netikras.studies.studentbuddy.core.service.StudentService;
import com.netikras.studies.studentbuddy.core.validator.PersonValidator;
import com.netikras.tools.common.exception.ErrorsCollection;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

import static com.netikras.tools.common.remote.http.HttpStatus.BAD_REQUEST;
import static com.netikras.tools.common.security.IntegrityUtils.isNullOrEmpty;

@Service
public class PersonServiceImpl implements PersonService {

    @Resource
    private PersonDao personDao;
    @Resource
    private PersonValidator personValidator;
    @Resource
    private StudentDao studentDao;
    @Resource
    private LecturerDao lecturerDao;
    @Resource
    private LectureGuestDao guestDao;
    @Resource
    private PersonnelDao personnelDao;
    @Resource
    private StudentService studentService;
    @Resource
    private LecturerService lecturerService;
    @Resource
    private SchoolService schoolService;



    @Override
    public Person getPerson(String id) {
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
        ErrorsCollection errors = personValidator.validateForCreation(person, null);
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
        Person person = getPerson(id);
        ErrorsCollection errors = personValidator.validateForRemoval(person, null);
        if (!errors.isEmpty()) {
            throw new StudBudUncheckedException()
                    .setMessage1("Cannot remove person")
                    .setMessage2("Validation errors: " + errors.size())
                    .setProbableCause(id)
                    .setErrors(errors)
                    .setStatusCode(BAD_REQUEST)
                    ;
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
    @Transactional
    public void purgePerson(String id) {
        Person person = getPerson(id);
        if (person == null) {
            return;
        }

        List<Lecturer> lecturers = lecturerDao.findByPerson_Id(id);
        if (!isNullOrEmpty(lecturers)) {
            lecturers.forEach(lecturer -> lecturerService.purgeLecturer(lecturer.getId()));
        }

        List<Student> students = studentDao.findByPerson_Id(id);
        if (!isNullOrEmpty(students)) {
            students.forEach(student -> studentService.purgeStudent(student.getId()));
        }

        List<LectureGuest> guests = guestDao.findAllByPerson_Id(id);
        if (!isNullOrEmpty(guests)) {
            guests.forEach(guest -> studentService.purgeLectureGuest(guest.getId()));
        }

        List<PersonnelMember> personnelMembers = personnelDao.findAllByPerson_Id(id);
        if (!isNullOrEmpty(personnelMembers)) {
            personnelMembers.forEach(member -> schoolService.purgePersonnelMember(member.getId()));
        }

        personDao.delete(id);
    }

    @Override
    public Person updatePerson(Person person) {
        return personDao.save(person);
    }

}
