package com.netikras.studies.studentbuddy.core.data.api.model;

import com.netikras.studies.studentbuddy.core.data.meta.Commentable;
import com.netikras.studies.studentbuddy.core.data.meta.Identifiable;
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
public class Lecture implements Commentable, Identifiable {

    @Id
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @ModelTransform(dtoFieldName = "id", dtoUpdatable = false)
    private String id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "discipline_id")
    @ModelTransform(dtoUpdatable = false)
    private Discipline discipline;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id")
    @ModelTransform(dtoUpdatable = false, dtoFieldName = "course")
    private Course course;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "lecture")
    @Fetch(FetchMode.SUBSELECT)
    @ModelTransform(dtoUpdatable = false, dtoFieldName = "assignments")
    private List<Assignment> pendingAssignments;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "lecture", orphanRemoval = true)
    @Fetch(FetchMode.SUBSELECT)
    @ModelTransform(dtoUpdatable = false, dtoFieldName = "tests")
    private List<DisciplineTest> pendingTests;

    @Column(name = "starts_on")
    @Temporal(TemporalType.TIMESTAMP)
    @ModelTransform(dtoUpdatable = false)
    private Date startsOn;

    @Column(name = "ends_on")
    @Temporal(TemporalType.TIMESTAMP)
    @ModelTransform(dtoUpdatable = false)
    private Date endsOn;

    @Transient
    @ModelTransform(dtoUpdatable = false)
    private List<Comment> comments;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_id")
    @ModelTransform
    private LectureRoom room;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "lecturer_id")
    @ModelTransform
    private Lecturer lecturer;

    @ManyToOne(fetch = FetchType.EAGER)
    @ModelTransform(dtoUpdatable = false)
    private StudentsGroup studentsGroup;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "lecture", cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.SUBSELECT)
    @ModelTransform(dtoUpdatable = false, dtoFieldName = "visitors")
    private List<LectureGuest> lectureGuests;

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

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
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

    public List<LectureGuest> getLectureGuests() {
        return lectureGuests;
    }

    public void setLectureGuests(List<LectureGuest> lectureGuests) {
        this.lectureGuests = lectureGuests;
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
                ", course=" + course +
                ", pendingAssignments=" + pendingAssignments +
                ", pendingTests=" + pendingTests +
                ", startsOn=" + startsOn +
                ", endsOn=" + endsOn +
                ", comments=" + comments +
                ", room=" + room +
                ", lecturer=" + lecturer +
                ", studentsGroup=" + studentsGroup +
                ", lectureGuests=" + lectureGuests +
                '}';
    }
}
