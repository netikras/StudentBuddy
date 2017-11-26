package com.netikras.studies.studentbuddy.core.data.meta;

import com.netikras.studies.studentbuddy.core.data.api.model.Comment;

import java.util.List;

public interface Commentable {

    void setComments(List<Comment> comments);

    List<Comment> getComments();

}
