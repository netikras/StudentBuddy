package com.netikras.studies.studentbuddy.api.user.mgmt.producer;

import com.netikras.studies.studentbuddy.api.user.mgmt.generated.AdminStudentApiProducer;
import com.netikras.studies.studentbuddy.api.user.mgmt.producer.impl.secured.AdminStudentProducerImpl;
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
    private AdminStudentProducerImpl impl;


    @Override
    protected void onPurgeStudentDto(String id) {
        impl.purgeStudent(id);
    }

    @Override
    protected void onPurgeStudentsGroupDto(String id) {
        impl.purgeStudentsGroup(id);
    }

    @Override
    protected void onPurgeLectureGuestDto(String id) {
        impl.purgeLectureGuest(id);
    }

    @Override
    protected StudentDto onCreateStudentDto(StudentDto studentDto) {
        return impl.createStudent(studentDto);
    }

    @Override
    protected void onDeleteStudentDto(String id) {
        impl.deleteStudent(id);
    }

    @Override
    protected StudentsGroupDto onCreateStudentsGroupDto(StudentsGroupDto groupDto) {
        return impl.createStudentsGroup(groupDto);
    }

    @Override
    protected void onDeleteStudentsGroupDto(String id) {
        impl.deleteStudentsGroup(id);
    }

    @Override
    protected LectureGuestDto onCreateLectureGuestDto(LectureGuestDto dto) {
        return impl.createLectureGuest(dto);
    }

    @Override
    protected void onDeleteLectureGuestDto(String id) {
        impl.deleteLectureGuest(id);
    }

    @Override
    protected void onAddStudentDtoAllToGroup(String groupId, List<String> studentIds) {
        impl.addAllStudentsToGroup(groupId, studentIds);
    }

    @Override
    protected void onAddStudentDtoToGroup(String groupId, String studentId) {
        impl.addStudentToGroup(groupId, studentId);
    }

    @Override
    protected void onRemoveStudentDtoFromGroup(String groupId, String studentId) {
        impl.removeStudentFromGroup(groupId, studentId);
    }

    @Override
    protected void onRemoveStudentDtoAllFromGroup(String groupId, List<String> studentIds) {
        impl.removeAllStudentsFromGroup(groupId, studentIds);
    }


    @Override
    protected LectureGuestDto onCreateLectureGuestDtoByPersonId(String personId, String lectureId) {
        return impl.createLectureGuestsByPersonId(personId, lectureId);
    }


    @Override
    protected LectureGuestDto onCreateLectureGuestDtoByPersonIdentifier(String personId, String lectureId) {
        return impl.createLectureGuestByPersonIdentifier(personId, lectureId);
    }
}
