package com.netikras.studies.studentbuddy.api.user.producer.impl.secured;

import com.netikras.studies.studentbuddy.core.data.api.dto.PersonDto;
import com.netikras.studies.studentbuddy.core.data.api.model.Person;
import com.netikras.studies.studentbuddy.core.data.meta.annotations.Authorizable;
import com.netikras.studies.studentbuddy.core.service.PersonService;
import com.netikras.tools.common.model.mapper.MappingSettings;
import com.netikras.tools.common.model.mapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

import static com.netikras.studies.studentbuddy.core.data.meta.Action.*;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.PERSON;

@Component
public class PersonProducerImpl {

    @Resource
    private ModelMapper modelMapper;

    @Resource
    private PersonService personService;


    @Authorizable(resource = PERSON, action = GET)
    public PersonDto getPerson(String id) {
        Person person = personService.getPerson(id);
        PersonDto personDto = modelMapper.transform(person, new PersonDto());

        return personDto;
    }

    @Authorizable(resource = PERSON, action = GET)
    public PersonDto getPersonByCode(String code) {
        Person person = personService.findPersonByPersonalCode(code);
        PersonDto personDto = modelMapper.transform(person, new PersonDto());

        return personDto;
    }

    @Authorizable(resource = PERSON, action = GET)
    public PersonDto getPersonByIdentifier(String id) {
        Person person = personService.findPersonByIdentifier(id);
        PersonDto personDto = modelMapper.transform(person, new PersonDto());

        return personDto;
    }

    @Authorizable(resource = PERSON, action = GET_ALL)
    public List<PersonDto> getAllPeople() {
        List<Person> people = personService.findAll();
        List<PersonDto> peopleDtos = (List<PersonDto>) modelMapper.transformAll(people, PersonDto.class);

        return peopleDtos;
    }

    @Authorizable(resource = PERSON, action = GET_ALL)
    public List<PersonDto> getAllPeopleByFirstName(String firstName) {
        List<Person> people = personService.findByFirstName(firstName);
        List<PersonDto> peopleDtos = (List<PersonDto>) modelMapper.transformAll(people, PersonDto.class);

        return peopleDtos;
    }

    @Authorizable(resource = PERSON, action = GET_ALL)
    public List<PersonDto> getAllPeopleByLastName(String lastName) {
        List<Person> people = personService.findByLastName(lastName);
        List<PersonDto> peopleDtos = (List<PersonDto>) modelMapper.transformAll(people, PersonDto.class);

        return peopleDtos;
    }

    @Authorizable(resource = PERSON, action = GET)
    public List<PersonDto> getAllPeopleByFirstAndLastName(String firstName, String lastName) {
        List<Person> people = personService.findByFirstAndLastName(firstName, lastName);
        List<PersonDto> peopleDtos = (List<PersonDto>) modelMapper.transformAll(people, PersonDto.class);

        return peopleDtos;
    }

    @Authorizable(resource = PERSON, action = SEARCH)
    public List<PersonDto> searchAllPeopleByFirstName(String fname) {
        List<Person> people = personService.searchAllByFirstName(fname);
        List<PersonDto> personDtos =
                (List<PersonDto>) modelMapper.transformAll(people, PersonDto.class, new MappingSettings().setDepthMax(3));

        return personDtos;
    }

    @Authorizable(resource = PERSON, action = SEARCH)
    public List<PersonDto> searchAllPeopleByLastName(String lname) {
        List<Person> people = personService.searchAllByLastName(lname);
        List<PersonDto> personDtos =
                (List<PersonDto>) modelMapper.transformAll(people, PersonDto.class, new MappingSettings().setDepthMax(3));

        return personDtos;
    }

    @Authorizable(resource = PERSON, action = SEARCH)
    public List<PersonDto> searchAllPeopleByPersonalCode(String code) {
        List<Person> people = personService.searchAllByPersonalCode(code);
        List<PersonDto> personDtos =
                (List<PersonDto>) modelMapper.transformAll(people, PersonDto.class, new MappingSettings().setDepthMax(3));

        return personDtos;
    }

    @Authorizable(resource = PERSON, action = SEARCH)
    public List<PersonDto> searchAllPeopleByIdentifier(String id) {
        List<Person> people = personService.searchAllByIdentification(id);
        List<PersonDto> personDtos =
                (List<PersonDto>) modelMapper.transformAll(people, PersonDto.class, new MappingSettings().setDepthMax(3));

        return personDtos;
    }
}
