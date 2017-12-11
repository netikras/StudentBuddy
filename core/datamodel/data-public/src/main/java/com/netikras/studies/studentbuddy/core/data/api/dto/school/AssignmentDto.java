package com.netikras.studies.studentbuddy.core.data.api.dto.school;

import com.netikras.studies.studentbuddy.core.data.api.dto.AbstractDto;

import java.util.Date;

public class AssignmentDto extends AbstractDto {

    private String id;
    private DisciplineDto discipline;
    private String description;
    private Date createdOn;
    private Date updatedOn;
    private Date due;
    private LectureDto lectureDto;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Date getDue() {
        return due;
    }

    public void setDue(Date due) {
        this.due = due;
    }

    public LectureDto getLectureDto() {
        return lectureDto;
    }

    public void setLectureDto(LectureDto lectureDto) {
        this.lectureDto = lectureDto;
    }

    @Override
    public String toString() {
        return "AssignmentDto{" +
                "id='" + id + '\'' +
                ", discipline=" + discipline +
                ", description='" + description + '\'' +
                ", createdOn=" + createdOn +
                ", updatedOn=" + updatedOn +
                ", due=" + due +
                ", lectureDto=" + lectureDto +
                '}';
    }
}
