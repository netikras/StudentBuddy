package com.netikras.studies.studentbuddy.core.data.meta.annotations;

import com.netikras.studies.studentbuddy.core.data.meta.Resource;
import com.netikras.studies.studentbuddy.core.data.meta.Action;

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
     * Hardcoded required roles for this particular action.<br/>
     * More requirements can be fetched from DB, cache, etc
     * @return
     */
    String[] requiredRoles() default {};

    /**
     * Should be the {@link #resource()} set to {@link Resource#_PARAM} this field<br/>
     * specifies the name of a param containing resource name
     * @return
     */
    String resourceParam() default "";

    /**
     * Should be the {@link #action()} set to {@link Action#_PARAM} this field<br/>
     * specifies the name of a param containing action name
     * @return
     */
    String actionParam() default "";



}
