package com.netikras.studies.studentbuddy.core.data.api.dto.meta;

import java.util.Date;

public class PasswordRequirementDto {

    private String id;
    private Date createdOn;
    private Date updatedOn;
    private String title;
    private boolean enabled;
    private String whitelist; // Whitelist and blacklist
    private String blacklist;
    private int countMax;
    private int countMin;
    private String message;

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

    public String getWhitelist() {
        return whitelist;
    }

    public void setWhitelist(String whitelist) {
        this.whitelist = whitelist;
    }

    public String getBlacklist() {
        return blacklist;
    }

    public void setBlacklist(String blacklist) {
        this.blacklist = blacklist;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "PasswordRequirementDto{" +
                "id='" + id + '\'' +
                ", createdOn=" + createdOn +
                ", updatedOn=" + updatedOn +
                ", title='" + title + '\'' +
                ", enabled=" + enabled +
                ", whitelist='" + whitelist + '\'' +
                ", blacklist='" + blacklist + '\'' +
                ", countMax=" + countMax +
                ", countMin=" + countMin +
                ", message='" + message + '\'' +
                '}';
    }
}
