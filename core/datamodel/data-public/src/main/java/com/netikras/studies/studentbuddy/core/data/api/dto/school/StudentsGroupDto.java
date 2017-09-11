package com.netikras.studies.studentbuddy.core.data.api.dto.school;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentsGroupDto {

    private String id;
    private Date createdOn;
    private Date updatedOn;
    private String title;
    private String email;
    private List<StudentDto> members;
    private SchoolDto school;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<StudentDto> getMembers() {
        return members;
    }

    public void setMembers(List<StudentDto> members) {
        this.members = members;
    }

    public List<StudentDto> addStudent(StudentDto studentDto) {
        List<StudentDto> dtos = getMembers();
        if (dtos == null) {
            dtos = new ArrayList<>();
            setMembers(dtos);
        }
        dtos.add(studentDto);
        return dtos;
    }

    public SchoolDto getSchool() {
        return school;
    }

    public void setSchool(SchoolDto school) {
        this.school = school;
    }

    @Override
    public String toString() {
        return "StudentsGroupDto{" +
                "id='" + id + '\'' +
                ", createdOn=" + createdOn +
                ", updatedOn=" + updatedOn +
                ", title='" + title + '\'' +
                ", email='" + email + '\'' +
                ", members=" + members +
                ", school=" + school +
                '}';
    }
}
