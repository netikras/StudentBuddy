package com.netikras.studies.studentbuddy.core.meta.annotations;

import com.netikras.studies.studentbuddy.core.meta.Action;
import com.netikras.studies.studentbuddy.core.meta.Resource;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

@Retention(RetentionPolicy.RUNTIME)
@Target({METHOD, TYPE})
public @interface Authorizable {

    /**
     * Annotated resource refers to some action. This is the action name.
     * @return
     */
    Action action();

    Resource resource();

    /**
     * Hardcoded required roles for this particular action action.<br/>
     * More requirements can be fetched from DB, cache, etc
     * @return
     */
    String[] requiredRoles() default {};



}
