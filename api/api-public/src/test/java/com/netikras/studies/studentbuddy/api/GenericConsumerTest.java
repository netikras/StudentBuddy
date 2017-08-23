package com.netikras.studies.studentbuddy.api;

import com.netikras.studies.studentbuddy.api.user.UserConsumer;
import com.netikras.studies.studentbuddy.core.data.api.dto.meta.UserDto;
import com.netikras.tools.common.remote.AuthenticationDetail;
import com.netikras.tools.common.remote.RemoteEndpointServer;
import com.netikras.tools.common.remote.http.GenericRestConsumer;
import com.netikras.tools.common.remote.http.HttpRequest;
import com.netikras.tools.common.remote.http.SessionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenericConsumerTest {

    private Logger logger = null;

    protected GenericConsumerTest(Logger logger) {
        this.logger = logger;
        responseListener = new ResponseListener(logger);
    }

    protected GenericConsumerTest() {
        this.logger = LoggerFactory.getLogger(GenericConsumerTest.class);
        responseListener = new ResponseListener(logger);
    }

    private UserConsumer userConsumer;
    private SessionContext sessionContext;
    protected RemoteEndpointServer server = new RemoteEndpointServer()
            .setProtocol(HttpRequest.Protocol.HTTP)
            .setAddress("localhost")
            .setPort(8080)
            .setRootUrl("/stubu")
            .setAuth(new AuthenticationDetail()
                    .setUsername("system")
                    .setPassword("system")
            );

    protected ResponseListener responseListener;


    protected static UserDto SYSTEM_USER = new UserDto();
    static {
        SYSTEM_USER.setId("136dacaf-a953-44b3-90e2-e3f0f4939981");
        SYSTEM_USER.setName("system");
    }

    protected static final String SYSTEM_USER_PASSWORD = "system";

    public void initGenericConsumer() {
        sessionContext = new SessionContext();
        userConsumer = attachConsumer(new UserConsumer());
    }

    protected <T extends GenericRestConsumer> T attachConsumer(T consumer) {
        if (consumer == null) return null;

        consumer.setSessionContext(getSessionContext());
        consumer.addServer("default", server);
        consumer.addListener("default", responseListener);

        return consumer;
    }

    protected UserDto login(String username, String password) {
        UserDto userDto = userConsumer.login(new AuthenticationDetail()
                .setUsername("system")
                .setPassword("system")
        );
        return userDto;
    }

    public SessionContext getSessionContext() {
        return sessionContext;
    }

    public UserConsumer getUserConsumer() {
        return userConsumer;
    }

    protected UserDto loginSystem() {
        return login(SYSTEM_USER.getName(), SYSTEM_USER_PASSWORD);
    }

    protected void pause(long timeMs) {
        try {
            Thread.sleep(timeMs);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
