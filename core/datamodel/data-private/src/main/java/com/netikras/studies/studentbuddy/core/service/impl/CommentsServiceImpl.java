package com.netikras.studies.studentbuddy.core.service.impl;

import com.netikras.studies.studentbuddy.commons.exception.StudBudUncheckedException;
import com.netikras.studies.studentbuddy.core.data.api.dao.CommentDao;
import com.netikras.studies.studentbuddy.core.data.api.dao.TagDao;
import com.netikras.studies.studentbuddy.core.data.api.model.Comment;
import com.netikras.studies.studentbuddy.core.data.api.model.CommentTag;
import com.netikras.studies.studentbuddy.core.data.api.model.Tag;
import com.netikras.studies.studentbuddy.core.service.CommentsService;
import com.netikras.studies.studentbuddy.core.validator.CommentValidator;
import com.netikras.tools.common.exception.ErrorsCollection;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

import static com.netikras.tools.common.remote.http.HttpStatus.BAD_REQUEST;

@Service
public class CommentsServiceImpl implements CommentsService {

    @Resource
    private CommentDao commentDao;

    @Resource
    private TagDao tagDao;


    @Resource
    private CommentValidator commentValidator;

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

    @Transactional
    public Comment saveComment(Comment comment) {
        return commentDao.saveAndFlush(comment);
    }

    @Override
    @Transactional
    public Comment createComment(Comment comment) {
        ErrorsCollection errors = commentValidator.validateForCreation(comment, null);
        if (!errors.isEmpty()) {
            throw new StudBudUncheckedException()
                    .setMessage1("Cannot create new comment")
                    .setMessage2("Validation errors: " + errors.size())
                    .setErrors(errors)
                    .setStatusCode(BAD_REQUEST)
                    ;
        }
        return saveComment(comment);
    }

    @Override
    public Comment updateComment(Comment comment) {
        return saveComment(comment);
    }

    @Override
    @Transactional
    public void assignTag(String commentId, Tag tag) {
        Comment comment = commentDao.findOne(commentId);
        ErrorsCollection errors = commentValidator.validateForAssignment(tag, comment, null);

        if (!errors.isEmpty()) {
            throw new StudBudUncheckedException()
                    .setMessage1("Cannot assign tag to comment")
                    .setMessage2("Validation errors: " + errors.size())
                    .setErrors(errors)
                    .setStatusCode(BAD_REQUEST)
                    ;
        }

        CommentTag commentTag = new CommentTag();
        commentTag.setComment(comment);
        commentTag.setTag(tag);

        comment.addTag(commentTag);

        saveComment(comment);
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

    @Override
    public List<Tag> searchAllTagsByValue(String query) {
        return tagDao.findAllByValueLikeIgnoreCase(tagDao.wrapSearchString(query));
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

    @Override
    public List<Comment> findCommentsByTagId(String tagId) {
        List<Comment> comments = commentDao.findAllByTags_Tag_Id(tagId);
        return comments;
    }

    @Override
    public List<Comment> findCommentsByTagValue(String tagValue) {
        List<Comment> comments = commentDao.findAllByTags_Tag_Value(tagValue);
        return comments;
    }

    @Override
    public List<Comment> findCommentsByPerson(String id) {
        List<Comment> comments = commentDao.findAllByAuthor_Id(id);
        return comments;
    }

    @Override
    public List<Comment> searchAllByTitle(String query) {
        return commentDao.findAllByTitleLikeIgnoreCase(commentDao.wrapSearchString(query));
    }

    @Override
    public List<Comment> searchAllByText(String query) {
        return commentDao.findAllByTextLikeIgnoreCase(commentDao.wrapSearchString(query));
    }
}
