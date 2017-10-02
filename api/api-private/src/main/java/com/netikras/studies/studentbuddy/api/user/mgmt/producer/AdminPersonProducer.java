package com.netikras.studies.studentbuddy.api.user.mgmt.producer;

import com.netikras.studies.studentbuddy.api.user.mgmt.generated.AdminPersonApiProducer;
import com.netikras.studies.studentbuddy.api.user.mgmt.producer.impl.secured.AdminPersonProducerImpl;
import com.netikras.studies.studentbuddy.core.data.api.dto.PersonDto;
import com.netikras.studies.studentbuddy.core.data.api.model.Person;
import com.netikras.studies.studentbuddy.core.data.meta.annotations.Authorizable;
import com.netikras.studies.studentbuddy.core.service.PersonService;
import com.netikras.tools.common.model.mapper.MappingSettings;
import com.netikras.tools.common.model.mapper.ModelMapper;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static com.netikras.studies.studentbuddy.core.data.meta.Action.CREATE;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.DELETE;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.MODIFY;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.PURGE;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.PERSON;

@RestController
public class AdminPersonProducer extends AdminPersonApiProducer {

    @Resource
    private AdminPersonProducerImpl impl;


    @Override
    protected void onPurgePersonDto(String id) {
        impl.purgePerson(id);
    }

    @Override
    protected PersonDto onCreatePersonDto(PersonDto personDto) {
        return impl.createPerson(personDto);
    }

    @Override
    protected void onDeletePersonDto(String id) {
        impl.deletePerson(id);
    }

    @Override
    protected PersonDto onUpdatePersonDto(PersonDto personDto) {
        return impl.updatePerson(personDto);
    }
}
