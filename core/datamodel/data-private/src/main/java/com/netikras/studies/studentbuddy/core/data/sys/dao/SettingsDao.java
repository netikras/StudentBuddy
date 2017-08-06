package com.netikras.studies.studentbuddy.core.data.sys.dao;

import com.netikras.studies.studentbuddy.core.data.api.dao.JpaRepo;
import com.netikras.studies.studentbuddy.core.data.sys.model.SystemSetting;
import org.springframework.stereotype.Repository;

@Repository
public interface SettingsDao extends JpaRepo<SystemSetting> {

    SystemSetting findByName(String name);

    void deleteByName(String name);



}
