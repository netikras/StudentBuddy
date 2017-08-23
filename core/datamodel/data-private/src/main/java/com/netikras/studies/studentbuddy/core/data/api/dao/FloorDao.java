package com.netikras.studies.studentbuddy.core.data.api.dao;

import com.netikras.studies.studentbuddy.core.data.api.model.BuildingFloor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FloorDao extends JpaRepo<BuildingFloor> {

    List<BuildingFloor> findAllByTitleLikeIgnoreCase(String title);

}
