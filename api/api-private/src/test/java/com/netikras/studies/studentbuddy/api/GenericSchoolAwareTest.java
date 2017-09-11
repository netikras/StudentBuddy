package com.netikras.studies.studentbuddy.api;

import com.netikras.studies.studentbuddy.api.location.generated.FloorApiConsumer;
import com.netikras.studies.studentbuddy.api.location.generated.LocationApiConsumer;
import com.netikras.studies.studentbuddy.api.location.generated.SchoolApiConsumer;
import com.netikras.studies.studentbuddy.api.timetable.controller.generated.AssignmentApiConsumer;
import com.netikras.studies.studentbuddy.api.timetable.controller.generated.LecturesApiConsumer;
import com.netikras.studies.studentbuddy.api.timetable.controller.generated.TestsApiConsumer;
import com.netikras.studies.studentbuddy.api.user.GenericPersonAwareTest;
import com.netikras.studies.studentbuddy.api.user.generated.LecturerApiConsumer;
import com.netikras.studies.studentbuddy.api.user.generated.StudentApiConsumer;
import com.netikras.studies.studentbuddy.api.user.mgmt.generated.AdminLecturerApiConsumer;
import com.netikras.studies.studentbuddy.api.user.mgmt.generated.AdminStudentApiConsumer;
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
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.netikras.tools.common.security.IntegrityUtils.isNullOrEmpty;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

public class GenericSchoolAwareTest extends GenericPersonAwareTest {


    protected LocationApiConsumer locationConsumer;
    protected FloorApiConsumer floorConsumer;
    protected LecturerApiConsumer lecturerConsumer;
    protected StudentApiConsumer studentConsumer;
    protected AssignmentApiConsumer assignmentConsumer;
    protected LecturesApiConsumer lecturesConsumer;
    protected TestsApiConsumer testsConsumer;
    protected SchoolApiConsumer schoolConsumer;

    protected AdminLecturerApiConsumer adminLecturerConsumer;
    protected AdminStudentApiConsumer adminStudentConsumer;

    @Before
    public void initSchoolAware() {
        initPersonAware();
        locationConsumer = attachConsumer(new LocationApiConsumer());
        floorConsumer = attachConsumer(new FloorApiConsumer());
        lecturerConsumer = attachConsumer(new LecturerApiConsumer());
        studentConsumer = attachConsumer(new StudentApiConsumer());
        assignmentConsumer = attachConsumer(new AssignmentApiConsumer());
        testsConsumer = attachConsumer(new TestsApiConsumer());
        lecturesConsumer = attachConsumer(new LecturesApiConsumer());
        schoolConsumer = attachConsumer(new SchoolApiConsumer());

        adminLecturerConsumer = attachConsumer(new AdminLecturerApiConsumer());
        adminStudentConsumer = attachConsumer(new AdminStudentApiConsumer());
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
        buildingDto.setAddress(buildAddress());
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
        sectionDto.setAddress(buildAddress());
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
        layoutDto.setFloorMap(new byte[]{1, 0, 1, 4, 3, 84});
        return layoutDto;
    }

    protected FloorLayoutDto buildFloorLayout(BuildingFloorDto floor, byte[] data) {
        FloorLayoutDto layoutDto = buildFloorLayout();
        layoutDto.setFloor(floor);
        layoutDto.setFloorMap(data);
        return layoutDto;
    }

    protected AddressDto buildAddress() {
        AddressDto addressDto = new AddressDto();

        addressDto.setBuildingNo("12");
        addressDto.setCity("Liepāja");
        addressDto.setCountry("Latvia");
        addressDto.setStreet("Jūras iela");
        addressDto.setPostalCode("LV-3401");

        return addressDto;
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

    protected LectureDto buildLecture(DisciplineDto disciplineDto, StudentsGroupDto groupDto, LectureRoomDto roomDto, LecturerDto lecturerDto) {
        LectureDto lectureDto = buildLecture();
        lectureDto.setRoom(roomDto);
        lectureDto.setStudentsGroup(groupDto);
        lectureDto.setDiscipline(disciplineDto);
        lectureDto.setLecturer(lecturerDto);

        return lectureDto;
    }

    protected AssignmentDto buildAssignment() {
        AssignmentDto assignmentDto = new AssignmentDto();
        assignmentDto.setDescription("Solve math problems on page #449");
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
        testDto.setDescription("Last quarter test");
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
        StudentsGroupDto groupDto = studentConsumer.getStudentsGroupDtoByTitle(dummyGroup.getTitle());
        if (groupDto != null) {
            adminStudentConsumer.deleteStudentsGroupDto(groupDto.getId());
            groupDto = studentConsumer.getStudentsGroupDtoByTitle(dummyGroup.getTitle());
        }

        assertNull("There should be no such group in the database now", groupDto);
        groupDto = adminStudentConsumer.createStudentsGroupDto(dummyGroup);
        assertNotNull("New group should have been created", groupDto);

        return groupDto;
    }

    protected void removeTestGroup() {
        loginSystem();
        StudentsGroupDto groupDto = studentConsumer.getStudentsGroupDtoByTitle(buildGroup().getTitle());
        if (groupDto != null) {
            adminStudentConsumer.deleteStudentsGroupDto(groupDto.getId());
        }
    }

    protected StudentDto getNewStudentForTesting() {
        PersonDto personDto = getPersonForTesting();
        StudentsGroupDto groupDto = getNewGroupForTesting();
        StudentDto dummyStudent = buildStudent(personDto);
        dummyStudent.setGroup(groupDto);
        List<StudentDto> studentDtos = studentConsumer.getStudentDtoAllByPersonId(personDto.getId());

        if (!isNullOrEmpty(studentDtos)) {
            studentDtos.forEach(studentDto -> adminStudentConsumer.deleteStudentDto(studentDto.getId()));
            studentDtos = studentConsumer.getStudentDtoAllByPersonId(personDto.getId());
        }

        assertNull("There should be no such student in the database now", studentDtos);
        StudentDto studentDto = adminStudentConsumer.createStudentDto(dummyStudent);
        assertNotNull("New student should have been created", studentDto);

        return studentDto;
    }

    protected SchoolDto getNewSchoolForTesting() {
        SchoolDto dummySchool = buildSchool();
        SchoolDto schoolDto = null;
        List<SchoolDto> schools = schoolConsumer.searchSchoolDtoAllByTitle(dummySchool.getTitle());

        if (schools != null && !schools.isEmpty()) {
            schools.forEach(dto -> schoolConsumer.deleteSchoolDto(dto.getId()));
        }

        schoolDto = schoolConsumer.createSchoolDto(dummySchool);

        assertNotNull("School for testing should not be null", schoolDto);
        return schoolDto;
    }

    protected SchoolDto getNewSchoolWithDepartmentForTesting() {
        SchoolDto schoolDto = getNewSchoolForTesting();
        SchoolDepartmentDto departmentDto = buildDepartment(getNewBuildingForTesting());
        departmentDto.setSchool(schoolDto);

        List<SchoolDepartmentDto> departmentDtos = schoolConsumer.searchSchoolDepartmentDtoAllByTitle(departmentDto.getTitle());
        if (departmentDtos != null && !departmentDtos.isEmpty()) {
            for (SchoolDepartmentDto dto : departmentDtos) {
                schoolConsumer.deleteSchoolDepartmentDto(dto.getId());
            }
        }

        departmentDto = schoolConsumer.createSchoolDepartmentDto(departmentDto);
        schoolDto = schoolConsumer.retrieveSchoolDto(schoolDto.getId());

        assertNotNull("School for testing should not be null", schoolDto);

        assertNotNull("School must have department", schoolDto.getDepartments());
        assertFalse("There should be at least one department in the list", schoolDto.getDepartments().isEmpty());

        return schoolDto;
    }

    protected BuildingDto getNewBuildingForTesting() {
        BuildingDto dummyBuilding = buildBuilding();
        BuildingDto buildingDto = null;
        List<BuildingDto> buildingDtos = locationConsumer.searchBuildingDtoAllByTitle(dummyBuilding.getTitle());

        if (buildingDtos != null && !buildingDtos.isEmpty()) {
            buildingDtos.forEach(dto -> locationConsumer.deleteBuildingDto(dto.getId()));
        }

        buildingDto = locationConsumer.createBuildingDto(dummyBuilding);
        assertNotNull("Newly created building must be non-null", buildingDto);
        assertEquals("Newly created building must have provided title", dummyBuilding.getTitle(), buildingDto.getTitle());

        return buildingDto;
    }


    protected DisciplineDto getNewDisciplineForTesting() {
        DisciplineDto dummyDiscipline = buildDiscipline();
        DisciplineDto disciplineDto = null;

        List<DisciplineDto> disciplineDtos = schoolConsumer.searchDisciplineDtoAllByTitle(dummyDiscipline.getTitle());
        if (disciplineDtos != null && !disciplineDtos.isEmpty()) {
            disciplineDtos.forEach(dto -> schoolConsumer.deleteDisciplineDto(dto.getId()));
        }

        dummyDiscipline.setSchool(getNewSchoolWithDepartmentForTesting());

        disciplineDto = schoolConsumer.createDisciplineDto(dummyDiscipline);

        assertNotNull("Newly created discipline must be non-null", disciplineDto);

        return disciplineDto;
    }

    protected LecturerDto getNewLecturerForTesting() {
        LecturerDto dummyLecturer = buildLecturer(getPersonForTesting(), getNewDisciplineForTesting());
        List<LecturerDto> lecturerDtos = lecturerConsumer.getLecturerDtoAllByPersonId(dummyLecturer.getPerson().getId());

        if (!isNullOrEmpty(lecturerDtos)) {
            lecturerDtos.forEach(lecturerDto -> adminLecturerConsumer.deleteLecturerDto(lecturerDto.getId()));
        }

        LecturerDto lecturerDto = adminLecturerConsumer.createLecturerDto(dummyLecturer);
        assertNotNull("Newly created lecturer must be non-null", lecturerDto);

        return lecturerDto;
    }

    protected LectureDto getNewLectureForTesting() {
        SchoolDto schoolDto = getNewSchoolWithDepartmentForTesting();
        DisciplineDto disciplineDto = buildDiscipline();
        disciplineDto.setSchool(schoolDto);
        disciplineDto = schoolConsumer.createDisciplineDto(disciplineDto);
        assertNotNull("Discipline must be created by now", disciplineDto);

        StudentsGroupDto groupDto = getNewGroupForTesting();

        List<BuildingDto> buildingDtos = locationConsumer.searchBuildingDtoAllByTitle("^" + buildBuilding().getTitle() + "$");

        LectureRoomDto roomDto = null;
//        roomDto = schoolDto.getDepartments().get(0).getBuildings().get(0).getBuildingSections().get(0).getFloors().get(0).getLectureRooms().get(0);
        BuildingFloorDto floorDto =
                floorConsumer.retrieveBuildingFloorDto(buildingDtos.get(0).getBuildingSections().get(0).getFloors().get(0).getId());

        roomDto = floorDto.getLectureRooms().get(0);
        LecturerDto lecturerDto = buildLecturer(getPersonForTesting(), disciplineDto);
        lecturerDto = adminLecturerConsumer.createLecturerDto(lecturerDto);
        assertNotNull("Newly created lecturer must be non-null", lecturerDto);
        LectureDto dummyLecture = buildLecture(disciplineDto, groupDto, roomDto, lecturerDto);
        LectureDto lectureDto = null;

        lectureDto = lecturesConsumer.createLectureDto(dummyLecture);
        assertNotNull("Newly created lecture should be non-null", lectureDto);

        return lectureDto;
    }

    protected LectureGuestDto getNewGuestForTesting() {
        LectureDto lectureDto = getNewLectureForTesting();
        LectureGuestDto guestDto = buildGuest(lectureDto, getPersonForTesting());
        guestDto = adminStudentConsumer.createLectureGuestDto(guestDto);

        assertNotNull("Newly created guest must be non-null", guestDto);

        return guestDto;
    }

    protected void removeTestLecture(LectureDto lectureDto) {
        lecturesConsumer.deleteLectureDto(lectureDto.getId());
    }

    protected void removeTestLecturer(LecturerDto lecturerDto) {
        adminLecturerConsumer.deleteLecturerDto(lecturerDto.getId());
    }

    protected void removeTestGuest(LectureGuestDto guestDto) {
        adminStudentConsumer.deleteLectureGuestDto(guestDto.getId());
    }

    protected void deleteTestDiscipline(DisciplineDto disciplineDto) {
        schoolConsumer.deleteDisciplineDto(disciplineDto.getId());
    }

    protected void deleteTestSchool() {
        SchoolDto dummySchool = buildSchool();
        List<SchoolDto> schoolDtos = schoolConsumer.getSchoolDtoAll();
        schoolDtos.forEach(dto -> {
            if (dto.getTitle().equals(dummySchool.getTitle())) {
                if (!isNullOrEmpty(dto.getDepartments())) {
                    dto.getDepartments().forEach(this::deleteTestDepartment);

                }
                schoolConsumer.deleteSchoolDto(dto.getId());
            }
        });
    }

    protected void deleteTestDepartment(SchoolDepartmentDto departmentDto) {
        if (!isNullOrEmpty(departmentDto.getBuildings())) {
            departmentDto.getBuildings().forEach(this::deleteTestBuilding);
        }
        schoolConsumer.deleteSchoolDepartmentDto(departmentDto.getId());
    }


    protected void deleteTestBuilding(BuildingDto buildingDto) {
        if (!isNullOrEmpty(buildingDto.getBuildingSections())) {

        }

    }


    @Test
    public void purgeSchools() {
        loginSystem();

        List<SchoolDto> schoolDtos = schoolConsumer.searchSchoolDtoAllByTitle(buildSchool().getTitle());
        if (!isNullOrEmpty(schoolDtos)) {
            schoolDtos.forEach(schoolDto -> schoolConsumer.purgeSchoolDto(schoolDto.getId()));
            schoolDtos = schoolConsumer.searchSchoolDtoAllByTitle(buildSchool().getTitle());
        }

        if (!isNullOrEmpty(schoolDtos)) {
            fail("Schools should have been purged");
        }
    }


    @Test
    public void purgeBuildings() {
        loginSystem();

        List<BuildingDto> buildingDtos = locationConsumer.searchBuildingDtoAllByTitle(buildBuilding().getTitle());
        if (!isNullOrEmpty(buildingDtos)) {
            buildingDtos.forEach(buildingDto -> locationConsumer.purgeBuildingDto(buildingDto.getId()));
            buildingDtos = locationConsumer.searchBuildingDtoAllByTitle(buildBuilding().getTitle());
        }

        if (!isNullOrEmpty(buildingDtos)) {
            fail("Buildings should have been purged");
        }
    }


    protected PersonDto purgePersonByIdentifier(String identifier) {
        PersonDto personDto = personConsumer.getPersonDtoByIdentifier(identifier);
        if (personDto != null) {
            adminPersonConsumer.purgePersonDto(personDto.getId());
            personDto = personConsumer.retrievePersonDto(personDto.getId());
        }

        return personDto;
    }



    @Test
    public void purgePeople() {
        loginSystem();

        PersonDto personDto = purgePersonByIdentifier(buildPerson().getIdentification());
        assertNull("Person should have been purged", personDto);


        personDto = purgePersonByIdentifier("00000001");
        assertNull("Person should have been purged", personDto);


        personDto = purgePersonByIdentifier("00000002");
        assertNull("Person should have been purged", personDto);


        personDto = purgePersonByIdentifier("00000003");
        assertNull("Person should have been purged", personDto);

    }

    @Test
    public void purgeAll() {
        purgeBuildings();
        purgePeople();
        purgeSchools();
    }

    @Test
    public void createInfraTest() {
        loginSystem();

        purgeAll();

        BuildingDto buildingDto = buildBuilding();
        buildingDto = locationConsumer.createBuildingDto(buildingDto);
        assertNotNull("Building should have been created", buildingDto);

        BuildingSectionDto sectionDto = buidBuildingSection();
        sectionDto.setBuilding(buildingDto);
        sectionDto = locationConsumer.createBuildingSectionDto(sectionDto);
        assertNotNull("Building section should have been created", sectionDto);

        BuildingFloorDto floorDto = buildFloor();
        floorDto.setBuilding(buildingDto);
        floorDto.setBuildingSection(sectionDto);
        floorDto = floorConsumer.createBuildingFloorDto(floorDto);
        assertNotNull("Floor should have been created", floorDto);


        SchoolDto schoolDto = buildSchool();
        schoolDto = schoolConsumer.createSchoolDto(schoolDto);
        assertNotNull("School should have been created", schoolDto);

        SchoolDepartmentDto departmentDto = buildDepartment();
        departmentDto.setSchool(schoolDto);
        departmentDto.addBuilding(buildingDto);
        departmentDto = schoolConsumer.createSchoolDepartmentDto(departmentDto);
        assertNotNull("School department should have been created", departmentDto);

        LectureRoomDto roomDto = buildRoom();
        roomDto.setSchool(schoolDto);
        roomDto.setFloor(floorDto);
        roomDto = floorConsumer.createLectureRoomDto(roomDto);
        assertNotNull("Lectures room should have been created", roomDto);

        DisciplineDto disciplineDto = buildDiscipline();
        disciplineDto.setSchool(schoolDto);
        disciplineDto = schoolConsumer.createDisciplineDto(disciplineDto);
        assertNotNull("Discipline should have been created", disciplineDto);

        StudentsGroupDto groupDto = buildGroup();
        groupDto.setSchool(schoolDto);
        groupDto = adminStudentConsumer.createStudentsGroupDto(groupDto);
        assertNotNull("Students group should have been created", groupDto);

        PersonDto personDto1 = buildPerson();
        personDto1.setPersonalCode("000000001");
        personDto1.setIdentification("00000001");
        personDto1 = adminPersonConsumer.createPersonDto(personDto1);
        assertNotNull("Person 1 should have been created", personDto1);

        StudentDto studentDto = buildStudent(personDto1);
        studentDto.setSchool(schoolDto);
        studentDto = adminStudentConsumer.createStudentDto(studentDto);
        assertNotNull("Student should have been created", studentDto);

        PersonDto personDto2 = buildPerson();
        personDto2.setPersonalCode("000000002");
        personDto2.setIdentification("00000002");
        personDto2 = adminPersonConsumer.createPersonDto(personDto2);
        assertNotNull("Person 2 should have been created", personDto2);

        LecturerDto lecturerDto = buildLecturer();
        lecturerDto.setSchool(schoolDto);
        lecturerDto.setPerson(personDto2);
        lecturerDto.addDiscipline(disciplineDto);
        lecturerDto = adminLecturerConsumer.createLecturerDto(lecturerDto);
        assertNotNull("Lecturer should have been created", lecturerDto);

        PersonDto personDto3 = buildPerson();
        personDto3.setPersonalCode("000000003");
        personDto3.setIdentification("00000003");
        personDto3 = adminPersonConsumer.createPersonDto(personDto3);
        assertNotNull("Person 3 should have been created", personDto3);

        LectureDto lectureDto = buildLecture();
        lectureDto.setDiscipline(disciplineDto);
        lectureDto.setLecturer(lecturerDto);
        lectureDto.setRoom(roomDto);
        lectureDto.setStudentsGroup(groupDto);
        lectureDto = lecturesConsumer.createLectureDto(lectureDto);
        assertNotNull("Lecture should have been created", lectureDto);

        LectureGuestDto guestDto = buildGuest();
        guestDto.setPerson(personDto3);
        guestDto.setLecture(lectureDto);
        guestDto = adminStudentConsumer.createLectureGuestDto(guestDto);
        assertNotNull("Lecture guest should have been created", guestDto);

        AssignmentDto assignmentDto = buildAssignment();
        assignmentDto.setDiscipline(disciplineDto);
        assignmentDto = assignmentConsumer.createAssignmentDto(assignmentDto);
        assertNotNull("Assignment should have been created", assignmentDto);

        DisciplineTestDto testDto = buildDisciplineTest();
        testDto.setLecture(lectureDto);
        testDto.setDiscipline(disciplineDto);
        testDto = testsConsumer.createDisciplineTestDto(testDto);
        assertNotNull("Discipline test should have been created", testDto);

//        schoolConsumer.purgeDisciplineDto(disciplineDto.getId());

    }


    @Test
    public void test1() {
        initSchoolAware();
        loginSystem();
        BuildingDto buildingDto = getNewBuildingForTesting();
        System.out.println(buildingDto);
        BuildingSectionDto sectionDto = buidBuildingSection();
        sectionDto.setBuilding(buildingDto);
        sectionDto.setAddress(buildAddress());
        sectionDto = locationConsumer.createBuildingSectionDto(sectionDto);
        System.out.println(sectionDto);

        locationConsumer.deleteBuildingDto(buildingDto.getId());
        buildingDto = locationConsumer.retrieveBuildingDto(buildingDto.getId());
        assertNotNull("Building deletion should have failed", buildingDto);

        locationConsumer.purgeBuildingDto(buildingDto.getId());
        buildingDto = locationConsumer.retrieveBuildingDto(buildingDto.getId());
        assertNull("Building should have been purged", buildingDto);
    }

    @Test
    public void testGetters() {
        initSchoolAware();
        loginSystem();
        System.out.println(getNewGuestForTesting());
        System.out.println(getNewLectureForTesting());
        System.out.println(getNewSchoolForTesting());
        System.out.println(getNewSchoolWithDepartmentForTesting());
        System.out.println(getNewBuildingForTesting());
        System.out.println(getNewDisciplineForTesting());
        System.out.println(getNewLecturerForTesting());
    }


}
