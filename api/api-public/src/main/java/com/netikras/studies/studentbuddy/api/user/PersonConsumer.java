package com.netikras.studies.studentbuddy.api.user;

import com.netikras.studies.studentbuddy.core.data.api.dto.PersonDto;
import com.netikras.tools.common.remote.http.GenericRestConsumer;
import com.netikras.tools.common.remote.http.HttpRequest;

import static com.netikras.studies.studentbuddy.api.constants.PersonConstants.endpointGetByCode;
import static com.netikras.studies.studentbuddy.api.constants.PersonConstants.endpointGetById;
import static com.netikras.studies.studentbuddy.api.constants.PersonConstants.endpointGetByIdetifier;

@SuppressWarnings({"unchecked", "UnnecessaryLocalVariable"})
public class PersonConsumer extends GenericRestConsumer {

    public PersonDto getById(String id) {
        HttpRequest<PersonDto> request = createRequest(endpointGetById())
                .withExpectedType(PersonDto.class)
                .setUrlProperty("id", id);

        PersonDto personDto = (PersonDto) sendRequest(request);
        return personDto;
    }

    public PersonDto getByIdentifier(String identifier) {
        HttpRequest<PersonDto> request = createRequest(endpointGetByIdetifier())
                .withExpectedType(PersonDto.class)
                .setUrlProperty("id", identifier);

        PersonDto personDto = (PersonDto) sendRequest(request);
        return personDto;
    }

    public PersonDto getByCode(String code) {
        HttpRequest<PersonDto> request = createRequest(endpointGetByCode())
                .withExpectedType(PersonDto.class)
                .setUrlProperty("code", code);

        PersonDto personDto = (PersonDto) sendRequest(request);
        return personDto;
    }




}
