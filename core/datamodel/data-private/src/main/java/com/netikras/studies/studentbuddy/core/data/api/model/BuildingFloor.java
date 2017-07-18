package com.netikras.studies.studentbuddy.core.data.api.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by netikras on 17.6.21.
 */
@Entity
@Table(name = "floor")
public class BuildingFloor {


    private String id;

    @Column(name = "number", nullable = false)
    private int number;

    @Column(name = "name")
    private String name;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
    private BuildingSection section;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private Building building;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "floor", orphanRemoval = true)
    private List<FloorLayout> layouts;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BuildingSection getSection() {
        return section;
    }

    public void setSection(BuildingSection section) {
        this.section = section;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public List<FloorLayout> getLayouts() {
        return layouts;
    }

    public void setLayouts(List<FloorLayout> layouts) {
        this.layouts = layouts;
    }

    @Override
    public String toString() {
        return "BuildingFloor{" +
                "id='" + id + '\'' +
                ", number=" + number +
                ", name='" + name + '\'' +
                ", section=" + section +
                ", building=" + building +
                ", layouts=" + layouts +
                '}';
    }
}
