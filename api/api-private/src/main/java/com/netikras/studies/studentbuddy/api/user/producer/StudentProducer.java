package com.netikras.studies.studentbuddy.api.user.producer;

import com.netikras.studies.studentbuddy.api.user.generated.StudentApiProducer;
import com.netikras.studies.studentbuddy.api.user.producer.impl.secured.StudentProducerImpl;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.LectureGuestDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.StudentDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.StudentsGroupDto;
import com.netikras.studies.studentbuddy.core.data.meta.annotations.Authorizable;

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
    private StudentProducerImpl impl;

    @Override
    protected StudentDto onUpdateStudentDto(StudentDto studentDto) {
        return impl.updateStudent(studentDto);
    }

    @Override
    protected StudentDto onRetrieveStudentDto(String id) {
        return impl.getStudent(id);
    }

    @Override
    protected StudentsGroupDto onUpdateStudentsGroupDto(StudentsGroupDto item) {
        return impl.updateGroup(item);
    }

    @Override
    protected StudentsGroupDto onRetrieveStudentsGroupDto(String id) {
        return impl.getStudentsGroup(id);
    }

    @Override
    protected LectureGuestDto onUpdateLectureGuestDto(LectureGuestDto dto) {
        return impl.updateLectureGuest(dto);
    }

    @Override
    protected LectureGuestDto onRetrieveLectureGuestDto(String id) {
        return impl.getLectureGuest(id);
    }


    @Override
    protected List<LectureGuestDto> onGetLectureGuestDtoAllGuestsByCourse(String id) {
        return impl.getAllGuestsByCourse(id);
    }

    @Override
    protected List<LectureGuestDto> onGetLectureGuestDtoAllGuestsByDiscipline(String id) {
        return impl.getAllGuestsByDiscipline(id);
    }

    @Override
    protected List<LectureGuestDto> onGetLectureGuestDtoAllGuestsByPerson(String id) {
        return impl.getAllGuestsByPerson(id);
    }

    @Override
    protected List<StudentDto> onGetStudentDtoAllByPersonId(String id) {
        return impl.getAllStudentsByPersonId(id);
    }

    @Override
    protected StudentsGroupDto onGetStudentsGroupDtoByTitle(String title) {
        return impl.getStudentsGroupByTitle(title);
    }

    @Override
    protected List<StudentsGroupDto> onGetStudentsGroupDtoAll() {
        return impl.getAllStudentsGroups();
    }

    @Override
    protected List<StudentDto> onGetStudentDtoAllByGroup(String id) {
        return impl.getAllStudentsByGroup(id);
    }

    @Override
    protected List<StudentDto> onSearchStudentDtoAllByFirstName(String fnameSubstring) {
        return impl.searchAllStudentByFirstName(fnameSubstring);
    }

    @Override
    protected List<StudentDto> onSearchStudentDtoAllByLastName(String lnameSubstring) {
        return impl.searchStudentsByLastName(lnameSubstring);
    }

    @Override
    protected List<StudentsGroupDto> onSearchStudentsGroupDtoAllByTitle(String titleSubstring) {
        return impl.searchStudentsGroupsByTitle(titleSubstring);
    }

    @Override
    protected List<LectureGuestDto> onSearchLectureGuestDtoByLastName(String lnameSubstring) {
        return impl.searchAllLectureGuestsByLastName(lnameSubstring);
    }

    @Override
    protected List<LectureGuestDto> onSearchLectureGuestDtoByFirstName(String fnameSubstring) {
        return impl.searchAllLectureGuestsByFirstName(fnameSubstring);
    }
}
