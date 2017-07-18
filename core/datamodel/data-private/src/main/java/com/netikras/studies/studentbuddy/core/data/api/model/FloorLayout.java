package com.netikras.studies.studentbuddy.core.data.api.model;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Blob;

@Entity
@Table(name = "floor_layout")
public class FloorLayout {

    private String id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "floor_id")
    private BuildingFloor floor;

    @Column(name = "active")
    private boolean active = true;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private Blob floorMap;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BuildingFloor getFloor() {
        return floor;
    }

    public void setFloor(BuildingFloor floor) {
        this.floor = floor;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Blob getFloorMap() {
        return floorMap;
    }

    public void setFloorMap(Blob floorMap) {
        this.floorMap = floorMap;
    }

    @Override
    public String toString() {
        return "FloorLayout{" +
                "id='" + id + '\'' +
                ", floor=" + floor +
                ", active=" + active +
                ", floorMap=" + floorMap +
                '}';
    }
}
