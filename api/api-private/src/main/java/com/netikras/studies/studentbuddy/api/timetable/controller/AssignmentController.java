package com.netikras.studies.studentbuddy.api.timetable.controller;


import com.netikras.studies.studentbuddy.commons.exception.StudBudUncheckedException;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.AssignmentDto;
import com.netikras.studies.studentbuddy.core.data.api.model.Assignment;
import com.netikras.studies.studentbuddy.core.data.api.model.Lecture;
import com.netikras.studies.studentbuddy.core.meta.annotations.Authorizable;
import com.netikras.studies.studentbuddy.core.service.LectureService;
import com.netikras.tools.common.model.mapper.ModelMapper;
import com.netikras.tools.common.remote.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static com.netikras.studies.studentbuddy.core.meta.Action.CREATE;
import static com.netikras.studies.studentbuddy.core.meta.Action.DELETE;
import static com.netikras.studies.studentbuddy.core.meta.Action.GET;
import static com.netikras.studies.studentbuddy.core.meta.Action.MODIFY;
import static com.netikras.studies.studentbuddy.core.meta.Resource.ASSIGNMENT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(value = "/assignment")
public class AssignmentController {

    @Resource
    private LectureService lectureService;

    @RequestMapping(
            value = "/id/{id}",
            method = RequestMethod.GET
    )
    @ResponseBody
    @Authorizable(resource = ASSIGNMENT, action = GET)
    public AssignmentDto getAssignment(
            @PathVariable(name = "id") String id
    ) {
        Assignment assignment = lectureService.getAssignment(id);
        AssignmentDto dto = ModelMapper.transform(assignment, new AssignmentDto());
        return dto;
    }


    @RequestMapping(
            value = "/",
            method = RequestMethod.POST
    )
    @ResponseBody
    @Authorizable(resource = ASSIGNMENT, action = CREATE)
    public AssignmentDto createAssignment(
            @RequestBody AssignmentDto dto
    ) {
        Assignment assignment = ModelMapper.apply(new Assignment(), dto);
        assignment = lectureService.createAssignment(assignment);
        dto = ModelMapper.transform(assignment, new AssignmentDto());
        return dto;
    }

    @RequestMapping(
            value = "/due/lecture/id/{id}",
            method = RequestMethod.POST
    )
    @ResponseBody
    @Authorizable(resource = ASSIGNMENT, action = CREATE)
    @Transactional
    public AssignmentDto createAssignment(
            @PathVariable(name = "id") String dueLectureId,
            @RequestBody String description
    ) {
        Lecture lecture = lectureService.findLecture(dueLectureId);

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


    @RequestMapping(
            value = "/",
            method = RequestMethod.PUT
    )
    @Authorizable(resource = ASSIGNMENT, action = MODIFY)
    public AssignmentDto updateAssignment(
            @RequestBody AssignmentDto dto
    ) {
        Assignment assignment = ModelMapper.apply(new Assignment(), dto);
        assignment = lectureService.updateAssignment(assignment);
        dto = ModelMapper.transform(assignment, new AssignmentDto());

        return dto;
    }

    @RequestMapping(
            value = "/id/{id}",
            method = RequestMethod.DELETE
    )
    @ResponseStatus(code = OK, reason = "Assignment has been deleted")
    @Authorizable(resource = ASSIGNMENT, action = DELETE)
    public void deleteAssignment(
            @PathVariable(name = "id") String id
    ) {
        lectureService.deleteAssignment(id);
    }


}
