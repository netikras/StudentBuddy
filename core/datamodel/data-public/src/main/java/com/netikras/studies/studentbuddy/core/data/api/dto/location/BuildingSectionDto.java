package com.netikras.studies.studentbuddy.core.data.api.dto.location;

import java.util.List;

public class BuildingSectionDto {

    private String id;
    private BuildingDto building;
    private String title;
    private List<BuildingFloorDto> floors;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BuildingDto getBuilding() {
        return building;
    }

    public void setBuilding(BuildingDto building) {
        this.building = building;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<BuildingFloorDto> getFloors() {
        return floors;
    }

    public void setFloors(List<BuildingFloorDto> floors) {
        this.floors = floors;
    }

    @Override
    public String toString() {
        return "BuildingSectionDto{" +
                "id='" + id + '\'' +
                ", building=" + building +
                ", title='" + title + '\'' +
                ", floors=" + floors +
                '}';
    }
}
