package com.netikras.studies.studentbuddy.api.timetable.controller;

import com.netikras.studies.studentbuddy.core.data.api.dto.school.LectureDto;
import com.netikras.studies.studentbuddy.core.data.api.model.Lecture;
import com.netikras.studies.studentbuddy.core.meta.annotations.Authorizable;
import com.netikras.studies.studentbuddy.core.service.LectureService;
import com.netikras.tools.common.model.mapper.ModelMapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import static com.netikras.studies.studentbuddy.core.meta.Action.GET;
import static com.netikras.studies.studentbuddy.core.meta.Action.MODIFY;
import static com.netikras.studies.studentbuddy.core.meta.Resource.LECTURE;

/**
 * Created by netikras on 17.6.21.
 */
@RestController
@RequestMapping(value = "/lecture")
public class LecturesController {

    @Resource
    private LectureService lectureService;

    /**
     * @param groupId   Students' group ID, e.g. PIN13
     * @param timeUnits Time units (ISO): M(onths), d(ays), H(ours), m(inutes), s(econds)
     * @param value     Time value
     */
    @RequestMapping(
            value = "/group/id/{groupId}/in/t/{timeUnits}/{value}",
            method = RequestMethod.GET
    )
    @ResponseBody
    @Authorizable(resource = LECTURE, action = GET)
    public List<LectureDto> getLecturesByGroupStartingIn(
            @PathVariable(name = "groupId") String groupId,
            @PathVariable(name = "timeUnits") String timeUnits,
            @PathVariable(name = "value") long value
    ) {
        return getLecturesByGroupStartingBetween(groupId, System.currentTimeMillis(), timeUnitsToMs(timeUnits, value));
    }

    @RequestMapping(
            value = "/student/id/{studentId}/in/t/{timeUnits}/{value}",
            method = RequestMethod.GET
    )
    @ResponseBody
    @Authorizable(resource = LECTURE, action = GET)
    public List<LectureDto> getLecturesByStudentStartingIn(
            @PathVariable(name = "studentId") String studentId,
            @PathVariable(name = "timeUnits") String timeUnits,
            @PathVariable(name = "value") long value
    ) {
        return getLecturesByStudentStartingBetween(studentId, System.currentTimeMillis(), timeUnitsToMs(timeUnits, value));
    }

    @RequestMapping(
            value = "/lecturer/id/{lecturerId}/in/t/{timeUnits}/{value}",
            method = RequestMethod.GET
    )
    @ResponseBody
    @Authorizable(resource = LECTURE, action = GET)
    public List<LectureDto> getLecturesByLecturerStartingIn(
            @PathVariable(name = "lecturerId") String lecturerId,
            @PathVariable(name = "timeUnits") String timeUnits,
            @PathVariable(name = "value") long value
    ) {
        return getLecturesByLecturerStartingBetween(lecturerId, System.currentTimeMillis(), timeUnitsToMs(timeUnits, value));
    }

    @RequestMapping(
            value = "/room/id/{roomId}/in/t/{timeUnits}/{value}",
            method = RequestMethod.GET
    )
    @ResponseBody
    @Authorizable(resource = LECTURE, action = GET)
    public List<LectureDto> getLecturesByRoomStartingIn(
            @PathVariable(name = "roomId") String roomId,
            @PathVariable(name = "timeUnits") String timeUnits,
            @PathVariable(name = "value") long value
    ) {
        return getLecturesByRoomStartingBetween(roomId, System.currentTimeMillis(), timeUnitsToMs(timeUnits, value));
    }


    @RequestMapping(
            value = "/group/id/{groupId}/between/{after}/{before}",
            method = RequestMethod.GET
    )
    @ResponseBody
    @Authorizable(resource = LECTURE, action = GET)
    public List<LectureDto> getLecturesByGroupStartingBetween(
            @PathVariable(name = "groupId") String groupId,
            @PathVariable(name = "after") long afterTimestamp,
            @PathVariable(name = "before") long beforeTimestamp
    ) {
        List<Lecture> lectures = lectureService.findLecturesForStudentsGroup(groupId, new Date(afterTimestamp), new Date(beforeTimestamp));
        List<LectureDto> lectureDtos = (List<LectureDto>) ModelMapper.transformAll(lectures, LectureDto.class);
        return lectureDtos;
    }

    @RequestMapping(
            value = "/student/id/{studentId}/between/{after}/{before}",
            method = RequestMethod.GET
    )
    @ResponseBody
    @Authorizable(resource = LECTURE, action = GET)
    public List<LectureDto> getLecturesByStudentStartingBetween(
            @PathVariable(name = "studentId") String studentId,
            @PathVariable(name = "after") long afterTimestamp,
            @PathVariable(name = "before") long beforeTimestamp
    ) {
        List<Lecture> lectures = lectureService.findLecturesForGuest(studentId, new Date(afterTimestamp), new Date(beforeTimestamp));
        List<LectureDto> lectureDtos = (List<LectureDto>) ModelMapper.transformAll(lectures, LectureDto.class);
        return lectureDtos;
    }

    @RequestMapping(
            value = "/lecturer/id/{lecturerId}/between/{after}/{before}",
            method = RequestMethod.GET
    )
    @ResponseBody
    @Authorizable(resource = LECTURE, action = GET)
    public List<LectureDto> getLecturesByLecturerStartingBetween(
            @PathVariable(name = "lecturerId") String lecturerId,
            @PathVariable(name = "after") long afterTimestamp,
            @PathVariable(name = "before") long beforeTimestamp
    ) {
        List<Lecture> lectures = lectureService.findLecturesForLecturer(lecturerId, new Date(afterTimestamp), new Date(beforeTimestamp));
        List<LectureDto> lectureDtos = (List<LectureDto>) ModelMapper.transformAll(lectures, LectureDto.class);
        return lectureDtos;
    }

    @RequestMapping(
            value = "/room/id/{roomId}/between/{after}/{before}",
            method = RequestMethod.GET
    )
    @ResponseBody
    @Authorizable(resource = LECTURE, action = GET)
    public List<LectureDto> getLecturesByRoomStartingBetween(
            @PathVariable(name = "roomId") String roomId,
            @PathVariable(name = "after") long afterTimestamp,
            @PathVariable(name = "before") long beforeTimestamp
    ) {
        List<Lecture> lectures = lectureService.findLecturesForRoom(roomId, new Date(afterTimestamp), new Date(beforeTimestamp));
        List<LectureDto> lectureDtos = (List<LectureDto>) ModelMapper.transformAll(lectures, LectureDto.class);
        return lectureDtos;
    }


    @RequestMapping(
            value = "/id/{id}",
            method = RequestMethod.GET
    )
    @ResponseBody
    @Authorizable(resource = LECTURE, action = GET)
    public LectureDto getLecture(
            @PathVariable(name = "id") String id
    ) {
        Lecture lecture = lectureService.findLecture(id);
        LectureDto lectureDto = ModelMapper.transform(lecture, new LectureDto());
        return lectureDto;
    }


    @RequestMapping(
            value = "/",
            method = RequestMethod.PUT
    )
    @ResponseBody
    @Authorizable(resource = LECTURE, action = MODIFY)
    public LectureDto updateLecture(
            @RequestBody LectureDto lectureDto
    ) {
        Lecture lecture = ModelMapper.apply(new Lecture(), lectureDto);
        lecture = lectureService.updateLecture(lecture);
        lectureDto = ModelMapper.transform(lecture, new LectureDto());
        return lectureDto;
    }






    public long timeUnitsToMs(String units, long value) {

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
