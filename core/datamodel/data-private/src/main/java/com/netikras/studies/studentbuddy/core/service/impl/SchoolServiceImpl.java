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
import com.netikras.studies.studentbuddy.core.service.SchoolService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SchoolServiceImpl implements SchoolService {

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
    public List<Lecture> getLecturesForGroup(String groupId, long afterTimestamp, long beforeTimestamp) {
        return lectureDao.getByStudentsGroupAndStartsOnBetween(groupDao.getOne(groupId), new Date(afterTimestamp), new Date(beforeTimestamp));
    }

    @Override
    public List<Lecture> getLecturesForStudent(String studentId, long afterTimestamp, long beforeTimestamp) {

        List<Lecture> lectures = new ArrayList<>();
        Student student = studentDao.getOne(studentId);

        if (student == null) return new ArrayList<>();

        List<StudentsGroup> groups = groupDao.getByMembersContaining(student);

        if (groups != null && !groups.isEmpty()) {
            for (StudentsGroup group : groups) {
                lectures.addAll(getLecturesForGroup(group.getId(), afterTimestamp, beforeTimestamp));
            }
        }

        lectures.addAll(lectureDao.getByExclusiveStudentsContainingAndStartsOnBetween(student, new Date(afterTimestamp), new Date(beforeTimestamp)));

        return lectures;
    }

    @Override
    public List<Lecture> getLecturesForLecturer(String lecturerId, long afterTimestamp, long beforeTimestamp) {
        Lecturer lecturer = lecturerDao.getOne(lecturerId);

        if (lecturer == null) return new ArrayList<>();

        return lectureDao.getByLecturerAndStartsOnBetween(lecturer, new Date(afterTimestamp), new Date(beforeTimestamp));
    }

    @Override
    public List<Lecture> getLecturesForRoom(String roomId, long afterTimestamp, long beforeTimestamp) {

        LectureRoom room = lectureRoomDao.getOne(roomId);

        if (room == null) return new ArrayList<>();

        return lectureDao.getByRoomAndStartsOnBetween(room, new Date(afterTimestamp), new Date(beforeTimestamp));
    }

    @Override
    public void commentLecture(String lectureId, Comment comment) {
        if (comment == null) return;

        Lecture lecture = lectureDao.getOne(lectureId);
        if (lecture == null) return;

        comment.setEntityId(lectureId);
        comment.setEntityType("LECTURE");

        lecture.addComment(comment);
        lectureDao.save(lecture);
    }
}
