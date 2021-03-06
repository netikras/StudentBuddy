package com.netikras.studies.studentbuddy.api.timetable.controller.producer.impl.secured;

import com.netikras.studies.studentbuddy.api.handlers.DtoMapper;
import com.netikras.studies.studentbuddy.commons.exception.StudBudUncheckedException;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.DisciplineTestDto;
import com.netikras.studies.studentbuddy.core.data.api.model.DisciplineTest;
import com.netikras.studies.studentbuddy.core.data.api.model.Lecture;
import com.netikras.studies.studentbuddy.core.data.meta.annotations.Authorizable;
import com.netikras.studies.studentbuddy.core.service.LectureService;
import com.netikras.studies.studentbuddy.core.validator.EntityProvider;
import com.netikras.tools.common.model.mapper.MappingSettings;
import com.netikras.tools.common.model.mapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import static com.netikras.studies.studentbuddy.core.data.meta.Action.CREATE;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.DELETE;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.GET;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.GET_ALL;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.MODIFY;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.PURGE;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.SEARCH;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.TEST;
import static com.netikras.tools.common.remote.http.HttpStatus.NOT_FOUND;

@Component
public class TestsProducerImpl {

    @Resource
    private ModelMapper modelMapper;

    @Resource
    private LectureService lectureService;
    @Resource
    private EntityProvider entityProvider;
    @Resource
    private DtoMapper dtoMapper;


    @Authorizable(resource = TEST, action = PURGE)
    public void purgeDisciplineTest(String id) {
        lectureService.purgeLectureTest(id);
    }

    @Authorizable(resource = TEST, action = CREATE)
    @Transactional
    public DisciplineTestDto createDisciplineTest(DisciplineTestDto disciplineTestDto) {
        DisciplineTest test = modelMapper.apply(new DisciplineTest(), disciplineTestDto, new MappingSettings().setForceUpdate(true));
        if (test != null) test.setId(null);
        test = lectureService.createTest(test);
        DisciplineTestDto dto = (DisciplineTestDto) dtoMapper.toDto(test);
        return dto;
    }

    @Authorizable(resource = TEST, action = GET)
    @Transactional
    public DisciplineTestDto getDisciplineTest(String id) {
        DisciplineTest test = lectureService.getTest(id);
        DisciplineTestDto dto = (DisciplineTestDto) dtoMapper.toDto(test);
        return dto;
    }

    @Authorizable(resource = TEST, action = MODIFY)
    @Transactional
    public DisciplineTestDto updateDisciplineTest(DisciplineTestDto item) {
        DisciplineTest test = modelMapper.apply(entityProvider.fetch(item), item);
        test = lectureService.updateTest(test);
        DisciplineTestDto dto = (DisciplineTestDto) dtoMapper.toDto(test);
        return dto;
    }

    @Authorizable(resource = TEST, action = DELETE)
    public void deleteDisciplineTest(String id) {
        lectureService.deleteTest(id);
    }

    @Authorizable(resource = TEST, action = CREATE)
    @Transactional
    public DisciplineTestDto createDisciplineTestNew(String dueLectureId, String description) {
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
        DisciplineTestDto dto = (DisciplineTestDto) dtoMapper.toDto(test);
        return dto;
    }


    @Authorizable(resource = TEST, action = GET)
    @Transactional
    public List<DisciplineTestDto> getAllDisciplineTestsForDisciplineInTimeframe(String disciplineId, long after, long before) {
        Date afterDate = new Date(after);
        Date beforeDate = new Date(before);
        List<DisciplineTest> tests = lectureService.getTestsForDiscipline(disciplineId, afterDate, beforeDate);
        List<DisciplineTestDto> dtos = (List<DisciplineTestDto>) dtoMapper.toDtos(tests);
        return dtos;
    }

    @Authorizable(resource = TEST, action = GET)
    @Transactional
    public List<DisciplineTestDto> getAllDisciplineTestsForDiscipline(String disciplineId) {
        List<DisciplineTest> tests = lectureService.getTestsForDiscipline(disciplineId);
        List<DisciplineTestDto> dtos = (List<DisciplineTestDto>) dtoMapper.toDtos(tests);
        return dtos;
    }

    @Authorizable(resource = TEST, action = GET)
    @Transactional
    public List<DisciplineTestDto> getAllDisciplineTestsForGroupInTimeframe(String disciplineId, String groupId, long after, long before) {
        Date afterDate = new Date(after);
        Date beforeDate = new Date(before);
        List<DisciplineTest> tests = lectureService.getTestsForDisciplineAndGroup(disciplineId, groupId, afterDate, beforeDate);
        List<DisciplineTestDto> dtos = (List<DisciplineTestDto>) dtoMapper.toDtos(tests);
        return dtos;
    }

    @Authorizable(resource = TEST, action = GET)
    @Transactional
    public List<DisciplineTestDto> getAllDisciplineTestsForGroup(String disciplineId, String groupId) {
        List<DisciplineTest> tests = lectureService.getTestsForDisciplineAndGroup(disciplineId, groupId);
        List<DisciplineTestDto> dtos = (List<DisciplineTestDto>) dtoMapper.toDtos(tests);
        return dtos;
    }

    @Authorizable(resource = TEST, action = SEARCH)
    @Transactional
    public List<DisciplineTestDto> searchAllDisciplineTestsByDescription(String descr) {
        List<DisciplineTest> tests = lectureService.searchAllTestsByDescription(descr);
        List<DisciplineTestDto> dtos = (List<DisciplineTestDto>) dtoMapper.toDtos(tests);
        return dtos;
    }

    @Authorizable(resource = TEST, action = GET_ALL)
    @Transactional
    public List<DisciplineTestDto> getAllDisciplineTestsForCourse(String id) {
        List<DisciplineTest> tests = lectureService.getTestsForCourse(id);
        List<DisciplineTestDto> dtos = (List<DisciplineTestDto>) dtoMapper.toDtos(tests);
        return dtos;
    }

    @Authorizable(resource = TEST, action = GET_ALL)
    @Transactional
    public List<DisciplineTestDto> getAllDisciplineTestsForCourseStartingBetween(String id, long afterTimestamp, long beforeTimestamp) {
        List<DisciplineTest> tests = lectureService.getTestsForCourseStartingBetween(id, new Date(afterTimestamp), new Date(beforeTimestamp));
        List<DisciplineTestDto> dtos = (List<DisciplineTestDto>) dtoMapper.toDtos(tests);
        return dtos;
    }
}
