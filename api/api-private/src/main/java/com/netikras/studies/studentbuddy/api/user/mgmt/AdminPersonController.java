package com.netikras.studies.studentbuddy.api.user.mgmt;


import com.netikras.studies.studentbuddy.core.data.api.dto.PersonDto;
import com.netikras.studies.studentbuddy.core.data.api.model.Person;
import com.netikras.studies.studentbuddy.core.meta.annotations.Authorizable;
import com.netikras.studies.studentbuddy.core.service.PersonService;
import com.netikras.tools.common.model.mapper.MappingSettings;
import com.netikras.tools.common.model.mapper.ModelMapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static com.netikras.studies.studentbuddy.api.constants.AdminPersonConstants.ADM_PERS_URL_CREATE;
import static com.netikras.studies.studentbuddy.api.constants.AdminPersonConstants.ADM_PERS_URL_DELETE_BY_ID;
import static com.netikras.studies.studentbuddy.api.constants.AdminPersonConstants.ADM_PERS_URL_UPDATE;
import static com.netikras.studies.studentbuddy.api.constants.AdminPersonConstants.BASE_URL;
import static com.netikras.studies.studentbuddy.core.meta.Action.CREATE;
import static com.netikras.studies.studentbuddy.core.meta.Action.DELETE;
import static com.netikras.studies.studentbuddy.core.meta.Action.MODIFY;
import static com.netikras.studies.studentbuddy.core.meta.Resource.PERSON;

@RestController
@RequestMapping(value = BASE_URL)
public class AdminPersonController {


    @Resource
    private PersonService personService;


    @RequestMapping(
            value = ADM_PERS_URL_CREATE,
            method = RequestMethod.POST
    )
    @ResponseBody
    @Authorizable(resource = PERSON, action = CREATE)
    public PersonDto createPerson(
            @RequestBody PersonDto personDto
    ) {
        Person person = ModelMapper.apply(new Person(), personDto, new MappingSettings().setForceUpdate(true));
        person.setId(null);
        person = personService.createPerson(person);
        personDto = ModelMapper.transform(person, new PersonDto());

        return personDto;
    }


    @RequestMapping(
            value = ADM_PERS_URL_DELETE_BY_ID,
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
            value = ADM_PERS_URL_UPDATE,
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
