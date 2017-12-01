package com.netikras.studies.studentbuddy.core.data.api.dao;

import com.netikras.studies.studentbuddy.core.data.api.model.FloorLayout;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FloorLayoutDao extends JpaRepo<FloorLayout> {


    List<FloorLayout> findAllByFloor_Id(String floor_id);

    List<FloorLayout> findAllByFloor_Section_Id(String floor_section_id);

    List<FloorLayout> findAllByFloor_Building_Id(String floor_building_id);

}
