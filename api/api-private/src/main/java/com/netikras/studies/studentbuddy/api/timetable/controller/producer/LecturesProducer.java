package com.netikras.studies.studentbuddy.api.timetable.controller.producer;

import com.netikras.studies.studentbuddy.api.timetable.controller.generated.LecturesApiProducer;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.LectureDto;
import com.netikras.studies.studentbuddy.core.data.api.model.Lecture;
import com.netikras.studies.studentbuddy.core.data.meta.annotations.Authorizable;
import com.netikras.studies.studentbuddy.core.service.LectureService;
import com.netikras.tools.common.model.mapper.MappingSettings;
import com.netikras.tools.common.model.mapper.ModelMapper;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import static com.netikras.studies.studentbuddy.core.data.meta.Action.CREATE;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.DELETE;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.GET;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.MODIFY;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.PURGE;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.LECTURE;

@RestController
public class LecturesProducer extends LecturesApiProducer {

    @Resource
    private LectureService lectureService;



    @Override
    @Authorizable(resource = LECTURE, action = PURGE)
    protected void onPurgeLectureDto(String id) {
        lectureService.purgeLecture(id);
    }

    /*
     * @param groupId   Students' group ID, e.g. PIN13
     * @param timeUnits Time units (ISO): M(onths), d(ays), H(ours), m(inutes), s(econds)
     * @param value     Time value
     */

    @Override
    @Authorizable(resource = LECTURE, action = CREATE)
    protected LectureDto onCreateLectureDto(LectureDto lectureDto) {
        Lecture lecture = ModelMapper.apply(new Lecture(), lectureDto, new MappingSettings().setForceUpdate(true));
        if (lecture != null) lecture.setId(null);
        lecture = lectureService.createLecture(lecture);
        lectureDto = ModelMapper.transform(lecture, new LectureDto());
        return lectureDto;
    }

    @Override
    @Authorizable(resource = LECTURE, action = GET)
    protected LectureDto onRetrieveLectureDto(String id) {
        Lecture lecture = lectureService.getLecture(id);
        LectureDto lectureDto = ModelMapper.transform(lecture, new LectureDto());
        return lectureDto;
    }

    @Override
    @Authorizable(resource = LECTURE, action = MODIFY)
    protected LectureDto onUpdateLectureDto(LectureDto lectureDto) {
        Lecture lecture = ModelMapper.apply(new Lecture(), lectureDto);
        lecture = lectureService.updateLecture(lecture);
        lectureDto = ModelMapper.transform(lecture, new LectureDto());
        return lectureDto;
    }

    @Override
    @Authorizable(resource = LECTURE, action = DELETE)
    protected void onDeleteLectureDto(String id) {
        lectureService.deleteLecture(id);
    }

    @Override
    @Authorizable(resource = LECTURE, action = GET)
    protected List<LectureDto> onGetLectureDtoAllByGroupIdStartingIn(String groupId, String timeUnits, long value) {
        return onGetLectureDtoAllByGroupIdStartingBetween(groupId, System.currentTimeMillis(), timeUnitsToMs(timeUnits, value));
    }

    @Override
    @Authorizable(resource = LECTURE, action = GET)
    protected List<LectureDto> onGetLectureDtoAllByStudentIdStartingIn(String studentId, String timeUnits, long value) {
        return onGetLectureDtoAllByStudentIdStartingBetween(studentId, System.currentTimeMillis(), timeUnitsToMs(timeUnits, value));
    }

    @Override
    @Authorizable(resource = LECTURE, action = GET)
    protected List<LectureDto> onGetLectureDtoAllByLecturerIdStartingIn(String lecturerId, String timeUnits, long value) {
        return onGetLectureDtoAllByLecturerIdStartingBetween(lecturerId, System.currentTimeMillis(), timeUnitsToMs(timeUnits, value));
    }

    @Override
    @Authorizable(resource = LECTURE, action = GET)
    protected List<LectureDto> onGetLectureDtoAllByRoomIdStartingIn(String roomId, String timeUnits, long value) {
        return onGetLectureDtoAllByRoomIdStartingBetween(roomId, System.currentTimeMillis(), timeUnitsToMs(timeUnits, value));
    }

    @Override
    @Authorizable(resource = LECTURE, action = GET)
    protected List<LectureDto> onGetLectureDtoAllByGroupIdStartingBetween(String groupId, long after, long before) {
        List<Lecture> lectures = lectureService.findLecturesForStudentsGroup(groupId, new Date(after), new Date(before));
        List<LectureDto> lectureDtos = (List<LectureDto>) ModelMapper.transformAll(lectures, LectureDto.class);
        return lectureDtos;
    }

    @Override
    @Authorizable(resource = LECTURE, action = GET)
    protected List<LectureDto> onGetLectureDtoAllByStudentIdStartingBetween(String studentId, long after, long before) {
        List<Lecture> lectures = lectureService.findLecturesForGuest(studentId, new Date(after), new Date(before));
        List<LectureDto> lectureDtos = (List<LectureDto>) ModelMapper.transformAll(lectures, LectureDto.class);
        return lectureDtos;
    }

    @Override
    @Authorizable(resource = LECTURE, action = GET)
    protected List<LectureDto> onGetLectureDtoAllByLecturerIdStartingBetween(String lecturerId, long after, long before) {
        List<Lecture> lectures = lectureService.findLecturesForLecturer(lecturerId, new Date(after), new Date(before));
        List<LectureDto> lectureDtos = (List<LectureDto>) ModelMapper.transformAll(lectures, LectureDto.class);
        return lectureDtos;
    }

    @Override
    @Authorizable(resource = LECTURE, action = GET)
    protected List<LectureDto> onGetLectureDtoAllByRoomIdStartingBetween(String roomId, long after, long before) {
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
