package com.netikras.studies.studentbuddy.core.service.impl;

import com.netikras.studies.studentbuddy.core.data.api.dao.CommentDao;
import com.netikras.studies.studentbuddy.core.data.api.dao.PersonDao;
import com.netikras.studies.studentbuddy.core.data.api.dao.TagDao;
import com.netikras.studies.studentbuddy.core.data.api.model.Comment;
import com.netikras.studies.studentbuddy.core.data.api.model.CommentTag;
import com.netikras.studies.studentbuddy.core.data.api.model.Person;
import com.netikras.studies.studentbuddy.core.data.api.model.Tag;
import com.netikras.studies.studentbuddy.core.service.CommentsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CommentsServiceImpl implements CommentsService {

    @Resource
    private CommentDao commentDao;

    @Resource
    private TagDao tagDao;

    @Resource
    private PersonDao personDao;


    @Override
    public Comment findComment(String id) {
        return commentDao.findOne(id);
    }

    @Override
    public List<Comment> findComments(String entityName, String entityId) {
        return commentDao.findByEntityTypeAndEntityId(entityName, entityId);
    }

    @Override
    public void deleteComment(String id) {
        commentDao.delete(id);
    }

    @Override
    @Transactional
    public Comment createComment(Comment comment) {
        if (comment != null) {
            Person author = personDao.findOne(comment.getAuthor().getId());

            if (author == null) {
                // either throw or create... up to implementation :)
                // FIXME throw as Person shall not be created automatically
                author = comment.getAuthor();
                author.setId(null);
                author = personDao.saveAndFlush(author);
            }

            List<CommentTag> commentTags = comment.getTags();
            if (commentTags != null) {
                for (CommentTag commentTag : commentTags) {
                    commentTag.setComment(comment);
                    Tag tag = tagDao.findByValue(commentTag.getTag().getValue());
                    if (tag == null) {
                        tag = commentTag.getTag();
                        tag.setId(null);
                        tag.setCreatedBy(author);
                    }
                    commentTag.setTag(tag);
                }
            }
        }
        return commentDao.saveAndFlush(comment);
    }

    @Override
    public Comment updateComment(Comment comment) {
        return commentDao.saveAndFlush(comment);
    }

    @Override
    public void assignTag(String commentId, Tag tag) {
        Comment comment = findComment(commentId);
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

        Comment comment = findComment(commentId);
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

    public List<Comment> findCommentsByType(String typeName) {
        return commentDao.findAllByEntityType(typeName);
    }

    @Override
    public void deleteCommentsByType(String typeName) {
        List<Comment> comments = findCommentsByType(typeName);
        commentDao.delete(comments);
    }

    @Override
    public void deleteCommentByType(String typeName, String typeId) {
        List<Comment> comments = findComments(typeName, typeId);
        commentDao.delete(comments);
    }
}
