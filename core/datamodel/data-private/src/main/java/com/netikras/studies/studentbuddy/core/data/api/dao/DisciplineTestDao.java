package com.netikras.studies.studentbuddy.core.data.api.dao;

import com.netikras.studies.studentbuddy.core.data.api.model.DisciplineTest;

import java.util.Date;
import java.util.List;

public interface DisciplineTestDao extends JpaRepo<DisciplineTest> {

    List<DisciplineTest> findAllByLecture_Id(String lecture_id);

    List<DisciplineTest> findAllByDiscipline_Id(String discipline_id);

    List<DisciplineTest> findAllByDiscipline_IdAndStartsOnBetween(String discipline_id, Date startsAfter, Date startsBefore);

    List<DisciplineTest> findAllByDiscipline_IdAndLecture_StudentsGroup_Id(String discipline_id, String lecture_studentsGroup_id);

    List<DisciplineTest> findAllByDiscipline_IdAndLecture_StudentsGroup_IdAndStartsOnBetween(String discipline_id, String lecture_studentsGroup_id, Date startsAfter, Date startsBefore);

    void deleteAllByDiscipline_Id(String discipline_id);

    void deleteAllByLecture_StudentsGroup_Id(String lecture_studentsGroup_id);

    void deleteAllByLecture_StudentsGroup_IdAndDiscipline_Id(String lecture_studentsGroup_id, String discipline_id);

    List<DisciplineTest> findAllByDescriptionLikeIgnoreCase(String description);

}
