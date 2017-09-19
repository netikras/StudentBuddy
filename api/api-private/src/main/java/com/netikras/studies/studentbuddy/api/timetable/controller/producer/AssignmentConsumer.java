package com.netikras.studies.studentbuddy.api.timetable.controller.producer;

import com.netikras.studies.studentbuddy.api.timetable.controller.generated.AssignmentApiProducer;
import com.netikras.studies.studentbuddy.commons.exception.StudBudUncheckedException;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.AssignmentDto;
import com.netikras.studies.studentbuddy.core.data.api.model.Assignment;
import com.netikras.studies.studentbuddy.core.data.api.model.Lecture;
import com.netikras.studies.studentbuddy.core.data.meta.annotations.Authorizable;
import com.netikras.studies.studentbuddy.core.service.LectureService;
import com.netikras.tools.common.model.mapper.MappingSettings;
import com.netikras.tools.common.model.mapper.ModelMapper;
import com.netikras.tools.common.remote.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import static com.netikras.studies.studentbuddy.core.data.meta.Action.CREATE;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.DELETE;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.GET;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.MODIFY;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.PURGE;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.ASSIGNMENT;

@RestController
public class AssignmentConsumer extends AssignmentApiProducer {

    @Resource
    private LectureService lectureService;


    @Override
    @Authorizable(resource = ASSIGNMENT, action = PURGE)
    protected void onPurgeAssignmentDto(String id) {
        lectureService.purgeLectureAssignment(id);
    }

    @Override
    @Authorizable(resource = ASSIGNMENT, action = MODIFY)
    protected AssignmentDto onUpdateAssignmentDto(AssignmentDto dto) {
        Assignment assignment = ModelMapper.apply(new Assignment(), dto);
        assignment = lectureService.updateAssignment(assignment);
        dto = ModelMapper.transform(assignment, new AssignmentDto());

        return dto;
    }

    @Override
    @Authorizable(resource = ASSIGNMENT, action = GET)
    protected AssignmentDto onRetrieveAssignmentDto(String id) {
        Assignment assignment = lectureService.getAssignment(id);
        AssignmentDto dto = ModelMapper.transform(assignment, new AssignmentDto());
        return dto;
    }

    @Override
    @Authorizable(resource = ASSIGNMENT, action = CREATE)
    protected AssignmentDto onCreateAssignmentDto(AssignmentDto dto) {
        Assignment assignment = ModelMapper.apply(new Assignment(), dto, new MappingSettings().setForceUpdate(true));
        if (assignment != null) assignment.setId(null);
        assignment = lectureService.createAssignment(assignment);
        dto = ModelMapper.transform(assignment, new AssignmentDto());
        return dto;
    }

    @Override
    @Authorizable(resource = ASSIGNMENT, action = DELETE)
    protected void onDeleteAssignmentDto(String id) {
        lectureService.deleteAssignment(id);
    }

    @Override
    @Authorizable(resource = ASSIGNMENT, action = GET)
    protected List<AssignmentDto> onGetAssignmentDtoAllByDisciplineId(String disciplineId, long after, long before) {
        List<Assignment> assignments = lectureService.getAllAssignmentsForDiscipline(disciplineId, new Date(after), new Date(before));
        List<AssignmentDto> dtos = (List<AssignmentDto>) ModelMapper.transformAll(assignments, AssignmentDto.class);
        return dtos;
    }

    @Override
    @Authorizable(resource = ASSIGNMENT, action = GET)
    protected List<AssignmentDto> onGetAssignmentDtoAllByGroupId(String groupId, long after, long before) {
        List<Assignment> assignments = lectureService.getAllAssignmentsForGroup(groupId, new Date(after), new Date(before));
        List<AssignmentDto> dtos = (List<AssignmentDto>) ModelMapper.transformAll(assignments, AssignmentDto.class);
        return dtos;
    }

    @Override
    @Authorizable(resource = ASSIGNMENT, action = GET)
    protected List<AssignmentDto> onGetAssignmentDtoAllByStudentId(String studentId, long after, long before) {
        List<Assignment> assignments = lectureService.getAllAssignmentsForStudent(studentId, new Date(after), new Date(before));
        List<AssignmentDto> dtos = (List<AssignmentDto>) ModelMapper.transformAll(assignments, AssignmentDto.class);
        return dtos;
    }

    @Override
    @Authorizable(resource = ASSIGNMENT, action = GET)
    protected List<AssignmentDto> onGetAssignmentDtoAllByDisciplineIdAndGroupId(String disciplineId, String groupId, long after, long before) {
        List<Assignment> assignments =
                lectureService.getAllAssignmentsForDisciplineAndGroup(disciplineId, groupId, new Date(after), new Date(before));
        List<AssignmentDto> dtos = (List<AssignmentDto>) ModelMapper.transformAll(assignments, AssignmentDto.class);
        return dtos;
    }

    @Override
    @Authorizable(resource = ASSIGNMENT, action = GET)
    protected List<AssignmentDto> onGetAssignmentDtoAllByDisciplineIdAndStudentId(String disciplineId, String studentId, long after, long before) {
        List<Assignment> assignments =
                lectureService.getAllAssignmentsForDisciplineAndStudent(disciplineId, studentId, new Date(after), new Date(before));
        List<AssignmentDto> dtos = (List<AssignmentDto>) ModelMapper.transformAll(assignments, AssignmentDto.class);
        return dtos;
    }

    @Override
    @Authorizable(resource = ASSIGNMENT, action = GET)
    protected List<AssignmentDto> onGetAssignmentDtoAllByLectureId(String lectureId) {
        List<Assignment> assignments = lectureService.getAllAssignmentsForLecture(lectureId);
        List<AssignmentDto> dtos = (List<AssignmentDto>) ModelMapper.transformAll(assignments, AssignmentDto.class);
        return dtos;
    }

    @Override
    @Authorizable(resource = ASSIGNMENT, action = CREATE)
    protected AssignmentDto onCreateAssignmentDtoNew(String dueLectureId, String description) {
        Lecture lecture = lectureService.getLecture(dueLectureId);

        if (lecture == null) {
            throw new StudBudUncheckedException()
                    .setMessage1("Cannot create assignment for lecture")
                    .setMessage2("Lecture with given ID does not exist")
                    .setProbableCause(dueLectureId)
                    .setStatusCode(HttpStatus.NOT_FOUND)
                    ;
        }
        Assignment assignment = new Assignment();
        assignment.setDiscipline(lecture.getDiscipline());
        assignment.setDescription(description);
        assignment.setLecture(lecture);
        assignment.setDueDate(lecture.getStartsOn());

        assignment = lectureService.createAssignment(assignment);
        AssignmentDto dto = ModelMapper.transform(assignment, new AssignmentDto());
        return dto;
    }
}
