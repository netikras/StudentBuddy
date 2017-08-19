package com.netikras.studies.studentbuddy.api.user;

import com.netikras.studies.studentbuddy.core.data.api.dto.PersonDto;
import com.netikras.studies.studentbuddy.core.data.api.model.Person;
import com.netikras.studies.studentbuddy.core.meta.annotations.Authorizable;
import com.netikras.studies.studentbuddy.core.service.PersonService;
import com.netikras.tools.common.model.mapper.ModelMapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static com.netikras.studies.studentbuddy.api.constants.PersonConstants.BASE_URL;
import static com.netikras.studies.studentbuddy.api.constants.PersonConstants.PERSON_URL_GET_BY_CODE;
import static com.netikras.studies.studentbuddy.api.constants.PersonConstants.PERSON_URL_GET_BY_ID;
import static com.netikras.studies.studentbuddy.api.constants.PersonConstants.PERSON_URL_GET_BY_IDENTIFIER;
import static com.netikras.studies.studentbuddy.core.meta.Action.GET;
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


}
