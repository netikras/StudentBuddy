package com.netikras.studies.studentbuddy.core.service.impl;

import com.netikras.studies.studentbuddy.commons.exception.StudBudUncheckedException;
import com.netikras.studies.studentbuddy.core.data.api.dao.LecturerDao;
import com.netikras.studies.studentbuddy.core.data.api.model.Discipline;
import com.netikras.studies.studentbuddy.core.data.api.model.DisciplineLecturer;
import com.netikras.studies.studentbuddy.core.data.api.model.Lecturer;
import com.netikras.studies.studentbuddy.core.data.api.model.Person;
import com.netikras.studies.studentbuddy.core.service.LecturerService;
import com.netikras.studies.studentbuddy.core.validator.SchoolValidator;
import com.netikras.tools.common.exception.ErrorsCollection;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static com.netikras.tools.common.remote.http.HttpStatus.BAD_REQUEST;
import static com.netikras.tools.common.remote.http.HttpStatus.CONFLICT;

@Service
public class LecturerServiceImpl implements LecturerService {


    @Resource
    private LecturerDao lecturerDao;

    @Resource
    private SchoolValidator schoolValidator;


    @Override
    public Lecturer getLecturerByPerson(String personId) {
        return lecturerDao.findByPerson_Id(personId);
    }

    @Override
    public Lecturer getLecturer(String id) {
        return lecturerDao.findOne(id);
    }

    @Override
    public Lecturer createLecturer(Lecturer lecturer) {

        ErrorsCollection errors = schoolValidator.validateForCreation(lecturer, null);
        if (!errors.isEmpty()) {
            throw new StudBudUncheckedException()
                    .setMessage1("Cannot create new lecturer")
                    .setMessage2("Validation errors: " + errors.size())
                    .setErrors(errors)
                    .setStatusCode(BAD_REQUEST)
                    ;
        }

        return lecturerDao.save(lecturer);
    }

    @Override
    public Lecturer createLecturer(Person person) {
        Lecturer lecturer = new Lecturer();
        lecturer.setPerson(person);

        ErrorsCollection errors = schoolValidator.validateForCreation(lecturer, null);
        if (!errors.isEmpty()) {
            throw new StudBudUncheckedException()
                    .setMessage1("Cannot create new lecturer")
                    .setMessage2("Validation errors: " + errors.size())
                    .setProbableCause(person == null ? "" : person.getIdentification())
                    .setErrors(errors)
                    .setStatusCode(BAD_REQUEST)
                    ;
        }

        return lecturer;
    }

    @Override
    public Lecturer updateLecturer(Lecturer lecturer) {
        return lecturerDao.save(lecturer);
    }

    @Override
    public void deleteLecturer(String id) {
        lecturerDao.delete(id);
    }

    @Override
    public void deleteLecturerByPerson(String personId) {
        lecturerDao.deleteAllByPerson_Id(personId);
    }

    @Override
    public List<Lecturer> findLecturersByDiscipline(String disciplineId) {
        return lecturerDao.findAllByDisciplineLecturers_Discipline_Id(disciplineId);
    }


    // search

    @Override
    public List<Lecturer> searchAllByDegree(String query) {
        return lecturerDao.findAllByDegreeLikeIgnoreCase(lecturerDao.wrapSearchString(query));
    }

    @Override
    public List<Lecturer> searchAllByFirstName(String query) {
        return lecturerDao.findAllByPerson_FirstNameLikeIgnoreCase(lecturerDao.wrapSearchString(query));
    }

    @Override
    public List<Lecturer> searchAllByLastName(String query) {
        return lecturerDao.findAllByPerson_LastNameLikeIgnoreCase(lecturerDao.wrapSearchString(query));
    }

    @Override
    public void attachToDiscipline(Lecturer lecturer, Discipline discipline) {
        List<DisciplineLecturer> disciplineLecturers = lecturer.getDisciplineLecturers();
        if (disciplineLecturers != null) {
            for (DisciplineLecturer disciplineLecturer : disciplineLecturers) {
                if (discipline.equals(disciplineLecturer.getDiscipline())) {
                    throw new StudBudUncheckedException()
                            .setMessage1("Cannot assign lecturer to new discipline")
                            .setMessage2("Lecturer is already assigned to that discipline")
                            .setProbableCause("lecturer.id=" + lecturer.getId() + ", discipline.id=" + discipline.getId())
                            .setStatusCode(CONFLICT)
                            ;
                }
            }
        } else {
            lecturer.setDisciplineLecturers(new ArrayList<>());
        }

        DisciplineLecturer disciplineLecturer = new DisciplineLecturer();
        disciplineLecturer.setDiscipline(discipline);
        disciplineLecturer.setLecturer(lecturer);

        lecturer.getDisciplineLecturers().add(disciplineLecturer);

        updateLecturer(lecturer);
    }

    @Override
    public void detatchFromDiscipline(Lecturer lecturer, Discipline discipline) {
        List<DisciplineLecturer> disciplineLecturers = lecturer.getDisciplineLecturers();
        int initialDiscCount = disciplineLecturers.size();
        if (disciplineLecturers != null) {
            disciplineLecturers.removeIf(disciplineLecturer -> discipline.equals(disciplineLecturer.getDiscipline()));
        }

        if (initialDiscCount != disciplineLecturers.size()) { // dirty
            updateLecturer(lecturer);
        }
    }
}
