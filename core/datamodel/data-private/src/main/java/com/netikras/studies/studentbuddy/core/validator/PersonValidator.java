package com.netikras.studies.studentbuddy.core.validator;

import com.netikras.studies.studentbuddy.core.data.api.dao.PersonDao;
import com.netikras.studies.studentbuddy.core.data.api.dao.StudentDao;
import com.netikras.studies.studentbuddy.core.data.api.dao.StudentsGroupDao;
import com.netikras.studies.studentbuddy.core.data.api.model.Person;
import com.netikras.studies.studentbuddy.core.data.api.model.Student;
import com.netikras.studies.studentbuddy.core.data.api.model.StudentsGroup;
import com.netikras.tools.common.exception.ErrorsCollection;
import com.netikras.tools.common.exception.ValidationError;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import static com.netikras.tools.common.remote.http.HttpStatus.CONFLICT;
import static com.netikras.tools.common.remote.http.HttpStatus.EXPECTATION_FAILED;
import static com.netikras.tools.common.remote.http.HttpStatus.NOT_FOUND;

@Component
public class PersonValidator {

    @Resource
    private StudentDao studentDao;

    @Resource
    private StudentsGroupDao groupDao;

    @Resource
    private PersonDao personDao;


    public ErrorsCollection validateStudentForCreation(Student student, ErrorsCollection errors) {
        if (errors == null) errors = new ErrorsCollection();

        if (student == null) {
            errors.add(new ValidationError()
                    .setMessage1("Student is not supplied")
                    .setStatus(NOT_FOUND.getCode())
            );
        } else {
            if (student.getGroup() != null) {
                StudentsGroup group = fetchGroup(student.getGroup());
                if (group == null) {
                    errors.add(new ValidationError()
                            .setSuggestion("Group must have either a valid ID or Title or both")
                            .setMessage1("Provided group cannot be found in the database")
                            .setStatus(NOT_FOUND.getCode())
                    );
                } else {
                    compare(group, student.getGroup(), errors);
                    student.setGroup(group);
                }
            }

            Person person = fetchPerson(student.getPerson());
            if (person == null) {
                errors.add(new ValidationError()
                        .setSuggestion("All students must be based on an existing person. Either refer to an already existing person " +
                                "or create a new one and refer to it by valid ID, IDENTIFICATION or PERSONAL_CODE or any valid combination of those")
                        .setMessage1("Student person cannot be found in the database")
                        .setStatus(NOT_FOUND.getCode())
                );
            } else {
                compare(person, student.getPerson(), errors);
                student.setPerson(person);
                Student existingStudent = studentDao.findByPerson_Id(person.getId());
                if (existingStudent != null) {
                    errors.add(new ValidationError()
                            .setSuggestion("Each person can have only one STUDENT record. Either use an existing student or remove it " +
                                    "and create a new one")
                            .setMessage1("This person is already a student")
                            .setStatus(CONFLICT.getCode())
                    );
                }
            }
        }

        return errors;
    }

    private void compare(StudentsGroup groupFromDb, StudentsGroup suppliedGroup, ErrorsCollection errors) {

        String groupId = suppliedGroup.getId();
        String groupTitle = suppliedGroup.getTitle();

        if (groupId != null && !groupId.equals(groupFromDb.getId())) {
            errors.add(new ValidationError()
                    .setSuggestion("Either choose correct group_ID and group_TITLE combination or use either of those two")
                    .setMessage1("Supplied group ID does not match group found in database")
                    .setCausedBy(groupId)
                    .setStatus(EXPECTATION_FAILED.getCode())
            );
        }

        if (groupTitle != null && !groupTitle.equals(groupFromDb.getTitle())) {
            errors.add(new ValidationError()
                    .setSuggestion("Either choose correct group_ID and group_TITLE combination or use either of those two")
                    .setMessage1("Supplied group TITLE does not match group found in database")
                    .setCausedBy(groupTitle)
                    .setStatus(EXPECTATION_FAILED.getCode())
            );
        }


    }

    private void compare(Person personFromDb, Person suppliedPerson, ErrorsCollection errors) {
        String personId = suppliedPerson.getId();
        String personIdentifier = suppliedPerson.getIdentification();
        String personCode = suppliedPerson.getPersonalCode();

        if (personId != null && !personId.equals(personFromDb.getId())) {
            errors.add(new ValidationError()
                    .setSuggestion("Either provide a valid person ID or omit it altogether")
                    .setMessage1("Supplied person ID does not match person found in database")
                    .setCausedBy(personCode)
                    .setStatus(NOT_FOUND.getCode())
            );
        }

        if (personIdentifier != null && !personIdentifier.equals(personFromDb.getIdentification())) {
            errors.add(new ValidationError()
                    .setSuggestion("Either provide a valid person IDENTIFIER or omit it altogether")
                    .setMessage1("Supplied person IDENTIFIER does not match person found in database")
                    .setCausedBy(personCode)
                    .setStatus(EXPECTATION_FAILED.getCode())
            );
        }

        if (personCode != null && !personCode.equals(personFromDb.getPersonalCode())) {
            errors.add(new ValidationError()
                    .setSuggestion("Either provide a valid person CODE or omit it altogether")
                    .setMessage1("Supplied person CODE does not match person found in database")
                    .setCausedBy(personCode)
                    .setStatus(EXPECTATION_FAILED.getCode())
            );
        }
    }

    private StudentsGroup fetchGroup(StudentsGroup group) {
        if (group == null) return group;
        StudentsGroup fetchedGroup = null;

        String groupId = group.getId();
        String groupTitle = group.getTitle();

        if (groupId != null && !groupId.isEmpty()) {
            fetchedGroup = groupDao.findOne(groupId);
        } else if (groupTitle != null && !groupTitle.isEmpty()) {
            fetchedGroup = groupDao.findByTitle(groupTitle);
        }

        return fetchedGroup;
    }

    private Person fetchPerson(Person person) {
        Person fetchedPerson = null;

        if (person == null) {
            return person;
        }

        String personId = person.getId();
        String personIdentifier = person.getIdentification();
        String personCode = person.getPersonalCode();

        if (personId != null && !personId.isEmpty()) {
            fetchedPerson = personDao.findOne(personId);
        } else if (personIdentifier != null && !personIdentifier.isEmpty()) {
            fetchedPerson = personDao.findByIdentification(personIdentifier);
        } else if (personCode != null && !personCode.isEmpty()) {
            fetchedPerson = personDao.findByPersonalCode(personCode);
        }

        return fetchedPerson;
    }

}
