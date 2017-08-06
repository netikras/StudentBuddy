package com.netikras.studies.studentbuddy.core.data.sys.model;

import com.netikras.tools.common.model.mapper.ModelTransform;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "role_permissions")
public class RolePermissions {

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

    @Column(name = "updated_on")
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @ModelTransform(dtoFieldName = "updatedOn", dtoUpdatable = false)
    private Date updatedOn;

    // TODO optional must be false
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "modified_by_id")
    private User modifedBy;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "rolePermission")
    private List<ResourceActionLink> resourceActions;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "role_id")
    private Role role;

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

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public User getModifedBy() {
        return modifedBy;
    }

    public void setModifedBy(User modifedBy) {
        this.modifedBy = modifedBy;
    }

    public List<ResourceActionLink> getResourceActions() {
        return resourceActions;
    }

    public void setResourceActions(List<ResourceActionLink> resourceActions) {
        this.resourceActions = resourceActions;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "RolePermissions{" +
                "id='" + id + '\'' +
                ", createdOn=" + createdOn +
                ", updatedOn=" + updatedOn +
                ", modifedBy=" + modifedBy +
                ", resourceActions=" + resourceActions +
                ", role=" + role +
                '}';
    }
}
