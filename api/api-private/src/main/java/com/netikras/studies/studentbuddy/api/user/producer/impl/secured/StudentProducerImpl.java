package com.netikras.studies.studentbuddy.api.user.producer.impl.secured;

import com.netikras.studies.studentbuddy.api.handlers.DtoMapper;
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
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

import static com.netikras.studies.studentbuddy.core.data.meta.Action.*;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.*;

@Component
public class StudentProducerImpl {

    @Resource
    private ModelMapper modelMapper;

    @Resource
    private StudentService studentService;

    @Resource
    private DtoMapper dtoMapper;

    @Authorizable(resource = STUDENT, action = MODIFY)
    public StudentDto updateStudent(StudentDto studentDto) {
        Student student = modelMapper.apply(new Student(), studentDto);
        student = studentService.updateStudent(student);
        studentDto = (StudentDto) dtoMapper.toDto(student, 3);

        return studentDto;
    }

    @Authorizable(resource = STUDENT, action = GET)
    public StudentDto getStudent(String id) {
        Student student = studentService.getStudent(id);
        StudentDto studentDto = (StudentDto) dtoMapper.toDto(student, 3);

        return studentDto;
    }

    @Authorizable(resource = STUDENT_GROUP, action = GET)
    public StudentsGroupDto getStudentsGroup(String id) {
        StudentsGroup group = studentService.getStudentsGroup(id);
        StudentsGroupDto dto = (StudentsGroupDto) dtoMapper.toDto(group, 3);
        return dto;
    }

    @Authorizable(resource = GUEST, action = MODIFY)
    public LectureGuestDto updateLectureGuest(LectureGuestDto dto) {
        LectureGuest guest = modelMapper.apply(new LectureGuest(), dto);
        guest = studentService.updateLectureGuest(guest);
        dto = (LectureGuestDto) dtoMapper.toDto(guest, 3);;

        return dto;
    }

    @Authorizable(resource = GUEST, action = GET)
    public LectureGuestDto getLectureGuest(String id) {
        LectureGuest guest = studentService.getLectureGuest(id);
        LectureGuestDto dto = (LectureGuestDto) dtoMapper.toDto(guest, 3);;

        return dto;
    }


    @Authorizable(resource = STUDENT, action = GET)
    public List<StudentDto> getAllStudentsByPersonId(String id) {
        List<Student> students = studentService.getStudentsByPerson(id);
        List<StudentDto> dtos = (List<StudentDto>) modelMapper.transformAll(students, StudentDto.class);

        return dtos;
    }

    @Authorizable(resource = STUDENT_GROUP, action = GET)
    public StudentsGroupDto getStudentsGroupByTitle(String title) {
        StudentsGroup group = studentService.getStudentsGroupByTitle(title);
        StudentsGroupDto dto = (StudentsGroupDto) dtoMapper.toDto(group, 3);;
        return dto;
    }

    @Authorizable(resource = STUDENT_GROUP, action = GET_ALL)
    public List<StudentsGroupDto> getAllStudentsGroups() {
        List<StudentsGroup> groups = studentService.getAllStudentGroups();
        List<StudentsGroupDto> dtos = (List<StudentsGroupDto>) modelMapper.transformAll(groups, StudentsGroupDto.class, new MappingSettings().setDepthMax(3));
        return dtos;
    }

    @Authorizable(resource = STUDENT, action = GET)
    public List<StudentDto> getAllStudentsByGroup(String id) {
        List<Student> students = studentService.getStudentsByGroupId(id);
        List<StudentDto> dtos = (List<StudentDto>) modelMapper.transformAll(students, StudentDto.class);

        return dtos;
    }

    @Authorizable(resource = STUDENT, action = SEARCH)
    public List<StudentDto> searchAllStudentByFirstName(String fnameSubstring) {
        List<Student> students = studentService.searchAllStudentsByFirstName(fnameSubstring);
        List<StudentDto> studentDtos =
                (List<StudentDto>) modelMapper.transformAll(students, StudentDto.class, new MappingSettings().setDepthMax(3));

        return studentDtos;
    }

    @Authorizable(resource = STUDENT, action = SEARCH)
    public List<StudentDto> searchStudentsByLastName(String lnameSubstring) {
        List<Student> students = studentService.searchAllStudentsByLastName(lnameSubstring);
        List<StudentDto> studentDtos =
                (List<StudentDto>) modelMapper.transformAll(students, StudentDto.class, new MappingSettings().setDepthMax(3));

        return studentDtos;
    }

    @Authorizable(resource = STUDENT_GROUP, action = SEARCH)
    public List<StudentsGroupDto> searchStudentsGroupsByTitle(String titleSubstring) {
        List<StudentsGroup> groups = studentService.searchAllGroupsByTitle(titleSubstring);
        List<StudentsGroupDto> groupDtos =
                (List<StudentsGroupDto>) modelMapper.transformAll(groups, StudentsGroupDto.class, new MappingSettings().setDepthMax(3));

        return groupDtos;
    }

    @Authorizable(resource = GUEST, action = SEARCH)
    public List<LectureGuestDto> searchAllLectureGuestsByLastName(String lnameSubstring) {
        List<LectureGuest> guests = studentService.searchAllGuestsByLastName(lnameSubstring);
        List<LectureGuestDto> guestDtos =
                (List<LectureGuestDto>) modelMapper.transformAll(guests, LectureGuestDto.class, new MappingSettings().setDepthMax(3));

        return guestDtos;
    }

    @Authorizable(resource = GUEST, action = SEARCH)
    public List<LectureGuestDto> searchAllLectureGuestsByFirstName(String fnameSubstring) {
        List<LectureGuest> guests = studentService.searchAllGuestsByFirstName(fnameSubstring);
        List<LectureGuestDto> guestDtos =
                (List<LectureGuestDto>) modelMapper.transformAll(guests, LectureGuestDto.class, new MappingSettings().setDepthMax(3));

        return guestDtos;
    }
}