package com.netikras.studies.studentbuddy.core.data.api.dao;

import com.netikras.studies.studentbuddy.core.data.api.model.PersonnelMember;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonnelDao extends JpaRepo<PersonnelMember> {

    List<PersonnelMember> findAllByTitleLikeIgnoreCase(String title);

    List<PersonnelMember> findAllByPerson_FirstNameLikeIgnoreCase(String person_firstName);

    List<PersonnelMember> findAllByPerson_LastNameLikeIgnoreCase(String person_lastName);

    List<PersonnelMember> findAllByPerson_Id(String person_id);

    List<PersonnelMember> findAllByTitle(String title);


}
