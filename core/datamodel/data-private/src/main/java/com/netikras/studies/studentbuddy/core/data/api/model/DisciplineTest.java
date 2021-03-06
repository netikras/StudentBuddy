package com.netikras.studies.studentbuddy.core.data.api.model;

import com.netikras.studies.studentbuddy.core.data.meta.Commentable;
import com.netikras.studies.studentbuddy.core.data.meta.Identifiable;
import com.netikras.tools.common.model.mapper.ModelTransform;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

/**
 * Created by netikras on 17.6.21.
 */
@Entity
@Table(name = "tests")
public class DisciplineTest implements Identifiable, Commentable {

    @Id
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @ModelTransform(dtoFieldName = "id", dtoUpdatable = false)
    private String id;

    @Column(name = "created_on")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdOn;

    @Column(name = "updated_on")
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @ModelTransform(dtoUpdatable = false)
    private Date updatedOn;

    @Column(name = "starts_on")
    @Temporal(TemporalType.TIMESTAMP)
    @ModelTransform
    private Date startsOn;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "lecture_id")
    @ModelTransform(dtoUpdatable = false)
    private Lecture lecture;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "discipline_id")
    @ModelTransform(dtoUpdatable = false)
    private Discipline discipline;

    @Column(name = "description")
    @ModelTransform
    private String description;

    @Column(name = "exam")
    @ModelTransform
    private boolean exam = false;

    @Transient
    private List<Comment> comments;


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

    public Date getStartsOn() {
        return startsOn;
    }

    public void setStartsOn(Date startsOn) {
        this.startsOn = startsOn;
    }

    public Lecture getLecture() {
        return lecture;
    }

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isExam() {
        return exam;
    }

    public void setExam(boolean exam) {
        this.exam = exam;
    }

    @Override
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public List<Comment> getComments() {
        return comments;
    }

    @Override
    public String toString() {
        return "DisciplineTest{" +
                "id='" + id + '\'' +
                ", createdOn=" + createdOn +
                ", updatedOn=" + updatedOn +
                ", startsOn=" + startsOn +
                ", lecture=" + lecture +
                ", discipline=" + discipline +
                ", description='" + description + '\'' +
                ", exam=" + exam +
                '}';
    }
}
