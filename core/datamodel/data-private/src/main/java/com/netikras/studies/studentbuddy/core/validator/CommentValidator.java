package com.netikras.studies.studentbuddy.core.validator;

import com.netikras.studies.studentbuddy.core.data.api.dao.CommentDao;
import com.netikras.studies.studentbuddy.core.data.api.dao.PersonDao;
import com.netikras.studies.studentbuddy.core.data.api.model.Comment;
import com.netikras.tools.common.exception.ErrorsCollection;
import com.netikras.tools.common.exception.ValidationError;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static com.netikras.tools.common.remote.http.HttpStatus.NOT_FOUND;
import static com.netikras.tools.common.security.IntegrityUtils.ensureValue;

@Component
public class CommentValidator {

    @Resource
    private CommentDao commentDao;

    @Resource
    private PersonDao personDao;

    @Transactional
    public ErrorsCollection validateForCreation(Comment comment, ErrorsCollection errors) {
        errors = ensure(errors);

        if (comment == null) {
            errors.add(new ValidationError()
                    .setSuggestion("Cannot create a non-existing comment")
                    .setMessage1("Comment is not given")
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
