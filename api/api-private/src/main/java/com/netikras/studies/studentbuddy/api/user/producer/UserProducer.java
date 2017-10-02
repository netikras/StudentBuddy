package com.netikras.studies.studentbuddy.api.user.producer;

import com.netikras.studies.studentbuddy.api.user.generated.UserApiProducer;
import com.netikras.studies.studentbuddy.api.user.producer.impl.secured.UserProducerImpl;
import com.netikras.studies.studentbuddy.core.data.api.dto.meta.RolePermissionDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.meta.UserDto;
import com.netikras.tools.common.remote.AuthenticationDetail;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class UserProducer extends UserApiProducer {

    @Resource
    private UserProducerImpl impl;


    @Override
    protected UserDto onUpdateUserDto(UserDto item) {
        return impl.updateUser(item);
    }

    @Override
    protected void onDeleteUserDto(String id) {
        impl.deleteUser(id);
    }

    @Override
    protected UserDto onCreateUserDto(UserDto item) {
        return impl.createUser(item);
    }

    @Override
    public UserDto onRetrieveUserDto(String id) {
        return impl.getUser(id);
    }


    @Override
    public UserDto onLoginUserDto(String username, String password, AuthenticationDetail auth) {
        return impl.loginUser(username, password, auth);
    }

    @Override
    protected void onLogoutUserDto() {
        impl.logoutUser();
    }


    @Override
    protected List<RolePermissionDto> onGetRolePermissionDtoPermittedActions(String userId) {
        return impl.getRolePermittedActions(userId);
    }

    @Override
    protected void onChangeUserDtoPassword(String userId, String password) {
        impl.changeUserPassword(userId, password);
    }

    @Override
    protected UserDto onGetUserDtoByName(String name) {
        return impl.getUserByName(name);
    }

    @Override
    protected UserDto onGetUserDtoByPerson(String userId) {
        return impl.getUserByPerson(userId);
    }


    // search

    @Override
    protected List<UserDto> onSearchUserDtoAllByUsername(String query) {
        return impl.searchUsersByUsername(query);
    }

    @Override
    protected List<UserDto> onSearchUserDtoAllByFirstName(String query) {
        return impl.searchUsersByFirstName(query);
    }


    @Override
    protected List<UserDto> onSearchUserDtoAllByLastName(String query) {
        return impl.searchUsersByLastName(query);
    }
}
