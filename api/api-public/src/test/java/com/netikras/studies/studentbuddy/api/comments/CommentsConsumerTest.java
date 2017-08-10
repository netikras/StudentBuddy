package com.netikras.studies.studentbuddy.api.comments;


import com.netikras.studies.studentbuddy.core.data.api.dto.meta.CommentDto;
import org.junit.Test;

import java.util.List;


public class CommentsConsumerTest {

    @Test
    public void getAllCommentsByTypeTest() {
        CommentsConsumer commentsConsumer = new CommentsConsumer();
        List<CommentDto> dtos = commentsConsumer.getAllCommentsByType("TYPE1");

        System.out.println(dtos);
    }

}