package com.netikras.studies.studentbuddy.core.data.api.dao;

import com.netikras.studies.studentbuddy.core.data.api.model.BuildingSection;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuildingSectionDao extends JpaRepo<BuildingSection> {

    List<BuildingSection> findAllByTitleLikeIgnoreCase(String title);

    BuildingSection findByTitleAndBuilding_Id(String title, String building_id);

    BuildingSection findByTitleAndBuilding_Title(String title, String building_title);

    BuildingSection findByAddress_Id(String address_id);

    List<BuildingSection> findAllByBuilding_Id(String building_id);


    List<BuildingSection> findAllByAddress_Id(String id);
}
