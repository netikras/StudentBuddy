package com.netikras.studies.studentbuddy.core.data.api.dao;

import com.netikras.studies.studentbuddy.core.data.api.model.Course;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseDao extends JpaRepo<Course> {

    Course findByTitle(String title);

    List<Course> findAllByDiscipline_Id(String discipline_id);

    List<Course> findAllByDiscipline_School_Id(String discipline_school_id);

    List<Course> findAllByTitleLikeIgnoreCase(String title);

}
