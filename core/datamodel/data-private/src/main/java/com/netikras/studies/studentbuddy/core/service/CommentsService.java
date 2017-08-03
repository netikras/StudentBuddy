package com.netikras.studies.studentbuddy.core.service;

import com.netikras.studies.studentbuddy.core.data.api.model.Comment;
import com.netikras.studies.studentbuddy.core.data.api.model.Tag;

import java.util.List;

public interface CommentsService {

    Comment getComment(String id);

    List<Comment> getComments(String entityName, String entityId);

    void deleteComment(String id);

    void createComment(Comment comment);

    void updateComment(Comment comment);

    void assignTag(String commentId, Tag tag);

    void removeTag(String commentId, String tagId);

    List<Comment> getCommentsByType(String typeName);

    void deleteCommentsByType(String typeName);

    void deleteCommentByType(String typeName, String typeId);
}
