package com.netikras.studies.studentbuddy.core.data.api.dao;

import com.netikras.studies.studentbuddy.core.data.api.model.Tag;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagDao extends JpaRepo<Tag> {

    Tag findByValue(String value);

    List<Tag> findAllByValueLikeIgnoreCase(String value);

}
