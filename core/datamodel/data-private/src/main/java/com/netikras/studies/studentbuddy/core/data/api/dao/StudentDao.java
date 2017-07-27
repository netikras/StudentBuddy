package com.netikras.studies.studentbuddy.core.data.api.dao;

import com.netikras.studies.studentbuddy.core.data.api.model.Student;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentDao extends JpaRepo<Student> {

}
