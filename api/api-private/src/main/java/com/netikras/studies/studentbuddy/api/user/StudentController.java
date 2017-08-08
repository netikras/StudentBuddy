package com.netikras.studies.studentbuddy.api.user;


import com.netikras.studies.studentbuddy.core.data.api.dto.school.StudentDto;
import com.netikras.studies.studentbuddy.core.data.api.model.Student;
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

import static com.netikras.studies.studentbuddy.core.meta.Action.CREATE;
import static com.netikras.studies.studentbuddy.core.meta.Action.DELETE;
import static com.netikras.studies.studentbuddy.core.meta.Action.GET;
import static com.netikras.studies.studentbuddy.core.meta.Action.MODIFY;
import static com.netikras.studies.studentbuddy.core.meta.Resource.STUDENT;

@RestController
@RequestMapping("/mgmt/student")
public class StudentController {

    @Resource
    private StudentService studentService;


    @RequestMapping(
            value = "/",
            method = RequestMethod.POST
    )
    @ResponseBody
    @Authorizable(resource = STUDENT, action = CREATE)
    public StudentDto createStudent(
            @RequestBody StudentDto studentDto
    ) {
        Student student = ModelMapper.apply(new Student(), studentDto);
        student = studentService.createStudent(student);
        studentDto = ModelMapper.transform(student, new StudentDto());

        return studentDto;
    }


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


    @RequestMapping(
            value = "/id/{id}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    @Authorizable(resource = STUDENT, action = DELETE)
    public void deleteStudent(
            @PathVariable(name = "id") String id
    ) {
        studentService.deleteStudent(id);
    }

}
