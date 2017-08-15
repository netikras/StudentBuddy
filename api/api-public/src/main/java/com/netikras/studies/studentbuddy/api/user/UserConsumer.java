package com.netikras.studies.studentbuddy.api.user;

import com.netikras.studies.studentbuddy.core.data.api.dto.meta.UserDto;
import com.netikras.tools.common.remote.AuthenticationDetail;
import com.netikras.tools.common.remote.http.GenericRestConsumer;
import com.netikras.tools.common.remote.http.HttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.netikras.studies.studentbuddy.api.user.UserConstants.endpointGetById;
import static com.netikras.studies.studentbuddy.api.user.UserConstants.endpointGetByName;
import static com.netikras.studies.studentbuddy.api.user.UserConstants.endpointGetByPersonId;
import static com.netikras.studies.studentbuddy.api.user.UserConstants.endpointLogin;

/**
 * Consumes /api/user endpoint.
 */
public class UserConsumer extends GenericRestConsumer {


    public UserConsumer() {

    }


    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public UserDto login(AuthenticationDetail authenticationDetail) {
        HttpRequest<AuthenticationDetail> request =
                createRequest(endpointLogin())
                        .withExpectedType(UserDto.class)
                        .setObject(authenticationDetail);
        UserDto dto = (UserDto) sendRequest(request);
        return dto;
    }

    public UserDto getUser(String id) {
        HttpRequest<UserDto> request =
                createRequest(endpointGetById())
                        .withExpectedType(UserDto.class)
                        .setUrlProperty("id", id);
        UserDto dto = (UserDto) sendRequest(request);
        return dto;
    }

    public UserDto getUserByName(String name) {
        HttpRequest<UserDto> request =
                createRequest(endpointGetByName())
                        .withExpectedType(UserDto.class)
                        .setUrlProperty("name", name);
        UserDto dto = (UserDto) sendRequest(request);
        return dto;
    }

    public UserDto getUserByPerson(String personId) {
        HttpRequest<UserDto> request =
                createRequest(endpointGetByPersonId())
                        .withExpectedType(UserDto.class)
                        .setUrlProperty("id", personId);
        UserDto dto = (UserDto) sendRequest(request);
        return dto;
    }


}
