package com.netikras.studies.studentbuddy.core.data.api.dao;

import com.netikras.studies.studentbuddy.core.data.api.model.PersonelMember;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonelDao extends JpaRepo<PersonelMember> {
}
