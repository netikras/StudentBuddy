package com.netikras.studies.studentbuddy.api.timetable.controller;

import com.netikras.studies.studentbuddy.commons.exception.StudBudUncheckedException;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.DisciplineTestDto;
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

import static com.netikras.studies.studentbuddy.core.meta.Action.CREATE;
import static com.netikras.studies.studentbuddy.core.meta.Action.DELETE;
import static com.netikras.studies.studentbuddy.core.meta.Action.GET;
import static com.netikras.studies.studentbuddy.core.meta.Action.MODIFY;
import static com.netikras.studies.studentbuddy.core.meta.Resource.TEST;
import static com.netikras.tools.common.remote.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(value = "/tests")
public class TestsController {


    @Resource
    private LectureService lectureService;

    @RequestMapping(
            value = "/",
            method = RequestMethod.POST
    )
    @ResponseBody
    @Authorizable(resource = TEST, action = CREATE)
    public DisciplineTestDto createTest(
            @RequestBody DisciplineTestDto disciplineTestDto
    ) {
        DisciplineTest test = ModelMapper.apply(new DisciplineTest(), disciplineTestDto);
        test = lectureService.createTest(test);
        disciplineTestDto = ModelMapper.transform(test, new DisciplineTestDto());

        return disciplineTestDto;
    }


    @RequestMapping(
            value = "/due/lecture/id/{id}",
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
                    .setErrorCode(NOT_FOUND.getCode())
                    ;
        }

        DisciplineTest test = new DisciplineTest();
        test.setDiscipline(lecture.getDiscipline());
        test.setLecture(lecture);
        test.setStartsOn(lecture.getStartsOn());

        test = lectureService.createTest(test);
        DisciplineTestDto dto = ModelMapper.transform(test, new DisciplineTestDto());

        return dto;
    }

    @RequestMapping(
            value = "/id/{id}",
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
            value = "/",
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
            value = "/id/{id}",
            method = RequestMethod.DELETE
    )
    @ResponseStatus(code = OK, reason = "Test has been deleted")
    @Authorizable(resource = TEST, action = DELETE)
    public void deleteTest(
            @PathVariable(name = "id") String id
    ) {
        lectureService.deleteTest(id);
    }


    // TODO implement findBy.... methods


}
