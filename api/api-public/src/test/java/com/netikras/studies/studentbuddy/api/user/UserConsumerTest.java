package com.netikras.studies.studentbuddy.api.user;

import com.netikras.studies.studentbuddy.api.ResponseListener;
import com.netikras.studies.studentbuddy.core.data.api.dto.meta.UserDto;
import com.netikras.tools.common.remote.AuthenticationDetail;
import com.netikras.tools.common.remote.RemoteEndpointServer;
import com.netikras.tools.common.remote.http.HttpRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class UserConsumerTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private UserConsumer userConsumer;
    private static UserDto SYSTEM_USER = new UserDto();
    static {
        SYSTEM_USER.setId("136dacaf-a953-44b3-90e2-e3f0f4939981");
        SYSTEM_USER.setName("system");
    }

    private static final String SYSTEM_USER_PASSWORD = "system";

    private RemoteEndpointServer server = new RemoteEndpointServer()
            .setProtocol(HttpRequest.Protocol.HTTP)
            .setAddress("localhost")
            .setPort(8080)
            .setRootUrl("/stubu")
            .setAuth(new AuthenticationDetail()
                    .setUsername(SYSTEM_USER.getName())
                    .setPassword(SYSTEM_USER_PASSWORD)
            );
    private ResponseListener responseListener = new ResponseListener(logger);

    @Before
    public void init() {
        userConsumer = new UserConsumer();
        userConsumer.addServer("default", server);
        userConsumer.addListener("default", responseListener);
    }


    private UserDto login(String username, String password) {
        UserDto userDto = userConsumer.login(new AuthenticationDetail()
                .setUsername(username)
                .setPassword(password)
        );
        return userDto;
    }

    private UserDto loginSystem() {
        return login(SYSTEM_USER.getName(), SYSTEM_USER_PASSWORD);
    }

    @Test
    public void login() throws Exception {
        UserDto userDto = loginSystem();

        logger.info("{}", userDto);
        assertNotNull("Log in as system user must succeed", userDto);
    }


    @Test
    public void getUserByName() throws Exception {
        loginSystem();
        UserDto userDto = userConsumer.getUserByName(SYSTEM_USER.getName());

        logger.info("{}", userDto);
        assertEquals("Returned user must be the system used", SYSTEM_USER.getId(), userDto.getId());
    }

    @Test
    public void getUserById() throws Exception {
        loginSystem();
        UserDto userDto = userConsumer.getUser(SYSTEM_USER.getId());

        logger.info("{}", userDto);
        assertEquals("Returned user must be the system user", SYSTEM_USER.getName(), userDto.getName());
    }

    @Test
    public void changePasswordTest() {
        loginSystem();

        String newPassword = "test123";
        boolean passwordChanged = false;

        passwordChanged = userConsumer.changePassword(SYSTEM_USER.getId(), newPassword);
        logger.info("Password changed: {}", passwordChanged);
        UserDto changedUser = loginSystem();
        logger.info("Login after password change: {}", changedUser);
        assertNull("After password change user must be unable to login", changedUser);

        changedUser = login(SYSTEM_USER.getName(), newPassword);
        logger.info("Login with new password: {}", changedUser);
        assertNotNull("After password change user must be able to login using new password", changedUser);

        passwordChanged = userConsumer.changePassword(SYSTEM_USER.getId(), SYSTEM_USER_PASSWORD);
        logger.info("Password restored: {}", passwordChanged);
        changedUser = loginSystem();
        logger.info("Login after restoring old password: {}", changedUser);
        assertNotNull("After password is restored user must be able to login using its old password", changedUser);
    }

    @Test
    public void logoutTest() {
        loginSystem();

        UserDto userDto = userConsumer.getUserByName(SYSTEM_USER.getName());

        logger.info("{}", userDto);
        assertEquals("Returned user must be the system used", SYSTEM_USER.getId(), userDto.getId());

        userConsumer.logout();
        userDto = userConsumer.getUserByName(SYSTEM_USER.getName());

        logger.info("{}", userDto);
        assertNull("After logout user must be unable to successfully fetch data from server", userDto);
    }

    @Test
    public void updateTest() {
        String newValue = "sys";
        UserDto userDto = loginSystem();

        userDto.setName(newValue);
        userDto = userConsumer.update(userDto, userDto.getId());

        logger.info("Updated user: {}", userDto);
        assertEquals("User detail must have been updated", newValue, userDto.getName());

        userDto.setName(SYSTEM_USER.getName());
        userDto = userConsumer.update(userDto, userDto.getId());

        logger.info("Restored user: {}", userDto);
        assertEquals("User detail must have been restored", SYSTEM_USER.getName(), userDto.getName());

    }


}
