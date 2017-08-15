package com.netikras.studies.studentbuddy.api.user;

import com.netikras.studies.studentbuddy.core.data.api.dto.meta.UserDto;
import com.netikras.tools.common.remote.AuthenticationDetail;
import com.netikras.tools.common.remote.RemoteEndpointServer;
import com.netikras.tools.common.remote.http.HttpRequest;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserConsumerTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private UserConsumer userConsumer;

    @Before
    public void init() {
        userConsumer = new UserConsumer();
        userConsumer.addServer("default",
                new RemoteEndpointServer()
                        .setProtocol(HttpRequest.Protocol.HTTP)
                        .setAddress("localhost")
                        .setPort(8080)
                        .setRootUrl("/stubu")
                        .setAuth(new AuthenticationDetail()
                                .setUsername("system")
                                .setPassword("system")
                        )
        )
        ;
    }


    private UserDto login(String username, String password) {
        UserDto userDto = userConsumer.login(new AuthenticationDetail()
                .setUsername("system")
                .setPassword("system")
        );
        return userDto;
    }

    @Test
    public void login() throws Exception {
        UserDto userDto = login("system", "system");

        logger.info("{}", userDto);
    }


    @Test
    public void getUserByName() throws Exception {
        login("system", "system");
        UserDto userDto = userConsumer.getUserByName("system");

        logger.info("{}", userDto);
    }

    @Test
    public void getUserById() throws Exception {
        login("system", "system");
        UserDto userDto = userConsumer.getUser("136dacaf-a953-44b3-90e2-e3f0f4939981");

        logger.info("{}", userDto);
    }


}
