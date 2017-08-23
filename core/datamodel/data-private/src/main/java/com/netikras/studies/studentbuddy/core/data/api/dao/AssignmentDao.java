package com.netikras.studies.studentbuddy.core.data.api.dao;

import com.netikras.studies.studentbuddy.core.data.api.model.Assignment;
import com.netikras.studies.studentbuddy.core.data.api.model.Student;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AssignmentDao extends JpaRepo<Assignment> {


    List<Assignment> findAllByLecture_Id(String lecture_id);

    List<Assignment> findAllByLecture_StudentsGroup_Members_IdContainingAndDueDateBetween(String studentId, Date after, Date before);

    List<Assignment> findAllByDiscipline_IdAndDueDateBetween(String discipline_id, Date after, Date before);

    List<Assignment> findAllByLecture_StudentsGroup_IdAndDueDateBetween(String lecture_studentsGroup_id, Date after, Date before);

    List<Assignment> findAllByDiscipline_IdAndLecture_StudentsGroup_IdAndDueDateBetween(String discipline_id, String lecture_studentsGroup_id,
                                                                                        Date after, Date before);

    List<Assignment> findAllByDiscipline_IdAndLecture_StudentsGroup_Members_IdContainingAndDueDateBetween(String discipline_id, String studentId,
                                                                                                          Date after, Date before);

    List<Assignment> findAllByDescriptionLikeIgnoreCase(String description);

}
