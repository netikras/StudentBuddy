package com.netikras.studies.studentbuddy.api.user.mgmt;


import com.netikras.studies.studentbuddy.api.user.GenericPersonAwareTest;
import com.netikras.studies.studentbuddy.api.user.StudentConsumer;
import com.netikras.studies.studentbuddy.core.data.api.dto.PersonDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.StudentDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.StudentsGroupDto;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class AdminStudentConsumerTest extends GenericPersonAwareTest {
    private static final Logger logger = LoggerFactory.getLogger(AdminPersonConsumerTest.class);

    private AdminStudentConsumer adminStudentConsumer;
    private StudentConsumer studentConsumer;


    @Before
    public void initPersonAware() {
        super.initPersonAware();
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

    protected void removeTestGroup() {
        loginSystem();
        StudentsGroupDto groupDto = studentConsumer.getGroupByTitle(buildGroup().getTitle());
        if (groupDto != null) {
            adminStudentConsumer.deleteStudentGroupById(groupDto.getId());
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
        removeTestPerson();
        removeTestGroup();
    }

    @Test
    public void deleteStudentByIdTest() throws Exception {
        loginSystem();
        PersonDto personDto = getPersonForTesting();
        StudentDto studentDto = studentConsumer.getStudentByPersonId(personDto.getId());
        if (studentDto == null) {
            studentDto = buildStudent(personDto);
            studentDto.setGroup(getGroupForTesting());
            studentDto = adminStudentConsumer.createStudent(studentDto);

        }

        assertNotNull("Student should be created by now", studentDto);

        adminStudentConsumer.deleteStudentById(studentDto.getId());
        removeTestPerson();
        removeTestGroup();

        studentDto = studentConsumer.getStudentByPersonId(personDto.getId());
        assertNull("Student should be deleted by now", studentDto);
        removeTestPerson();
        removeTestGroup();
    }

    @Test
    public void createStudentGroupTest() throws Exception {
        loginSystem();
        StudentsGroupDto groupDto = studentConsumer.getGroupByTitle(buildGroup().getTitle());
        if (groupDto != null) {
            adminStudentConsumer.deleteGuestById(groupDto.getId());
        }

        assertNull("There should be no test group created in the DB by now", groupDto);

        groupDto = adminStudentConsumer.createStudentGroup(buildGroup());
        assertNotNull("New group should have been created", groupDto);
        assertEquals("New group should have given title", buildGroup().getTitle(), groupDto.getTitle());

        adminStudentConsumer.deleteStudentGroupById(groupDto.getId());
        removeTestPerson();
        removeTestGroup();
    }

    @Test
    public void deleteStudentGroupByIdTest() throws Exception {
        loginSystem();
        StudentsGroupDto groupDto = studentConsumer.getGroupByTitle(buildGroup().getTitle());
        if (groupDto == null) {
            groupDto = adminStudentConsumer.createStudentGroup(buildGroup());
        }

        assertNotNull("Group DTO must not be null at this point", groupDto);
        adminStudentConsumer.deleteStudentGroupById(groupDto.getId());
        groupDto = studentConsumer.getGroupByTitle(buildGroup().getTitle());
        assertNull("Group must have been deleted", groupDto);
        removeTestPerson();
        removeTestGroup();
    }

    @Test
    public void addStudentToGroupTest() throws Exception {
        loginSystem();
        StudentDto studentDto = studentConsumer.getStudentByPersonId(getPersonForTesting().getId());
        if (studentDto != null) {
            adminStudentConsumer.deleteStudentById(studentDto.getId());
        }
        assertNull("There should be no such student in the database atm", studentDto);

        StudentsGroupDto groupDto = studentConsumer.getGroupByTitle(buildGroup().getTitle());
        if (groupDto != null) {
            adminStudentConsumer.deleteStudentGroupById(groupDto.getId());
            groupDto = studentConsumer.getGroupById(groupDto.getId());
        }
        assertNull("Group should be removed now", groupDto);

        groupDto = adminStudentConsumer.createStudentGroup(buildGroup());
        assertNotNull("There should be a student group in the DB by now", groupDto);

        studentDto = adminStudentConsumer.createStudent(buildStudent(getPersonForTesting()));
        assertNotNull("Student should be created at this point", studentDto);
        assertNull("Student should not be assigned to any group", studentDto.getGroup());

        adminStudentConsumer.addStudentToGroup(groupDto.getId(), studentDto.getId());

        studentDto = studentConsumer.getStudentById(studentDto.getId());
        assertNotNull("Student shall not be deleted yet", studentDto);
        assertNotNull("Student should be assigned to group now", studentDto.getGroup());
        assertEquals("Student should be assigned to that particular group", buildGroup().getTitle(), studentDto.getGroup().getTitle());

        groupDto = studentConsumer.getGroupByTitle(groupDto.getTitle());
        assertNotNull("The group should not be null", groupDto);
        assertEquals("There should be only one member in the group", 1, groupDto.getMembers().size());
        assertEquals("Member of the group must have a persisted ID", studentDto.getId(), groupDto.getMembers().get(0).getId());

        adminStudentConsumer.deleteStudentById(studentDto.getId());
        adminStudentConsumer.deleteStudentGroupById(groupDto.getId());
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
        PersonDto personDto2 = personConsumer.getByCode(person2.getPersonalCode());
        if (personDto2 == null) {
            personDto2 = adminPersonConsumer.createPerson(person2);
        }

        StudentDto studentDto1 = studentConsumer.getStudentByPersonId(personDto1.getId());
        StudentDto studentDto2 = studentConsumer.getStudentByPersonId(personDto2.getId());
        if (studentDto1 != null) {
            adminStudentConsumer.deleteStudentById(studentDto1.getId());
            studentDto1 = studentConsumer.getStudentByPersonId(personDto1.getId());
        }
        if (studentDto2 != null) {
            adminStudentConsumer.deleteStudentById(studentDto2.getId());
            studentDto2 = studentConsumer.getStudentByPersonId(personDto2.getId());
        }
        assertNull("There should be no such student1 in the database atm", studentDto1);
        assertNull("There should be no such student2 in the database atm", studentDto2);

        StudentsGroupDto groupDto = studentConsumer.getGroupByTitle(buildGroup().getTitle());
        if (groupDto != null) {
            adminStudentConsumer.deleteStudentGroupById(groupDto.getId());
            groupDto = studentConsumer.getGroupById(groupDto.getId());
        }
        assertNull("Group should be removed now", groupDto);

        groupDto = adminStudentConsumer.createStudentGroup(buildGroup());
        assertNotNull("There should be a student group in the DB by now", groupDto);


        studentDto1 = adminStudentConsumer.createStudent(buildStudent(personDto1));
        assertNotNull("Student1 should be created at this point", studentDto1);
        assertNull("Student1 should not be assigned to any group", studentDto1.getGroup());

        studentDto2 = adminStudentConsumer.createStudent(buildStudent(personDto2));
        assertNotNull("Student2 should be created at this point", studentDto2);
        assertNull("Student2 should not be assigned to any group", studentDto2.getGroup());

        adminStudentConsumer.addAllStudentsToGroup(groupDto.getId(), studentDto1.getId(), studentDto2.getId());

        studentDto1 = studentConsumer.getStudentById(studentDto1.getId());
        assertNotNull("Student1 shall not be deleted yet", studentDto1);
        assertNotNull("Student1 should be assigned to group now", studentDto1.getGroup());
        assertEquals("Student1 should be assigned to that particular group", buildGroup().getTitle(), studentDto1.getGroup().getTitle());

        studentDto2 = studentConsumer.getStudentById(studentDto2.getId());
        assertNotNull("Student2 shall not be deleted yet", studentDto2);
        assertNotNull("Student2 should be assigned to group now", studentDto2.getGroup());
        assertEquals("Student2 should be assigned to that particular group", buildGroup().getTitle(), studentDto2.getGroup().getTitle());

        groupDto = studentConsumer.getGroupByTitle(groupDto.getTitle());
        assertNotNull("The group should not be null", groupDto);
        assertEquals("There should be two members in the group", 2, groupDto.getMembers().size());
        assertTrue("Members of the group must have a persisted ID",
                studentDto1.getId().equals(groupDto.getMembers().get(0).getId())
                        || studentDto2.getId().equals(groupDto.getMembers().get(0).getId()));
        assertTrue("Members of the group must have a persisted ID",
                studentDto1.getId().equals(groupDto.getMembers().get(1).getId())
                        || studentDto2.getId().equals(groupDto.getMembers().get(1).getId()));

        adminStudentConsumer.removeAllStudentsFromGroup(groupDto.getId(), studentDto1.getId(), studentDto2.getId());

        adminStudentConsumer.deleteStudentById(studentDto1.getId());
        adminStudentConsumer.deleteStudentById(studentDto2.getId());
        adminStudentConsumer.deleteStudentGroupById(groupDto.getId());

        adminPersonConsumer.deletePersonById(personDto2.getId());
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
        PersonDto personDto2 = personConsumer.getByCode(person2.getPersonalCode());
        if (personDto2 == null) {
            personDto2 = adminPersonConsumer.createPerson(person2);
        }

        StudentDto studentDto1 = studentConsumer.getStudentByPersonId(personDto1.getId());
        StudentDto studentDto2 = studentConsumer.getStudentByPersonId(personDto2.getId());
        if (studentDto1 != null) {
            adminStudentConsumer.deleteStudentById(studentDto1.getId());
            studentDto1 = studentConsumer.getStudentByPersonId(personDto1.getId());
        }
        if (studentDto2 != null) {
            adminStudentConsumer.deleteStudentById(studentDto2.getId());
            studentDto2 = studentConsumer.getStudentByPersonId(personDto2.getId());
        }
        assertNull("There should be no such student1 in the database atm", studentDto1);
        assertNull("There should be no such student2 in the database atm", studentDto2);

        StudentsGroupDto groupDto = studentConsumer.getGroupByTitle(buildGroup().getTitle());
        if (groupDto != null) {
            adminStudentConsumer.deleteStudentGroupById(groupDto.getId());
            groupDto = studentConsumer.getGroupById(groupDto.getId());
        }
        assertNull("Group should be removed now", groupDto);

        groupDto = adminStudentConsumer.createStudentGroup(buildGroup());
        assertNotNull("There should be a student group in the DB by now", groupDto);


        studentDto1 = adminStudentConsumer.createStudent(buildStudent(personDto1));
        assertNotNull("Student1 should be created at this point", studentDto1);
        assertNull("Student1 should not be assigned to any group", studentDto1.getGroup());

        studentDto2 = adminStudentConsumer.createStudent(buildStudent(personDto2));
        assertNotNull("Student2 should be created at this point", studentDto2);
        assertNull("Student2 should not be assigned to any group", studentDto2.getGroup());

        adminStudentConsumer.addStudentToGroup(groupDto.getId(), studentDto1.getId());
        adminStudentConsumer.addStudentToGroup(groupDto.getId(), studentDto2.getId());

        studentDto1 = studentConsumer.getStudentById(studentDto1.getId());
        assertNotNull("Student1 shall not be deleted yet", studentDto1);
        assertNotNull("Student1 should be assigned to group now", studentDto1.getGroup());
        assertEquals("Student1 should be assigned to that particular group", buildGroup().getTitle(), studentDto1.getGroup().getTitle());

        studentDto2 = studentConsumer.getStudentById(studentDto2.getId());
        assertNotNull("Student2 shall not be deleted yet", studentDto2);
        assertNotNull("Student2 should be assigned to group now", studentDto2.getGroup());
        assertEquals("Student2 should be assigned to that particular group", buildGroup().getTitle(), studentDto2.getGroup().getTitle());

        groupDto = studentConsumer.getGroupByTitle(groupDto.getTitle());
        assertNotNull("The group should not be null", groupDto);
        assertEquals("There should be two members in the group", 2, groupDto.getMembers().size());
        assertTrue("Members of the group must have a persisted ID",
                studentDto1.getId().equals(groupDto.getMembers().get(0).getId())
                        || studentDto2.getId().equals(groupDto.getMembers().get(0).getId()));
        assertTrue("Members of the group must have a persisted ID",
                studentDto1.getId().equals(groupDto.getMembers().get(1).getId())
                        || studentDto2.getId().equals(groupDto.getMembers().get(1).getId()));


        adminStudentConsumer.removeAllStudentsFromGroup(groupDto.getId(), studentDto1.getId(), studentDto2.getId());

        groupDto = studentConsumer.getGroupById(groupDto.getId());
        assertNotNull("Group must not have been removed. Only its members", groupDto);
        logger.info("Group after removal: {}", groupDto);
        assertTrue("Group must have no more entries now", groupDto.getMembers().isEmpty());

        adminStudentConsumer.deleteStudentById(studentDto1.getId());
        adminStudentConsumer.deleteStudentById(studentDto2.getId());
        adminStudentConsumer.deleteStudentGroupById(groupDto.getId());

        adminPersonConsumer.deletePersonById(personDto2.getId());
        removeTestPerson();
        removeTestGroup();
    }

    @Test
    public void groupSearchTest() {
        loginSystem();
        StudentsGroupDto groupDto = getGroupForTesting();

        assertNotNull("Group DTO should not be null after creation", groupDto);

        List<StudentsGroupDto> groupDtos = studentConsumer.searchAllGroupsByTitle("PIN");
        assertNotNull("There should be a non-null list of groups", groupDtos);
        assertEquals("There must be 1 result", 1, groupDtos.size());
        assertEquals("The found group should have a correct title", groupDto.getTitle(), groupDtos.get(0).getTitle());

        groupDtos = studentConsumer.searchAllGroupsByTitle("P*N13");
        assertNotNull("There should be a non-null list of groups", groupDtos);
        assertEquals("There must be 1 result", 1, groupDtos.size());
        assertEquals("The found group should have a correct title", groupDto.getTitle(), groupDtos.get(0).getTitle());

        groupDtos = studentConsumer.searchAllGroupsByTitle("*N13$");
        assertNotNull("There should be a non-null list of groups", groupDtos);
        assertEquals("There must be 1 result", 1, groupDtos.size());
        assertEquals("The found group should have a correct title", groupDto.getTitle(), groupDtos.get(0).getTitle());

        adminStudentConsumer.deleteStudentGroupById(groupDtos.get(0).getId());
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
