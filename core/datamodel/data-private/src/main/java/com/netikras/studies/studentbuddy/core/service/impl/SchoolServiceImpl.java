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
import com.netikras.studies.studentbuddy.core.validator.SchoolValidator;
import com.netikras.tools.common.exception.ErrorsCollection;
import com.netikras.tools.common.exception.FriendlyUncheckedException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static com.netikras.tools.common.remote.http.HttpStatus.BAD_REQUEST;

@Service
public class SchoolServiceImpl implements SchoolService {

    @Resource
    private SchoolDao schoolDao;

    @Resource
    private DisciplineDao disciplineDao;

    @Resource
    private SchoolDepartmentDao departmentDao;

    @Resource
    private PersonnelDao personnelDao;

    @Resource
    private SchoolValidator schoolValidator;


    @Override
    public School createSchool(School school) {
        ErrorsCollection errors = schoolValidator.validateForCreation(school, null);
        if (!errors.isEmpty()) {
            throw new FriendlyUncheckedException()
                    .setMessage1("Cannot create new school")
                    .setMessage2("Validation errors: " + errors.size())
                    .setErrors(errors)
                    .setStatusCode(BAD_REQUEST)
                    ;
        }
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
        ErrorsCollection errors = schoolValidator.validateForCreation(department, null);
        if (!errors.isEmpty()) {
            throw new FriendlyUncheckedException()
                    .setMessage1("Cannot create new department")
                    .setMessage2("Validation errors: " + errors.size())
                    .setErrors(errors)
                    .setStatusCode(BAD_REQUEST)
                    ;
        }
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
        ErrorsCollection errors = schoolValidator.validateForCreation(member, null);
        if (!errors.isEmpty()) {
            throw new FriendlyUncheckedException()
                    .setMessage1("Cannot create new personnel member")
                    .setMessage2("Validation errors: " + errors.size())
                    .setErrors(errors)
                    .setStatusCode(BAD_REQUEST)
                    ;
        }
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
    public List<PersonnelMember> getAllPersonnelBySchool(String schoolId) {
        return personnelDao.findAllBySchool_Id(schoolId);
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
    public void deleteDiscipline(String id) {
        disciplineDao.delete(id);
    }

    @Override
    public List<Discipline> getAllDisciplinesBySchoolId(String id) {
        return disciplineDao.findAllBySchool_Id(id);
    }

    @Override
    public List<School> getAllSchools() {
        return schoolDao.findAll();
    }

    @Override
    public List<School> searchAllSchoolsByTitle(String query) {
        return schoolDao.findAllByTitleLikeIgnoreCase(schoolDao.wrapSearchString(query));
    }

    @Override
    public List<SchoolDepartment> searchAllDepartmentsByTitle(String query) {
        return departmentDao.findAllByTitleLikeIgnoreCase(schoolDao.wrapSearchString(query));
    }

    @Override
    public List<PersonnelMember> searchAllPersonnelByTitle(String query) {
        return personnelDao.findAllByTitleLikeIgnoreCase(personnelDao.wrapSearchString(query));
    }

    @Override
    public List<PersonnelMember> getAllPersonnelByTitle(String title) {
        return personnelDao.findAllByTitle(title);
    }

    @Override
    public List<PersonnelMember> getAllPersonnelByPerson(String personId) {
        return personnelDao.findAllByPerson_Id(personId);
    }

    @Override
    public List<PersonnelMember> searchAllPersonnelByFirstName(String query) {
        return personnelDao.findAllByPerson_FirstNameLikeIgnoreCase(personnelDao.wrapSearchString(query));
    }

    @Override
    public List<PersonnelMember> searchAllPersonnelByLastName(String query) {
        return personnelDao.findAllByPerson_LastNameLikeIgnoreCase(personnelDao.wrapSearchString(query));
    }

    @Override
    public List<PersonnelMember> searchAllPersonnelByIdentifier(String identifier) {
        return personnelDao.findAllByPerson_IdentificationIgnoreCase(personnelDao.wrapSearchString(identifier));
    }

    @Override
    public List<PersonnelMember> searchAllPersonnelByPersonalCode(String code) {
        return personnelDao.findAllByPerson_PersonalCodeLikeIgnoreCase(personnelDao.wrapSearchString(code));
    }

    @Override
    public List<Discipline> searchAllDisciplinesByTitle(String query) {
        return disciplineDao.findAllByTitleLikeIgnoreCase(disciplineDao.wrapSearchString(query));
    }

    @Override
    public List<Discipline> searchAllDisciplinesByDescription(String query) {
        return disciplineDao.findAllByDescriptionLikeIgnoreCase(disciplineDao.wrapSearchString(query));
    }
}
