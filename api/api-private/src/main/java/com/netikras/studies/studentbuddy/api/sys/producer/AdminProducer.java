package com.netikras.studies.studentbuddy.api.sys.producer;

import com.netikras.studies.studentbuddy.api.sys.generated.AdminApiProducer;
import com.netikras.studies.studentbuddy.core.data.api.dto.meta.PasswordRequirementDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.meta.SystemSettingDto;
import com.netikras.studies.studentbuddy.core.data.sys.SystemService;
import com.netikras.studies.studentbuddy.core.data.sys.model.PasswordRequirement;
import com.netikras.studies.studentbuddy.core.data.sys.model.SystemSetting;
import com.netikras.studies.studentbuddy.core.meta.annotations.Authorizable;
import com.netikras.tools.common.model.mapper.MappingSettings;
import com.netikras.tools.common.model.mapper.ModelMapper;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

import static com.netikras.studies.studentbuddy.core.meta.Action.CREATE;
import static com.netikras.studies.studentbuddy.core.meta.Action.DELETE;
import static com.netikras.studies.studentbuddy.core.meta.Action.GET;
import static com.netikras.studies.studentbuddy.core.meta.Action.GET_ALL;
import static com.netikras.studies.studentbuddy.core.meta.Action.MODERATE;
import static com.netikras.studies.studentbuddy.core.meta.Action.MODIFY;
import static com.netikras.studies.studentbuddy.core.meta.Resource.SYSTEM_PWREQ;
import static com.netikras.studies.studentbuddy.core.meta.Resource.SYSTEM_SETTING;

@RestController
public class AdminProducer extends AdminApiProducer {

    @Resource
    private SystemService systemService;



    @Override
    @Authorizable(resource = SYSTEM_SETTING, action = GET)
    protected SystemSettingDto onRetrieveSystemSettingDto(String id) {
        SystemSetting setting = systemService.getSetting(id);
        SystemSettingDto dto = ModelMapper.transform(setting, new SystemSettingDto());
        return dto;
    }

    @Override
    @Authorizable(resource = SYSTEM_SETTING, action = CREATE)
    protected SystemSettingDto onCreateSystemSettingDto(SystemSettingDto item) {
        SystemSetting setting = ModelMapper.apply(new SystemSetting(), item, new MappingSettings().setForceUpdate(true));
        setting = systemService.createSetting(setting);
        item = ModelMapper.transform(setting, new SystemSettingDto());
        return item;
    }

    @Override
    @Authorizable(resource = SYSTEM_SETTING, action = MODIFY)
    protected SystemSettingDto onUpdateSystemSettingDto(SystemSettingDto item) {
        SystemSetting setting = ModelMapper.apply(new SystemSetting(), item);
        setting = systemService.updateSystemSetting(setting);
        item = ModelMapper.transform(setting, new SystemSettingDto());
        return item;
    }

    @Override
    @Authorizable(resource = SYSTEM_SETTING, action = DELETE)
    protected void onDeleteSystemSettingDto(String id) {
        systemService.deleteSystemSetting(id);
    }

    @Override
    @Authorizable(resource = SYSTEM_PWREQ, action = GET)
    protected PasswordRequirementDto onRetrievePasswordRequirementDto(String id) {
        PasswordRequirement requirement = systemService.getPasswordRequirement(id);
        PasswordRequirementDto dto = ModelMapper.transform(requirement, new PasswordRequirementDto());

        return dto;
    }

    @Override
    @Authorizable(resource = SYSTEM_PWREQ, action = CREATE)
    protected PasswordRequirementDto onCreatePasswordRequirementDto(PasswordRequirementDto item) {
        PasswordRequirement requirement = ModelMapper.apply(new PasswordRequirement(), item, new MappingSettings().setForceUpdate(true));
        requirement = systemService.createPasswordRequirement(requirement);
        item = ModelMapper.transform(requirement, new PasswordRequirementDto());
        return item;
    }

    @Override
    @Authorizable(resource = SYSTEM_PWREQ, action = MODIFY)
    protected PasswordRequirementDto onUpdatePasswordRequirementDto(PasswordRequirementDto item) {
        PasswordRequirement requirement = ModelMapper.apply(new PasswordRequirement(), item);
        requirement = systemService.updatePasswordRequirement(requirement);
        item = ModelMapper.transform(requirement, new PasswordRequirementDto());
        return item;
    }

    @Override
    @Authorizable(resource = SYSTEM_PWREQ, action = DELETE)
    protected void onDeletePasswordRequirementDto(String id) {
        systemService.deletePasswordRequirement(id);
    }




    @Override
    @Authorizable(resource = SYSTEM_SETTING, action = GET_ALL)
    protected List<SystemSettingDto> onGetSystemSettingDtoAllStored() {
        List<SystemSetting> settings = systemService.fetchSystemSettings();
        ModelMapper.transformAll(settings, SystemSettingDto.class);
        List<SystemSettingDto> settingDtos = (List<SystemSettingDto>) ModelMapper.transformAll(settings, SystemSettingDto.class);
        return settingDtos;
    }

    @Override
    @Authorizable(resource = SYSTEM_SETTING, action = GET_ALL)
    protected List<SystemSettingDto> onGetSystemSettingDtoAllLive() {
        List<SystemSetting> settings = systemService.getSystemSettings();
        ModelMapper.transformAll(settings, SystemSetting.class);
        List<SystemSettingDto> settingDtos = (List<SystemSettingDto>) ModelMapper.transformAll(settings, SystemSettingDto.class);
        return settingDtos;
    }

    @Override
    @Authorizable(resource = SYSTEM_SETTING, action = GET)
    protected SystemSettingDto onGetSystemSettingDtoLive(String name) {
        SystemSetting setting = systemService.getSettingByName(name);
        SystemSettingDto settingDto = ModelMapper.transform(setting, new SystemSettingDto());
        return settingDto;
    }

    @Override
    @Authorizable(resource = SYSTEM_SETTING, action = GET)
    protected SystemSettingDto onGetSystemSettingDtoStored(String name) {
        SystemSetting setting = systemService.getStoredSetting(name);
        SystemSettingDto settingDto = ModelMapper.transform(setting, new SystemSettingDto());
        return settingDto;
    }

    @Override
    @Authorizable(resource = SYSTEM_PWREQ, action = GET_ALL)
    protected List<PasswordRequirementDto> onGetPasswordRequirementDtoAllLive() {
        List<PasswordRequirement> requirements = systemService.fetchPasswordRequirements();
        List<PasswordRequirementDto> requirementDtos =
                (List<PasswordRequirementDto>) ModelMapper.transformAll(requirements, PasswordRequirementDto.class);
        return requirementDtos;
    }

    @Override
    @Authorizable(resource = SYSTEM_PWREQ, action = MODERATE)
    protected void onRefreshPasswordRequirementDtoLive() {
        systemService.refreshPaswordRequirements();
    }

    @Override
    @Authorizable(resource = SYSTEM_SETTING, action = MODERATE)
    protected void onRefreshSystemSettingDtoLive() {
        systemService.refreshSettings();
    }


    @Override
    @Authorizable(resource = SYSTEM_SETTING, action = DELETE)
    public void onDeleteSystemSettingDtoStoredByName(String name) {
        systemService.deleteSystemSettingByName(name);
    }
}
