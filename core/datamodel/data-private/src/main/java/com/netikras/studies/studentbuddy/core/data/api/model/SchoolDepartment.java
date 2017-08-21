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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

/**
 * aka faculty, but could also be museum, library, etc.
 */
@Entity
@Table(name = "department")
public class SchoolDepartment {

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

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @ModelTransform(dtoFieldName = "school", dtoUpdatable = false)
    private School school;

    @Column(name = "title", nullable = false)
    @ModelTransform(dtoFieldName = "title")
    private String title;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "department")
    @ModelTransform(dtoFieldName = "buildings", dtoUpdatable = false)
    private List<Building> buildings;



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

    public List<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(List<Building> buildings) {
        this.buildings = buildings;
    }


    @Override
    public String toString() {
        return "SchoolDepartment{" +
                "id='" + id + '\'' +
                ", school=" + school +
                ", title='" + title + '\'' +
                ", buildings=" + buildings +
                '}';
    }
}
