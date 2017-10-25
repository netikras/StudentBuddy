package com.netikras.studies.studentbuddy.core.data.api.dto.school;

import com.netikras.studies.studentbuddy.core.data.api.dto.AbstractDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.meta.CommentDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.location.LectureRoomDto;

import java.util.Date;
import java.util.List;

public class LectureDto extends AbstractDto {

    private String id;
    private DisciplineDto discipline;
    private CourseDto course;
    private List<AssignmentDto> assignments;
    private List<DisciplineTestDto> tests;
    private Date startsOn;
    private Date endsOn;
    private List<CommentDto> comments;
    private LectureRoomDto room;
    private StudentsGroupDto studentsGroup;
    private List<StudentDto> visitors;
    private LecturerDto lecturer;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DisciplineDto getDiscipline() {
        return discipline;
    }

    public void setDiscipline(DisciplineDto discipline) {
        this.discipline = discipline;
    }

    public CourseDto getCourse() {
        return course;
    }

    public void setCourse(CourseDto course) {
        this.course = course;
    }

    public List<AssignmentDto> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<AssignmentDto> assignments) {
        this.assignments = assignments;
    }

    public List<DisciplineTestDto> getTests() {
        return tests;
    }

    public void setTests(List<DisciplineTestDto> tests) {
        this.tests = tests;
    }

    public Date getStartsOn() {
        return startsOn;
    }

    public void setStartsOn(Date startsOn) {
        this.startsOn = startsOn;
    }

    public Date getEndsOn() {
        return endsOn;
    }

    public void setEndsOn(Date endsOn) {
        this.endsOn = endsOn;
    }

    public List<CommentDto> getComments() {
        return comments;
    }

    public void setComments(List<CommentDto> comments) {
        this.comments = comments;
    }

    public LectureRoomDto getRoom() {
        return room;
    }

    public void setRoom(LectureRoomDto room) {
        this.room = room;
    }

    public StudentsGroupDto getStudentsGroup() {
        return studentsGroup;
    }

    public void setStudentsGroup(StudentsGroupDto studentsGroup) {
        this.studentsGroup = studentsGroup;
    }

    public List<StudentDto> getVisitors() {
        return visitors;
    }

    public void setVisitors(List<StudentDto> visitors) {
        this.visitors = visitors;
    }

    public LecturerDto getLecturer() {
        return lecturer;
    }

    public void setLecturer(LecturerDto lecturer) {
        this.lecturer = lecturer;
    }

    @Override
    public String toString() {
        return "LectureDto{" +
                "id='" + id + '\'' +
                ", discipline=" + discipline +
                ", course=" + course +
                ", assignments=" + assignments +
                ", tests=" + tests +
                ", startsOn=" + startsOn +
                ", endsOn=" + endsOn +
                ", comments=" + comments +
                ", room=" + room +
                ", studentsGroup=" + studentsGroup +
                ", visitors=" + visitors +
                ", lecturer=" + lecturer +
                '}';
    }
}
