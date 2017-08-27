package com.netikras.studies.studentbuddy.core.data.sys.model;

import com.netikras.tools.common.model.mapper.ModelTransform;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
@Table(name = "password_requirement")
public class PasswordRequirement {

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



    @Column(name = "title", nullable = false)
    @ModelTransform(dtoFieldName = "title")
    private String title;

    @Column(name = "enabled", nullable = false)
    @ModelTransform(dtoFieldName = "enabled")
    private boolean enabled;

    @Column(name = "whitelist")
    @ModelTransform(dtoFieldName = "whitelist")
    private String allowedValues; // Whitelist and blacklist

    @Column(name = "blacklist")
    @ModelTransform(dtoFieldName = "blacklist")
    private String deniedValues;

    @Column(name = "count_max", nullable = false)
    @ModelTransform(dtoFieldName = "countMax")
    private int countMax;

    @Column(name = "count_min", nullable = false)
    @ModelTransform(dtoFieldName = "countMin")
    private int countMin;

    @Column(name = "message", nullable = false)
    @ModelTransform(dtoFieldName = "message")
    private String warningMessage;


    public boolean validate(String password) {
        char[] symbols = password.toCharArray();

        if (!enabled) return true;

        if (!hasWhitelist() && !hasBlacklist()) {
            if (countMin >= 0 && symbols.length < countMin) {
                // password length validation failed
                return false;
            }
            if (countMax >= 0 && symbols.length > countMax) {
                return false;
            }
        } else {
            if (hasWhitelist()) {
                if (!isAllowed(symbols)) {
                    return false;
                }
                if (!isCountProper(symbols)) {
                    return false;
                }
            }
            if (hasBlacklist()) {
                if (isDenied(symbols)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean hasWhitelist() {
        return allowedValues != null && !allowedValues.isEmpty();
    }

    private boolean hasBlacklist() {
        return deniedValues != null && !deniedValues.isEmpty();
    }

    private boolean isAllowed(char[] symbols) {
        for (char c : symbols) {
            if (allowedValues.indexOf(c) < 0) {
                return false;
            }
        }
        return true;
    }

    private boolean isDenied(char[] symbols) {
        for (char c : symbols) {
            if (deniedValues.indexOf(c) >= 0) {
                return true;
            }
        }
        return false;
    }

    private boolean isCountProper(char[] symbols) {
        if (!hasWhitelist()) return true;
        int found = 0;
        for (char c : symbols) {
            if (allowedValues.indexOf(c) >= 0) {
                found++;
            }
        }

        return found >= countMin && found <= countMax;
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getAllowedValues() {
        return allowedValues;
    }

    public void setAllowedValues(String allowedValues) {
        this.allowedValues = allowedValues;
    }

    public String getDeniedValues() {
        return deniedValues;
    }

    public void setDeniedValues(String deniedValues) {
        this.deniedValues = deniedValues;
    }

    public int getCountMax() {
        return countMax;
    }

    public void setCountMax(int countMax) {
        this.countMax = countMax;
    }

    public int getCountMin() {
        return countMin;
    }

    public void setCountMin(int countMin) {
        this.countMin = countMin;
    }

    public String getWarningMessage() {
        return warningMessage;
    }

    public void setWarningMessage(String warningMessage) {
        this.warningMessage = warningMessage;
    }

    @Override
    public String toString() {
        return "PasswordRequirement{" +
                "id='" + id + '\'' +
                ", createdOn=" + createdOn +
                ", updatedOn=" + updatedOn +
                ", title='" + title + '\'' +
                ", enabled=" + enabled +
                ", allowedValues='" + allowedValues + '\'' +
                ", deniedValues='" + deniedValues + '\'' +
                ", countMax=" + countMax +
                ", countMin=" + countMin +
                ", warningMessage='" + warningMessage + '\'' +
                '}';
    }
}