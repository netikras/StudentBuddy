package com.netikras.studies.studentbuddy.api.sys;

import com.netikras.studies.studentbuddy.api.constants.AdminConstants;
import com.netikras.studies.studentbuddy.api.filters.ThreadContext;
import com.netikras.studies.studentbuddy.core.data.sys.SystemService;
import com.netikras.studies.studentbuddy.core.data.sys.model.PasswordRequirement;
import com.netikras.studies.studentbuddy.core.data.sys.model.SystemSetting;
import com.netikras.studies.studentbuddy.core.data.sys.model.User;
import com.netikras.studies.studentbuddy.core.data.api.dto.meta.PasswordRequirementDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.meta.SystemSettingDto;
import com.netikras.tools.common.model.mapper.ModelMapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

import static com.netikras.studies.studentbuddy.api.constants.AdminConstants.ADM_URL_CREATE_PASSWORD_REQ;
import static com.netikras.studies.studentbuddy.api.constants.AdminConstants.ADM_URL_CREATE_SETTING;
import static com.netikras.studies.studentbuddy.api.constants.AdminConstants.ADM_URL_DELETE_PASSWORD_REQ_BY_ID;
import static com.netikras.studies.studentbuddy.api.constants.AdminConstants.ADM_URL_DELETE_SETTING_BY_NAME;
import static com.netikras.studies.studentbuddy.api.constants.AdminConstants.ADM_URL_GET_PASSWORD_REQS;
import static com.netikras.studies.studentbuddy.api.constants.AdminConstants.ADM_URL_GET_PASSWORD_REQS_LIVE;
import static com.netikras.studies.studentbuddy.api.constants.AdminConstants.ADM_URL_GET_SETTINGS;
import static com.netikras.studies.studentbuddy.api.constants.AdminConstants.ADM_URL_GET_SETTINGS_LIVE;
import static com.netikras.studies.studentbuddy.api.constants.AdminConstants.ADM_URL_GET_SETTING_LIVE_BY_NAME;
import static com.netikras.studies.studentbuddy.api.constants.AdminConstants.ADM_URL_REFRESH_PASSWORD_REQS;
import static com.netikras.studies.studentbuddy.api.constants.AdminConstants.ADM_URL_REFRESH_SETTINGS;
import static com.netikras.studies.studentbuddy.api.constants.AdminConstants.ADM_URL_UPDATE_PASSWORD_REQ;
import static com.netikras.studies.studentbuddy.api.constants.AdminConstants.ADM_URL_UPDATE_SETTING;

@RestController
@RequestMapping(value = AdminConstants.BASE_URL)
public class AdminController {

    @Resource
    private SystemService systemService;

    @RequestMapping(
            value = ADM_URL_GET_SETTINGS_LIVE,
            method = RequestMethod.GET
    )
    @ResponseBody
    public List<SystemSettingDto> getLiveSettings() {
        List<SystemSetting> settings = systemService.getSystemSettings();
        ModelMapper.transformAll(settings, SystemSetting.class);
        List<SystemSettingDto> settingDtos = (List<SystemSettingDto>) ModelMapper.transformAll(settings, SystemSettingDto.class);
        return settingDtos;
    }

    @RequestMapping(
            value = ADM_URL_GET_SETTINGS,
            method = RequestMethod.GET
    )
    @ResponseBody
    public List<SystemSettingDto> getSettings() {
        List<SystemSetting> settings = systemService.fetchSystemSettings();
        ModelMapper.transformAll(settings, SystemSettingDto.class);
        List<SystemSettingDto> settingDtos = (List<SystemSettingDto>) ModelMapper.transformAll(settings, SystemSettingDto.class);
        return settingDtos;
    }

    @RequestMapping(
            value = ADM_URL_GET_SETTING_LIVE_BY_NAME,
            method = RequestMethod.GET
    )
    @ResponseBody
    public SystemSettingDto getSettingByName(
            @PathVariable(name = "name") String name
    ) {
        SystemSetting setting = systemService.getSetting(name);
        SystemSettingDto settingDto = ModelMapper.transform(setting, new SystemSettingDto());
        return settingDto;
    }

    @RequestMapping(
            value = ADM_URL_CREATE_SETTING,
            method = RequestMethod.POST
    )
    @ResponseBody
    public SystemSettingDto createSetting(
            @RequestBody SystemSettingDto settingDto
    ) {
        SystemSetting setting = ModelMapper.apply(new SystemSetting(), settingDto);
        User creator = ThreadContext.current().getUser();
        setting.setModifiedBy(creator);
        setting = systemService.createSetting(setting);
        settingDto = ModelMapper.transform(setting, new SystemSettingDto());
        return settingDto;
    }

    @RequestMapping(
            value = ADM_URL_UPDATE_SETTING,
            method = RequestMethod.PUT
    )
    @ResponseBody
    public SystemSettingDto updateSetting(
            @RequestBody SystemSettingDto settingDto
    ) {
        SystemSetting setting = ModelMapper.apply(new SystemSetting(), settingDto);
        User creator = ThreadContext.current().getUser();
        setting.setModifiedBy(creator);
        setting = systemService.updateSystemSetting(setting);

        settingDto = ModelMapper.transform(setting, new SystemSettingDto());
        return settingDto;
    }

    @RequestMapping(
            value = ADM_URL_DELETE_SETTING_BY_NAME,
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public void deleteSetting(
            @PathVariable(name = "name") String name
    ) {
        systemService.deleteSystemSetting(name);
    }


    @RequestMapping(
            value = ADM_URL_GET_PASSWORD_REQS_LIVE,
            method = RequestMethod.GET
    )
    @ResponseBody
    public List<PasswordRequirementDto> getLivePasswordRequirements() {
        List<PasswordRequirement> requirements = systemService.getPasswordRequirements();
        List<PasswordRequirementDto> requirementDtos =
                (List<PasswordRequirementDto>) ModelMapper.transformAll(requirements, PasswordRequirementDto.class);
        return requirementDtos;
    }

    @RequestMapping(
            value = ADM_URL_GET_PASSWORD_REQS,
            method = RequestMethod.GET
    )
    @ResponseBody
    public List<PasswordRequirementDto> getPasswordRequirements() {
        List<PasswordRequirement> requirements = systemService.fetchPasswordRequirements();
        List<PasswordRequirementDto> requirementDtos =
                (List<PasswordRequirementDto>) ModelMapper.transformAll(requirements, PasswordRequirementDto.class);
        return requirementDtos;
    }





    @RequestMapping(
            value = ADM_URL_CREATE_PASSWORD_REQ,
            method = RequestMethod.POST
    )
    @ResponseBody
    public PasswordRequirementDto createPasswordRequirement(
            @RequestBody PasswordRequirementDto requirementDto
    ) {
        PasswordRequirement requirement = ModelMapper.apply(new PasswordRequirement(), requirementDto);
        requirement = systemService.createPasswordRequirement(requirement);
        requirementDto = ModelMapper.transform(requirement, new PasswordRequirementDto());
        return requirementDto;
    }

    @RequestMapping(
            value = ADM_URL_UPDATE_PASSWORD_REQ,
            method = RequestMethod.PUT
    )
    @ResponseBody
    public PasswordRequirementDto updatePasswordRequirement(
            @RequestBody PasswordRequirementDto requirementDto
    ) {
        PasswordRequirement requirement = ModelMapper.apply(new PasswordRequirement(), requirementDto);
        requirement = systemService.updatePasswordRequirement(requirement);
        requirementDto = ModelMapper.transform(requirement, new PasswordRequirementDto());
        return requirementDto;
    }

    @RequestMapping(
            value = ADM_URL_DELETE_PASSWORD_REQ_BY_ID,
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public void deletePasswordRequirement(
            @PathVariable(name = "id") String id
    ) {
        systemService.deletePasswordRequirement(id);
    }


    @RequestMapping(
            value = ADM_URL_REFRESH_PASSWORD_REQS,
            method = RequestMethod.POST
    )
    @ResponseBody
    public void refreshPasswordRequirements() {
        systemService.refreshPaswordRequirements();
    }

    @RequestMapping(
            value = ADM_URL_REFRESH_SETTINGS,
            method = RequestMethod.POST
    )
    @ResponseBody
    public void refreshSystemSettings() {
        systemService.refreshSettings();
    }

}
