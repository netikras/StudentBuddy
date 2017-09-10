package com.netikras.studies.studentbuddy.core.data.api.dao;

import com.netikras.studies.studentbuddy.core.data.api.model.Lecturer;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LecturerDao extends JpaRepo<Lecturer> {

    List<Lecturer> findByPerson_Id(String person_id);

    void deleteAllByPerson_Id(String person_id);

    List<Lecturer> findAllByDisciplineLecturers_Discipline_Id(String disciplineId);

    List<Lecturer> findAllByDegreeLikeIgnoreCase(String degree);

    List<Lecturer> findAllByPerson_FirstNameLikeIgnoreCase(String person_firstName);

    List<Lecturer> findAllByPerson_LastNameLikeIgnoreCase(String person_lastName);


}
