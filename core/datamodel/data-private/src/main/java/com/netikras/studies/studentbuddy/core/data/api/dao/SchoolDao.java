package com.netikras.studies.studentbuddy.core.data.api.dao;

import com.netikras.studies.studentbuddy.core.data.api.model.School;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchoolDao extends JpaRepo<School> {

    List<School> findAllByTitleLikeIgnoreCase(String title);

}
