package com.netikras.studies.studentbuddy.core.meta.dto;

import java.util.Date;

public class SystemSettingDto {

    private String id;
    private String name;
    private String value;

    private Date createdOn;
    private Date updatedOn;
    private String updatedBy;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Override
    public String toString() {
        return "SystemSettingDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", createdOn=" + createdOn +
                ", updatedOn=" + updatedOn +
                ", updatedBy='" + updatedBy + '\'' +
                '}';
    }
}
