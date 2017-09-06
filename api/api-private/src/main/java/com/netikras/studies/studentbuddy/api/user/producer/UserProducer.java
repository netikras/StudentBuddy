package com.netikras.studies.studentbuddy.api.user.producer;

import com.netikras.studies.studentbuddy.api.filters.HttpThreadContext;
import com.netikras.studies.studentbuddy.api.user.generated.UserApiProducer;
import com.netikras.studies.studentbuddy.commons.exception.StudBudUncheckedException;
import com.netikras.studies.studentbuddy.core.data.api.dto.meta.ResourceActionDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.meta.RolePermissionsDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.meta.UserDto;
import com.netikras.studies.studentbuddy.core.data.sys.SystemService;
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
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static com.netikras.studies.studentbuddy.core.meta.Action.MODERATE;
import static com.netikras.studies.studentbuddy.core.meta.Resource.ROLE_PERMISSIONS;
import static com.netikras.studies.studentbuddy.core.meta.Resource.USER;
import static com.netikras.tools.common.security.IntegrityUtils.isNullOrEmpty;

@RestController
public class UserProducer extends UserApiProducer {

    @Resource
    private UserService userService;

    @Resource
    private SystemService systemService;


    @Override
    @Transactional
    @Authorizable(resource = USER, action = Action.MODIFY)
    protected UserDto onUpdateUserDto(UserDto item) {
        User user = ModelMapper.apply(new User(), item);
        user.setId(item.getId());
        user = userService.updateUser(user);

        item = ModelMapper.transform(user, new UserDto());

        return item;
    }

    @Override
    protected void onDeleteUserDto(String id) {
        userService.deleteUser(id);
    }

    @Override
    protected UserDto onCreateUserDto(UserDto item) {
        User user = ModelMapper.apply(new User(), item, new MappingSettings().setForceUpdate(true));
        user = userService.createUser(user);
        item = ModelMapper.transform(user, new UserDto());
        return item;
    }

    @Override
    @Authorizable(resource = USER, action = Action.GET)
    protected UserDto onRetrieveUserDto(String id) {
        UserDto dto;
        User user = userService.findUser(id);
        dto = ModelMapper.transform(user, new UserDto());
        return dto;
    }


    @Override
    protected UserDto onLoginUserDto(String username, String password, AuthenticationDetail auth) {
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

    @Override
    protected void onLogoutUserDto() {
        HttpThreadContext.current().removeUser();
    }

    @Override
    @Authorizable(resource = ROLE_PERMISSIONS, action = Action.GET)
    @Transactional
    protected List<ResourceActionDto> onGetResourceActionDtoPermittedActions(String userId) {
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

    @Override
    @Authorizable(resource = USER, action = Action.MODIFY)
    protected void onChangeUserDtoPassword(String userId, String password) {
        userService.changePassword(userId, password);
    }

    @Override
    @Authorizable(resource = USER, action = Action.GET)
    protected UserDto onGetUserDtoByName(String name) {
        UserDto dto;
        User user = userService.findUserByName(name);
        dto = ModelMapper.transform(user, new UserDto());
        return dto;
    }

    @Override
    @Authorizable(resource = USER, action = Action.GET)
    protected UserDto onGetUserDtoByPerson(String userId) {
        UserDto dto;
        User user = userService.findUserByPerson(userId);
        dto = ModelMapper.transform(user, new UserDto());
        return dto;
    }


    // search

    @Override
    @Authorizable(resource = USER, action = Action.SEARCH)
    protected List<UserDto> onSearchUserDtoAllByUsername(String query) {
        List<UserDto> dtos;
        List<User> users = userService.searchAllUsersByUsername(query);
        dtos = (List<UserDto>) ModelMapper.transformAll(users, UserDto.class, new MappingSettings().setDepthMax(2));
        return dtos;
    }

    @Override
    @Authorizable(resource = USER, action = Action.SEARCH)
    protected List<UserDto> onSearchUserDtoAllByFirstName(String query) {
        List<UserDto> dtos;
        List<User> users = userService.searchAllUsersByFirstName(query);
        dtos = (List<UserDto>) ModelMapper.transformAll(users, UserDto.class, new MappingSettings().setDepthMax(2));
        return dtos;
    }


    @Override
    @Authorizable(resource = USER, action = Action.SEARCH)
    protected List<UserDto> onSearchUserDtoAllByLastName(String query) {
        List<UserDto> dtos;
        List<User> users = userService.searchAllUsersByLastName(query);
        dtos = (List<UserDto>) ModelMapper.transformAll(users, UserDto.class, new MappingSettings().setDepthMax(2));
        return dtos;
    }
}
