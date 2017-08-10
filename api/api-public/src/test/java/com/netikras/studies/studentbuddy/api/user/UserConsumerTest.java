package com.netikras.studies.studentbuddy.api.user;

import com.netikras.studies.studentbuddy.core.data.api.dto.meta.UserDto;
import com.netikras.tools.common.remote.AuthenticationDetail;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserConsumerTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void login() throws Exception {
        UserConsumer userConsumer = new UserConsumer();
        UserDto userDto = userConsumer.login(new AuthenticationDetail()
                .setUsername("system")
                .setPassword("system")
        );

        logger.info("{}", userDto);
    }


}
