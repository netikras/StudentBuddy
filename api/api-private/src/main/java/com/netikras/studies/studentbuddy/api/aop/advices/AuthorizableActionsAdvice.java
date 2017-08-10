package com.netikras.studies.studentbuddy.api.aop.advices;

import com.netikras.studies.studentbuddy.api.filters.ThreadContext;
import com.netikras.studies.studentbuddy.commons.exception.StudBudUncheckedException;
import com.netikras.studies.studentbuddy.core.data.sys.SystemService;
import com.netikras.studies.studentbuddy.core.data.sys.model.User;
import com.netikras.studies.studentbuddy.core.meta.annotations.Authorizable;
import com.netikras.tools.common.remote.http.HttpStatus;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import static com.netikras.studies.studentbuddy.core.meta.Resource._PARAM;

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
            throw new StudBudUncheckedException()
                    .setMessage1("Unable to perform action " + authorizable.action())
                    .setMessage2("No current user in session. Not logged in?")
                    .setProbableCause("Resource: " + authorizable.resource())
                    .setStatusCode(HttpStatus.UNAUTHORIZED)
                    .setUrl("/login")
                    ;
        }

        com.netikras.studies.studentbuddy.core.meta.Resource resource = getResource(joinPoint, authorizable);
        com.netikras.studies.studentbuddy.core.meta.Action action = getAction(joinPoint, authorizable);


        boolean isAllowed = systemService.isUserAllowedToPerformAction(user, resource.name(), action.name());

        if (!isAllowed) {
            logger.debug("Resource access not authorized");
            throw new StudBudUncheckedException()
                    .setMessage1("Unable to perform action " + action)
                    .setMessage2("User is not authorized")
                    .setProbableCause("Resource: " + resource)
                    .setStatusCode(HttpStatus.UNAUTHORIZED)
                    ;
        }

        try {
            logger.info("Calling [{}]: {}->{}", joinPoint.getSignature().toShortString(), resource, action);
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {

        }
        return result;
    }


    private com.netikras.studies.studentbuddy.core.meta.Resource getResource(JoinPoint joinPoint, Authorizable authorizable) {
        com.netikras.studies.studentbuddy.core.meta.Resource resource = authorizable.resource();
        if (_PARAM.equals(resource)) {
            if (authorizable.resourceParam().isEmpty()) {
                return resource;
            }
            try {

                String value = getMethodParamValue(authorizable.resourceParam(), (MethodSignature) joinPoint.getSignature(), joinPoint.getArgs());

                if (value == null) {
                    logger.error("Possible mistake in the code. Method {} param with name {} is null -- cannot transform it to Resource",
                            joinPoint.toLongString(), authorizable.resourceParam());
                    return resource;
                }

                if (String.class.isAssignableFrom(value.getClass())) {
                    logger.error("Possible mistake in the code. Method {} param with name {} is non-string -- cannot transform it to Resource",
                            joinPoint.toLongString(), authorizable.resourceParam());
                    return resource;
                }

                value = value.toUpperCase();
                resource = com.netikras.studies.studentbuddy.core.meta.Resource.valueOf(value);

            } catch (Throwable throwable) {
                logger.error("Possible mistake in the code. Method {} param with name {} cannot be transformed to Resource",
                        joinPoint.toLongString(), authorizable.resourceParam());
                return resource;
            }
        }
        return resource;
    }


    private com.netikras.studies.studentbuddy.core.meta.Action getAction(JoinPoint joinPoint, Authorizable authorizable) {
        com.netikras.studies.studentbuddy.core.meta.Action action = authorizable.action();
        if (_PARAM.equals(action)) {
            if (authorizable.actionParam().isEmpty()) {
                return action;
            }
            try {

                String value = getMethodParamValue(authorizable.actionParam(), (MethodSignature) joinPoint.getSignature(), joinPoint.getArgs());

                if (value == null) {
                    logger.error("Possible mistake in the code. Method {} param with name {} is null -- cannot transform it to Resource",
                            joinPoint.toLongString(), authorizable.resourceParam());
                    return action;
                }

                if (String.class.isAssignableFrom(value.getClass())) {
                    logger.error("Possible mistake in the code. Method {} param with name {} is non-string -- cannot transform it to Resource",
                            joinPoint.toLongString(), authorizable.resourceParam());
                    return action;
                }

                value = value.toUpperCase();

                action = com.netikras.studies.studentbuddy.core.meta.Action.valueOf(value);

            } catch (Throwable throwable) {
                logger.error("Possible mistake in the code. Method {} param with name {} cannot be transformed to Resource",
                        joinPoint.toLongString(), authorizable.resourceParam());
                return action;
            }
        }
        return action;
    }

    private String getMethodParamValue(String paramName, MethodSignature signature, Object[] values) {
        String[] paramNames = signature.getParameterNames();
        for (int i = 0; i < paramNames.length; i++) {
            if (paramName.equalsIgnoreCase(paramNames[i])) {
                return (String) values[i];
            }
        }
        return null;
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }
}
