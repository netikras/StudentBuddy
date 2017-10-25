package com.netikras.studies.studentbuddy.core.data.api.dto.meta;

import com.netikras.studies.studentbuddy.core.data.api.dto.AbstractDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.PersonDto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class TagDto extends AbstractDto {

    private String id;
    private Date createdOn;
    private PersonDto author;
    private String value;
    private List<CommentDto> comments;

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

    public PersonDto getAuthor() {
        return author;
    }

    public void setAuthor(PersonDto author) {
        this.author = author;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<CommentDto> getComments() {
        return comments;
    }

    public void setComments(List<CommentDto> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "TagDto{" +
                "id='" + id + '\'' +
                ", createdOn=" + createdOn +
                ", author=" + author +
                ", value='" + value + '\'' +
                ", comments=" + comments +
                '}';
    }
}
