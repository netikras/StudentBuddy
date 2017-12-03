package com.netikras.studies.studentbuddy.core.data.api.model;

import com.netikras.studies.studentbuddy.core.data.meta.Identifiable;
import com.netikras.tools.common.model.mapper.ModelTransform;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import static com.netikras.tools.common.security.IntegrityUtils.isNullOrEmpty;

@Entity
@Table(name = "course")
public class Course implements Identifiable {

    @Id
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @ModelTransform(dtoFieldName = "id", dtoUpdatable = false)
    private String id;

    @Column(name = "created_on")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @ModelTransform(dtoUpdatable = false)
    private Date createdOn;

    @Column(name = "updated_on")
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @ModelTransform(dtoUpdatable = false)
    private Date updatedOn;

    @Column(name = "title", nullable = false, unique = true)
    @ModelTransform
    private String title;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "discipline_id")
    @ModelTransform(dtoUpdatable = false)
    private Discipline discipline;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "course", cascade = {CascadeType.ALL})
    @ModelTransform(dtoUpdatable = false)
    private List<CourseLecturer> lecturers;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "course")
    @ModelTransform(dtoUpdatable = false)
    private List<Lecture> lectures;

    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "group_id")
    @ModelTransform
    private StudentsGroup group;

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

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public List<CourseLecturer> getLecturers() {
        return lecturers;
    }

    public void setLecturers(List<CourseLecturer> lecturers) {
        this.lecturers = lecturers;
    }

    public boolean addLecturer(Lecturer lecturer) {

        if (lecturer == null || hasLecturer(lecturer.getId())) {
            return false;
        }

        List<CourseLecturer> lecturersList = getLecturers();
        if (lecturersList == null) {
            lecturersList = new ArrayList<>();
            setLecturers(lecturersList);
        }

        CourseLecturer courseLecturer = new CourseLecturer();
        courseLecturer.setCourse(this);
        courseLecturer.setLecturer(lecturer);

        lecturersList.add(courseLecturer);
        return true;
    }

    public boolean removeLecturer(Lecturer lecturer) {
        if (lecturer == null
                || isNullOrEmpty(getLecturers())
                || !hasLecturer(lecturer.getId())) {
            return false;
        }

        for (Iterator<CourseLecturer> iterator = getLecturers().iterator(); iterator.hasNext();) {
            CourseLecturer courseLecturer = iterator.next();
            if (courseLecturer != null && courseLecturer.getLecturer() != null) {
                if (courseLecturer.getLecturer().equals(lecturer) || (!isNullOrEmpty(lecturer.getId()) && lecturer.getId().equals(courseLecturer.getLecturer().getId()))) {
                    iterator.remove();
                    return true;
                }
            }
        }

        return false;
    }

    public boolean hasLecturer(String id) {
        if (!isNullOrEmpty(id) && !isNullOrEmpty(getLecturers())) {
            for (CourseLecturer courseLecturer : getLecturers()) {
                if (courseLecturer.getLecturer() != null && id.equals(courseLecturer.getLecturer().getId())) {
                    return true;
                }
            }
        }
        return false;
    }

    public StudentsGroup getGroup() {
        return group;
    }

    public void setGroup(StudentsGroup group) {
        this.group = group;
    }

    public List<Lecture> getLectures() {
        return lectures;
    }

    public void setLectures(List<Lecture> lectures) {
        this.lectures = lectures;
    }

    public List<Lecture> addLecture(Lecture lecture) {
        List<Lecture> lectureList = getLectures();
        if (lectureList == null) {
            lectureList = new ArrayList<>();
            setLectures(lectureList);
        }
        lectureList.add(lecture);
        return lectureList;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id='" + id + '\'' +
                ", createdOn=" + createdOn +
                ", updatedOn=" + updatedOn +
                ", title='" + title + '\'' +
                ", discipline=" + discipline +
                ", lecturers=" + lecturers +
                ", lectures=" + lectures +
                ", group=" + group +
                '}';
    }
}
