package com.netikras.studies.studentbuddy.api.sys.producer.impl.secured;

import com.netikras.studies.studentbuddy.core.data.api.dto.meta.PasswordRequirementDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.meta.SystemSettingDto;
import com.netikras.studies.studentbuddy.core.data.meta.annotations.Authorizable;
import com.netikras.studies.studentbuddy.core.data.sys.model.PasswordRequirement;
import com.netikras.studies.studentbuddy.core.data.sys.model.SystemSetting;
import com.netikras.studies.studentbuddy.core.service.SystemService;
import com.netikras.tools.common.model.mapper.MappingSettings;
import com.netikras.tools.common.model.mapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

import static com.netikras.studies.studentbuddy.core.data.meta.Action.*;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.SYSTEM_PWREQ;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.SYSTEM_SETTING;

@Component
public class AdminProducerImpl {

    @Resource
    private SystemService systemService;



    @Authorizable(resource = SYSTEM_SETTING, action = GET)
    public SystemSettingDto getSystemSetting(String id) {
        SystemSetting setting = systemService.getSetting(id);
        SystemSettingDto dto = ModelMapper.transform(setting, new SystemSettingDto());
        return dto;
    }

    @Authorizable(resource = SYSTEM_SETTING, action = CREATE)
    public SystemSettingDto createSystemSetting(SystemSettingDto item) {
        SystemSetting setting = ModelMapper.apply(new SystemSetting(), item, new MappingSettings().setForceUpdate(true));
        setting = systemService.createSetting(setting);
        item = ModelMapper.transform(setting, new SystemSettingDto());
        return item;
    }

    @Authorizable(resource = SYSTEM_SETTING, action = MODIFY)
    public SystemSettingDto updateSystemSetting(SystemSettingDto item) {
        SystemSetting setting = ModelMapper.apply(new SystemSetting(), item);
        setting = systemService.updateSystemSetting(setting);
        item = ModelMapper.transform(setting, new SystemSettingDto());
        return item;
    }

    @Authorizable(resource = SYSTEM_SETTING, action = DELETE)
    public void deleteSystemSetting(String id) {
        systemService.deleteSystemSetting(id);
    }

    @Authorizable(resource = SYSTEM_PWREQ, action = GET)
    public PasswordRequirementDto getPasswordRequirement(String id) {
        PasswordRequirement requirement = systemService.getPasswordRequirement(id);
        PasswordRequirementDto dto = ModelMapper.transform(requirement, new PasswordRequirementDto());

        return dto;
    }

    @Authorizable(resource = SYSTEM_PWREQ, action = CREATE)
    public PasswordRequirementDto createPasswordRequirement(PasswordRequirementDto item) {
        PasswordRequirement requirement = ModelMapper.apply(new PasswordRequirement(), item, new MappingSettings().setForceUpdate(true));
        requirement = systemService.createPasswordRequirement(requirement);
        item = ModelMapper.transform(requirement, new PasswordRequirementDto());
        return item;
    }

    @Authorizable(resource = SYSTEM_PWREQ, action = MODIFY)
    public PasswordRequirementDto updatePasswordRequirement(PasswordRequirementDto item) {
        PasswordRequirement requirement = ModelMapper.apply(new PasswordRequirement(), item);
        requirement = systemService.updatePasswordRequirement(requirement);
        item = ModelMapper.transform(requirement, new PasswordRequirementDto());
        return item;
    }

    @Authorizable(resource = SYSTEM_PWREQ, action = DELETE)
    public void deletePasswordRequirement(String id) {
        systemService.deletePasswordRequirement(id);
    }




    @Authorizable(resource = SYSTEM_SETTING, action = GET_ALL)
    public List<SystemSettingDto> getAllStoredSystemSettings() {
        List<SystemSetting> settings = systemService.fetchSystemSettings();
        ModelMapper.transformAll(settings, SystemSettingDto.class);
        List<SystemSettingDto> settingDtos = (List<SystemSettingDto>) ModelMapper.transformAll(settings, SystemSettingDto.class);
        return settingDtos;
    }

    @Authorizable(resource = SYSTEM_SETTING, action = GET_ALL)
    public List<SystemSettingDto> getAllLiveSystemSettings() {
        List<SystemSetting> settings = systemService.getSystemSettings();
        ModelMapper.transformAll(settings, SystemSetting.class);
        List<SystemSettingDto> settingDtos = (List<SystemSettingDto>) ModelMapper.transformAll(settings, SystemSettingDto.class);
        return settingDtos;
    }

    @Authorizable(resource = SYSTEM_SETTING, action = GET)
    public SystemSettingDto getLiveSystemSetting(String name) {
        SystemSetting setting = systemService.getSettingByName(name);
        SystemSettingDto settingDto = ModelMapper.transform(setting, new SystemSettingDto());
        return settingDto;
    }

    @Authorizable(resource = SYSTEM_SETTING, action = GET)
    public SystemSettingDto getStoredSystemSetting(String name) {
        SystemSetting setting = systemService.getStoredSetting(name);
        SystemSettingDto settingDto = ModelMapper.transform(setting, new SystemSettingDto());
        return settingDto;
    }

    @Authorizable(resource = SYSTEM_PWREQ, action = GET_ALL)
    public List<PasswordRequirementDto> getAllLivePasswordRequirements() {
        List<PasswordRequirement> requirements = systemService.fetchPasswordRequirements();
        List<PasswordRequirementDto> requirementDtos =
                (List<PasswordRequirementDto>) ModelMapper.transformAll(requirements, PasswordRequirementDto.class);
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
}
