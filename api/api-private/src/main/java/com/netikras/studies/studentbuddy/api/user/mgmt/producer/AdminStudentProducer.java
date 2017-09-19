package com.netikras.studies.studentbuddy.api.user.mgmt.producer;

import com.netikras.studies.studentbuddy.api.user.mgmt.generated.AdminStudentApiProducer;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.LectureGuestDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.StudentDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.StudentsGroupDto;
import com.netikras.studies.studentbuddy.core.data.api.model.Lecture;
import com.netikras.studies.studentbuddy.core.data.api.model.LectureGuest;
import com.netikras.studies.studentbuddy.core.data.api.model.Person;
import com.netikras.studies.studentbuddy.core.data.api.model.Student;
import com.netikras.studies.studentbuddy.core.data.api.model.StudentsGroup;
import com.netikras.studies.studentbuddy.core.data.meta.annotations.Authorizable;
import com.netikras.studies.studentbuddy.core.service.LectureService;
import com.netikras.studies.studentbuddy.core.service.PersonService;
import com.netikras.studies.studentbuddy.core.service.StudentService;
import com.netikras.tools.common.model.mapper.MappingSettings;
import com.netikras.tools.common.model.mapper.ModelMapper;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

import static com.netikras.studies.studentbuddy.core.data.meta.Action.CREATE;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.DELETE;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.MODERATE;
import static com.netikras.studies.studentbuddy.core.data.meta.Action.PURGE;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.GUEST;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.STUDENT;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.STUDENT_GROUP;

@RestController
public class AdminStudentProducer extends AdminStudentApiProducer {

    @Resource
    private StudentService studentService;

    @Resource
    private PersonService personService;

    @Resource
    private LectureService lectureService;


    @Override
    @Authorizable(resource = STUDENT, action = PURGE)
    protected void onPurgeStudentDto(String id) {
        studentService.purgeStudent(id);
    }

    @Override
    @Authorizable(resource = STUDENT_GROUP, action = PURGE)
    protected void onPurgeStudentsGroupDto(String id) {
        studentService.purgeStudentsGroup(id);
    }

    @Override
    @Authorizable(resource = GUEST, action = PURGE)
    protected void onPurgeLectureGuestDto(String id) {
        studentService.purgeLectureGuest(id);
    }

    @Override
    @Authorizable(resource = STUDENT, action = CREATE)
    protected StudentDto onCreateStudentDto(StudentDto studentDto) {
        Student student = ModelMapper.apply(new Student(), studentDto, new MappingSettings().setForceUpdate(true));
        if (student != null) student.setId(null);

        student = studentService.createStudent(student);
        studentDto = ModelMapper.transform(student, new StudentDto());

        return studentDto;
    }

    @Override
    @Authorizable(resource = STUDENT, action = DELETE)
    protected void onDeleteStudentDto(String id) {
        studentService.deleteStudent(id);
    }

    @Override
    @Authorizable(resource = STUDENT_GROUP, action = CREATE)
    protected StudentsGroupDto onCreateStudentsGroupDto(StudentsGroupDto groupDto) {
        StudentsGroup group = ModelMapper.apply(new StudentsGroup(), groupDto, new MappingSettings().setForceUpdate(true));
        group = studentService.createStudentsGroup(group);
        StudentsGroupDto dto = ModelMapper.transform(group, new StudentsGroupDto());
        return dto;
    }

    @Override
    @Authorizable(resource = STUDENT_GROUP, action = DELETE)
    protected void onDeleteStudentsGroupDto(String id) {
        studentService.deleteStudentsGroup(id);
    }

    @Override
    @Authorizable(resource = GUEST, action = CREATE)
    protected LectureGuestDto onCreateLectureGuestDto(LectureGuestDto dto) {
        LectureGuest guest = ModelMapper.apply(new LectureGuest(), dto, new MappingSettings().setForceUpdate(true));
        guest = studentService.createLectureGuest(guest);
        dto = ModelMapper.transform(guest, new LectureGuestDto());

        return dto;
    }

    @Override
    @Authorizable(resource = GUEST, action = DELETE)
    protected void onDeleteLectureGuestDto(String id) {
        studentService.deleteLectureGuest(id);
    }

    @Override
    @Authorizable(resource = STUDENT, action = MODERATE)
    protected void onAddStudentDtoAllToGroup(String groupId, List<String> studentIds) {
        studentService.addStudentsToGroup(groupId, studentIds);
    }

    @Override
    @Authorizable(resource = STUDENT, action = MODERATE)
    protected void onAddStudentDtoToGroup(String groupId, String studentId) {
        StudentsGroup group = studentService.getStudentsGroup(groupId);
        Student student = studentService.getStudent(studentId);
        studentService.addStudentToGroup(group, student);
    }

    @Override
    @Authorizable(resource = STUDENT, action = MODERATE)
    protected void onRemoveStudentDtoFromGroup(String groupId, String studentId) {
        StudentsGroup group = studentService.getStudentsGroup(groupId);
        Student student = studentService.getStudent(studentId);
        studentService.removeStudentFromGroup(group, student);
    }

    @Override
    @Authorizable(resource = STUDENT, action = MODERATE)
    protected void onRemoveStudentDtoAllFromGroup(String groupId, List<String> studentIds) {
        studentService.removeStudentsFromGroup(groupId, studentIds);
    }


    @Override
    @Authorizable(resource = GUEST, action = CREATE)
    protected LectureGuestDto onCreateLectureGuestDtoByPersonId(String personId, String lectureId) {
        Person person = personService.getPerson(personId);
        Lecture lecture = lectureService.getLecture(lectureId);
        LectureGuest guest = studentService.createLectureGuest(person, lecture);
        LectureGuestDto dto = ModelMapper.transform(guest, new LectureGuestDto());

        return dto;
    }


    @Override
    @Authorizable(resource = GUEST, action = CREATE)
    protected LectureGuestDto onCreateLectureGuestDtoByPersonIdentifier(String personId, String lectureId) {
        Person person = personService.findPersonByIdentifier(personId);
        Lecture lecture = lectureService.getLecture(lectureId);
        LectureGuest guest = studentService.createLectureGuest(person, lecture);
        LectureGuestDto dto = ModelMapper.transform(guest, new LectureGuestDto());

        return dto;
    }
}
