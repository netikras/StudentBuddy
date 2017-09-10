package com.netikras.studies.studentbuddy.core.data.api.dao;

import com.netikras.studies.studentbuddy.core.data.api.model.Student;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface StudentDao extends JpaRepo<Student> {

    List<Student> findByPerson_Id(String person_id);

    void deleteAllByPerson_Id(String person_id);

    List<Student> findAllByGroup_Id(String group_id);

    List<Student> findAllByIdIsIn(Collection<String> id);

    List<Student> findAllByPerson_FirstNameLikeIgnoreCase(String person_firstName);

    List<Student> findAllByPerson_LastNameLikeIgnoreCase(String person_lastName);

}
