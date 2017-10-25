package com.netikras.studies.studentbuddy.api.user.mgmt.producer.impl.secured;

import com.netikras.studies.studentbuddy.api.handlers.DtoMapper;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.LectureGuestDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.StudentDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.StudentsGroupDto;
import com.netikras.studies.studentbuddy.core.data.api.model.*;
import com.netikras.studies.studentbuddy.core.data.meta.annotations.Authorizable;
import com.netikras.studies.studentbuddy.core.service.LectureService;
import com.netikras.studies.studentbuddy.core.service.PersonService;
import com.netikras.studies.studentbuddy.core.service.StudentService;
import com.netikras.tools.common.model.mapper.MappingSettings;
import com.netikras.tools.common.model.mapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

import static com.netikras.studies.studentbuddy.core.data.meta.Action.*;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.*;

@Component
public class AdminStudentProducerImpl {

    @Resource
    private ModelMapper modelMapper;

    @Resource
    private StudentService studentService;

    @Resource
    private PersonService personService;

    @Resource
    private LectureService lectureService;
    @Resource
    private DtoMapper dtoMapper;


    @Authorizable(resource = STUDENT, action = PURGE)
    public void purgeStudent(String id) {
        studentService.purgeStudent(id);
    }

    @Authorizable(resource = STUDENT_GROUP, action = PURGE)
    public void purgeStudentsGroup(String id) {
        studentService.purgeStudentsGroup(id);
    }

    @Authorizable(resource = GUEST, action = PURGE)
    public void purgeLectureGuest(String id) {
        studentService.purgeLectureGuest(id);
    }

    @Authorizable(resource = STUDENT, action = CREATE)
    public StudentDto createStudent(StudentDto studentDto) {
        Student student = modelMapper.apply(new Student(), studentDto, new MappingSettings().setForceUpdate(true));
        if (student != null) student.setId(null);

        student = studentService.createStudent(student);
        studentDto = (StudentDto) dtoMapper.toDto(student, 3);

        return studentDto;
    }

    @Authorizable(resource = STUDENT, action = DELETE)
    public void deleteStudent(String id) {
        studentService.deleteStudent(id);
    }

    @Authorizable(resource = STUDENT_GROUP, action = CREATE)
    public StudentsGroupDto createStudentsGroup(StudentsGroupDto groupDto) {
        StudentsGroup group = modelMapper.apply(new StudentsGroup(), groupDto, new MappingSettings().setForceUpdate(true));
        group = studentService.createStudentsGroup(group);
        StudentsGroupDto dto = (StudentsGroupDto) dtoMapper.toDto(group, 3);
        return dto;
    }

    @Authorizable(resource = STUDENT_GROUP, action = DELETE)
    public void deleteStudentsGroup(String id) {
        studentService.deleteStudentsGroup(id);
    }

    @Authorizable(resource = GUEST, action = CREATE)
    public LectureGuestDto createLectureGuest(LectureGuestDto dto) {
        LectureGuest guest = modelMapper.apply(new LectureGuest(), dto, new MappingSettings().setForceUpdate(true));
        guest = studentService.createLectureGuest(guest);
        dto = (LectureGuestDto) dtoMapper.toDto(guest, 3);

        return dto;
    }

    @Authorizable(resource = GUEST, action = DELETE)
    public void deleteLectureGuest(String id) {
        studentService.deleteLectureGuest(id);
    }

    @Authorizable(resource = STUDENT, action = MODERATE)
    public void addAllStudentsToGroup(String groupId, List<String> studentIds) {
        studentService.addStudentsToGroup(groupId, studentIds);
    }

    @Authorizable(resource = STUDENT, action = MODERATE)
    public void addStudentToGroup(String groupId, String studentId) {
        StudentsGroup group = studentService.getStudentsGroup(groupId);
        Student student = studentService.getStudent(studentId);
        studentService.addStudentToGroup(group, student);
    }

    @Authorizable(resource = STUDENT, action = MODERATE)
    public void removeStudentFromGroup(String groupId, String studentId) {
        StudentsGroup group = studentService.getStudentsGroup(groupId);
        Student student = studentService.getStudent(studentId);
        studentService.removeStudentFromGroup(group, student);
    }

    @Authorizable(resource = STUDENT, action = MODERATE)
    public void removeAllStudentsFromGroup(String groupId, List<String> studentIds) {
        studentService.removeStudentsFromGroup(groupId, studentIds);
    }


    @Authorizable(resource = GUEST, action = CREATE)
    public LectureGuestDto createLectureGuestsByPersonId(String personId, String lectureId) {
        Person person = personService.getPerson(personId);
        Lecture lecture = lectureService.getLecture(lectureId);
        LectureGuest guest = studentService.createLectureGuest(person, lecture);
        LectureGuestDto dto = (LectureGuestDto) dtoMapper.toDto(guest, 3);

        return dto;
    }


    @Authorizable(resource = GUEST, action = CREATE)
    public LectureGuestDto createLectureGuestByPersonIdentifier(String personId, String lectureId) {
        Person person = personService.findPersonByIdentifier(personId);
        Lecture lecture = lectureService.getLecture(lectureId);
        LectureGuest guest = studentService.createLectureGuest(person, lecture);
        LectureGuestDto dto = (LectureGuestDto) dtoMapper.toDto(guest, 3);

        return dto;
    }
}
