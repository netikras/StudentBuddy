package com.netikras.studies.studentbuddy.core.data.api.model;

import com.netikras.tools.common.model.mapper.ModelTransform;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

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
import java.util.List;

import static org.hibernate.annotations.FetchMode.SUBSELECT;

/**
 * Created by netikras on 17.6.21.
 */
@Entity
@Table(name = "lecturer")
public class Lecturer {

    @Id
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @ModelTransform(dtoFieldName = "id", dtoUpdatable = false)
    private String id;

    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "person_id")
    @ModelTransform(dtoFieldName = "person", dtoUpdatable = false)
    private Person person;

    @Column(name = "degree")
    @ModelTransform
    private String degree;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "lecturer")
    @Fetch(SUBSELECT)
    @ModelTransform(dtoUpdatable = false, dtoValueExtractField = "discipline")
    private List<DisciplineLecturer> disciplineLecturers;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "lecturer")
    @Fetch(SUBSELECT)
    @ModelTransform(dtoUpdatable = false, dtoValueExtractField = "course")
    private List<CourseLecturer> courseLecturers;

    @OneToMany(orphanRemoval = true, mappedBy = "lecturer", fetch = FetchType.LAZY)
    @Fetch(SUBSELECT)
    @ModelTransform(dtoUpdatable = false)
    private List<Lecture> lectures;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id")
    @ModelTransform(dtoUpdatable = false)
    private School school;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public List<DisciplineLecturer> getDisciplineLecturers() {
        return disciplineLecturers;
    }

    public void setDisciplineLecturers(List<DisciplineLecturer> disciplineLecturers) {
        this.disciplineLecturers = disciplineLecturers;
    }

    public List<CourseLecturer> getCourseLecturers() {
        return courseLecturers;
    }

    public void setCourseLecturers(List<CourseLecturer> courseLecturers) {
        this.courseLecturers = courseLecturers;
    }

    public List<Lecture> getLectures() {
        return lectures;
    }

    public void setLectures(List<Lecture> lectures) {
        this.lectures = lectures;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    @Override
    public String toString() {
        return "Lecturer{" +
                "id='" + id + '\'' +
                ", person=" + person +
                ", degree='" + degree + '\'' +
                ", disciplineLecturers=" + disciplineLecturers +
                ", courseLecturers=" + courseLecturers +
                ", lectures=" + lectures +
                ", school=" + school +
                '}';
    }
}
