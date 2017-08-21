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
import com.netikras.tools.common.model.mapper.MappingSettings;
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

import static com.netikras.studies.studentbuddy.api.constants.AdminStudentConstants.ADM_STUD_ADD_ALL_STUDENTS_TO_GROUP;
import static com.netikras.studies.studentbuddy.api.constants.AdminStudentConstants.ADM_STUD_ADD_STUDENT_TO_GROUP;
import static com.netikras.studies.studentbuddy.api.constants.AdminStudentConstants.ADM_STUD_CREATE;
import static com.netikras.studies.studentbuddy.api.constants.AdminStudentConstants.ADM_STUD_CREATE_GROUP;
import static com.netikras.studies.studentbuddy.api.constants.AdminStudentConstants.ADM_STUD_CREATE_GUEST;
import static com.netikras.studies.studentbuddy.api.constants.AdminStudentConstants.ADM_STUD_CREATE_GUEST_BY_PERSON_IDENT_AND_LECTURE_ID;
import static com.netikras.studies.studentbuddy.api.constants.AdminStudentConstants.ADM_STUD_CREATE_GUEST_BY_PERSON_ID_AND_LECTURE_ID;
import static com.netikras.studies.studentbuddy.api.constants.AdminStudentConstants.ADM_STUD_DELETE_BY_ID;
import static com.netikras.studies.studentbuddy.api.constants.AdminStudentConstants.ADM_STUD_DELETE_GROUP_BY_ID;
import static com.netikras.studies.studentbuddy.api.constants.AdminStudentConstants.ADM_STUD_DELETE_GUEST_BY_ID;
import static com.netikras.studies.studentbuddy.api.constants.AdminStudentConstants.ADM_STUD_REMOVE_ALL_STUDENTS_FROM_GROUP;
import static com.netikras.studies.studentbuddy.api.constants.AdminStudentConstants.ADM_STUD_REMOVE_STUDENT_FROM_GROUP;
import static com.netikras.studies.studentbuddy.api.constants.AdminStudentConstants.BASE_URL;
import static com.netikras.studies.studentbuddy.core.meta.Action.CREATE;
import static com.netikras.studies.studentbuddy.core.meta.Action.DELETE;
import static com.netikras.studies.studentbuddy.core.meta.Action.MODIFY;
import static com.netikras.studies.studentbuddy.core.meta.Resource.GUEST;
import static com.netikras.studies.studentbuddy.core.meta.Resource.STUDENT;
import static com.netikras.studies.studentbuddy.core.meta.Resource.STUDENT_GROUP;

@RestController
@RequestMapping(value = BASE_URL)
public class AdminStudentController {


    @Resource
    private StudentService studentService;

    @Resource
    private PersonService personService;

    @Resource
    private LectureService lectureService;


    @RequestMapping(
            value = ADM_STUD_CREATE,
            method = RequestMethod.POST
    )
    @ResponseBody
    @Authorizable(resource = STUDENT, action = CREATE)
    public StudentDto createStudent(
            @RequestBody StudentDto studentDto
    ) {
        Student student = ModelMapper.apply(new Student(), studentDto, new MappingSettings().setForceUpdate(true));
        student.setId(null);

        student = studentService.createStudent(student);
        studentDto = ModelMapper.transform(student, new StudentDto());

        return studentDto;
    }


    @RequestMapping(
            value = ADM_STUD_DELETE_BY_ID,
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
            value = ADM_STUD_CREATE_GROUP,
            method = RequestMethod.POST
    )
    @ResponseBody
    @Authorizable(resource = STUDENT_GROUP, action = CREATE)
    public StudentsGroupDto createGroup(
            @RequestBody StudentsGroupDto groupDto
    ) {
        StudentsGroup group = ModelMapper.apply(new StudentsGroup(), groupDto);
        group = studentService.createStudentsGroup(group);
        StudentsGroupDto dto = ModelMapper.transform(group, new StudentsGroupDto());
        return dto;
    }




    @RequestMapping(
            value = ADM_STUD_DELETE_GROUP_BY_ID,
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
            value = ADM_STUD_ADD_ALL_STUDENTS_TO_GROUP,
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
            value = ADM_STUD_ADD_STUDENT_TO_GROUP,
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
            value = ADM_STUD_REMOVE_STUDENT_FROM_GROUP,
            method = RequestMethod.DELETE
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
            value = ADM_STUD_REMOVE_ALL_STUDENTS_FROM_GROUP,
            method = RequestMethod.DELETE
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
            value = ADM_STUD_CREATE_GUEST,
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
            value = ADM_STUD_CREATE_GUEST_BY_PERSON_ID_AND_LECTURE_ID,
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
            value = ADM_STUD_CREATE_GUEST_BY_PERSON_IDENT_AND_LECTURE_ID,
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
            value = ADM_STUD_DELETE_GUEST_BY_ID,
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
