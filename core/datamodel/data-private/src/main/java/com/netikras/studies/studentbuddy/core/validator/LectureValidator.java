package com.netikras.studies.studentbuddy.core.validator;

import com.netikras.studies.studentbuddy.core.data.api.dao.AssignmentDao;
import com.netikras.studies.studentbuddy.core.data.api.dao.DisciplineDao;
import com.netikras.studies.studentbuddy.core.data.api.dao.DisciplineTestDao;
import com.netikras.studies.studentbuddy.core.data.api.dao.LectureDao;
import com.netikras.studies.studentbuddy.core.data.api.dao.LectureRoomDao;
import com.netikras.studies.studentbuddy.core.data.api.dao.LecturerDao;
import com.netikras.studies.studentbuddy.core.data.api.dao.StudentsGroupDao;
import com.netikras.studies.studentbuddy.core.data.api.model.Assignment;
import com.netikras.studies.studentbuddy.core.data.api.model.Discipline;
import com.netikras.studies.studentbuddy.core.data.api.model.DisciplineTest;
import com.netikras.studies.studentbuddy.core.data.api.model.Lecture;
import com.netikras.studies.studentbuddy.core.data.api.model.LectureRoom;
import com.netikras.studies.studentbuddy.core.data.api.model.Lecturer;
import com.netikras.studies.studentbuddy.core.data.api.model.StudentsGroup;
import com.netikras.tools.common.exception.ErrorsCollection;
import com.netikras.tools.common.exception.ValidationError;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

import static com.netikras.tools.common.remote.http.HttpStatus.CONFLICT;
import static com.netikras.tools.common.remote.http.HttpStatus.NOT_FOUND;
import static com.netikras.tools.common.security.IntegrityUtils.ensureValue;
import static com.netikras.tools.common.security.IntegrityUtils.isNullOrEmpty;

@Component
public class LectureValidator {

    @Resource
    private LectureDao lectureDao;
    @Resource
    private LectureRoomDao roomDao;
    @Resource
    private DisciplineDao disciplineDao;
    @Resource
    private LecturerDao lecturerDao;
    @Resource
    private StudentsGroupDao groupDao;
    @Resource
    private DisciplineTestDao testDao;
    @Resource
    private AssignmentDao assignmentDao;

    @Transactional
    public ErrorsCollection validateForCreation(Lecture lecture, ErrorsCollection errors) {
        errors = ensure(errors);

        if (lecture == null) {
            errors.add(new ValidationError()
                    .setSuggestion("Cannot create a non-existing lecture")
                    .setMessage1("Lecture is not given")
                    .setStatus(NOT_FOUND.getCode())
            );
            return errors;
        }

        /*
        NOTE!
        1. Single group might have multiple lectures at the same time (split-groups)
        2. Multiple groups might have a lecture of the same discipline at the same time and delivered by the same lecturer
        3. etc.

        This is why I am not looking for collisions
         */

        Date startsOn = lecture.getStartsOn();
        Date endsOn = lecture.getEndsOn();


        if (startsOn != null && endsOn != null) {
            if (startsOn.getTime() > endsOn.getTime()) {
                errors.add(new ValidationError()
                        .setSuggestion("Lecture must start BEFORE it ends, not vice-versa")
                        .setMessage1("Lecture timing invalid")
                        .setStatus(CONFLICT.getCode())
                );
            }
        } else {
            if (startsOn == null) {
                errors.add(new ValidationError()
                        .setSuggestion("Lecture start time must be provided at creation time")
                        .setMessage1("Lecture start time missing")
                        .setStatus(NOT_FOUND.getCode())
                );
            }

            if (endsOn == null) {
                errors.add(new ValidationError()
                        .setSuggestion("Lecture start time must be provided at creation time")
                        .setMessage1("Lecture start time missing")
                        .setStatus(NOT_FOUND.getCode())
                );
            }
        }

        lecture.setRoom(fetch(lecture.getRoom()));
        if (lecture.getRoom() == null) {
            errors.add(new ValidationError()
                    .setSuggestion("Lecture must be linked to an existing room it will take place in")
                    .setMessage1("Lecture room missing")
                    .setStatus(NOT_FOUND.getCode())
            );
        }

        lecture.setDiscipline(fetch(lecture.getDiscipline()));
        if (lecture.getDiscipline() == null) {
            errors.add(new ValidationError()
                    .setSuggestion("Lecture must be linked to an existing discipline")
                    .setMessage1("Lecture discipline missing")
                    .setStatus(NOT_FOUND.getCode())
            );
        }

        lecture.setLecturer(fetch(lecture.getLecturer()));
        if (lecture.getDiscipline() == null) {
            errors.add(new ValidationError()
                    .setSuggestion("Lecture must be linked to an existing discipline")
                    .setMessage1("Lecture lecturer missing")
                    .setStatus(NOT_FOUND.getCode())
            );
        }

        lecture.setStudentsGroup(fetch(lecture.getStudentsGroup()));
        if (lecture.getStudentsGroup() == null) {
            errors.add(new ValidationError()
                    .setSuggestion("Lecture must be linked to an existing students group that is to be attending that lecture")
                    .setMessage1("Lecture group missing")
                    .setStatus(NOT_FOUND.getCode())
            );
        }

        return errors;
    }

    @Transactional
    public ErrorsCollection validateForCreation(DisciplineTest test, ErrorsCollection errors) {
        errors = ensure(errors);

        if (test == null) {
            errors.add(new ValidationError()
                    .setSuggestion("Cannot create a non-existing test")
                    .setMessage1("Test is not given")
                    .setStatus(NOT_FOUND.getCode())
            );
            return errors;
        }


        if (isNullOrEmpty(test.getDescription())) {
            errors.add(new ValidationError()
                    .setSuggestion("Test description is mandatory. It should give a hint on what topics will be included, type of test, etc.")
                    .setMessage1("Test description missing")
                    .setStatus(NOT_FOUND.getCode())
            );
        }

        test.setDiscipline(fetch(test.getDiscipline()));
        test.setLecture(fetch(test.getLecture()));

        if (test.getDiscipline() == null) {
            errors.add(new ValidationError()
                    .setSuggestion("Test must be linked to some already existing discipline")
                    .setMessage1("Test discipline missing")
                    .setStatus(NOT_FOUND.getCode())
            );
        }


        if (test.getLecture() == null) {
            if (test.getStartsOn() == null) {
                errors.add(new ValidationError()
                        .setSuggestion("Test start date&time are not clear. Either startsOn time or a valid lecture instance is required")
                        .setMessage1("Test lecture or timing missing")
                        .setStatus(NOT_FOUND.getCode())
                );
            } else {
                if (test.getDiscipline() == null) {
                    errors.add(new ValidationError()
                            .setSuggestion("Test scheduled for particular date&time and w/o assigned lecture instance must have a discipline specified")
                            .setMessage1("Test with start time discipline missing")
                            .setStatus(NOT_FOUND.getCode())
                    );
                }
            }
        }


        test.setId(null);
        return errors;
    }

    @Transactional
    public ErrorsCollection validateForCreation(Assignment assignment, ErrorsCollection errors) {
        errors = ensure(errors);

        if (assignment == null) {
            errors.add(new ValidationError()
                    .setSuggestion("Cannot create a non-existing assignment")
                    .setMessage1("Assignment is not given")
                    .setStatus(NOT_FOUND.getCode())
            );
            return errors;
        }

        if (isNullOrEmpty(assignment.getDescription())) {
            errors.add(new ValidationError()
                    .setSuggestion("Assignment must have a description clarifying on what, how, etc. should it be completed")
                    .setMessage1("Assignment description missing")
                    .setStatus(NOT_FOUND.getCode())
            );
        }

        assignment.setLecture(fetch(assignment.getLecture()));
        assignment.setDiscipline(fetch(assignment.getDiscipline()));

        if (assignment.getLecture() == null) {
            if (assignment.getDueDate() == null) {
                errors.add(new ValidationError()
                        .setSuggestion("Assignment completion date&time are not clear. Either dueDate or a valid lecture instance is required")
                        .setMessage1("Assignment lecture or timing missing")
                        .setStatus(NOT_FOUND.getCode())
                );
            } else {
                if (assignment.getDiscipline() == null) {
                    errors.add(new ValidationError()
                            .setSuggestion("Assignment pending for particular date&time and w/o assigned lecture instance must have a discipline specified")
                            .setMessage1("Assignment with due-time discipline missing")
                            .setStatus(NOT_FOUND.getCode())
                    );
                }
            }
        }

        assignment.setId(null);
        return errors;
    }


    @Transactional
    protected Lecture fetch(Lecture lecture) {
        Lecture existing = null;

        if (lecture == null) return existing;

        if (!isNullOrEmpty(lecture.getId())) {
            existing = lectureDao.findOne(lecture.getId());
        }

        return existing;
    }


    @Transactional
    protected LectureRoom fetch(LectureRoom room) {
        LectureRoom existing = null;

        if (room == null) return existing;

        if (!isNullOrEmpty(room.getId())) {
            existing = roomDao.findOne(room.getId());
        }

        return existing;
    }

    @Transactional
    protected Discipline fetch(Discipline discipline) {
        Discipline existing = null;

        if (discipline == null) return existing;

        if (!isNullOrEmpty(discipline.getId())) {
            existing = disciplineDao.findOne(discipline.getId());
        }

        return existing;
    }

    @Transactional
    protected Lecturer fetch(Lecturer lecturer) {
        Lecturer existing = null;

        if (lecturer == null) return existing;

        if (!isNullOrEmpty(lecturer.getId())) {
            existing = lecturerDao.findOne(lecturer.getId());
        }

        return existing;
    }

    @Transactional
    protected StudentsGroup fetch(StudentsGroup group) {
        StudentsGroup existing = null;

        if (group == null) return existing;

        if (!isNullOrEmpty(group.getId())) {
            existing = groupDao.findOne(group.getId());
        }

        return existing;
    }

    @Transactional
    protected DisciplineTest fetch(DisciplineTest test) {
        DisciplineTest existing = null;

        if (test == null) return existing;

        if (!isNullOrEmpty(test.getId())) {
            existing = testDao.findOne(test.getId());
        }

        return existing;
    }

    @Transactional
    protected Assignment fetch(Assignment assignment) {
        Assignment existing = null;

        if (assignment == null) return existing;

        if (!isNullOrEmpty(assignment.getId())) {
            existing = assignmentDao.findOne(assignment.getId());
        }

        return existing;
    }


    private ErrorsCollection ensure(ErrorsCollection errors) {
        return ensureValue(errors, ErrorsCollection.class);
    }

}
