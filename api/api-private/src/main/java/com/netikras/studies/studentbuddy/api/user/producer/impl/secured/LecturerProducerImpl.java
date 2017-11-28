package com.netikras.studies.studentbuddy.api.user.producer.impl.secured;

import com.netikras.studies.studentbuddy.api.handlers.DtoMapper;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.LecturerDto;
import com.netikras.studies.studentbuddy.core.data.api.model.Lecturer;
import com.netikras.studies.studentbuddy.core.data.meta.annotations.Authorizable;
import com.netikras.studies.studentbuddy.core.service.LecturerService;
import com.netikras.studies.studentbuddy.core.validator.EntityProvider;
import com.netikras.tools.common.model.mapper.MappingSettings;
import com.netikras.tools.common.model.mapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

import static com.netikras.studies.studentbuddy.core.data.meta.Action.*;
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
        LecturerDto dto = (LecturerDto) dtoMapper.toDto(lecturer, 3);

        return dto;
    }

    @Authorizable(resource = LECTURER, action = MODIFY)
    public LecturerDto updateLecturer(LecturerDto dto) {
        Lecturer lecturer = modelMapper.apply(entityProvider.fetch(dto), dto);
        lecturer = lecturerService.updateLecturer(lecturer);
        dto = (LecturerDto) dtoMapper.toDto(lecturer, 3);

        return dto;
    }


    @Authorizable(resource = LECTURER, action = GET)
    public List<LecturerDto> getAllLecturersByDiscipline(String id) {
        List<Lecturer> lecturers = lecturerService.findLecturersByDiscipline(id);
        List<LecturerDto> dtos = (List<LecturerDto>) modelMapper.transformAll(lecturers, LecturerDto.class);

        return dtos;
    }


    @Authorizable(resource = LECTURER, action = GET)
    public List<LecturerDto> getAllLecturersByPersonId(String id) {
        List<Lecturer> lecturers = lecturerService.getLecturersByPerson(id);
        List<LecturerDto> dtos = (List<LecturerDto>) modelMapper.transformAll(lecturers, LecturerDto.class);

        return dtos;
    }

    @Authorizable(resource = LECTURER, action = SEARCH)
    public List<LecturerDto> searchLecturersByDegree(String degree) {
        List<Lecturer> lecturers = lecturerService.searchAllByDegree(degree);
        List<LecturerDto> lecturerDtos =
                (List<LecturerDto>) modelMapper.transformAll(lecturers, LecturerDto.class, new MappingSettings().setDepthMax(3));

        return lecturerDtos;
    }

    @Authorizable(resource = LECTURER, action = SEARCH)
    public List<LecturerDto> searchLecturersByFirstName(String fname) {
        List<Lecturer> lecturers = lecturerService.searchAllByFirstName(fname);
        List<LecturerDto> lecturerDtos =
                (List<LecturerDto>) modelMapper.transformAll(lecturers, LecturerDto.class, new MappingSettings().setDepthMax(3));

        return lecturerDtos;
    }

    @Authorizable(resource = LECTURER, action = SEARCH)
    public List<LecturerDto> searchLecturersByLastName(String lname) {
        List<Lecturer> lecturers = lecturerService.searchAllByLastName(lname);
        List<LecturerDto> lecturerDtos =
                (List<LecturerDto>) modelMapper.transformAll(lecturers, LecturerDto.class, new MappingSettings().setDepthMax(3));

        return lecturerDtos;
    }
}
