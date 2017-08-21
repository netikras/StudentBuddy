package com.netikras.studies.studentbuddy.core.data.api.model;

import com.netikras.studies.studentbuddy.core.data.api.model.transformers.BlobToBytesTransformer;
import com.netikras.tools.common.model.mapper.ModelTransform;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.sql.Blob;
import java.util.Date;

@Entity
@Table(name = "floor_layout")
public class FloorLayout {

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

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "floor_id")
    @ModelTransform(dtoUpdatable = false)
    private BuildingFloor floor;

    @Column(name = "active")
    @ModelTransform
    private boolean active = true;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @ModelTransform(dtoUpdatable = false, transformer = BlobToBytesTransformer.class)
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
