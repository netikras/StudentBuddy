package com.netikras.studies.studentbuddy.core.data.api.dao;

import com.netikras.studies.studentbuddy.core.data.api.model.Person;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonDao extends JpaRepo<Person> {


}
