package com.netikras.studies.studentbuddy.api.timetable.controller.producer;

import com.netikras.studies.studentbuddy.api.timetable.controller.generated.TestsApiProducer;
import com.netikras.studies.studentbuddy.api.timetable.controller.producer.impl.secured.TestsProducerImpl;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.DisciplineTestDto;
import com.netikras.studies.studentbuddy.core.data.meta.annotations.Authorizable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

import static com.netikras.studies.studentbuddy.core.data.meta.Action.CREATE;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.TEST;

@RestController
public class TestsProducer extends TestsApiProducer {

    @Resource
    private TestsProducerImpl impl;


    @Override
    protected void onPurgeDisciplineTestDto(String id) {
        impl.purgeDisciplineTest(id);
    }

    @Override
    @Authorizable(resource = TEST, action = CREATE)
    protected DisciplineTestDto onCreateDisciplineTestDto(DisciplineTestDto disciplineTestDto) {
        return impl.createDisciplineTest(disciplineTestDto);
    }

    @Override
    protected DisciplineTestDto onRetrieveDisciplineTestDto(String id) {
        return impl.getDisciplineTest(id);
    }

    @Override
    protected DisciplineTestDto onUpdateDisciplineTestDto(DisciplineTestDto item) {
        return impl.updateDisciplineTest(item);
    }

    @Override
    protected void onDeleteDisciplineTestDto(String id) {
        impl.deleteDisciplineTest(id);
    }

    @Override
    protected DisciplineTestDto onCreateDisciplineTestDtoNew(String dueLectureId, String description) {
        return impl.createDisciplineTestNew(dueLectureId, description);
    }


    @Override
    protected List<DisciplineTestDto> onGetDisciplineTestDtoAllForCourse(String id) {
        return impl.getAllDisciplineTestsForCourse(id);
    }

    @Override
    protected List<DisciplineTestDto> onGetDisciplineTestDtoAllForCourseInTimeframe(String id, long afterTimestamp, long beforeTimestamp) {
        return impl.getAllDisciplineTestsForCourseStartingBetween(id, afterTimestamp, beforeTimestamp);
    }

    @Override
    protected List<DisciplineTestDto> onGetDisciplineTestDtoAllForDisciplineInTimeframe(String disciplineId, long after, long before) {
        return impl.getAllDisciplineTestsForDisciplineInTimeframe(disciplineId, after, before);
    }

    @Override
    protected List<DisciplineTestDto> onGetDisciplineTestDtoAllForDiscipline(String disciplineId) {
        return impl.getAllDisciplineTestsForDiscipline(disciplineId);
    }

    @Override
    protected List<DisciplineTestDto> onGetDisciplineTestDtoAllForGroupInTimeframe(String disciplineId, String groupId, long after, long before) {
        return impl.getAllDisciplineTestsForGroupInTimeframe(disciplineId, groupId, after, before);
    }

    @Override
    protected List<DisciplineTestDto> onGetDisciplineTestDtoAllForGroup(String disciplineId, String groupId) {
        return impl.getAllDisciplineTestsForGroup(disciplineId, groupId);
    }

    @Override
    protected List<DisciplineTestDto> onSearchDisciplineTestDtoAllByDescription(String descr) {
        return impl.searchAllDisciplineTestsByDescription(descr);
    }
}
