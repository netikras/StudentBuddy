package com.netikras.studies.studentbuddy.core.data.sys;

import com.netikras.studies.studentbuddy.core.data.sys.model.PasswordRequirement;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemService {

    private List<PasswordRequirement> passwordRequirements;

    public List<PasswordRequirement> getPasswordRequirements() {
        return passwordRequirements;
    }

    public List<PasswordRequirement> refreshPaswordRequirements() {
        // refresh
        return getPasswordRequirements();
    }

    public void deletePasswordRequirement(String id) {
        // delete
    }

    public PasswordRequirement updatePasswordRequirement(PasswordRequirement requirement) {
        // update
        // no-refresh
        return requirement;
    }



}
