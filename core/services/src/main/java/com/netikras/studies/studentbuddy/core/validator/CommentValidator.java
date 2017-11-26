package com.netikras.studies.studentbuddy.core.validator;

import com.netikras.studies.studentbuddy.core.data.api.dao.CommentDao;
import com.netikras.studies.studentbuddy.core.data.api.dao.JpaRepo;
import com.netikras.studies.studentbuddy.core.data.api.dao.PersonDao;
import com.netikras.studies.studentbuddy.core.data.api.dao.ResourceRepoProvider;
import com.netikras.studies.studentbuddy.core.data.api.dao.TagDao;
import com.netikras.studies.studentbuddy.core.data.api.model.Comment;
import com.netikras.studies.studentbuddy.core.data.api.model.CommentTag;
import com.netikras.studies.studentbuddy.core.data.api.model.Person;
import com.netikras.studies.studentbuddy.core.data.api.model.Tag;
import com.netikras.tools.common.exception.ErrorsCollection;
import com.netikras.tools.common.exception.ValidationError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Iterator;

import static com.netikras.tools.common.remote.http.HttpStatus.CONFLICT;
import static com.netikras.tools.common.remote.http.HttpStatus.NOT_FOUND;
import static com.netikras.tools.common.security.IntegrityUtils.ensureValue;
import static com.netikras.tools.common.security.IntegrityUtils.isNullOrEmpty;

@Component
public class CommentValidator {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private ResourceRepoProvider repositories;

    @Resource
    private CommentDao commentDao;

    @Resource
    private PersonDao personDao;

    @Resource
    private TagDao tagDao;


    @Transactional
    public ErrorsCollection validateForCreation(Comment comment, ErrorsCollection errors) {
        errors = ensure(errors);

        if (comment == null) {
            errors.add(new ValidationError()
                    .setSuggestion("Cannot create a non-existing comment")
                    .setMessage1("Comment is not given")
                    .setStatus(NOT_FOUND.getCode())
            );
            return errors;
        }

        comment.setAuthor(fetch(comment.getAuthor()));
        if (!isNullOrEmpty(comment.getTags())) {
            for (Iterator<CommentTag> iterator = comment.getTags().iterator(); iterator.hasNext(); ) {
                CommentTag commentTag = iterator.next();
                if (commentTag == null) {
                    iterator.remove();
                    continue;
                }
                if (commentTag.getTag() != null) {
                    Tag tag = fetch(commentTag.getTag());
                    if (tag == null) {
                        commentTag.getTag().setId(null); // keep the value -- new tag should be created
                    } else {
                        commentTag.setTag(tag);
                    }
                } else {
                    iterator.remove();
                }
            }
        }

        if (isNullOrEmpty(comment.getEntityType()) || isNullOrEmpty(comment.getEntityId())) {
            errors.add(new ValidationError()
                    .setSuggestion("Every comment must be linked to some particular object, let it be a lecture, school, person or whatever")
                    .setMessage1("Comment entity missing")
                    .setStatus(NOT_FOUND.getCode())
            );
        } else {
            try {
                com.netikras.studies.studentbuddy.core.data.meta.Resource entityType =
                        com.netikras.studies.studentbuddy.core.data.meta.Resource.valueOf(comment.getEntityType().toUpperCase());
                JpaRepo repo = repositories.getRepoForResource(entityType);
                if (repo == null) {
                    logger.warn("Repo not found for entity [{}]", entityType);
                    throw new Exception("Cannot find repository for entity type");
                }
                Object entity = repo.findOne(comment.getEntityId());
                if (entity == null) {
                    errors.add(new ValidationError()
                            .setSuggestion("Provided entity id could not be found. Comments can be only created for already existing entities")
                            .setMessage1("Entity ID not found")
                            .setCausedBy(comment.getEntityType())
                            .setStatus(NOT_FOUND.getCode())
                    );
                }
            } catch (Exception e) {
                errors.add(new ValidationError()
                        .setSuggestion("Provided entity type could not be recognized")
                        .setMessage1("Incorrect entity type")
                        .setCausedBy(comment.getEntityType())
                        .setStatus(NOT_FOUND.getCode())
                );
            }

        }

        comment.setId(null);
        return errors;
    }

    @Transactional
    public ErrorsCollection validateForRemoval(Comment comment, ErrorsCollection errors) {
        errors = ensure(errors);

        if (comment == null) {
            errors.add(new ValidationError()
                    .setSuggestion("Cannot create a non-existing comment")
                    .setMessage1("Comment is not given")
                    .setStatus(NOT_FOUND.getCode())
            );
            return errors;
        }


        return errors;
    }


    @Transactional
    public ErrorsCollection validateForAssignment(Tag tag, Comment comment, ErrorsCollection errors) {
        errors = ensure(errors);

        if (comment == null) {
            errors.add(new ValidationError()
                    .setSuggestion("Cannot assign tag to non-existing comment")
                    .setMessage1("Comment is not given")
                    .setStatus(NOT_FOUND.getCode())
            );
            return errors;
        }

        if (tag == null) {
            errors.add(new ValidationError()
                    .setSuggestion("Cannot assign non-existing tag to a comment")
                    .setMessage1("Tag is not given")
                    .setStatus(NOT_FOUND.getCode())
            );
            return errors;
        }

        Comment existingComment = fetch(comment);
        if (existingComment == null) {
            errors.add(new ValidationError()
                    .setSuggestion("Tag can only be assigned to an already existing comment")
                    .setMessage1("Comment cannot be found")
                    .setStatus(NOT_FOUND.getCode())
            );
        } else {
            if (!isNullOrEmpty(existingComment.getTags())) {
                for (CommentTag commentTag : existingComment.getTags()) {
                    if (commentTag.getTag().getValue().equalsIgnoreCase(tag.getValue())) {
                        errors.add(new ValidationError()
                                .setSuggestion("Comment tags cannot duplicate")
                                .setMessage1("Tag already assigned")
                                .setStatus(CONFLICT.getCode())
                        );
                        break;
                    }
                }
            }
        }

        Tag existingTag = fetch(tag);
        if (existingTag == null) {
            if (isNullOrEmpty(tag.getValue())) {
                errors.add(new ValidationError()
                        .setSuggestion("If new tag is to be created tag value is mandatory")
                        .setMessage1("Tag value missing")
                        .setStatus(NOT_FOUND.getCode())
                );
            } else {
                tag.setId(null);
                tag.setCreatedBy(comment.getAuthor());
            }
        } else {
            tag.setId(existingTag.getId());
            tag.setValue(existingTag.getValue());
            tag.setCreatedBy(existingTag.getCreatedBy());
            tag.setCreatedOn(existingTag.getCreatedOn());
        }


        return errors;
    }


    private ErrorsCollection ensure(ErrorsCollection errors) {
        return ensureValue(errors, ErrorsCollection.class);
    }


    @Transactional
    protected Tag fetch(Tag tag) {
        Tag existing = null;

        if (tag == null) return existing;

        if (!isNullOrEmpty(tag.getId())) {
            existing = tagDao.findOne(tag.getId());
        } else if (!isNullOrEmpty(tag.getValue())) {
            existing = tagDao.findByValue(tag.getValue());
        }

        return existing;
    }

    @Transactional
    protected Comment fetch(Comment comment) {
        Comment existing = null;

        if (comment == null) return existing;

        if (!isNullOrEmpty(comment.getId())) {
            existing = commentDao.findOne(comment.getId());
        }

        return existing;
    }

    @Transactional
    protected Person fetch(Person person) {
        Person existing = null;

        if (person == null) return existing;

        if (!isNullOrEmpty(person.getId())) {
            existing = personDao.findOne(person.getId());
        }

        return existing;
    }


}
