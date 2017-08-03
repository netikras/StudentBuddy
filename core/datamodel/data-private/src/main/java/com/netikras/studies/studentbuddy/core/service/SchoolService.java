package com.netikras.studies.studentbuddy.core.service;

import com.netikras.studies.studentbuddy.core.data.api.model.Comment;
import com.netikras.studies.studentbuddy.core.data.api.model.Lecture;

import java.util.List;

public interface SchoolService {

    List<Lecture> getLecturesForGroup(String groupId, long afterTimestamp, long beforeTimestamp);

    List<Lecture> getLecturesForStudent(String groupId, long afterTimestamp, long beforeTimestamp);

    List<Lecture> getLecturesForLecturer(String groupId, long afterTimestamp, long beforeTimestamp);

    List<Lecture> getLecturesForRoom(String groupId, long afterTimestamp, long beforeTimestamp);

    void commentLecture(String lectureId, Comment comment);
}
