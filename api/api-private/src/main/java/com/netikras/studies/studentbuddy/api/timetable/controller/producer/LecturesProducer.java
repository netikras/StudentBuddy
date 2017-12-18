package com.netikras.studies.studentbuddy.api.timetable.controller.producer;

import com.netikras.studies.studentbuddy.api.timetable.controller.generated.LecturesApiProducer;
import com.netikras.studies.studentbuddy.api.timetable.controller.producer.impl.secured.LecturesProducerImpl;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.LectureDto;
import com.netikras.studies.studentbuddy.core.data.api.model.Lecture;
import com.netikras.tools.common.model.mapper.MappingSettings;
import com.netikras.tools.common.model.mapper.ModelMapper;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RestController
public class LecturesProducer extends LecturesApiProducer {

    @Resource
    private LecturesProducerImpl impl;



    @Override
    protected void onPurgeLectureDto(String id) {
        impl.purgeLectureDto(id);
    }

    /*
     * @param groupId   Students' group ID, e.g. PIN13
     * @param timeUnits Time units (ISO): M(onths), d(ays), H(ours), m(inutes), s(econds)
     * @param value     Time value
     */

    @Override
    protected LectureDto onCreateLectureDto(LectureDto lectureDto) {
        return impl.createLecture(lectureDto);
    }

    @Override
    protected LectureDto onRetrieveLectureDto(String id) {
        return impl.getLecture(id);
    }

    @Override
    protected LectureDto onUpdateLectureDto(LectureDto lectureDto) {
        return impl.updateLecture(lectureDto);
    }

    @Override
    protected void onDeleteLectureDto(String id) {
        impl.deleteLecture(id);
    }

    @Override
    protected List<LectureDto> onGetLectureDtoAllByGroupIdStartingIn(String groupId, String timeUnits, long value, long offsetValue) {
        return impl.getAllLecturesByGroupIdStartingIn(groupId, timeUnits, value, offsetValue);
    }

    @Override
    protected List<LectureDto> onGetLectureDtoAllByStudentIdStartingIn(String studentId, String timeUnits, long value, long offsetValue) {
        return impl.getAllLecturesByStudentIdStartingIn(studentId, timeUnits, value, offsetValue);
    }

    @Override
    protected List<LectureDto> onGetLectureDtoAllByLecturerIdStartingIn(String lecturerId, String timeUnits, long value, long offsetValue) {
        return impl.getAllLecturesByLecturerIdStartingIn(lecturerId, timeUnits, value, offsetValue);
    }

    @Override
    protected List<LectureDto> onGetLectureDtoAllByRoomIdStartingIn(String roomId, String timeUnits, long value, long offsetValue) {
        return impl.getAllLecturesByRoomIdStartingIn(roomId, timeUnits, value, offsetValue);
    }

    @Override
    protected List<LectureDto> onGetLectureDtoAllByGroupIdStartingBetween(String groupId, long after, long before) {
        return impl.getAllLecturesByGroupIdStartingBetween(groupId, after, before);
    }

    @Override
    protected List<LectureDto> onGetLectureDtoAllByStudentIdStartingBetween(String studentId, long after, long before) {
        return impl.getAllLecturesByStudentIdStartingBetween(studentId, after, before);
    }

    @Override
    protected List<LectureDto> onGetLectureDtoAllByLecturerIdStartingBetween(String lecturerId, long after, long before) {
        return impl.getAllLecturesByLecturerIdStartingBetween(lecturerId, after, before);
    }

    @Override
    protected List<LectureDto> onGetLectureDtoAllByRoomIdStartingBetween(String roomId, long after, long before) {
        return impl.getAllLecturesByRoomIdStartingBetween(roomId, after, before);
    }


    @Override
    protected List<LectureDto> onGetLectureDtoAllByCourseIdStartingIn(String id, String timeUnits, long value, long offsetValue) {
        return impl.getAllLecturesByCourseIdStartingIn(id, timeUnits, value, offsetValue);
    }

    @Override
    protected List<LectureDto> onGetLectureDtoAllByBuildingIdStartingIn(String id, String timeUnits, long value, long offsetValue) {
        return impl.getAllLecturesByBuildingIdStartingIn(id, timeUnits, value, offsetValue);
    }

    @Override
    protected List<LectureDto> onGetLectureDtoAllBySectionIdStartingIn(String id, String timeUnits, long value, long offsetValue) {
        return impl.getAllLecturesBySectionIdStartingIn(id, timeUnits, value, offsetValue);
    }

    @Override
    protected List<LectureDto> onGetLectureDtoAllByFloorIdStartingIn(String id, String timeUnits, long value, long offsetValue) {
        return impl.getAllLecturesByFloorIdStartingIn(id, timeUnits, value, offsetValue);
    }

    @Override
    protected List<LectureDto> onGetLectureDtoAllByCourseIdStartingBetween(String id, long after, long before) {
        return impl.getAllLecturesByCourseIdStartingBetween(id, after, before);
    }

    @Override
    protected List<LectureDto> onGetLectureDtoAllByBuildingIdStartingBetween(String id, long after, long before) {
        return impl.getAllLecturesByBuildingIdStartingBetween(id, after, before);
    }

    @Override
    protected List<LectureDto> onGetLectureDtoAllBySectionIdStartingBetween(String id, long after, long before) {
        return impl.getAllLecturesBySectionIdStartingBetween(id, after, before);
    }

    @Override
    protected List<LectureDto> onGetLectureDtoAllByFloorIdStartingBetween(String id, long after, long before) {
        return impl.getAllLecturesByFloorIdStartingBetween(id, after, before);
    }
}
