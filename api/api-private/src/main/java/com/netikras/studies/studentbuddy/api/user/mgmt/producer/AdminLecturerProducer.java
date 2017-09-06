package com.netikras.studies.studentbuddy.api.user.mgmt.producer;

import com.netikras.studies.studentbuddy.api.user.mgmt.generated.AdminLecturerApiProducer;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.LecturerDto;
import com.netikras.studies.studentbuddy.core.data.api.model.Discipline;
import com.netikras.studies.studentbuddy.core.data.api.model.Lecturer;
import com.netikras.studies.studentbuddy.core.meta.annotations.Authorizable;
import com.netikras.studies.studentbuddy.core.service.LecturerService;
import com.netikras.studies.studentbuddy.core.service.SchoolService;
import com.netikras.tools.common.model.mapper.MappingSettings;
import com.netikras.tools.common.model.mapper.ModelMapper;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static com.netikras.studies.studentbuddy.core.meta.Action.CREATE;
import static com.netikras.studies.studentbuddy.core.meta.Action.DELETE;
import static com.netikras.studies.studentbuddy.core.meta.Action.MODIFY;
import static com.netikras.studies.studentbuddy.core.meta.Resource.DISCIPLINE;

@RestController
public class AdminLecturerProducer extends AdminLecturerApiProducer {

    @Resource
    private LecturerService lecturerService;

    @Resource
    private SchoolService schoolService;


    @Override
    @Authorizable(resource = DISCIPLINE, action = CREATE)
    protected LecturerDto onCreateLecturerDto(LecturerDto dto) {
        Lecturer lecturer = ModelMapper.apply(new Lecturer(), dto, new MappingSettings().setForceUpdate(true));
        if (lecturer != null) lecturer.setId(null);
        lecturer = lecturerService.createLecturer(lecturer);
        dto = ModelMapper.transform(lecturer, new LecturerDto());

        return dto;
    }

    @Override
    @Authorizable(resource = DISCIPLINE, action = DELETE)
    protected void onDeleteLecturerDto(String id) {
        lecturerService.deleteLecturer(id);
    }

    @Override
    @Authorizable(resource = DISCIPLINE, action = MODIFY)
    protected void onAssignLecturerDtoToDiscipline(String lecturerId, String disciplineId) {
        Lecturer lecturer = lecturerService.getLecturer(lecturerId);
        Discipline discipline = schoolService.getDiscipline(disciplineId);
        lecturerService.attachToDiscipline(lecturer, discipline);
    }

    @Override
    @Authorizable(resource = DISCIPLINE, action = MODIFY)
    protected void onUnassignLecturerDtoFromDiscipline(String lecturerId, String disciplineId) {
        Lecturer lecturer = lecturerService.getLecturer(lecturerId);
        Discipline discipline = schoolService.getDiscipline(disciplineId);
        lecturerService.detatchFromDiscipline(lecturer, discipline);
    }
}
