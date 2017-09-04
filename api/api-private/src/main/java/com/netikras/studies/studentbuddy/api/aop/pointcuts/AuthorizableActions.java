package com.netikras.studies.studentbuddy.api.aop.pointcuts;

import com.netikras.studies.studentbuddy.core.meta.annotations.Authorizable;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuthorizableActions {

//    @Pointcut("execution(public * com.netikras.studies.studentbuddy..*.*(..)) && @annotation(authorizable)")
    @Pointcut("execution(* com.netikras.studies.studentbuddy..*.*(..)) && @annotation(authorizable)")
    public void authorizableMethods(final Authorizable authorizable) {

    }

}
