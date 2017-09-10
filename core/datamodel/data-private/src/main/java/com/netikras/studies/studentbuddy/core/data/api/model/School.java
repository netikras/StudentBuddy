package com.netikras.studies.studentbuddy.core.data.api.model;

import com.netikras.tools.common.model.mapper.ModelTransform;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hibernate.annotations.FetchMode.SUBSELECT;

/**
 * Created by netikras on 17.6.21.
 */
@Entity
@Table(name = "school")
public class School {

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
    @ModelTransform(dtoFieldName = "updatedOn", dtoUpdatable = false)
    private Date updatedOn;


    @Column(name = "title", nullable = false)
    @ModelTransform(dtoFieldName = "title", dtoUpdatable = false)
    private String title;

    @OneToMany(mappedBy = "school", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(SUBSELECT)
    @ModelTransform(dtoFieldName = "departments", dtoUpdatable = false)
    private List<SchoolDepartment> departments;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = {CascadeType.ALL}, mappedBy = "school")
    @Fetch(SUBSELECT)
    @ModelTransform(dtoFieldName = "disciplines", dtoUpdatable = false)
    private List<Discipline> disciplines;

    @OneToMany(mappedBy = "school", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Fetch(SUBSELECT)
    @ModelTransform(dtoFieldName = "personnel", dtoUpdatable = false)
    private List<PersonnelMember> personnel;

    @OneToMany(mappedBy = "school", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Fetch(SUBSELECT)
    @ModelTransform(dtoFieldName = "lecturers", dtoUpdatable = false)
    private List<Lecturer> lecturers;

    @OneToMany(mappedBy = "school", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Fetch(SUBSELECT)
    @ModelTransform(dtoFieldName = "students", dtoUpdatable = false)
    private List<Student> students;

    @OneToMany(mappedBy = "school", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Fetch(SUBSELECT)
    @ModelTransform(dtoFieldName = "groups", dtoUpdatable = false)
    private List<StudentsGroup> groups;

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

    public List<SchoolDepartment> getDepartments() {
        return departments;
    }

    public List<SchoolDepartment> addDepartment(SchoolDepartment department) {
        List<SchoolDepartment> departmentList = getDepartments();
        if (departmentList == null) {
            departmentList = new ArrayList<>();
            setDepartments(departmentList);
        }
        departmentList.add(department);
        return departmentList;
    }

    public void setDepartments(List<SchoolDepartment> departments) {
        this.departments = departments;
    }

    public List<Discipline> addDiscipline(Discipline discipline) {
        List<Discipline> disciplines = getDisciplines();
        if (disciplines == null) {
            disciplines = new ArrayList<>();
            setDisciplines(disciplines);
        }
        if (discipline != null) {
            disciplines.add(discipline);
        }
        return getDisciplines();
    }

    public List<Discipline> getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(List<Discipline> disciplines) {
        this.disciplines = disciplines;
    }

    public List<PersonnelMember> getPersonnel() {
        return personnel;
    }

    public void setPersonnel(List<PersonnelMember> personnel) {
        this.personnel = personnel;
    }

    public List<Lecturer> getLecturers() {
        return lecturers;
    }

    public void setLecturers(List<Lecturer> lecturers) {
        this.lecturers = lecturers;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<StudentsGroup> getGroups() {
        return groups;
    }

    public void setGroups(List<StudentsGroup> groups) {
        this.groups = groups;
    }

    @Override
    public String toString() {
        return "School{" +
                "id='" + id + '\'' +
                ", createdOn=" + createdOn +
                ", updatedOn=" + updatedOn +
                ", title='" + title + '\'' +
                ", departments=" + departments +
                ", disciplines=" + disciplines +
                ", personnel=" + personnel +
                ", lecturers=" + lecturers +
                ", students=" + students +
                ", groups=" + groups +
                '}';
    }
}
