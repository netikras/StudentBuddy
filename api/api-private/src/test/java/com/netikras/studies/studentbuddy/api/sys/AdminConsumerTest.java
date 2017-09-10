package com.netikras.studies.studentbuddy.api.sys;


import com.netikras.studies.studentbuddy.api.GenericConsumerTest;
import com.netikras.studies.studentbuddy.api.sys.generated.AdminApiConsumer;
import com.netikras.studies.studentbuddy.core.data.api.dto.meta.PasswordRequirementDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.meta.SystemSettingDto;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class AdminConsumerTest extends GenericConsumerTest {

    private static final Logger logger = LoggerFactory.getLogger(AdminConsumerTest.class);

    public AdminConsumerTest() {
//        super(logger);
    }

    private AdminApiConsumer adminConsumer;


    @Before
    public void initGenericConsumer() {
        super.initGenericConsumer();
        adminConsumer = new AdminApiConsumer();
        adminConsumer.setSessionContext(getSessionContext());
        adminConsumer.addServer("default", server);
        adminConsumer.addListener("default", responseListener);
    }


    private SystemSettingDto buildSetting() {
        SystemSettingDto settingDto = new SystemSettingDto();
        settingDto.setName("bunniesCount");
        settingDto.setValue("5000");

        return settingDto;
    }

    private PasswordRequirementDto buildPwReq() {
        PasswordRequirementDto requirementDto = new PasswordRequirementDto();
        requirementDto.setEnabled(true);
        requirementDto.setTitle("Digits only");
        requirementDto.setWhitelist("0123456789");
        requirementDto.setCountMin(1);
        requirementDto.setCountMax(10);
        requirementDto.setMessage("Only digits are allowed");

        return requirementDto;
    }

    PasswordRequirementDto findRequirement(String title, List<PasswordRequirementDto> requirementDtos) {
        PasswordRequirementDto dto = null;

        if (requirementDtos == null) return dto;
        if (title == null || title.isEmpty()) return dto;
        for (PasswordRequirementDto requirementDto : requirementDtos) {
            if (title.equals(requirementDto.getTitle())) {
                return requirementDto;
            }
        }

        return dto;
    }

    @Test
    public void getSystemSettingDtoAllLiveTest() throws Exception {
        loginSystem();
        List<SystemSettingDto> settingDtos = adminConsumer.getSystemSettingDtoAllLive();
        if (settingDtos == null || settingDtos.isEmpty()) {
            logger.info("Creating setting entry as it does not yet exist");
            adminConsumer.createSystemSettingDto(buildSetting());
            adminConsumer.refreshSystemSettingDtoLive();
            pause(1000);
            settingDtos = adminConsumer.getSystemSettingDtoAllLive();
        }
        logger.info("Live settings: {}", settingDtos);
        assertNotNull("There should be at least one live setting", settingDtos);
        assertFalse("There should be at least one live setting in the list", settingDtos.isEmpty());
        adminConsumer.deleteSystemSettingDtoStoredByName(buildSetting().getName());
        adminConsumer.refreshSystemSettingDtoLive();
    }

    @Test
    public void getSettingsTest() throws Exception {
        loginSystem();
        List<SystemSettingDto> settingDtos = adminConsumer.getSystemSettingDtoAllStored();
        if (settingDtos == null || settingDtos.isEmpty()) {
            logger.info("Creating setting entry as it does not yet exist");
            adminConsumer.createSystemSettingDto(buildSetting());
            settingDtos = adminConsumer.getSystemSettingDtoAllStored();
        }
        logger.info("Stored settings: {}", settingDtos);
        assertNotNull("There should be at least one stored setting", settingDtos);
        assertFalse("There should be at least one stored setting in the list", settingDtos.isEmpty());
        adminConsumer.deleteSystemSettingDtoStoredByName(buildSetting().getName());
    }

    @Test
    public void getSystemSettingDtoLiveTest() throws Exception {
        loginSystem();
        SystemSettingDto settingDto = buildSetting();
        settingDto = adminConsumer.getSystemSettingDtoLive(settingDto.getName());

        if (settingDto == null) {
            logger.info("Creating setting entry as it does not yet exist");
            settingDto = buildSetting();
            settingDto = adminConsumer.createSystemSettingDto(settingDto);
            adminConsumer.refreshSystemSettingDtoLive();
            pause(1000);
            settingDto = adminConsumer.getSystemSettingDtoLive(settingDto.getName());
        }

        logger.info("Retrieved setting: {}", settingDto);
        assertNotNull("Setting shall not be null", settingDto);
        adminConsumer.deleteSystemSettingDto(settingDto.getName());
        adminConsumer.refreshSystemSettingDtoLive();
    }

    @Test
    public void getSystemSettingDtoStoredTest() throws Exception {
        loginSystem();
        SystemSettingDto settingDto = adminConsumer.getSystemSettingDtoStored(buildSetting().getName());
        if (settingDto == null) {
            settingDto = adminConsumer.createSystemSettingDto(buildSetting());
            settingDto = adminConsumer.getSystemSettingDtoStored(settingDto.getName());
        }

        assertNotNull("Stored setting DTO must not be null", settingDto);
        adminConsumer.deleteSystemSettingDtoStoredByName(settingDto.getName());
    }

    @Test
    public void createSystemSettingDtoTest() throws Exception {
        loginSystem();
        SystemSettingDto settingDto;

        settingDto = buildSetting();

        settingDto = adminConsumer.createSystemSettingDto(settingDto);
        logger.info("New setting: {}", settingDto);
        List<SystemSettingDto> settingDtos = adminConsumer.getSystemSettingDtoAllLive();
        logger.info("Live settings: {}", settingDtos);

        adminConsumer.refreshSystemSettingDtoLive();
        settingDtos = adminConsumer.getSystemSettingDtoAllLive();
        logger.info("Live settings: {}", settingDtos);
    }

    @Test
    public void updateSystemSettingDtoTest() throws Exception {
        loginSystem();

        String newValue = "awesome!";
        SystemSettingDto settingDto = adminConsumer.getSystemSettingDtoLive(buildSetting().getName());
        if (settingDto == null) {
            logger.info("Creating setting entry as it does not yet exist");
            adminConsumer.createSystemSettingDto(buildSetting());
            adminConsumer.refreshSystemSettingDtoLive();
            pause(1000);
            settingDto = adminConsumer.getSystemSettingDtoLive(buildSetting().getName());
        }

        settingDto.setValue(newValue);
        settingDto = adminConsumer.updateSystemSettingDto(settingDto);

        logger.info("Updated setting: {}", settingDto);
        assertEquals("Updated setting value should have changed", newValue, settingDto.getValue());
        adminConsumer.deleteSystemSettingDtoStoredByName(settingDto.getName());
        adminConsumer.refreshSystemSettingDtoLive();
        settingDto = adminConsumer.updateSystemSettingDto(settingDto);
        assertNull("Must not allow updating non-existent settings", settingDto);
    }

    @Test
    public void deleteSystemSettingDtoTest() throws Exception {
        loginSystem();
        SystemSettingDto settingDto = buildSetting();
        settingDto = adminConsumer.getSystemSettingDtoLive(settingDto.getName());
        if (settingDto == null) {
            settingDto = adminConsumer.createSystemSettingDto(buildSetting());
            adminConsumer.refreshSystemSettingDtoLive();
            pause(1000);
            settingDto = adminConsumer.getSystemSettingDtoLive(settingDto.getName());
        }
        assertNotNull("Setting DTO must have been created for deletion", settingDto);
        adminConsumer.deleteSystemSettingDtoStoredByName(settingDto.getName());
        settingDto = adminConsumer.getSystemSettingDtoLive(settingDto.getName());
        assertNotNull("Setting DTO must still be available in Live after deletion", settingDto);
        settingDto = adminConsumer.getSystemSettingDtoStored(settingDto.getName());
        assertNull("Setting DTO must be unavailable in Stored after deletion", settingDto);
        adminConsumer.refreshSystemSettingDtoLive();
        pause(1000);
        settingDto = adminConsumer.getSystemSettingDtoLive(buildSetting().getName());
        assertNull("Setting must be gone after it's been deleted", settingDto);
    }

    @Test
    public void refreshSystemSettingDtoLiveTest() throws Exception {
        loginSystem();
        SystemSettingDto settingDto = adminConsumer.getSystemSettingDtoLive(buildSetting().getName());
        if (settingDto != null) {
            adminConsumer.deleteSystemSettingDtoStoredByName(settingDto.getName());
            adminConsumer.refreshSystemSettingDtoLive();
            pause(1000);
            settingDto = adminConsumer.getSystemSettingDtoLive(buildSetting().getName());
            assertNull("After removal and refresh setting must be no longer in Live", settingDto);
        } else {
            settingDto = adminConsumer.createSystemSettingDto(buildSetting());
            adminConsumer.refreshSystemSettingDtoLive();
            pause(1000);
            settingDto = adminConsumer.getSystemSettingDtoLive(settingDto.getName());
            assertNotNull("Newly created setting must be in Live after refresh", settingDto);
            adminConsumer.deleteSystemSettingDto(settingDto.getName());
            adminConsumer.refreshSystemSettingDtoLive();
        }
    }

    @Test
    public void getPasswordRequirementDtoAllLiveTest() throws Exception {
        loginSystem();
        List<PasswordRequirementDto> requirementDtos = adminConsumer.getPasswordRequirementDtoAllLive();
        PasswordRequirementDto dto = null;
        if (requirementDtos == null || requirementDtos.isEmpty()) {
            adminConsumer.refreshPasswordRequirementDtoLive();
            adminConsumer.getPasswordRequirementDtoAllLive();
            pause(1000);
            requirementDtos = adminConsumer.getPasswordRequirementDtoAllLive();
        }

        logger.info("Live password requirements: {}", requirementDtos);
        assertNotNull("There must already be at least one Live password requirement", requirementDtos);
        assertFalse("There must already be at least one Live password requirement in the list", requirementDtos.isEmpty());
        if (dto == null) {
            dto = findRequirement(buildPwReq().getTitle(), requirementDtos);
        }
        if (dto != null) {
            adminConsumer.deletePasswordRequirementDto(dto.getId());
            adminConsumer.refreshPasswordRequirementDtoLive();
        }
    }

    @Test
    public void getPwReqsTest() throws Exception {
        loginSystem();
        List<PasswordRequirementDto> requirementDtos = adminConsumer.getPasswordRequirementDtoAllLive();
        PasswordRequirementDto dto = null;
        if (requirementDtos == null || requirementDtos.isEmpty()) {
            dto = adminConsumer.createPasswordRequirementDto(buildPwReq());
            adminConsumer.refreshPasswordRequirementDtoLive();
            requirementDtos = adminConsumer.getPasswordRequirementDtoAllLive();
        }

        logger.info("Live password requirements: {}", requirementDtos);
        assertNotNull("There must already be at least one Stored password requirement", requirementDtos);
        assertFalse("There must already be at least one Stored password requirement in the list", requirementDtos.isEmpty());

        if (dto == null) {
            dto = findRequirement(buildPwReq().getTitle(), requirementDtos);
        }
        if (dto != null) {
            adminConsumer.deletePasswordRequirementDto(dto.getId());
        }
    }

    @Test
    public void createPasswordRequirementDtoTest() throws Exception {
        loginSystem();
        List<PasswordRequirementDto> requirementDtos = adminConsumer.getPasswordRequirementDtoAllStored();
        if (requirementDtos == null || requirementDtos.isEmpty()) {
            adminConsumer.createPasswordRequirementDto(buildPwReq());
            requirementDtos = adminConsumer.getPasswordRequirementDtoAllStored();
        }

        PasswordRequirementDto dto = findRequirement(buildPwReq().getTitle(), requirementDtos);

        if (dto != null) {
            adminConsumer.deletePasswordRequirementDto(dto.getId());
        }

        dto = adminConsumer.createPasswordRequirementDto(buildPwReq());
        logger.info("Newly created password requirement: {}", dto);
        assertNotNull("New password requirement must not be null", dto);
        adminConsumer.deletePasswordRequirementDto(dto.getId());
    }

    @Test
    public void updatePasswordRequirementDtoTest() throws Exception {
        loginSystem();

        String newValue = "456";
        List<PasswordRequirementDto> requirementDtos = adminConsumer.getPasswordRequirementDtoAllStored();
        if (requirementDtos == null || requirementDtos.isEmpty()) {
            adminConsumer.createPasswordRequirementDto(buildPwReq());
            requirementDtos = adminConsumer.getPasswordRequirementDtoAllStored();
        }

        PasswordRequirementDto dto = findRequirement(buildPwReq().getTitle(), requirementDtos);

        if (dto == null) {
            dto = adminConsumer.createPasswordRequirementDto(buildPwReq());
        }
        dto.setWhitelist(newValue);
        dto = adminConsumer.updatePasswordRequirementDto(dto);
        logger.info("Updated pw req: {}", dto);
        assertEquals("Updated pw req must have adopted a new value", newValue, dto.getWhitelist());

        adminConsumer.deletePasswordRequirementDto(dto.getId());
    }

    @Test
    public void deletePasswordRequirementDtoTest() throws Exception {
        loginSystem();
        List<PasswordRequirementDto> requirementDtos = adminConsumer.getPasswordRequirementDtoAllStored();
        if (requirementDtos == null || requirementDtos.isEmpty()) {
            adminConsumer.createPasswordRequirementDto(buildPwReq());
            requirementDtos = adminConsumer.getPasswordRequirementDtoAllStored();
        }

        PasswordRequirementDto dto = findRequirement(buildPwReq().getTitle(), requirementDtos);

        if (dto == null) {
            dto = adminConsumer.createPasswordRequirementDto(buildPwReq());
        }

        assertNotNull("Pw req must be available in Stored list", dto);
        adminConsumer.deletePasswordRequirementDto(dto.getId());

        requirementDtos = adminConsumer.getPasswordRequirementDtoAllStored();
        dto = findRequirement(buildPwReq().getTitle(), requirementDtos);
        assertNull("PwReq must be removed by now", dto);

    }

    @Test
    public void refreshPasswordRequirementDtoLiveTest() throws Exception {
        loginSystem();

        List<PasswordRequirementDto> requirementDtos = adminConsumer.getPasswordRequirementDtoAllLive();
        PasswordRequirementDto dto = findRequirement(buildPwReq().getTitle(), requirementDtos);

        if (dto == null) {
            dto = adminConsumer.createPasswordRequirementDto(buildPwReq());
            adminConsumer.refreshPasswordRequirementDtoLive();
            pause(1000);
            requirementDtos = adminConsumer.getPasswordRequirementDtoAllLive();
            dto = findRequirement(buildPwReq().getTitle(), requirementDtos);
            logger.info("PwReq after creation and refresh: {}", dto);
            assertNotNull("PwReq must be in Live after its created and refreshed", dto);
            adminConsumer.deletePasswordRequirementDto(dto.getId());
        } else {
            adminConsumer.deletePasswordRequirementDto(dto.getId());
            adminConsumer.refreshPasswordRequirementDtoLive();
            pause(1000);
            requirementDtos = adminConsumer.getPasswordRequirementDtoAllLive();
            dto = findRequirement(buildPwReq().getTitle(), requirementDtos);
            logger.info("PwReq after deletion and refresh: {}", dto);
            assertNull("PwReq must be no longer in Live after its deleted and refreshed", dto);
        }
    }

}
