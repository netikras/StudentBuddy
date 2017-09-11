package com.netikras.studies.studentbuddy.core.service.impl;

import com.netikras.studies.studentbuddy.commons.exception.StudBudUncheckedException;
import com.netikras.studies.studentbuddy.core.data.api.dao.DisciplineDao;
import com.netikras.studies.studentbuddy.core.data.api.dao.PersonnelDao;
import com.netikras.studies.studentbuddy.core.data.api.dao.SchoolDao;
import com.netikras.studies.studentbuddy.core.data.api.dao.SchoolDepartmentDao;
import com.netikras.studies.studentbuddy.core.data.api.model.Discipline;
import com.netikras.studies.studentbuddy.core.data.api.model.PersonnelMember;
import com.netikras.studies.studentbuddy.core.data.api.model.School;
import com.netikras.studies.studentbuddy.core.data.api.model.SchoolDepartment;
import com.netikras.studies.studentbuddy.core.data.api.model.Website;
import com.netikras.studies.studentbuddy.core.service.LectureService;
import com.netikras.studies.studentbuddy.core.service.LecturerService;
import com.netikras.studies.studentbuddy.core.service.SchoolService;
import com.netikras.studies.studentbuddy.core.service.StudentService;
import com.netikras.studies.studentbuddy.core.validator.SchoolValidator;
import com.netikras.tools.common.exception.ErrorsCollection;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static com.netikras.tools.common.remote.http.HttpStatus.BAD_REQUEST;
import static com.netikras.tools.common.security.IntegrityUtils.isNullOrEmpty;

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

    @Resource
    private LectureService lectureService;
    @Resource
    private LecturerService lecturerService;
    @Resource
    private StudentService studentService;

    @Resource
    private EntityManager entityManager;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public School createSchool(School school) {
        List<SchoolDepartment> departments = school.getDepartments();
        school.setDepartments(null);

        ErrorsCollection errors = schoolValidator.validateForCreation(school, null);
        if (!errors.isEmpty()) {
            throw new StudBudUncheckedException()
                    .setMessage1("Cannot create new school")
                    .setMessage2("Validation errors: " + errors.size())
                    .setErrors(errors)
                    .setStatusCode(BAD_REQUEST)
                    ;
        }

        school = schoolDao.save(school);

        if (!isNullOrEmpty(departments)) {
            for (SchoolDepartment department : departments) {
                department.setSchool(school);
                createSchoolDepartment(department);
            }
        }

        school = schoolDao.findOne(school.getId());

        return school;
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
        School school = getSchool(id);
        ErrorsCollection errors = schoolValidator.validateForRemoval(school, null);
        if (!errors.isEmpty()) {
            throw new StudBudUncheckedException()
                    .setMessage1("Cannot remove school")
                    .setMessage2("Validation errors: " + errors.size())
                    .setProbableCause(id)
                    .setErrors(errors)
                    .setStatusCode(BAD_REQUEST)
                    ;
        }
        schoolDao.delete(id);
    }

    @Override
    @Transactional
    public void purgeSchool(String id) {
        School school = getSchool(id);
        if (school == null) {
            return;
        }

        if (!isNullOrEmpty(school.getDepartments())) {
            school.getDepartments().forEach(department -> purgeSchoolDepartment(department.getId()));
            school.getDepartments().clear();
        }

        if (!isNullOrEmpty(school.getDisciplines())) {
            school.getDisciplines().forEach(discipline -> purgeDiscipline(discipline.getId()));
            school.getDisciplines().clear();
        }

        if (!isNullOrEmpty(school.getLecturers())) {
            school.getLecturers().forEach(lecturer -> lecturerService.purgeLecturer(lecturer.getId()));
            school.getLecturers().clear();
        }

        if (!isNullOrEmpty(school.getPersonnel())) {
            school.getPersonnel().forEach(member -> purgePersonnelMember(member.getId()));
            school.getPersonnel().clear();
        }

        if (!isNullOrEmpty(school.getStudents())) {
            school.getStudents().forEach(student -> studentService.purgeStudent(student.getId()));
            school.getStudents().clear();
        }

        if (!isNullOrEmpty(school.getGroups())) {
            school.getGroups().forEach(group -> studentService.purgeStudentsGroup(group.getId()));
            school.getGroups().clear();
        }

        school = getSchool(id);

        schoolDao.delete(id);
    }

    @Override
    public SchoolDepartment createSchoolDepartment(SchoolDepartment department) {
        ErrorsCollection errors = schoolValidator.validateForCreation(department, null);
        if (!errors.isEmpty()) {
            throw new StudBudUncheckedException()
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
        SchoolDepartment department = getSchoolDepartment(id);
        ErrorsCollection errors = schoolValidator.validateForRemoval(department, null);
        if (!errors.isEmpty()) {
            throw new StudBudUncheckedException()
                    .setMessage1("Cannot remove school department")
                    .setMessage2("Validation errors: " + errors.size())
                    .setProbableCause(id)
                    .setErrors(errors)
                    .setStatusCode(BAD_REQUEST)
                    ;
        }
        departmentDao.delete(id);
    }

    @Override
    @Transactional
    public void purgeSchoolDepartment(String id) {
        SchoolDepartment department = getSchoolDepartment(id);
        if (department == null) {
            return;
        }

        departmentDao.delete(id);
    }

    @Override
    public PersonnelMember createPersonnelMember(PersonnelMember member) {
        ErrorsCollection errors = schoolValidator.validateForCreation(member, null);
        if (!errors.isEmpty()) {
            throw new StudBudUncheckedException()
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
        PersonnelMember personnelMember = getPersonnelMember(id);
        ErrorsCollection errors = schoolValidator.validateForRemoval(personnelMember, null);
        if (!errors.isEmpty()) {
            throw new StudBudUncheckedException()
                    .setMessage1("Cannot remove personnel member")
                    .setMessage2("Validation errors: " + errors.size())
                    .setProbableCause(id)
                    .setErrors(errors)
                    .setStatusCode(BAD_REQUEST)
                    ;
        }
        personnelDao.delete(id);
    }

    @Override
    @Transactional
    public void purgePersonnelMember(String id) {
        PersonnelMember personnelMember = getPersonnelMember(id);
        if (personnelMember == null) {
            return;
        }

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
        Discipline discipline = getDiscipline(id);
        ErrorsCollection errors = schoolValidator.validateForRemoval(discipline, null);
        if (!errors.isEmpty()) {
            throw new StudBudUncheckedException()
                    .setMessage1("Cannot remove discipline")
                    .setMessage2("Validation errors: " + errors.size())
                    .setProbableCause(id)
                    .setErrors(errors)
                    .setStatusCode(BAD_REQUEST)
                    ;
        }
        disciplineDao.delete(id);
    }

    @Override
    @Transactional
    public void purgeDiscipline(String id) {
        Discipline discipline = getDiscipline(id);
        if (discipline == null) {
            return;
        }

        List<Website> websites = discipline.getSites();

        if (!isNullOrEmpty(discipline.getLectures())) {
            List<String> ids = new ArrayList<>();
            discipline.getLectures().forEach(lecture -> ids.add(lecture.getId()));
            lectureService.purgeLectures(ids);
        }

        if (!isNullOrEmpty(discipline.getAssignments())) {
            discipline.getAssignments().forEach(assignment -> lectureService.purgeLectureAssignment(assignment.getId()));
        }

        if (!isNullOrEmpty(discipline.getTests())) {
            discipline.getTests().forEach(test -> lectureService.purgeLectureTest(test.getId()));
        }

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


    // search


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
