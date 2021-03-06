package com.netikras.studies.studentbuddy.core.service;

import com.netikras.studies.studentbuddy.core.data.api.dto.school.AssignmentDto;
import com.netikras.studies.studentbuddy.core.data.api.model.Assignment;
import com.netikras.studies.studentbuddy.core.data.api.model.DisciplineTest;
import com.netikras.studies.studentbuddy.core.data.api.model.Lecture;

import java.util.Date;
import java.util.List;

public interface LectureService {

    // Lookup
    Lecture getLecture(String id);

    List<Lecture> findLectures(Date startsAfter, Date startsBefore);

    List<Lecture> findLecturesForDiscipline(String disciplineId, Date startsAfter, Date startsBefore);

    List<Lecture> findLecturesForStudentsGroup(String groupId, Date startsAfter, Date startsBefore);

    List<Lecture> findLecturesForLecturer(String lecturerId, Date startsAfter, Date startsBefore);

    List<Lecture> findLecturesForRoom(String roomId, Date startsAfter, Date startsBefore);

    List<Lecture> findLecturesForStudentsGroupAndDiscipline(String groupId, String disciplineId, Date startsAfter, Date startsBefore);

    List<Lecture> findLecturesForLecturerAndDiscipline(String lecturerId, String disciplineId, Date startsAfter, Date startsBefore);

    List<Lecture> findLecturesForRoomAndDiscipline(String roomId, String disciplineId, Date startsAfter, Date startsBefore);

    List<Lecture> findLecturesForBuildingStartingBetween(String buildingId, Date startsAfter, Date startsBefore);

    List<Lecture> findLecturesForGuest(String guestId, Date startsAfter, Date startsBefore);

    List<Lecture> findLecturesForGuestAndDiscipline(String guestId, String disciplineId, Date startsAfter, Date startsBefore);


    // Mgmt
    Lecture createLecture(Lecture lecture);

    void createLectures(List<Lecture> lectures);

    void deleteLecture(String id);

    void deleteLecturesByIds(List<String> ids);

    void deleteLectures(List<Lecture> lectures);

    void deleteLecturesByDiscipline(String disciplineId);

    Lecture updateLecture(Lecture lecture);


    // Tests

    DisciplineTest createTest(DisciplineTest test);

    DisciplineTest updateTest(DisciplineTest test);

    DisciplineTest getTest(String id);

    List<DisciplineTest> getTestsForDiscipline(String disciplineId);

    List<DisciplineTest> getTestsForDiscipline(String disciplineId, Date startsAfter, Date startsBefore);

    List<DisciplineTest> getTestsForDisciplineAndGroup(String disciplineId, String groupId);

    List<DisciplineTest> getTestsForDisciplineAndGroup(String disciplineId, String groupId, Date startsAfter, Date startsBefore);

    void deleteTest(String id);

    void deleteTestsByDiscipline(String disciplineId);

    void deleteTestsByGroup(String groupId);

    void deleteTestsByGroupAndDiscipline(String groupId, String disciplineId);


    // Assignments

    Assignment createAssignment(Assignment assignment);

    Assignment getAssignment(String id);

    Assignment updateAssignment(Assignment assignment);

    void deleteAssignment(String id);

    List<Assignment> getAllAssignmentsForLecture(String id);

    List<Assignment> getAllAssignmentsForDiscipline(String id, Date startsAfter, Date startsBefore);

    List<Assignment> getAllAssignmentsForGroup(String id, Date startsAfter, Date startsBefore);

    List<Assignment> getAllAssignmentsForStudent(String id, Date startsAfter, Date startsBefore);

    List<Assignment> getAllAssignmentsForDisciplineAndGroup(String disciplineId, String groupId, Date startsAfter, Date startsBefore);

    List<Assignment> getAllAssignmentsForDisciplineAndStudent(String disciplineId, String studentId, Date startsAfter, Date startsBefore);

    List<DisciplineTest> searchAllTestsByDescription(String query);

    List<DisciplineTest> findAllTestsByDescription(String query);

    void purgeLecture(String id);

    void purgeLectures(List<String> lectureIds);

    void purgeLectureTest(String testId);

    void purgeLectureAssignment(String id);

    List<DisciplineTest> getTestsForCourse(String id);

    List<DisciplineTest> getTestsForCourseStartingBetween(String id, Date after, Date before);

    List<Lecture> findLecturesForFloorStartingBetween(String id, Date after, Date before);

    List<Lecture> findLecturesForSectionStartingBetween(String id, Date after, Date before);

    List<Lecture> findLecturesForCourseStartingBetween(String id, Date after, Date before);

    List<Assignment> getAllAssignmentsForCourseStartingBetween(String id, Date after, Date before);

    List<Assignment> getAllAssignmentsForCourse(String id);

    List<Lecture> findLecturesForStudent(String studentId, Date after, Date before);
}
