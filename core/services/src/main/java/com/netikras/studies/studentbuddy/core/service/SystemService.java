package com.netikras.studies.studentbuddy.core.service;

import com.netikras.studies.studentbuddy.core.data.sys.SysProp;
import com.netikras.studies.studentbuddy.core.data.sys.model.PasswordRequirement;
import com.netikras.studies.studentbuddy.core.data.sys.model.ResourceActionLink;
import com.netikras.studies.studentbuddy.core.data.sys.model.Role;
import com.netikras.studies.studentbuddy.core.data.sys.model.SystemSetting;
import com.netikras.studies.studentbuddy.core.data.sys.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SystemService {
    User getGuestUser();

    List<PasswordRequirement> getPasswordRequirements();

    List<PasswordRequirement> fetchPasswordRequirements();

    PasswordRequirement createPasswordRequirement(PasswordRequirement requirement);

    List<PasswordRequirement> refreshPasswordRequirements();

    void deletePasswordRequirement(String id);

    PasswordRequirement updatePasswordRequirement(PasswordRequirement requirement);

    List<SystemSetting> getSystemSettings();

    List<SystemSetting> fetchSystemSettings();

    List<SystemSetting> refreshSettings();

    SystemSetting updateSystemSetting(SystemSetting setting);

    void deleteSystemSettingByName(String name);

    SystemSetting createSetting(SystemSetting setting);

    SystemSetting getStoredSetting(String name);

    SystemSetting getSettingByName(String name);

    String getSettingValue(String name, String defaultValue);

    boolean getSettingValue(String name, boolean defaultValue);

    int getSettingValue(String name, int defaultValue);

    long getSettingValue(String name, long defaultValue);

    int getSettingValue(SysProp.IntProperty property);

    long getSettingValue(SysProp.LongProperty property);

    boolean getSettingValue(SysProp.BooleanProperty property);

    String getSettingValue(SysProp.StringProperty property);


    ResourceActionLink createRolePermission(ResourceActionLink permission);

    void removeRolePermission(String roleName, ResourceActionLink resourceActionLink);

    List<Role> fetchRoles();

    List<Role> refreshRolesPermissions();

    List<ResourceActionLink> getPermissionsForRole(String roleName);

    List<ResourceActionLink> getPermissionsForResource(String resourceName, String action);

    // TODO move this to facade?
    boolean isUserAllowedToPerformAction(User user, String resourceName, String resourceId, String actionName);

    SystemSetting getSetting(String id);

    void deleteSystemSetting(String id);

    PasswordRequirement getPasswordRequirement(String id);
}
