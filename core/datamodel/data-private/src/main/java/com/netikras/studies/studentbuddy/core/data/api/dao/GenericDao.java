package com.netikras.studies.studentbuddy.core.data.api.dao;


import java.io.Serializable;
import java.util.List;

/**
 * Created by netikras on 16.11.18.
 */
public interface GenericDao<T, PK extends Serializable> {


    /**
     * Gets entity by given id.
     *
     * @param id of entity
     * @return persisted entity or null if not found.
     */
    T get(final PK id);


    /**
     * Saves given entity.
     *
     * @param entity entity to saved
     * @return saved entity id
     */
    PK save(final T entity);

    /**
     * Updates given entity.
     *
     * @param entity entity to update
     * @return updated entity
     */
    T update(final T entity);

    /**
     * Deletes given entity.
     *
     * @param entity
     */
    void delete(final T entity);


    void deleteById(PK id);

    void saveOrUpdate(T entity);

    /**
     * Gets total available entity count.
     *
     * @return total available {@link T}
     */
    Long getTotalCount();

    /**
     * Returns all entities in the table. Should not be used much
     *
     * @return all entities of type {@link T}.
     */
    List<T> getAll();

    /**
     * Returns page of entities, specified by offset and limit.
     *
     * @param offset
     * @param limit
     * @return
     */
    List<T> getWithOffset(int offset, int limit);

    List<T> getPage(int pageNum, int limit);
}
