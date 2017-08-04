package com.netikras.studies.studentbuddy.core.data.api.dao;

import com.netikras.studies.studentbuddy.core.data.api.model.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentDao extends JpaRepo<Comment> {

    List<Comment> findByEntityTypeAndEntityId(String entityType, String entityId);

    List<Comment> findAllByEntityType(String typeName);

}
