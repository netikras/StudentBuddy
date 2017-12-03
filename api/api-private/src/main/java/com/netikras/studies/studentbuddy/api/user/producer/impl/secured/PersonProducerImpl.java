package com.netikras.studies.studentbuddy.api.user.producer.impl.secured;

import com.netikras.studies.studentbuddy.api.handlers.DtoMapper;
import com.netikras.studies.studentbuddy.core.data.api.dto.PersonDto;
import com.netikras.studies.studentbuddy.core.data.api.model.Person;
import com.netikras.studies.studentbuddy.core.data.meta.annotations.Authorizable;
import com.netikras.studies.studentbuddy.core.service.PersonService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

import static com.netikras.studies.studentbuddy.core.data.meta.Action.GET;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.GET_ALL;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.SEARCH;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.PERSON;

@Component
public class PersonProducerImpl {

    @Resource
    private PersonService personService;

    @Resource
    private DtoMapper dtoMapper;

    @Authorizable(resource = PERSON, action = GET)
    @Transactional
    public PersonDto getPerson(String id) {
        Person person = personService.getPerson(id);
        PersonDto personDto = (PersonDto) dtoMapper.toDto(person);
        return personDto;
    }

    @Authorizable(resource = PERSON, action = GET)
    @Transactional
    public PersonDto getPersonByCode(String code) {
        Person person = personService.findPersonByPersonalCode(code);
        PersonDto personDto = (PersonDto) dtoMapper.toDto(person);
        return personDto;
    }

    @Authorizable(resource = PERSON, action = GET)
    @Transactional
    public PersonDto getPersonByIdentifier(String id) {
        Person person = personService.findPersonByIdentifier(id);
        PersonDto personDto = (PersonDto) dtoMapper.toDto(person);
        return personDto;
    }

    @Authorizable(resource = PERSON, action = GET_ALL)
    @Transactional
    public List<PersonDto> getAllPeople() {
        List<Person> people = personService.findAll();
        List<PersonDto> personDtos = (List<PersonDto>) dtoMapper.toDtos(people);
        return personDtos;
    }

    @Authorizable(resource = PERSON, action = GET_ALL)
    @Transactional
    public List<PersonDto> getAllPeopleByFirstName(String firstName) {
        List<Person> people = personService.findByFirstName(firstName);
        List<PersonDto> personDtos = (List<PersonDto>) dtoMapper.toDtos(people);
        return personDtos;
    }

    @Authorizable(resource = PERSON, action = GET_ALL)
    @Transactional
    public List<PersonDto> getAllPeopleByLastName(String lastName) {
        List<Person> people = personService.findByLastName(lastName);
        List<PersonDto> personDtos = (List<PersonDto>) dtoMapper.toDtos(people);
        return personDtos;
    }

    @Authorizable(resource = PERSON, action = GET)
    @Transactional
    public List<PersonDto> getAllPeopleByFirstAndLastName(String firstName, String lastName) {
        List<Person> people = personService.findByFirstAndLastName(firstName, lastName);
        List<PersonDto> personDtos = (List<PersonDto>) dtoMapper.toDtos(people);
        return personDtos;
    }

    @Authorizable(resource = PERSON, action = SEARCH)
    @Transactional
    public List<PersonDto> searchAllPeopleByFirstName(String fname) {
        List<Person> people = personService.searchAllByFirstName(fname);
        List<PersonDto> personDtos = (List<PersonDto>) dtoMapper.toDtos(people);
        return personDtos;
    }

    @Authorizable(resource = PERSON, action = SEARCH)
    @Transactional
    public List<PersonDto> searchAllPeopleByLastName(String lname) {
        List<Person> people = personService.searchAllByLastName(lname);
        List<PersonDto> personDtos = (List<PersonDto>) dtoMapper.toDtos(people);
        return personDtos;
    }

    @Authorizable(resource = PERSON, action = SEARCH)
    @Transactional
    public List<PersonDto> searchAllPeopleByPersonalCode(String code) {
        List<Person> people = personService.searchAllByPersonalCode(code);
        List<PersonDto> personDtos = (List<PersonDto>) dtoMapper.toDtos(people);
        return personDtos;
    }

    @Authorizable(resource = PERSON, action = SEARCH)
    @Transactional
    public List<PersonDto> searchAllPeopleByIdentifier(String id) {
        List<Person> people = personService.searchAllByIdentification(id);
        List<PersonDto> personDtos = (List<PersonDto>) dtoMapper.toDtos(people);
        return personDtos;
    }
}
