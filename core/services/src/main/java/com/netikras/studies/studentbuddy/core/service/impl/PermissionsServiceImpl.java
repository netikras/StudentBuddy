package com.netikras.studies.studentbuddy.core.service.impl;

import com.netikras.studies.studentbuddy.core.data.api.dao.RoleDao;
import com.netikras.studies.studentbuddy.core.data.api.model.Person;
import com.netikras.studies.studentbuddy.core.data.meta.Action;
import com.netikras.studies.studentbuddy.core.data.sys.dao.UserDao;
import com.netikras.studies.studentbuddy.core.data.sys.model.Role;
import com.netikras.studies.studentbuddy.core.data.sys.model.User;
import com.netikras.studies.studentbuddy.core.data.sys.model.UserRole;
import com.netikras.studies.studentbuddy.core.service.PermissionsService;
import com.netikras.studies.studentbuddy.core.service.UserService;
import com.netikras.studies.studentbuddy.core.validator.EntityProvider;
import com.netikras.tools.common.security.ThreadContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

import static com.netikras.tools.common.security.IntegrityUtils.isNullOrEmpty;

@Service
public class PermissionsServiceImpl implements PermissionsService {

    @Resource
    private UserService userService;

    @Resource
    private UserDao userDao;
    @Resource
    private RoleDao roleDao;

    @Resource
    private EntityProvider entityProvider;

    public void deleteRolePermission(String id) {
//        ResourceActionLink resourceActionLink =
    }

    @Override
    @Transactional
    public void assignUserRoles(String userId, List<String> roleNames) {
        User user = userService.findUser(userId);

        if (user == null) {
            throw new IllegalStateException("User not found");
        }

        Role temp = new Role();
        boolean pristine = true;

        for (String roleName : roleNames) {
            temp.setName(roleName);
            Role existing = entityProvider.fetch(temp);
            if (existing == null) {
                existing = temp;
                temp = new Role();
            }

            if (!user.hasRole(roleName)) {
                UserRole userRole = new UserRole();
                userRole.setUser(user);
                userRole.setRole(existing);
                userRole.setGrantedBy((User) ThreadContext.current().getValue("user"));
                user.addRole(userRole);
                pristine = false;
            }
        }

        if (!pristine) {
            userDao.save(user);
        }
    }

    @Override
    @Transactional
    public void removeUserRole(String userId, String roleName) {
        User user = userService.findUser(userId);

        if (user == null) {
            throw new IllegalStateException("User not found");
        }

        user.removeRole(roleName);

        userDao.save(user);
    }

    @Override
    @Transactional
    public List<User> getUsersByRole(String roleName) {
        return userDao.findAllByRoles_Role_NameContaining(roleName);
    }

    @Override
    public Role addRolePermission(String roleName, String resourceName, String actionName, String resourceId, Boolean strict) {
        return addRolePermission(roleName,
                com.netikras.studies.studentbuddy.core.data.meta.Resource.valueOf(resourceName.toUpperCase()),
                Action.valueOf(actionName.toUpperCase()), resourceId, strict);
    }

    @Override
    @Transactional
    public Role addRolePermission(String roleName, com.netikras.studies.studentbuddy.core.data.meta.Resource resource,
                                  Action action, String resourceId, Boolean strict) {
        Role role = roleDao.findByName(roleName);
        return addRolePermission(role, resource, action, resourceId, strict);

    }

    @Override
    @Transactional
    public Role addRolePermission(Role role, com.netikras.studies.studentbuddy.core.data.meta.Resource resource,
                                  Action action, String resourceId, Boolean strict) {
        if (role == null) {
            return null;
        }
        role.addPermission(resource, action, resourceId, strict);
        return roleDao.save(role);
    }

    @Override
    @Transactional
    public Role removeRolePermission(String roleName, String resourceName, String actionName, String resourceId) {
        Role role = roleDao.findByName(roleName);
        return removeRolePermission(role, resourceName, actionName, resourceId);
    }

    @Override
    @Transactional
    public Role removeRolePermission(Role role, String resourceName, String actionName, String resourceId) {
        role.removePermission(resourceName, actionName, resourceId);
        return roleDao.save(role);
    }


    @Override
    @Transactional
    public List<Role> getAllRoles() {
        return roleDao.findAll();
    }

    @Override
    @Transactional
    public void deleteRole(String name) {
        Role role = roleDao.findByName(name);
        if (role == null) {
            throw new IllegalStateException("Role not found");
        }

        List<User> users = userDao.findAllByRoles_Role_NameContaining(name);
        if (!isNullOrEmpty(users)) {
            users.forEach(user -> {
                user.removeRole(name);
                userDao.save(user);
            });
        }

        roleDao.delete(role);
    }

    @Override
    @Transactional
    public Role createRole(String roleName) {
        Role role = roleDao.findByName(roleName);
        if (role != null) {
            throw new IllegalStateException("Role already exists");
        }

        role = new Role();
        role.setName(roleName);
        role.setCreatedBy((Person) ThreadContext.current().getValue("user"));

        return roleDao.save(role);
    }

    @Override
    @Transactional
    public void deleteRoleById(String id) {
        roleDao.delete(id);
    }

    @Override
    @Transactional
    public Role getRoleById(String id) {
        return roleDao.findOne(id);
    }

    @Override
    @Transactional
    public Role getRoleByName(String name) {
        return roleDao.findByName(name);
    }
}
