package com.netikras.studies.studentbuddy.api.user;

import com.netikras.studies.studentbuddy.api.GenericSchoolAwareTest;
import com.netikras.studies.studentbuddy.api.sys.generated.AdminApiConsumer;
import com.netikras.studies.studentbuddy.api.user.generated.UserApiConsumer;
import com.netikras.studies.studentbuddy.core.data.api.dto.PersonDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.meta.UserDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.SchoolDto;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotNull;

public class UserTests extends GenericSchoolAwareTest {

    private UserApiConsumer userApiConsumer;
    private AdminApiConsumer adminApiConsumer;


    @Before
    public void initUserTest() {
        initPersonAware();
        userApiConsumer = attachConsumer(new UserApiConsumer());
        adminApiConsumer = attachConsumer(new AdminApiConsumer());
    }


    @Test
    public void userCreateTest() {
        createInfra(true);

        String password = "qwerty123";

        PersonDto personDto = personConsumer.getPersonDtoByCode("000000001");
        UserDto userDto = getUserConsumer().createUserDto(buildUser(personDto, "johnny", password));

        assertNotNull("User should have been created", userDto);

        login(userDto.getName(), password);

        List<SchoolDto> schoolDtos = schoolConsumer.getSchoolDtoAll();
        System.out.println(schoolDtos);

    }


}
