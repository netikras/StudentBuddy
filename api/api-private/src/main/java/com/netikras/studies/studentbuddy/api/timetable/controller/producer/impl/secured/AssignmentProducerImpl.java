package com.netikras.studies.studentbuddy.api.timetable.controller.producer.impl.secured;

import com.netikras.studies.studentbuddy.commons.exception.StudBudUncheckedException;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.AssignmentDto;
import com.netikras.studies.studentbuddy.core.data.api.model.Assignment;
import com.netikras.studies.studentbuddy.core.data.api.model.Lecture;
import com.netikras.studies.studentbuddy.core.data.meta.annotations.Authorizable;
import com.netikras.studies.studentbuddy.core.service.LectureService;
import com.netikras.studies.studentbuddy.core.validator.EntityProvider;
import com.netikras.tools.common.model.mapper.MappingSettings;
import com.netikras.tools.common.model.mapper.ModelMapper;
import com.netikras.tools.common.remote.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import static com.netikras.studies.studentbuddy.core.data.meta.Action.*;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.ASSIGNMENT;

@Component
public class AssignmentProducerImpl {

    @Resource
    private ModelMapper modelMapper;

    @Resource
    private LectureService lectureService;
    @Resource
    private EntityProvider entityProvider;


    @Authorizable(resource = ASSIGNMENT, action = PURGE)
    public void purgeAssignment(String id) {
        lectureService.purgeLectureAssignment(id);
    }

    @Authorizable(resource = ASSIGNMENT, action = MODIFY)
    public AssignmentDto updateAssignment(AssignmentDto dto) {
        Assignment assignment = modelMapper.apply(entityProvider.fetch(dto), dto);
        assignment = lectureService.updateAssignment(assignment);
        dto = modelMapper.transform(assignment, new AssignmentDto(), new MappingSettings().setDepthMax(3));

        return dto;
    }

    @Authorizable(resource = ASSIGNMENT, action = GET)
    public AssignmentDto getAssignment(String id) {
        Assignment assignment = lectureService.getAssignment(id);
        AssignmentDto dto = modelMapper.transform(assignment, new AssignmentDto(), new MappingSettings().setDepthMax(3));
        return dto;
    }

    @Authorizable(resource = ASSIGNMENT, action = CREATE)
    public AssignmentDto createAssignment(AssignmentDto dto) {
        Assignment assignment = modelMapper.apply(new Assignment(), dto, new MappingSettings().setForceUpdate(true));
        if (assignment != null) assignment.setId(null);
        assignment = lectureService.createAssignment(assignment);
        dto = modelMapper.transform(assignment, new AssignmentDto(), new MappingSettings().setDepthMax(3));
        return dto;
    }

    @Authorizable(resource = ASSIGNMENT, action = DELETE)
    public void deleteAssignment(String id) {
        lectureService.deleteAssignment(id);
    }

    @Authorizable(resource = ASSIGNMENT, action = GET)
    public List<AssignmentDto> getAllAssignmentsByDisciplineId(String disciplineId, long after, long before) {
        List<Assignment> assignments = lectureService.getAllAssignmentsForDiscipline(disciplineId, new Date(after), new Date(before));
        List<AssignmentDto> dtos = (List<AssignmentDto>) modelMapper.transformAll(assignments, AssignmentDto.class, new MappingSettings().setDepthMax(3));
        return dtos;
    }

    @Authorizable(resource = ASSIGNMENT, action = GET)
    public List<AssignmentDto> getAllAssignmentsByGroupId(String groupId, long after, long before) {
        List<Assignment> assignments = lectureService.getAllAssignmentsForGroup(groupId, new Date(after), new Date(before));
        List<AssignmentDto> dtos = (List<AssignmentDto>) modelMapper.transformAll(assignments, AssignmentDto.class, new MappingSettings().setDepthMax(3));
        return dtos;
    }

    @Authorizable(resource = ASSIGNMENT, action = GET)
    public List<AssignmentDto> getAllAssignmentsByStudentId(String studentId, long after, long before) {
        List<Assignment> assignments = lectureService.getAllAssignmentsForStudent(studentId, new Date(after), new Date(before));
        List<AssignmentDto> dtos = (List<AssignmentDto>) modelMapper.transformAll(assignments, AssignmentDto.class, new MappingSettings().setDepthMax(3));
        return dtos;
    }

    @Authorizable(resource = ASSIGNMENT, action = GET)
    public List<AssignmentDto> getAllAssignmentsByDisciplineIdAndGroupId(String disciplineId, String groupId, long after, long before) {
        List<Assignment> assignments =
                lectureService.getAllAssignmentsForDisciplineAndGroup(disciplineId, groupId, new Date(after), new Date(before));
        List<AssignmentDto> dtos = (List<AssignmentDto>) modelMapper.transformAll(assignments, AssignmentDto.class, new MappingSettings().setDepthMax(3));
        return dtos;
    }

    @Authorizable(resource = ASSIGNMENT, action = GET)
    public List<AssignmentDto> getAllAssignmentsByDisciplineIdAndStudentId(String disciplineId, String studentId, long after, long before) {
        List<Assignment> assignments =
                lectureService.getAllAssignmentsForDisciplineAndStudent(disciplineId, studentId, new Date(after), new Date(before));
        List<AssignmentDto> dtos = (List<AssignmentDto>) modelMapper.transformAll(assignments, AssignmentDto.class, new MappingSettings().setDepthMax(3));
        return dtos;
    }

    @Authorizable(resource = ASSIGNMENT, action = GET)
    public List<AssignmentDto> getAllAssignmentsByLectureId(String lectureId) {
        List<Assignment> assignments = lectureService.getAllAssignmentsForLecture(lectureId);
        List<AssignmentDto> dtos = (List<AssignmentDto>) modelMapper.transformAll(assignments, AssignmentDto.class, new MappingSettings().setDepthMax(3));
        return dtos;
    }

    @Authorizable(resource = ASSIGNMENT, action = CREATE)
    public AssignmentDto createAssignmentNew(String dueLectureId, String description) {
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
        AssignmentDto dto = modelMapper.transform(assignment, new AssignmentDto());
        return dto;
    }

    @Authorizable(resource = ASSIGNMENT, action = GET)
    public List<AssignmentDto> getAssignmentsByCourseId(String id) {
        List<Assignment> assignments = lectureService.getAllAssignmentsForCourse(id);
        List<AssignmentDto> dtos = (List<AssignmentDto>) modelMapper.transformAll(assignments, AssignmentDto.class, new MappingSettings().setDepthMax(3));
        return dtos;
    }

    @Authorizable(resource = ASSIGNMENT, action = GET)
    public List<AssignmentDto> getAssignmentsByCourseIdStartingBetween(String id, long after, long before) {
        List<Assignment> assignments =  lectureService.getAllAssignmentsForCourseStartingBetween(id, new Date(after), new Date(before));
        List<AssignmentDto> dtos = (List<AssignmentDto>) modelMapper.transformAll(assignments, AssignmentDto.class, new MappingSettings().setDepthMax(3));
        return dtos;
    }
}
