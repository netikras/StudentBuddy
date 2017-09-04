package com.netikras.studies.studentbuddy.api.user.generated;

import com.netikras.studies.studentbuddy.core.data.api.dto.meta.ResourceActionDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import com.netikras.studies.studentbuddy.core.data.api.dto.meta.UserDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import com.netikras.studies.studentbuddy.api.user.generated.UserApiConstants;
import java.lang.String;
import com.netikras.tools.common.remote.AuthenticationDetail;


/**
 * This class is generated automatically with Rest API preprocessor (netikras' commons).<br/>
 * It is not recommended to modify this file as it will be overwritten on next compile.<br/>
 * In case one needs to modify this class either modify its template or override its methods.
 */

@RequestMapping(value = UserApiConstants.BASE_URL)
public class UserApiProducer {

    @RequestMapping(value = UserApiConstants.URL_USER_DTO_RETRIEVE, method = RequestMethod.GET)
    @ResponseBody
    public UserDto retrieveUserDto(
                        @PathVariable(value = "id") String id) {
        UserDto result = onRetrieveUserDto(id);
        return result;
    }

    protected UserDto onRetrieveUserDto(String id) {
        return null;
    }

    @RequestMapping(value = UserApiConstants.URL_USER_DTO_UPDATE, method = RequestMethod.PUT)
    @ResponseBody
    public UserDto updateUserDto(
                        @RequestBody(required = true) UserDto item) {
        UserDto result = onUpdateUserDto(item);
        return result;
    }

    protected UserDto onUpdateUserDto(UserDto item) {
        return null;
    }

    @RequestMapping(value = UserApiConstants.URL_USER_DTO_DELETE, method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteUserDto(
                        @PathVariable(value = "id") String id) {
        onDeleteUserDto(id);
        return ;
    }

    protected void onDeleteUserDto(String id) {
        return ;
    }

    @RequestMapping(value = UserApiConstants.URL_USER_DTO_CREATE, method = RequestMethod.POST)
    @ResponseBody
    public UserDto createUserDto(
                        @RequestBody(required = true) UserDto item) {
        UserDto result = onCreateUserDto(item);
        return result;
    }

    protected UserDto onCreateUserDto(UserDto item) {
        return null;
    }

    @RequestMapping(value = UserApiConstants.URL_USER_DTO_LOGIN, method = RequestMethod.POST)
    @ResponseBody
    public UserDto loginUserDto(
                        @RequestParam(value = "username", required = true) String username, 
                        @RequestParam(value = "password", required = true) String password, 
                        @RequestBody(required = false) AuthenticationDetail auth) {
        UserDto result = onLoginUserDto(username, password, auth);
        return result;
    }

    protected UserDto onLoginUserDto(String username, String password, AuthenticationDetail auth) {
        return null;
    }

    @RequestMapping(value = UserApiConstants.URL_USER_DTO_LOGOUT, method = RequestMethod.POST)
    @ResponseBody
    public void logoutUserDto() {
        onLogoutUserDto();
        return ;
    }

    protected void onLogoutUserDto() {
        return ;
    }

    @RequestMapping(value = UserApiConstants.URL_RESOURCE_ACTION_DTO_GET_PERMITTED_ACTIONS, method = RequestMethod.GET)
    @ResponseBody
    public List getPermittedActionsResourceActionDto(
                        @RequestParam(value = "id", required = true) String id) {
        List result = onGetPermittedActionsResourceActionDto(id);
        return result;
    }

    protected List onGetPermittedActionsResourceActionDto(String id) {
        return null;
    }

    @RequestMapping(value = UserApiConstants.URL_USER_DTO_CHANGE_PASSWORD, method = RequestMethod.PUT)
    @ResponseBody
    public void changePasswordUserDto(
                        @PathVariable(value = "id") String id, 
                        @RequestParam(value = "password", required = true) String password) {
        onChangePasswordUserDto(id, password);
        return ;
    }

    protected void onChangePasswordUserDto(String id, String password) {
        return ;
    }

    @RequestMapping(value = UserApiConstants.URL_USER_DTO_GET_BY_NAME, method = RequestMethod.GET)
    @ResponseBody
    public UserDto getByNameUserDto(
                        @PathVariable(value = "name") String name) {
        UserDto result = onGetByNameUserDto(name);
        return result;
    }

    protected UserDto onGetByNameUserDto(String name) {
        return null;
    }

    @RequestMapping(value = UserApiConstants.URL_USER_DTO_GET_BY_PERSON, method = RequestMethod.GET)
    @ResponseBody
    public UserDto getByPersonUserDto(
                        @PathVariable(value = "id") String id) {
        UserDto result = onGetByPersonUserDto(id);
        return result;
    }

    protected UserDto onGetByPersonUserDto(String id) {
        return null;
    }

    @RequestMapping(value = UserApiConstants.URL_USER_DTO_SEARCH_ALL_BY_USERNAME, method = RequestMethod.GET)
    @ResponseBody
    public List<UserDto> searchAllByUsernameUserDto(
                        @PathVariable(value = "username") String username) {
        List result = onSearchAllByUsernameUserDto(username);
        return result;
    }

    protected List<UserDto> onSearchAllByUsernameUserDto(String username) {
        return null;
    }

    @RequestMapping(value = UserApiConstants.URL_USER_DTO_SEARCH_ALL_BY_FIRST_NAME, method = RequestMethod.GET)
    @ResponseBody
    public List<UserDto> searchAllByFirstNameUserDto(
                        @PathVariable(value = "fname") String fname) {
        List result = onSearchAllByFirstNameUserDto(fname);
        return result;
    }

    protected List<UserDto> onSearchAllByFirstNameUserDto(String fname) {
        return null;
    }

    @RequestMapping(value = UserApiConstants.URL_USER_DTO_SEARCH_ALL_BY_LAST_NAME, method = RequestMethod.GET)
    @ResponseBody
    public List<UserDto> searchAllByLastNameUserDto(
                        @PathVariable(value = "lname") String lname) {
        List result = onSearchAllByLastNameUserDto(lname);
        return result;
    }

    protected List<UserDto> onSearchAllByLastNameUserDto(String lname) {
        return null;
    }


}
