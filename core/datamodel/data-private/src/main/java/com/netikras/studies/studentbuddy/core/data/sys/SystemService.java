package com.netikras.studies.studentbuddy.core.data.sys;

import com.netikras.studies.studentbuddy.commons.exception.StudBudUncheckedException;
import com.netikras.studies.studentbuddy.core.data.api.dao.RolePermissionsDao;
import com.netikras.studies.studentbuddy.core.data.sys.dao.PasswordRequirementsDao;
import com.netikras.studies.studentbuddy.core.data.sys.dao.SettingsDao;
import com.netikras.studies.studentbuddy.core.data.sys.model.PasswordRequirement;
import com.netikras.studies.studentbuddy.core.data.sys.model.ResourceActionLink;
import com.netikras.studies.studentbuddy.core.data.sys.model.RolePermissions;
import com.netikras.studies.studentbuddy.core.data.sys.model.SystemSetting;
import com.netikras.studies.studentbuddy.core.data.sys.model.User;
import com.netikras.studies.studentbuddy.core.data.sys.model.UserRole;
import com.netikras.tools.common.remote.http.HttpStatus;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        if (passwordRequirements == null) {
            refreshPaswordRequirements();
        }
        return passwordRequirements;
    }

    @Transactional
    public List<PasswordRequirement> fetchPasswordRequirements() {
        return passwordRequirementsDao.findAll();
    }

    @Transactional
    public PasswordRequirement createPasswordRequirement(PasswordRequirement requirement) {
        return passwordRequirementsDao.save(requirement);
    }

    public List<PasswordRequirement> refreshPaswordRequirements() {
        passwordRequirements = fetchPasswordRequirements();
        return getPasswordRequirements();
    }

    @Transactional
    public void deletePasswordRequirement(String id) {
        passwordRequirementsDao.delete(id);
    }


    @Transactional
    public PasswordRequirement updatePasswordRequirement(PasswordRequirement requirement) {
        return passwordRequirementsDao.save(requirement);
    }


    public List<SystemSetting> getSystemSettings() {
        if (systemSettings == null) {
            refreshSettings();
        }
        return systemSettings;
    }

    @Transactional
    public List<SystemSetting> fetchSystemSettings() {
        return settingsDao.findAll();
    }

    public List<SystemSetting> refreshSettings() {
        systemSettings = fetchSystemSettings();
        return getSystemSettings();
    }

    @Transactional
    public SystemSetting updateSystemSetting(SystemSetting setting) {
        SystemSetting oldSetting = settingsDao.findByName(setting.getName());
        if (oldSetting == null) {
            throw new StudBudUncheckedException()
                    .setMessage1("Unable to update setting")
                    .setMessage2("Original setting not found")
                    .setProbableCause(setting.getName())
                    .setStatusCode(HttpStatus.NOT_FOUND);
        }
        setting.setId(oldSetting.getId());
        return settingsDao.save(setting);
    }

    @Transactional
    public void deleteSystemSettingByName(String name) {
        settingsDao.deleteByName(name);
    }

    @Transactional
    public SystemSetting createSetting(SystemSetting setting) {
        return settingsDao.save(setting);
    }

    @Transactional
    public SystemSetting getStoredSetting(String name) {
        return settingsDao.findByName(name);
    }


    public SystemSetting getSettingByName(String name) {
        if (getSystemSettings() == null) return null;
        if (name == null || name.isEmpty()) return null;

        for (SystemSetting setting : getSystemSettings()) {
            if (name.equals(setting.getName())) {
                return setting;
            }
        }
        return null;
    }

    public String getSettingValue(String name, String defaultValue) {
        SystemSetting setting = getSettingByName(name);
        if (setting == null) return defaultValue;
        if (setting.getValue() == null && defaultValue != null) {
            return defaultValue;
        }
        return setting.getValue();
    }

    public boolean getSettingValue(String name, boolean defaultValue) {
        String settingValue = getSettingValue(name, "" + defaultValue);
        boolean result = defaultValue;
        try {
            result = Boolean.valueOf(settingValue);
        } catch (Throwable ignored) {
        }

        return result;
    }

    public int getSettingValue(String name, int defaultValue) {
        String settingValue = getSettingValue(name, "" + defaultValue);
        int result = defaultValue;
        try {
            result = Integer.valueOf(settingValue);
        } catch (Throwable ignored) {
        }

        return result;
    }

    public long getSettingValue(String name, long defaultValue) {
        String settingValue = getSettingValue(name, "" + defaultValue);
        long result = defaultValue;
        try {
            result = Long.valueOf(settingValue);
        } catch (Throwable ignored) {
        }

        return result;
    }

    public int getSettingValue(SysProp.IntProperty property) {
        return getSettingValue(property.getName(), property.getDefaultValue());
    }

    public long getSettingValue(SysProp.LongProperty property) {
        return getSettingValue(property.getName(), property.getDefaultValue());
    }

    public boolean getSettingValue(SysProp.BooleanProperty property) {
        return getSettingValue(property.getName(), property.getDefaultValue());
    }

    public String getSettingValue(SysProp.StringProperty property) {
        return getSettingValue(property.getName(), property.getDefaultValue());
    }


    @Transactional
    public RolePermissions createRolePermissions(RolePermissions permissions) {
        return rolePermissionsDao.save(permissions);
    }

    @Transactional
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

    @Transactional
    public void removeRolePermission(String roleName, ResourceActionLink resourceActionLink) {
        RolePermissions permissions = rolePermissionsDao.findByRole_Name(roleName);
        if (permissions == null) return;

        if (permissions.getResourceActions() == null) return;

        boolean removed = permissions.getResourceActions().removeIf(resourceActionLink::isLike);

        if (removed) {
            rolePermissionsDao.save(permissions);
        }
    }


    @Transactional
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

        List<RolePermissions> livePermissions = permissionsForResource.get(resourceId);
        if (livePermissions == null || livePermissions.isEmpty()) return permissions;

        if (action != null && !action.isEmpty()) {
            for (RolePermissions permission : livePermissions) {
                for (ResourceActionLink actionLink : permission.getResourceActions()) {
                    if (actionLink.getAction().name().equals(action)) {
                        permissions.add(permission);
                    }
                }

            }
        } else {
            permissions = new ArrayList<>(livePermissions);
        }

        return permissions;
    }

    // TODO move this to facade?
    public boolean isUserAllowedToPerformAction(User user, String resourceName, String actionName) {
        if (user == null) return false;
        if ("system".equals(user.getName())) return true;
        if (user.getRoles() == null || user.getRoles().isEmpty()) return false;

        List<RolePermissions> actionPermissions = getPermissionsForResource(resourceName, actionName);
        if (actionPermissions == null || actionPermissions.isEmpty()) return false;

        for (RolePermissions permissions : actionPermissions) {
            for (UserRole userRole : user.getRoles()) {
                if (userRole.getRole().equals(permissions.getRole())) {
                    return true;
                }
            }

        }
        return false;
    }


    public SystemSetting getSetting(String id) {
        return settingsDao.findOne(id);
    }

    @Transactional
    public void deleteSystemSetting(String id) {
        settingsDao.delete(id);
    }

    public PasswordRequirement getPasswordRequirement(String id) {
        return passwordRequirementsDao.findOne(id);
    }
}
