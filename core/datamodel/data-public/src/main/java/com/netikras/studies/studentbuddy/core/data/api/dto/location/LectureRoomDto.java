package com.netikras.studies.studentbuddy.core.data.api.dto.location;

import com.netikras.studies.studentbuddy.core.data.api.dto.meta.CommentDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.SchoolDto;

import java.util.Date;
import java.util.List;

public class LectureRoomDto {

    private String id;
    private Date createdOn;
    private Date updatedOn;
    private String number; // e.g. 137A
    private String title;
    private SchoolDto school;
    private List<CommentDto> comments;
    private BuildingFloorDto floor;


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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public SchoolDto getSchool() {
        return school;
    }

    public void setSchool(SchoolDto school) {
        this.school = school;
    }

    public List<CommentDto> getComments() {
        return comments;
    }

    public void setComments(List<CommentDto> comments) {
        this.comments = comments;
    }

    public BuildingFloorDto getFloor() {
        return floor;
    }

    public void setFloor(BuildingFloorDto floor) {
        this.floor = floor;
    }

    @Override
    public String toString() {
        return "LectureRoomDto{" +
                "id='" + id + '\'' +
                ", createdOn=" + createdOn +
                ", updatedOn=" + updatedOn +
                ", number='" + number + '\'' +
                ", title='" + title + '\'' +
                ", school=" + school +
                ", comments=" + comments +
                ", floor=" + floor +
                '}';
    }
}
