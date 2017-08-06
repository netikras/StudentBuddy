package com.netikras.studies.studentbuddy.api.aop.pointcuts;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by netikras on 17.3.11.
 */

@Component
@Aspect
public class TransactionalMethods {

//    @Pointcut("execution(@com.unifier.coreservices.data.aop.annotations.DaoTransaction * *(..))")
    @Pointcut("execution(public * com.unifier.coreservices.data..*.*(..)) && @annotation(daoTransaction)")
    public void transactionalMethods(final DaoTransaction daoTransaction) {

    }

    @Pointcut("execution(public * com.unifier.commons.dao.GenericDao+.*(..))")
    public void daoMethods() {

    }

    @Pointcut("execution(public * com.unifier.commons.dao.GenericDaoImpl+.*(..))")
    public void localDaoMethods() {

    }

    @Pointcut("execution(public * com.unifier.coreservices.data.dao.GenericDaoRemoteImpl+.*(..))")
    public void remoteDaoMethods() {

    }




}
