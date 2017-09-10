package com.netikras.studies.studentbuddy.core.data.api.dao;

import com.netikras.studies.studentbuddy.core.data.api.model.LectureGuest;

import java.util.List;

public interface LectureGuestDao extends JpaRepo<LectureGuest> {

    List<LectureGuest> findAllByPerson_FirstNameLikeIgnoreCase(String person_firstName);

    List<LectureGuest> findAllByPerson_LastNameLikeIgnoreCase(String person_lastName);

    List<LectureGuest> findAllByPerson_Id(String person_id);

}
