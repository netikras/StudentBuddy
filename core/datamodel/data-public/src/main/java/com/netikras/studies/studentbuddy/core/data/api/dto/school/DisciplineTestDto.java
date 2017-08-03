package com.netikras.studies.studentbuddy.core.data.api.dto.school;

import java.util.Date;

public class DisciplineTestDto {

    private String id;
    private Date updatedOn;
    private Date startsOn;
    private LectureDto lecture;
    private DisciplineDto discipline;

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

    public Date getStartsOn() {
        return startsOn;
    }

    public void setStartsOn(Date startsOn) {
        this.startsOn = startsOn;
    }

    public LectureDto getLecture() {
        return lecture;
    }

    public void setLecture(LectureDto lecture) {
        this.lecture = lecture;
    }

    public DisciplineDto getDiscipline() {
        return discipline;
    }

    public void setDiscipline(DisciplineDto discipline) {
        this.discipline = discipline;
    }

    @Override
    public String toString() {
        return "DisciplineTestDto{" +
                "id='" + id + '\'' +
                ", updatedOn=" + updatedOn +
                ", startsOn=" + startsOn +
                ", lecture=" + lecture +
                ", discipline=" + discipline +
                '}';
    }
}
