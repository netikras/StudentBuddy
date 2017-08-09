package com.netikras.studies.studentbuddy.api.user.mgmt;


import com.netikras.studies.studentbuddy.core.data.api.dto.PersonDto;
import com.netikras.studies.studentbuddy.core.data.api.model.Person;
import com.netikras.studies.studentbuddy.core.meta.annotations.Authorizable;
import com.netikras.studies.studentbuddy.core.service.PersonService;
import com.netikras.tools.common.model.mapper.ModelMapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static com.netikras.studies.studentbuddy.core.meta.Action.CREATE;
import static com.netikras.studies.studentbuddy.core.meta.Action.DELETE;
import static com.netikras.studies.studentbuddy.core.meta.Action.MODIFY;
import static com.netikras.studies.studentbuddy.core.meta.Resource.PERSON;

@RestController
@RequestMapping(value = "/mgmt/person")
public class AdminPersonController {


    @Resource
    private PersonService personService;


    @RequestMapping(
            value = "/",
            method = RequestMethod.POST
    )
    @ResponseBody
    @Authorizable(resource = PERSON, action = CREATE)
    public PersonDto createPerson(
            @RequestBody PersonDto personDto
    ) {
        Person person = ModelMapper.apply(new Person(), personDto);
        person = personService.createPerson(person);
        personDto = ModelMapper.transform(person, new PersonDto());

        return personDto;
    }


    @RequestMapping(
            value = "/id/{id}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    @Authorizable(resource = PERSON, action = DELETE)
    public void deletePerson(
            @PathVariable(name = "id") String id
    ) {
        personService.deletePerson(id);
    }


    @RequestMapping(
            value = "/id2/{id}",
            method = RequestMethod.PUT
    )
    @ResponseBody
    @Authorizable(resource = PERSON, action = MODIFY)
    public PersonDto updatePerson(
            @RequestBody PersonDto personDto
    ) {
        Person person = ModelMapper.apply(new Person(), personDto);
        person = personService.updatePerson(person);
        personDto = ModelMapper.transform(person, new PersonDto());

        return personDto;
    }


}
