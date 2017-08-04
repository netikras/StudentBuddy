package com.netikras.studies.studentbuddy.core.data.api.dao;

import com.netikras.studies.studentbuddy.core.data.api.model.Lecture;
import com.netikras.studies.studentbuddy.core.data.api.model.LectureRoom;
import com.netikras.studies.studentbuddy.core.data.api.model.Lecturer;
import com.netikras.studies.studentbuddy.core.data.api.model.Student;
import com.netikras.studies.studentbuddy.core.data.api.model.StudentsGroup;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface LectureDao extends JpaRepo<Lecture> {

    List<Lecture> findByLecturerAndStartsOnBetween(Lecturer lecturer, Date startsAfter, Date startsBefore);

    List<Lecture> findByStudentsGroupAndStartsOnBetween(StudentsGroup group, Date startsAfter, Date startsBefore);

    List<Lecture> findByExclusiveStudentsContainingAndStartsOnBetween(Student exclusiveStudent, Date startsAfter, Date startsBefore);

    List<Lecture> findByRoomAndStartsOnBetween(LectureRoom room, Date startsAfter, Date startsBefore);
}
