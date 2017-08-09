package com.netikras.studies.studentbuddy.core.data.api.dto.school;

import com.netikras.studies.studentbuddy.core.data.api.dto.meta.CommentDto;

import java.util.Date;
import java.util.List;

/**
 * A student or any other person not belonging to an assigned student-group and not a teacher
 */
public class LectureGuestDto {

    private String id;
    private Date createdOn;
    private Date updatedOn;
    private LectureDto lecture;
    private StudentDto student;
    private List<CommentDto> comments;

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

    public LectureDto getLecture() {
        return lecture;
    }

    public void setLecture(LectureDto lecture) {
        this.lecture = lecture;
    }

    public StudentDto getStudent() {
        return student;
    }

    public void setStudent(StudentDto student) {
        this.student = student;
    }

    public List<CommentDto> getComments() {
        return comments;
    }

    public void setComments(List<CommentDto> comments) {
        this.comments = comments;
    }


    @Override
    public String toString() {
        return "LectureGuestDto{" +
                "id='" + id + '\'' +
                ", createdOn=" + createdOn +
                ", updatedOn=" + updatedOn +
                ", lecture=" + lecture +
                ", student=" + student +
                ", comments=" + comments +
                '}';
    }
}
