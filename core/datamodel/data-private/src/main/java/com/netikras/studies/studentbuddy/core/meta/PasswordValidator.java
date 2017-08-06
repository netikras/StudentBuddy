package com.netikras.studies.studentbuddy.core.meta;

import com.netikras.studies.studentbuddy.core.data.sys.model.PasswordRequirement;

import java.util.List;

public class PasswordValidator {

    private List<PasswordRequirement> requirements;



    public void setRequirements(List<PasswordRequirement> requirements) {
        this.requirements = requirements;
    }

    public PasswordValidationResult validate(String password) {

        PasswordValidationResult result = new PasswordValidationResult();
        if (password == null) {
            result.setValid(false);
            result.addCriteria("Password", "Password not provided");
            return result;
        }

        if (requirements == null || requirements.isEmpty()) {
            // failsafe. Do not allow system malfunction let invalid values to slip through.
            // AT LEAST ONE VALIDATOR IS MANDATORY
            result.setValid(false);
            result.addCriteria("System", "Password validation failed. Please contact system administrator");
            return result;
        }

        for (PasswordRequirement requirement : requirements) {
            if (!requirement.validate(password)) {
                result.setValid(false);
                result.addCriteria(requirement.getTitle(), requirement.getWarningMessage());
            } else {
                result.addCriteria(requirement.getTitle(), PasswordValidationResult.CRITERIA_OK);
            }
        }

        return result;
    }


}
