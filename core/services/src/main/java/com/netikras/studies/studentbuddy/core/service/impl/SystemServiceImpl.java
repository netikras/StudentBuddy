package com.netikras.studies.studentbuddy.core.service.impl;

import com.netikras.studies.studentbuddy.commons.exception.StudBudUncheckedException;
import com.netikras.studies.studentbuddy.core.data.api.dao.JpaRepo;
import com.netikras.studies.studentbuddy.core.data.api.dao.ResourceRepoProvider;
import com.netikras.studies.studentbuddy.core.data.api.dao.RoleDao;
import com.netikras.studies.studentbuddy.core.data.api.dao.RolePermissionDao;
import com.netikras.studies.studentbuddy.core.data.api.model.Course;
import com.netikras.studies.studentbuddy.core.data.api.model.Lecture;
import com.netikras.studies.studentbuddy.core.data.sys.SysProp;
import com.netikras.studies.studentbuddy.core.data.sys.dao.PasswordRequirementsDao;
import com.netikras.studies.studentbuddy.core.data.sys.dao.SettingsDao;
import com.netikras.studies.studentbuddy.core.data.sys.dao.UserDao;
import com.netikras.studies.studentbuddy.core.data.sys.model.PasswordRequirement;
import com.netikras.studies.studentbuddy.core.data.sys.model.ResourceActionLink;
import com.netikras.studies.studentbuddy.core.data.sys.model.Role;
import com.netikras.studies.studentbuddy.core.data.sys.model.SystemSetting;
import com.netikras.studies.studentbuddy.core.data.sys.model.User;
import com.netikras.studies.studentbuddy.core.data.sys.model.UserRole;
import com.netikras.studies.studentbuddy.core.service.SystemService;
import com.netikras.studies.studentbuddy.core.validator.SystemValidator;
import com.netikras.tools.common.exception.ErrorsCollection;
import com.netikras.tools.common.remote.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.netikras.tools.common.remote.http.HttpStatus.BAD_REQUEST;
import static com.netikras.tools.common.security.IntegrityUtils.areEqual;
import static com.netikras.tools.common.security.IntegrityUtils.isNullOrEmpty;

@Service
public class SystemServiceImpl implements SystemService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private SettingsDao settingsDao;
    @Resource
    private PasswordRequirementsDao passwordRequirementsDao;
    @Resource
    private RoleDao roleDao;
    @Resource
    private UserDao userDao;
    @Resource
    private SystemValidator systemValidator;
    @Resource
    private ResourceRepoProvider repoProvider;
    @Resource
    private RolePermissionDao permissionDao;

    private User guestUser = null;

    private List<PasswordRequirement> passwordRequirements;

    private List<SystemSetting> systemSettings;

    private Map<String, List<ResourceActionLink>> permissionsForRole;

    private Map<String, List<ResourceActionLink>> permissionsForResource;

    @Override
    @Transactional
    public User getGuestUser() {

        if (getSettingValue("disable_guest", false)) {
            return null;
        }

        if (guestUser == null
                || getSettingValue("refresh_guest", false)) {
            guestUser = userDao.findByName("guest");
        }
        return guestUser;
    }

    @Override
    public List<PasswordRequirement> getPasswordRequirements() {
        if (passwordRequirements == null) {
            refreshPasswordRequirements();
        }
        return passwordRequirements;
    }

    @Override
    @Transactional
    public List<PasswordRequirement> fetchPasswordRequirements() {
        return passwordRequirementsDao.findAll();
    }

    @Override
    @Transactional
    public PasswordRequirement createPasswordRequirement(PasswordRequirement requirement) {
        return passwordRequirementsDao.save(requirement);
    }

    @Override
    public List<PasswordRequirement> refreshPasswordRequirements() {
        passwordRequirements = fetchPasswordRequirements();
        return getPasswordRequirements();
    }

    @Override
    @Transactional
    public void deletePasswordRequirement(String id) {
        passwordRequirementsDao.delete(id);
    }


    @Override
    @Transactional
    public PasswordRequirement updatePasswordRequirement(PasswordRequirement requirement) {
        return passwordRequirementsDao.save(requirement);
    }


    @Override
    public List<SystemSetting> getSystemSettings() {
        if (systemSettings == null) {
            refreshSettings();
        }
        return systemSettings;
    }

    @Override
    @Transactional
    public List<SystemSetting> fetchSystemSettings() {
        return settingsDao.findAll();
    }

    @Override
    public List<SystemSetting> refreshSettings() {
        systemSettings = fetchSystemSettings();
        return getSystemSettings();
    }

    @Override
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

    @Override
    @Transactional
    public void deleteSystemSettingByName(String name) {
        settingsDao.deleteByName(name);
    }

    @Override
    @Transactional
    public SystemSetting createSetting(SystemSetting setting) {
        ErrorsCollection errors = systemValidator.validateForCreation(setting, null);
        if (!errors.isEmpty()) {
            throw new StudBudUncheckedException()
                    .setMessage1("Cannot create role permissions")
                    .setMessage2("Validation errors: " + errors.size())
                    .setErrors(errors)
                    .setStatusCode(BAD_REQUEST)
                    ;
        }

        return settingsDao.save(setting);
    }

    @Override
    @Transactional
    public SystemSetting getStoredSetting(String name) {
        return settingsDao.findByName(name);
    }


    @Override
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

    @Override
    public String getSettingValue(String name, String defaultValue) {
        SystemSetting setting = getSettingByName(name);
        if (setting == null) return defaultValue;
        if (setting.getValue() == null && defaultValue != null) {
            return defaultValue;
        }
        return setting.getValue();
    }

    @Override
    public boolean getSettingValue(String name, boolean defaultValue) {
        String settingValue = getSettingValue(name, "" + defaultValue);
        boolean result = defaultValue;
        try {
            result = Boolean.valueOf(settingValue);
        } catch (Throwable ignored) {
        }

        return result;
    }


    @Override
    public int getSettingValue(String name, int defaultValue) {
        String settingValue = getSettingValue(name, "" + defaultValue);
        int result = defaultValue;
        try {
            result = Integer.valueOf(settingValue);
        } catch (Throwable ignored) {
        }

        return result;
    }


    @Override
    public long getSettingValue(String name, long defaultValue) {
        String settingValue = getSettingValue(name, "" + defaultValue);
        long result = defaultValue;
        try {
            result = Long.valueOf(settingValue);
        } catch (Throwable ignored) {
        }

        return result;
    }

    @Override
    public int getSettingValue(SysProp.IntProperty property) {
        return getSettingValue(property.getName(), property.getDefaultValue());
    }

    @Override
    public long getSettingValue(SysProp.LongProperty property) {
        return getSettingValue(property.getName(), property.getDefaultValue());
    }

    @Override
    public boolean getSettingValue(SysProp.BooleanProperty property) {
        return getSettingValue(property.getName(), property.getDefaultValue());
    }

    @Override
    public String getSettingValue(SysProp.StringProperty property) {
        return getSettingValue(property.getName(), property.getDefaultValue());
    }


    @Override
    @Transactional
    public ResourceActionLink createRolePermission(ResourceActionLink permission) {
        ErrorsCollection errors = systemValidator.validateForCreation(permission, null);
        if (!errors.isEmpty()) {
            throw new StudBudUncheckedException()
                    .setMessage1("Cannot create role permissions")
                    .setMessage2("Validation errors: " + errors.size())
                    .setErrors(errors)
                    .setStatusCode(BAD_REQUEST)
                    ;
        }
        return permissionDao.save(permission);
    }

    @Override
    @Transactional
    public void removeRolePermission(String roleName, ResourceActionLink resourceActionLink) {
        Role role = roleDao.findByName(roleName);
        if (role == null) return;

        if (isNullOrEmpty(role.getPermissions())) return;

        boolean removed = role.getPermissions().removeIf(resourceActionLink::isLike);

        if (removed) {
            roleDao.save(role);
        }
    }


    @Override
    @Transactional
    public List<Role> fetchRoles() {
        return roleDao.findAll();
    }

    @Override
    public List<Role> refreshRolesPermissions() {
        List<Role> freshRoles = fetchRoles();
        sortFreshPermissions(freshRoles);
        return freshRoles;
    }

    public void sortFreshPermissions(List<Role> freshRoles) {

        List<ResourceActionLink> permissionsByResource = null;
        List<ResourceActionLink> permissionsByRole = null;

        if (permissionsForRole == null) permissionsForRole = new HashMap<>();
        if (permissionsForResource == null) permissionsForResource = new HashMap<>();

        permissionsForResource.clear();
        permissionsForRole.clear();

        if (isNullOrEmpty(freshRoles)) return;

        for (Role role : freshRoles) {
            if (isNullOrEmpty(role.getPermissions())) continue;

            String roleName = role.getName();

            for (ResourceActionLink resourceAction : role.getPermissions()) {
                String resource = resourceAction.getResource().name();
                permissionsByResource = permissionsForResource.computeIfAbsent(resource, k -> new ArrayList<>());
                permissionsByResource.add(resourceAction);

                permissionsByRole = permissionsForRole.computeIfAbsent(roleName, k -> new ArrayList<>());
                permissionsByRole.add(resourceAction);
            }
        }
    }

    @Override
    public List<ResourceActionLink> getPermissionsForRole(String roleName) {
        return permissionsForRole == null ? new ArrayList<>() : permissionsForRole.get(roleName);
    }


    @Override
    public List<ResourceActionLink> getPermissionsForResource(String resourceName, String action) {
        List<ResourceActionLink> permissions = new ArrayList<>();

        if (isNullOrEmpty(resourceName)) return permissions;
        if (isNullOrEmpty(permissionsForResource)) return permissions;

        List<ResourceActionLink> livePermissions = permissionsForResource.get(resourceName);
        if (isNullOrEmpty(livePermissions)) return permissions;

        if (!isNullOrEmpty(action)) {
            for (ResourceActionLink permission : livePermissions) {
                if (permission.getAction().name().equals(action)) {
                    permissions.add(permission);
                }
            }
        } else {
            permissions = new ArrayList<>(livePermissions);
        }

        return permissions;
    }

    // TODO move this to facade?
    @Override
    @Transactional
    public boolean isUserAllowedToPerformAction(User user, String resourceName, String resourceId, String actionName) {
        if (user == null) return false;
        if ("system".equals(user.getName())) {
            logAccessAllowed(user.getName(), actionName, resourceName, resourceId, "-", "-");
            return true;
        }
        if (isNullOrEmpty(user.getRoles())) {
            logAccessDenied(user.getName(), actionName, resourceName, resourceId, "user has no roles assigned");
            return false;
        }

        List<ResourceActionLink> actionPermissions = getPermissionsForResource(resourceName, actionName);
        if (isNullOrEmpty(actionPermissions)) {
            logAccessDenied(user.getName(), actionName, resourceName, resourceId, "there are no roles granting needed access for resource");
            return false;
        }


        if (!isNullOrEmpty(resourceId)) {
            if (com.netikras.studies.studentbuddy.core.data.meta.Resource.LECTURE.name().equals(resourceName)) {
                JpaRepo<Lecture> repo = repoProvider.getRepoForResource(resourceName);
                Course course = repo.findOne(resourceId).getCourse();
                if (course != null
                        && !isNullOrEmpty(course.getId())) {
                    resourceId = course.getId();
                }

            }
        }

        for (ResourceActionLink permissions : actionPermissions) {
            for (UserRole userRole : user.getRoles()) {
                if (userRole.getRole().getName().equals(permissions.getRole().getName())) {
                    if (permissions.isStrict()) {
                        if (areEqual(permissions.getEntityId(), resourceId)) {
                            logAccessAllowed(user.getName(), actionName, resourceName, resourceId, userRole.getRole().getName(), permissions.getId());
                            return true;
                        }
                    } else {
                        logAccessAllowed(user.getName(), actionName, resourceName, resourceId, userRole.getRole().getName(), permissions.getId());
                        return true;
                    }
                }
            }
        }

        logAccessDenied(user.getName(), actionName, resourceName, resourceId, "allowing access rule not found");
        return false;
    }

    private void logAccessAllowed(String userName, String actionName, String resourceName, String resourceId, String roleName, String permissionsId) {
        logger.debug(String.format(
                "Allowing user %s to perform action %s on resource %s with id %s. Access granted as per role %s permission %s",
                userName, actionName, resourceName, resourceId, roleName, permissionsId
                )
        );
    }

    private void logAccessDenied(String userName, String actionName, String resourceName, String resourceId, String reason) {
        logger.debug(String.format(
                "Denying user %s from performing action %s on resource %s with id %s. Reason: %s",
                userName, actionName, resourceName, resourceId, reason
                )
        );
    }

    @Override
    public SystemSetting getSetting(String id) {
        return settingsDao.findOne(id);
    }

    @Override
    @Transactional
    public void deleteSystemSetting(String id) {
        settingsDao.delete(id);
    }

    @Override
    public PasswordRequirement getPasswordRequirement(String id) {
        return passwordRequirementsDao.findOne(id);
    }
}
