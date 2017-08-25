package com.netikras.studies.studentbuddy.core.data.api.dao;

import com.netikras.studies.studentbuddy.core.data.api.model.Discipline;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DisciplineDao extends JpaRepo<Discipline> {

    List<Discipline> findAllByDescriptionLikeIgnoreCase(String description);

    List<Discipline> findAllByTitleLikeIgnoreCase(String title);

    List<Discipline> findAllBySchool_Id(String school_id);

}
