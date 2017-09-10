package com.netikras.studies.studentbuddy.api.user.mgmt;


import com.netikras.studies.studentbuddy.api.user.GenericPersonAwareTest;
import com.netikras.studies.studentbuddy.api.user.generated.StudentApiConsumer;
import com.netikras.studies.studentbuddy.api.user.mgmt.generated.AdminStudentApiConsumer;
import com.netikras.studies.studentbuddy.core.data.api.dto.PersonDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.StudentDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.StudentsGroupDto;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.netikras.tools.common.security.IntegrityUtils.isNullOrEmpty;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class AdminStudentConsumerTest extends GenericPersonAwareTest {
    private static final Logger logger = LoggerFactory.getLogger(AdminPersonConsumerTest.class);

    private AdminStudentApiConsumer adminStudentConsumer;
    private StudentApiConsumer studentConsumer;


    @Before
    public void initPersonAware() {
        super.initPersonAware();
        studentConsumer = attachConsumer(new StudentApiConsumer());
        adminStudentConsumer = attachConsumer(new AdminStudentApiConsumer());
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
        StudentsGroupDto groupDto = studentConsumer.getStudentsGroupDtoByTitle(buildGroup().getTitle());

        if (groupDto == null) {
            groupDto = adminStudentConsumer.createStudentsGroupDto(buildGroup());
        }

        return groupDto;
    }

    protected void removeTestGroup() {
        loginSystem();
        StudentsGroupDto groupDto = studentConsumer.getStudentsGroupDtoByTitle(buildGroup().getTitle());
        if (groupDto != null) {
            adminStudentConsumer.deleteStudentsGroupDto(groupDto.getId());
        }
    }

    protected StudentsGroupDto buildGroupWithStudent(PersonDto personDto) {
        StudentsGroupDto groupDto = buildGroup();
        List<StudentDto> studentDtos = new ArrayList<>();
        studentDtos.add(buildStudent(personDto));
        groupDto.setMembers(studentDtos);
        return groupDto;
    }

    @Test
    public void createStudentDtoTest() throws Exception {
        loginSystem();
        PersonDto personDto = getPersonForTesting();
        List<StudentDto> studentDtos = studentConsumer.getStudentDtoAllByPersonId(personDto.getId());
        StudentDto studentDto;
        if (!isNullOrEmpty(studentDtos)) {
            studentDtos.forEach(studentDto1 -> adminStudentConsumer.deleteStudentDto(studentDto1.getId()));
        }

        studentDto = buildStudent(personDto);
        studentDto.setGroup(getGroupForTesting());
        studentDto = adminStudentConsumer.createStudentDto(studentDto);

        assertNotNull("Student should be created by now", studentDto);

        adminStudentConsumer.deleteStudentDto(studentDto.getId());
        removeTestPerson();
        removeTestGroup();
    }

    @Test
    public void deleteStudentDtoTest() throws Exception {
        loginSystem();
        PersonDto personDto = getPersonForTesting();
        List<StudentDto> studentDtos = studentConsumer.getStudentDtoAllByPersonId(personDto.getId());
        StudentDto studentDto;
        if (isNullOrEmpty(studentDtos)) {
            studentDto = buildStudent(personDto);
            studentDto.setGroup(getGroupForTesting());
            studentDto = adminStudentConsumer.createStudentDto(studentDto);
        } else {
            studentDto = studentDtos.get(0);
        }

        assertNotNull("Student should be created by now", studentDto);

        adminStudentConsumer.deleteStudentDto(studentDto.getId());
        removeTestPerson();
        removeTestGroup();

        studentDtos = studentConsumer.getStudentDtoAllByPersonId(personDto.getId());
        assertNull("Student should be deleted by now", studentDto);
        removeTestPerson();
        removeTestGroup();
    }

    @Test
    public void createStudentDtosGroupDtoTest() throws Exception {
        loginSystem();
        StudentsGroupDto groupDto = studentConsumer.getStudentsGroupDtoByTitle(buildGroup().getTitle());
        if (groupDto != null) {
            adminStudentConsumer.deleteLectureGuestDto(groupDto.getId());
        }

        assertNull("There should be no test group created in the DB by now", groupDto);

        groupDto = adminStudentConsumer.createStudentsGroupDto(buildGroup());
        assertNotNull("New group should have been created", groupDto);
        assertEquals("New group should have given title", buildGroup().getTitle(), groupDto.getTitle());

        adminStudentConsumer.deleteStudentsGroupDto(groupDto.getId());
        removeTestPerson();
        removeTestGroup();
    }

    @Test
    public void deleteStudentsGroupDtoTest() throws Exception {
        loginSystem();
        StudentsGroupDto groupDto = studentConsumer.getStudentsGroupDtoByTitle(buildGroup().getTitle());
        if (groupDto == null) {
            groupDto = adminStudentConsumer.createStudentsGroupDto(buildGroup());
        }

        assertNotNull("Group DTO must not be null at this point", groupDto);
        adminStudentConsumer.deleteStudentsGroupDto(groupDto.getId());
        groupDto = studentConsumer.getStudentsGroupDtoByTitle(buildGroup().getTitle());
        assertNull("Group must have been deleted", groupDto);
        removeTestPerson();
        removeTestGroup();
    }

    @Test
    public void addStudentToGroupTest() throws Exception {
        loginSystem();
        List<StudentDto> studentDtos = studentConsumer.getStudentDtoAllByPersonId(getPersonForTesting().getId());
        if (!isNullOrEmpty(studentDtos)) {
            studentDtos.forEach(studentDto -> adminStudentConsumer.deleteStudentDto(studentDto.getId()));
        }
        assertNull("There should be no such student in the database atm", studentDtos);

        StudentsGroupDto groupDto = studentConsumer.getStudentsGroupDtoByTitle(buildGroup().getTitle());
        if (groupDto != null) {
            adminStudentConsumer.deleteStudentsGroupDto(groupDto.getId());
            groupDto = studentConsumer.retrieveStudentsGroupDto(groupDto.getId());
        }
        assertNull("Group should be removed now", groupDto);

        groupDto = adminStudentConsumer.createStudentsGroupDto(buildGroup());
        assertNotNull("There should be a student group in the DB by now", groupDto);

        StudentDto studentDto = adminStudentConsumer.createStudentDto(buildStudent(getPersonForTesting()));
        assertNotNull("Student should be created at this point", studentDto);
        assertNull("Student should not be assigned to any group", studentDto.getGroup());

        adminStudentConsumer.addStudentDtoToGroup(groupDto.getId(), studentDto.getId());

        studentDto = studentConsumer.retrieveStudentDto(studentDto.getId());
        assertNotNull("Student shall not be deleted yet", studentDto);
        assertNotNull("Student should be assigned to group now", studentDto.getGroup());
        assertEquals("Student should be assigned to that particular group", buildGroup().getTitle(), studentDto.getGroup().getTitle());

        groupDto = studentConsumer.getStudentsGroupDtoByTitle(groupDto.getTitle());
        assertNotNull("The group should not be null", groupDto);
        assertEquals("There should be only one member in the group", 1, groupDto.getMembers().size());
        assertEquals("Member of the group must have a persisted ID", studentDto.getId(), groupDto.getMembers().get(0).getId());

        adminStudentConsumer.deleteStudentDto(studentDto.getId());
        adminStudentConsumer.deleteStudentsGroupDto(groupDto.getId());
        removeTestPerson();
        removeTestGroup();
    }

    @Test
    public void addAllStudentsToGroupTest() throws Exception {
        loginSystem();
        PersonDto person2 = new PersonDto();
        person2.setPersonalCode("12345");
        person2.setIdentification("543210");
        person2.setFirstName("John");
        person2.setLastName("Irwing");
        PersonDto personDto1 = getPersonForTesting();
        PersonDto personDto2 = personConsumer.getPersonDtoByCode(person2.getPersonalCode());
        if (personDto2 == null) {
            personDto2 = adminPersonConsumer.createPersonDto(person2);
        }

        List<StudentDto> studentDtos1 = studentConsumer.getStudentDtoAllByPersonId(personDto1.getId());
        List<StudentDto> studentDtos2 = studentConsumer.getStudentDtoAllByPersonId(personDto2.getId());
        if (!isNullOrEmpty(studentDtos1)) {
            studentDtos1.forEach(studentDto -> adminStudentConsumer.deleteStudentDto(studentDto.getId()));
            studentDtos1 = studentConsumer.getStudentDtoAllByPersonId(personDto1.getId());
        }
        if (!isNullOrEmpty(studentDtos2)) {
            studentDtos2.forEach(studentDto -> adminStudentConsumer.deleteStudentDto(studentDto.getId()));
            studentDtos2 = studentConsumer.getStudentDtoAllByPersonId(personDto2.getId());
        }
        assertNull("There should be no such student1 in the database atm", studentDtos1);
        assertNull("There should be no such student2 in the database atm", studentDtos2);


        StudentsGroupDto groupDto = studentConsumer.getStudentsGroupDtoByTitle(buildGroup().getTitle());
        if (groupDto != null) {
            adminStudentConsumer.deleteStudentsGroupDto(groupDto.getId());
            groupDto = studentConsumer.retrieveStudentsGroupDto(groupDto.getId());
        }
        assertNull("Group should be removed now", groupDto);

        groupDto = adminStudentConsumer.createStudentsGroupDto(buildGroup());
        assertNotNull("There should be a student group in the DB by now", groupDto);


        StudentDto studentDto1 = adminStudentConsumer.createStudentDto(buildStudent(personDto1));
        assertNotNull("Student1 should be created at this point", studentDto1);
        assertNull("Student1 should not be assigned to any group", studentDto1.getGroup());

        StudentDto studentDto2 = adminStudentConsumer.createStudentDto(buildStudent(personDto2));
        assertNotNull("Student2 should be created at this point", studentDto2);
        assertNull("Student2 should not be assigned to any group", studentDto2.getGroup());

        List<String> studentIds = new ArrayList<>();
        Collections.addAll(studentIds, studentDto1.getId(), studentDto2.getId());
        adminStudentConsumer.addStudentDtoAllToGroup(groupDto.getId(), studentIds);

        studentDto1 = studentConsumer.retrieveStudentDto(studentDto1.getId());
        assertNotNull("Student1 shall not be deleted yet", studentDto1);
        assertNotNull("Student1 should be assigned to group now", studentDto1.getGroup());
        assertEquals("Student1 should be assigned to that particular group", buildGroup().getTitle(), studentDto1.getGroup().getTitle());

        studentDto2 = studentConsumer.retrieveStudentDto(studentDto2.getId());
        assertNotNull("Student2 shall not be deleted yet", studentDto2);
        assertNotNull("Student2 should be assigned to group now", studentDto2.getGroup());
        assertEquals("Student2 should be assigned to that particular group", buildGroup().getTitle(), studentDto2.getGroup().getTitle());

        groupDto = studentConsumer.getStudentsGroupDtoByTitle(groupDto.getTitle());
        assertNotNull("The group should not be null", groupDto);
        assertEquals("There should be two members in the group", 2, groupDto.getMembers().size());
        assertTrue("Members of the group must have a persisted ID",
                studentDto1.getId().equals(groupDto.getMembers().get(0).getId())
                        || studentDto2.getId().equals(groupDto.getMembers().get(0).getId()));
        assertTrue("Members of the group must have a persisted ID",
                studentDto1.getId().equals(groupDto.getMembers().get(1).getId())
                        || studentDto2.getId().equals(groupDto.getMembers().get(1).getId()));

        adminStudentConsumer.removeStudentDtoAllFromGroup(groupDto.getId(), studentIds);

        adminStudentConsumer.deleteStudentDto(studentDto1.getId());
        adminStudentConsumer.deleteStudentDto(studentDto2.getId());
        adminStudentConsumer.deleteStudentsGroupDto(groupDto.getId());

        adminPersonConsumer.deletePersonDto(personDto2.getId());
        removeTestPerson();
        removeTestGroup();
    }

    @Test
    public void removeStudentFromGroupTest() throws Exception {

    }

    @Test
    public void removeAllStudentsFromGroupTest() throws Exception {
        loginSystem();
        PersonDto person2 = new PersonDto();
        person2.setPersonalCode("12345");
        person2.setIdentification("543210");
        person2.setFirstName("John");
        person2.setLastName("Irwing");
        PersonDto personDto1 = getPersonForTesting();
        PersonDto personDto2 = personConsumer.getPersonDtoByCode(person2.getPersonalCode());
        if (personDto2 == null) {
            personDto2 = adminPersonConsumer.createPersonDto(person2);
        }

        List<StudentDto> studentDtos1 = studentConsumer.getStudentDtoAllByPersonId(personDto1.getId());
        List<StudentDto> studentDtos2 = studentConsumer.getStudentDtoAllByPersonId(personDto2.getId());
        if (!isNullOrEmpty(studentDtos1)) {
            studentDtos1.forEach(studentDto -> adminStudentConsumer.deleteStudentDto(studentDto.getId()));
            studentDtos1 = studentConsumer.getStudentDtoAllByPersonId(personDto1.getId());
        }
        if (!isNullOrEmpty(studentDtos2)) {
            studentDtos2.forEach(studentDto -> adminStudentConsumer.deleteStudentDto(studentDto.getId()));
            studentDtos2 = studentConsumer.getStudentDtoAllByPersonId(personDto2.getId());
        }
        assertNull("There should be no such student1 in the database atm", studentDtos1);
        assertNull("There should be no such student2 in the database atm", studentDtos2);

        StudentsGroupDto groupDto = studentConsumer.getStudentsGroupDtoByTitle(buildGroup().getTitle());
        if (groupDto != null) {
            adminStudentConsumer.deleteStudentsGroupDto(groupDto.getId());
            groupDto = studentConsumer.retrieveStudentsGroupDto(groupDto.getId());
        }
        assertNull("Group should be removed now", groupDto);

        groupDto = adminStudentConsumer.createStudentsGroupDto(buildGroup());
        assertNotNull("There should be a student group in the DB by now", groupDto);


        StudentDto studentDto1 = adminStudentConsumer.createStudentDto(buildStudent(personDto1));
        assertNotNull("Student1 should be created at this point", studentDto1);
        assertNull("Student1 should not be assigned to any group", studentDto1.getGroup());

        StudentDto studentDto2 = adminStudentConsumer.createStudentDto(buildStudent(personDto2));
        assertNotNull("Student2 should be created at this point", studentDto2);
        assertNull("Student2 should not be assigned to any group", studentDto2.getGroup());

        adminStudentConsumer.addStudentDtoToGroup(groupDto.getId(), studentDto1.getId());
        adminStudentConsumer.addStudentDtoToGroup(groupDto.getId(), studentDto2.getId());

        studentDto1 = studentConsumer.retrieveStudentDto(studentDto1.getId());
        assertNotNull("Student1 shall not be deleted yet", studentDto1);
        assertNotNull("Student1 should be assigned to group now", studentDto1.getGroup());
        assertEquals("Student1 should be assigned to that particular group", buildGroup().getTitle(), studentDto1.getGroup().getTitle());

        studentDto2 = studentConsumer.retrieveStudentDto(studentDto2.getId());
        assertNotNull("Student2 shall not be deleted yet", studentDto2);
        assertNotNull("Student2 should be assigned to group now", studentDto2.getGroup());
        assertEquals("Student2 should be assigned to that particular group", buildGroup().getTitle(), studentDto2.getGroup().getTitle());

        groupDto = studentConsumer.getStudentsGroupDtoByTitle(groupDto.getTitle());
        assertNotNull("The group should not be null", groupDto);
        assertEquals("There should be two members in the group", 2, groupDto.getMembers().size());
        assertTrue("Members of the group must have a persisted ID",
                studentDto1.getId().equals(groupDto.getMembers().get(0).getId())
                        || studentDto2.getId().equals(groupDto.getMembers().get(0).getId()));
        assertTrue("Members of the group must have a persisted ID",
                studentDto1.getId().equals(groupDto.getMembers().get(1).getId())
                        || studentDto2.getId().equals(groupDto.getMembers().get(1).getId()));

        List<String> studentIds = new ArrayList<>();
        Collections.addAll(studentIds, studentDto1.getId(), studentDto2.getId());

        adminStudentConsumer.removeStudentDtoAllFromGroup(groupDto.getId(), studentIds);

        groupDto = studentConsumer.retrieveStudentsGroupDto(groupDto.getId());
        assertNotNull("Group must not have been removed. Only its members", groupDto);
        logger.info("Group after removal: {}", groupDto);
        assertTrue("Group must have no more entries now", groupDto.getMembers().isEmpty());

        adminStudentConsumer.deleteStudentDto(studentDto1.getId());
        adminStudentConsumer.deleteStudentDto(studentDto2.getId());
        adminStudentConsumer.deleteStudentsGroupDto(groupDto.getId());

        adminPersonConsumer.deletePersonDto(personDto2.getId());
        removeTestPerson();
        removeTestGroup();
    }

    @Test
    public void groupSearchTest() {
        loginSystem();
        StudentsGroupDto groupDto = getGroupForTesting();

        assertNotNull("Group DTO should not be null after creation", groupDto);

        List<StudentsGroupDto> groupDtos = studentConsumer.searchStudentsGroupDtoAllByTitle("PIN");
        assertNotNull("There should be a non-null list of groups", groupDtos);
        assertEquals("There must be 1 result", 1, groupDtos.size());
        assertEquals("The found group should have a correct title", groupDto.getTitle(), groupDtos.get(0).getTitle());

        groupDtos = studentConsumer.searchStudentsGroupDtoAllByTitle("P*N13");
        assertNotNull("There should be a non-null list of groups", groupDtos);
        assertEquals("There must be 1 result", 1, groupDtos.size());
        assertEquals("The found group should have a correct title", groupDto.getTitle(), groupDtos.get(0).getTitle());

        groupDtos = studentConsumer.searchStudentsGroupDtoAllByTitle("*N13$");
        assertNotNull("There should be a non-null list of groups", groupDtos);
        assertEquals("There must be 1 result", 1, groupDtos.size());
        assertEquals("The found group should have a correct title", groupDto.getTitle(), groupDtos.get(0).getTitle());

        adminStudentConsumer.deleteStudentsGroupDto(groupDtos.get(0).getId());
    }

    @Test
    @Ignore(value = "Guest creation is quite complicated as it needs the whole infra created (school, discipline, etc.)")
    public void createLectureGuestTest() throws Exception {
    }

    @Test
    @Ignore(value = "Guest creation is quite complicated as it needs the whole infra created (school, discipline, etc.)")
    public void createLectureGuestByPersonIdAndLectureIdTest() throws Exception {
    }

    @Test
    @Ignore(value = "Guest creation is quite complicated as it needs the whole infra created (school, discipline, etc.)")
    public void createLectureGuestByPersonIdAndLectureIdentifierTest() throws Exception {
    }

    @Test
    @Ignore(value = "Guest creation is quite complicated as it needs the whole infra created (school, discipline, etc.)")
    public void deleteGuestByIdTest() throws Exception {
    }

}
