package com.netikras.studies.studentbuddy.api.timetable.controller.producer.impl.secured;

import com.netikras.studies.studentbuddy.core.data.api.dto.school.LectureDto;
import com.netikras.studies.studentbuddy.core.data.api.model.Lecture;
import com.netikras.studies.studentbuddy.core.data.meta.annotations.Authorizable;
import com.netikras.studies.studentbuddy.core.service.LectureService;
import com.netikras.tools.common.model.mapper.MappingSettings;
import com.netikras.tools.common.model.mapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import static com.netikras.studies.studentbuddy.core.data.meta.Action.*;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.LECTURE;

@Component
public class LecturesProducerImpl {

    @Resource
    private LectureService lectureService;



    @Authorizable(resource = LECTURE, action = PURGE)
    public void purgeLectureDto(String id) {
        lectureService.purgeLecture(id);
    }

    /*
     * @param groupId   Students' group ID, e.g. PIN13
     * @param timeUnits Time units (ISO): M(onths), d(ays), H(ours), m(inutes), s(econds)
     * @param value     Time value
     */

    @Authorizable(resource = LECTURE, action = CREATE)
    public LectureDto createLecture(LectureDto lectureDto) {
        Lecture lecture = ModelMapper.apply(new Lecture(), lectureDto, new MappingSettings().setForceUpdate(true));
        if (lecture != null) lecture.setId(null);
        lecture = lectureService.createLecture(lecture);
        lectureDto = ModelMapper.transform(lecture, new LectureDto());
        return lectureDto;
    }

    @Authorizable(resource = LECTURE, action = GET)
    public LectureDto getLecture(String id) {
        Lecture lecture = lectureService.getLecture(id);
        LectureDto lectureDto = ModelMapper.transform(lecture, new LectureDto());
        return lectureDto;
    }

    @Authorizable(resource = LECTURE, action = MODIFY)
    public LectureDto updateLecture(LectureDto lectureDto) {
        Lecture lecture = ModelMapper.apply(new Lecture(), lectureDto);
        lecture = lectureService.updateLecture(lecture);
        lectureDto = ModelMapper.transform(lecture, new LectureDto());
        return lectureDto;
    }

    @Authorizable(resource = LECTURE, action = DELETE)
    public void deleteLecture(String id) {
        lectureService.deleteLecture(id);
    }

    @Authorizable(resource = LECTURE, action = GET)
    public List<LectureDto> getAllLecturesByGroupIdStartingIn(String groupId, String timeUnits, long value) {
        return getAllLecturesByGroupIdStartingBetween(groupId, System.currentTimeMillis(), timeUnitsToMs(timeUnits, value));
    }

    @Authorizable(resource = LECTURE, action = GET)
    public List<LectureDto> getAllLecturesByStudentIdStartingIn(String studentId, String timeUnits, long value) {
        return getAllLecturesByStudentIdStartingBetween(studentId, System.currentTimeMillis(), timeUnitsToMs(timeUnits, value));
    }

    @Authorizable(resource = LECTURE, action = GET)
    public List<LectureDto> getAllLecturesByLecturerIdStartingIn(String lecturerId, String timeUnits, long value) {
        return getAllLecturesByLecturerIdStartingBetween(lecturerId, System.currentTimeMillis(), timeUnitsToMs(timeUnits, value));
    }

    @Authorizable(resource = LECTURE, action = GET)
    public List<LectureDto> getAllLecturesByRoomIdStartingIn(String roomId, String timeUnits, long value) {
        return getAllLecturesByRoomIdStartingBetween(roomId, System.currentTimeMillis(), timeUnitsToMs(timeUnits, value));
    }

    @Authorizable(resource = LECTURE, action = GET)
    public List<LectureDto> getAllLecturesByGroupIdStartingBetween(String groupId, long after, long before) {
        List<Lecture> lectures = lectureService.findLecturesForStudentsGroup(groupId, new Date(after), new Date(before));
        List<LectureDto> lectureDtos = (List<LectureDto>) ModelMapper.transformAll(lectures, LectureDto.class);
        return lectureDtos;
    }

    @Authorizable(resource = LECTURE, action = GET)
    public List<LectureDto> getAllLecturesByStudentIdStartingBetween(String studentId, long after, long before) {
        List<Lecture> lectures = lectureService.findLecturesForGuest(studentId, new Date(after), new Date(before));
        List<LectureDto> lectureDtos = (List<LectureDto>) ModelMapper.transformAll(lectures, LectureDto.class);
        return lectureDtos;
    }

    @Authorizable(resource = LECTURE, action = GET)
    public List<LectureDto> getAllLecturesByLecturerIdStartingBetween(String lecturerId, long after, long before) {
        List<Lecture> lectures = lectureService.findLecturesForLecturer(lecturerId, new Date(after), new Date(before));
        List<LectureDto> lectureDtos = (List<LectureDto>) ModelMapper.transformAll(lectures, LectureDto.class);
        return lectureDtos;
    }

    @Authorizable(resource = LECTURE, action = GET)
    public List<LectureDto> getAllLecturesByRoomIdStartingBetween(String roomId, long after, long before) {
        List<Lecture> lectures = lectureService.findLecturesForRoom(roomId, new Date(after), new Date(before));
        List<LectureDto> lectureDtos = (List<LectureDto>) ModelMapper.transformAll(lectures, LectureDto.class);
        return lectureDtos;
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
