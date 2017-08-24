package com.netikras.studies.studentbuddy.api.user;


import com.netikras.studies.studentbuddy.core.data.api.dto.school.LectureGuestDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.StudentDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.StudentsGroupDto;
import com.netikras.studies.studentbuddy.core.data.api.model.LectureGuest;
import com.netikras.studies.studentbuddy.core.data.api.model.Student;
import com.netikras.studies.studentbuddy.core.data.api.model.StudentsGroup;
import com.netikras.studies.studentbuddy.core.meta.annotations.Authorizable;
import com.netikras.studies.studentbuddy.core.service.StudentService;
import com.netikras.tools.common.model.mapper.MappingSettings;
import com.netikras.tools.common.model.mapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

import static com.netikras.studies.studentbuddy.api.constants.StudentConstants.BASE_URL;
import static com.netikras.studies.studentbuddy.api.constants.StudentConstants.STUD_URL_GET_ALL_GROUPS;
import static com.netikras.studies.studentbuddy.api.constants.StudentConstants.STUD_URL_GET_ALL_STUDENTS_BY_GROUP_ID;
import static com.netikras.studies.studentbuddy.api.constants.StudentConstants.STUD_URL_GET_BY_ID;
import static com.netikras.studies.studentbuddy.api.constants.StudentConstants.STUD_URL_GET_BY_PERSON_ID;
import static com.netikras.studies.studentbuddy.api.constants.StudentConstants.STUD_URL_GET_GROUP_BY_ID;
import static com.netikras.studies.studentbuddy.api.constants.StudentConstants.STUD_URL_GET_GROUP_BY_TITLE;
import static com.netikras.studies.studentbuddy.api.constants.StudentConstants.STUD_URL_GET_GUEST_BY_ID;
import static com.netikras.studies.studentbuddy.api.constants.StudentConstants.STUD_URL_SEARCH_ALL_BY_FIRST_NAME;
import static com.netikras.studies.studentbuddy.api.constants.StudentConstants.STUD_URL_SEARCH_ALL_BY_LAST_NAME;
import static com.netikras.studies.studentbuddy.api.constants.StudentConstants.STUD_URL_SEARCH_ALL_GROUPS_BY_TITLE;
import static com.netikras.studies.studentbuddy.api.constants.StudentConstants.STUD_URL_SEARCH_ALL_GUESTS_BY_FIRST_NAME;
import static com.netikras.studies.studentbuddy.api.constants.StudentConstants.STUD_URL_SEARCH_ALL_GUESTS_BY_LAST_NAME;
import static com.netikras.studies.studentbuddy.api.constants.StudentConstants.STUD_URL_UPDATE;
import static com.netikras.studies.studentbuddy.api.constants.StudentConstants.STUD_URL_UPDATE_GUEST;
import static com.netikras.studies.studentbuddy.core.meta.Action.GET;
import static com.netikras.studies.studentbuddy.core.meta.Action.MODIFY;
import static com.netikras.studies.studentbuddy.core.meta.Action.SEARCH;
import static com.netikras.studies.studentbuddy.core.meta.Resource.GUEST;
import static com.netikras.studies.studentbuddy.core.meta.Resource.STUDENT;
import static com.netikras.studies.studentbuddy.core.meta.Resource.STUDENT_GROUP;

@RestController
@RequestMapping(value = BASE_URL)
public class StudentController {

    @Resource
    private StudentService studentService;

    @RequestMapping(
            value = STUD_URL_GET_BY_ID,
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
            value = STUD_URL_GET_BY_PERSON_ID,
            method = RequestMethod.GET
    )
    @ResponseBody
    @Authorizable(resource = STUDENT, action = GET)
    public StudentDto getStudentByPersonId(
            @PathVariable(name = "id") String id
    ) {
        Student student = studentService.getStudentByPerson(id);
        StudentDto studentDto = ModelMapper.transform(student, new StudentDto());

        return studentDto;
    }


    @RequestMapping(
            value = STUD_URL_UPDATE,
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
            value = STUD_URL_GET_GROUP_BY_ID,
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
            value = STUD_URL_GET_GROUP_BY_TITLE,
            method = RequestMethod.GET
    )
    @ResponseBody
    @Authorizable(resource = STUDENT_GROUP, action = GET)
    @Transactional
    public StudentsGroupDto getGroupByTitle(
            @PathVariable(name = "title") String title
    ) {
        StudentsGroup group = studentService.getStudentsGroupByTitle(title);
        StudentsGroupDto dto = ModelMapper.transform(group, new StudentsGroupDto(), new MappingSettings().setDepthMax(3));
        return dto;
    }

    @RequestMapping(
            value = STUD_URL_GET_ALL_GROUPS,
            method = RequestMethod.GET
    )
    @ResponseBody
    @Authorizable(resource = STUDENT_GROUP, action = GET)
    public List<StudentsGroupDto> getAllGroups() {
        List<StudentsGroup> groups = studentService.getAllStudentGroups();
        List<StudentsGroupDto> dtos = (List<StudentsGroupDto>) ModelMapper.transformAll(groups, StudentsGroupDto.class, new MappingSettings().setDepthMax(3));
        return dtos;
    }



    @RequestMapping(
            value = STUD_URL_GET_ALL_STUDENTS_BY_GROUP_ID,
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
            value = STUD_URL_GET_GUEST_BY_ID,
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
            value = STUD_URL_UPDATE_GUEST,
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


    @RequestMapping(
            value = STUD_URL_SEARCH_ALL_BY_FIRST_NAME,
            method = RequestMethod.GET
    )
    @ResponseBody
    @Authorizable(resource = STUDENT, action = SEARCH)
    public List<StudentDto> searchAllStudentsByFirstName(
            @PathVariable(name = "fname") String fnameSubstring
    ) {
        List<Student> students = studentService.searchAllStudentsByFirstName(fnameSubstring);
        List<StudentDto> studentDtos =
                (List<StudentDto>) ModelMapper.transformAll(students, StudentDto.class, new MappingSettings().setDepthMax(3));

        return studentDtos;
    }

    @RequestMapping(
            value = STUD_URL_SEARCH_ALL_BY_LAST_NAME,
            method = RequestMethod.GET
    )
    @ResponseBody
    @Authorizable(resource = STUDENT, action = SEARCH)
    public List<StudentDto> searchAllStudentsByLastName(
            @PathVariable(name = "lname") String lnameSubstring
    ) {
        List<Student> students = studentService.searchAllStudentsByLastName(lnameSubstring);
        List<StudentDto> studentDtos =
                (List<StudentDto>) ModelMapper.transformAll(students, StudentDto.class, new MappingSettings().setDepthMax(3));

        return studentDtos;
    }


    @RequestMapping(
            value = STUD_URL_SEARCH_ALL_GROUPS_BY_TITLE,
            method = RequestMethod.GET
    )
    @ResponseBody
    @Authorizable(resource = STUDENT_GROUP, action = SEARCH)
    public List<StudentsGroupDto> searchAllGroupsByTitle(
            @PathVariable(name = "title") String titleSubstring
    ) {
        List<StudentsGroup> groups = studentService.searchAllGroupsByTitle(titleSubstring);
        List<StudentsGroupDto> groupDtos =
                (List<StudentsGroupDto>) ModelMapper.transformAll(groups, StudentsGroupDto.class, new MappingSettings().setDepthMax(3));

        return groupDtos;
    }


    @RequestMapping(
            value = STUD_URL_SEARCH_ALL_GUESTS_BY_LAST_NAME,
            method = RequestMethod.GET
    )
    @ResponseBody
    @Authorizable(resource = GUEST, action = SEARCH)
    public List<LectureGuestDto> searchAllGuestsByLastName(
            @PathVariable(name = "lname") String lnameSubstring
    ) {
        List<LectureGuest> guests = studentService.searchAllGuestsByLastName(lnameSubstring);
        List<LectureGuestDto> guestDtos =
                (List<LectureGuestDto>) ModelMapper.transformAll(guests, LectureGuestDto.class, new MappingSettings().setDepthMax(3));

        return guestDtos;
    }

    @RequestMapping(
            value = STUD_URL_SEARCH_ALL_GUESTS_BY_FIRST_NAME,
            method = RequestMethod.GET
    )
    @ResponseBody
    @Authorizable(resource = GUEST, action = SEARCH)
    public List<LectureGuestDto> searchAllGuestsByFirstName(
            @PathVariable(name = "fname") String fnameSubstring
    ) {
        List<LectureGuest> guests = studentService.searchAllGuestsByFirstName(fnameSubstring);
        List<LectureGuestDto> guestDtos =
                (List<LectureGuestDto>) ModelMapper.transformAll(guests, LectureGuestDto.class, new MappingSettings().setDepthMax(3));

        return guestDtos;
    }


}
