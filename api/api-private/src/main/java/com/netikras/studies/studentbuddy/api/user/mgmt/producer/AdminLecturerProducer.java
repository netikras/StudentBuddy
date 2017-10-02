package com.netikras.studies.studentbuddy.api.user.mgmt.producer;

import com.netikras.studies.studentbuddy.api.user.mgmt.generated.AdminLecturerApiProducer;
import com.netikras.studies.studentbuddy.api.user.mgmt.producer.impl.secured.AdminLecturerProducerImpl;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.LecturerDto;
import com.netikras.studies.studentbuddy.core.data.api.model.Discipline;
import com.netikras.studies.studentbuddy.core.data.api.model.Lecturer;
import com.netikras.studies.studentbuddy.core.data.meta.annotations.Authorizable;
import com.netikras.studies.studentbuddy.core.service.LecturerService;
import com.netikras.studies.studentbuddy.core.service.SchoolService;
import com.netikras.tools.common.model.mapper.MappingSettings;
import com.netikras.tools.common.model.mapper.ModelMapper;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static com.netikras.studies.studentbuddy.core.data.meta.Action.CREATE;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.DELETE;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.MODIFY;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.PURGE;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.DISCIPLINE;

@RestController
public class AdminLecturerProducer extends AdminLecturerApiProducer {

    @Resource
    private AdminLecturerProducerImpl impl;


    @Override
    protected void onPurgeLecturerDto(String id) {
        impl.purgeLecturer(id);
    }

    @Override
    protected LecturerDto onCreateLecturerDto(LecturerDto dto) {
        return impl.createLecturer(dto);
    }

    @Override
    protected void onDeleteLecturerDto(String id) {
        impl.deleteLecturer(id);
    }

    @Override
    protected void onAssignLecturerDtoToDiscipline(String lecturerId, String disciplineId) {
        impl.assignLecturerToDiscipline(lecturerId, disciplineId);
    }

    @Override
    protected void onUnassignLecturerDtoFromDiscipline(String lecturerId, String disciplineId) {
        impl.unassignLecturerFromDiscipline(lecturerId, disciplineId);
    }
}
