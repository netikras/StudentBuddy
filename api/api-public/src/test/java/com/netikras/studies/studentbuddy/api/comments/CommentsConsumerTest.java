package com.netikras.studies.studentbuddy.api.comments;


import com.netikras.studies.studentbuddy.api.user.UserConsumer;
import com.netikras.studies.studentbuddy.core.data.api.dto.PersonDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.meta.CommentDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.meta.UserDto;
import com.netikras.tools.common.remote.AuthenticationDetail;
import com.netikras.tools.common.remote.RemoteEndpointServer;
import com.netikras.tools.common.remote.http.HttpRequest;
import com.netikras.tools.common.remote.http.SessionContext;
import org.junit.Before;
import org.junit.Test;

import java.util.List;


public class CommentsConsumerTest {

    private UserConsumer userConsumer;
    private CommentsConsumer commentsConsumer;
    private SessionContext sessionContext;


    @Before
    public void init() {
        commentsConsumer = new CommentsConsumer();
        userConsumer = new UserConsumer();

        sessionContext = new SessionContext();
        userConsumer.setSessionContext(sessionContext);
        commentsConsumer.setSessionContext(sessionContext);


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
        );

        commentsConsumer.addServer("default",
                new RemoteEndpointServer()
                        .setProtocol(HttpRequest.Protocol.HTTP)
                        .setAddress("localhost")
                        .setPort(8080)
                        .setRootUrl("/stubu")
                        .setAuth(new AuthenticationDetail()
                                .setUsername("system")
                                .setPassword("system")
                        )
        );
    }

    private UserDto login(String username, String password) {
        UserDto userDto = userConsumer.login(new AuthenticationDetail()
                .setUsername("system")
                .setPassword("system")
        );
        return userDto;
    }

    @Test
    public void getAllCommentsByTypeTest() {
        login("system", "system");

        List<CommentDto> dtos = commentsConsumer.getAllCommentsByType("TYPE1", "112233");

        System.out.println(dtos);
    }

    @Test
    public void createComment() {
        login("system", "system");

        CommentDto comment = new CommentDto();
        comment.setCategory("PLAYERS");
        comment.setCategory("112233");
        comment.setTitle("Some generic comment");
        comment.setText("some comment texxxxxttttttt");

        CommentDto dto = commentsConsumer.createComment(comment);

        System.out.println(dto);
    }

}