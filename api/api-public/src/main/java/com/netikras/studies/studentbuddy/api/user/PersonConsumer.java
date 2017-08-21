package com.netikras.studies.studentbuddy.api.user;

import com.fasterxml.jackson.core.type.TypeReference;
import com.netikras.studies.studentbuddy.core.data.api.dto.PersonDto;
import com.netikras.tools.common.remote.http.GenericRestConsumer;
import com.netikras.tools.common.remote.http.HttpRequest;

import java.util.List;

import static com.netikras.studies.studentbuddy.api.constants.PersonConstants.endpointGetAll;
import static com.netikras.studies.studentbuddy.api.constants.PersonConstants.endpointGetAllByFirstAndLastName;
import static com.netikras.studies.studentbuddy.api.constants.PersonConstants.endpointGetAllByFirstName;
import static com.netikras.studies.studentbuddy.api.constants.PersonConstants.endpointGetAllByLastName;
import static com.netikras.studies.studentbuddy.api.constants.PersonConstants.endpointGetByCode;
import static com.netikras.studies.studentbuddy.api.constants.PersonConstants.endpointGetById;
import static com.netikras.studies.studentbuddy.api.constants.PersonConstants.endpointGetByIdetifier;

@SuppressWarnings({"unchecked", "UnnecessaryLocalVariable"})
public class PersonConsumer extends GenericRestConsumer {

    private TypeReference peopleListTypeRef = new TypeReference<List<PersonDto>>() {
    };

    public PersonDto getById(String id) {
        HttpRequest request = createRequest(endpointGetById())
                .withExpectedType(PersonDto.class)
                .setUrlProperty("id", id);

        PersonDto personDto = (PersonDto) sendRequest(request);
        return personDto;
    }

    public PersonDto getByIdentifier(String identifier) {
        HttpRequest request = createRequest(endpointGetByIdetifier())
                .withExpectedType(PersonDto.class)
                .setUrlProperty("id", identifier);

        PersonDto personDto = (PersonDto) sendRequest(request);
        return personDto;
    }

    public PersonDto getByCode(String code) {
        HttpRequest request = createRequest(endpointGetByCode())
                .withExpectedType(PersonDto.class)
                .setUrlProperty("code", code);

        PersonDto personDto = (PersonDto) sendRequest(request);
        return personDto;
    }

    public List<PersonDto> getAll() {
        HttpRequest request = createRequest(endpointGetAll())
                .withTypeReference(peopleListTypeRef);

        List<PersonDto> personDtos = (List<PersonDto>) sendRequest(request);
        return personDtos;
    }

    public List<PersonDto> getAllByFirstName(String firstName) {
        HttpRequest request = createRequest(endpointGetAllByFirstName())
                .withTypeReference(peopleListTypeRef)
                .setUrlProperty("firstName", firstName);

        List<PersonDto> personDtos = (List<PersonDto>) sendRequest(request);
        return personDtos;
    }

    public List<PersonDto> getAllByLastName(String lastName) {
        HttpRequest request = createRequest(endpointGetAllByLastName())
                .withTypeReference(peopleListTypeRef)
                .setUrlProperty("lastName", lastName);

        List<PersonDto> personDtos = (List<PersonDto>) sendRequest(request);
        return personDtos;
    }

    public List<PersonDto> getAllByFirstAndLastName(String firstName, String lastName) {
        HttpRequest request = createRequest(endpointGetAllByFirstAndLastName())
                .withTypeReference(peopleListTypeRef)
                .setUrlProperty("firstName", firstName)
                .setUrlProperty("lastName", lastName);

        List<PersonDto> personDtos = (List<PersonDto>) sendRequest(request);
        return personDtos;
    }

}
