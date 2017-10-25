package com.netikras.studies.studentbuddy.core.data.api.dto.meta;

import com.netikras.studies.studentbuddy.core.data.api.dto.AbstractDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.PersonDto;

import java.util.Date;
import java.util.List;

public class UserDto extends AbstractDto {

    private String id;
    private String name;
    private Date createdOn;
    private Date updatedOn;
    private List<String> roles;
    private PersonDto person;
    private String password;

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

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public PersonDto getPerson() {
        return person;
    }

    public void setPerson(PersonDto person) {
        this.person = person;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", createdOn=" + createdOn +
                ", updatedOn=" + updatedOn +
                ", roles=" + roles +
                ", person=" + person +
                '}';
    }
}
