package com.netikras.studies.studentbuddy.api.timetable.controller;

import com.netikras.studies.studentbuddy.core.data.api.dto.LectureDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by netikras on 17.6.21.
 */
@RestController
@RequestMapping(value = "/schedule")
public class TimetableController {


    @RequestMapping(
            value = "/lectures/group/{groupId}",
            method = RequestMethod.GET
    )
    @ResponseBody
    public List<LectureDto> getLecturesByGroup(
            @PathVariable(name = "groupId") String groupId
    ) {
        return null;
    }

    @RequestMapping(
            value = "/lectures/student/{studentId}",
            method = RequestMethod.GET
    )
    @ResponseBody
    public List<LectureDto> getLecturesByStudent(
            @PathVariable(name = "studentId") String studentId
    ) {
        return null;
    }


    /**
     * @param groupId   Students' group ID, e.g. PIN13
     * @param timeUnits Time units (ISO): M(onths), d(ays), H(ours), m(inutes), s(econds)
     * @param value     Time value
     */
    @RequestMapping(
            value = "/lectures/group/{groupId}/in/t/{timeUnits}/{value}",
            method = RequestMethod.GET
    )
    @ResponseBody
    public List<LectureDto> getLecturesByGroupStartingIn(
            @PathVariable(name = "groupId") String groupId,
            @PathVariable(name = "timeUnits") String timeUnits,
            @PathVariable(name = "value") Double value
    ) {
        return null;
    }

    @RequestMapping(
            value = "/lectures/student/{studentId}/in/t/{timeUnits}/{value}",
            method = RequestMethod.GET
    )
    @ResponseBody
    public List<LectureDto> getLecturesByStudentStartingIn(
            @PathVariable(name = "studentId") String studentId,
            @PathVariable(name = "timeUnits") String timeUnits,
            @PathVariable(name = "value") Double value
    ) {
        return null;
    }


}
