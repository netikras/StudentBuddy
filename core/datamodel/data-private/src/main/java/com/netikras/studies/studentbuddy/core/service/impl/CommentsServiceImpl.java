package com.netikras.studies.studentbuddy.core.service.impl;

import com.netikras.studies.studentbuddy.core.data.api.dao.CommentDao;
import com.netikras.studies.studentbuddy.core.data.api.dao.TagDao;
import com.netikras.studies.studentbuddy.core.data.api.model.Comment;
import com.netikras.studies.studentbuddy.core.data.api.model.CommentTag;
import com.netikras.studies.studentbuddy.core.data.api.model.Tag;
import com.netikras.studies.studentbuddy.core.service.CommentsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CommentsServiceImpl implements CommentsService {

    @Resource
    private CommentDao commentDao;

    @Resource
    private TagDao tagDao;


    @Override
    public Comment getComment(String id) {
        return commentDao.findOne(id);
    }

    @Override
    public List<Comment> getComments(String entityName, String entityId) {
        return commentDao.findByEntityTypeAndEntityId(entityName, entityId);
    }

    @Override
    public void deleteComment(String id) {
        commentDao.delete(id);
    }

    @Override
    public void createComment(Comment comment) {
        commentDao.saveAndFlush(comment);
    }

    @Override
    public void updateComment(Comment comment) {
        commentDao.saveAndFlush(comment);
    }

    @Override
    public void assignTag(String commentId, Tag tag) {
        Comment comment = getComment(commentId);
        if (comment == null) return;

        CommentTag commentTag = new CommentTag();
        commentTag.setComment(comment);
        commentTag.setTag(tag);

        comment.addTag(commentTag);

        updateComment(comment);
    }

    @Override
    public void removeTag(String commentId, String tagId) {
        boolean pristine = true;

        Comment comment = getComment(commentId);
        if (comment == null) return;

        if (tagId == null || tagId.isEmpty()) return;

        List<CommentTag> tags = comment.getTags();
        if (tags == null || tags.isEmpty()) return;

        for (int i = 0; i < tags.size(); i++) {
            CommentTag commentTag = tags.get(i);
            Tag tag = commentTag.getTag();
            if (tag != null && tagId.equals(tag.getId())) {
                tags.remove(i);
                pristine = false;
            }
        }

        if (!pristine) {
            commentDao.saveAndFlush(comment);
        }
    }

    public List<Comment> getCommentsByType(String typeName) {
        return commentDao.getAllByEntityType(typeName);
    }

    @Override
    public void deleteCommentsByType(String typeName) {
        List<Comment> comments = getCommentsByType(typeName);
        commentDao.delete(comments);
    }

    @Override
    public void deleteCommentByType(String typeName, String typeId) {
        List<Comment> comments = getComments(typeName, typeId);
        commentDao.delete(comments);
    }
}
