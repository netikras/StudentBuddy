package com.netikras.studies.studentbuddy.api.user.generated;

import com.netikras.studies.studentbuddy.core.data.api.dto.meta.ResourceActionDto;
import java.util.List;
import com.netikras.studies.studentbuddy.api.user.generated.UserApiConstants;
import com.netikras.studies.studentbuddy.core.data.api.dto.meta.UserDto;
import com.netikras.tools.common.remote.http.GenericRestConsumer;
import java.lang.String;
import com.fasterxml.jackson.core.type.TypeReference;
import com.netikras.tools.common.remote.AuthenticationDetail;
import com.netikras.tools.common.remote.http.HttpRequest;
import com.netikras.tools.common.remote.http.impl.json.HttpResponseJsonImpl;


/**
 * This class is generated automatically with Rest API preprocessor (netikras' commons).<br/>
 * It is not recommended to modify this file as it will be overwritten on next compile.<br/>
 * In case one needs to modify this class either modify its template or override its methods.
 */

public class UserApiConsumer extends GenericRestConsumer {

    public UserDto retrieveUserDto(
                        String id) {
        HttpRequest request = createRequest(UserApiConstants.endpointRetrieveUserDto())
            .withExpectedType(UserDto.class)
            
            .setUrlProperty("id", id);

        UserDto result = (UserDto) sendRequest(request);
        return result;
    }

    public UserDto updateUserDto(
                        UserDto item) {
        HttpRequest<UserDto> request = createRequest(UserApiConstants.endpointUpdateUserDto())
            .withExpectedType(UserDto.class)
            
            .setObject(item);

        UserDto result = (UserDto) sendRequest(request);
        return result;
    }

    public void deleteUserDto(
                        String id) {
        HttpRequest request = createRequest(UserApiConstants.endpointDeleteUserDto())
            
            
            .setUrlProperty("id", id);

        sendRequest(request);
        return ;
    }

    public UserDto createUserDto(
                        UserDto item) {
        HttpRequest<UserDto> request = createRequest(UserApiConstants.endpointCreateUserDto())
            .withExpectedType(UserDto.class)
            
            .setObject(item);

        UserDto result = (UserDto) sendRequest(request);
        return result;
    }

    public UserDto loginUserDto(
                        String username, 
                        String password, 
                        AuthenticationDetail auth) {
        HttpRequest<AuthenticationDetail> request = createRequest(UserApiConstants.endpointLoginUserDto())
            .withExpectedType(UserDto.class)
            
            .addParam("username", username)
            .addParam("password", password)
            .setObject(auth);

        UserDto result = (UserDto) sendRequest(request);
        return result;
    }

    public void logoutUserDto() {
        HttpRequest request = createRequest(UserApiConstants.endpointLogoutUserDto())
            
            ;

        sendRequest(request);
        return ;
    }

    public List getPermittedActionsResourceActionDto(
                        String id) {
        HttpRequest request = createRequest(UserApiConstants.endpointGetPermittedActionsResourceActionDto())
            .withExpectedType(List.class)
            
            .addParam("id", id);

        List result = (List) sendRequest(request);
        return result;
    }

    public void changePasswordUserDto(
                        String id, 
                        String password) {
        HttpRequest request = createRequest(UserApiConstants.endpointChangePasswordUserDto())
            
            
            .setUrlProperty("id", id)
            .addParam("password", password);

        sendRequest(request);
        return ;
    }

    public UserDto getByNameUserDto(
                        String name) {
        HttpRequest request = createRequest(UserApiConstants.endpointGetByNameUserDto())
            .withExpectedType(UserDto.class)
            
            .setUrlProperty("name", name);

        UserDto result = (UserDto) sendRequest(request);
        return result;
    }

    public UserDto getByPersonUserDto(
                        String id) {
        HttpRequest request = createRequest(UserApiConstants.endpointGetByPersonUserDto())
            .withExpectedType(UserDto.class)
            
            .setUrlProperty("id", id);

        UserDto result = (UserDto) sendRequest(request);
        return result;
    }

    public List<UserDto> searchAllByUsernameUserDto(
                        String username) {
        HttpRequest request = createRequest(UserApiConstants.endpointSearchAllByUsernameUserDto())
            .withTypeReference(new TypeReference<List<UserDto>>() {})
            
            .setUrlProperty("username", username);

        List<UserDto> result = (List<UserDto>) sendRequest(request);
        return result;
    }

    public List<UserDto> searchAllByFirstNameUserDto(
                        String fname) {
        HttpRequest request = createRequest(UserApiConstants.endpointSearchAllByFirstNameUserDto())
            .withTypeReference(new TypeReference<List<UserDto>>() {})
            
            .setUrlProperty("fname", fname);

        List<UserDto> result = (List<UserDto>) sendRequest(request);
        return result;
    }

    public List<UserDto> searchAllByLastNameUserDto(
                        String lname) {
        HttpRequest request = createRequest(UserApiConstants.endpointSearchAllByLastNameUserDto())
            .withTypeReference(new TypeReference<List<UserDto>>() {})
            
            .setUrlProperty("lname", lname);

        List<UserDto> result = (List<UserDto>) sendRequest(request);
        return result;
    }


}
