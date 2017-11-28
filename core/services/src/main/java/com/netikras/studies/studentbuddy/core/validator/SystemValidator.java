package com.netikras.studies.studentbuddy.core.validator;

import com.netikras.studies.studentbuddy.core.data.meta.PasswordValidationResult;
import com.netikras.studies.studentbuddy.core.data.meta.PasswordValidator;
import com.netikras.studies.studentbuddy.core.data.sys.dao.SettingsDao;
import com.netikras.studies.studentbuddy.core.data.sys.model.PasswordRequirement;
import com.netikras.studies.studentbuddy.core.data.sys.model.ResourceActionLink;
import com.netikras.studies.studentbuddy.core.data.sys.model.Role;
import com.netikras.studies.studentbuddy.core.data.sys.model.SystemSetting;
import com.netikras.studies.studentbuddy.core.data.sys.model.User;
import com.netikras.studies.studentbuddy.core.data.sys.model.UserRole;
import com.netikras.studies.studentbuddy.core.service.SystemService;
import com.netikras.tools.common.exception.ErrorsCollection;
import com.netikras.tools.common.exception.ValidationError;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.Map;

import static com.netikras.tools.common.remote.http.HttpStatus.CONFLICT;
import static com.netikras.tools.common.remote.http.HttpStatus.NOT_FOUND;
import static com.netikras.tools.common.security.IntegrityUtils.ensureValue;
import static com.netikras.tools.common.security.IntegrityUtils.isNullOrEmpty;

@Component
public class SystemValidator {

    @Resource
    private SettingsDao settingsDao;
    @Resource
    private SystemService systemService;
    @Resource
    private EntityProvider entityProvider;


    @Transactional
    public ErrorsCollection validateForCreation(User user, ErrorsCollection errors) {
        errors = ensure(errors);

        if (user == null) {
            errors.add(new ValidationError()
                    .setSuggestion("Cannot create a non-existing user")
                    .setMessage1("User is not given")
                    .setStatus(NOT_FOUND.getCode())
            );
            return errors;
        }

        if (isNullOrEmpty(user.getPasswordHash())) {
            errors.add(new ValidationError()
                    .setSuggestion("User must have a password")
                    .setMessage1("User password missing")
                    .setStatus(NOT_FOUND.getCode())
            );
        }

        if (isNullOrEmpty(user.getAlias())) {
            user.setAlias(user.getName());
        }

        if (isNullOrEmpty(user.getName())) {
            errors.add(new ValidationError()
                    .setSuggestion("User must have a name")
                    .setMessage1("User name missing")
                    .setStatus(NOT_FOUND.getCode())
            );
        }

        User existingUser = entityProvider.fetch(user);
        if (existingUser != null) {
            errors.add(new ValidationError()
                    .setSuggestion("Every user must have a unique username and alias")
                    .setMessage1("User already exists")
                    .setStatus(CONFLICT.getCode())
            );
        }

        user.setPerson(entityProvider.fetch(user.getPerson()));

        if (user.getPerson() == null) {
            errors.add(new ValidationError()
                    .setSuggestion("User must be linked to an already existing person")
                    .setMessage1("User person missing")
                    .setStatus(NOT_FOUND.getCode())
            );
        }

        if (!isNullOrEmpty(user.getRoles())) {
            for (Iterator<UserRole> iterator = user.getRoles().iterator(); iterator.hasNext(); ) {
                UserRole userRole = iterator.next();

                if (userRole == null) {
                    iterator.remove();
                    continue;
                }

                Role role = userRole.getRole();
                if (role == null) {
                    iterator.remove();
                    continue;
                }

                Role existingRole = entityProvider.fetch(role);
                if (existingRole == null) {
                    role.setId(null);
                    role.setCreatedBy(null); // FIXME
                } else {
                    userRole.setRole(existingRole);
                }

                userRole.setUser(user);
            }
        }

        user.setId(null);
        return errors;
    }


    @Transactional
    public ErrorsCollection validateForCreation(PasswordRequirement requirement, ErrorsCollection errors) {
        errors = ensure(errors);

        if (requirement == null) {
            errors.add(new ValidationError()
                    .setSuggestion("Cannot create a non-existing password requirement")
                    .setMessage1("Password requirement is not given")
                    .setStatus(NOT_FOUND.getCode())
            );
            return errors;
        }

        if (isNullOrEmpty(requirement.getTitle())) {
            errors.add(new ValidationError()
                    .setSuggestion("Password requirements must have a title")
                    .setMessage1("Password requirement title missing")
                    .setStatus(NOT_FOUND.getCode())
            );
        }

        if (isNullOrEmpty(requirement.getWarningMessage())) {
            errors.add(new ValidationError()
                    .setSuggestion("Password requirements must have a message to display when requirement is not met")
                    .setMessage1("Password requirement message missing")
                    .setStatus(NOT_FOUND.getCode())
            );
        }

        requirement.setId(null);
        return errors;
    }

    @Transactional
    public ErrorsCollection validateForCreation(SystemSetting setting, ErrorsCollection errors) {
        errors = ensure(errors);

        if (setting == null) {
            errors.add(new ValidationError()
                    .setSuggestion("Cannot create a non-existing setting")
                    .setMessage1("Setting is not given")
                    .setStatus(NOT_FOUND.getCode())
            );
            return errors;
        }

        if (isNullOrEmpty(setting.getName())) {
            errors.add(new ValidationError()
                    .setSuggestion("System setting must have a name")
                    .setMessage1("Setting name missing")
                    .setStatus(NOT_FOUND.getCode())
            );
        } else {
            SystemSetting existingSetting = settingsDao.findByName(setting.getName());
            if (existingSetting != null) {
                errors.add(new ValidationError()
                        .setSuggestion("System setting with such name already exists")
                        .setMessage1("Setting already exists")
                        .setCausedBy(existingSetting.getName())
                        .setStatus(CONFLICT.getCode())
                );
            }
        }


        setting.setId(null);
        return errors;
    }

    @Transactional
    public ErrorsCollection validateForCreation(ResourceActionLink permission, ErrorsCollection errors) {
        errors = ensure(errors);

        if (permission == null) {
            errors.add(new ValidationError()
                    .setSuggestion("Cannot create a non-existing role permission")
                    .setMessage1("Role permission is not given")
                    .setStatus(NOT_FOUND.getCode())
            );
            return errors;
        }

        Role role = permission.getRole();

        if (role == null) {
            errors.add(new ValidationError()
                    .setSuggestion("Role permissions must link a role and one or more resource actions")
                    .setMessage1("Role missing")
                    .setStatus(NOT_FOUND.getCode())
            );
            permission.setId(null);
            return errors;
        }


        Role existingRole = entityProvider.fetch(role);

        if (existingRole == null) {
            errors.add(new ValidationError()
                    .setSuggestion("Each role can be linked to a single RolePermissions collection. " +
                            "If collection already exists feel free to modify it")
                    .setMessage1("Role permissions already exist")
                    .setStatus(CONFLICT.getCode())
            );
        }

        permission.setId(null);
        return errors;
    }


    @Transactional
    public ErrorsCollection validatePassword(String password, ErrorsCollection errors) {
        errors = ensure(errors);

        if (isNullOrEmpty(password)) {
            errors.add(new ValidationError()
                    .setSuggestion("Password is not provided")
                    .setMessage1("Password is not given")
                    .setStatus(NOT_FOUND.getCode())
            );
            return errors;
        }

        PasswordValidator validator = new PasswordValidator();
        validator.setRequirements(systemService.getPasswordRequirements());

        PasswordValidationResult result = validator.validate(password);

        if (!isNullOrEmpty(result.getCriteria())) {
            for (Map.Entry<String, String> stringStringEntry : result.getCriteria().entrySet()) {
                if (!isNullOrEmpty(stringStringEntry.getValue()) && !PasswordValidationResult.CRITERIA_OK.equals(stringStringEntry.getValue())) {
                    errors.add(new ValidationError()
                            .setMessage1(stringStringEntry.getValue())
                            .setStatus(NOT_FOUND.getCode())
                    );
                }
            }
        }

        return errors;
    }


    public ErrorsCollection validateForRemoval(User user, ErrorsCollection errors) {
        errors = ensure(errors);

        if (user == null) {
            errors.add(new ValidationError()
                    .setSuggestion("User is not provided")
                    .setMessage1("User is not given")
                    .setStatus(NOT_FOUND.getCode())
            );
            return errors;
        }

        return errors;
    }

    private ErrorsCollection ensure(ErrorsCollection errors) {
        return ensureValue(errors, ErrorsCollection.class);
    }


}
