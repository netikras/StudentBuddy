package com.netikras.studies.studentbuddy.core.data.api.dao;

import com.netikras.studies.studentbuddy.core.data.api.model.Lecture;
import com.netikras.studies.studentbuddy.core.data.api.model.LectureRoom;
import com.netikras.studies.studentbuddy.core.data.api.model.Lecturer;
import com.netikras.studies.studentbuddy.core.data.api.model.Student;
import com.netikras.studies.studentbuddy.core.data.api.model.StudentsGroup;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Repository
public interface LectureDao extends JpaRepo<Lecture> {

    List<Lecture> findAllByStartsOnBetween(Date startsAfter, Date startsBefore);

    List<Lecture> findAllByDiscipline_IdAndStartsOnBetween(String discipline_id, Date startsAfter, Date startsBefore);

    List<Lecture> findAllByStudentsGroup_IdAndStartsOnBetween(String studentsGroup_id, Date startsAfter, Date startsBefore);

    List<Lecture> findAllByLecturer_IdAndStartsOnBetween(String lecturer_id, Date startsAfter, Date startsBefore);

    List<Lecture> findAllByRoom_IdAndStartsOnBetween(String room_id, Date startsAfter, Date startsBefore);

    List<Lecture> findAllByStudentsGroup_IdAndDiscipline_IdAndStartsOnBetween(String studentsGroup_id, String discipline_id, Date startsAfter, Date startsBefore);

    List<Lecture> findAllByLecturer_IdAndDiscipline_IdAndStartsOnBetween(String lecturer_id, String discipline_id, Date startsAfter, Date startsBefore);

    List<Lecture> findAllByRoom_IdAndDiscipline_IdAndStartsOnBetween(String room_id, String discipline_id, Date startsAfter, Date startsBefore);

    List<Lecture> findAllByRoom_Floor_Building_IdAndStartsOnBetween(String room_floor_building_id, Date startsAfter, Date startsBefore);

    List<Lecture> findAllByLectureGuests_IdAndStartsOnBetween(String guest_id, Date startsAfter, Date startsBefore);

    List<Lecture> findAllByLectureGuests_IdAndDiscipline_IdAndStartsOnBetween(String guest_id, String discipline_id, Date startsAfter, Date startsBefore);

    List<Lecture> findAllByStudentsGroup_Members_IdAndStartsOnBetween(String studentsGroup_members_id, Date startsAfter, Date startsBefore);

    void deleteAllByIdIn(Collection<String> id);

    void deleteAllByDiscipline_Id(String discipline_id);



    List<Lecture> findByLecturerAndStartsOnBetween(Lecturer lecturer, Date startsAfter, Date startsBefore);

    List<Lecture> findByStudentsGroupAndStartsOnBetween(StudentsGroup group, Date startsAfter, Date startsBefore);

    List<Lecture> findByLectureGuestsContainingAndStartsOnBetween(Student exclusiveStudent, Date startsAfter, Date startsBefore);

    List<Lecture> findByRoomAndStartsOnBetween(LectureRoom room, Date startsAfter, Date startsBefore);
}
