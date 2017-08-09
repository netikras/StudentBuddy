package com.netikras.studies.studentbuddy.core.service.impl;

import com.netikras.studies.studentbuddy.core.data.api.dao.DisciplineDao;
import com.netikras.studies.studentbuddy.core.data.api.dao.PersonnelDao;
import com.netikras.studies.studentbuddy.core.data.api.dao.SchoolDao;
import com.netikras.studies.studentbuddy.core.data.api.dao.SchoolDepartmentDao;
import com.netikras.studies.studentbuddy.core.data.api.model.Discipline;
import com.netikras.studies.studentbuddy.core.data.api.model.PersonnelMember;
import com.netikras.studies.studentbuddy.core.data.api.model.School;
import com.netikras.studies.studentbuddy.core.data.api.model.SchoolDepartment;
import com.netikras.studies.studentbuddy.core.service.SchoolService;

import javax.annotation.Resource;

public class SchoolServiceImpl implements SchoolService {

    @Resource
    private SchoolDao schoolDao;

    @Resource
    private DisciplineDao disciplineDao;

    @Resource
    private SchoolDepartmentDao departmentDao;

    @Resource
    private PersonnelDao personnelDao;


    @Override
    public School createSchool(School school) {
        return schoolDao.save(school);
    }

    @Override
    public School getSchool(String id) {
        return schoolDao.findOne(id);
    }

    @Override
    public School updateSchool(School school) {
        return schoolDao.save(school);
    }

    @Override
    public void deleteSchool(String id) {
        schoolDao.delete(id);
    }

    @Override
    public SchoolDepartment createSchoolDepartment(SchoolDepartment department) {
        return departmentDao.save(department);
    }

    @Override
    public SchoolDepartment getSchoolDepartment(String id) {
        return departmentDao.findOne(id);
    }

    @Override
    public SchoolDepartment updateSchoolDepartment(SchoolDepartment department) {
        return departmentDao.save(department);
    }

    @Override
    public void deleteSchoolDepartment(String id) {
        departmentDao.delete(id);
    }

    @Override
    public PersonnelMember createPersonnelMember(PersonnelMember member) {
        return personnelDao.save(member);
    }

    @Override
    public PersonnelMember getPersonnelMember(String id) {
        return personnelDao.findOne(id);
    }

    @Override
    public PersonnelMember updatePersonnelMember(PersonnelMember member) {
        return personnelDao.save(member);
    }

    @Override
    public void deletePersonnelMember(String id) {
        personnelDao.delete(id);
    }

    @Override
    public Discipline createDiscipline(Discipline discipline) {
        return disciplineDao.save(discipline);
    }

    @Override
    public Discipline getDiscipline(String id) {
        return disciplineDao.findOne(id);
    }

    @Override
    public Discipline updateDiscipline(Discipline discipline) {
        return disciplineDao.save(discipline);
    }

    @Override
    public void removeDiscipline(String id) {
        disciplineDao.delete(id);
    }
}
