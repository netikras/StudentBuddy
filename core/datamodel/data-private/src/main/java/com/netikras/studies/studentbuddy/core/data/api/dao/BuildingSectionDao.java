package com.netikras.studies.studentbuddy.core.data.api.dao;

import com.netikras.studies.studentbuddy.core.data.api.model.BuildingSection;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuildingSectionDao extends JpaRepo<BuildingSection> {

    List<BuildingSection> findAllByTitleLikeIgnoreCase(String title);

}
