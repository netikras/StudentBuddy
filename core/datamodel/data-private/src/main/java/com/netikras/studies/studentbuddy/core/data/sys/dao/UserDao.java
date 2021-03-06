package com.netikras.studies.studentbuddy.core.data.sys.dao;

import com.netikras.studies.studentbuddy.core.data.api.dao.JpaRepo;
import com.netikras.studies.studentbuddy.core.data.sys.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends JpaRepo<User> {

    User findByNameAndPasswordHash(String name, String passwordHash);

    User findByName(String name);

    User findByPerson_Id(String person_id);

    List<User> findAllByPersonIsNull();

    List<User> findAllByNameLikeIgnoreCase(String name);

    List<User> findAllByPerson_FirstNameLikeIgnoreCase(String person_firstName);

    List<User> findAllByPerson_LastNameLikeIgnoreCase(String person_lastName);

    List<User> findAllByRoles_Role_NameContaining(String roleName);

    User findByAlias(String alias);
}
