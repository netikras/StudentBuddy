package com.netikras.studies.studentbuddy.api.aop.advices;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by netikras on 17.3.12.
 */
@Aspect
@Component
public class DaoMethodsAdvice {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @SuppressWarnings("Duplicates")
    @Around("com.unifier.coreservices.data.aop.pointcuts.TransactionalMethods.localDaoMethods()")
    public Object performDaoCall(ProceedingJoinPoint joinPoint) {

        GenericDaoImpl dao = null;
        Session session = null;
        Transaction transaction = null;

        Object result = null;

        try {
            dao = (GenericDaoImpl) joinPoint.getTarget();


            logger.debug("Preparing transaction");
//            session = dao.getCurrentSession();
            session = dao.getSessionFactory().openSession();

            try {
                logger.debug("Starting transaction");
                transaction = session.beginTransaction();
                dao.setCurrentSession(session);
                result = joinPoint.proceed();
                logger.debug("Committing transaction");
                transaction.commit();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
                logger.error("{}", throwable);
                if (transaction != null) {
                    logger.debug("Rolling back transaction");
                    transaction.rollback();
                }
//                throw throwable;
            } finally {
                session.close();
            }
        } catch (Exception e) {
            // ClassCastExc, NPE, etc.
            logger.error("{}", e);
        } finally {
            if (dao != null && session != null) {
                dao.setCurrentSession(null);
            }
            return result;
        }

    }
}
