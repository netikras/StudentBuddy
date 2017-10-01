package com.netikras.studies.studentbuddy.core.service;

import com.netikras.studies.studentbuddy.core.data.sys.model.ResourceActionLink;
import com.netikras.studies.studentbuddy.core.data.sys.model.User;
import com.netikras.tools.common.remote.AuthenticationDetail;

import java.util.List;

public interface UserService {

    User login(AuthenticationDetail auth);

    User createUser(User user);

    User updateUser(User user);

    void deleteUser(String id);

    void purgeUser(String id);

    User findUser(String id);

    //    @Transactional
    List<ResourceActionLink> getPermissions(String userId);

    User findUserByName(String name);

    User findUserByPerson(String personId);

    List<User> findVirtualUsers();

    void changePassword(String userId, String password);

    List<User> searchAllUsersByLastName(String query);

    List<User> searchAllUsersByFirstName(String query);

    List<User> searchAllUsersByUsername(String query);
}
