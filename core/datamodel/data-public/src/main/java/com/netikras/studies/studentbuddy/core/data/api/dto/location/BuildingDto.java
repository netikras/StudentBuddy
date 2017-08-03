package com.netikras.studies.studentbuddy.core.data.api.dto.location;

import java.util.Date;
import java.util.List;

public class BuildingDto {

    private String id;
    private Date updatedOn;
    private AddressDto address;
    private String title;
    private List<BuildingSectionDto> buildingSections;

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

    @Override
    public String toString() {
        return "BuildingDto{" +
                "id='" + id + '\'' +
                ", updatedOn=" + updatedOn +
                ", address=" + address +
                ", title='" + title + '\'' +
                ", buildingSections=" + buildingSections +
                '}';
    }
}
