package com.netikras.studies.studentbuddy.core.data.api.dao;

import com.netikras.studies.studentbuddy.core.data.api.model.Student;
import com.netikras.studies.studentbuddy.core.data.api.model.StudentsGroup;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentsGroupDao extends JpaRepo<StudentsGroup> {

    List<StudentsGroup> findByMembersContaining(Student member);

    StudentsGroup findByTitle(String title);

    List<StudentsGroup> findAllByTitleLikeIgnoreCase(String substring);

}
