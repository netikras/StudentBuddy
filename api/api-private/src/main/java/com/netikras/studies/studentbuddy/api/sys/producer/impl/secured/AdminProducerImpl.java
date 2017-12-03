package com.netikras.studies.studentbuddy.api.sys.producer.impl.secured;

import com.netikras.studies.studentbuddy.api.handlers.DtoMapper;
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
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

import static com.netikras.studies.studentbuddy.core.data.meta.Action.CREATE;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.DELETE;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.GET;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.GET_ALL;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.MODERATE;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.MODIFY;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.ROLE_PERMISSIONS;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.SYSTEM_PWREQ;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.SYSTEM_SETTING;

@Component
public class AdminProducerImpl {

    @Resource
    private ModelMapper modelMapper;
    @Resource
    private DtoMapper dtoMapper;

    @Resource
    private SystemService systemService;

    @Resource
    private PermissionsService permissionsService;
    @Resource
    private EntityProvider entityProvider;


    @Authorizable(resource = SYSTEM_SETTING, action = GET)
    @Transactional
    public SystemSettingDto getSystemSetting(String id) {
        SystemSetting setting = systemService.getSetting(id);
        SystemSettingDto dto = (SystemSettingDto) dtoMapper.toDto(setting);
        return dto;
    }

    @Authorizable(resource = SYSTEM_SETTING, action = CREATE)
    @Transactional
    public SystemSettingDto createSystemSetting(SystemSettingDto item) {
        SystemSetting setting = modelMapper.apply(new SystemSetting(), item, new MappingSettings().setForceUpdate(true));
        setting = systemService.createSetting(setting);
        SystemSettingDto dto = (SystemSettingDto) dtoMapper.toDto(setting);
        return dto;
    }

    @Authorizable(resource = SYSTEM_SETTING, action = MODIFY)
    @Transactional
    public SystemSettingDto updateSystemSetting(SystemSettingDto item) {
        SystemSetting setting = modelMapper.apply(entityProvider.fetch(item), item);
        setting = systemService.updateSystemSetting(setting);
        SystemSettingDto dto = (SystemSettingDto) dtoMapper.toDto(setting);
        return dto;
    }

    @Authorizable(resource = SYSTEM_SETTING, action = DELETE)
    public void deleteSystemSetting(String id) {
        systemService.deleteSystemSetting(id);
    }

    @Authorizable(resource = SYSTEM_PWREQ, action = GET)
    @Transactional
    public PasswordRequirementDto getPasswordRequirement(String id) {
        PasswordRequirement requirement = systemService.getPasswordRequirement(id);
        PasswordRequirementDto dto = (PasswordRequirementDto) dtoMapper.toDto(requirement);
        return dto;
    }

    @Authorizable(resource = SYSTEM_PWREQ, action = CREATE)
    @Transactional
    public PasswordRequirementDto createPasswordRequirement(PasswordRequirementDto item) {
        PasswordRequirement requirement = modelMapper.apply(new PasswordRequirement(), item, new MappingSettings().setForceUpdate(true));
        requirement = systemService.createPasswordRequirement(requirement);
        PasswordRequirementDto dto = (PasswordRequirementDto) dtoMapper.toDto(requirement);
        return dto;
    }

    @Authorizable(resource = SYSTEM_PWREQ, action = MODIFY)
    @Transactional
    public PasswordRequirementDto updatePasswordRequirement(PasswordRequirementDto item) {
        PasswordRequirement requirement = modelMapper.apply(entityProvider.fetch(item), item);
        requirement = systemService.updatePasswordRequirement(requirement);
        PasswordRequirementDto dto = (PasswordRequirementDto) dtoMapper.toDto(requirement);
        return dto;
    }

    @Authorizable(resource = SYSTEM_PWREQ, action = DELETE)
    public void deletePasswordRequirement(String id) {
        systemService.deletePasswordRequirement(id);
    }


    @Authorizable(resource = SYSTEM_SETTING, action = GET_ALL)
    @Transactional
    public List<SystemSettingDto> getAllStoredSystemSettings() {
        List<SystemSetting> settings = systemService.fetchSystemSettings();
        modelMapper.transformAll(settings, SystemSettingDto.class);
        List<SystemSettingDto> settingDtos = (List<SystemSettingDto>) dtoMapper.toDtos(settings);
        return settingDtos;
    }

    @Authorizable(resource = SYSTEM_SETTING, action = GET_ALL)
    @Transactional
    public List<SystemSettingDto> getAllLiveSystemSettings() {
        List<SystemSetting> settings = systemService.getSystemSettings();
        modelMapper.transformAll(settings, SystemSetting.class);
        List<SystemSettingDto> settingDtos = (List<SystemSettingDto>) dtoMapper.toDtos(settings);
        return settingDtos;
    }

    @Authorizable(resource = SYSTEM_SETTING, action = GET)
    @Transactional
    public SystemSettingDto getLiveSystemSetting(String name) {
        SystemSetting setting = systemService.getSettingByName(name);
        SystemSettingDto settingDto = (SystemSettingDto) dtoMapper.toDto(setting);
        return settingDto;
    }

    @Authorizable(resource = SYSTEM_SETTING, action = GET)
    @Transactional
    public SystemSettingDto getStoredSystemSetting(String name) {
        SystemSetting setting = systemService.getStoredSetting(name);
        SystemSettingDto settingDto = (SystemSettingDto) dtoMapper.toDto(setting);
        return settingDto;
    }

    @Authorizable(resource = SYSTEM_PWREQ, action = GET_ALL)
    @Transactional
    public List<PasswordRequirementDto> getAllLivePasswordRequirements() {
        List<PasswordRequirement> requirements = systemService.fetchPasswordRequirements();
        List<PasswordRequirementDto> requirementDtos = (List<PasswordRequirementDto>) dtoMapper.toDtos(requirements);
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
    public void deleteRolePermission(String roleName, String resourceName, String actionName, String resourceId, Boolean strict) {
        permissionsService.removeRolePermission(roleName, resourceName, actionName, resourceId, strict);
    }

    @Authorizable(resource = ROLE_PERMISSIONS, action = DELETE)
    public void deleteRolePermission(String roleName, String permissionId) {
        permissionsService.removeRolePermission(roleName, permissionId);
    }

    @Authorizable(resource = ROLE_PERMISSIONS, action = GET_ALL)
    @Transactional
    public List<RoleDto> getAllRoles() {
        List<Role> roles = permissionsService.getAllRoles();
        List<RoleDto> roleDtos = (List<RoleDto>) dtoMapper.toDtos(roles);
        return roleDtos;
    }


    @Authorizable(resource = ROLE_PERMISSIONS, action = CREATE)
    @Transactional
    public RolePermissionDto createRolePermission(String roleName, String resourceName, String actionName, String resourceId, Boolean strict) {
        Role role = permissionsService.addRolePermission(roleName, resourceName, actionName, resourceId, strict);
        ResourceActionLink link = role.getPermission(resourceName, actionName, resourceId);
        RolePermissionDto permissionDto = (RolePermissionDto) dtoMapper.toDto(link);
        return permissionDto;
    }

    @Authorizable(resource = ROLE_PERMISSIONS, action = DELETE)
    public void deleteRoleById(String id) {
        permissionsService.deleteRoleById(id);
    }

    @Authorizable(resource = ROLE_PERMISSIONS, action = CREATE)
    @Transactional
    public RoleDto createRole(String name) {
        Role role = permissionsService.createRole(name);
        RoleDto dto = (RoleDto) dtoMapper.toDto(role);
        return dto;
    }

    @Authorizable(resource = ROLE_PERMISSIONS, action = GET)
    @Transactional
    public RoleDto getRoleById(String id) {
        Role role = permissionsService.getRoleById(id);
        RoleDto dto = (RoleDto) dtoMapper.toDto(role);
        return dto;
    }

    @Authorizable(resource = ROLE_PERMISSIONS, action = GET)
    @Transactional
    public RoleDto getRoleByName(String name) {
        Role role = permissionsService.getRoleByName(name);
        RoleDto dto = (RoleDto) dtoMapper.toDto(role);
        return dto;
    }

    @Authorizable(resource = SYSTEM_PWREQ, action = GET_ALL)
    @Transactional
    public List<PasswordRequirementDto> getAllStoredPasswordRequirements() {
        List<PasswordRequirement> requirements = systemService.getPasswordRequirements();
        List<PasswordRequirementDto> dtos = (List<PasswordRequirementDto>) dtoMapper.toDtos(requirements);
        return dtos;
    }


    public void refreshLiveRolePermissions() {
        systemService.refreshRolesPermissions();
    }
}
