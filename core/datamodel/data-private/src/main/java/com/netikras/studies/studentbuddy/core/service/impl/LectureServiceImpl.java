package com.netikras.studies.studentbuddy.core.service.impl;

import com.netikras.studies.studentbuddy.core.data.api.dao.DisciplineTestDao;
import com.netikras.studies.studentbuddy.core.data.api.dao.LectureDao;
import com.netikras.studies.studentbuddy.core.data.api.model.DisciplineTest;
import com.netikras.studies.studentbuddy.core.data.api.model.Lecture;
import com.netikras.studies.studentbuddy.core.service.LectureService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

public class LectureServiceImpl implements LectureService {

    @Resource
    private LectureDao lectureDao;

    @Resource
    private DisciplineTestDao disciplineTestDao;


    
    @Override
    public Lecture findLecture(String id) {
        return null;
    }

    @Override
    public List<Lecture> findLectures(Date startsAfter, Date startsBefore) {
        return null;
    }

    @Override
    public List<Lecture> findLecturesForDiscipline(String disciplineId, Date startsAfter, Date startsBefore) {
        return null;
    }

    @Override
    public List<Lecture> findLecturesForStudentsGroup(String groupId, Date startsAfter, Date startsBefore) {
        return null;
    }

    @Override
    public List<Lecture> findLecturesForLecturer(String lecturerId, Date startsAfter, Date startsBefore) {
        return null;
    }

    @Override
    public List<Lecture> findLecturesForRoom(String roomId, Date startsAfter, Date startsBefore) {
        return null;
    }

    @Override
    public List<Lecture> findLecturesForStudentsGroupAndDiscipline(String groupId, String disciplineId, Date startsAfter, Date startsBefore) {
        return null;
    }

    @Override
    public List<Lecture> findLecturesForLecturerAndDiscipline(String lecturerId, String disciplineId, Date startsAfter, Date startsBefore) {
        return null;
    }

    @Override
    public List<Lecture> findLecturesForRoomAndDiscipline(String roomId, String disciplineId, Date startsAfter, Date startsBefore) {
        return null;
    }

    @Override
    public List<Lecture> findLecturesForBuilding(String buildingId, Date startsAfter, Date startsBefore) {
        return null;
    }

    @Override
    public List<Lecture> findLecturesForGuest(String guestId, Date startsAfter, Date startsBefore) {
        return null;
    }

    @Override
    public List<Lecture> findLecturesForGuestAndDiscipline(String guestId, String disciplineId, Date startsAfter, Date startsBefore) {
        return null;
    }

    @Override
    public Lecture createLecture(Lecture lecture) {
        return null;
    }

    @Override
    public void createLectures(List<Lecture> lectures) {

    }

    @Override
    public void deleteLecture(String id) {

    }

    @Override
    public void deleteLecturesByIds(List<String> ids) {

    }

    @Override
    public void deleteLectures(List<Lecture> lectures) {

    }

    @Override
    public void deleteLecturesByDiscipline(String disciplineId) {

    }

    @Override
    public Lecture updateLecture(Lecture lecture) {
        return null;
    }

    @Override
    public DisciplineTest createTest(DisciplineTest test) {
        return null;
    }

    @Override
    public DisciplineTest updateTest(DisciplineTest test) {
        return null;
    }

    @Override
    public DisciplineTest getTest(String id) {
        return null;
    }

    @Override
    public List<DisciplineTest> getTestsForDiscipline(String disciplineId) {
        return null;
    }

    @Override
    public List<DisciplineTest> getTestsForDiscipline(String disciplineId, Date startsAfter, Date startsBefore) {
        return null;
    }

    @Override
    public List<DisciplineTest> getTestsForDisciplineAndGroup(String disciplineId, String groupId) {
        return null;
    }

    @Override
    public List<DisciplineTest> getTestsForDisciplineAndGroup(String disciplineId, String groupId, Date startsAfter, Date startsBefore) {
        return null;
    }

    @Override
    public void deleteTest(String id) {

    }

    @Override
    public void deleteTestsByDiscipline(String testId, String disciplineId) {

    }

    @Override
    public void deleteTestsByGroup(String testId, String groupId) {

    }

    @Override
    public void deleteTestsByGroupAndDiscipline(String testId, String groupId, String disciplineId) {

    }
}
