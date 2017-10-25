package com.netikras.studies.studentbuddy.core.data.api.dto.meta;

import com.netikras.studies.studentbuddy.core.data.api.dto.AbstractDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.PersonDto;

import java.util.Date;
import java.util.List;

public class RoleDto extends AbstractDto {

    private String id;
    private String name;
    private Date createdOn;
    private PersonDto createdBy;
    private List<RolePermissionDto> permissions;


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

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public PersonDto getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(PersonDto createdBy) {
        this.createdBy = createdBy;
    }

    public List<RolePermissionDto> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<RolePermissionDto> permissions) {
        this.permissions = permissions;
    }


    @Override
    public String toString() {
        return "RoleDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", createdOn=" + createdOn +
                ", createdBy=" + createdBy +
                ", permissions=" + permissions +
                '}';
    }
}
