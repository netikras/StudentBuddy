package com.netikras.studies.studentbuddy.api.user.mgmt;

import com.netikras.studies.studentbuddy.core.data.api.dto.meta.UserDto;
import com.netikras.tools.common.remote.http.GenericRestConsumer;
import com.netikras.tools.common.remote.http.HttpRequest;
import com.netikras.tools.common.remote.http.impl.json.HttpResponseJsonImpl;

import static com.netikras.studies.studentbuddy.api.constants.AdminUserConstants.endpointCreateUser;
import static com.netikras.studies.studentbuddy.api.constants.AdminUserConstants.endpointDeleteUserById;

@SuppressWarnings({"unchecked", "UnnecessaryLocalVariable"})
public class AdminUserConsumer extends GenericRestConsumer {

    public UserDto createUser(UserDto userDto) {
        HttpRequest<UserDto> request = createRequest(endpointCreateUser())
                .withExpectedType(UserDto.class)
                .setObject(userDto);

        UserDto user = (UserDto) sendRequest(request);
        return user;
    }

    public boolean deleteUser(String id) {
        HttpRequest<UserDto> request = createRequest(endpointDeleteUserById())
                .withExpectedType(UserDto.class)
                .setUrlProperty("id", id);

        HttpResponseJsonImpl responseJson = new HttpResponseJsonImpl(UserDto.class);
        sendRequest(request, responseJson);
        return responseJson.isResponseSuccess();
    }

}
