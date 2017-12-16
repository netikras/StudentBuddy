package com.netikras.studies.studentbuddy.core.service.impl;

import com.netikras.studies.studentbuddy.commons.exception.StudBudUncheckedException;
import com.netikras.studies.studentbuddy.core.data.api.dao.CommentDao;
import com.netikras.studies.studentbuddy.core.data.api.dao.ResourceRepoProvider;
import com.netikras.studies.studentbuddy.core.data.api.dao.TagDao;
import com.netikras.studies.studentbuddy.core.data.api.model.Comment;
import com.netikras.studies.studentbuddy.core.data.api.model.CommentTag;
import com.netikras.studies.studentbuddy.core.data.api.model.Tag;
import com.netikras.studies.studentbuddy.core.data.meta.Commentable;
import com.netikras.studies.studentbuddy.core.data.meta.Identifiable;
import com.netikras.studies.studentbuddy.core.data.sys.model.ResourceActionLink;
import com.netikras.studies.studentbuddy.core.data.sys.model.User;
import com.netikras.studies.studentbuddy.core.data.sys.model.UserRole;
import com.netikras.studies.studentbuddy.core.service.CommentsService;
import com.netikras.studies.studentbuddy.core.service.SystemService;
import com.netikras.studies.studentbuddy.core.validator.CommentValidator;
import com.netikras.tools.common.exception.ErrorsCollection;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static com.netikras.studies.studentbuddy.core.data.meta.Action.COMMENT_GET;
import static com.netikras.tools.common.remote.http.HttpStatus.BAD_REQUEST;
import static com.netikras.tools.common.security.IntegrityUtils.isNullOrEmpty;

@Service
public class CommentsServiceImpl implements CommentsService {

    @Resource
    private CommentDao commentDao;

    @Resource
    private TagDao tagDao;
    @Resource
    private ResourceRepoProvider repoProvider;
    @Resource
    private SystemService systemService;


    @Resource
    private CommentValidator commentValidator;

    @Override
    public Comment getComment(String id) {
        return commentDao.findOne(id);
    }

    @Override
    public List<Comment> findComments(String entityName, String entityId) {
        return commentDao.findByEntityTypeAndEntityId(entityName, entityId);
    }

    @Override
    @Transactional
    public <T extends Commentable & Identifiable> T assignComments(T item) {

        if (item == null) {
            return null;
        }

        if (isNullOrEmpty(item.getId())) {
            return item;
        }

        String entityType = repoProvider.getResourceNameForModel(item.getClass());
        return assignComments(item, entityType);
    }

    @Override
    @Transactional
    public <T extends Commentable & Identifiable> Collection<T> assignComments(Collection<T> items) {
        if (isNullOrEmpty(items)) {
            return items;
        }

        String entityType = null;

        for (T item : items) {
            if (entityType == null) {
                entityType = repoProvider.getResourceNameForModel(item.getClass());
                if (isNullOrEmpty(entityType)) {
                    continue;
                }
            }

            if (isNullOrEmpty(item.getId())) {
                continue;
            }

            assignComments(item, entityType);
        }

        return items;
    }

    private <T extends Commentable & Identifiable> T assignComments(T item, String entityType) {
        List<Comment> comments = commentDao.findByEntityTypeAndEntityId(entityType, item.getId());
        item.setComments(comments);
        return item;
    }

    @Override
    public List<Comment> findComments(Class entity, String entityId) {
        if (entity == null) {
            return null;
        }

        com.netikras.studies.studentbuddy.core.data.meta.Resource resource = null;

        try {
            resource = repoProvider.getResourceForModel(entity);
        } catch (Exception e) {
            return null;
        }
        return commentDao.findByEntityTypeAndEntityId(resource.name(), entityId);
    }

    @Override
    @Transactional
    public void deleteComment(String id) {
        Comment comment = getComment(id);
        ErrorsCollection errors = commentValidator.validateForRemoval(comment, null);
        if (!errors.isEmpty()) {
            throw new StudBudUncheckedException()
                    .setMessage1("Cannot remove comment")
                    .setMessage2("Validation errors: " + errors.size())
                    .setProbableCause(id)
                    .setErrors(errors)
                    .setStatusCode(BAD_REQUEST)
                    ;
        }
        commentDao.delete(id);
    }

    @Override
    @Transactional
    public void purgeComment(String id) {
        Comment comment = getComment(id);
        if (comment == null) {
            return;
        }

        commentDao.delete(comment);
    }

    @Override
    @Transactional
    public List<Comment> getAllAllowedByEntityIdsUpdatedAfter(List<String> ids, Date date) {

        User user = systemService.getCurrentUser();

        if (user == null
                || "system".equals(user.getName())
                || isNullOrEmpty(user.getRoles())) {
            return commentDao.findAllByEntityIdInAndCreatedOnAfterOrUpdatedOnAfter(ids, date);
        }


        List<ResourceActionLink> actionLinks = new ArrayList<>();

        for (UserRole userRole : user.getRoles()) {
            actionLinks.addAll(systemService.getPermissionsForRole(userRole.getRole().getName()));
        }

        if (isNullOrEmpty(actionLinks)) {
            return Arrays.asList();
        }

        List<String> allowedIds = new ArrayList<>();
        List<String> allowedResources = new ArrayList<>();
        for (ResourceActionLink actionLink : actionLinks) {
            if (actionLink.isStrict()) {
                if (!isNullOrEmpty(actionLink.getEntityId())) {
                    allowedIds.add(actionLink.getEntityId());
                }
            } else {
                if (actionLink.getResource() != null) {
                    allowedResources.add(actionLink.getResource().name());
                }
            }
        }

        return commentDao.findAllByEntityIdInAndCreatedOnAfterOrUpdatedOnAfter(ids, allowedIds, allowedResources, date);
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
