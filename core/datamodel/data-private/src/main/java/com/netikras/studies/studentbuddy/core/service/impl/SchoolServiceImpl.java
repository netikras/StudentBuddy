package com.netikras.studies.studentbuddy.core.service.impl;

import com.netikras.studies.studentbuddy.core.data.api.dao.LectureDao;
import com.netikras.studies.studentbuddy.core.data.api.dao.LectureRoomDao;
import com.netikras.studies.studentbuddy.core.data.api.dao.LecturerDao;
import com.netikras.studies.studentbuddy.core.data.api.dao.SchoolDao;
import com.netikras.studies.studentbuddy.core.data.api.dao.StudentDao;
import com.netikras.studies.studentbuddy.core.data.api.dao.StudentsGroupDao;
import com.netikras.studies.studentbuddy.core.data.api.model.Comment;
import com.netikras.studies.studentbuddy.core.data.api.model.Lecture;
import com.netikras.studies.studentbuddy.core.data.api.model.LectureRoom;
import com.netikras.studies.studentbuddy.core.data.api.model.Lecturer;
import com.netikras.studies.studentbuddy.core.data.api.model.Student;
import com.netikras.studies.studentbuddy.core.data.api.model.StudentsGroup;
import com.netikras.studies.studentbuddy.core.service.CommentsService;
import com.netikras.studies.studentbuddy.core.service.SchoolService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SchoolServiceImpl implements SchoolService {

    @Resource
    private CommentsService commentsService;

    @Resource
    private SchoolDao schoolDao;

    @Resource
    private LectureDao lectureDao;

    @Resource
    private LecturerDao lecturerDao;

    @Resource
    private StudentsGroupDao groupDao;

    @Resource
    private StudentDao studentDao;

    @Resource
    private LectureRoomDao lectureRoomDao;

    @Override
    @Transactional
    public List<Lecture> findLecturesForGroup(String groupId, long afterTimestamp, long beforeTimestamp) {
        return lectureDao.findByStudentsGroupAndStartsOnBetween(groupDao.findOne(groupId), new Date(afterTimestamp), new Date(beforeTimestamp));
    }

    @Override
    @Transactional
    public List<Lecture> findLecturesForGuest(String studentId, long afterTimestamp, long beforeTimestamp) {

        List<Lecture> lectures = new ArrayList<>();
        Student student = studentDao.findOne(studentId);

        if (student == null) return new ArrayList<>();

        List<StudentsGroup> groups = groupDao.findByMembersContaining(student);

        if (groups != null && !groups.isEmpty()) {
            for (StudentsGroup group : groups) {
                lectures.addAll(findLecturesForGroup(group.getId(), afterTimestamp, beforeTimestamp));
            }
        }

        lectures.addAll(lectureDao.findByLectureGuestsContainingAndStartsOnBetween(student, new Date(afterTimestamp), new Date(beforeTimestamp)));

        return lectures;
    }

    @Override
    @Transactional
    public List<Lecture> findLecturesForLecturer(String lecturerId, long afterTimestamp, long beforeTimestamp) {
        Lecturer lecturer = lecturerDao.findOne(lecturerId);

        if (lecturer == null) return new ArrayList<>();

        return lectureDao.findByLecturerAndStartsOnBetween(lecturer, new Date(afterTimestamp), new Date(beforeTimestamp));
    }

    @Override
    @Transactional
    public List<Lecture> getLecturesForRoom(String roomId, long afterTimestamp, long beforeTimestamp) {

        LectureRoom room = lectureRoomDao.findOne(roomId);

        if (room == null) return new ArrayList<>();

        return lectureDao.findByRoomAndStartsOnBetween(room, new Date(afterTimestamp), new Date(beforeTimestamp));
    }

    @Override
    @Transactional
    public void commentLecture(String lectureId, Comment comment) {
        if (comment == null) return;

        Lecture lecture = lectureDao.findOne(lectureId);
        if (lecture == null) return;

        comment.setEntityId(lectureId);
        comment.setEntityType("LECTURE");

        lecture.addComment(comment);
//        lectureDao.save(lecture);
        commentsService.createComment(comment);
    }
}
