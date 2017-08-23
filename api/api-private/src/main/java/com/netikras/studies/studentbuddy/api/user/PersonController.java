package com.netikras.studies.studentbuddy.api.user;

import com.netikras.studies.studentbuddy.core.data.api.dto.PersonDto;
import com.netikras.studies.studentbuddy.core.data.api.model.Person;
import com.netikras.studies.studentbuddy.core.meta.annotations.Authorizable;
import com.netikras.studies.studentbuddy.core.service.PersonService;
import com.netikras.tools.common.model.mapper.MappingSettings;
import com.netikras.tools.common.model.mapper.ModelMapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import java.util.List;

import static com.netikras.studies.studentbuddy.api.constants.PersonConstants.BASE_URL;
import static com.netikras.studies.studentbuddy.api.constants.PersonConstants.PERSON_URL_GET_ALL;
import static com.netikras.studies.studentbuddy.api.constants.PersonConstants.PERSON_URL_GET_ALL_BY_FIRST_AND_LAST_NAME;
import static com.netikras.studies.studentbuddy.api.constants.PersonConstants.PERSON_URL_GET_ALL_BY_FIRST_NAME;
import static com.netikras.studies.studentbuddy.api.constants.PersonConstants.PERSON_URL_GET_ALL_BY_LAST_NAME;
import static com.netikras.studies.studentbuddy.api.constants.PersonConstants.PERSON_URL_GET_BY_CODE;
import static com.netikras.studies.studentbuddy.api.constants.PersonConstants.PERSON_URL_GET_BY_ID;
import static com.netikras.studies.studentbuddy.api.constants.PersonConstants.PERSON_URL_GET_BY_IDENTIFIER;
import static com.netikras.studies.studentbuddy.core.meta.Action.GET;
import static com.netikras.studies.studentbuddy.core.meta.Action.GET_ALL;
import static com.netikras.studies.studentbuddy.core.meta.Action.MODERATE;
import static com.netikras.studies.studentbuddy.core.meta.Action.SEARCH;
import static com.netikras.studies.studentbuddy.core.meta.Resource.PERSON;

@RestController
@RequestMapping(BASE_URL)
public class PersonController {


    @Resource
    private PersonService personService;


    @RequestMapping(
            value = PERSON_URL_GET_BY_ID,
            method = RequestMethod.GET
    )
    @ResponseBody
    @Authorizable(resource = PERSON, action = GET)
    public PersonDto getPerson(
            @PathVariable(name = "id") String id
    ) {
        Person person = personService.findPerson(id);
        PersonDto personDto = ModelMapper.transform(person, new PersonDto());

        return personDto;
    }

    @RequestMapping(
            value = PERSON_URL_GET_BY_CODE,
            method = RequestMethod.GET
    )
    @ResponseBody
    @Authorizable(resource = PERSON, action = GET)
    public PersonDto getPersonByCode(
            @PathVariable(name = "code") String code
    ) {
        Person person = personService.findPersonByPersonalCode(code);
        PersonDto personDto = ModelMapper.transform(person, new PersonDto());

        return personDto;
    }


    @RequestMapping(
            value = PERSON_URL_GET_BY_IDENTIFIER,
            method = RequestMethod.GET
    )
    @ResponseBody
    @Authorizable(resource = PERSON, action = GET)
    public PersonDto getPersonByIdentifier(
            @PathVariable(name = "id") String id
    ) {
        Person person = personService.findPersonByIdentifier(id);
        PersonDto personDto = ModelMapper.transform(person, new PersonDto());

        return personDto;
    }

    @RequestMapping(
            value = PERSON_URL_GET_ALL,
            method = RequestMethod.GET
    )
    @ResponseBody
    @Authorizable(resource = PERSON, action = GET_ALL)
    public List<PersonDto> getAllPeople() {
        List<Person> people = personService.findAll();
        List<PersonDto> peopleDtos = (List<PersonDto>) ModelMapper.transformAll(people, PersonDto.class);

        return peopleDtos;
    }

    @RequestMapping(
            value = PERSON_URL_GET_ALL_BY_FIRST_NAME,
            method = RequestMethod.GET
    )
    @ResponseBody
    @Authorizable(resource = PERSON, action = SEARCH)
    public List<PersonDto> getAllByFirstName(
            @PathVariable(name = "firstName") String firstName
    ) {
        List<Person> people = personService.findByFirstName(firstName);
        List<PersonDto> peopleDtos = (List<PersonDto>) ModelMapper.transformAll(people, PersonDto.class);

        return peopleDtos;
    }

    @RequestMapping(
            value = PERSON_URL_GET_ALL_BY_LAST_NAME,
            method = RequestMethod.GET
    )
    @ResponseBody
    @Authorizable(resource = PERSON, action = SEARCH)
    public List<PersonDto> getAllByLastName(
            @PathVariable(name = "lastName") String lastName
    ) {
        List<Person> people = personService.findByLastName(lastName);
        List<PersonDto> peopleDtos = (List<PersonDto>) ModelMapper.transformAll(people, PersonDto.class);

        return peopleDtos;
    }

    @RequestMapping(
            value = PERSON_URL_GET_ALL_BY_FIRST_AND_LAST_NAME,
            method = RequestMethod.GET
    )
    @ResponseBody
    @Authorizable(resource = PERSON, action = SEARCH)
    public List<PersonDto> getAllByFirstAndLastName(
            @PathVariable(name = "firstName") String firstName,
            @PathVariable(name = "lastName") String lastName

    ) {
        List<Person> people = personService.findByFirstAndLastName(firstName, lastName);
        List<PersonDto> peopleDtos = (List<PersonDto>) ModelMapper.transformAll(people, PersonDto.class);

        return peopleDtos;
    }


    @RequestMapping(
            value = "/search/firstName/{fname}",
            method = RequestMethod.GET
    )
    @ResponseBody
    @Authorizable(resource = PERSON, action = SEARCH)
    public List<PersonDto> searchAllPeopleByFirstName(
            @PathVariable(name = "fname") String fnameSubstring
    ) {
        List<Person> people = personService.searchAllByFirstName(fnameSubstring);
        List<PersonDto> personDtos =
                (List<PersonDto>) ModelMapper.transformAll(people, PersonDto.class, new MappingSettings().setDepthMax(3));

        return personDtos;
    }

    @RequestMapping(
            value = "/search/lastName/{lname}",
            method = RequestMethod.GET
    )
    @ResponseBody
    @Authorizable(resource = PERSON, action = SEARCH)
    public List<PersonDto> searchAllPeopleByLastName(
            @PathVariable(name = "lname") String lnameSubstring
    ) {
        List<Person> people = personService.searchAllByLastName(lnameSubstring);
        List<PersonDto> personDtos =
                (List<PersonDto>) ModelMapper.transformAll(people, PersonDto.class, new MappingSettings().setDepthMax(3));

        return personDtos;
    }

    @RequestMapping(
            value = "/search/code/{code}",
            method = RequestMethod.GET
    )
    @ResponseBody
    @Authorizable(resource = PERSON, action = SEARCH)
    public List<PersonDto> searchAllPeopleByPersonalCode(
            @PathVariable(name = "code") String codeSubstring
    ) {
        List<Person> people = personService.searchAllByPersonalCode(codeSubstring);
        List<PersonDto> personDtos =
                (List<PersonDto>) ModelMapper.transformAll(people, PersonDto.class, new MappingSettings().setDepthMax(3));

        return personDtos;
    }

    @RequestMapping(
            value = "/search/id2/{id}",
            method = RequestMethod.GET
    )
    @ResponseBody
    @Authorizable(resource = PERSON, action = SEARCH)
    public List<PersonDto> searchAllPeopleByIdentifier(
            @PathVariable(name = "id") String id
    ) {
        List<Person> people = personService.searchAllByIdentification(id);
        List<PersonDto> personDtos =
                (List<PersonDto>) ModelMapper.transformAll(people, PersonDto.class, new MappingSettings().setDepthMax(3));

        return personDtos;
    }

}
