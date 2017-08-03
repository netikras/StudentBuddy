package com.netikras.studies.studentbuddy.core.data.api.dto.meta;

import com.netikras.studies.studentbuddy.core.data.api.dto.PersonDto;

import java.util.Date;

public class WebsiteDto {

    private String id;
    private Date createdOn;
    private Date updatedOn;
    private PersonDto createdBy;
    private String address;
    private String name;
    private String description;
    private String credentials;
    private boolean active = true;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public PersonDto getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(PersonDto createdBy) {
        this.createdBy = createdBy;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCredentials() {
        return credentials;
    }

    public void setCredentials(String credentials) {
        this.credentials = credentials;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "WebsiteDto{" +
                "id='" + id + '\'' +
                ", createdOn=" + createdOn +
                ", updatedOn=" + updatedOn +
                ", createdBy=" + createdBy +
                ", address='" + address + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", credentials='" + credentials + '\'' +
                ", active=" + active +
                '}';
    }
}
