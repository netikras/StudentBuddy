package com.netikras.studies.studentbuddy.core.data.api.dao;

import com.netikras.studies.studentbuddy.core.data.api.model.LectureRoom;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectureRoomDao extends JpaRepo<LectureRoom> {

    List<LectureRoom> findAllByTitleLikeIgnoreCase(String title);

    List<LectureRoom> findAllByNumberLikeIgnoreCase(String number);

    LectureRoom findLectureRoomByTitleAndNumberAndFloor_Id(String title, String number, String floor_id);

    LectureRoom findLectureRoomByTitleAndNumberAndFloor_NumberAndFloor_Building_Id(String title, String number, int floor_number, String floor_building_id);

}
