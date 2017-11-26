package com.netikras.studies.studentbuddy.core.service.impl;

import com.netikras.studies.studentbuddy.commons.exception.StudBudUncheckedException;
import com.netikras.studies.studentbuddy.core.data.sys.SysProp;
import com.netikras.studies.studentbuddy.core.data.sys.dao.UserDao;
import com.netikras.studies.studentbuddy.core.data.sys.model.ResourceActionLink;
import com.netikras.studies.studentbuddy.core.data.sys.model.User;
import com.netikras.studies.studentbuddy.core.data.sys.model.UserRole;
import com.netikras.studies.studentbuddy.core.service.SystemService;
import com.netikras.studies.studentbuddy.core.service.UserService;
import com.netikras.studies.studentbuddy.core.validator.SystemValidator;
import com.netikras.tools.common.exception.ErrorsCollection;
import com.netikras.tools.common.remote.AuthenticationDetail;
import com.netikras.tools.common.remote.http.HttpStatus;
import com.netikras.tools.common.security.IntegrityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static com.netikras.tools.common.remote.http.HttpStatus.BAD_REQUEST;
import static com.netikras.tools.common.remote.http.HttpStatus.NOT_FOUND;
import static com.netikras.tools.common.security.IntegrityUtils.isNullOrEmpty;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Resource
    private SystemService systemService;

    @Resource
    private SystemValidator systemValidator;

    @Override
    @Transactional
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
                if (user != null) {
                    user.getPerson();
                }
            }
        } // else // other methods mot implemented
        return user;
    }

    @Override
    @Transactional
    public User createUser(User user) {
        if (user == null) return null;
        ErrorsCollection errors = systemValidator.validatePassword(user.getPassword(), null);
        user.setPasswordHash(hashPassword(user.getPassword()));
        if (user.getPasswordHash() != null) {
            user.setPasswordHash(user.getPasswordHash().toLowerCase());
        }
        errors = systemValidator.validateForCreation(user, errors);
        if (!errors.isEmpty()) {
            throw new StudBudUncheckedException()
                    .setMessage1("Cannot create new user")
                    .setMessage2("Validation errors: " + errors.size())
                    .setErrors(errors)
                    .setStatusCode(BAD_REQUEST)
                    ;
        }
        user = userDao.save(user);
        user.getPerson();
        return user;
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
        oldUser = userDao.save(oldUser);
        oldUser.getPerson();
        return oldUser;
    }

    @Override
    @Transactional
    public void deleteUser(String id) {
        User user = userDao.findOne(id);
        ErrorsCollection errors = systemValidator.validateForRemoval(user, null);
        if (!errors.isEmpty()) {
            throw new StudBudUncheckedException()
                    .setMessage1("Cannot delete user")
                    .setMessage2("Validation errors: " + errors.size())
                    .setErrors(errors)
                    .setStatusCode(BAD_REQUEST)
                    ;
        }
        userDao.delete(id);
    }


    @Override
    @Transactional
    public void purgeUser(String id) {
        User user = userDao.findOne(id);
        if (user == null) {
            return;
        }
        userDao.delete(user);
    }

    @Override
    @Transactional
    public User findUser(String id) {
        User user = userDao.findOne(id);
        if (user != null) {
            user.getPerson();
        }
        return user;
    }

    @Override
    @Transactional
    public List<ResourceActionLink> getPermissions(String userId) {
        List<ResourceActionLink> permissions = new ArrayList<>();
        if (isNullOrEmpty(userId)) {
            return permissions;
        }
        User user = userDao.findOne(userId);
        if (user == null) {
            return permissions;
        }


        if (isNullOrEmpty(user.getRoles())) {
            return permissions;
        }

        for (UserRole userRole : user.getRoles()) {
            if (userRole == null || userRole.getRole() == null || isNullOrEmpty(userRole.getRole().getName())) {
                continue;
            }
            List<ResourceActionLink> userRolePermissions = systemService.getPermissionsForRole(userRole.getRole().getName());
            if (!isNullOrEmpty(userRolePermissions)) {
                permissions.addAll(userRolePermissions);
            }
        }

        return permissions;
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
        ErrorsCollection errors = systemValidator.validatePassword(password, null);
        if (!errors.isEmpty()) {
            throw new StudBudUncheckedException()
                    .setMessage1("Unable to update password")
                    .setMessage2("Password does not mee requirements: " + errors.size())
                    .setStatusCode(HttpStatus.BAD_REQUEST);
        }

        User user = userDao.findOne(userId);
        user.setPasswordHash(hashPassword(password));
        userDao.save(user);
    }

    public String hashPassword(String password) {
        if (isNullOrEmpty(password)) {
            return null;
        }
        byte[] raw = IntegrityUtils.sha256sum(password.getBytes());
        password = IntegrityUtils.bytesToHexString(raw).toLowerCase();
        System.out.println("pwhash=" + password);
        return password;
    }


    @Override
    public List<User> searchAllUsersByLastName(String query) {
        return userDao.findAllByNameLikeIgnoreCase(userDao.wrapSearchString(query));
    }

    @Override
    public List<User> searchAllUsersByFirstName(String query) {
        return userDao.findAllByPerson_FirstNameLikeIgnoreCase(userDao.wrapSearchString(query));
    }

    @Override
    public List<User> searchAllUsersByUsername(String query) {
        return userDao.findAllByPerson_LastNameLikeIgnoreCase(userDao.wrapSearchString(query));
    }


}
