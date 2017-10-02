package com.netikras.studies.studentbuddy.api.user.producer;

import com.netikras.studies.studentbuddy.api.user.generated.PersonApiProducer;
import com.netikras.studies.studentbuddy.api.user.producer.impl.secured.PersonProducerImpl;
import com.netikras.studies.studentbuddy.core.data.api.dto.PersonDto;
import com.netikras.studies.studentbuddy.core.data.api.model.Person;
import com.netikras.studies.studentbuddy.core.data.meta.annotations.Authorizable;
import com.netikras.studies.studentbuddy.core.service.PersonService;
import com.netikras.studies.studentbuddy.core.service.impl.PersonServiceImpl;
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
    private PersonProducerImpl impl;


    @Override
    protected PersonDto onRetrievePersonDto(String id) {
        return impl.getPerson(id);
    }

    @Override
    protected PersonDto onGetPersonDtoByCode(String code) {
        return impl.getPersonByCode(code);
    }

    @Override
    protected PersonDto onGetPersonDtoByIdentifier(String id) {
        return impl.getPersonByIdentifier(id);
    }

    @Override
    protected List<PersonDto> onGetPersonDtoAll() {
        return impl.getAllPeople();
    }

    @Override
    protected List<PersonDto> onGetPersonDtoAllByFirstName(String firstName) {
        return impl.getAllPeopleByFirstName(firstName);
    }

    @Override
    protected List<PersonDto> onGetPersonDtoAllByLastName(String lastName) {
        return impl.getAllPeopleByLastName(lastName);
    }

    @Override
    protected List<PersonDto> onGetPersonDtoAllByFirstAndLastName(String firstName, String lastName) {
        return impl.getAllPeopleByFirstAndLastName(firstName, lastName);
    }

    @Override
    protected List<PersonDto> onSearchPersonDtoAllByFirstName(String fname) {
        return impl.searchAllPeopleByFirstName(fname);
    }

    @Override
    protected List<PersonDto> onSearchPersonDtoAllByLastName(String lname) {
        return impl.searchAllPeopleByLastName(lname);
    }

    @Override
    protected List<PersonDto> onSearchPersonDtoAllByPersonalCode(String code) {
        return impl.searchAllPeopleByPersonalCode(code);
    }

    @Override
    protected List<PersonDto> onSearchPersonDtoAllByIdentifier(String id) {
        return impl.searchAllPeopleByIdentifier(id);
    }
}
