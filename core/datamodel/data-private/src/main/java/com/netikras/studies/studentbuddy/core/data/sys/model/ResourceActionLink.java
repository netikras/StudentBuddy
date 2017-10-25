package com.netikras.studies.studentbuddy.core.data.sys.model;

import com.netikras.studies.studentbuddy.core.data.meta.Action;
import com.netikras.studies.studentbuddy.core.data.meta.Resource;
import com.netikras.tools.common.model.mapper.ModelTransform;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

import static com.netikras.tools.common.security.IntegrityUtils.isNullOrEmpty;

@Entity
@Table(name = "resource_action")
public class ResourceActionLink {

    @Id
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @ModelTransform(dtoFieldName = "id", dtoUpdatable = false)
    private String id;

    @Column(name = "created_on", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @ModelTransform(dtoFieldName = "createdOn", dtoUpdatable = false)
    private Date createdOn;

    @Column(name = "resource")
    @Enumerated(EnumType.STRING)
    @ModelTransform
    private Resource resource;

    @Column(name = "action")
    @Enumerated(EnumType.STRING)
    @ModelTransform
    private Action action;

    @Column(name = "entity_id")
    @ModelTransform
    private String entityId;

    @Column(name = "strict")
    @ModelTransform
    private boolean strict = true;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "role_id")
    private Role role;

    public ResourceActionLink() {
    }

    public ResourceActionLink(Resource resource, Action action, String entityId) {
        this.resource = resource;
        this.action = action;
        this.entityId = entityId;
    }

    public ResourceActionLink(Resource resource, Action action, String entityId, Boolean strict) {
        this.resource = resource;
        this.action = action;
        this.entityId = entityId;
        if (strict != null) {
            this.strict = strict;
        }
    }

    public boolean isLike(ResourceActionLink otherLnk) {
        return otherLnk != null
                && getResource().equals(otherLnk.getResource())
                && getAction().equals(otherLnk.getAction())
                && isStrict() == otherLnk.isStrict()
                && areEqual(getEntityId(), otherLnk.getEntityId())
                ;
    }

    private boolean areEqual(Object left, Object right) {
        if (left == right) return true;
        if (left == null || right == null) {
            return false;
        }

        return left.equals(right);
    }

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

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public boolean isStrict() {
        return strict;
    }

    public void setStrict(boolean strict) {
        this.strict = strict;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "ResourceActionLink{" +
                "id='" + id + '\'' +
                ", createdOn=" + createdOn +
                ", resource=" + resource +
                ", action=" + action +
                ", entityId='" + entityId + '\'' +
                ", strict=" + strict +
                ", role=" + role +
                '}';
    }
}
