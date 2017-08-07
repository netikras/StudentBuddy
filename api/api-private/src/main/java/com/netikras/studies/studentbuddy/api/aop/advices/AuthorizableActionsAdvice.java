package com.netikras.studies.studentbuddy.api.aop.advices;

import com.netikras.studies.studentbuddy.api.filters.ThreadContext;
import com.netikras.studies.studentbuddy.core.data.sys.SystemService;
import com.netikras.studies.studentbuddy.core.data.sys.model.User;
import com.netikras.studies.studentbuddy.core.meta.annotations.Authorizable;
import com.netikras.tools.common.exception.FriendlyUncheckedException;
import com.netikras.tools.common.remote.http.HttpStatus;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by netikras on 17.3.11.
 */
@Aspect
@Component
public class AuthorizableActionsAdvice implements ApplicationContextAware {

    @Resource
    private ApplicationContext context;

    @Resource
    private SystemService systemService;


    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Around("com.netikras.studies.studentbuddy.api.aop.pointcuts.AuthorizableActions.authorizableMethods(authorizable)")
    public Object performAction(ProceedingJoinPoint joinPoint, Authorizable authorizable) {

        Object result = null;

        User user = ThreadContext.current().getUser();

        logger.debug("User {} attempting to perform action {} on resource {}",
                user == null ? null : user.getName(), authorizable.action(), authorizable.resource());

        if (user == null) {
            throw new FriendlyUncheckedException()
                    .setMessage1("Unable to perform action " + authorizable.action())
                    .setMessage2("No current user in session. Not logged in?")
                    .setProbableCause("Resource: " + authorizable.resource())
                    .setStatusCode(HttpStatus.UNAUTHORIZED)
                    .setUrl("/login")
                    ;
        }

        boolean isAllowed = systemService.isUserAllowedToPerformAction(user, authorizable.resource().name(), authorizable.action().name());

        if (!isAllowed) {
            logger.debug("Resource access not authorized");
            throw new FriendlyUncheckedException()
                    .setMessage1("Unable to perform action " + authorizable.action())
                    .setMessage2("User is not authorized")
                    .setProbableCause("Resource: " + authorizable.resource())
                    .setStatusCode(HttpStatus.UNAUTHORIZED)
                    ;
        }

        try {
            logger.info("Calling [{}]: {}->{}", joinPoint.getSignature().toShortString(), authorizable.resource(), authorizable.action());
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {

        }
        return result;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }
}
