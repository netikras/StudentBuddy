package com.netikras.studies.studentbuddy.api.user.producer;

import com.netikras.studies.studentbuddy.api.user.generated.StudentApiProducer;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.LectureGuestDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.StudentDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.StudentsGroupDto;
import com.netikras.studies.studentbuddy.core.data.api.model.LectureGuest;
import com.netikras.studies.studentbuddy.core.data.api.model.Student;
import com.netikras.studies.studentbuddy.core.data.api.model.StudentsGroup;
import com.netikras.studies.studentbuddy.core.data.meta.annotations.Authorizable;

import com.netikras.studies.studentbuddy.core.service.StudentService;
import com.netikras.tools.common.model.mapper.MappingSettings;
import com.netikras.tools.common.model.mapper.ModelMapper;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

import static com.netikras.studies.studentbuddy.core.data.meta.Action.GET;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.GET_ALL;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.MODIFY;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.SEARCH;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.GUEST;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.STUDENT;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.STUDENT_GROUP;

@RestController
public class StudentProducer extends StudentApiProducer {

    @Resource
    private StudentService studentService;

    @Override
    @Authorizable(resource = STUDENT, action = MODIFY)
    protected StudentDto onUpdateStudentDto(StudentDto studentDto) {
        Student student = ModelMapper.apply(new Student(), studentDto);
        student = studentService.updateStudent(student);
        studentDto = ModelMapper.transform(student, new StudentDto());

        return studentDto;
    }

    @Override
    @Authorizable(resource = STUDENT, action = GET)
    protected StudentDto onRetrieveStudentDto(String id) {
        Student student = studentService.getStudent(id);
        StudentDto studentDto = ModelMapper.transform(student, new StudentDto());

        return studentDto;
    }

    @Override
    @Authorizable(resource = STUDENT_GROUP, action = GET)
    protected StudentsGroupDto onRetrieveStudentsGroupDto(String id) {
        StudentsGroup group = studentService.getStudentsGroup(id);
        StudentsGroupDto dto = ModelMapper.transform(group, new StudentsGroupDto());
        return dto;
    }

    @Override
    @Authorizable(resource = GUEST, action = MODIFY)
    protected LectureGuestDto onUpdateLectureGuestDto(LectureGuestDto dto) {
        LectureGuest guest = ModelMapper.apply(new LectureGuest(), dto);
        guest = studentService.updateLectureGuest(guest);
        dto = ModelMapper.transform(guest, new LectureGuestDto());

        return dto;
    }

    @Override
    @Authorizable(resource = GUEST, action = GET)
    protected LectureGuestDto onRetrieveLectureGuestDto(String id) {
        LectureGuest guest = studentService.getLectureGuest(id);
        LectureGuestDto dto = ModelMapper.transform(guest, new LectureGuestDto());

        return dto;
    }


    @Override
    @Authorizable(resource = STUDENT, action = GET)
    protected List<StudentDto> onGetStudentDtoAllByPersonId(String id) {
        List<Student> students = studentService.getStudentsByPerson(id);
        List<StudentDto> dtos = (List<StudentDto>) ModelMapper.transformAll(students, StudentDto.class);

        return dtos;
    }

    @Override
    @Authorizable(resource = STUDENT_GROUP, action = GET)
    protected StudentsGroupDto onGetStudentsGroupDtoByTitle(String title) {
        StudentsGroup group = studentService.getStudentsGroupByTitle(title);
        StudentsGroupDto dto = ModelMapper.transform(group, new StudentsGroupDto(), new MappingSettings().setDepthMax(3));
        return dto;
    }

    @Override
    @Authorizable(resource = STUDENT_GROUP, action = GET_ALL)
    protected List<StudentsGroupDto> onGetStudentsGroupDtoAll() {
        List<StudentsGroup> groups = studentService.getAllStudentGroups();
        List<StudentsGroupDto> dtos = (List<StudentsGroupDto>) ModelMapper.transformAll(groups, StudentsGroupDto.class, new MappingSettings().setDepthMax(3));
        return dtos;
    }

    @Override
    @Authorizable(resource = STUDENT, action = GET)
    protected List<StudentDto> onGetStudentDtoAllByGroup(String id) {
        List<Student> students = studentService.getStudentsByGroupId(id);
        List<StudentDto> dtos = (List<StudentDto>) ModelMapper.transformAll(students, StudentDto.class);

        return dtos;
    }

    @Override
    @Authorizable(resource = STUDENT, action = SEARCH)
    protected List<StudentDto> onSearchStudentDtoAllByFirstName(String fnameSubstring) {
        List<Student> students = studentService.searchAllStudentsByFirstName(fnameSubstring);
        List<StudentDto> studentDtos =
                (List<StudentDto>) ModelMapper.transformAll(students, StudentDto.class, new MappingSettings().setDepthMax(3));

        return studentDtos;
    }

    @Override
    @Authorizable(resource = STUDENT, action = SEARCH)
    protected List<StudentDto> onSearchStudentDtoAllByLastName(String lnameSubstring) {
        List<Student> students = studentService.searchAllStudentsByLastName(lnameSubstring);
        List<StudentDto> studentDtos =
                (List<StudentDto>) ModelMapper.transformAll(students, StudentDto.class, new MappingSettings().setDepthMax(3));

        return studentDtos;
    }

    @Override
    @Authorizable(resource = STUDENT_GROUP, action = SEARCH)
    protected List<StudentsGroupDto> onSearchStudentsGroupDtoAllByTitle(String titleSubstring) {
        List<StudentsGroup> groups = studentService.searchAllGroupsByTitle(titleSubstring);
        List<StudentsGroupDto> groupDtos =
                (List<StudentsGroupDto>) ModelMapper.transformAll(groups, StudentsGroupDto.class, new MappingSettings().setDepthMax(3));

        return groupDtos;
    }

    @Override
    @Authorizable(resource = GUEST, action = SEARCH)
    protected List<LectureGuestDto> onSearchLectureGuestDtoByLastName(String lnameSubstring) {
        List<LectureGuest> guests = studentService.searchAllGuestsByLastName(lnameSubstring);
        List<LectureGuestDto> guestDtos =
                (List<LectureGuestDto>) ModelMapper.transformAll(guests, LectureGuestDto.class, new MappingSettings().setDepthMax(3));

        return guestDtos;
    }

    @Override
    @Authorizable(resource = GUEST, action = SEARCH)
    protected List<LectureGuestDto> onSearchLectureGuestDtoByFirstName(String fnameSubstring) {
        List<LectureGuest> guests = studentService.searchAllGuestsByFirstName(fnameSubstring);
        List<LectureGuestDto> guestDtos =
                (List<LectureGuestDto>) ModelMapper.transformAll(guests, LectureGuestDto.class, new MappingSettings().setDepthMax(3));

        return guestDtos;
    }
}
