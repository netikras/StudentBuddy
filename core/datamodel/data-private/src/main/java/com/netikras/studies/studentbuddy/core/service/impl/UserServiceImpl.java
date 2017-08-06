package com.netikras.studies.studentbuddy.core.service.impl;

import com.netikras.studies.studentbuddy.core.data.sys.SystemService;
import com.netikras.studies.studentbuddy.core.data.sys.dao.UserDao;
import com.netikras.studies.studentbuddy.core.data.sys.model.User;
import com.netikras.studies.studentbuddy.core.data.sys.model.PasswordRequirement;
import com.netikras.studies.studentbuddy.core.meta.PasswordValidationResult;
import com.netikras.studies.studentbuddy.core.meta.PasswordValidator;
import com.netikras.studies.studentbuddy.core.service.UserService;
import com.netikras.tools.common.remote.AuthenticationDetail;
import com.netikras.tools.common.security.IntegrityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Resource
    private SystemService systemService;

    @Override
    public User login(AuthenticationDetail auth) {
        if (auth == null) return null;

        User user = null;

        String username = auth.getUsername();
        String password = auth.getPassword();

        if (username != null && !username.isEmpty()) {
            if (password != null && !password.isEmpty()) {
                password = hashPassword(password);
                user = userDao.findByNameAndPasswordHash(username, password);
            }
        } // else // other methods mot implemented
        return user;
    }

    @Override
    public User createUser(User user) {
        return userDao.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userDao.save(user);
    }

    @Override
    public void deleteUser(String id) {
        userDao.delete(id);
    }

    @Override
    public User findUser(String id) {
        return userDao.findOne(id);
    }

    @Override
    public User findUserByName(String name) {
        return userDao.findByName(name);
    }

    @Override
    public User findUserByPerson(String personId) {
        return userDao.findByPerson_Id(personId);
    }

    @Override
    public List<User> findVirtualUsers() {
        return userDao.findAllByPersonIsNull();
    }

    @Override
    @Transactional
    public void changePassword(String userId, String password) {
        User user = userDao.findByName(userId);
        user.setPasswordHash(hashPassword(password));
    }

    public PasswordValidationResult validatePassword(String password) {
        PasswordValidator validator = new PasswordValidator();
        validator.setRequirements(systemService.getPasswordRequirements());

        PasswordValidationResult result = validator.validate(password);

        return result;
    }

    public String hashPassword(String password) {
        byte[] raw = IntegrityUtils.sha256sum(password.getBytes());
        password = IntegrityUtils.bytesToHexString(raw);

        return password;
    }


}
