package com.netikras.studies.studentbuddy.api.user.mgmt.producer.impl.secured;

import com.netikras.studies.studentbuddy.api.handlers.DtoMapper;
import com.netikras.studies.studentbuddy.core.data.api.dto.PersonDto;
import com.netikras.studies.studentbuddy.core.data.api.model.Person;
import com.netikras.studies.studentbuddy.core.data.meta.annotations.Authorizable;
import com.netikras.studies.studentbuddy.core.service.PersonService;
import com.netikras.studies.studentbuddy.core.validator.EntityProvider;
import com.netikras.tools.common.model.mapper.MappingSettings;
import com.netikras.tools.common.model.mapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import static com.netikras.studies.studentbuddy.core.data.meta.Action.*;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.PERSON;

@Component
public class AdminPersonProducerImpl {

    @Resource
    private ModelMapper modelMapper;

    @Resource
    private PersonService personService;
    @Resource
    private DtoMapper dtoMapper;
    @Resource
    private EntityProvider entityProvider;


    @Authorizable(resource = PERSON, action = PURGE)
    public void purgePerson(String id) {
        personService.purgePerson(id);
    }

    @Authorizable(resource = PERSON, action = CREATE)
    public PersonDto createPerson(PersonDto personDto) {
        Person person = modelMapper.apply(new Person(), personDto, new MappingSettings().setForceUpdate(true));
        if (person != null) person.setId(null);
        person = personService.createPerson(person);
        personDto = (PersonDto) dtoMapper.toDto(person, 3);

        return personDto;
    }

    @Authorizable(resource = PERSON, action = DELETE)
    public void deletePerson(String id) {
        personService.deletePerson(id);
    }

    @Authorizable(resource = PERSON, action = MODIFY)
    public PersonDto updatePerson(PersonDto personDto) {
        Person person = modelMapper.apply(entityProvider.fetch(personDto), personDto);
        person = personService.updatePerson(person);
        personDto = (PersonDto) dtoMapper.toDto(person, 3);

        return personDto;
    }
}
