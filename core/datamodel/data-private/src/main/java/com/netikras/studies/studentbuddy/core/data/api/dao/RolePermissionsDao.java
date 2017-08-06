package com.netikras.studies.studentbuddy.core.data.api.dao;

import com.netikras.studies.studentbuddy.core.data.sys.model.RolePermissions;

import java.util.List;

public interface RolePermissionsDao extends JpaRepo<RolePermissions> {

    RolePermissions findByRole_Name(String role_name);


}
