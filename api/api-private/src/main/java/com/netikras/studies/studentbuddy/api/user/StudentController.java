package com.netikras.studies.studentbuddy.api.user;


import com.netikras.studies.studentbuddy.core.data.api.dto.school.LectureGuestDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.StudentDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.StudentsGroupDto;
import com.netikras.studies.studentbuddy.core.data.api.model.LectureGuest;
import com.netikras.studies.studentbuddy.core.data.api.model.Student;
import com.netikras.studies.studentbuddy.core.data.api.model.StudentsGroup;
import com.netikras.studies.studentbuddy.core.meta.annotations.Authorizable;
import com.netikras.studies.studentbuddy.core.service.StudentService;
import com.netikras.tools.common.model.mapper.ModelMapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

import static com.netikras.studies.studentbuddy.core.meta.Action.GET;
import static com.netikras.studies.studentbuddy.core.meta.Action.MODIFY;
import static com.netikras.studies.studentbuddy.core.meta.Resource.GUEST;
import static com.netikras.studies.studentbuddy.core.meta.Resource.STUDENT;
import static com.netikras.studies.studentbuddy.core.meta.Resource.STUDENT_GROUP;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Resource
    private StudentService studentService;

    @RequestMapping(
            value = "/id/{id}",
            method = RequestMethod.GET
    )
    @ResponseBody
    @Authorizable(resource = STUDENT, action = GET)
    public StudentDto getStudent(
            @PathVariable(name = "id") String id
    ) {
        Student student = studentService.getStudent(id);
        StudentDto studentDto = ModelMapper.transform(student, new StudentDto());

        return studentDto;
    }


    @RequestMapping(
            value = "/",
            method = RequestMethod.PUT
    )
    @ResponseBody
    @Authorizable(resource = STUDENT, action = MODIFY)
    public StudentDto updateStudent(
            @RequestBody StudentDto studentDto
    ) {
        Student student = ModelMapper.apply(new Student(), studentDto);
        student = studentService.updateStudent(student);
        studentDto = ModelMapper.transform(student, new StudentDto());

        return studentDto;
    }


    // Student groups


    @RequestMapping(
            value = "/group/id/{id}",
            method = RequestMethod.GET
    )
    @ResponseBody
    @Authorizable(resource = STUDENT_GROUP, action = GET)
    public StudentsGroupDto getGroup(
            @PathVariable(name = "id") String id
    ) {
        StudentsGroup group = studentService.getStudentsGroup(id);
        StudentsGroupDto dto = ModelMapper.transform(group, new StudentsGroupDto());
        return dto;
    }

    @RequestMapping(
            value = "/group",
            method = RequestMethod.GET
    )
    @ResponseBody
    @Authorizable(resource = STUDENT_GROUP, action = GET)
    public List<StudentsGroupDto> getAllGroups() {
        List<StudentsGroup> groups = studentService.getAllStudentGroups();
        List<StudentsGroupDto> dtos = (List<StudentsGroupDto>) ModelMapper.transformAll(groups, StudentsGroupDto.class);
        return dtos;
    }



    @RequestMapping(
            value = "/all/group/id/{id}",
            method = RequestMethod.GET
    )
    @ResponseBody
    @Authorizable(resource = STUDENT, action = GET)
    public List<StudentDto> getStudentsByGroup(
            @PathVariable(name = "id") String groupId
    ) {
        List<Student> students = studentService.getStudentsByGroupId(groupId);
        List<StudentDto> dtos = (List<StudentDto>) ModelMapper.transformAll(students, StudentDto.class);

        return dtos;
    }



    // lecture guests


    @RequestMapping(
            value = "/guest/id/{id}",
            method = RequestMethod.GET
    )
    @ResponseBody
    @Authorizable(resource = GUEST, action = GET)
    public LectureGuestDto getGuest(
            @PathVariable(name = "id") String id
    ) {
        LectureGuest guest = studentService.getLectureGuest(id);
        LectureGuestDto dto = ModelMapper.transform(guest, new LectureGuestDto());

        return dto;
    }


    @RequestMapping(
            value = "/guest",
            method = RequestMethod.POST
    )
    @Authorizable(resource = GUEST, action = MODIFY)
    public LectureGuestDto updateGuest(
            @RequestBody LectureGuestDto dto
    ) {
        LectureGuest guest = ModelMapper.apply(new LectureGuest(), dto);
        guest = studentService.updateLectureGuest(guest);
        dto = ModelMapper.transform(guest, new LectureGuestDto());

        return dto;
    }


}
