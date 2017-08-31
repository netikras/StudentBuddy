package com.netikras.studies.studentbuddy.core.data.api.dto.meta;

import java.util.Date;
import java.util.List;

public class RolePermissionsDto {

    private String id;
    private Date createdOn;
    private String role;
    private UserDto modifiedBy;
    private List<ResourceActionDto> resourceActions;

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public UserDto getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(UserDto modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public List<ResourceActionDto> getResourceActions() {
        return resourceActions;
    }

    public void setResourceActions(List<ResourceActionDto> resourceActions) {
        this.resourceActions = resourceActions;
    }

    @Override
    public String toString() {
        return "RolePermissionsDto{" +
                "id='" + id + '\'' +
                ", createdOn=" + createdOn +
                ", role='" + role + '\'' +
                ", modifiedBy=" + modifiedBy +
                ", resourceActions=" + resourceActions +
                '}';
    }
}
