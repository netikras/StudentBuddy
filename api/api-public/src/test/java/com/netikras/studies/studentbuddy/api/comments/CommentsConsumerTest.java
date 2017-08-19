package com.netikras.studies.studentbuddy.api.comments;


import com.netikras.studies.studentbuddy.api.ResponseListener;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


public class CommentsConsumerTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private UserConsumer userConsumer;
    private CommentsConsumer commentsConsumer;
    private SessionContext sessionContext;
    private RemoteEndpointServer server = new RemoteEndpointServer()
            .setProtocol(HttpRequest.Protocol.HTTP)
            .setAddress("localhost")
            .setPort(8080)
            .setRootUrl("/stubu")
            .setAuth(new AuthenticationDetail()
                    .setUsername("system")
                    .setPassword("system")
            );
    private ResponseListener responseListener = new ResponseListener(logger);


    @Before
    public void init() {
        commentsConsumer = new CommentsConsumer();
        userConsumer = new UserConsumer();

        sessionContext = new SessionContext();
        userConsumer.setSessionContext(sessionContext);
        commentsConsumer.setSessionContext(sessionContext);


        userConsumer.addServer("default", server);
        commentsConsumer.addServer("default", server);

        userConsumer.addListener("default", responseListener);
        commentsConsumer.addListener("default", responseListener);
    }

    private UserDto login(String username, String password) {
        UserDto userDto = userConsumer.login(new AuthenticationDetail()
                .setUsername("system")
                .setPassword("system")
        );
        return userDto;
    }

    private CommentDto buildComment() {
        PersonDto author = new PersonDto();
        author.setId("10101");
        author.setFirstName("Andrew");
        author.setLastName("Hopkins");
        author.setEmail("andhop@yahoo.co.uk");
        List<String> tags = new ArrayList<>();
        tags.add("#tag1");
        tags.add("#tag7");

        CommentDto comment = new CommentDto();
        comment.setCategory("PLAYERS");
        comment.setEntityType("LECTURE");
        comment.setEntityId("112233");
        comment.setTitle("Some generic comment");
        comment.setText("some comment texxxxxttttttt");
        comment.setAuthor(author);
        comment.setTags(tags);
        return comment;
    }

    private CommentDto createComment(CommentDto commentDto) {
        CommentDto dto = commentsConsumer.createComment(commentDto);
        return dto;
    }

    @Test
    public void getAllCommentsByTypeTest() {
        login("system", "system");

        List<CommentDto> dtos = commentsConsumer.getAllCommentsByType("LECTURE", "112233");

        logger.info("All comments by type: {}", dtos);
    }

    @Test
    public void createCommentTest() {
        login("system", "system");

        CommentDto comment = buildComment();

        CommentDto dto = createComment(comment);

        assertNotNull("Created comment DTO must be returned", dto);
        assertNotEquals("Server must not use provided author's ID if such author is non-existent",
                comment.getAuthor().getId(), dto.getAuthor().getId());
        assertEquals("Server must preserve author's info if new person is created",
                comment.getAuthor().getEmail(), dto.getAuthor().getEmail());
        assertEquals("Newly created comment must contain the same text",
                comment.getText(), dto.getText());
        assertEquals("Comment tags must be preserved",
                comment.getTags().size(), dto.getTags().size());

        logger.info("Created comment: {}", dto);
    }

    @Test
    public void deleteCommentTest() {
        login("system", "system");
        CommentDto commentDto = buildComment();
        commentDto = createComment(commentDto);
        commentDto = createComment(commentDto);

        List<CommentDto> comments = commentsConsumer.getAllCommentsByType(commentDto.getEntityType(), commentDto.getEntityId());

        assertNotNull("Newly created comments must be available for fetching", comments);
        assertFalse("Newly created comments must be available for fetching", comments.isEmpty());

        for (CommentDto dto : comments) {
            logger.info("Deleting comment: {}", dto);
            commentsConsumer.deleteById(dto.getId());
        }

        comments = commentsConsumer.getAllCommentsByType("LECTURE", "112233");
        logger.info("Comments after removal: {}", comments);
        assertNotNull("Comments list must be non-nul empty list", comments);
        assertTrue("Comments list must be empty after removal", comments.isEmpty());
    }


    @Test
    public void getAllCommentsByTagValueTest() {
        login("system", "system");
        CommentDto commentDto = buildComment();
        commentDto = createComment(commentDto);
        commentDto = createComment(commentDto);

        String tagValue = commentDto.getTags().get(0);

        List<CommentDto> comments = commentsConsumer.getAllCommentsByTagValue(tagValue);

        assertNotNull("There should be plenty comments having given tag", comments);
        assertFalse("There should be plenty comments having given tag", comments.isEmpty());

        logger.info("All comments by tag {} ({}): {}", tagValue, comments.size(), comments);

        for (CommentDto dto : comments) {
            commentsConsumer.deleteById(dto.getId());
        }

        comments = commentsConsumer.getAllCommentsByTagValue(tagValue);
        logger.info("Comments after removal: {}", comments);
        assertTrue("After removal there should be none comments having given tag", comments.isEmpty());

    }

    @Test
    public void getAllCommentsByAuthorIdTest() {
        login("system", "system");
        CommentDto commentDto = buildComment();
        commentDto = createComment(commentDto);
        commentDto = createComment(commentDto);


        String authorId = commentDto.getAuthor().getId();

        List<CommentDto> comments = commentsConsumer.getAllCommentsByPersonId(authorId);
        assertFalse("There should be at least a few comments having given author ID", comments.isEmpty());
        logger.info("Comments by author ID ({}): {}", comments.size(), comments);

        commentsConsumer.deleteAllByPersonId(authorId);

        comments = commentsConsumer.getAllCommentsByPersonId(authorId);
        assertTrue("There should be no more comments left from the given author after removal", comments.isEmpty());
        logger.info("Comments by author ID after removal ({}): {}", comments.size(), comments);
    }

}