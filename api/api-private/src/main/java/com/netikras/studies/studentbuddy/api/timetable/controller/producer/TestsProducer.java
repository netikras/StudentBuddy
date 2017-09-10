package com.netikras.studies.studentbuddy.api.timetable.controller.producer;

import com.netikras.studies.studentbuddy.api.timetable.controller.generated.TestsApiProducer;
import com.netikras.studies.studentbuddy.commons.exception.StudBudUncheckedException;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.DisciplineTestDto;
import com.netikras.studies.studentbuddy.core.data.api.model.DisciplineTest;
import com.netikras.studies.studentbuddy.core.data.api.model.Lecture;
import com.netikras.studies.studentbuddy.core.meta.annotations.Authorizable;
import com.netikras.studies.studentbuddy.core.service.LectureService;
import com.netikras.tools.common.model.mapper.MappingSettings;
import com.netikras.tools.common.model.mapper.ModelMapper;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import static com.netikras.studies.studentbuddy.core.meta.Action.CREATE;
import static com.netikras.studies.studentbuddy.core.meta.Action.DELETE;
import static com.netikras.studies.studentbuddy.core.meta.Action.GET;
import static com.netikras.studies.studentbuddy.core.meta.Action.MODIFY;
import static com.netikras.studies.studentbuddy.core.meta.Action.SEARCH;
import static com.netikras.studies.studentbuddy.core.meta.Resource.TEST;
import static com.netikras.tools.common.remote.http.HttpStatus.NOT_FOUND;

@RestController
public class TestsProducer extends TestsApiProducer {

    @Resource
    private LectureService lectureService;



    @Override
    @Authorizable(resource = TEST, action = CREATE)
    protected DisciplineTestDto onCreateDisciplineTestDto(DisciplineTestDto disciplineTestDto) {
        DisciplineTest test = ModelMapper.apply(new DisciplineTest(), disciplineTestDto, new MappingSettings().setForceUpdate(true));
        if (test != null) test.setId(null);
        test = lectureService.createTest(test);
        disciplineTestDto = ModelMapper.transform(test, new DisciplineTestDto());

        return disciplineTestDto;
    }

    @Override
    @Authorizable(resource = TEST, action = GET)
    protected DisciplineTestDto onRetrieveDisciplineTestDto(String id) {
        DisciplineTest test = lectureService.getTest(id);
        DisciplineTestDto disciplineTestDto = ModelMapper.transform(test, new DisciplineTestDto());

        return disciplineTestDto;
    }

    @Override
    @Authorizable(resource = TEST, action = MODIFY)
    protected DisciplineTestDto onUpdateDisciplineTestDto(DisciplineTestDto item) {
        DisciplineTest test = ModelMapper.apply(new DisciplineTest(), item);
        test = lectureService.updateTest(test);
        DisciplineTestDto disciplineTestDto = ModelMapper.transform(test, new DisciplineTestDto());

        return disciplineTestDto;
    }

    @Override
    @Authorizable(resource = TEST, action = DELETE)
    protected void onDeleteDisciplineTestDto(String id) {
        lectureService.deleteTest(id);
    }

    @Override
    @Authorizable(resource = TEST, action = CREATE)
    protected DisciplineTestDto onCreateDisciplineTestDtoNew(String dueLectureId, String description) {
        Lecture lecture = lectureService.getLecture(dueLectureId);

        if (lecture == null) {
            throw new StudBudUncheckedException()
                    .setMessage1("Cannot create test for lecture")
                    .setMessage2("Lecture with given ID does not exist")
                    .setProbableCause("ID: " + dueLectureId)
                    .setStatusCode(NOT_FOUND)
                    ;
        }

        DisciplineTest test = new DisciplineTest();
        test.setDiscipline(lecture.getDiscipline());
        test.setLecture(lecture);
        test.setStartsOn(lecture.getStartsOn());
        test.setDescription(description);

        test = lectureService.createTest(test);
        DisciplineTestDto dto = ModelMapper.transform(test, new DisciplineTestDto());

        return dto;
    }




    @Override
    @Authorizable(resource = TEST, action = GET)
    protected List<DisciplineTestDto> onGetDisciplineTestDtoAllForDisciplineInTimeframe(String disciplineId, long after, long before) {
        Date afterDate = new Date(after);
        Date beforeDate = new Date(before);
        List<DisciplineTest> tests = lectureService.getTestsForDiscipline(disciplineId, afterDate, beforeDate);
        List<DisciplineTestDto> dtos = (List<DisciplineTestDto>) ModelMapper.transformAll(tests, DisciplineTestDto.class);

        return dtos;
    }

    @Override
    @Authorizable(resource = TEST, action = GET)
    protected List<DisciplineTestDto> onGetDisciplineTestDtoAllForDiscipline(String disciplineId) {
        List<DisciplineTest> tests = lectureService.getTestsForDiscipline(disciplineId);
        List<DisciplineTestDto> dtos = (List<DisciplineTestDto>) ModelMapper.transformAll(tests, DisciplineTestDto.class);

        return dtos;
    }

    @Override
    @Authorizable(resource = TEST, action = GET)
    protected List<DisciplineTestDto> onGetDisciplineTestDtoAllForGroupInTimeframe(String disciplineId, String groupId, long after, long before) {
        Date afterDate = new Date(after);
        Date beforeDate = new Date(before);
        List<DisciplineTest> tests = lectureService.getTestsForDisciplineAndGroup(disciplineId, groupId, afterDate, beforeDate);
        List<DisciplineTestDto> dtos = (List<DisciplineTestDto>) ModelMapper.transformAll(tests, DisciplineTestDto.class);

        return dtos;
    }

    @Override
    @Authorizable(resource = TEST, action = GET)
    protected List<DisciplineTestDto> onGetDisciplineTestDtoAllForGroup(String disciplineId, String groupId) {
        List<DisciplineTest> tests = lectureService.getTestsForDisciplineAndGroup(disciplineId, groupId);
        List<DisciplineTestDto> dtos = (List<DisciplineTestDto>) ModelMapper.transformAll(tests, DisciplineTestDto.class);

        return dtos;
    }

    @Override
    @Authorizable(resource = TEST, action = SEARCH)
    protected List<DisciplineTestDto> onSearchDisciplineTestDtoAllByDescription(String descr) {
        List<DisciplineTest> tests = lectureService.searchAllTestsByDescription(descr);
        List<DisciplineTestDto> testDtos =
                (List<DisciplineTestDto>) ModelMapper.transformAll(tests, DisciplineTestDto.class, new MappingSettings().setDepthMax(3));

        return testDtos;
    }
}
