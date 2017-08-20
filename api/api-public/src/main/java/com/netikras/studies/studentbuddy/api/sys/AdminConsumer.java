package com.netikras.studies.studentbuddy.api.sys;

import com.fasterxml.jackson.core.type.TypeReference;
import com.netikras.studies.studentbuddy.core.data.api.dto.meta.PasswordRequirementDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.meta.SystemSettingDto;
import com.netikras.tools.common.remote.http.GenericRestConsumer;
import com.netikras.tools.common.remote.http.HttpRequest;
import com.netikras.tools.common.remote.http.impl.json.HttpResponseJsonImpl;

import java.util.List;

import static com.netikras.studies.studentbuddy.api.constants.AdminConstants.endpointCreatePwReq;
import static com.netikras.studies.studentbuddy.api.constants.AdminConstants.endpointCreateSetting;
import static com.netikras.studies.studentbuddy.api.constants.AdminConstants.endpointDeletePwReqById;
import static com.netikras.studies.studentbuddy.api.constants.AdminConstants.endpointDeleteSettingByName;
import static com.netikras.studies.studentbuddy.api.constants.AdminConstants.endpointGetPwReqs;
import static com.netikras.studies.studentbuddy.api.constants.AdminConstants.endpointGetPwReqsLive;
import static com.netikras.studies.studentbuddy.api.constants.AdminConstants.endpointGetSettingLiveByName;
import static com.netikras.studies.studentbuddy.api.constants.AdminConstants.endpointGetSettingStoredByName;
import static com.netikras.studies.studentbuddy.api.constants.AdminConstants.endpointGetSettings;
import static com.netikras.studies.studentbuddy.api.constants.AdminConstants.endpointGetSettingsLive;
import static com.netikras.studies.studentbuddy.api.constants.AdminConstants.endpointRefreshPasswordRequirements;
import static com.netikras.studies.studentbuddy.api.constants.AdminConstants.endpointRefreshSettings;
import static com.netikras.studies.studentbuddy.api.constants.AdminConstants.endpointUpdatePwReq;
import static com.netikras.studies.studentbuddy.api.constants.AdminConstants.endpointUpdateSetting;

public class AdminConsumer extends GenericRestConsumer {

    public List<SystemSettingDto> getLiveSettings() {
        HttpRequest<SystemSettingDto> request = createRequest(endpointGetSettingsLive());

        List<SystemSettingDto> settings =
                (List<SystemSettingDto>) sendRequest(request, new HttpResponseJsonImpl(new TypeReference<List<SystemSettingDto>>() {}));
        return settings;
    }

    public List<SystemSettingDto> getSettings() {
        HttpRequest<SystemSettingDto> request = createRequest(endpointGetSettings());

        List<SystemSettingDto> settings =
                (List<SystemSettingDto>) sendRequest(request, new HttpResponseJsonImpl(new TypeReference<List<SystemSettingDto>>() {}));
        return settings;
    }

    public SystemSettingDto getLiveSetting(String name) {
        HttpRequest<SystemSettingDto> request = createRequest(endpointGetSettingLiveByName())
                .withExpectedType(SystemSettingDto.class)
                .setUrlProperty("name", name);

        SystemSettingDto setting = (SystemSettingDto) sendRequest(request);
        return setting;
    }

    public SystemSettingDto getStoredSetting(String name) {
        HttpRequest<SystemSettingDto> request = createRequest(endpointGetSettingStoredByName())
                .withExpectedType(SystemSettingDto.class)
                .setUrlProperty("name", name);

        SystemSettingDto setting = (SystemSettingDto) sendRequest(request);
        return setting;
    }

    public SystemSettingDto createSetting(SystemSettingDto settingDto) {
        HttpRequest<SystemSettingDto> request = createRequest(endpointCreateSetting())
                .withExpectedType(SystemSettingDto.class)
                .setObject(settingDto);

        SystemSettingDto setting = (SystemSettingDto) sendRequest(request);
        return setting;
    }

    public SystemSettingDto updateSetting(SystemSettingDto settingDto) {
        HttpRequest<SystemSettingDto> request = createRequest(endpointUpdateSetting())
                .withExpectedType(SystemSettingDto.class)
                .setObject(settingDto);

        SystemSettingDto setting = (SystemSettingDto) sendRequest(request);
        return setting;
    }

    public boolean deleteSetting(String name) {
        HttpRequest<SystemSettingDto> request = createRequest(endpointDeleteSettingByName())
                .setUrlProperty("name", name);

        HttpResponseJsonImpl responseJson = new HttpResponseJsonImpl(SystemSettingDto.class);
        sendRequest(request, responseJson);
        return responseJson.isResponseSuccess();
    }


    public boolean refreshSettings() {
        HttpRequest<SystemSettingDto> request = createRequest(endpointRefreshSettings());

        HttpResponseJsonImpl responseJson = new HttpResponseJsonImpl(SystemSettingDto.class);
        sendRequest(request, responseJson);
        return responseJson.isResponseSuccess();
    }


    // Password requirements

    public List<PasswordRequirementDto> getLivePwReqs() {
        HttpRequest<PasswordRequirementDto> request = createRequest(endpointGetPwReqsLive());

        List<PasswordRequirementDto> settings =
                (List<PasswordRequirementDto>) sendRequest(request, new HttpResponseJsonImpl(new TypeReference<List<PasswordRequirementDto>>() {}));
        return settings;
    }

    public List<PasswordRequirementDto> getStoredPwReqs() {
        HttpRequest<PasswordRequirementDto> request = createRequest(endpointGetPwReqs());

        List<PasswordRequirementDto> settings =
                (List<PasswordRequirementDto>) sendRequest(request, new HttpResponseJsonImpl(new TypeReference<List<PasswordRequirementDto>>() {}));
        return settings;
    }

    public PasswordRequirementDto createPwReq(PasswordRequirementDto requirementDto) {
        HttpRequest<PasswordRequirementDto> request = createRequest(endpointCreatePwReq())
                .withExpectedType(PasswordRequirementDto.class)
                .setObject(requirementDto);

        PasswordRequirementDto dto = (PasswordRequirementDto) sendRequest(request);
        return dto;
    }

    public PasswordRequirementDto updatePwReq(PasswordRequirementDto requirementDto) {
        HttpRequest<PasswordRequirementDto> request = createRequest(endpointUpdatePwReq())
                .withExpectedType(PasswordRequirementDto.class)
                .setObject(requirementDto);

        PasswordRequirementDto dto = (PasswordRequirementDto) sendRequest(request);
        return dto;
    }

    public boolean deletePwReq(String id) {
        HttpRequest<PasswordRequirementDto> request = createRequest(endpointDeletePwReqById())
                .setUrlProperty("id", id);

        HttpResponseJsonImpl responseJson = new HttpResponseJsonImpl(PasswordRequirementDto.class);
        sendRequest(request, responseJson);

        return responseJson.isResponseSuccess();
    }

    public boolean refreshPwReqs() {
        HttpRequest<PasswordRequirementDto> request = createRequest(endpointRefreshPasswordRequirements());

        HttpResponseJsonImpl responseJson = new HttpResponseJsonImpl(PasswordRequirementDto.class);
        sendRequest(request, responseJson);

        return responseJson.isResponseSuccess();
    }

}
