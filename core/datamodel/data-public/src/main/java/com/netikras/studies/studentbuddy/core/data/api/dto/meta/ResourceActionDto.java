package com.netikras.studies.studentbuddy.core.data.api.dto.meta;

public class ResourceActionDto {

    private String id;

    private String resource;
    private String action;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "ResourceActionDto{" +
                "id='" + id + '\'' +
                ", resource='" + resource + '\'' +
                ", action='" + action + '\'' +
                '}';
    }
}
