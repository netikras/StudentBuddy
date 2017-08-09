package com.netikras.studies.studentbuddy.api.user.mgmt;

import com.netikras.studies.studentbuddy.core.data.api.dto.school.LectureGuestDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.StudentDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.StudentsGroupDto;
import com.netikras.studies.studentbuddy.core.data.api.model.Lecture;
import com.netikras.studies.studentbuddy.core.data.api.model.LectureGuest;
import com.netikras.studies.studentbuddy.core.data.api.model.Person;
import com.netikras.studies.studentbuddy.core.data.api.model.Student;
import com.netikras.studies.studentbuddy.core.data.api.model.StudentsGroup;
import com.netikras.studies.studentbuddy.core.meta.annotations.Authorizable;
import com.netikras.studies.studentbuddy.core.service.LectureService;
import com.netikras.studies.studentbuddy.core.service.PersonService;
import com.netikras.studies.studentbuddy.core.service.StudentService;
import com.netikras.tools.common.model.mapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

import static com.netikras.studies.studentbuddy.core.meta.Action.CREATE;
import static com.netikras.studies.studentbuddy.core.meta.Action.DELETE;
import static com.netikras.studies.studentbuddy.core.meta.Action.GET;
import static com.netikras.studies.studentbuddy.core.meta.Action.MODIFY;
import static com.netikras.studies.studentbuddy.core.meta.Resource.GUEST;
import static com.netikras.studies.studentbuddy.core.meta.Resource.STUDENT;
import static com.netikras.studies.studentbuddy.core.meta.Resource.STUDENT_GROUP;

@RestController
@RequestMapping(value = "/mgmt/student")
public class AdminStudentController {


    @Resource
    private StudentService studentService;

    @Resource
    private PersonService personService;

    @Resource
    private LectureService lectureService;


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
            method = RequestMethod.DELETE
    )
    @ResponseStatus(code = HttpStatus.OK, reason = "Student has been deleted")
    @Authorizable(resource = STUDENT, action = DELETE)
    public void deleteStudent(
            @PathVariable(name = "id") String id
    ) {
        studentService.deleteStudent(id);
    }



    @RequestMapping(
            value = "/group",
            method = RequestMethod.POST
    )
    @ResponseBody
    @Authorizable(resource = STUDENT_GROUP, action = GET)
    public StudentsGroupDto createGroup(
            @RequestBody StudentsGroupDto groupDto
    ) {
        StudentsGroup group = ModelMapper.apply(new StudentsGroup(), groupDto);
        group = studentService.createStudentsGroup(group);
        StudentsGroupDto dto = ModelMapper.transform(group, new StudentsGroupDto());
        return dto;
    }




    @RequestMapping(
            value = "/group/id/{id}",
            method = RequestMethod.DELETE
    )
    @ResponseStatus(code = HttpStatus.OK, reason = "Group has been deleted")
    @Authorizable(resource = STUDENT_GROUP, action = DELETE)
    public void deleteGroup(
            @PathVariable(name = "id") String id
    ) {
        studentService.deleteStudentsGroup(id);
    }




    @RequestMapping(
            value = "/group/add/all/{groupId}/{studentIds}",
            method = RequestMethod.PUT
    )
    @ResponseStatus(code = HttpStatus.OK, reason = "Students have been added the group")
    @Authorizable(resource = STUDENT_GROUP, action = MODIFY)
    public void addStudentsToGroup(
            @PathVariable(name = "groupId") String groupId,
            @PathVariable(name = "studentIds") List<String> studentIds
    ) {
        studentService.addStudentsToGroup(groupId, studentIds);
    }

    @RequestMapping(
            value = "/group/add/single/{groupId}/{studentId}",
            method = RequestMethod.PUT
    )
    @ResponseStatus(code = HttpStatus.OK, reason = "Student has been added the group")
    @Authorizable(resource = STUDENT_GROUP, action = MODIFY)
    @Transactional
    public void addStudentToGroup(
            @PathVariable(name = "groupId") String groupId,
            @PathVariable(name = "studentId") String studentId
    ) {
        StudentsGroup group = studentService.getStudentsGroup(groupId);
        Student student = studentService.getStudent(studentId);
        studentService.addStudentToGroup(group, student);
    }

    @RequestMapping(
            value = "/group/remove/single/{groupId}/{studentId}",
            method = RequestMethod.PUT
    )
    @ResponseStatus(code = HttpStatus.OK, reason = "Student has been removed from the group")
    @Authorizable(resource = STUDENT_GROUP, action = MODIFY)
    @Transactional
    public void removeStudentFromGroup(
            @PathVariable(name = "groupId") String groupId,
            @PathVariable(name = "studentId") String studentId
    ) {
        StudentsGroup group = studentService.getStudentsGroup(groupId);
        Student student = studentService.getStudent(studentId);
        studentService.removeStudentFromGroup(group, student);
    }

    @RequestMapping(
            value = "/group/remove/all/{groupId}/{studentIds}",
            method = RequestMethod.PUT
    )
    @ResponseStatus(code = HttpStatus.OK, reason = "Student has been removed from the group")
    @Authorizable(resource = STUDENT_GROUP, action = MODIFY)
    public void removeStudentsFromGroup(
            @PathVariable(name = "groupId") String groupId,
            @PathVariable(name = "studentIds") List<String> studentIds
    ) {
        studentService.removeStudentsFromGroup(groupId, studentIds);
    }





    @RequestMapping(
            value = "/guest",
            method = RequestMethod.POST
    )
    @ResponseBody
    @Authorizable(resource = GUEST, action = CREATE)
    public LectureGuestDto createGuest(
            @RequestBody LectureGuestDto dto
    ) {
        LectureGuest guest = ModelMapper.apply(new LectureGuest(), dto);
        guest = studentService.createLectureGuest(guest);
        dto = ModelMapper.transform(guest, new LectureGuestDto());

        return dto;
    }

    @SuppressWarnings("Duplicates")
    @RequestMapping(
            value = "/guest/person/id/{personId}/lecture/id/{lectureId}",
            method = RequestMethod.POST
    )
    @ResponseBody
    @Authorizable(resource = GUEST, action = CREATE)
    @Transactional
    public LectureGuestDto createGuest(
            @PathVariable(name = "personId") String personId,
            @PathVariable(name = "lectureId") String lectureId
    ) {
        Person person = personService.findPerson(personId);
        Lecture lecture = lectureService.findLecture(lectureId);
        LectureGuest guest = studentService.createLectureGuest(person, lecture);
        LectureGuestDto dto = ModelMapper.transform(guest, new LectureGuestDto());

        return dto;
    }

    @SuppressWarnings("Duplicates")
    @RequestMapping(
            value = "/guest/person/identifier/{personId}/lecture/id/{lectureId}",
            method = RequestMethod.POST
    )
    @ResponseBody
    @Authorizable(resource = GUEST, action = CREATE)
    @Transactional
    public LectureGuestDto createGuestByPersonIdentifier(
            @PathVariable(name = "personId") String personId,
            @PathVariable(name = "lectureId") String lectureId
    ) {
        Person person = personService.findPersonByIdentifier(personId);
        Lecture lecture = lectureService.findLecture(lectureId);
        LectureGuest guest = studentService.createLectureGuest(person, lecture);
        LectureGuestDto dto = ModelMapper.transform(guest, new LectureGuestDto());

        return dto;
    }


    @RequestMapping(
            value = "/guest/id/{id}",
            method = RequestMethod.DELETE
    )
    @ResponseStatus(code = HttpStatus.OK, reason = "Lecture guest has been deleted")
    @Authorizable(resource = GUEST, action = DELETE)
    public void deleteGuest(
            @PathVariable(name = "id") String id
    ) {
        studentService.deleteLectureGuest(id);
    }




}
