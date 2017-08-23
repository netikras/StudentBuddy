package com.netikras.studies.studentbuddy.api.sys;


import com.netikras.studies.studentbuddy.api.GenericConsumerTest;
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

    private AdminConsumer adminConsumer;


    @Before
    public void initGenericConsumer() {
        super.initGenericConsumer();
        adminConsumer = new AdminConsumer();
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
    public void getLiveSettingsTest() throws Exception {
        loginSystem();
        List<SystemSettingDto> settingDtos = adminConsumer.getLiveSettings();
        if (settingDtos == null || settingDtos.isEmpty()) {
            logger.info("Creating setting entry as it does not yet exist");
            adminConsumer.createSetting(buildSetting());
            adminConsumer.refreshSettings();
            pause(1000);
            settingDtos = adminConsumer.getLiveSettings();
        }
        logger.info("Live settings: {}", settingDtos);
        assertNotNull("There should be at least one live setting", settingDtos);
        assertFalse("There should be at least one live setting in the list", settingDtos.isEmpty());
        adminConsumer.deleteSetting(buildSetting().getName());
        adminConsumer.refreshSettings();
    }

    @Test
    public void getSettingsTest() throws Exception {
        loginSystem();
        List<SystemSettingDto> settingDtos = adminConsumer.getSettings();
        if (settingDtos == null || settingDtos.isEmpty()) {
            logger.info("Creating setting entry as it does not yet exist");
            adminConsumer.createSetting(buildSetting());
            settingDtos = adminConsumer.getSettings();
        }
        logger.info("Stored settings: {}", settingDtos);
        assertNotNull("There should be at least one stored setting", settingDtos);
        assertFalse("There should be at least one stored setting in the list", settingDtos.isEmpty());
        adminConsumer.deleteSetting(buildSetting().getName());
    }

    @Test
    public void getLiveSettingTest() throws Exception {
        loginSystem();
        SystemSettingDto settingDto = buildSetting();
        settingDto = adminConsumer.getLiveSetting(settingDto.getName());

        if (settingDto == null) {
            logger.info("Creating setting entry as it does not yet exist");
            settingDto = buildSetting();
            settingDto = adminConsumer.createSetting(settingDto);
            adminConsumer.refreshSettings();
            pause(1000);
            settingDto = adminConsumer.getLiveSetting(settingDto.getName());
        }

        logger.info("Retrieved setting: {}", settingDto);
        assertNotNull("Setting shall not be null", settingDto);
        adminConsumer.deleteSetting(settingDto.getName());
        adminConsumer.refreshSettings();
    }

    @Test
    public void getStoredSettingTest() throws Exception {
        loginSystem();
        SystemSettingDto settingDto = adminConsumer.getStoredSetting(buildSetting().getName());
        if (settingDto == null) {
            settingDto = adminConsumer.createSetting(buildSetting());
            settingDto = adminConsumer.getStoredSetting(settingDto.getName());
        }

        assertNotNull("Stored setting DTO must not be null", settingDto);
        adminConsumer.deleteSetting(settingDto.getName());
    }

    @Test
    public void createSettingTest() throws Exception {
        loginSystem();
        SystemSettingDto settingDto;

        settingDto = buildSetting();

        settingDto = adminConsumer.createSetting(settingDto);
        logger.info("New setting: {}", settingDto);
        List<SystemSettingDto> settingDtos = adminConsumer.getLiveSettings();
        logger.info("Live settings: {}", settingDtos);

        adminConsumer.refreshSettings();
        settingDtos = adminConsumer.getLiveSettings();
        logger.info("Live settings: {}", settingDtos);
    }

    @Test
    public void updateSettingTest() throws Exception {
        loginSystem();

        String newValue = "awesome!";
        SystemSettingDto settingDto = adminConsumer.getLiveSetting(buildSetting().getName());
        if (settingDto == null) {
            logger.info("Creating setting entry as it does not yet exist");
            adminConsumer.createSetting(buildSetting());
            adminConsumer.refreshSettings();
            pause(1000);
            settingDto = adminConsumer.getLiveSetting(buildSetting().getName());
        }

        settingDto.setValue(newValue);
        settingDto = adminConsumer.updateSetting(settingDto);

        logger.info("Updated setting: {}", settingDto);
        assertEquals("Updated setting value should have changed", newValue, settingDto.getValue());
        adminConsumer.deleteSetting(settingDto.getName());
        adminConsumer.refreshSettings();
        settingDto = adminConsumer.updateSetting(settingDto);
        assertNull("Must not allow updating non-existent settings", settingDto);
    }

    @Test
    public void deleteSettingTest() throws Exception {
        loginSystem();
        SystemSettingDto settingDto = buildSetting();
        settingDto = adminConsumer.getLiveSetting(settingDto.getName());
        if (settingDto == null) {
            settingDto = adminConsumer.createSetting(buildSetting());
            adminConsumer.refreshSettings();
            pause(1000);
            settingDto = adminConsumer.getLiveSetting(settingDto.getName());
        }
        assertNotNull("Setting DTO must have been created for deletion", settingDto);
        adminConsumer.deleteSetting(settingDto.getName());
        settingDto = adminConsumer.getLiveSetting(settingDto.getName());
        assertNotNull("Setting DTO must still be available in Live after deletion", settingDto);
        settingDto = adminConsumer.getStoredSetting(settingDto.getName());
        assertNull("Setting DTO must be unavailable in Stored after deletion", settingDto);
        adminConsumer.refreshSettings();
        pause(1000);
        settingDto = adminConsumer.getLiveSetting(buildSetting().getName());
        assertNull("Setting must be gone after it's been deleted", settingDto);
    }

    @Test
    public void refreshSettingsTest() throws Exception {
        loginSystem();
        SystemSettingDto settingDto = adminConsumer.getLiveSetting(buildSetting().getName());
        if (settingDto != null) {
            adminConsumer.deleteSetting(settingDto.getName());
            adminConsumer.refreshSettings();
            pause(1000);
            settingDto = adminConsumer.getLiveSetting(buildSetting().getName());
            assertNull("After removal and refresh setting must be no longer in Live", settingDto);
        } else {
            settingDto = adminConsumer.createSetting(buildSetting());
            adminConsumer.refreshSettings();
            pause(1000);
            settingDto = adminConsumer.getLiveSetting(settingDto.getName());
            assertNotNull("Newly created setting must be in Live after refresh", settingDto);
            adminConsumer.deleteSetting(settingDto.getName());
            adminConsumer.refreshSettings();
        }
    }

    @Test
    public void getLivePwReqsTest() throws Exception {
        loginSystem();
        List<PasswordRequirementDto> requirementDtos = adminConsumer.getLivePwReqs();
        PasswordRequirementDto dto = null;
        if (requirementDtos == null || requirementDtos.isEmpty()) {
            dto = adminConsumer.createPwReq(buildPwReq());
            adminConsumer.refreshPwReqs();
            pause(1000);
            requirementDtos = adminConsumer.getLivePwReqs();
        }

        logger.info("Live password requirements: {}", requirementDtos);
        assertNotNull("There must already be at least one Live password requirement", requirementDtos);
        assertFalse("There must already be at least one Live password requirement in the list", requirementDtos.isEmpty());
        if (dto == null) {
            dto = findRequirement(buildPwReq().getTitle(), requirementDtos);
        }
        if (dto != null) {
            adminConsumer.deletePwReq(dto.getId());
            adminConsumer.refreshPwReqs();
        }
    }

    @Test
    public void getPwReqsTest() throws Exception {
        loginSystem();
        List<PasswordRequirementDto> requirementDtos = adminConsumer.getStoredPwReqs();
        PasswordRequirementDto dto = null;
        if (requirementDtos == null || requirementDtos.isEmpty()) {
            dto = adminConsumer.createPwReq(buildPwReq());
            requirementDtos = adminConsumer.getStoredPwReqs();
        }

        logger.info("Live password requirements: {}", requirementDtos);
        assertNotNull("There must already be at least one Stored password requirement", requirementDtos);
        assertFalse("There must already be at least one Stored password requirement in the list", requirementDtos.isEmpty());

        if (dto == null) {
            dto = findRequirement(buildPwReq().getTitle(), requirementDtos);
        }
        if (dto != null) {
            adminConsumer.deletePwReq(dto.getId());
        }
    }

    @Test
    public void createPwReqTest() throws Exception {
        loginSystem();
        List<PasswordRequirementDto> requirementDtos = adminConsumer.getStoredPwReqs();
        if (requirementDtos == null || requirementDtos.isEmpty()) {
            adminConsumer.createPwReq(buildPwReq());
            requirementDtos = adminConsumer.getStoredPwReqs();
        }

        PasswordRequirementDto dto = findRequirement(buildPwReq().getTitle(), requirementDtos);

        if (dto != null) {
            adminConsumer.deletePwReq(dto.getId());
        }

        dto = adminConsumer.createPwReq(buildPwReq());
        logger.info("Newly created password requirement: {}", dto);
        assertNotNull("New password requirement must not be null", dto);
        adminConsumer.deletePwReq(dto.getId());
    }

    @Test
    public void updatePwReqTest() throws Exception {
        loginSystem();

        String newValue = "456";
        List<PasswordRequirementDto> requirementDtos = adminConsumer.getStoredPwReqs();
        if (requirementDtos == null || requirementDtos.isEmpty()) {
            adminConsumer.createPwReq(buildPwReq());
            requirementDtos = adminConsumer.getStoredPwReqs();
        }

        PasswordRequirementDto dto = findRequirement(buildPwReq().getTitle(), requirementDtos);

        if (dto == null) {
            dto = adminConsumer.createPwReq(buildPwReq());
        }
        dto.setWhitelist(newValue);
        dto = adminConsumer.updatePwReq(dto);
        logger.info("Updated pw req: {}", dto);
        assertEquals("Updated pw req must have adopted a new value", newValue, dto.getWhitelist());

        adminConsumer.deletePwReq(dto.getId());
    }

    @Test
    public void deletePwReqTest() throws Exception {
        loginSystem();
        List<PasswordRequirementDto> requirementDtos = adminConsumer.getStoredPwReqs();
        if (requirementDtos == null || requirementDtos.isEmpty()) {
            adminConsumer.createPwReq(buildPwReq());
            requirementDtos = adminConsumer.getStoredPwReqs();
        }

        PasswordRequirementDto dto = findRequirement(buildPwReq().getTitle(), requirementDtos);

        if (dto == null) {
            dto = adminConsumer.createPwReq(buildPwReq());
        }

        assertNotNull("Pw req must be available in Stored list", dto);
        adminConsumer.deletePwReq(dto.getId());

        requirementDtos = adminConsumer.getStoredPwReqs();
        dto = findRequirement(buildPwReq().getTitle(), requirementDtos);
        assertNull("PwReq must be removed by now", dto);

    }

    @Test
    public void refreshPwReqsTest() throws Exception {
        loginSystem();

        List<PasswordRequirementDto> requirementDtos = adminConsumer.getLivePwReqs();
        PasswordRequirementDto dto = findRequirement(buildPwReq().getTitle(), requirementDtos);

        if (dto == null) {
            dto = adminConsumer.createPwReq(buildPwReq());
            adminConsumer.refreshPwReqs();
            pause(1000);
            requirementDtos = adminConsumer.getLivePwReqs();
            dto = findRequirement(buildPwReq().getTitle(), requirementDtos);
            logger.info("PwReq after creation and refresh: {}", dto);
            assertNotNull("PwReq must be in Live after its created and refreshed", dto);
            adminConsumer.deletePwReq(dto.getId());
        } else {
            adminConsumer.deletePwReq(dto.getId());
            adminConsumer.refreshPwReqs();
            requirementDtos = adminConsumer.getLivePwReqs();
            dto = findRequirement(buildPwReq().getTitle(), requirementDtos);
            logger.info("PwReq after deletion and refresh: {}", dto);
            assertNull("PwReq must be no longer in Live after its deleted and refreshed", dto);
        }
    }

}
