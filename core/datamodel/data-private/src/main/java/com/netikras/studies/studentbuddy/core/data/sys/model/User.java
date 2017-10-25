package com.netikras.studies.studentbuddy.core.data.sys.model;

import com.netikras.studies.studentbuddy.core.data.api.model.Person;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import static com.netikras.tools.common.security.IntegrityUtils.isNullOrEmpty;

@Entity
@Table(name = "user")
public class User {

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

    @Column(name = "username", unique = true, nullable = false)
    @ModelTransform(dtoFieldName = "name")
    private String name;

    @Column(name = "alias", unique = true, nullable = false)
    @ModelTransform(dtoFieldName = "alias")
    private String alias;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Transient
    @ModelTransform(dtoUpdatable = false)
    private String password;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "user")
    @ModelTransform(dtoFieldName = "roles", dtoUpdatable = false, dtoValueExtractField = "role.name")
    private List<UserRole> roles;

    /**
     * No person == virtual user. Like SYSTEM
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id")
    @ModelTransform(dtoUpdatable = false)
    private Person person;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UserRole> getRoles() {
        return roles;
    }

    public void addRole(UserRole role) {
        if (getRoles() == null) {
            setRoles(new ArrayList<>());
        }
        getRoles().add(role);
    }

    public void removeRole(String roleName) {
        if (isNullOrEmpty(getRoles())) {
            return;
        }

        if (roleName == null) {
            return;
        }

        for (Iterator<UserRole> iterator = getRoles().iterator(); iterator.hasNext();) {
            UserRole userRole = iterator.next();
            if (userRole.getRole() != null
                    && userRole.getRole().getName() != null
                    && userRole.getRole().getName().equalsIgnoreCase(roleName)) {
                iterator.remove();
            }
        }
    }

    public boolean hasRole(String roleName) {
        if (isNullOrEmpty(getRoles())) {
            return false;
        }

        if (roleName == null) {
            return false;
        }

        for (UserRole userRole : getRoles()) {
            if (userRole.getRole() != null
                    && userRole.getRole().getName() != null
                    && userRole.getRole().getName().equalsIgnoreCase(roleName)) {
                return true;
            }
        }

        return false;
    }

    public void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", createdOn=" + createdOn +
                ", updatedOn=" + updatedOn +
                ", name='" + name + '\'' +
                ", alias='" + alias + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", roles=" + roles +
                ", person=" + person +
                '}';
    }
}
