package com.netikras.studies.studentbuddy.api.user.mgmt;


import com.netikras.studies.studentbuddy.api.user.GenericPersonAwareTest;
import com.netikras.studies.studentbuddy.api.user.StudentConsumer;
import com.netikras.studies.studentbuddy.core.data.api.dto.PersonDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.StudentDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.StudentsGroupDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;

public class AdminStudentConsumerTest extends GenericPersonAwareTest {
    private static final Logger logger = LoggerFactory.getLogger(AdminPersonConsumerTest.class);

    private AdminStudentConsumer adminStudentConsumer;
    private StudentConsumer studentConsumer;


    @Before
    public void init() {
        super.init();
        studentConsumer = attachConsumer(new StudentConsumer());
        adminStudentConsumer = attachConsumer(new AdminStudentConsumer());
    }


    protected StudentDto buildStudent(PersonDto personDto) {
        StudentDto studentDto = new StudentDto();

        studentDto.setPerson(personDto);

        return studentDto;
    }

    protected StudentDto buildStudentWithGroup(PersonDto personDto) {
        StudentDto studentDto = buildStudent(personDto);
        studentDto.setGroup(buildGroup());
        return studentDto;
    }

    protected StudentsGroupDto buildGroup() {
        StudentsGroupDto groupDto = new StudentsGroupDto();

        groupDto.setEmail("pin13@gmail.com");
        groupDto.setTitle("PIN13");

        return groupDto;
    }

    protected StudentsGroupDto getGroupForTesting() {
        StudentsGroupDto groupDto = studentConsumer.getGroupByTitle(buildGroup().getTitle());

        if (groupDto == null) {
            groupDto = adminStudentConsumer.createStudentGroup(buildGroup());
        }

        return groupDto;
    }

    protected StudentsGroupDto buildGroupWithStudent(PersonDto personDto) {
        StudentsGroupDto groupDto = buildGroup();
        List<StudentDto> studentDtos = new ArrayList<>();
        studentDtos.add(buildStudent(personDto));
        groupDto.setMembers(studentDtos);
        return groupDto;
    }

    @Test
    public void createStudentTest() throws Exception {
        loginSystem();
        PersonDto personDto = getPersonForTesting();
        StudentDto studentDto = studentConsumer.getStudentByPersonId(personDto.getId());
        if (studentDto != null) {
            adminStudentConsumer.deleteStudentById(studentDto.getId());
        }

        studentDto = buildStudent(personDto);
        studentDto.setGroup(getGroupForTesting());
        studentDto = adminStudentConsumer.createStudent(studentDto);

        assertNotNull("Student should be created by now", studentDto);

        adminStudentConsumer.deleteStudentById(studentDto.getId());
    }

    @Test
    public void deleteStudentByIdTest() throws Exception {
    }

    @Test
    public void createStudentGroupTest() throws Exception {
    }

    @Test
    public void deleteStudentGroupByIdTest() throws Exception {
    }

    @Test
    public void addStudentToGroupTest() throws Exception {
    }

    @Test
    public void addAllStudentsToGroupTest() throws Exception {
    }

    @Test
    public void removeStudentFromGroupTest() throws Exception {
    }

    @Test
    public void removeAllStudentsFromGroupTest() throws Exception {
    }

    @Test
    public void createLectureGuestTest() throws Exception {
    }

    @Test
    public void createLectureGuestByPersonIdAndLectureIdTest() throws Exception {
    }

    @Test
    public void createLectureGuestByPersonIdAndLectureIdentifierTest() throws Exception {
    }

    @Test
    public void deleteGuestByIdTest() throws Exception {
    }

}
