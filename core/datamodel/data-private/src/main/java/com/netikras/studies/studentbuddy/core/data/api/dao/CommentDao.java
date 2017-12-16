package com.netikras.studies.studentbuddy.core.data.api.dao;

import com.netikras.studies.studentbuddy.core.data.api.model.Comment;
import com.netikras.studies.studentbuddy.core.data.api.model.CommentTag;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Repository
public interface CommentDao extends JpaRepo<Comment> {

    List<Comment> findByEntityTypeAndEntityId(String entityType, String entityId);

    List<Comment> findAllByEntityType(String typeName);

    List<Comment> findAllByTagsContains(List<CommentTag> tags);

    List<Comment> findAllByTags_Tag_Value(String tagValue);

    List<Comment> findAllByTags_Tag_Id(String tagId);

    List<Comment> findAllByAuthor_Id(String author_id);

    List<Comment> findAllByTitleLikeIgnoreCase(String title);

    List<Comment> findAllByTextLikeIgnoreCase(String text);

    @Query("select c from Comment c where c.entityId in ?1 and (c.createdOn > ?2 or c.updatedOn > ?2)")
    List<Comment> findAllByEntityIdInAndCreatedOnAfterOrUpdatedOnAfter(List<String> ids, Date after);

    @Query("select c from Comment c where c.entityId in ?1 and (c.createdOn > ?4 or c.updatedOn > ?4) and (c.entityId in ?2 or c.entityType in ?3)")
    List<Comment> findAllByEntityIdInAndCreatedOnAfterOrUpdatedOnAfter(List<String> ids, List<String> allowedIds, List<String> wildcardResources, Date after);

}
