package com.netikras.studies.studentbuddy.core.service;

import com.netikras.studies.studentbuddy.core.data.api.model.Comment;
import com.netikras.studies.studentbuddy.core.data.api.model.Lecture;

import java.util.List;

public interface SchoolService {

    List<Lecture> findLecturesForGroup(String groupId, long afterTimestamp, long beforeTimestamp);

    List<Lecture> findLecturesForGuest(String groupId, long afterTimestamp, long beforeTimestamp);

    List<Lecture> findLecturesForLecturer(String groupId, long afterTimestamp, long beforeTimestamp);

    List<Lecture> getLecturesForRoom(String groupId, long afterTimestamp, long beforeTimestamp);

    void commentLecture(String lectureId, Comment comment);
}
