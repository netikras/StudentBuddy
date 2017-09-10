package com.netikras.studies.studentbuddy.api.user.mgmt.producer;

import com.netikras.studies.studentbuddy.api.user.mgmt.generated.AdminPersonApiProducer;
import com.netikras.studies.studentbuddy.core.data.api.dto.PersonDto;
import com.netikras.studies.studentbuddy.core.data.api.model.Person;
import com.netikras.studies.studentbuddy.core.meta.annotations.Authorizable;
import com.netikras.studies.studentbuddy.core.service.PersonService;
import com.netikras.tools.common.model.mapper.MappingSettings;
import com.netikras.tools.common.model.mapper.ModelMapper;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static com.netikras.studies.studentbuddy.core.meta.Action.CREATE;
import static com.netikras.studies.studentbuddy.core.meta.Action.DELETE;
import static com.netikras.studies.studentbuddy.core.meta.Action.MODIFY;
import static com.netikras.studies.studentbuddy.core.meta.Action.PURGE;
import static com.netikras.studies.studentbuddy.core.meta.Resource.PERSON;

@RestController
public class AdminPersonProducer extends AdminPersonApiProducer {

    @Resource
    private PersonService personService;


    @Override
    @Authorizable(resource = PERSON, action = PURGE)
    protected void onPurgePersonDto(String id) {
        personService.purgePerson(id);
    }

    @Override
    @Authorizable(resource = PERSON, action = CREATE)
    protected PersonDto onCreatePersonDto(PersonDto personDto) {
        Person person = ModelMapper.apply(new Person(), personDto, new MappingSettings().setForceUpdate(true));
        if (person != null) person.setId(null);
        person = personService.createPerson(person);
        personDto = ModelMapper.transform(person, new PersonDto());

        return personDto;
    }

    @Override
    @Authorizable(resource = PERSON, action = DELETE)
    protected void onDeletePersonDto(String id) {
        personService.deletePerson(id);
    }

    @Override
    @Authorizable(resource = PERSON, action = MODIFY)
    protected PersonDto onUpdatePersonDto(PersonDto personDto) {
        Person person = ModelMapper.apply(new Person(), personDto);
        person = personService.updatePerson(person);
        personDto = ModelMapper.transform(person, new PersonDto());

        return personDto;
    }
}
