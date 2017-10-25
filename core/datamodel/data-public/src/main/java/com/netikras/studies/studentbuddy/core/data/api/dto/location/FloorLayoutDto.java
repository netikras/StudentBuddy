package com.netikras.studies.studentbuddy.core.data.api.dto.location;

import com.netikras.studies.studentbuddy.core.data.api.dto.AbstractDto;

import java.util.Arrays;
import java.util.Date;

public class FloorLayoutDto extends AbstractDto {

    private String id;
    private Date updatedOn;
    private BuildingFloorDto floor;
    private boolean active = true;
    private byte[] floorMap;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public BuildingFloorDto getFloor() {
        return floor;
    }

    public void setFloor(BuildingFloorDto floor) {
        this.floor = floor;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public byte[] getFloorMap() {
        return floorMap;
    }

    public void setFloorMap(byte[] floorMap) {
        this.floorMap = floorMap;
    }

    @Override
    public String toString() {
        return "FloorLayoutDto{" +
                "id='" + id + '\'' +
                ", updatedOn=" + updatedOn +
                ", floor=" + floor +
                ", active=" + active +
                ", floorMap=" + Arrays.toString(floorMap) +
                '}';
    }
}
