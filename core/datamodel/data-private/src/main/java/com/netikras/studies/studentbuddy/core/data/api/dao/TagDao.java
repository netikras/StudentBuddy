package com.netikras.studies.studentbuddy.core.data.api.dao;

import com.netikras.studies.studentbuddy.core.data.api.model.Tag;
import org.springframework.stereotype.Repository;

@Repository
public interface TagDao extends JpaRepo<Tag> {

    Tag findByValue(String value);

}
