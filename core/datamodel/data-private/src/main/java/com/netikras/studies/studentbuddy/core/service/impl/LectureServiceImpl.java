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
}
