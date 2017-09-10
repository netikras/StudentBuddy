package com.netikras.studies.studentbuddy.core.data.api.dao;

import com.netikras.studies.studentbuddy.core.data.api.model.Building;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by netikras on 17.6.21.
 */
@Repository
public interface BuildingDao extends JpaRepo<Building> {

    List<Building> findAllByTitleLikeIgnoreCase(String title);

    Building findByAddress_Id(String address_id);

}
