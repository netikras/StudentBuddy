package com.netikras.studies.studentbuddy.api.sys.producer;

import com.netikras.studies.studentbuddy.api.sys.generated.AdminApiProducer;
import com.netikras.studies.studentbuddy.api.sys.producer.impl.secured.AdminProducerImpl;
import com.netikras.studies.studentbuddy.core.data.api.dto.meta.PasswordRequirementDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.meta.SystemSettingDto;
import com.netikras.studies.studentbuddy.core.data.meta.annotations.Authorizable;
import com.netikras.studies.studentbuddy.core.data.sys.model.PasswordRequirement;
import com.netikras.studies.studentbuddy.core.data.sys.model.SystemSetting;
import com.netikras.studies.studentbuddy.core.service.SystemService;
import com.netikras.tools.common.model.mapper.MappingSettings;
import com.netikras.tools.common.model.mapper.ModelMapper;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

import static com.netikras.studies.studentbuddy.core.data.meta.Action.CREATE;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.DELETE;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.GET;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.GET_ALL;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.MODERATE;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.MODIFY;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.SYSTEM_PWREQ;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.SYSTEM_SETTING;

@RestController
public class AdminProducer extends AdminApiProducer {

    @Resource
    private AdminProducerImpl impl;



    @Override
    protected SystemSettingDto onRetrieveSystemSettingDto(String id) {
        return impl.getSystemSetting(id);
    }

    @Override
    protected SystemSettingDto onCreateSystemSettingDto(SystemSettingDto item) {
        return impl.createSystemSetting(item);
    }

    @Override
    protected SystemSettingDto onUpdateSystemSettingDto(SystemSettingDto item) {
        return impl.updateSystemSetting(item);
    }

    @Override
    protected void onDeleteSystemSettingDto(String id) {
        impl.deleteSystemSetting(id);
    }

    @Override
    protected PasswordRequirementDto onRetrievePasswordRequirementDto(String id) {
        return impl.getPasswordRequirement(id);
    }

    @Override
    protected PasswordRequirementDto onCreatePasswordRequirementDto(PasswordRequirementDto item) {
        return impl.createPasswordRequirement(item);
    }

    @Override
    protected PasswordRequirementDto onUpdatePasswordRequirementDto(PasswordRequirementDto item) {
        return impl.updatePasswordRequirement(item);
    }

    @Override
    protected void onDeletePasswordRequirementDto(String id) {
        impl.deletePasswordRequirement(id);
    }




    @Override
    public List<SystemSettingDto> onGetSystemSettingDtoAllStored() {
        return impl.getAllStoredSystemSettings();
    }

    @Override
    protected List<SystemSettingDto> onGetSystemSettingDtoAllLive() {
        return impl.getAllLiveSystemSettings();
    }

    @Override
    protected SystemSettingDto onGetSystemSettingDtoLive(String name) {
        return impl.getLiveSystemSetting(name);
    }

    @Override
    protected SystemSettingDto onGetSystemSettingDtoStored(String name) {
        return impl.getStoredSystemSetting(name);
    }

    @Override
    protected List<PasswordRequirementDto> onGetPasswordRequirementDtoAllLive() {
        return impl.getAllLivePasswordRequirements();
    }

    @Override
    protected void onRefreshPasswordRequirementDtoLive() {
        impl.refreshLivePasswordRequirements();
    }

    @Override
    protected void onRefreshSystemSettingDtoLive() {
        impl.refreshLiveSystemSettings();
    }


    @Override
    public void onDeleteSystemSettingDtoStoredByName(String name) {
        impl.deleteStoredSystemSettingByName(name);
    }
}
