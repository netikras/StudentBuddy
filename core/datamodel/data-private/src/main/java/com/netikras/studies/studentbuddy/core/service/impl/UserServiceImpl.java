package com.netikras.studies.studentbuddy.core.service.impl;

import com.netikras.studies.studentbuddy.commons.exception.StudBudUncheckedException;
import com.netikras.studies.studentbuddy.core.data.sys.SysProp;
import com.netikras.studies.studentbuddy.core.data.sys.SystemService;
import com.netikras.studies.studentbuddy.core.data.sys.dao.UserDao;
import com.netikras.studies.studentbuddy.core.data.sys.model.User;
import com.netikras.studies.studentbuddy.core.meta.PasswordValidationResult;
import com.netikras.studies.studentbuddy.core.meta.PasswordValidator;
import com.netikras.studies.studentbuddy.core.service.UserService;
import com.netikras.tools.common.remote.AuthenticationDetail;
import com.netikras.tools.common.remote.http.HttpStatus;
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

        if (!systemService.getSettingValue(SysProp.SESSION_ENABLE)) {
            if (!"system".equals(username)) {
                throw new StudBudUncheckedException()
                        .setMessage1("Cannot log in")
                        .setMessage2("New logins have been disabled by administrator")
                        .setStatusCode(HttpStatus.SERVICE_UNAVAILABLE)
                        ;
            }
        }

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
        if (user == null) return null;
        if (user.getPasswordHash() != null) {
            user.setPasswordHash(user.getPasswordHash().toLowerCase());
        }
        return userDao.save(user);
    }

    @Override
    @Transactional
    public User updateUser(User user) {
        User oldUser = userDao.findOne(user.getId());
        if (oldUser == null) {
            throw new StudBudUncheckedException()
                    .setMessage1("Unable to update user")
                    .setMessage2("User with given ID does not exist")
                    .setProbableCause(user.getName())
                    .setStatusCode(HttpStatus.NOT_FOUND);
        }
        oldUser.setName(user.getName());
        return userDao.save(oldUser);
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
        User user = userDao.findOne(userId);
        user.setPasswordHash(hashPassword(password));
        userDao.save(user);
    }

    public PasswordValidationResult validatePassword(String password) {
        PasswordValidator validator = new PasswordValidator();
        validator.setRequirements(systemService.getPasswordRequirements());

        PasswordValidationResult result = validator.validate(password);

        return result;
    }

    public String hashPassword(String password) {
        byte[] raw = IntegrityUtils.sha256sum(password.getBytes());
        password = IntegrityUtils.bytesToHexString(raw).toLowerCase();
        System.out.println("pwhash=" + password);
        return password;
    }


}
