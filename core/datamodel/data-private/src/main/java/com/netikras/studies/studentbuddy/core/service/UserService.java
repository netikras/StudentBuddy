package com.netikras.studies.studentbuddy.core.service;

import com.netikras.studies.studentbuddy.core.data.sys.model.User;
import com.netikras.tools.common.remote.AuthenticationDetail;

import java.util.List;

public interface UserService {

    User login(AuthenticationDetail auth);

    User createUser(User user);

    User updateUser(User user);

    void deleteUser(String id);

    User findUser(String id);

    User findUserByName(String name);

    User findUserByPerson(String personId);

    List<User> findVirtualUsers();

    void changePassword(String userId, String password);
}
