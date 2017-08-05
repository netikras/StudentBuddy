package com.netikras.studies.studentbuddy.core.service;

import com.netikras.studies.studentbuddy.core.data.api.model.Comment;
import com.netikras.studies.studentbuddy.core.data.api.model.Tag;

import java.util.List;

public interface CommentsService {

    Comment findComment(String id);

    List<Comment> findComments(String entityName, String entityId);

    void deleteComment(String id);

    Comment createComment(Comment comment);

    Comment updateComment(Comment comment);

    void assignTag(String commentId, Tag tag);

    void removeTag(String commentId, String tagId);

    List<Comment> findCommentsByType(String typeName);

    void deleteCommentsByType(String typeName);

    void deleteCommentByType(String typeName, String typeId);

    List<Comment> findCommentsByTagId(String tagId);

    List<Comment> findCommentsByTagValue(String tagValue);
}
