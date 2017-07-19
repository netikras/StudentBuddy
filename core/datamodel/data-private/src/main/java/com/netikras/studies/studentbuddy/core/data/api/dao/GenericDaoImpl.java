package com.netikras.studies.studentbuddy.core.data.api.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;

import java.io.Serializable;
import java.util.List;

/**
 * Created by netikras on 16.11.18.
 */
public abstract class GenericDaoImpl<T, PK extends Serializable> implements GenericDao<T, PK> {

    protected static final int BATCH_SIZE = 20;

    private Class<T> entityClass;

    private Session currentSession = null;

//    @Resource(name = "sessionFactory")
//    private SessionFactory factory = null;

    @Override
//    @Transactional
    @SuppressWarnings("unchecked")
    public T get(final PK id) {
        return (T) getCurrentSession().get(getPersistentClass(), id);
    }

    @Override
//    @Transactional
    @SuppressWarnings("unchecked")
    public PK save(final T entity) {
        return (PK) getCurrentSession().save(entity);
    }

    @Override
//    @Transactional
    public T update(final T entity) {
        getCurrentSession().update(entity);
        return entity;
    }

    @Override
//    @Transactional
    public void delete(final T entity) {
        getCurrentSession().delete(entity);
    }

    @Override
//    @Transactional
    public void deleteById(final PK id) {
        T entity = get(id);

        if (entity != null) {
            getCurrentSession().delete(entity);
        }
    }


    @Override
//    @Transactional
    public void saveOrUpdate(final T entity) {
        getCurrentSession().saveOrUpdate(entity);
    }


    @Override
//    @Transactional
    public Long getTotalCount() {
        return (Long) buildDefaultCriteria()
                .setProjection(Projections.count("id"))
                .uniqueResult();
    }

    @SuppressWarnings("unchecked")
//    @Transactional
    @Override
    public List<T> getAll() {
        return buildDefaultCriteria().list();
    }

    @SuppressWarnings("unchecked")
//    @Transactional
    @Override
    public List<T> getWithOffset(final int offset, final int limit) {
        return buildDefaultCriteria()
                .setFirstResult(offset)
                .setMaxResults(limit)
                .list();
    }

    @SuppressWarnings("unchecked")
//    @Transactional
    @Override
    public List<T> getPage(int pageNum, int limit) {
        return getWithOffset(limit + pageNum, limit);
    }


    protected void flush() {
        getCurrentSession().flush();
    }

    protected void evict(final T entity) {
        getCurrentSession().evict(entity);
    }

    /**
     * Type of entity class. Required due to java limitation and not wanting to use reflection.
     *
     * @return typeof T.
     */
    protected Class<T> getPersistentClass() {
        return entityClass;
    };

    protected Criteria buildDefaultCriteria() {
        return getCurrentSession().createCriteria(getPersistentClass());
    }

    protected Criteria buildDefaultCriteria(String alias) {
        return getCurrentSession().createCriteria(getPersistentClass(), alias);
    }

    /**
     * Hibernate session factory for managing sessions.
     */
    public abstract SessionFactory getSessionFactory();
//    protected SessionFactory getFactory() {
//        return factory;
//    }

    public Session getCurrentSession() {
        Session session = this.currentSession;
        if (session == null || ! session.isOpen()) {
            try {
                session = getSessionFactory().getCurrentSession();
            } catch (Exception e) {
                session = getSessionFactory().openSession();
            }
        }

        return session;
    }

    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }
}

