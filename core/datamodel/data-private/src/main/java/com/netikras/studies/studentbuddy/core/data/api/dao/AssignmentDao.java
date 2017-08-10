package com.netikras.studies.studentbuddy.core.data.api.dao;

import com.netikras.studies.studentbuddy.core.data.api.model.Assignment;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AssignmentDao extends JpaRepo<Assignment> {


    List<Assignment> findAllByLecture_IdAndDueDateBetween(String lecture_id, Date after, Date before);

    List<Assignment> findAllByDiscipline_IdAndDueDateBetween(String discipline_id, Date after, Date before);

    List<Assignment> findAllByLecture_StudentsGroup_IdAndDueDateBetween(String lecture_studentsGroup_id, Date after, Date before);

    List<Assignment> findAllByDiscipline_IdAndLecture_StudentsGroup_IdAndDueDateBetween(String discipline_id, String lecture_studentsGroup_id,
                                                                                        Date after, Date before);


}
