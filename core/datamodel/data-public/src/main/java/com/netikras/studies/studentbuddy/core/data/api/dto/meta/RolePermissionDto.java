package com.netikras.studies.studentbuddy.core.data.api.dto.meta;

import java.util.Date;

public class RolePermissionDto {

    private String id;
    private Date createdOn;
    private String role;
    private String resource;
    private String action;
    private boolean strict;
    private String entityId;

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

    public boolean isStrict() {
        return strict;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setStrict(boolean strict) {
        this.strict = strict;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    @Override
    public String toString() {
        return "RolePermissionDto{" +
                "id='" + id + '\'' +
                ", createdOn=" + createdOn +
                ", role='" + role + '\'' +
                ", resource='" + resource + '\'' +
                ", action='" + action + '\'' +
                ", strict=" + strict +
                ", entityId='" + entityId + '\'' +
                '}';
    }
}
