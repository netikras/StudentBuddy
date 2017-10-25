package com.netikras.studies.studentbuddy.core.data.api.dto.school;

import com.netikras.studies.studentbuddy.core.data.api.dto.AbstractDto;

import java.util.Date;
import java.util.List;

public class SchoolDto extends AbstractDto {

    private String id;
    private Date updatedOn;
    private String title;
    private List<SchoolDepartmentDto> departments;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public List<SchoolDepartmentDto> getDepartments() {
        return departments;
    }

    public void setDepartments(List<SchoolDepartmentDto> departments) {
        this.departments = departments;
    }

    @Override
    public String toString() {
        return "SchoolDto{" +
                "id='" + id + '\'' +
                ", updatedOn=" + updatedOn +
                ", title='" + title + '\'' +
                ", departments=" + departments +
                '}';
    }
}
