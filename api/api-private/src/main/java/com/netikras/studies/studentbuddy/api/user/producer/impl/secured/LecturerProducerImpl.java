package com.netikras.studies.studentbuddy.api.user.producer.impl.secured;

import com.netikras.studies.studentbuddy.api.handlers.DtoMapper;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.LecturerDto;
import com.netikras.studies.studentbuddy.core.data.api.model.Lecturer;
import com.netikras.studies.studentbuddy.core.data.meta.annotations.Authorizable;
import com.netikras.studies.studentbuddy.core.service.LecturerService;
import com.netikras.studies.studentbuddy.core.validator.EntityProvider;
import com.netikras.tools.common.model.mapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

import static com.netikras.studies.studentbuddy.core.data.meta.Action.GET;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.MODIFY;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.SEARCH;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.LECTURER;

@Component
public class LecturerProducerImpl {

    @Resource
    private ModelMapper modelMapper;

    @Resource
    private LecturerService lecturerService;
    @Resource
    private DtoMapper dtoMapper;
    @Resource
    private EntityProvider entityProvider;

    @Authorizable(resource = LECTURER, action = GET)
    public LecturerDto getLecturer(String id) {
        Lecturer lecturer = lecturerService.getLecturer(id);
        LecturerDto dto = (LecturerDto) dtoMapper.toDto(lecturer);
        return dto;
    }

    @Authorizable(resource = LECTURER, action = MODIFY)
    @Transactional
    public LecturerDto updateLecturer(LecturerDto dto) {
        Lecturer lecturer = modelMapper.apply(entityProvider.fetch(dto), dto);
        lecturer = lecturerService.updateLecturer(lecturer);
        dto = (LecturerDto) dtoMapper.toDto(lecturer);
        return dto;
    }


    @Authorizable(resource = LECTURER, action = GET)
    @Transactional
    public List<LecturerDto> getAllLecturersByDiscipline(String id) {
        List<Lecturer> lecturers = lecturerService.findLecturersByDiscipline(id);
        List<LecturerDto> lecturerDtos = (List<LecturerDto>) dtoMapper.toDtos(lecturers);
        return lecturerDtos;
    }


    @Authorizable(resource = LECTURER, action = GET)
    @Transactional
    public List<LecturerDto> getAllLecturersByPersonId(String id) {
        List<Lecturer> lecturers = lecturerService.getLecturersByPerson(id);
        List<LecturerDto> lecturerDtos = (List<LecturerDto>) dtoMapper.toDtos(lecturers);
        return lecturerDtos;
    }

    @Authorizable(resource = LECTURER, action = SEARCH)
    @Transactional
    public List<LecturerDto> searchLecturersByDegree(String degree) {
        List<Lecturer> lecturers = lecturerService.searchAllByDegree(degree);
        List<LecturerDto> lecturerDtos = (List<LecturerDto>) dtoMapper.toDtos(lecturers);
        return lecturerDtos;
    }

    @Authorizable(resource = LECTURER, action = SEARCH)
    @Transactional
    public List<LecturerDto> searchLecturersByFirstName(String fname) {
        List<Lecturer> lecturers = lecturerService.searchAllByFirstName(fname);
        List<LecturerDto> lecturerDtos = (List<LecturerDto>) dtoMapper.toDtos(lecturers);
        return lecturerDtos;
    }

    @Authorizable(resource = LECTURER, action = SEARCH)
    @Transactional
    public List<LecturerDto> searchLecturersByLastName(String lname) {
        List<Lecturer> lecturers = lecturerService.searchAllByLastName(lname);
        List<LecturerDto> lecturerDtos = (List<LecturerDto>) dtoMapper.toDtos(lecturers);
        return lecturerDtos;
    }
}
