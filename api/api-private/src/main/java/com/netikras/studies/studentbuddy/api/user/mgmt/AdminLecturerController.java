package com.netikras.studies.studentbuddy.api.user.mgmt;

import com.netikras.studies.studentbuddy.core.data.api.dto.school.LecturerDto;
import com.netikras.studies.studentbuddy.core.data.api.model.Discipline;
import com.netikras.studies.studentbuddy.core.data.api.model.Lecturer;
import com.netikras.studies.studentbuddy.core.meta.annotations.Authorizable;
import com.netikras.studies.studentbuddy.core.service.LecturerService;
import com.netikras.studies.studentbuddy.core.service.SchoolService;
import com.netikras.studies.studentbuddy.core.service.impl.LectureServiceImpl;
import com.netikras.studies.studentbuddy.core.service.impl.LecturerServiceImpl;
import com.netikras.tools.common.model.mapper.ModelMapper;
import org.springframework.http.HttpStatus;
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
import static com.netikras.studies.studentbuddy.core.meta.Action.MODIFY;
import static com.netikras.studies.studentbuddy.core.meta.Resource.DISCIPLINE;
import static com.netikras.studies.studentbuddy.core.meta.Resource.LECTURER;

@RestController
@RequestMapping("/mgmt/lecturer")
public class AdminLecturerController {

    @Resource
    private LecturerService lecturerService;

    @Resource
    private SchoolService schoolService;

    @RequestMapping(
            value = "/",
            method = RequestMethod.POST
    )
    @ResponseBody
    @Authorizable(resource = LECTURER, action = CREATE)
    public LecturerDto createLecturer(
            @RequestBody LecturerDto dto
    ) {
        Lecturer lecturer = ModelMapper.apply(new Lecturer(), dto);
        lecturer = lecturerService.createLecturer(lecturer);
        dto = ModelMapper.transform(lecturer, new LecturerDto());

        return dto;
    }

    @RequestMapping(
            value = "/id/{id}",
            method = RequestMethod.DELETE
    )
    @ResponseStatus(code = HttpStatus.OK, reason = "Lecturer has been deleted")
    @Authorizable(resource = LECTURER, action = DELETE)
    public void deleteLecturer(
            @PathVariable(name = "id") String id
    ) {
        lecturerService.deleteLecturer(id);
    }

    @RequestMapping(
            value = "/id/{lecturerId}/discipline/{disciplineId}",
            method = RequestMethod.PUT
    )
    @ResponseStatus(code = HttpStatus.OK, reason = "Lecturer has been assigned to a discipline")
    @Authorizable(resource = DISCIPLINE, action = MODIFY)
    public void assignLecturerToDiscipline(
            @PathVariable(name = "lecturerId") String lecturerId,
            @PathVariable(name = "disciplineId") String disciplineId
    ) {
        Lecturer lecturer = lecturerService.getLecturer(lecturerId);
        Discipline discipline = schoolService.getDiscipline(disciplineId);
        lecturerService.attachToDiscipline(lecturer, discipline);
    }

    @RequestMapping(
            value = "/id/{lecturerId}/discipline/{disciplineId}",
            method = RequestMethod.DELETE
    )
    @ResponseStatus(code = HttpStatus.OK, reason = "Lecturer has been unassigned to a discipline")
    @Authorizable(resource = DISCIPLINE, action = MODIFY)
    public void unassignLecturerFromDiscipline(
            @PathVariable(name = "lecturerId") String lecturerId,
            @PathVariable(name = "disciplineId") String disciplineId
    ) {
        Lecturer lecturer = lecturerService.getLecturer(lecturerId);
        Discipline discipline = schoolService.getDiscipline(disciplineId);
        lecturerService.detatchFromDiscipline(lecturer, discipline);
    }



}
