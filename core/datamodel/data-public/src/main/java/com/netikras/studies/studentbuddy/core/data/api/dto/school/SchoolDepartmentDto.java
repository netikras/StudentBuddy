package com.netikras.studies.studentbuddy.core.data.api.dto.school;

import com.netikras.studies.studentbuddy.core.data.api.dto.location.BuildingDto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SchoolDepartmentDto {

    private String id;
    private Date updatedOn;
    private SchoolDto school;
    private String title;
    private List<BuildingDto> buildings;

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

    public SchoolDto getSchool() {
        return school;
    }

    public void setSchool(SchoolDto school) {
        this.school = school;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<BuildingDto> getBuildings() {
        return buildings;
    }

    public void setBuildings(List<BuildingDto> buildings) {
        this.buildings = buildings;
    }

    public List<BuildingDto> addBuilding(BuildingDto buildingDto) {
        List<BuildingDto> dtos = getBuildings();
        if (dtos == null) {
            dtos = new ArrayList<>();
            setBuildings(dtos);
        }
        dtos.add(buildingDto);
        return dtos;
    }

    @Override
    public String toString() {
        return "SchoolDepartmentDto{" +
                "id='" + id + '\'' +
                ", updatedOn=" + updatedOn +
                ", school=" + school +
                ", title='" + title + '\'' +
                ", buildings=" + buildings +
                '}';
    }
}
