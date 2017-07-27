package com.netikras.studies.studentbuddy.core.data.api.dao;

import com.netikras.studies.studentbuddy.core.data.api.model.School;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolDao extends JpaRepo<School> {
}
