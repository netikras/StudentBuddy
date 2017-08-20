package com.netikras.studies.studentbuddy.api.user.mgmt;

import com.netikras.studies.studentbuddy.core.data.api.dto.PersonDto;
import com.netikras.tools.common.remote.http.GenericRestConsumer;
import com.netikras.tools.common.remote.http.HttpRequest;
import com.netikras.tools.common.remote.http.impl.json.HttpResponseJsonImpl;

import static com.netikras.studies.studentbuddy.api.constants.AdminPersonConstants.endpointCreatePerson;
import static com.netikras.studies.studentbuddy.api.constants.AdminPersonConstants.endpointDeletePersonById;
import static com.netikras.studies.studentbuddy.api.constants.AdminPersonConstants.endpointUpdatePerson;

@SuppressWarnings({"unchecked", "UnnecessaryLocalVariable"})
public class AdminPersonConsumer extends GenericRestConsumer {

    public PersonDto createPerson(PersonDto personDto) {
        HttpRequest<PersonDto> request = createRequest(endpointCreatePerson())
                .withExpectedType(PersonDto.class)
                .setObject(personDto);

        PersonDto dto = (PersonDto) sendRequest(request);
        return dto;
    }

    public boolean deletePersonById(String id) {
        HttpRequest<PersonDto> request = createRequest(endpointDeletePersonById())
                .setUrlProperty("id", id);

        HttpResponseJsonImpl responseJson = new HttpResponseJsonImpl(PersonDto.class);
        sendRequest(request, responseJson);

        return responseJson.isResponseSuccess();
    }

    public PersonDto updatePerson(PersonDto personDto) {
        HttpRequest<PersonDto> request = createRequest(endpointUpdatePerson())
                .withExpectedType(PersonDto.class)
                .setObject(personDto);

        PersonDto dto = (PersonDto) sendRequest(request);

        return dto;
    }
}
