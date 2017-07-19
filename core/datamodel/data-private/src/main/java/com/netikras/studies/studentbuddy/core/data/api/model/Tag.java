package com.netikras.studies.studentbuddy.core.data.api.model;


import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tags")
public class Tag {

    private String id;

    @Column(name = "created_on")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdOn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "creator_id")
    private Person createdBy;

    @Column(name = "value")
    private String value;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "tag")
    private List<CommentTag> commentTags;

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

    public Person getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Person createdBy) {
        this.createdBy = createdBy;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<CommentTag> getCommentTags() {
        return commentTags;
    }

    public void setCommentTags(List<CommentTag> commentTags) {
        this.commentTags = commentTags;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id='" + id + '\'' +
                ", createdOn=" + createdOn +
                ", createdBy=" + createdBy +
                ", value='" + value + '\'' +
                ", commentTags=" + commentTags +
                '}';
    }
}
