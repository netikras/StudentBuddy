package com.netikras.studies.studentbuddy.core.data.api.dao;

import com.netikras.studies.studentbuddy.core.data.api.model.LectureRoom;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectureRoomDao extends JpaRepo<LectureRoom> {

    List<LectureRoom> findAllByTitleLikeIgnoreCase(String title);

    List<LectureRoom> findAllByNumberLikeIgnoreCase(String number);

}
