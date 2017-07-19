package com.netikras.studies.studentbuddy.core.data.api.model;

import com.netikras.studies.studentbuddy.commons.utils.model.ModelTransform;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

/**
 * Like Maths, English, etc.
 *
 * Created by netikras on 17.6.21.
 */
@Entity
@Table(name = "discipline")
public class Discipline {

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
    private Date updatedOn;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id")
    private School school;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", length = 512)
    private String description;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "discipline", orphanRemoval = true, cascade = CascadeType.ALL)
    @Fetch(FetchMode.SUBSELECT)
    private List<Website> sites;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "discipline", orphanRemoval = true, cascade = CascadeType.ALL)
    @Fetch(FetchMode.SUBSELECT)
    private List<DisciplineLecturer> lecturers;

    @OneToMany(mappedBy = "discipline", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    private List<Lecture> lectures;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Website> getSites() {
        return sites;
    }

    public void setSites(List<Website> sites) {
        this.sites = sites;
    }

    public List<DisciplineLecturer> getLecturers() {
        return lecturers;
    }

    public void setLecturers(List<DisciplineLecturer> lecturers) {
        this.lecturers = lecturers;
    }

    public List<Lecture> getLectures() {
        return lectures;
    }

    public void setLectures(List<Lecture> lectures) {
        this.lectures = lectures;
    }

    @Override
    public String toString() {
        return "Discipline{" +
                "id='" + id + '\'' +
                ", school=" + school +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", sites=" + sites +
                ", lecturers=" + lecturers +
                ", lectures=" + lectures +
                '}';
    }
}
