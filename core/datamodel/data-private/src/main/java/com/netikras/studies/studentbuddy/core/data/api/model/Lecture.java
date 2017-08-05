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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Is an instance of a lecture. E.g. Maths on Monday @13:45
 *
 * Created by netikras on 17.6.21.
 */
@Entity
@Table(name = "lecture")
public class Lecture {

    @Id
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @ModelTransform(dtoFieldName = "id", dtoUpdatable = false)
    private String id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "discipline_id")
    private Discipline discipline;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "lecture")
    @Fetch(FetchMode.SUBSELECT)
    private List<Assignment> pendingAssignments;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "lecture", orphanRemoval = true)
    @Fetch(FetchMode.SUBSELECT)
    private List<DisciplineTest> pendingTests;

    @Column(name = "starts_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startsOn;

    @Column(name = "ends_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endsOn;

    @Transient
    private List<Comment> comments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private LectureRoom room;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "lecturer_id")
    private Lecturer lecturer;

    @ManyToOne(fetch = FetchType.EAGER)
    private StudentsGroup studentsGroup;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "lecture", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LectureGuest> exclusiveStudents;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public List<Assignment> getPendingAssignments() {
        return pendingAssignments;
    }

    public void setPendingAssignments(List<Assignment> pendingAssignments) {
        this.pendingAssignments = pendingAssignments;
    }

    public List<DisciplineTest> getPendingTests() {
        return pendingTests;
    }

    public void setPendingTests(List<DisciplineTest> pendingTests) {
        this.pendingTests = pendingTests;
    }

    public Date getStartsOn() {
        return startsOn;
    }

    public void setStartsOn(Date startsOn) {
        this.startsOn = startsOn;
    }

    public Date getEndsOn() {
        return endsOn;
    }

    public void setEndsOn(Date endsOn) {
        this.endsOn = endsOn;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public LectureRoom getRoom() {
        return room;
    }

    public void setRoom(LectureRoom room) {
        this.room = room;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    public StudentsGroup getStudentsGroup() {
        return studentsGroup;
    }

    public void setStudentsGroup(StudentsGroup studentsGroup) {
        this.studentsGroup = studentsGroup;
    }

    public List<LectureGuest> getExclusiveStudents() {
        return exclusiveStudents;
    }

    public void setExclusiveStudents(List<LectureGuest> exclusiveStudents) {
        this.exclusiveStudents = exclusiveStudents;
    }

    public void addComment(Comment comment) {
        List<Comment> comments = getComments();
        if (comments == null) {
            comments = new ArrayList<>();
            setComments(comments);
        }
        comments.add(comment);
    }

    @Override
    public String toString() {
        return "Lecture{" +
                "id='" + id + '\'' +
                ", discipline=" + discipline +
                ", pendingAssignments=" + pendingAssignments +
                ", pendingTests=" + pendingTests +
                ", startsOn=" + startsOn +
                ", endsOn=" + endsOn +
                ", comments=" + comments +
                ", room=" + room +
                ", lecturer=" + lecturer +
                ", studentsGroup=" + studentsGroup +
                ", exclusiveStudents=" + exclusiveStudents +
                '}';
    }
}
