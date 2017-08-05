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
    private Building building;

    @Column(name = "title")
    private String title;

    @OneToMany(mappedBy = "section", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BuildingFloor> floors;

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

    @Override
    public String toString() {
        return "BuildingSection{" +
                "id='" + id + '\'' +
                ", building=" + building +
                ", title='" + title + '\'' +
                ", floors=" + floors +
                '}';
    }
}