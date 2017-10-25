package com.netikras.studies.studentbuddy.core.data.sys.model;

import com.netikras.studies.studentbuddy.core.data.api.model.Person;
import com.netikras.studies.studentbuddy.core.data.meta.Action;
import com.netikras.studies.studentbuddy.core.data.meta.Resource;
import com.netikras.tools.common.model.mapper.ModelTransform;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import static com.netikras.tools.common.security.IntegrityUtils.areEqual;
import static com.netikras.tools.common.security.IntegrityUtils.isNullOrEmpty;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @ModelTransform(dtoFieldName = "id", dtoUpdatable = false)
    private String id;

    @Column(name = "user", unique = true, nullable = false)
    @ModelTransform(dtoFieldName = "name", dtoUpdatable = false)
    private String name;

    @Column(name = "created_on", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @ModelTransform(dtoFieldName = "createdOn", dtoUpdatable = false)
    private Date createdOn;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "author_id", nullable = true)
    @ModelTransform(dtoFieldName = "author", dtoUpdatable = false)
    private Person createdBy;

    @OneToMany(orphanRemoval = true, fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "role")
    @ModelTransform
    private List<ResourceActionLink> permissions;

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

    public Person getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Person createdBy) {
        this.createdBy = createdBy;
    }

    public List<ResourceActionLink> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<ResourceActionLink> permissions) {
        this.permissions = permissions;
    }

    public List<ResourceActionLink> addPermission(ResourceActionLink permission) {
        List<ResourceActionLink> permissions = getPermissions();
        if (permissions == null) {
            permissions = new ArrayList<>();
            setPermissions(permissions);
        }
        permissions.add(permission);
        permission.setRole(this);
        return permissions;
    }

    public List<ResourceActionLink> addPermission(Resource resource, Action action, String resourceId, Boolean strict) {
        return addPermission(new ResourceActionLink(resource, action, resourceId, strict));
    }

    public void removePermission(Resource resource, Action action, String resourceId) {
        removePermission(resource.name(), action.name(), resourceId);
    }

    public void removePermission(String resourceName, String actionName, String resourceId) {
        if (isNullOrEmpty(getPermissions())) {
            return;
        }

        resourceName = resourceName.toUpperCase();
        actionName = actionName.toUpperCase();

        for (Iterator<ResourceActionLink> iterator = getPermissions().iterator(); iterator.hasNext();) {
            ResourceActionLink link = iterator.next();
            if (link.getResource() != null
                    && link.getResource().name().equals(resourceName)
                    && link.getAction() != null
                    && link.getAction().name().equals(actionName)) {

                if (areEqual(resourceId, link.getEntityId())) {
                    iterator.remove();
                }

            }
        }
    }

    public ResourceActionLink getPermission(String resourceName, String actionName, String resourceId) {
        if (isNullOrEmpty(getPermissions())) {
            return null;
        }

        resourceName = resourceName.toUpperCase();
        actionName = actionName.toUpperCase();

        for (Iterator<ResourceActionLink> iterator = getPermissions().iterator(); iterator.hasNext();) {
            ResourceActionLink link = iterator.next();
            if (link.getResource() != null
                    && link.getResource().name().equals(resourceName)
                    && link.getAction() != null
                    && link.getAction().name().equals(actionName)) {

                if (areEqual(resourceId, link.getEntityId())) {
                    return link;
                }

            }
        }
        return null;
    }

    public boolean hasPermission(String resourceName, String actionName, String resourceId) {
        return getPermission(resourceName, actionName, resourceId) != null;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", createdOn=" + createdOn +
                ", createdBy=" + createdBy +
                ", permissions=" + permissions +
                '}';
    }
}