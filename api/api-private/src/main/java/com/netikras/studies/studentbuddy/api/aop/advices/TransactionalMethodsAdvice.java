package com.netikras.studies.studentbuddy.api.aop.advices;

import com.netikras.studies.studentbuddy.api.filters.ThreadContext;
import com.netikras.studies.studentbuddy.core.data.sys.SystemService;
import com.netikras.studies.studentbuddy.core.data.sys.model.RolePermissions;
import com.netikras.studies.studentbuddy.core.data.sys.model.User;
import com.netikras.studies.studentbuddy.core.meta.annotations.Authorizable;
import com.netikras.tools.common.exception.FriendlyException;
import com.netikras.tools.common.exception.FriendlyUncheckedException;
import com.netikras.tools.common.remote.http.HttpStatus;
import com.unifier.commons.dao.GenericDaoImpl;
import com.unifier.coreservices.data.aop.annotations.DaoTransaction;
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
import java.util.List;

/**
 * Created by netikras on 17.3.11.
 */
@Aspect
@Component
public class TransactionalMethodsAdvice implements ApplicationContextAware {

    @Resource
    private ApplicationContext context;

    @Resource
    private SystemService systemService;


    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Around("com.netikras.studies.studentbuddy.api.aop.pointcuts.AuthorizableActions.authorizableMethods(authorizable)")
    public Object performAction(ProceedingJoinPoint joinPoint, Authorizable authorizable) {

        Object result = null;

        User user = ThreadContext.current().getUser();
        if (user == null) {
            throw new FriendlyUncheckedException()
                    .setMessage1("Unable to perform action " + authorizable.action()[0])
                    .setMessage2("No current user in session. Not logged in?")
                    .setProbableCause("Resource: " + authorizable.resource()[0])
                    .setStatusCode(HttpStatus.UNAUTHORIZED)
                    .setUrl("/login")
                    ;
        }

        boolean isAllowed = systemService.isUserAllowedToPerformAction(user, authorizable.resource()[0].name(), authorizable.action()[0].name());

        if (!isAllowed) {
            throw new FriendlyUncheckedException()
                    .setMessage1("Unable to perform action " + authorizable.action()[0])
                    .setMessage2("User is not authorized")
                    .setProbableCause("Resource: " + authorizable.resource()[0])
                    .setStatusCode(HttpStatus.UNAUTHORIZED)
                    ;
        }

        try {

            if (!daoTransaction.daoBeanName().isEmpty()) {
                dao = (GenericDaoImpl) context.getBean(daoTransaction.daoBeanName());
            } else {
                dao = (GenericDaoImpl) joinPoint.getTarget();
            }

            try {
                result = joinPoint.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            } finally {

            }
        } catch (Exception e) {
            // ClassCastExc, NPE, etc.
            logger.error("{}", e);
        } finally {
            return result;
        }

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }
}
