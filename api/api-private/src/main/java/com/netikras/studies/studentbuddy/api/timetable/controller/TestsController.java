package com.netikras.studies.studentbuddy.api.timetable.controller;

import com.netikras.studies.studentbuddy.commons.exception.StudBudUncheckedException;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.DisciplineTestDto;
import com.netikras.studies.studentbuddy.core.data.api.model.Discipline;
import com.netikras.studies.studentbuddy.core.data.api.model.DisciplineTest;
import com.netikras.studies.studentbuddy.core.data.api.model.Lecture;
import com.netikras.studies.studentbuddy.core.meta.annotations.Authorizable;
import com.netikras.studies.studentbuddy.core.service.LectureService;
import com.netikras.tools.common.model.mapper.ModelMapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import static com.netikras.studies.studentbuddy.api.constants.TestsConstants.BASE_URL;
import static com.netikras.studies.studentbuddy.api.constants.TestsConstants.TESTS_URL_CREATE;
import static com.netikras.studies.studentbuddy.api.constants.TestsConstants.TESTS_URL_CREATE_BY_LECTURE_ID;
import static com.netikras.studies.studentbuddy.api.constants.TestsConstants.TESTS_URL_DELETE_BY_ID;
import static com.netikras.studies.studentbuddy.api.constants.TestsConstants.TESTS_URL_GET_ALL_FOR_DISCIPLINE_ID;
import static com.netikras.studies.studentbuddy.api.constants.TestsConstants.TESTS_URL_GET_ALL_FOR_DISCIPLINE_ID_AND_GROUP_ID;
import static com.netikras.studies.studentbuddy.api.constants.TestsConstants.TESTS_URL_GET_ALL_FOR_DISCIPLINE_ID_AND_GROUP_ID_BETWEEN;
import static com.netikras.studies.studentbuddy.api.constants.TestsConstants.TESTS_URL_GET_ALL_FOR_DISCIPLINE_ID_BETWEEN;
import static com.netikras.studies.studentbuddy.api.constants.TestsConstants.TESTS_URL_GET_BY_ID;
import static com.netikras.studies.studentbuddy.api.constants.TestsConstants.TESTS_URL_UPDATE;
import static com.netikras.studies.studentbuddy.core.meta.Action.CREATE;
import static com.netikras.studies.studentbuddy.core.meta.Action.DELETE;
import static com.netikras.studies.studentbuddy.core.meta.Action.GET;
import static com.netikras.studies.studentbuddy.core.meta.Action.MODIFY;
import static com.netikras.studies.studentbuddy.core.meta.Resource.TEST;
import static com.netikras.tools.common.remote.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(value = BASE_URL)
public class TestsController {


    @Resource
    private LectureService lectureService;

    @RequestMapping(
            value = TESTS_URL_CREATE,
            method = RequestMethod.POST
    )
    @ResponseBody
    @Authorizable(resource = TEST, action = CREATE)
    public DisciplineTestDto createTest(
            @RequestBody DisciplineTestDto disciplineTestDto
    ) {
        DisciplineTest test = ModelMapper.apply(new DisciplineTest(), disciplineTestDto);
        test.setDiscipline(ModelMapper.apply(new Discipline(), disciplineTestDto.getDiscipline()));
        test.setLecture(ModelMapper.apply(new Lecture(), disciplineTestDto.getLecture()));
        test = lectureService.createTest(test);
        disciplineTestDto = ModelMapper.transform(test, new DisciplineTestDto());

        return disciplineTestDto;
    }


    @RequestMapping(
            value = TESTS_URL_CREATE_BY_LECTURE_ID,
            method = RequestMethod.POST
    )
    @ResponseBody
    @Authorizable(resource = TEST, action = CREATE)
    public DisciplineTestDto createTest(
        @PathVariable(name = "id") String dueLectureId,
        @RequestBody String description
    ) {
        Lecture lecture = lectureService.findLecture(dueLectureId);

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

    @RequestMapping(
            value = TESTS_URL_GET_BY_ID,
            method = RequestMethod.GET
    )
    @ResponseBody
    @Authorizable(resource = TEST, action = GET)
    public DisciplineTestDto getTest(
            @PathVariable(name = "id") String id
    ) {
        DisciplineTest test = lectureService.getTest(id);
        DisciplineTestDto disciplineTestDto = ModelMapper.transform(test, new DisciplineTestDto());

        return disciplineTestDto;
    }


    @RequestMapping(
            value = TESTS_URL_UPDATE,
            method = RequestMethod.PUT
    )
    @ResponseBody
    @Authorizable(resource = TEST, action = MODIFY)
    public DisciplineTestDto updateTest(
            @RequestBody DisciplineTestDto dto
    ) {
        DisciplineTest test = ModelMapper.apply(new DisciplineTest(), dto);
        test = lectureService.updateTest(test);
        DisciplineTestDto disciplineTestDto = ModelMapper.transform(test, new DisciplineTestDto());

        return disciplineTestDto;
    }



    @RequestMapping(
            value = TESTS_URL_DELETE_BY_ID,
            method = RequestMethod.DELETE
    )
    @ResponseStatus(code = OK, reason = "Test has been deleted")
    @Authorizable(resource = TEST, action = DELETE)
    public void deleteTest(
            @PathVariable(name = "id") String id
    ) {
        lectureService.deleteTest(id);
    }


    // findBy


    @RequestMapping(
            value = TESTS_URL_GET_ALL_FOR_DISCIPLINE_ID_BETWEEN,
            method = RequestMethod.GET
    )
    @ResponseBody
    @Authorizable(resource = TEST, action = GET)
    public List<DisciplineTestDto> getTestsForDiscipline(
            @PathVariable(name = "id") String disciplineId,
            @PathVariable(name = "after") long afterTimestamp,
            @PathVariable(name = "before") long beforeTimestamp
    ) {
        Date after = new Date(afterTimestamp);
        Date before = new Date(beforeTimestamp);
        List<DisciplineTest> tests = lectureService.getTestsForDiscipline(disciplineId, after, before);
        List<DisciplineTestDto> dtos = (List<DisciplineTestDto>) ModelMapper.transformAll(tests, DisciplineTestDto.class);

        return dtos;
    }


    @RequestMapping(
            value = TESTS_URL_GET_ALL_FOR_DISCIPLINE_ID,
            method = RequestMethod.GET
    )
    @ResponseBody
    @Authorizable(resource = TEST, action = GET)
    public List<DisciplineTestDto> getTestsForDiscipline(
            @PathVariable(name = "id") String disciplineId
    ) {
        List<DisciplineTest> tests = lectureService.getTestsForDiscipline(disciplineId);
        List<DisciplineTestDto> dtos = (List<DisciplineTestDto>) ModelMapper.transformAll(tests, DisciplineTestDto.class);

        return dtos;
    }

    @RequestMapping(
            value = TESTS_URL_GET_ALL_FOR_DISCIPLINE_ID_AND_GROUP_ID_BETWEEN,
            method = RequestMethod.GET
    )
    @ResponseBody
    @Authorizable(resource = TEST, action = GET)
    public List<DisciplineTestDto> getTestsForGroup(
            @PathVariable(name = "disciplineId") String disciplineId,
            @PathVariable(name = "groupId") String groupId,
            @PathVariable(name = "after") long afterTimestamp,
            @PathVariable(name = "before") long beforeTimestamp
    ) {
        Date after = new Date(afterTimestamp);
        Date before = new Date(beforeTimestamp);
        List<DisciplineTest> tests = lectureService.getTestsForDisciplineAndGroup(disciplineId, groupId, after, before);
        List<DisciplineTestDto> dtos = (List<DisciplineTestDto>) ModelMapper.transformAll(tests, DisciplineTestDto.class);

        return dtos;
    }

    @RequestMapping(
            value = TESTS_URL_GET_ALL_FOR_DISCIPLINE_ID_AND_GROUP_ID,
            method = RequestMethod.GET
    )
    @ResponseBody
    @Authorizable(resource = TEST, action = GET)
    public List<DisciplineTestDto> getTestsForGroup(
            @PathVariable(name = "disciplineId") String disciplineId,
            @PathVariable(name = "groupId") String groupId
    ) {
        List<DisciplineTest> tests = lectureService.getTestsForDisciplineAndGroup(disciplineId, groupId);
        List<DisciplineTestDto> dtos = (List<DisciplineTestDto>) ModelMapper.transformAll(tests, DisciplineTestDto.class);

        return dtos;
    }


}
