package com.netikras.studies.studentbuddy.core.data.api.model;

import com.netikras.tools.common.model.mapper.ModelTransform;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by netikras on 17.6.21.
 */
@Entity
@Table(name = "building_section")
public class BuildingSection {

    @Id
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @ModelTransform(dtoFieldName = "id", dtoUpdatable = false)
    private String id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "building_id")
    @ModelTransform(dtoUpdatable = false)
    private Building building;

    @Column(name = "title")
    @ModelTransform
    private String title;

    @OneToMany(mappedBy = "section", fetch = FetchType.EAGER, orphanRemoval = true)
    @ModelTransform(dtoUpdatable = false)
    private List<BuildingFloor> floors;

    @OneToOne(optional = false, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "address_id")
    @ModelTransform(dtoUpdatable = false)
    private Address address;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<BuildingFloor> getFloors() {
        return floors;
    }

    public void setFloors(List<BuildingFloor> floors) {
        this.floors = floors;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "BuildingSection{" +
                "id='" + id + '\'' +
                ", building=" + building +
                ", title='" + title + '\'' +
                ", floors=" + floors +
                ", address=" + address +
                '}';
    }
}
