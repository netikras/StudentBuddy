package com.netikras.studies.studentbuddy.api.user;

import com.netikras.studies.studentbuddy.core.data.api.dto.school.LecturerDto;
import com.netikras.studies.studentbuddy.core.data.api.model.Lecturer;
import com.netikras.studies.studentbuddy.core.meta.annotations.Authorizable;
import com.netikras.studies.studentbuddy.core.service.LecturerService;
import com.netikras.tools.common.model.mapper.ModelMapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import java.util.List;

import static com.netikras.studies.studentbuddy.core.meta.Action.GET;
import static com.netikras.studies.studentbuddy.core.meta.Action.MODIFY;
import static com.netikras.studies.studentbuddy.core.meta.Resource.LECTURER;

@RestController
@RequestMapping(value = "/lecturer")
public class LecturerController {

    @Resource
    private LecturerService lecturerService;

    @RequestMapping(
            value = "/id/{id}",
            method = RequestMethod.GET
    )
    @ResponseBody
    @Authorizable(resource = LECTURER, action = GET)
    public LecturerDto getLecturer(
            @PathVariable(name = "id") String lecturerId
    ) {
        Lecturer lecturer = lecturerService.getLecturer(lecturerId);
        LecturerDto dto = ModelMapper.transform(lecturer, new LecturerDto());

        return dto;
    }


    @RequestMapping(
            value = "/discipline/id/{id}",
            method = RequestMethod.GET
    )
    @ResponseBody
    @Authorizable(resource = LECTURER, action = GET)
    public List<LecturerDto> getLecturersByDiscipline(
            @PathVariable(name = "id") String disciplineId
    ) {
        List<Lecturer> lecturers = lecturerService.findLecturersByDiscipline(disciplineId);
        List<LecturerDto> dtos = (List<LecturerDto>) ModelMapper.transformAll(lecturers, LecturerDto.class);

        return dtos;
    }

    @RequestMapping(
            value = "/person/id/{id}",
            method = RequestMethod.GET
    )
    @ResponseBody
    @Authorizable(resource = LECTURER, action = GET)
    public LecturerDto getLecturerByPersonId(
            @PathVariable(name = "id") String personId
    ) {
        Lecturer lecturers = lecturerService.getLecturerByPerson(personId);
        LecturerDto dto = ModelMapper.transform(lecturers, new LecturerDto());

        return dto;
    }


    @RequestMapping(
            value = "/",
            method = RequestMethod.PUT
    )
    @ResponseBody
    @Authorizable(resource = LECTURER, action = MODIFY)
    public LecturerDto updateLecturer(
            @RequestBody LecturerDto dto
    ) {
        Lecturer lecturer = ModelMapper.apply(new Lecturer(), dto);
        lecturer = lecturerService.updateLecturer(lecturer);
        dto = ModelMapper.transform(lecturer, new LecturerDto());

        return dto;
    }


}
