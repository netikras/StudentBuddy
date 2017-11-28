package com.netikras.studies.studentbuddy.core.data.api.dto.location;

import com.netikras.studies.studentbuddy.core.data.api.dto.AbstractDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.SchoolDepartmentDto;

import java.util.Date;
import java.util.List;

public class BuildingDto extends AbstractDto {

    private String id;
    private Date updatedOn;
    private AddressDto address;
    private String title;
    private List<BuildingSectionDto> buildingSections;
    private SchoolDepartmentDto department;
    private List<BuildingFloorDto> floors;

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

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<BuildingSectionDto> getBuildingSections() {
        return buildingSections;
    }

    public void setBuildingSections(List<BuildingSectionDto> buildingSections) {
        this.buildingSections = buildingSections;
    }

    public SchoolDepartmentDto getDepartment() {
        return department;
    }

    public void setDepartment(SchoolDepartmentDto department) {
        this.department = department;
    }

    public List<BuildingFloorDto> getFloors() {
        return floors;
    }

    public void setFloors(List<BuildingFloorDto> floors) {
        this.floors = floors;
    }

    @Override
    public String toString() {
        return "BuildingDto{" +
                "id='" + id + '\'' +
                ", updatedOn=" + updatedOn +
                ", address=" + address +
                ", title='" + title + '\'' +
                ", buildingSections=" + buildingSections +
                ", department=" + department +
                ", floors=" + floors +
                '}';
    }
}
