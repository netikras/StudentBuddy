package com.netikras.studies.studentbuddy.core.data.api.dao;

import com.netikras.studies.studentbuddy.core.data.api.model.LectureRoom;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectureRoomDao extends JpaRepo<LectureRoom> {

    List<LectureRoom> findAllByTitleLikeIgnoreCase(String title);

    List<LectureRoom> findAllByNumberLikeIgnoreCase(String number);

    LectureRoom findLectureRoomByTitleAndNumberAndFloor_Id(String title, String number, String floor_id);

    LectureRoom findLectureRoomByTitleAndNumberAndFloor_NumberAndFloor_Building_Id(String title, String number, int floor_number, String floor_building_id);

    List<LectureRoom> findAllByFloor_Building_Id(String floor_building_id);

    List<LectureRoom> findAllByFloor_Section_Id(String floor_section_id);

    List<LectureRoom> findAllByFloor_Id(String floor_id);

    @Query("select rooms from LectureRoom rooms join BuildingFloor floor join FloorLayout layout where layout.id = ?1")
    List<LectureRoom> findAllByFloor_Layouts_IdContains(String id);

//    List<LectureRoom> findAllBỵFloor_Layouts_IdContains(String id);
//    List<LectureRoom> findAllByFloor_Layouts_IdContains(String id);

//    LectureRoom findByFloor_Layouts_IdContains(String id);
//LectureRoom findByFloor_LayoutsContains(Object id);

}
