package com.netikras.studies.studentbuddy.core.data.api.model;


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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "students_group")
public class StudentsGroup {

    @Id
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @ModelTransform(dtoFieldName = "id", dtoUpdatable = false)
    private String id;

    @Column(name = "created_on")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @ModelTransform(dtoFieldName = "createdOn", dtoUpdatable = false)
    private Date createdOn;

    @Column(name = "updated_on")
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @ModelTransform(dtoFieldName = "updatedOn", dtoUpdatable = false)
    private Date updatedOn;

    @Column(name = "title", nullable = false, unique = true)
    @ModelTransform(dtoFieldName = "title")
    private String title;

    @Column(name = "email")
    @ModelTransform(dtoFieldName = "email")
    private String email;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "group", cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE})
    @ModelTransform(dtoFieldName = "members", dtoUpdatable = false)
    private List<Student> members;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "school_id")
    @ModelTransform(dtoFieldName = "school", dtoUpdatable = false)
    private School school;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "group")
    @ModelTransform(dtoFieldName = "courses", dtoUpdatable = false)
    private List<Course> courses;


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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Student> getMembers() {
        return members;
    }

    public void setMembers(List<Student> members) {
        this.members = members;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public boolean addMember(Student student) {
        List<Student> members = getMembers();
        if (members == null) {
            members = new ArrayList<>();
            setMembers(members);
        }
        return members.add(student);
    }

    public boolean removeMember(Student student) {
        List<Student> members = getMembers();
        if (members == null || members.isEmpty()) {
            return false;
        }

        return members.remove(student);
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    @Override
    public String toString() {
        return "StudentsGroup{" +
                "id='" + id + '\'' +
                ", createdOn=" + createdOn +
                ", updatedOn=" + updatedOn +
                ", title='" + title + '\'' +
                ", email='" + email + '\'' +
                ", members=" + members +
                ", school=" + school +
                '}';
    }
}
