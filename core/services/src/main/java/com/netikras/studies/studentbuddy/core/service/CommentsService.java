package com.netikras.studies.studentbuddy.core.service;

import com.netikras.studies.studentbuddy.core.data.api.model.Comment;
import com.netikras.studies.studentbuddy.core.data.api.model.Tag;
import com.netikras.studies.studentbuddy.core.data.meta.Commentable;
import com.netikras.studies.studentbuddy.core.data.meta.Identifiable;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface CommentsService {

    Comment getComment(String id);

    List<Comment> findComments(String entityName, String entityId);

    <T extends Commentable & Identifiable> T assignComments(T item);

    <T extends Commentable & Identifiable> Collection<T> assignComments(Collection<T> items);

    List<Comment> findComments(Class entity, String entityId);

    void deleteComment(String id);

    Comment createComment(Comment comment);

    Comment updateComment(Comment comment);

    void assignTag(String commentId, Tag tag);

    void removeTag(String commentId, String tagId);

    List<Tag> searchAllTagsByValue(String query);

    List<Comment> findCommentsByType(String typeName);

    void deleteCommentsByType(String typeName);

    void deleteCommentByType(String typeName, String typeId);

    List<Comment> findCommentsByTagId(String tagId);

    List<Comment> findCommentsByTagValue(String tagValue);

    List<Comment> findCommentsByPerson(String id);

    List<Comment> searchAllByTitle(String query);

    List<Comment> searchAllByText(String query);

    void purgeComment(String id);

    List<Comment> getAllAllowedByEntityIdsUpdatedAfter(List<String> ids, Date date);
}
