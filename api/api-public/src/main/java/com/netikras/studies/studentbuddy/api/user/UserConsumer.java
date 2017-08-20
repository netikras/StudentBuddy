package com.netikras.studies.studentbuddy.api.user;

import com.netikras.studies.studentbuddy.core.data.api.dto.meta.UserDto;
import com.netikras.tools.common.remote.AuthenticationDetail;
import com.netikras.tools.common.remote.http.GenericRestConsumer;
import com.netikras.tools.common.remote.http.HttpRequest;
import com.netikras.tools.common.remote.http.impl.json.HttpResponseJsonImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.netikras.studies.studentbuddy.api.constants.UserConstants.endpointChangePassword;
import static com.netikras.studies.studentbuddy.api.constants.UserConstants.endpointGetById;
import static com.netikras.studies.studentbuddy.api.constants.UserConstants.endpointGetByName;
import static com.netikras.studies.studentbuddy.api.constants.UserConstants.endpointGetByPersonId;
import static com.netikras.studies.studentbuddy.api.constants.UserConstants.endpointLogin;
import static com.netikras.studies.studentbuddy.api.constants.UserConstants.endpointLogout;
import static com.netikras.studies.studentbuddy.api.constants.UserConstants.endpointUpdateById;

/**
 * Consumes /api/user endpoint.
 */
@SuppressWarnings({"unchecked", "UnnecessaryLocalVariable"})
public class UserConsumer extends GenericRestConsumer {


    public UserConsumer() {

    }


    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public UserDto login(AuthenticationDetail authenticationDetail) {
        HttpRequest<AuthenticationDetail> request = createRequest(endpointLogin())
                .withExpectedType(UserDto.class)
                .setObject(authenticationDetail);
        UserDto dto = (UserDto) sendRequest(request);
        return dto;
    }

    public boolean logout() {
        HttpRequest<AuthenticationDetail> request = createRequest(endpointLogout());
        HttpResponseJsonImpl responseJson = new HttpResponseJsonImpl();
        sendRequest(request, responseJson);
        return responseJson.isResponseSuccess();
    }

    public UserDto update(UserDto userDto, String id) {
        HttpRequest<UserDto> request = createRequest(endpointUpdateById())
                .withExpectedType(UserDto.class)
                .setUrlProperty("id", id)
                .setObject(userDto);
        UserDto dto = (UserDto) sendRequest(request);
        return dto;
    }

    public UserDto getUser(String id) {
        HttpRequest<UserDto> request = createRequest(endpointGetById())
                .withExpectedType(UserDto.class)
                .setUrlProperty("id", id);
        UserDto dto = (UserDto) sendRequest(request);
        return dto;
    }

    public UserDto getUserByName(String name) {
        HttpRequest request = createRequest(endpointGetByName())
                .withExpectedType(UserDto.class)
                .setUrlProperty("name", name);
        UserDto dto = (UserDto) sendRequest(request);
        return dto;
    }

    public UserDto getUserByPerson(String personId) {
        HttpRequest request = createRequest(endpointGetByPersonId())
                .withExpectedType(UserDto.class)
                .setUrlProperty("id", personId);
        UserDto dto = (UserDto) sendRequest(request);
        return dto;
    }

    public boolean changePassword(String userId, String password) {
        HttpRequest request = createRequest(endpointChangePassword())
                .withExpectedType(UserDto.class)
                .setUrlProperty("id", userId)
                .addParam("password", password);
        HttpResponseJsonImpl responseJson = new HttpResponseJsonImpl(UserDto.class);
        sendRequest(request, responseJson);
        return responseJson.isResponseSuccess();
    }


}
