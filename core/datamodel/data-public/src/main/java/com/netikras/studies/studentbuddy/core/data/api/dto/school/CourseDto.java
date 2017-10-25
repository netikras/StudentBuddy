package com.netikras.studies.studentbuddy.core.data.api.dto.school;

import com.netikras.studies.studentbuddy.core.data.api.dto.AbstractDto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CourseDto extends AbstractDto {
    private String id;

    private Date createdOn;
    private Date updatedOn;
    private String title;
    private DisciplineDto discipline;
    private List<LectureDto> lectures;
    private StudentsGroupDto group;

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

    public DisciplineDto getDiscipline() {
        return discipline;
    }

    public void setDiscipline(DisciplineDto discipline) {
        this.discipline = discipline;
    }

    public StudentsGroupDto getGroup() {
        return group;
    }

    public void setGroup(StudentsGroupDto group) {
        this.group = group;
    }

    public List<LectureDto> getLectures() {
        return lectures;
    }

    public void setLectures(List<LectureDto> lectures) {
        this.lectures = lectures;
    }

    public List<LectureDto> addLecture(LectureDto lectureDto) {
        List<LectureDto> lectureDtos = getLectures();
        if (lectureDtos == null) {
            lectureDtos = new ArrayList<>();
            setLectures(lectureDtos);
        }
        lectureDtos.add(lectureDto);
        return lectureDtos;
    }



    @Override
    public String toString() {
        return "CourseDto{" +
                "id='" + id + '\'' +
                ", createdOn=" + createdOn +
                ", updatedOn=" + updatedOn +
                ", title='" + title + '\'' +
                ", discipline=" + discipline +
                ", lectures=" + lectures +
                ", group=" + group +
                '}';
    }
}
