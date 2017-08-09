package com.netikras.studies.studentbuddy.core.service;

import com.netikras.studies.studentbuddy.core.data.api.model.Discipline;
import com.netikras.studies.studentbuddy.core.data.api.model.PersonnelMember;
import com.netikras.studies.studentbuddy.core.data.api.model.School;
import com.netikras.studies.studentbuddy.core.data.api.model.SchoolDepartment;

public interface SchoolService {

    School createSchool(School school);

    School getSchool(String id);

    School updateSchool(School school);

    void deleteSchool(String id);


    SchoolDepartment createSchoolDepartment(SchoolDepartment department);

    SchoolDepartment getSchoolDepartment(String id);

    SchoolDepartment updateSchoolDepartment(SchoolDepartment department);

    void deleteSchoolDepartment(String id);


    PersonnelMember createPersonnelMember(PersonnelMember member);

    PersonnelMember getPersonnelMember(String id);

    PersonnelMember updatePersonnelMember(PersonnelMember member);

    void deletePersonnelMember(String id);


    Discipline createDiscipline(Discipline discipline);

    Discipline getDiscipline(String id);

    Discipline updateDiscipline(Discipline discipline);

    void removeDiscipline(String id);

}
