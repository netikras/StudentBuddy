package com.netikras.studies.studentbuddy.core.data.sys.dao;

import com.netikras.studies.studentbuddy.core.data.api.dao.JpaRepo;
import com.netikras.studies.studentbuddy.core.data.sys.model.PasswordRequirement;

public interface PasswordRequirementsDao extends JpaRepo<PasswordRequirement> {


    PasswordRequirement findByTitle(String title);
}
