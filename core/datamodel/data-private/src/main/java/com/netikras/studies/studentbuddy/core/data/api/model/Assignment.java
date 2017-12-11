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
@Table(name = "assignment")
public class Assignment implements Commentable, Identifiable {

    @Id
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @ModelTransform(dtoFieldName = "id", dtoUpdatable = false)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id")
    @ModelTransform(dtoUpdatable = false)
    private Lecture lecture;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "discipline_id")
    @ModelTransform(dtoUpdatable = false)
    private Discipline discipline;

    @Column(name = "description", nullable = false, length = 512)
    @ModelTransform
    private String description;

    @Column(name = "created_on", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @ModelTransform(dtoUpdatable = false)
    private Date createdOn;

    @Column(name = "updated_on")
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @ModelTransform(dtoUpdatable = false)
    private Date updatedOn;

    @Column(name = "due_date")
    @Temporal(TemporalType.TIMESTAMP)
    @ModelTransform(dtoUpdatable = false, dtoFieldName = "due")
    private Date dueDate;

    @Transient
    private List<Comment> comments;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "id='" + id + '\'' +
                ", lecture=" + lecture +
                ", discipline=" + discipline +
                ", description='" + description + '\'' +
                ", createdOn=" + createdOn +
                ", updatedOn=" + updatedOn +
                ", dueDate=" + dueDate +
                '}';
    }

    @Override
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public List<Comment> getComments() {
        return comments;
    }
}
