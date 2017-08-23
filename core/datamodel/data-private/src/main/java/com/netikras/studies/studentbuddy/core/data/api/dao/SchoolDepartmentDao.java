package com.netikras.studies.studentbuddy.core.data.api.dao;

import com.netikras.studies.studentbuddy.core.data.api.model.SchoolDepartment;

import java.util.List;

public interface SchoolDepartmentDao extends JpaRepo<SchoolDepartment> {

    List<SchoolDepartment> findAllByTitleLikeIgnoreCase(String substring);

}
