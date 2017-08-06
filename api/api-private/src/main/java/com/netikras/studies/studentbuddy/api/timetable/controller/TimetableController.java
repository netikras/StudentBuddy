package com.netikras.studies.studentbuddy.api.timetable.controller;

import com.netikras.studies.studentbuddy.core.data.api.dto.meta.CommentDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.LectureDto;
import com.netikras.studies.studentbuddy.core.data.api.model.Comment;
import com.netikras.studies.studentbuddy.core.data.api.model.Lecture;
import com.netikras.studies.studentbuddy.core.meta.Action;
import com.netikras.studies.studentbuddy.core.meta.Resource;
import com.netikras.studies.studentbuddy.core.meta.annotations.Authorizable;
import com.netikras.studies.studentbuddy.core.service.SchoolService;
import com.netikras.tools.common.model.mapper.ModelMapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by netikras on 17.6.21.
 */
@RestController
@RequestMapping(value = "/schedule")
@Authorizable(resource = Resource.LECTURE, action = Action.GET)
public class TimetableController {

    private SchoolService schoolService;

    /**
     * @param groupId   Students' group ID, e.g. PIN13
     * @param timeUnits Time units (ISO): M(onths), d(ays), H(ours), m(inutes), s(econds)
     * @param value     Time value
     */
    @RequestMapping(
            value = "/lectures/group/{groupId}/in/t/{timeUnits}/{value}",
            method = RequestMethod.GET
    )
    @ResponseBody
    public List<LectureDto> getLecturesByGroupStartingIn(
            @PathVariable(name = "groupId") String groupId,
            @PathVariable(name = "timeUnits") String timeUnits,
            @PathVariable(name = "value") long value
    ) {
        return getLecturesByGroupStartingBetween(groupId, System.currentTimeMillis(), timeUnitsToMs(timeUnits, value));
    }

    @RequestMapping(
            value = "/lectures/student/{studentId}/in/t/{timeUnits}/{value}",
            method = RequestMethod.GET
    )
    @ResponseBody
    public List<LectureDto> getLecturesByStudentStartingIn(
            @PathVariable(name = "studentId") String studentId,
            @PathVariable(name = "timeUnits") String timeUnits,
            @PathVariable(name = "value") long value
    ) {
        return getLecturesByStudentStartingBetween(studentId, System.currentTimeMillis(), timeUnitsToMs(timeUnits, value));
    }

    @RequestMapping(
            value = "/lectures/lecturer/{lecturerId}/in/t/{timeUnits}/{value}",
            method = RequestMethod.GET
    )
    @ResponseBody
    public List<LectureDto> getLecturesByLecturerStartingIn(
            @PathVariable(name = "lecturerId") String lecturerId,
            @PathVariable(name = "timeUnits") String timeUnits,
            @PathVariable(name = "value") long value
    ) {
        return getLecturesByLecturerStartingBetween(lecturerId, System.currentTimeMillis(), timeUnitsToMs(timeUnits, value));
    }

    @RequestMapping(
            value = "/lectures/room/{roomId}/in/t/{timeUnits}/{value}",
            method = RequestMethod.GET
    )
    @ResponseBody
    public List<LectureDto> getLecturesByRoomStartingIn(
            @PathVariable(name = "roomId") String roomId,
            @PathVariable(name = "timeUnits") String timeUnits,
            @PathVariable(name = "value") long value
    ) {
        return getLecturesByRoomStartingBetween(roomId, System.currentTimeMillis(), timeUnitsToMs(timeUnits, value));
    }


    @RequestMapping(
            value = "/lectures/group/{groupId}/between/{after}/{before}",
            method = RequestMethod.GET
    )
    @ResponseBody
    public List<LectureDto> getLecturesByGroupStartingBetween(
            @PathVariable(name = "groupId") String groupId,
            @PathVariable(name = "after") long afterTimestamp,
            @PathVariable(name = "before") long beforeTimestamp
    ) {
        List<Lecture> lectures = schoolService.findLecturesForGroup(groupId, afterTimestamp, beforeTimestamp);
        return toLectureDtos(lectures);
    }

    @RequestMapping(
            value = "/lectures/student/{studentId}/between/{after}/{before}",
            method = RequestMethod.GET
    )
    @ResponseBody
    public List<LectureDto> getLecturesByStudentStartingBetween(
            @PathVariable(name = "studentId") String studentId,
            @PathVariable(name = "after") long afterTimestamp,
            @PathVariable(name = "before") long beforeTimestamp
    ) {
        List<Lecture> lectures = schoolService.findLecturesForGuest(studentId, afterTimestamp, beforeTimestamp);
        return toLectureDtos(lectures);
    }

    @RequestMapping(
            value = "/lectures/lecturer/{lecturerId}/between/{after}/{before}",
            method = RequestMethod.GET
    )
    @ResponseBody
    public List<LectureDto> getLecturesByLecturerStartingBetween(
            @PathVariable(name = "lecturerId") String lecturerId,
            @PathVariable(name = "after") long afterTimestamp,
            @PathVariable(name = "before") long beforeTimestamp
    ) {
        List<Lecture> lectures = schoolService.findLecturesForLecturer(lecturerId, afterTimestamp, beforeTimestamp);
        return toLectureDtos(lectures);
    }

    @RequestMapping(
            value = "/lectures/room/{roomId}/between/{after}/{before}",
            method = RequestMethod.GET
    )
    @ResponseBody
    public List<LectureDto> getLecturesByRoomStartingBetween(
            @PathVariable(name = "roomId") String roomId,
            @PathVariable(name = "after") long afterTimestamp,
            @PathVariable(name = "before") long beforeTimestamp
    ) {
        List<Lecture> lectures = schoolService.getLecturesForRoom(roomId, afterTimestamp, beforeTimestamp);
        return toLectureDtos(lectures);
    }


    @RequestMapping(
            value = "/lecture/{lectureId}/comment",
            method = RequestMethod.POST
    )
    @ResponseBody
    @Authorizable(resource = Resource.LECTURE, action = Action.COMMENT_CREATE)
    public void comment(
            @PathVariable(name = "lectureId") String lectureId,
            @RequestBody(required = true) CommentDto commentDto
    ) {

        Comment comment = ModelMapper.apply(new Comment(), commentDto);

        schoolService.commentLecture(lectureId, comment);
    }

    private List<LectureDto> toLectureDtos(Collection<Lecture> lectures) {
        if (lectures == null) return null;

        List<LectureDto> lectureDtos = new ArrayList<>();
        for (Lecture lecture : lectures) {
            lectureDtos.add(ModelMapper.transform(lecture, new LectureDto()));
        }

        return lectureDtos;
    }

    private long timeUnitsToMs(String units, long value) {

        if (units == null) {
            return 0;
        }

        long result = 0;

        switch (units) {
            case "M":
                result = value * 1000 * 60 * 60 * 24 * 30;
                break;
            case "d":
                result = value * 1000 * 60 * 60 * 24;
                break;
            case "H":
                result = value * 1000 * 60 * 60;
                break;
            case "m":
                result = value * 1000 * 60;
                break;
            case "s":
                result = value * 1000;
                break;
            default:
                throw new NumberFormatException("Incorrect time unit: " + units);
        }

        return result;
    }

}
