package com.netikras.studies.studentbuddy.api.user;

import com.netikras.studies.studentbuddy.api.constants.UserConstants;
import com.netikras.studies.studentbuddy.api.filters.HttpThreadContext;
import com.netikras.studies.studentbuddy.commons.exception.StudBudUncheckedException;
import com.netikras.studies.studentbuddy.core.data.api.dto.meta.ResourceActionDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.meta.RolePermissionsDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.meta.UserDto;
import com.netikras.studies.studentbuddy.core.data.sys.SystemService;
import com.netikras.studies.studentbuddy.core.data.sys.model.ResourceActionLink;
import com.netikras.studies.studentbuddy.core.data.sys.model.RolePermissions;
import com.netikras.studies.studentbuddy.core.data.sys.model.User;
import com.netikras.studies.studentbuddy.core.meta.Action;
import com.netikras.studies.studentbuddy.core.meta.annotations.Authorizable;
import com.netikras.studies.studentbuddy.core.service.UserService;
import com.netikras.tools.common.model.mapper.MappingSettings;
import com.netikras.tools.common.model.mapper.ModelMapper;
import com.netikras.tools.common.remote.AuthenticationDetail;
import com.netikras.tools.common.remote.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static com.netikras.studies.studentbuddy.api.constants.UserConstants.USER_URL_PERMISSIONS;
import static com.netikras.studies.studentbuddy.core.meta.Action.MODERATE;
import static com.netikras.studies.studentbuddy.core.meta.Resource.ROLE_PERMISSIONS;
import static com.netikras.studies.studentbuddy.core.meta.Resource.USER;
import static com.netikras.tools.common.security.IntegrityUtils.isNullOrEmpty;

@RestController
@RequestMapping("/user")
public class UserController {


    @Resource
    private UserService userService;

    @Resource
    private SystemService systemService;


    @RequestMapping(
            value = UserConstants.USER_URL_LOGIN,
            method = RequestMethod.POST
    )
    @ResponseBody
    public UserDto login(
            @RequestParam(name = "username", required = false) String username,
            @RequestParam(name = "password", required = false) String password,
            @RequestBody(required = false) AuthenticationDetail auth
    ) {

        if (auth == null || auth.getUsername() == null || auth.getUsername().isEmpty()) {
            auth = new AuthenticationDetail();
            auth.setUsername(username);
            auth.setPassword(password);
        }

        User user = userService.login(auth);
        UserDto dto = ModelMapper.transform(user, new UserDto());

        if (user == null) {
            throw new StudBudUncheckedException()
                    .setMessage1("Login failure")
                    .setMessage2("Incorrect login information")
                    .setProbableCause(username)
                    .setStatusCode(HttpStatus.UNAUTHORIZED)
                    ;
        }

        HttpThreadContext.current().setNewUser(user);
        return dto;
    }

    @RequestMapping(
            value = UserConstants.USER_URL_LOGOUT,
            method = RequestMethod.POST
    )
    public void logout() {
        HttpThreadContext.current().removeUser();
    }

    @RequestMapping(
            value = USER_URL_PERMISSIONS,
            method = RequestMethod.GET
    )
    @ResponseBody
    @Authorizable(resource = ROLE_PERMISSIONS, action = Action.GET)
    @Transactional
    public List<ResourceActionDto> getPermittedActions(
            @RequestParam(name = "userId", required = false) String userId
    ) {

        User user = HttpThreadContext.current().getUser();

        if (isNullOrEmpty(userId)) {
            if (user != null) {
                userId = user.getId();
            }
        } else {
            if (!user.getId().equals(userId)) {
                if (!systemService.isUserAllowedToPerformAction(user, ROLE_PERMISSIONS.name(), MODERATE.name())) {
                    throw new StudBudUncheckedException()
                            .setMessage1("Cannot get user's permissions")
                            .setMessage2("Current user is not allowed to query other users' permissions")
                            .setProbableCause(userId);
                }
            }
        }

        List<RolePermissions> rolePermissions = userService.getPermissions(userId);

        List<RolePermissionsDto> permissions =
                (List<RolePermissionsDto>) ModelMapper.transformAll(rolePermissions, RolePermissionsDto.class, new MappingSettings().setDepthMax(3));

        List<ResourceActionDto> resourceActions = new ArrayList<>();
        if (permissions != null) {
            // Hide role-permission link from end user. Only reveal permissions.
            for (RolePermissionsDto permission : permissions) {
                List<ResourceActionDto> actionLinks = permission.getResourceActions();
                if (isNullOrEmpty(actionLinks)) continue;
                resourceActions.addAll(actionLinks);
            }

        }

        return resourceActions;
    }

    @RequestMapping(
            value = UserConstants.USER_URL_UPDATE_BY_ID,
            method = RequestMethod.PUT
    )
    @Authorizable(resource = USER, action = Action.MODIFY)
    public UserDto updateUser(
            @PathVariable(name = "id") String userId,
            @RequestBody UserDto userDto
    ) {
        userDto.setId(userId);
        User user = ModelMapper.apply(new User(), userDto);
        user.setId(userId);

        user = userService.updateUser(user);

        userDto = ModelMapper.transform(user, new UserDto());

        return userDto;
    }

    @RequestMapping(
            value = UserConstants.USER_URL_CHANGE_PASSWORD,
            method = RequestMethod.PUT
    )
    @Authorizable(resource = USER, action = Action.MODIFY)
    public void changePassword(
            @PathVariable(name = "id") String userId,
            @RequestParam(name = "password") String password
    ) {
        userService.changePassword(userId, password);
    }

    @RequestMapping(
            value = UserConstants.USER_URL_GET_BY_ID,
            method = RequestMethod.GET
    )
    @Authorizable(resource = USER, action = Action.GET)
    public UserDto getUser(
            @PathVariable(name = "id") String userId
    ) {
        UserDto dto;
        User user = userService.findUser(userId);
        dto = ModelMapper.transform(user, new UserDto());
        return dto;
    }

    @RequestMapping(
            value = UserConstants.USER_URL_GET_BY_NAME,
            method = RequestMethod.GET
    )
    @Authorizable(resource = USER, action = Action.GET)
    public UserDto getUserByName(
            @PathVariable(name = "name") String name
    ) {
        UserDto dto;
        User user = userService.findUserByName(name);
        dto = ModelMapper.transform(user, new UserDto());
        return dto;
    }


    @RequestMapping(
            value = UserConstants.USER_URL_GET_USER_BY_PERSON_ID,
            method = RequestMethod.GET
    )
    @Authorizable(resource = USER, action = Action.GET)
    public UserDto getUserByPerson(
            @PathVariable(name = "id") String userId
    ) {
        UserDto dto;
        User user = userService.findUserByPerson(userId);
        dto = ModelMapper.transform(user, new UserDto());
        return dto;
    }

    @RequestMapping(
            value = UserConstants.USER_URL_SEARCH_ALL_USERS_BY_USERNAME,
            method = RequestMethod.GET
    )
    @Authorizable(resource = USER, action = Action.SEARCH)
    public List<UserDto> searchAllUsersByUsername(
            @PathVariable(name = "username") String query
    ) {
        List<UserDto> dtos;
        List<User> users = userService.searchAllUsersByUsername(query);
        dtos = (List<UserDto>) ModelMapper.transformAll(users, UserDto.class, new MappingSettings().setDepthMax(2));
        return dtos;
    }

    @RequestMapping(
            value = UserConstants.USER_URL_SEARCH_ALL_USERS_BY_FIRST_NAME,
            method = RequestMethod.GET
    )
    @Authorizable(resource = USER, action = Action.SEARCH)
    public List<UserDto> searchAllUsersByFirstName(
            @PathVariable(name = "fname") String query
    ) {
        List<UserDto> dtos;
        List<User> users = userService.searchAllUsersByFirstName(query);
        dtos = (List<UserDto>) ModelMapper.transformAll(users, UserDto.class, new MappingSettings().setDepthMax(2));
        return dtos;
    }

    @RequestMapping(
            value = UserConstants.USER_URL_SEARCH_ALL_USERS_BY_LAST_NAME,
            method = RequestMethod.GET
    )
    @Authorizable(resource = USER, action = Action.SEARCH)
    public List<UserDto> searchAllUsersByLastName(
            @PathVariable(name = "lname") String query
    ) {
        List<UserDto> dtos;
        List<User> users = userService.searchAllUsersByLastName(query);
        dtos = (List<UserDto>) ModelMapper.transformAll(users, UserDto.class, new MappingSettings().setDepthMax(2));
        return dtos;
    }

}
