package com.netikras.studies.studentbuddy.core.data.api.dto.school;

import com.netikras.studies.studentbuddy.core.data.api.dto.PersonDto;

import java.util.Date;

public class StudentDto {

    private String id;
    private Date createdOn;
    private Date updatedOn;
    private PersonDto person;
    private StudentsGroupDto group;
    private SchoolDto school;
    private SchoolDepartmentDto department;

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

    public PersonDto getPerson() {
        return person;
    }

    public void setPerson(PersonDto person) {
        this.person = person;
    }

    public StudentsGroupDto getGroup() {
        return group;
    }

    public void setGroup(StudentsGroupDto group) {
        this.group = group;
    }

    public SchoolDto getSchool() {
        return school;
    }

    public void setSchool(SchoolDto school) {
        this.school = school;
    }

    public SchoolDepartmentDto getDepartment() {
        return department;
    }

    public void setDepartment(SchoolDepartmentDto department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "StudentDto{" +
                "id='" + id + '\'' +
                ", createdOn=" + createdOn +
                ", updatedOn=" + updatedOn +
                ", person=" + person +
                ", group=" + group +
                ", school=" + school +
                ", department=" + department +
                '}';
    }
}
