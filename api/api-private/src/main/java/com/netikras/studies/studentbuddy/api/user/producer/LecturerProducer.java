package com.netikras.studies.studentbuddy.api.user.producer;

import com.netikras.studies.studentbuddy.api.user.generated.LecturerApiProducer;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.LecturerDto;
import com.netikras.studies.studentbuddy.core.data.api.model.Lecturer;
import com.netikras.studies.studentbuddy.core.meta.annotations.Authorizable;
import com.netikras.studies.studentbuddy.core.service.LecturerService;
import com.netikras.tools.common.model.mapper.MappingSettings;
import com.netikras.tools.common.model.mapper.ModelMapper;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

import static com.netikras.studies.studentbuddy.core.meta.Action.GET;
import static com.netikras.studies.studentbuddy.core.meta.Action.MODIFY;
import static com.netikras.studies.studentbuddy.core.meta.Action.SEARCH;
import static com.netikras.studies.studentbuddy.core.meta.Resource.LECTURER;

@RestController
public class LecturerProducer extends LecturerApiProducer {

    @Resource
    private LecturerService lecturerService;

    @Override
    @Authorizable(resource = LECTURER, action = GET)
    protected LecturerDto onRetrieveLecturerDto(String id) {
        Lecturer lecturer = lecturerService.getLecturer(id);
        LecturerDto dto = ModelMapper.transform(lecturer, new LecturerDto());

        return dto;
    }

    @Override
    @Authorizable(resource = LECTURER, action = MODIFY)
    protected LecturerDto onUpdateLecturerDto(LecturerDto dto) {
        Lecturer lecturer = ModelMapper.apply(new Lecturer(), dto);
        lecturer = lecturerService.updateLecturer(lecturer);
        dto = ModelMapper.transform(lecturer, new LecturerDto());

        return dto;
    }

    @Override
    @Authorizable(resource = LECTURER, action = GET)
    protected List<LecturerDto> onGetListByDiscipline(String disciplineId) {
        List<Lecturer> lecturers = lecturerService.findLecturersByDiscipline(disciplineId);
        List<LecturerDto> dtos = (List<LecturerDto>) ModelMapper.transformAll(lecturers, LecturerDto.class);

        return dtos;
    }

    @Override
    @Authorizable(resource = LECTURER, action = GET)
    protected LecturerDto onGetLecturerDtoByPersonId(String id) {
        Lecturer lecturers = lecturerService.getLecturerByPerson(id);
        LecturerDto dto = ModelMapper.transform(lecturers, new LecturerDto());

        return dto;
    }

    @Override
    @Authorizable(resource = LECTURER, action = SEARCH)
    protected List<LecturerDto> onSearchListAllLecturersByDegree(String degree) {
        List<Lecturer> lecturers = lecturerService.searchAllByDegree(degree);
        List<LecturerDto> lecturerDtos =
                (List<LecturerDto>) ModelMapper.transformAll(lecturers, LecturerDto.class, new MappingSettings().setDepthMax(3));

        return lecturerDtos;
    }

    @Override
    @Authorizable(resource = LECTURER, action = SEARCH)
    protected List<LecturerDto> onSearchListByFirstName(String fname) {
        List<Lecturer> lecturers = lecturerService.searchAllByFirstName(fname);
        List<LecturerDto> lecturerDtos =
                (List<LecturerDto>) ModelMapper.transformAll(lecturers, LecturerDto.class, new MappingSettings().setDepthMax(3));

        return lecturerDtos;
    }

    @Override
    @Authorizable(resource = LECTURER, action = SEARCH)
    protected List<LecturerDto> onSearchListAllLecturersByLastName(String lname) {
        List<Lecturer> lecturers = lecturerService.searchAllByLastName(lname);
        List<LecturerDto> lecturerDtos =
                (List<LecturerDto>) ModelMapper.transformAll(lecturers, LecturerDto.class, new MappingSettings().setDepthMax(3));

        return lecturerDtos;
    }
}
