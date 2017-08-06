package com.netikras.studies.studentbuddy.core.data.sys;

import com.netikras.studies.studentbuddy.core.data.api.dao.RolePermissionsDao;
import com.netikras.studies.studentbuddy.core.data.sys.dao.PasswordRequirementsDao;
import com.netikras.studies.studentbuddy.core.data.sys.dao.SettingsDao;
import com.netikras.studies.studentbuddy.core.data.sys.model.PasswordRequirement;
import com.netikras.studies.studentbuddy.core.data.sys.model.ResourceActionLink;
import com.netikras.studies.studentbuddy.core.data.sys.model.RolePermissions;
import com.netikras.studies.studentbuddy.core.data.sys.model.SystemSetting;
import com.netikras.studies.studentbuddy.core.data.sys.model.User;
import com.netikras.studies.studentbuddy.core.data.sys.model.UserRole;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SystemService {

    @Resource
    private SettingsDao settingsDao;

    @Resource
    private PasswordRequirementsDao passwordRequirementsDao;

    @Resource
    private RolePermissionsDao rolePermissionsDao;

    private List<PasswordRequirement> passwordRequirements;

    private List<SystemSetting> systemSettings;

    private Map<String, List<RolePermissions>> permissionsForRole;

    private Map<String, List<RolePermissions>> permissionsForResource;


    public List<PasswordRequirement> getPasswordRequirements() {
        return passwordRequirements;
    }

    public List<PasswordRequirement> fetchPasswordRequirements() {
        return passwordRequirementsDao.findAll();
    }


    public PasswordRequirement createPasswordRequirement(PasswordRequirement requirement) {
        return passwordRequirementsDao.save(requirement);
    }

    public List<PasswordRequirement> refreshPaswordRequirements() {
        passwordRequirements = fetchPasswordRequirements();
        return getPasswordRequirements();
    }

    public void deletePasswordRequirement(String id) {
        passwordRequirementsDao.delete(id);
    }

    public PasswordRequirement updatePasswordRequirement(PasswordRequirement requirement) {
        return passwordRequirementsDao.save(requirement);
    }


    public List<SystemSetting> getSystemSettings() {
        return systemSettings;
    }

    public List<SystemSetting> fetchSystemSettings() {
        return settingsDao.findAll();
    }

    public List<SystemSetting> refreshSettings() {
        systemSettings = fetchSystemSettings();
        return getSystemSettings();
    }

    public SystemSetting updateSystemSetting(SystemSetting setting) {
        return settingsDao.save(setting);
    }

    public void deleteSystemSetting(String name) {
        settingsDao.deleteByName(name);
    }

    public SystemSetting createSetting(SystemSetting setting) {
        return settingsDao.save(setting);
    }


    public SystemSetting getSetting(String name) {
        if (getSystemSettings() == null) return null;
        if (name == null || name.isEmpty()) return null;

        for (SystemSetting setting : getSystemSettings()) {
            if (name.equals(setting.getName())) {
                return setting;
            }
        }
        return null;
    }

    public String getSettingValue(String name) {
        SystemSetting setting = getSetting(name);
        if (setting == null) return null;
        return setting.getValue();
    }


    public RolePermissions createRolePermissions(RolePermissions permissions) {
        return rolePermissionsDao.save(permissions);
    }

    public RolePermissions addRolePermission(String roleName, ResourceActionLink resourceActionLink) {
        RolePermissions permissions = rolePermissionsDao.findByRole_Name(roleName);
        if (permissions == null) return null;

        if (permissions.getResourceActions() == null) {
            permissions.setResourceActions(new ArrayList<>());
        } else {
            for (ResourceActionLink link : permissions.getResourceActions()) {
                if (resourceActionLink.isLike(link)) {
                    return permissions;
                }
            }
        }
        permissions.getResourceActions().add(resourceActionLink);

        permissions = rolePermissionsDao.save(permissions);
        return permissions;
    }

    public void removeRolePermission(String roleName, ResourceActionLink resourceActionLink) {
        RolePermissions permissions = rolePermissionsDao.findByRole_Name(roleName);
        if (permissions == null) return;

        if (permissions.getResourceActions() == null) return;

        boolean removed = permissions.getResourceActions().removeIf(resourceActionLink::isLike);

        if (removed) {
            rolePermissionsDao.save(permissions);
        }
    }


    public List<RolePermissions> fetchRolePermissions() {
        return rolePermissionsDao.findAll();
    }


    public List<RolePermissions> refreshRolePermissions() {
        List<RolePermissions> freshPermissions = fetchRolePermissions();

        sortFreshPermissions(freshPermissions);

        return freshPermissions;
    }

    public List<RolePermissions> sortFreshPermissions(List<RolePermissions> freshPermissions) {

        List<RolePermissions> permissions;

        if (permissionsForRole == null) permissionsForRole = new HashMap<>();
        if (permissionsForResource == null) permissionsForResource = new HashMap<>();

        permissionsForResource.clear();
        permissionsForRole.clear();

        if (freshPermissions == null || freshPermissions.isEmpty()) return null;

        for (RolePermissions permission : freshPermissions) {
            if (permission.getResourceActions() == null || permission.getResourceActions().isEmpty()) continue;

            String role = permission.getRole().getName();

            for (ResourceActionLink resourceAction : permission.getResourceActions()) {
                String resource = resourceAction.getResource().name();
                permissions = permissionsForResource.computeIfAbsent(resource, k -> new ArrayList<>());
                permissions.add(permission);
            }
            permissions = permissionsForRole.computeIfAbsent(role, k -> new ArrayList<>());
            permissions.add(permission);
        }

        return freshPermissions;
    }

    public List<RolePermissions> getPermissionsForRole(String roleName) {
        return permissionsForRole == null ? new ArrayList<>() : permissionsForRole.get(roleName);
    }


    public List<RolePermissions> getPermissionsForResource(String resourceId, String action) {
        List<RolePermissions> permissions = new ArrayList<>();

        if (resourceId == null || resourceId.isEmpty()) return permissions;
        if (permissionsForResource == null || permissionsForResource.isEmpty()) return permissions;

        List<RolePermissions> storedPermissions = permissionsForResource.get(resourceId);
        if (storedPermissions == null || storedPermissions.isEmpty()) return permissions;

        if (action != null && !action.isEmpty()) {
            for (RolePermissions permission : storedPermissions) {
                for (ResourceActionLink actionLink : permission.getResourceActions()) {
                    if (actionLink.getAction().name().equals(action)) {
                        permissions.add(permission);
                    }
                }

            }
        } else {
            permissions = new ArrayList<>(storedPermissions);
        }

        return permissions;
    }

    // TODO move this to facade?
    public boolean isUserAllowedToPerformAction(User user, String resourceName, String actionName) {
        if (user == null) return false;
        if (user.getRoles() == null || user.getRoles().isEmpty()) return false;

        List<RolePermissions> actionPermissions = getPermissionsForResource(resourceName, actionName);
        if (actionPermissions == null || actionPermissions.isEmpty()) return false;
        // TODO log a warning for above -- there is an action noone is allowed to perform.. that's not right :/

        for (RolePermissions permissions : actionPermissions) {
            for (UserRole userRole : user.getRoles()) {
                if (userRole.getRole().equals(permissions.getRole())) {
                    return true;
                }
            }

        }
        return false;
    }


}
