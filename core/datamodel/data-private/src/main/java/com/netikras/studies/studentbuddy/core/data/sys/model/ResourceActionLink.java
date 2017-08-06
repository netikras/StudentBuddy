package com.netikras.studies.studentbuddy.core.data.sys.model;

import com.netikras.studies.studentbuddy.core.meta.Action;
import com.netikras.studies.studentbuddy.core.meta.Resource;
import com.netikras.tools.common.model.mapper.ModelTransform;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

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
    private Resource resource;

    @Column(name = "action")
    @Enumerated(EnumType.STRING)
    private Action action;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "role_permission_id")
    private RolePermissions rolePermission;


    public boolean isLike(ResourceActionLink otherLnk) {
        return otherLnk != null
                && getResource().equals(otherLnk.getResource())
                && getAction().equals(otherLnk.getAction());
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

    public RolePermissions getRolePermission() {
        return rolePermission;
    }

    public void setRolePermission(RolePermissions rolePermission) {
        this.rolePermission = rolePermission;
    }

    @Override
    public String toString() {
        return "ResourceActionLink{" +
                "id='" + id + '\'' +
                ", createdOn=" + createdOn +
                ", resource=" + resource +
                ", action=" + action +
                ", rolePermission=" + rolePermission +
                '}';
    }
}
