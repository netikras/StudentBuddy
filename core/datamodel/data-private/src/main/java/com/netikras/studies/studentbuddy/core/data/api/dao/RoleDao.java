package com.netikras.studies.studentbuddy.core.data.api.dao;

import com.netikras.studies.studentbuddy.core.data.sys.model.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends JpaRepo<Role> {

    Role findByName(String name);

}
