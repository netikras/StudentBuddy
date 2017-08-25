package com.netikras.studies.studentbuddy.core.validator;

import com.netikras.studies.studentbuddy.core.data.api.dao.PersonDao;
import com.netikras.studies.studentbuddy.core.data.sys.dao.PasswordRequirementsDao;
import com.netikras.studies.studentbuddy.core.data.sys.dao.SettingsDao;
import com.netikras.studies.studentbuddy.core.data.sys.dao.UserDao;
import com.netikras.studies.studentbuddy.core.data.sys.model.User;
import com.netikras.tools.common.exception.ErrorsCollection;
import com.netikras.tools.common.exception.ValidationError;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static com.netikras.tools.common.remote.http.HttpStatus.NOT_FOUND;
import static com.netikras.tools.common.security.IntegrityUtils.ensureValue;

@Component
public class SystemValidator {

    @Resource
    private UserDao userDao;
    @Resource
    private PersonDao personDao;
    @Resource
    private SettingsDao settingsDao;
    @Resource
    private PasswordRequirementsDao passwordRequirementsDao;


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
         // TODO implement

        return errors;
    }


    private ErrorsCollection ensure(ErrorsCollection errors) {
        return ensureValue(errors, ErrorsCollection.class);
    }
}
