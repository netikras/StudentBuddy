package com.netikras.studies.studentbuddy.api.user.producer;

import com.netikras.studies.studentbuddy.api.user.generated.PersonApiProducer;
import com.netikras.studies.studentbuddy.core.data.api.dto.PersonDto;
import com.netikras.studies.studentbuddy.core.data.api.model.Person;
import com.netikras.studies.studentbuddy.core.data.meta.annotations.Authorizable;
import com.netikras.studies.studentbuddy.core.service.PersonService;
import com.netikras.tools.common.model.mapper.MappingSettings;
import com.netikras.tools.common.model.mapper.ModelMapper;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

import static com.netikras.studies.studentbuddy.core.data.meta.Action.GET;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.GET_ALL;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.SEARCH;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.PERSON;

@RestController
public class PersonProducer extends PersonApiProducer {

    @Resource
    private PersonService personService;


    @Override
    @Authorizable(resource = PERSON, action = GET)
    protected PersonDto onRetrievePersonDto(String id) {
        Person person = personService.getPerson(id);
        PersonDto personDto = ModelMapper.transform(person, new PersonDto());

        return personDto;
    }

    @Override
    @Authorizable(resource = PERSON, action = GET)
    protected PersonDto onGetPersonDtoByCode(String code) {
        Person person = personService.findPersonByPersonalCode(code);
        PersonDto personDto = ModelMapper.transform(person, new PersonDto());

        return personDto;
    }

    @Override
    @Authorizable(resource = PERSON, action = GET)
    protected PersonDto onGetPersonDtoByIdentifier(String id) {
        Person person = personService.findPersonByIdentifier(id);
        PersonDto personDto = ModelMapper.transform(person, new PersonDto());

        return personDto;
    }

    @Override
    @Authorizable(resource = PERSON, action = GET_ALL)
    protected List<PersonDto> onGetPersonDtoAll() {
        List<Person> people = personService.findAll();
        List<PersonDto> peopleDtos = (List<PersonDto>) ModelMapper.transformAll(people, PersonDto.class);

        return peopleDtos;
    }

    @Override
    @Authorizable(resource = PERSON, action = GET_ALL)
    protected List<PersonDto> onGetPersonDtoAllByFirstName(String firstName) {
        List<Person> people = personService.findByFirstName(firstName);
        List<PersonDto> peopleDtos = (List<PersonDto>) ModelMapper.transformAll(people, PersonDto.class);

        return peopleDtos;
    }

    @Override
    @Authorizable(resource = PERSON, action = GET_ALL)
    protected List<PersonDto> onGetPersonDtoAllByLastName(String lastName) {
        List<Person> people = personService.findByLastName(lastName);
        List<PersonDto> peopleDtos = (List<PersonDto>) ModelMapper.transformAll(people, PersonDto.class);

        return peopleDtos;
    }

    @Override
    @Authorizable(resource = PERSON, action = GET)
    protected List<PersonDto> onGetPersonDtoAllByFirstAndLastName(String firstName, String lastName) {
        List<Person> people = personService.findByFirstAndLastName(firstName, lastName);
        List<PersonDto> peopleDtos = (List<PersonDto>) ModelMapper.transformAll(people, PersonDto.class);

        return peopleDtos;
    }

    @Override
    @Authorizable(resource = PERSON, action = SEARCH)
    protected List<PersonDto> onSearchPersonDtoAllByFirstName(String fname) {
        List<Person> people = personService.searchAllByFirstName(fname);
        List<PersonDto> personDtos =
                (List<PersonDto>) ModelMapper.transformAll(people, PersonDto.class, new MappingSettings().setDepthMax(3));

        return personDtos;
    }

    @Override
    @Authorizable(resource = PERSON, action = SEARCH)
    protected List<PersonDto> onSearchPersonDtoAllByLastName(String lname) {
        List<Person> people = personService.searchAllByLastName(lname);
        List<PersonDto> personDtos =
                (List<PersonDto>) ModelMapper.transformAll(people, PersonDto.class, new MappingSettings().setDepthMax(3));

        return personDtos;
    }

    @Override
    @Authorizable(resource = PERSON, action = SEARCH)
    protected List<PersonDto> onSearchPersonDtoAllByPersonalCode(String code) {
        List<Person> people = personService.searchAllByPersonalCode(code);
        List<PersonDto> personDtos =
                (List<PersonDto>) ModelMapper.transformAll(people, PersonDto.class, new MappingSettings().setDepthMax(3));

        return personDtos;
    }

    @Override
    @Authorizable(resource = PERSON, action = SEARCH)
    protected List<PersonDto> onSearchPersonDtoAllByIdentifier(String id) {
        List<Person> people = personService.searchAllByIdentification(id);
        List<PersonDto> personDtos =
                (List<PersonDto>) ModelMapper.transformAll(people, PersonDto.class, new MappingSettings().setDepthMax(3));

        return personDtos;
    }
}
