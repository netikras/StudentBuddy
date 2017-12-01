package com.netikras.studies.studentbuddy.api.timetable.controller.producer;

import com.netikras.studies.studentbuddy.api.timetable.controller.generated.AssignmentApiProducer;
import com.netikras.studies.studentbuddy.api.timetable.controller.producer.impl.secured.AssignmentProducerImpl;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.AssignmentDto;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class AssignmentProducer extends AssignmentApiProducer {

    @Resource
    private AssignmentProducerImpl impl;


    @Override
    protected void onPurgeAssignmentDto(String id) {
        impl.purgeAssignment(id);
    }

    @Override
    protected AssignmentDto onUpdateAssignmentDto(AssignmentDto dto) {
        return impl.updateAssignment(dto);
    }

    @Override
    protected AssignmentDto onRetrieveAssignmentDto(String id) {
        return impl.getAssignment(id);
    }

    @Override
    protected AssignmentDto onCreateAssignmentDto(AssignmentDto dto) {
        return impl.createAssignment(dto);
    }

    @Override
    protected void onDeleteAssignmentDto(String id) {
        impl.deleteAssignment(id);
    }

    @Override
    protected List<AssignmentDto> onGetAssignmentDtoAllByDisciplineId(String disciplineId, long after, long before) {
        return impl.getAllAssignmentsByDisciplineId(disciplineId, after, before);
    }

    @Override
    protected List<AssignmentDto> onGetAssignmentDtoAllByGroupId(String groupId, long after, long before) {
        return impl.getAllAssignmentsByGroupId(groupId, after, before);
    }

    @Override
    protected List<AssignmentDto> onGetAssignmentDtoAllByStudentId(String studentId, long after, long before) {
        return impl.getAllAssignmentsByStudentId(studentId, after, before);
    }

    @Override
    protected List<AssignmentDto> onGetAssignmentDtoAllByDisciplineIdAndGroupId(String disciplineId, String groupId, long after, long before) {
        return impl.getAllAssignmentsByDisciplineIdAndGroupId(disciplineId, groupId, after, before);
    }

    @Override
    protected List<AssignmentDto> onGetAssignmentDtoAllByDisciplineIdAndStudentId(String disciplineId, String studentId, long after, long before) {
        return impl.getAllAssignmentsByDisciplineIdAndStudentId(studentId, studentId, after, before);
    }

    @Override
    protected List<AssignmentDto> onGetAssignmentDtoAllByLectureId(String lectureId) {
        return impl.getAllAssignmentsByLectureId(lectureId);
    }

    @Override
    protected AssignmentDto onCreateAssignmentDtoNew(String dueLectureId, String description) {
        return impl.createAssignmentNew(dueLectureId, description);
    }

    @Override
    protected List<AssignmentDto> onGetAssignmentDtoAllByCourseId(String id) {
        return impl.getAssignmentsByCourseId(id);
    }

    @Override
    protected List<AssignmentDto> onGetAssignmentDtoAllByCourseIdStartingBetween(String id, long after, long before) {
        return impl.getAssignmentsByCourseIdStartingBetween(id, after, before);
    }
}
