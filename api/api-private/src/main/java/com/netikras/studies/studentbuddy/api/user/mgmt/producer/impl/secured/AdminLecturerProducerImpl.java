package com.netikras.studies.studentbuddy.api.user.mgmt.producer.impl.secured;

import com.netikras.studies.studentbuddy.api.handlers.DtoMapper;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.LecturerDto;
import com.netikras.studies.studentbuddy.core.data.api.model.Discipline;
import com.netikras.studies.studentbuddy.core.data.api.model.Lecturer;
import com.netikras.studies.studentbuddy.core.data.meta.annotations.Authorizable;
import com.netikras.studies.studentbuddy.core.service.LecturerService;
import com.netikras.studies.studentbuddy.core.service.SchoolService;
import com.netikras.tools.common.model.mapper.MappingSettings;
import com.netikras.tools.common.model.mapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static com.netikras.studies.studentbuddy.core.data.meta.Action.CREATE;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.DELETE;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.MODIFY;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.PURGE;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.DISCIPLINE;

@Component
public class AdminLecturerProducerImpl {

    @Resource
    private ModelMapper modelMapper;
    @Resource
    private DtoMapper dtoMapper;

    @Resource
    private LecturerService lecturerService;

    @Resource
    private SchoolService schoolService;


    @Authorizable(resource = DISCIPLINE, action = PURGE)
    public void purgeLecturer(String id) {
        lecturerService.purgeLecturer(id);
    }

    @Authorizable(resource = DISCIPLINE, action = CREATE)
    @Transactional
    public LecturerDto createLecturer(LecturerDto dto) {
        Lecturer lecturer = modelMapper.apply(new Lecturer(), dto, new MappingSettings().setForceUpdate(true));
        if (lecturer != null) lecturer.setId(null);
        lecturer = lecturerService.createLecturer(lecturer);
        dto = (LecturerDto) dtoMapper.toDto(lecturer);
        return dto;
    }

    @Authorizable(resource = DISCIPLINE, action = DELETE)
    public void deleteLecturer(String id) {
        lecturerService.deleteLecturer(id);
    }

    @Authorizable(resource = DISCIPLINE, action = MODIFY)
    public void assignLecturerToDiscipline(String lecturerId, String disciplineId) {
        Lecturer lecturer = lecturerService.getLecturer(lecturerId);
        Discipline discipline = schoolService.getDiscipline(disciplineId);
        lecturerService.attachToDiscipline(lecturer, discipline);
    }

    @Authorizable(resource = DISCIPLINE, action = MODIFY)
    public void unassignLecturerFromDiscipline(String lecturerId, String disciplineId) {
        Lecturer lecturer = lecturerService.getLecturer(lecturerId);
        Discipline discipline = schoolService.getDiscipline(disciplineId);
        lecturerService.detatchFromDiscipline(lecturer, discipline);
    }
}
