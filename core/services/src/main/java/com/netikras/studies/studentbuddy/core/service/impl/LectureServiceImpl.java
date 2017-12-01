package com.netikras.studies.studentbuddy.core.service.impl;

import com.netikras.studies.studentbuddy.commons.exception.StudBudUncheckedException;
import com.netikras.studies.studentbuddy.core.data.api.dao.AssignmentDao;
import com.netikras.studies.studentbuddy.core.data.api.dao.DisciplineTestDao;
import com.netikras.studies.studentbuddy.core.data.api.dao.LectureDao;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.AssignmentDto;
import com.netikras.studies.studentbuddy.core.data.api.model.Assignment;
import com.netikras.studies.studentbuddy.core.data.api.model.DisciplineTest;
import com.netikras.studies.studentbuddy.core.data.api.model.Lecture;
import com.netikras.studies.studentbuddy.core.service.CommentsService;
import com.netikras.studies.studentbuddy.core.service.LectureService;
import com.netikras.studies.studentbuddy.core.service.StudentService;
import com.netikras.studies.studentbuddy.core.validator.LectureValidator;
import com.netikras.tools.common.exception.ErrorsCollection;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.netikras.tools.common.remote.http.HttpStatus.BAD_REQUEST;
import static com.netikras.tools.common.security.IntegrityUtils.isNullOrEmpty;

@Service
public class LectureServiceImpl implements LectureService {

    @Resource
    private LectureDao lectureDao;
    @Resource
    private DisciplineTestDao disciplineTestDao;
    @Resource
    private AssignmentDao assignmentDao;

    @Resource
    private StudentService studentService;
    @Resource
    private CommentsService commentsService;

    @Resource
    private LectureValidator lectureValidator;

    @Override
    @Transactional
    public Lecture getLecture(String id) {
        Lecture lecture = lectureDao.findOne(id);
        commentsService.assignComments(lecture);
        return lecture;
    }

    @Override
    @Transactional
    public List<Lecture> findLectures(Date startsAfter, Date startsBefore) {
        List<Lecture> lectures = lectureDao.findAllByStartsOnBetween(startsAfter, startsBefore);
        commentsService.assignComments(lectures);
        return lectures;
    }

    @Override
    @Transactional
    public List<Lecture> findLecturesForDiscipline(String disciplineId, Date startsAfter, Date startsBefore) {
        List<Lecture> lectures = lectureDao.findAllByDiscipline_IdAndStartsOnBetween(disciplineId, startsAfter, startsBefore);
        commentsService.assignComments(lectures);
        return lectures;
    }

    @Override
    @Transactional
    public List<Lecture> findLecturesForStudentsGroup(String groupId, Date startsAfter, Date startsBefore) {
        List<Lecture> lectures =  lectureDao.findAllByStudentsGroup_IdAndStartsOnBetween(groupId, startsAfter, startsBefore);
        commentsService.assignComments(lectures);
        return lectures;
    }

    @Override
    @Transactional
    public List<Lecture> findLecturesForLecturer(String lecturerId, Date startsAfter, Date startsBefore) {
        List<Lecture> lectures = lectureDao.findAllByLecturer_IdAndStartsOnBetween(lecturerId, startsAfter, startsBefore);
        commentsService.assignComments(lectures);
        return lectures;
    }

    @Override
    @Transactional
    public List<Lecture> findLecturesForRoom(String roomId, Date startsAfter, Date startsBefore) {
        List<Lecture> lectures = lectureDao.findAllByRoom_IdAndStartsOnBetween(roomId, startsAfter, startsBefore);
        commentsService.assignComments(lectures);
        return lectures;
    }

    @Override
    @Transactional
    public List<Lecture> findLecturesForStudentsGroupAndDiscipline(String groupId, String disciplineId, Date startsAfter, Date startsBefore) {
        List<Lecture> lectures = lectureDao.findAllByStudentsGroup_IdAndDiscipline_IdAndStartsOnBetween(groupId, disciplineId, startsAfter, startsBefore);
        commentsService.assignComments(lectures);
        return lectures;
    }

    @Override
    @Transactional
    public List<Lecture> findLecturesForLecturerAndDiscipline(String lecturerId, String disciplineId, Date startsAfter, Date startsBefore) {
        List<Lecture> lectures = lectureDao.findAllByLecturer_IdAndDiscipline_IdAndStartsOnBetween(lecturerId, disciplineId, startsAfter, startsBefore);
        commentsService.assignComments(lectures);
        return lectures;
    }

    @Override
    @Transactional
    public List<Lecture> findLecturesForRoomAndDiscipline(String roomId, String disciplineId, Date startsAfter, Date startsBefore) {
        List<Lecture> lectures = lectureDao.findAllByRoom_IdAndDiscipline_IdAndStartsOnBetween(roomId, disciplineId, startsAfter, startsBefore);
        commentsService.assignComments(lectures);
        return lectures;
    }

    @Override
    @Transactional
    public List<Lecture> findLecturesForBuildingStartingBetween(String buildingId, Date startsAfter, Date startsBefore) {
        List<Lecture> lectures = lectureDao.findAllByRoom_Floor_Building_IdAndStartsOnBetween(buildingId, startsAfter, startsBefore);
        commentsService.assignComments(lectures);
        return lectures;
    }

    @Override
    @Transactional
    public List<Lecture> findLecturesForGuest(String guestId, Date startsAfter, Date startsBefore) {
        List<Lecture> lectures = lectureDao.findAllByLectureGuests_IdAndStartsOnBetween(guestId, startsAfter, startsBefore);
        commentsService.assignComments(lectures);
        return lectures;
    }

    @Override
    @Transactional
    public List<Lecture> findLecturesForGuestAndDiscipline(String guestId, String disciplineId, Date startsAfter, Date startsBefore) {
        List<Lecture> lectures = lectureDao.findAllByLectureGuests_IdAndDiscipline_IdAndStartsOnBetween(guestId, disciplineId, startsAfter, startsBefore);
        commentsService.assignComments(lectures);
        return lectures;
    }

    @Transactional
    public List<Lecture> findLecturesForStudent(String studentId, Date startsAfter, Date startsBefore) {
        List<Lecture> lectures = lectureDao.findAllByStudentsGroup_Members_IdAndStartsOnBetween(studentId, startsAfter, startsBefore);
        commentsService.assignComments(lectures);
        return lectures;
    }

    @Override
    @Transactional
    public Lecture createLecture(Lecture lecture) {
        ErrorsCollection errors = lectureValidator.validateForCreation(lecture, null);
        if (!errors.isEmpty()) {
            throw new StudBudUncheckedException()
                    .setMessage1("Cannot create new lecture")
                    .setMessage2("Validation errors: " + errors.size())
                    .setErrors(errors)
                    .setStatusCode(BAD_REQUEST)
                    ;
        }
        return lectureDao.save(lecture);
    }

    @Override
    @Transactional
    public void createLectures(List<Lecture> lectures) {
        if (lectures == null) return;

        for (Lecture lecture : lectures) {
            createLecture(lecture);
        }
    }

    @Override
    @Transactional
    public void deleteLecture(String id) {
        Lecture lecture = getLecture(id);
        ErrorsCollection errors = lectureValidator.validateForRemoval(lecture, null);
        if (!errors.isEmpty()) {
            throw new StudBudUncheckedException()
                    .setMessage1("Cannot remove lecture")
                    .setMessage2("Validation errors: " + errors.size())
                    .setProbableCause(id)
                    .setErrors(errors)
                    .setStatusCode(BAD_REQUEST)
                    ;
        }

        lectureDao.delete(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteLecturesByIds(List<String> ids) {
        if (ids == null) return;

        lectureDao.deleteAllByIdIn(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteLectures(List<Lecture> lectures) {
        if (isNullOrEmpty(lectures)) {
            return;
        }
        lectures.forEach(lecture -> deleteLecture(lecture.getId()));
    }

    @Override
    @Transactional
    public void deleteLecturesByDiscipline(String disciplineId) {
        lectureDao.deleteAllByDiscipline_Id(disciplineId);
    }

    @Override
    @Transactional
    public Lecture updateLecture(Lecture lecture) {
        Lecture updated = lectureDao.save(lecture);

        return commentsService.assignComments(updated);
    }


    @Override
    @Transactional
    public DisciplineTest createTest(DisciplineTest test) {
        ErrorsCollection errors = lectureValidator.validateForCreation(test, null);
        if (!errors.isEmpty()) {
            throw new StudBudUncheckedException()
                    .setMessage1("Cannot create new test")
                    .setMessage2("Validation errors: " + errors.size())
                    .setErrors(errors)
                    .setStatusCode(BAD_REQUEST)
                    ;
        }
        return disciplineTestDao.save(test);
    }

    @Override
    @Transactional
    public DisciplineTest updateTest(DisciplineTest test) {
        DisciplineTest updated = disciplineTestDao.save(test);
        commentsService.assignComments(updated);
        return updated;
    }

    @Override
    @Transactional
    public DisciplineTest getTest(String id) {
        DisciplineTest test = disciplineTestDao.findOne(id);
        commentsService.assignComments(test);
        return test;
    }

    @Override
    @Transactional
    public List<DisciplineTest> getTestsForDiscipline(String disciplineId) {
        List<DisciplineTest> tests = disciplineTestDao.findAllByDiscipline_Id(disciplineId);
        commentsService.assignComments(tests);
        return tests;
    }

    @Override
    @Transactional
    public List<DisciplineTest> getTestsForDiscipline(String disciplineId, Date startsAfter, Date startsBefore) {
        Set<DisciplineTest> tests = new HashSet<>(disciplineTestDao.findAllByDiscipline_IdAndStartsOnBetween(disciplineId, startsAfter, startsBefore));
        tests.addAll(disciplineTestDao.findAllByDiscipline_IdAndLecture_StartsOnBetween(disciplineId, startsAfter, startsBefore));
        commentsService.assignComments(tests);
        return new ArrayList<>(tests);
    }

    @Override
    @Transactional
    public List<DisciplineTest> getTestsForDisciplineAndGroup(String disciplineId, String groupId) {
        List<DisciplineTest> tests = disciplineTestDao.findAllByDiscipline_IdAndLecture_StudentsGroup_Id(disciplineId, groupId);
        commentsService.assignComments(tests);
        return tests;
    }

    @Override
    @Transactional
    public List<DisciplineTest> getTestsForDisciplineAndGroup(String disciplineId, String groupId, Date startsAfter, Date startsBefore) {
        Set<DisciplineTest> tests = new HashSet<>(disciplineTestDao.findAllByDiscipline_IdAndLecture_StudentsGroup_IdAndStartsOnBetween(disciplineId, groupId, startsAfter, startsBefore));
        tests.addAll(disciplineTestDao.findAllByDiscipline_IdAndLecture_StudentsGroup_IdAndLecture_StartsOnBetween(disciplineId, groupId, startsAfter, startsBefore));
        commentsService.assignComments(tests);
        return new ArrayList<>(tests);
    }

    @Override
    @Transactional
    public void deleteTest(String id) {
        disciplineTestDao.delete(id);
    }

    @Override
    @Transactional
    public void deleteTestsByDiscipline(String disciplineId) {
        disciplineTestDao.deleteAllByDiscipline_Id(disciplineId);
    }

    @Override
    @Transactional
    public void deleteTestsByGroup(String groupId) {
        disciplineTestDao.deleteAllByLecture_StudentsGroup_Id(groupId);
    }

    @Override
    @Transactional
    public void deleteTestsByGroupAndDiscipline(String groupId, String disciplineId) {
        disciplineTestDao.deleteAllByLecture_StudentsGroup_IdAndDiscipline_Id(groupId, disciplineId);
    }

    @Override
    @Transactional
    public Assignment createAssignment(Assignment assignment) {
        ErrorsCollection errors = lectureValidator.validateForCreation(assignment, null);
        if (!errors.isEmpty()) {
            throw new StudBudUncheckedException()
                    .setMessage1("Cannot create new assignment")
                    .setMessage2("Validation errors: " + errors.size())
                    .setErrors(errors)
                    .setStatusCode(BAD_REQUEST)
                    ;
        }
        return assignmentDao.save(assignment);
    }

    @Override
    @Transactional
    public Assignment getAssignment(String id) {
        Assignment assignment = assignmentDao.findOne(id);
        commentsService.assignComments(assignment);
        return assignment;
    }

    @Override
    @Transactional
    public Assignment updateAssignment(Assignment assignment) {
        Assignment updated = assignmentDao.save(assignment);
        commentsService.assignComments(updated);
        return updated;
    }

    @Override
    @Transactional
    public void deleteAssignment(String id) {
        assignmentDao.delete(id);
    }

    @Override
    @Transactional
    public List<Assignment> getAllAssignmentsForLecture(String id) {
        List<Assignment> assignments = assignmentDao.findAllByLecture_Id(id);
        commentsService.assignComments(assignments);
        return assignments;
    }

    @Override
    @Transactional
    public List<Assignment> getAllAssignmentsForDiscipline(String id, Date startsAfter, Date startsBefore) {
        List<Assignment> assignments = assignmentDao.findAllByDiscipline_IdAndDueDateBetween(id, startsAfter, startsBefore);
        commentsService.assignComments(assignments);
        return assignments;
    }

    @Override
    @Transactional
    public List<Assignment> getAllAssignmentsForGroup(String id, Date startsAfter, Date startsBefore) {
        List<Assignment> assignments = assignmentDao.findAllByLecture_StudentsGroup_IdAndDueDateBetween(id, startsAfter, startsBefore);
        commentsService.assignComments(assignments);
        return assignments;
    }

    @Override
    @Transactional
    public List<Assignment> getAllAssignmentsForStudent(String id, Date startsAfter, Date startsBefore) {
        List<Assignment> assignments = assignmentDao.findAllByLecture_StudentsGroup_Members_IdContainingAndDueDateBetween(id, startsAfter, startsBefore);
        commentsService.assignComments(assignments);
        return assignments;
    }

    @Override
    @Transactional
    public List<Assignment> getAllAssignmentsForDisciplineAndGroup(String disciplineId, String groupId, Date startsAfter, Date startsBefore) {
        List<Assignment> assignments = assignmentDao.findAllByDiscipline_IdAndLecture_StudentsGroup_IdAndDueDateBetween(disciplineId, groupId, startsAfter, startsBefore);
        commentsService.assignComments(assignments);
        return assignments;
    }

    @Override
    @Transactional
    public List<Assignment> getAllAssignmentsForDisciplineAndStudent(String disciplineId, String studentId, Date startsAfter, Date startsBefore) {
        List<Assignment> assignments = assignmentDao.findAllByDiscipline_IdAndLecture_StudentsGroup_Members_IdContainingAndDueDateBetween(disciplineId, studentId, startsAfter, startsBefore);
        commentsService.assignComments(assignments);
        return assignments;
    }

    @Override
    @Transactional
    public List<DisciplineTest> searchAllTestsByDescription(String query) {
        List<DisciplineTest> tests = disciplineTestDao.findAllByDescriptionLikeIgnoreCase(disciplineTestDao.wrapSearchString(query));
        commentsService.assignComments(tests);
        return tests;
    }

    @Override
    @Transactional
    public List<DisciplineTest> findAllTestsByDescription(String query) {
        List<DisciplineTest> tests = disciplineTestDao.findAllByDescriptionLikeIgnoreCase(disciplineTestDao.wrapSearchString(query));
        commentsService.assignComments(tests);
        return tests;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void purgeLectures(List<String> lectureIds) {
        if (isNullOrEmpty(lectureIds)) {
            return;
        }
        lectureIds.forEach(this::purgeLecture);
    }


    @Override
    @Transactional
    public void purgeLecture(String lectureId) {
        Lecture lecture = getLecture(lectureId);
        if (lecture == null) {
            return;
        }

        if (!isNullOrEmpty(lecture.getPendingAssignments())) {
            lecture.getPendingAssignments().forEach(
                    assignment -> purgeLectureAssignment(assignment.getId()));
            lecture.getPendingAssignments().clear();
        }

        if (!isNullOrEmpty(lecture.getPendingTests())) {
            lecture.getPendingTests().forEach(
                    test -> purgeLectureTest(test.getId()));
            lecture.getPendingTests().clear();
        }

        if (!isNullOrEmpty(lecture.getLectureGuests())) {
            lecture.getLectureGuests().forEach(
                    guest -> studentService.purgeLectureGuest(guest.getId()));
            lecture.getLectureGuests().clear();
        }

        if (!isNullOrEmpty(lecture.getComments())) {
            lecture.getComments().forEach(
                    comment -> commentsService.purgeComment(comment.getId()));
        }

        lectureDao.delete(lectureId);
    }

    @Override
    @Transactional
    public void purgeLectureTest(String testId) {
        DisciplineTest test = getTest(testId);
        if (test == null) {
            return;
        }

        disciplineTestDao.delete(testId);
    }

    @Override
    @Transactional
    public void purgeLectureAssignment(String id) {
        Assignment assignment = getAssignment(id);
        if (assignment == null) {
            return;
        }

        assignmentDao.delete(id);
    }

    @Override
    @Transactional
    public List<DisciplineTest> getTestsForCourse(String id) {
        return disciplineTestDao.findAllByLecture_Course_Id(id);
    }

    @Override
    @Transactional
    public List<DisciplineTest> getTestsForCourseStartingBetween(String id, Date afterTimestamp, Date beforeTimestamp) {
        Set<DisciplineTest> tests = new HashSet<>(disciplineTestDao.findAllByLecture_Course_Id_AndStartsOnBetween(id, afterTimestamp, beforeTimestamp));
        tests.addAll(disciplineTestDao.findAllByLecture_Course_Id_AndLecture_StartsOnBetween(id, afterTimestamp, beforeTimestamp));
//        commentsService.assignComments(tests);
        return new ArrayList<>(tests);
    }

    @Override
    @Transactional
    public List<Lecture> findLecturesForFloorStartingBetween(String id, Date after, Date before) {
        List<Lecture> lectures = lectureDao.findAllByRoom_Floor_IdAndStartsOnBetween(id, after, before);
        commentsService.assignComments(lectures);
        return lectures;
    }

    @Override
    @Transactional
    public List<Lecture> findLecturesForSectionStartingBetween(String id, Date after, Date before) {
        List<Lecture> lectures = lectureDao.findAllByRoom_Floor_Section_IdAndStartsOnBetween(id, after, before);
        commentsService.assignComments(lectures);
        return lectures;
    }

    @Override
    @Transactional
    public List<Lecture> findLecturesForCourseStartingBetween(String id, Date after, Date before) {
        List<Lecture> lectures = lectureDao.findAllByCourse_IdAndStartsOnBetween(id, after, before);
        commentsService.assignComments(lectures);
        return lectures;
    }

    @Override
    @Transactional
    public List<Assignment> getAllAssignmentsForCourseStartingBetween(String id, Date after, Date before) {
        Set<Assignment> assignments = new HashSet<>(assignmentDao.findAllByLecture_Course_IdAndDueDateBetween(id, after, before));
        assignments.addAll(assignmentDao.findAllByLecture_Course_IdAndLecture_StartsOnBetween(id, after, before));
        commentsService.assignComments(assignments);
        return new ArrayList<>(assignments);
    }

    @Override
    @Transactional
    public List<Assignment> getAllAssignmentsForCourse(String id) {
        return assignmentDao.findAllByLecture_Course_Id(id);
    }


}
