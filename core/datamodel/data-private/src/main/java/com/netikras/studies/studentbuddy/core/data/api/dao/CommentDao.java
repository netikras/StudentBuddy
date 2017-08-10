package com.netikras.studies.studentbuddy.core.data.api.dao;

import com.netikras.studies.studentbuddy.core.data.api.model.Comment;
import com.netikras.studies.studentbuddy.core.data.api.model.CommentTag;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface CommentDao extends JpaRepo<Comment> {

    List<Comment> findByEntityTypeAndEntityId(String entityType, String entityId);

    List<Comment> findAllByEntityType(String typeName);

    List<Comment> findAllByTagsContains(List<CommentTag> tags);

    List<Comment> findAllByTags_Tag_Value(String tagValue);

    List<Comment> findAllByTags_Tag_Id(String tagId);

    List<Comment> findAllByAuthor_Id(String author_id);

}
