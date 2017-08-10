package com.netikras.studies.studentbuddy.api.sys;

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

@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    @Resource
    private SystemService systemService;

    @RequestMapping(
            value = "/settings/live",
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
            value = "/settings",
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
            value = "/settings/name/{name}",
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
            value = "/settings",
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
            value = "/settings",
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
            value = "/settings/name/{name}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public void deleteSetting(
            @PathVariable(name = "name") String name
    ) {
        systemService.deleteSystemSetting(name);
    }


    @RequestMapping(
            value = "/pwrq/live",
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
            value = "/pwrq",
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
            value = "/pwrq",
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
            value = "/pwrq",
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
            value = "/pwrq/id/{id}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public void deletePasswordRequirement(
            @PathVariable(name = "id") String id
    ) {
        systemService.deletePasswordRequirement(id);
    }


    @RequestMapping(
            value = "/pwrq/refresh",
            method = RequestMethod.POST
    )
    @ResponseBody
    public void refreshPasswordRequirements() {
        systemService.refreshPaswordRequirements();
    }

    @RequestMapping(
            value = "/settings/refresh",
            method = RequestMethod.POST
    )
    @ResponseBody
    public void refreshSystemSettings() {
        systemService.refreshSettings();
    }

}
