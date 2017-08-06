package com.netikras.studies.studentbuddy.core.meta;

import java.util.HashMap;
import java.util.Map;

public class PasswordValidationResult {

    private boolean valid = false;
    private Map<String, String> criteria;
    private String strength = "moderate";

    public static final String CRITERIA_OK = "OK";

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    /**
     * e.g. LENGTH:OK, DIGITS:"at least 2 digits are required",...
     * @param criteria
     * @param value
     */
    public void addCriteria(String criteria, String value) {
        if (getCriteria() == null) {
            setCriteria(new HashMap<>());
        }
        getCriteria().put(criteria, value);
    }

    public Map<String, String> getCriteria() {
        return criteria;
    }

    public void setCriteria(Map<String, String> criteria) {
        this.criteria = criteria;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }




}