package com.netikras.studies.studentbuddy.api;

import com.netikras.studies.studentbuddy.api.location.FloorConsumer;
import com.netikras.studies.studentbuddy.api.location.LocationConsumer;
import com.netikras.studies.studentbuddy.api.timetable.AssignmentConsumer;
import com.netikras.studies.studentbuddy.api.timetable.LecturesConsumer;
import com.netikras.studies.studentbuddy.api.timetable.TestsConsumer;
import com.netikras.studies.studentbuddy.api.user.GenericPersonAwareTest;
import com.netikras.studies.studentbuddy.api.user.LecturerConsumer;
import com.netikras.studies.studentbuddy.api.user.StudentConsumer;
import com.netikras.studies.studentbuddy.api.user.mgmt.AdminLecturerConsumer;
import com.netikras.studies.studentbuddy.api.user.mgmt.AdminStudentConsumer;
import com.netikras.studies.studentbuddy.core.data.api.dto.PersonDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.location.AddressDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.location.BuildingDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.location.BuildingFloorDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.location.BuildingSectionDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.location.FloorLayoutDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.location.LectureRoomDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.AssignmentDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.DisciplineDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.DisciplineTestDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.LectureDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.LectureGuestDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.LecturerDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.SchoolDepartmentDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.SchoolDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.StudentDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.StudentsGroupDto;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class GenericSchoolAwareTest extends GenericPersonAwareTest {


    protected LocationConsumer locationConsumer;
    protected FloorConsumer floorConsumer;
    protected LecturerConsumer lecturerConsumer;
    protected StudentConsumer studentConsumer;
    protected AssignmentConsumer assignmentConsumer;
    protected LecturesConsumer lecturesConsumer;
    protected TestsConsumer testsConsumer;

    protected AdminLecturerConsumer adminLecturerConsumer;
    protected AdminStudentConsumer adminStudentConsumer;


    protected void initSchoolAware() {
        initPersonAware();
        locationConsumer = attachConsumer(new LocationConsumer());
        floorConsumer = attachConsumer(new FloorConsumer());
        lecturerConsumer = attachConsumer(new LecturerConsumer());
        studentConsumer = attachConsumer(new StudentConsumer());
        assignmentConsumer = attachConsumer(new AssignmentConsumer());
        testsConsumer = attachConsumer(new TestsConsumer());
        lecturesConsumer = attachConsumer(new LecturesConsumer());

        adminLecturerConsumer = attachConsumer(new AdminLecturerConsumer());
        adminStudentConsumer = attachConsumer(new AdminStudentConsumer());
    }

    protected <E> List<E> createList(E... items) {
        List<E> list = new ArrayList<>();

        if (items != null) {
            list.addAll(Arrays.asList(items));
        }

        return list;
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

    protected StudentsGroupDto buildGroupWithStudent(PersonDto personDto) {
        StudentsGroupDto groupDto = buildGroup();
        groupDto.setMembers(createList(buildStudent(personDto)));
        return groupDto;
    }

    protected StudentsGroupDto buildGroup(StudentDto... students) {
        StudentsGroupDto groupDto = buildGroup();
        groupDto.setMembers(createList(students));
        return groupDto;
    }

    protected SchoolDto buildSchool() {
        SchoolDto schoolDto = new SchoolDto();
        schoolDto.setTitle("Elementary testing school");
        return schoolDto;
    }

    protected SchoolDto buildSchool(SchoolDepartmentDto... departments) {
        SchoolDto schoolDto = buildSchool();
        schoolDto.setDepartments(createList(departments));
        return schoolDto;
    }

    protected SchoolDepartmentDto buildDepartment() {
        SchoolDepartmentDto departmentDto = new SchoolDepartmentDto();
        departmentDto.setTitle("Testing department #1");
        return departmentDto;
    }

    protected SchoolDepartmentDto buildDepartment(BuildingDto... buildings) {
        SchoolDepartmentDto departmentDto = buildDepartment();
        departmentDto.setBuildings(createList(buildings));
        return departmentDto;
    }

    protected BuildingDto buildBuilding() {
        BuildingDto buildingDto = new BuildingDto();
        buildingDto.setTitle("Main building");
        return buildingDto;
    }

    protected BuildingDto buildBuilding(AddressDto addressDto, BuildingSectionDto... sectionDtos) {
        BuildingDto buildingDto = buildBuilding();
        buildingDto.setAddress(addressDto);
        buildingDto.setBuildingSections(createList(sectionDtos));
        return buildingDto;
    }

    protected BuildingSectionDto buidBuildingSection() {
        BuildingSectionDto sectionDto = new BuildingSectionDto();
        sectionDto.setTitle("Main section");
        return sectionDto;
    }

    protected BuildingSectionDto buidBuildingSection(BuildingFloorDto... floorDtos) {
        BuildingSectionDto sectionDto = buidBuildingSection();
        sectionDto.setFloors(createList(floorDtos));
        return sectionDto;
    }

    protected BuildingFloorDto buildFloor() {
        BuildingFloorDto floorDto = new BuildingFloorDto();
        floorDto.setTitle("Best floor");
        floorDto.setNumber("3");
        return floorDto;
    }

    protected BuildingFloorDto buildFloor(FloorLayoutDto layout, LectureRoomDto... rooms) {
        BuildingFloorDto floorDto = buildFloor();
        floorDto.setLectureRooms(createList(rooms));
        floorDto.setLayouts(createList(layout));
        return floorDto;
    }

    protected LectureRoomDto buildRoom() {
        LectureRoomDto roomDto = new LectureRoomDto();
        roomDto.setNumber("7");
        roomDto.setTitle("math-g33ks room");
        return roomDto;
    }

    protected FloorLayoutDto buildFloorLayout() {
        FloorLayoutDto layoutDto = new FloorLayoutDto();
        layoutDto.setActive(true);
        return layoutDto;
    }

    protected FloorLayoutDto buildFloorLayout(BuildingFloorDto floor, byte[] data) {
        FloorLayoutDto layoutDto = buildFloorLayout();
        layoutDto.setFloor(floor);
        layoutDto.setFloorMap(data);
        return layoutDto;
    }

    protected DisciplineDto buildDiscipline() {
        DisciplineDto disciplineDto = new DisciplineDto();
        disciplineDto.setTitle("Math");
        disciplineDto.setDescription("The best ever!");
        return disciplineDto;
    }

    protected LecturerDto buildLecturer() {
        LecturerDto lecturerDto = new LecturerDto();
        lecturerDto.setDegree("Master");
        return lecturerDto;
    }

    protected LecturerDto buildLecturer(PersonDto personDto, DisciplineDto... disciplines) {
        LecturerDto lecturerDto = buildLecturer();
        lecturerDto.setPerson(personDto);
        lecturerDto.setDisciplines(createList(disciplines));
        return lecturerDto;
    }

    protected LectureDto buildLecture() {
        LectureDto lectureDto = new LectureDto();
        lectureDto.setStartsOn(new Date());
        lectureDto.setEndsOn(new Date(System.currentTimeMillis() + 1000 * 60 * 45));

        return lectureDto;
    }

    protected LectureDto buildLecture(DisciplineDto disciplineDto, StudentsGroupDto groupDto, LectureRoomDto roomDto) {
        LectureDto lectureDto = buildLecture();
        lectureDto.setRoom(roomDto);
        lectureDto.setStudentsGroup(groupDto);
        lectureDto.setDiscipline(disciplineDto);

        return lectureDto;
    }

    protected AssignmentDto buildAssignment() {
        AssignmentDto assignmentDto = new AssignmentDto();
        assignmentDto.setDue(new Date());
        return assignmentDto;
    }

    protected AssignmentDto buildAssignment(DisciplineDto disciplineDto) {
        AssignmentDto assignmentDto = buildAssignment();
        assignmentDto.setDiscipline(disciplineDto);
        return assignmentDto;
    }

    protected DisciplineTestDto buildDisciplineTest() {
        DisciplineTestDto testDto = new DisciplineTestDto();
        testDto.setStartsOn(new Date());
        return testDto;
    }

    protected DisciplineTestDto buildDisciplineTest(DisciplineDto disciplineDto, LectureDto lectureDto) {
        DisciplineTestDto testDto = buildDisciplineTest();
        testDto.setDiscipline(disciplineDto);
        testDto.setLecture(lectureDto);
        return testDto;
    }

    protected LectureGuestDto buildGuest() {
        LectureGuestDto guestDto = new LectureGuestDto();
        return guestDto;
    }

    protected LectureGuestDto buildGuest(LectureDto lectureDto, PersonDto personDto) {
        LectureGuestDto guestDto = buildGuest();
        guestDto.setPerson(personDto);
        guestDto.setLecture(lectureDto);
        return guestDto;
    }


    //
    //
    // get<...>ForTesting()
    //
    //


    protected StudentsGroupDto getNewGroupForTesting() {
        loginSystem();
        StudentsGroupDto dummyGroup = buildGroup();
        StudentsGroupDto groupDto = studentConsumer.getGroupByTitle(dummyGroup.getTitle());
        if (groupDto != null) {
            adminStudentConsumer.deleteStudentGroupById(groupDto.getId());
            groupDto = studentConsumer.getGroupByTitle(dummyGroup.getTitle());
        }

        assertNull("There should be no such group in the database now", groupDto);
        groupDto = adminStudentConsumer.createStudentGroup(dummyGroup);
        assertNotNull("New group should have been created", groupDto);

        return groupDto;
    }

    protected void removeTestGroup() {
        loginSystem();
        StudentsGroupDto groupDto = studentConsumer.getGroupByTitle(buildGroup().getTitle());
        if (groupDto != null) {
            adminStudentConsumer.deleteStudentGroupById(groupDto.getId());
        }
    }

    protected StudentDto getNewStudentForTesting() {
        PersonDto personDto = getPersonForTesting();
        StudentsGroupDto groupDto = getNewGroupForTesting();
        StudentDto dummyStudent = buildStudent(personDto);
        dummyStudent.setGroup(groupDto);
        StudentDto studentDto = studentConsumer.getStudentByPersonId(personDto.getId());

        if (studentDto != null) {
            adminStudentConsumer.deleteStudentById(studentDto.getId());
            studentDto = studentConsumer.getStudentById(studentDto.getId());
        }

        assertNull("There should be no such student in the database now", studentDto);
        studentDto = adminStudentConsumer.createStudent(dummyStudent);
        assertNotNull("New student should have been created", studentDto);

        return studentDto;
    }

    protected SchoolDto getNewSchoolForTesting() {
        SchoolDto schoolDto = buildSchool();



        return schoolDto;
    }


}
