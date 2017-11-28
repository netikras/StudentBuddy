package com.netikras.studies.studentbuddy.api.sys.producer.impl.secured;

import com.netikras.studies.studentbuddy.core.data.api.dto.meta.PasswordRequirementDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.meta.RoleDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.meta.RolePermissionDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.meta.SystemSettingDto;
import com.netikras.studies.studentbuddy.core.data.meta.annotations.Authorizable;
import com.netikras.studies.studentbuddy.core.data.sys.model.PasswordRequirement;
import com.netikras.studies.studentbuddy.core.data.sys.model.ResourceActionLink;
import com.netikras.studies.studentbuddy.core.data.sys.model.Role;
import com.netikras.studies.studentbuddy.core.data.sys.model.SystemSetting;
import com.netikras.studies.studentbuddy.core.service.PermissionsService;
import com.netikras.studies.studentbuddy.core.service.SystemService;
import com.netikras.studies.studentbuddy.core.validator.EntityProvider;
import com.netikras.tools.common.model.mapper.MappingSettings;
import com.netikras.tools.common.model.mapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

import static com.netikras.studies.studentbuddy.core.data.meta.Action.*;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.ROLE_PERMISSIONS;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.SYSTEM_PWREQ;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.SYSTEM_SETTING;

@Component
public class AdminProducerImpl {

    @Resource
    private ModelMapper modelMapper;

    @Resource
    private SystemService systemService;

    @Resource
    private PermissionsService permissionsService;
    @Resource
    private EntityProvider entityProvider;



    @Authorizable(resource = SYSTEM_SETTING, action = GET)
    public SystemSettingDto getSystemSetting(String id) {
        SystemSetting setting = systemService.getSetting(id);
        SystemSettingDto dto = modelMapper.transform(setting, new SystemSettingDto());
        return dto;
    }

    @Authorizable(resource = SYSTEM_SETTING, action = CREATE)
    public SystemSettingDto createSystemSetting(SystemSettingDto item) {
        SystemSetting setting = modelMapper.apply(new SystemSetting(), item, new MappingSettings().setForceUpdate(true));
        setting = systemService.createSetting(setting);
        item = modelMapper.transform(setting, new SystemSettingDto());
        return item;
    }

    @Authorizable(resource = SYSTEM_SETTING, action = MODIFY)
    public SystemSettingDto updateSystemSetting(SystemSettingDto item) {
        SystemSetting setting = modelMapper.apply(entityProvider.fetch(item), item);
        setting = systemService.updateSystemSetting(setting);
        item = modelMapper.transform(setting, new SystemSettingDto());
        return item;
    }

    @Authorizable(resource = SYSTEM_SETTING, action = DELETE)
    public void deleteSystemSetting(String id) {
        systemService.deleteSystemSetting(id);
    }

    @Authorizable(resource = SYSTEM_PWREQ, action = GET)
    public PasswordRequirementDto getPasswordRequirement(String id) {
        PasswordRequirement requirement = systemService.getPasswordRequirement(id);
        PasswordRequirementDto dto = modelMapper.transform(requirement, new PasswordRequirementDto());

        return dto;
    }

    @Authorizable(resource = SYSTEM_PWREQ, action = CREATE)
    public PasswordRequirementDto createPasswordRequirement(PasswordRequirementDto item) {
        PasswordRequirement requirement = modelMapper.apply(new PasswordRequirement(), item, new MappingSettings().setForceUpdate(true));
        requirement = systemService.createPasswordRequirement(requirement);
        item = modelMapper.transform(requirement, new PasswordRequirementDto());
        return item;
    }

    @Authorizable(resource = SYSTEM_PWREQ, action = MODIFY)
    public PasswordRequirementDto updatePasswordRequirement(PasswordRequirementDto item) {
        PasswordRequirement requirement = modelMapper.apply(entityProvider.fetch(item), item);
        requirement = systemService.updatePasswordRequirement(requirement);
        item = modelMapper.transform(requirement, new PasswordRequirementDto());
        return item;
    }

    @Authorizable(resource = SYSTEM_PWREQ, action = DELETE)
    public void deletePasswordRequirement(String id) {
        systemService.deletePasswordRequirement(id);
    }




    @Authorizable(resource = SYSTEM_SETTING, action = GET_ALL)
    public List<SystemSettingDto> getAllStoredSystemSettings() {
        List<SystemSetting> settings = systemService.fetchSystemSettings();
        modelMapper.transformAll(settings, SystemSettingDto.class);
        List<SystemSettingDto> settingDtos = (List<SystemSettingDto>) modelMapper.transformAll(settings, SystemSettingDto.class);
        return settingDtos;
    }

    @Authorizable(resource = SYSTEM_SETTING, action = GET_ALL)
    public List<SystemSettingDto> getAllLiveSystemSettings() {
        List<SystemSetting> settings = systemService.getSystemSettings();
        modelMapper.transformAll(settings, SystemSetting.class);
        List<SystemSettingDto> settingDtos = (List<SystemSettingDto>) modelMapper.transformAll(settings, SystemSettingDto.class);
        return settingDtos;
    }

    @Authorizable(resource = SYSTEM_SETTING, action = GET)
    public SystemSettingDto getLiveSystemSetting(String name) {
        SystemSetting setting = systemService.getSettingByName(name);
        SystemSettingDto settingDto = modelMapper.transform(setting, new SystemSettingDto());
        return settingDto;
    }

    @Authorizable(resource = SYSTEM_SETTING, action = GET)
    public SystemSettingDto getStoredSystemSetting(String name) {
        SystemSetting setting = systemService.getStoredSetting(name);
        SystemSettingDto settingDto = modelMapper.transform(setting, new SystemSettingDto());
        return settingDto;
    }

    @Authorizable(resource = SYSTEM_PWREQ, action = GET_ALL)
    public List<PasswordRequirementDto> getAllLivePasswordRequirements() {
        List<PasswordRequirement> requirements = systemService.fetchPasswordRequirements();
        List<PasswordRequirementDto> requirementDtos =
                (List<PasswordRequirementDto>) modelMapper.transformAll(requirements, PasswordRequirementDto.class);
        return requirementDtos;
    }

    @Authorizable(resource = SYSTEM_PWREQ, action = MODERATE)
    public void refreshLivePasswordRequirements() {
        systemService.refreshPasswordRequirements();
    }

    @Authorizable(resource = SYSTEM_SETTING, action = MODERATE)
    public void refreshLiveSystemSettings() {
        systemService.refreshSettings();
    }


    @Authorizable(resource = SYSTEM_SETTING, action = DELETE)
    public void deleteStoredSystemSettingByName(String name) {
        systemService.deleteSystemSettingByName(name);
    }

    @Authorizable(resource = ROLE_PERMISSIONS, action = DELETE)
    public void deleteRolePermission(String roleName, String resourceName, String actionName, String resourceId) {
        permissionsService.removeRolePermission(roleName, resourceName, actionName, resourceId);
    }

    @Authorizable(resource = ROLE_PERMISSIONS, action = GET_ALL)
    public List<RoleDto> getAllRoles() {
        List<Role> roles = permissionsService.getAllRoles();
        List<RoleDto> roleDtos = (List<RoleDto>) modelMapper.transformAll(roles, RoleDto.class);
        return roleDtos;
    }


    @Authorizable(resource = ROLE_PERMISSIONS, action = CREATE)
    public RolePermissionDto createRolePermission(String roleName, String resourceName, String actionName, String resourceId, Boolean strict) {
        Role role = permissionsService.addRolePermission(roleName, resourceName, actionName, resourceId, strict);
        ResourceActionLink link = role.getPermission(resourceName, actionName, resourceId);
        RolePermissionDto permissionDto = modelMapper.transform(link, new RolePermissionDto());
        return permissionDto;
    }

    @Authorizable(resource = ROLE_PERMISSIONS, action = DELETE)
    public void deleteRoleById(String id) {
        permissionsService.deleteRoleById(id);
    }

    @Authorizable(resource = ROLE_PERMISSIONS, action = CREATE)
    public RoleDto createRole(String name) {
        Role role = permissionsService.createRole(name);
        RoleDto dto = modelMapper.transform(role, new RoleDto());
        return dto;
    }

    @Authorizable(resource = ROLE_PERMISSIONS, action = GET)
    public RoleDto getRoleById(String id) {
        Role role = permissionsService.getRoleById(id);
        RoleDto dto = modelMapper.transform(role, new RoleDto());
        return dto;
    }

    @Authorizable(resource = ROLE_PERMISSIONS, action = GET)
    public RoleDto getRoleByName(String name) {
        Role role = permissionsService.getRoleByName(name);
        RoleDto dto = modelMapper.transform(role, new RoleDto());
        return dto;
    }

    @Authorizable(resource = SYSTEM_PWREQ, action = GET_ALL)
    public List<PasswordRequirementDto> getAllStoredPasswordRequirements() {
        List<PasswordRequirement> requirements = systemService.getPasswordRequirements();
        List<PasswordRequirementDto> dtos = (List<PasswordRequirementDto>) modelMapper.transformAll(requirements, PasswordRequirementDto.class);
        return dtos;
    }


    public void refreshLiveRolePermissions() {
        systemService.refreshRolesPermissions();
    }
}
