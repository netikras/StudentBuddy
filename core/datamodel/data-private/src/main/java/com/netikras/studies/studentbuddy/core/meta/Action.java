package com.netikras.studies.studentbuddy.core.meta;

import com.netikras.studies.studentbuddy.core.data.sys.model.ActionRequirement;

/**
 * These actions describe API actions. Each action can be assigned a role, <br/>
 * effectively enforcing requestor to have either required role for the action <br/>
 * to be completed.
 * <br/>
 * See {@link ActionRequirement}
 * <br/>
 * HINT: Better use autocomplete for action selection.
 */
public enum Action {

    _PARAM,

    GET,
    GET_ALL,
    SEARCH,
    CREATE,
    MODIFY,
    DELETE,
    PURGE,
    COMMENT_GET,
    COMMENT_CREATE,
    COMMENT_MODIFY,
    COMMENT_DELETE,

    MODERATE,


    ;

}
