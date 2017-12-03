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
import com.netikras.studies.studentbuddy.core.validator.EntityProvider;
import com.netikras.tools.common.model.mapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

import static com.netikras.studies.studentbuddy.core.data.meta.Action.GET;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.GET_ALL;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.MODIFY;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.SEARCH;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.GUEST;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.STUDENT;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.STUDENT_GROUP;

@Component
public class StudentProducerImpl {

    @Resource
    private ModelMapper modelMapper;

    @Resource
    private StudentService studentService;

    @Resource
    private DtoMapper dtoMapper;
    @Resource
    private EntityProvider entityProvider;

    @Authorizable(resource = STUDENT, action = MODIFY)
    @Transactional
    public StudentDto updateStudent(StudentDto studentDto) {
        Student student = modelMapper.apply(entityProvider.fetch(studentDto), studentDto);
        student = studentService.updateStudent(student);
        studentDto = (StudentDto) dtoMapper.toDto(student);
        return studentDto;
    }

    @Authorizable(resource = STUDENT, action = GET)
    @Transactional
    public StudentDto getStudent(String id) {
        Student student = studentService.getStudent(id);
        StudentDto studentDto = (StudentDto) dtoMapper.toDto(student);
        return studentDto;
    }

    @Authorizable(resource = STUDENT_GROUP, action = GET)
    @Transactional
    public StudentsGroupDto getStudentsGroup(String id) {
        StudentsGroup group = studentService.getStudentsGroup(id);
        StudentsGroupDto dto = (StudentsGroupDto) dtoMapper.toDto(group);
        return dto;
    }

    @Authorizable(resource = STUDENT_GROUP, action = MODIFY)
    @Transactional
    public StudentsGroupDto updateGroup(StudentsGroupDto groupDto) {
        StudentsGroup group = modelMapper.apply(entityProvider.fetch(groupDto), groupDto);
        group = studentService.updateGroup(group);
        groupDto = (StudentsGroupDto) dtoMapper.toDto(group);
        return groupDto;
    }

    @Authorizable(resource = GUEST, action = MODIFY)
    @Transactional
    public LectureGuestDto updateLectureGuest(LectureGuestDto dto) {
        LectureGuest guest = modelMapper.apply(entityProvider.fetch(dto), dto);
        guest = studentService.updateLectureGuest(guest);
        dto = (LectureGuestDto) dtoMapper.toDto(guest);
        return dto;
    }

    @Authorizable(resource = GUEST, action = GET)
    @Transactional
    public LectureGuestDto getLectureGuest(String id) {
        LectureGuest guest = studentService.getLectureGuest(id);
        LectureGuestDto dto = (LectureGuestDto) dtoMapper.toDto(guest);
        return dto;
    }


    @Authorizable(resource = STUDENT, action = GET)
    @Transactional
    public List<StudentDto> getAllStudentsByPersonId(String id) {
        List<Student> students = studentService.getStudentsByPerson(id);
        List<StudentDto> dtos = (List<StudentDto>) dtoMapper.toDtos(students);
        return dtos;
    }

    @Authorizable(resource = STUDENT_GROUP, action = GET)
    @Transactional
    public StudentsGroupDto getStudentsGroupByTitle(String title) {
        StudentsGroup group = studentService.getStudentsGroupByTitle(title);
        StudentsGroupDto dto = (StudentsGroupDto) dtoMapper.toDto(group);
        return dto;
    }

    @Authorizable(resource = STUDENT_GROUP, action = GET_ALL)
    @Transactional
    public List<StudentsGroupDto> getAllStudentsGroups() {
        List<StudentsGroup> groups = studentService.getAllStudentGroups();
        List<StudentsGroupDto> dtos = (List<StudentsGroupDto>) dtoMapper.toDtos(groups);
        return dtos;
    }

    @Authorizable(resource = STUDENT, action = GET)
    @Transactional
    public List<StudentDto> getAllStudentsByGroup(String id) {
        List<Student> students = studentService.getStudentsByGroupId(id);
        List<StudentDto> dtos = (List<StudentDto>) dtoMapper.toDtos(students);
        return dtos;
    }

    @Authorizable(resource = STUDENT, action = SEARCH)
    @Transactional
    public List<StudentDto> searchAllStudentByFirstName(String fnameSubstring) {
        List<Student> students = studentService.searchAllStudentsByFirstName(fnameSubstring);
        List<StudentDto> studentDtos = (List<StudentDto>) dtoMapper.toDtos(students);
        return studentDtos;
    }

    @Authorizable(resource = STUDENT, action = SEARCH)
    @Transactional
    public List<StudentDto> searchStudentsByLastName(String lnameSubstring) {
        List<Student> students = studentService.searchAllStudentsByLastName(lnameSubstring);
        List<StudentDto> studentDtos = (List<StudentDto>) dtoMapper.toDtos(students);
        return studentDtos;
    }

    @Authorizable(resource = STUDENT_GROUP, action = SEARCH)
    @Transactional
    public List<StudentsGroupDto> searchStudentsGroupsByTitle(String titleSubstring) {
        List<StudentsGroup> groups = studentService.searchAllGroupsByTitle(titleSubstring);
        List<StudentsGroupDto> groupDtos = (List<StudentsGroupDto>) dtoMapper.toDtos(groups);
        return groupDtos;
    }

    @Authorizable(resource = GUEST, action = SEARCH)
    @Transactional
    public List<LectureGuestDto> searchAllLectureGuestsByLastName(String lnameSubstring) {
        List<LectureGuest> guests = studentService.searchAllGuestsByLastName(lnameSubstring);
        List<LectureGuestDto> guestDtos = (List<LectureGuestDto>) dtoMapper.toDtos(guests);
        return guestDtos;
    }

    @Authorizable(resource = GUEST, action = SEARCH)
    @Transactional
    public List<LectureGuestDto> searchAllLectureGuestsByFirstName(String fnameSubstring) {
        List<LectureGuest> guests = studentService.searchAllGuestsByFirstName(fnameSubstring);
        List<LectureGuestDto> guestDtos = (List<LectureGuestDto>) dtoMapper.toDtos(guests);
        return guestDtos;
    }

    @Authorizable(resource = GUEST, action = GET_ALL)
    @Transactional
    public List<LectureGuestDto> getAllGuestsByPerson(String id) {
        List<LectureGuest> guests = studentService.getAllGuestsByPersonId(id);
        List<LectureGuestDto> guestDtos = (List<LectureGuestDto>) dtoMapper.toDtos(guests);
        return guestDtos;
    }

    @Authorizable(resource = GUEST, action = GET_ALL)
    @Transactional
    public List<LectureGuestDto> getAllGuestsByDiscipline(String id) {
        List<LectureGuest> guests = studentService.getAllGuestsByDisciplineId(id);
        List<LectureGuestDto> guestDtos = (List<LectureGuestDto>) dtoMapper.toDtos(guests);
        return guestDtos;
    }

    @Authorizable(resource = GUEST, action = GET_ALL)
    @Transactional
    public List<LectureGuestDto> getAllGuestsByCourse(String id) {
        List<LectureGuest> guests = studentService.getAllGuestsByCourseId(id);
        List<LectureGuestDto> guestDtos = (List<LectureGuestDto>) dtoMapper.toDtos(guests);
        return guestDtos;
    }
}
