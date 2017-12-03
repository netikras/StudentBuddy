package com.netikras.studies.studentbuddy.core.data.api.model;

import com.netikras.studies.studentbuddy.core.data.meta.Identifiable;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

/**
 * Created by netikras on 17.6.21.
 */
@Entity
@Table(name = "building")
public class Building implements Identifiable {

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


    @OneToOne(optional = false, fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    @ModelTransform(dtoUpdatable = false)
    private Address address;

    @Column(name = "title")
    @ModelTransform
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    @ModelTransform
    private SchoolDepartment department;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "building")
    @ModelTransform(dtoUpdatable = false, dtoFieldName = "buildingSections")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<BuildingSection> sections;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "building")
    @ModelTransform(dtoUpdatable = false, dtoFieldName = "floors")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<BuildingFloor> floors;

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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public SchoolDepartment getDepartment() {
        return department;
    }

    public void setDepartment(SchoolDepartment department) {
        this.department = department;
    }

    public List<BuildingSection> getSections() {
        return sections;
    }

    public void setSections(List<BuildingSection> sections) {
        this.sections = sections;
    }

    public List<BuildingFloor> getFloors() {
        return floors;
    }

    public void setFloors(List<BuildingFloor> floors) {
        this.floors = floors;
    }

    @Override
    public String toString() {
        return "Building{" +
                "id='" + id + '\'' +
                ", createdOn=" + createdOn +
                ", updatedOn=" + updatedOn +
                ", address=" + address +
                ", title='" + title + '\'' +
                ", department=" + department +
                ", sections=" + sections +
                ", floors=" + floors +
                '}';
    }
}
