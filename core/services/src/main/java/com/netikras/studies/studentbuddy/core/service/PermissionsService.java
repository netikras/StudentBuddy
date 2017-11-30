package com.netikras.studies.studentbuddy.core.service;

import com.netikras.studies.studentbuddy.core.data.meta.Action;
import com.netikras.studies.studentbuddy.core.data.sys.model.Role;
import com.netikras.studies.studentbuddy.core.data.sys.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PermissionsService {


    void assignUserRoles(String userId, List<String> roleNames);

    void removeUserRole(String userId, String roleName);

    List<User> getUsersByRole(String roleName);

    Role addRolePermission(String roleName, String resourceName, String actionName, String resourceId, Boolean strict);

    Role addRolePermission(String roleName, com.netikras.studies.studentbuddy.core.data.meta.Resource resource,
                           Action action, String resourceId, Boolean strict);

    Role addRolePermission(Role role, com.netikras.studies.studentbuddy.core.data.meta.Resource resource, Action action, String resourceId, Boolean strict);

    Role removeRolePermission(String roleName, String resourceName, String actionName, String resourceId, Boolean strict);

    Role removeRolePermission(Role role, String resourceName, String actionName, String resourceId, Boolean strict);

    Role removeRolePermission(String rolename, String permissionId);

    List<Role> getAllRoles();

    void deleteRole(String name);

    Role createRole(String roleName);

    void deleteRoleById(String id);

    Role getRoleById(String id);

    Role getRoleByName(String name);
}
