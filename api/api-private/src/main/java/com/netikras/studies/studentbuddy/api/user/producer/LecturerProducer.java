package com.netikras.studies.studentbuddy.api.user.producer;

import com.netikras.studies.studentbuddy.api.user.generated.LecturerApiProducer;
import com.netikras.studies.studentbuddy.api.user.producer.impl.secured.LecturerProducerImpl;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.LecturerDto;
import com.netikras.studies.studentbuddy.core.data.api.model.Lecturer;
import com.netikras.studies.studentbuddy.core.data.meta.annotations.Authorizable;
import com.netikras.studies.studentbuddy.core.service.LecturerService;
import com.netikras.tools.common.model.mapper.MappingSettings;
import com.netikras.tools.common.model.mapper.ModelMapper;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

import static com.netikras.studies.studentbuddy.core.data.meta.Action.GET;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.MODIFY;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.SEARCH;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.LECTURER;

@RestController
public class LecturerProducer extends LecturerApiProducer {

    @Resource
    private LecturerProducerImpl impl;

    @Override
    protected LecturerDto onRetrieveLecturerDto(String id) {
        return impl.getLecturer(id);
    }

    @Override
    protected LecturerDto onUpdateLecturerDto(LecturerDto dto) {
        return impl.updateLecturer(dto);
    }


    @Override
    protected List<LecturerDto> onGetLecturerDtoByDiscipline(String id) {
        return impl.getAllLecturersByDiscipline(id);
    }


    @Override
    protected List<LecturerDto> onGetLecturerDtoAllByPersonId(String id) {
        return impl.getAllLecturersByPersonId(id);
    }

    @Override
    protected List<LecturerDto> onSearchLecturerDtoAllLecturersByDegree(String degree) {
        return impl.searchLecturersByDegree(degree);
    }

    @Override
    protected List<LecturerDto> onSearchLecturerDtoByFirstName(String fname) {
        return impl.searchLecturersByFirstName(fname);
    }

    @Override
    protected List<LecturerDto> onSearchLecturerDtoAllLecturersByLastName(String lname) {
        return impl.searchLecturersByLastName(lname);
    }
}
