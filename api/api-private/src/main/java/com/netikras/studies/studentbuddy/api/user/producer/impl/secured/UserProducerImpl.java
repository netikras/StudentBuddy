package com.netikras.studies.studentbuddy.api.user.producer.impl.secured;

import com.netikras.studies.studentbuddy.api.filters.HttpThreadContext;
import com.netikras.studies.studentbuddy.api.handlers.DtoMapper;
import com.netikras.studies.studentbuddy.commons.exception.StudBudUncheckedException;
import com.netikras.studies.studentbuddy.core.data.api.dto.meta.RolePermissionDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.meta.UserDto;
import com.netikras.studies.studentbuddy.core.data.meta.Action;
import com.netikras.studies.studentbuddy.core.data.meta.annotations.Authorizable;
import com.netikras.studies.studentbuddy.core.data.sys.model.ResourceActionLink;
import com.netikras.studies.studentbuddy.core.data.sys.model.User;
import com.netikras.studies.studentbuddy.core.service.SystemService;
import com.netikras.studies.studentbuddy.core.service.UserService;
import com.netikras.studies.studentbuddy.core.validator.EntityProvider;
import com.netikras.tools.common.model.mapper.MappingSettings;
import com.netikras.tools.common.model.mapper.ModelMapper;
import com.netikras.tools.common.remote.AuthenticationDetail;
import com.netikras.tools.common.remote.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

import static com.netikras.studies.studentbuddy.core.data.meta.Action.MODERATE;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.ROLE_PERMISSIONS;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.USER;
import static com.netikras.tools.common.security.IntegrityUtils.isNullOrEmpty;

@Component
public class UserProducerImpl {

    @Resource
    private ModelMapper modelMapper;

    @Resource
    private UserService userService;

    @Resource
    private SystemService systemService;

    @Resource
    private DtoMapper dtoMapper;
    @Resource
    private EntityProvider entityProvider;

    @Authorizable(resource = USER, action = Action.MODIFY)
    @Transactional
    public UserDto updateUser(UserDto item) {
        User user = modelMapper.apply(entityProvider.fetch(item), item);
        user.setId(item.getId());
        user = userService.updateUser(user);

        item = (UserDto) dtoMapper.toDto(user);

        return item;
    }

    @Authorizable(resource = USER, action = Action.DELETE)
    public void deleteUser(String id) {
        userService.deleteUser(id);
    }


    @Authorizable(resource = USER, action = Action.CREATE)
    @Transactional
    public UserDto createUser(UserDto item) {
        User user = modelMapper.apply(new User(), item, new MappingSettings().setForceUpdate(true));
        user = userService.createUser(user);
        item = (UserDto) dtoMapper.toDto(user);
        return item;
    }


    @Authorizable(resource = USER, action = Action.GET)
    @Transactional
    public UserDto getUser(String id) {
        UserDto dto;
        User user = userService.findUser(id);
        dto = (UserDto) dtoMapper.toDto(user);
        return dto;
    }

    @Transactional
    public UserDto getCurrentUser() {
        return (UserDto) dtoMapper.toDto(HttpThreadContext.current().getUser());
    }


    @Transactional
    public UserDto loginUser(String username, String password, AuthenticationDetail auth) {
        if (auth == null || auth.getUsername() == null || auth.getUsername().isEmpty()) {
            auth = new AuthenticationDetail();
            auth.setUsername(username);
            auth.setPassword(password);
        }

        User user = userService.login(auth);
        UserDto dto = (UserDto) dtoMapper.toDto(user);

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


    public void logoutUser() {
        try {
            HttpThreadContext.current().removeUser();
            HttpThreadContext.current().getSession().invalidate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Authorizable(resource = ROLE_PERMISSIONS, action = Action.GET)
    @Transactional
    public List<RolePermissionDto> getRolePermittedActions(String userId) {
        User user = HttpThreadContext.current().getUser();

        if (isNullOrEmpty(userId)) {
            if (user != null) {
                userId = user.getId();
            }
        } else {
            if (!user.getId().equals(userId)) {
                if (!systemService.isUserAllowedToPerformAction(user, ROLE_PERMISSIONS.name(), null, MODERATE.name())) {
                    throw new StudBudUncheckedException()
                            .setMessage1("Cannot get user's permissions")
                            .setMessage2("Current user is not allowed to query other users' permissions")
                            .setProbableCause(userId);
                }
            }
        }

        List<ResourceActionLink> rolePermissions = userService.getPermissions(userId);

        List<RolePermissionDto> permissions = (List<RolePermissionDto>) dtoMapper.toDtos(rolePermissions);


        return permissions;
    }


    @Authorizable(resource = USER, action = Action.MODIFY)
    public void changeUserPassword(String userId, String password) {
        userService.changePassword(userId, password);
    }


    @Authorizable(resource = USER, action = Action.GET)
    @Transactional
    public UserDto getUserByName(String name) {
        UserDto dto;
        User user = userService.findUserByName(name);
        dto = (UserDto) dtoMapper.toDto(user);
        return dto;
    }


    @Authorizable(resource = USER, action = Action.GET)
    @Transactional
    public UserDto getUserByPerson(String userId) {
        UserDto dto;
        User user = userService.findUserByPerson(userId);
        dto = (UserDto) dtoMapper.toDto(user);
        return dto;
    }


    // search


    @Authorizable(resource = USER, action = Action.SEARCH)
    @Transactional
    public List<UserDto> searchUsersByUsername(String query) {
        List<UserDto> dtos;
        List<User> users = userService.searchAllUsersByUsername(query);
        dtos = (List<UserDto>) dtoMapper.toDtos(users);
        return dtos;
    }


    @Authorizable(resource = USER, action = Action.SEARCH)
    @Transactional
    public List<UserDto> searchUsersByFirstName(String query) {
        List<UserDto> dtos;
        List<User> users = userService.searchAllUsersByFirstName(query);
        dtos = (List<UserDto>) dtoMapper.toDtos(users);
        return dtos;
    }


    @Authorizable(resource = USER, action = Action.SEARCH)
    @Transactional
    public List<UserDto> searchUsersByLastName(String query) {
        List<UserDto> dtos;
        List<User> users = userService.searchAllUsersByLastName(query);
        dtos = (List<UserDto>) dtoMapper.toDtos(users);
        return dtos;
    }

}
