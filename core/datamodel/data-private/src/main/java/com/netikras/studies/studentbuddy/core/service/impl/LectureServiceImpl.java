package com.netikras.studies.studentbuddy.core.service.impl;

import com.netikras.studies.studentbuddy.core.data.api.dao.AssignmentDao;
import com.netikras.studies.studentbuddy.core.data.api.dao.DisciplineTestDao;
import com.netikras.studies.studentbuddy.core.data.api.dao.LectureDao;
import com.netikras.studies.studentbuddy.core.data.api.model.Assignment;
import com.netikras.studies.studentbuddy.core.data.api.model.DisciplineTest;
import com.netikras.studies.studentbuddy.core.data.api.model.Lecture;
import com.netikras.studies.studentbuddy.core.service.LectureService;
import com.netikras.studies.studentbuddy.core.validator.LectureValidator;
import com.netikras.tools.common.exception.ErrorsCollection;
import com.netikras.tools.common.exception.FriendlyUncheckedException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import static com.netikras.tools.common.remote.http.HttpStatus.BAD_REQUEST;

@Service
public class LectureServiceImpl implements LectureService {

    @Resource
    private LectureDao lectureDao;

    @Resource
    private DisciplineTestDao disciplineTestDao;

    @Resource
    private AssignmentDao assignmentDao;

    @Resource
    private LectureValidator lectureValidator;


    @Override
    public Lecture findLecture(String id) {
        return lectureDao.findOne(id);
    }

    @Override
    public List<Lecture> findLectures(Date startsAfter, Date startsBefore) {
        return lectureDao.findAllByStartsOnBetween(startsAfter, startsBefore);
    }

    @Override
    public List<Lecture> findLecturesForDiscipline(String disciplineId, Date startsAfter, Date startsBefore) {
        return lectureDao.findAllByDiscipline_IdAndStartsOnBetween(disciplineId, startsAfter, startsBefore);
    }

    @Override
    public List<Lecture> findLecturesForStudentsGroup(String groupId, Date startsAfter, Date startsBefore) {
        return lectureDao.findAllByStudentsGroup_IdAndStartsOnBetween(groupId, startsAfter, startsBefore);
    }

    @Override
    public List<Lecture> findLecturesForLecturer(String lecturerId, Date startsAfter, Date startsBefore) {
        return lectureDao.findAllByLecturer_IdAndStartsOnBetween(lecturerId, startsAfter, startsBefore);
    }

    @Override
    public List<Lecture> findLecturesForRoom(String roomId, Date startsAfter, Date startsBefore) {
        return lectureDao.findAllByRoom_IdAndStartsOnBetween(roomId, startsAfter, startsBefore);
    }

    @Override
    public List<Lecture> findLecturesForStudentsGroupAndDiscipline(String groupId, String disciplineId, Date startsAfter, Date startsBefore) {
        return lectureDao.findAllByStudentsGroup_IdAndDiscipline_IdAndStartsOnBetween(groupId, disciplineId, startsAfter, startsBefore);
    }

    @Override
    public List<Lecture> findLecturesForLecturerAndDiscipline(String lecturerId, String disciplineId, Date startsAfter, Date startsBefore) {
        return lectureDao.findAllByLecturer_IdAndDiscipline_IdAndStartsOnBetween(lecturerId, disciplineId, startsAfter, startsBefore);
    }

    @Override
    public List<Lecture> findLecturesForRoomAndDiscipline(String roomId, String disciplineId, Date startsAfter, Date startsBefore) {
        return lectureDao.findAllByRoom_IdAndDiscipline_IdAndStartsOnBetween(roomId, disciplineId, startsAfter, startsBefore);
    }

    @Override
    public List<Lecture> findLecturesForBuilding(String buildingId, Date startsAfter, Date startsBefore) {
        return lectureDao.findAllByRoom_Floor_Building_IdAndStartsOnBetween(buildingId, startsAfter, startsBefore);
    }

    @Override
    public List<Lecture> findLecturesForGuest(String guestId, Date startsAfter, Date startsBefore) {
        return lectureDao.findAllByLectureGuests_IdAndStartsOnBetween(guestId, startsAfter, startsBefore);
    }

    @Override
    public List<Lecture> findLecturesForGuestAndDiscipline(String guestId, String disciplineId, Date startsAfter, Date startsBefore) {
        return lectureDao.findAllByLectureGuests_IdAndDiscipline_IdAndStartsOnBetween(guestId, disciplineId, startsAfter, startsBefore);
    }

    public List<Lecture> findLecturesForStudent(String studentId, Date startsAfter, Date startsBefore) {
        return lectureDao.findAllByStudentsGroup_Members_IdAndStartsOnBetween(studentId, startsAfter, startsBefore);
    }

    @Override
    public Lecture createLecture(Lecture lecture) {
        ErrorsCollection errors = lectureValidator.validateForCreation(lecture, null);
        if (!errors.isEmpty()) {
            throw new FriendlyUncheckedException()
                    .setMessage1("Cannot create new lecture")
                    .setMessage2("Validation errors: " + errors.size())
                    .setErrors(errors)
                    .setStatusCode(BAD_REQUEST)
                    ;
        }
        return lectureDao.save(lecture);
    }

    @Override
    public void createLectures(List<Lecture> lectures) {
        if (lectures == null) return;

        for (Lecture lecture : lectures) {
            createLecture(lecture);
        }
    }

    @Override
    public void deleteLecture(String id) {
        lectureDao.delete(id);
    }

    @Override
    public void deleteLecturesByIds(List<String> ids) {
        if (ids == null) return;

        lectureDao.deleteAllByIdIn(ids);
    }

    @Override
    public void deleteLectures(List<Lecture> lectures) {
        lectureDao.delete(lectures);
    }

    @Override
    public void deleteLecturesByDiscipline(String disciplineId) {
        lectureDao.deleteAllByDiscipline_Id(disciplineId);
    }

    @Override
    public Lecture updateLecture(Lecture lecture) {
        return lectureDao.save(lecture);
    }




    @Override
    public DisciplineTest createTest(DisciplineTest test) {
        ErrorsCollection errors = lectureValidator.validateForCreation(test, null);
        if (!errors.isEmpty()) {
            throw new FriendlyUncheckedException()
                    .setMessage1("Cannot create new test")
                    .setMessage2("Validation errors: " + errors.size())
                    .setErrors(errors)
                    .setStatusCode(BAD_REQUEST)
                    ;
        }
        return disciplineTestDao.save(test);
    }

    @Override
    public DisciplineTest updateTest(DisciplineTest test) {
        return disciplineTestDao.save(test);
    }

    @Override
    public DisciplineTest getTest(String id) {
        return disciplineTestDao.findOne(id);
    }

    @Override
    public List<DisciplineTest> getTestsForDiscipline(String disciplineId) {
        return disciplineTestDao.findAllByDiscipline_Id(disciplineId);
    }

    @Override
    public List<DisciplineTest> getTestsForDiscipline(String disciplineId, Date startsAfter, Date startsBefore) {
        return disciplineTestDao.findAllByDiscipline_IdAndStartsOnBetween(disciplineId, startsAfter, startsBefore);
    }

    @Override
    public List<DisciplineTest> getTestsForDisciplineAndGroup(String disciplineId, String groupId) {
        return disciplineTestDao.findAllByDiscipline_IdAndLecture_StudentsGroup_Id(disciplineId, groupId);
    }

    @Override
    public List<DisciplineTest> getTestsForDisciplineAndGroup(String disciplineId, String groupId, Date startsAfter, Date startsBefore) {
        return disciplineTestDao.findAllByDiscipline_IdAndLecture_StudentsGroup_IdAndStartsOnBetween(disciplineId, groupId, startsAfter, startsBefore);
    }

    @Override
    public void deleteTest(String id) {
        disciplineTestDao.delete(id);
    }

    @Override
    public void deleteTestsByDiscipline(String disciplineId) {
        disciplineTestDao.deleteAllByDiscipline_Id(disciplineId);
    }

    @Override
    public void deleteTestsByGroup(String groupId) {
        disciplineTestDao.deleteAllByLecture_StudentsGroup_Id(groupId);
    }

    @Override
    public void deleteTestsByGroupAndDiscipline(String groupId, String disciplineId) {
        disciplineTestDao.deleteAllByLecture_StudentsGroup_IdAndDiscipline_Id(groupId, disciplineId);
    }

    @Override
    public Assignment createAssignment(Assignment assignment) {
        ErrorsCollection errors = lectureValidator.validateForCreation(assignment, null);
        if (!errors.isEmpty()) {
            throw new FriendlyUncheckedException()
                    .setMessage1("Cannot create new assignment")
                    .setMessage2("Validation errors: " + errors.size())
                    .setErrors(errors)
                    .setStatusCode(BAD_REQUEST)
                    ;
        }
        return assignmentDao.save(assignment);
    }

    @Override
    public Assignment getAssignment(String id) {
        return assignmentDao.findOne(id);
    }

    @Override
    public Assignment updateAssignment(Assignment assignment) {
        return assignmentDao.save(assignment);
    }

    @Override
    public void deleteAssignment(String id) {
        assignmentDao.delete(id);
    }

    @Override
    public List<Assignment> getAllAssignmentsForLecture(String id) {
        return assignmentDao.findAllByLecture_Id(id);
    }

    @Override
    public List<Assignment> getAllAssignmentsForDiscipline(String id, Date startsAfter, Date startsBefore) {
        return assignmentDao.findAllByDiscipline_IdAndDueDateBetween(id, startsAfter, startsBefore);
    }

    @Override
    public List<Assignment> getAllAssignmentsForGroup(String id, Date startsAfter, Date startsBefore) {
        return assignmentDao.findAllByLecture_StudentsGroup_IdAndDueDateBetween(id, startsAfter, startsBefore);
    }

    @Override
    public List<Assignment> getAllAssignmentsForStudent(String id, Date startsAfter, Date startsBefore) {
        return assignmentDao.findAllByLecture_StudentsGroup_Members_IdContainingAndDueDateBetween(id, startsAfter, startsBefore);
    }

    @Override
    public List<Assignment> getAllAssignmentsForDisciplineAndGroup(String disciplineId, String groupId, Date startsAfter, Date startsBefore) {
        return assignmentDao.findAllByDiscipline_IdAndLecture_StudentsGroup_IdAndDueDateBetween(disciplineId, groupId, startsAfter, startsBefore);
    }

    @Override
    public List<Assignment> getAllAssignmentsForDisciplineAndStudent(String disciplineId, String studentId, Date startsAfter, Date startsBefore) {
        return assignmentDao.findAllByDiscipline_IdAndLecture_StudentsGroup_Members_IdContainingAndDueDateBetween(disciplineId, studentId, startsAfter, startsBefore);
    }

    @Override
    public List<DisciplineTest> searchAllTestsByDescription(String query) {
        return disciplineTestDao.findAllByDescriptionLikeIgnoreCase(disciplineTestDao.wrapSearchString(query));
    }

    @Override
    public List<DisciplineTest> findAllTestsByDescription(String query) {
        return disciplineTestDao.findAllByDescriptionLikeIgnoreCase(disciplineTestDao.wrapSearchString(query));
    }

}
