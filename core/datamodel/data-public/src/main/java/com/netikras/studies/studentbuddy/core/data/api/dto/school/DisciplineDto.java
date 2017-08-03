package com.netikras.studies.studentbuddy.core.data.api.dto.school;

import com.netikras.studies.studentbuddy.core.data.api.dto.meta.WebsiteDto;

import java.util.Date;
import java.util.List;

public class DisciplineDto {

    private String id;
    private Date updatedOn;
    private String title;
    private String description;
    private SchoolDto school;
    private List<WebsiteDto> websites;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SchoolDto getSchool() {
        return school;
    }

    public void setSchool(SchoolDto school) {
        this.school = school;
    }

    public List<WebsiteDto> getWebsites() {
        return websites;
    }

    public void setWebsites(List<WebsiteDto> websites) {
        this.websites = websites;
    }

    @Override
    public String toString() {
        return "DisciplineDto{" +
                "id='" + id + '\'' +
                ", updatedOn=" + updatedOn +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", school=" + school +
                ", websites=" + websites +
                '}';
    }
}
