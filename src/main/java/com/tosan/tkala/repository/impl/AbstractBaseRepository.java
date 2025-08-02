package com.tosan.tkala.repository.impl;

import com.tosan.tkala.domain.Product;
import com.tosan.tkala.repository.BaseRepository;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public abstract class AbstractBaseRepository<T, ID> implements BaseRepository<T, ID> {

    @PersistenceContext
    protected EntityManager em;
    private final Class<T> clazz;

    public AbstractBaseRepository(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T findById(ID id) {
        return em.find(clazz, id);
    }

    @Override
    public List<T> findAll() {
        return em.createQuery("from " + clazz.getName(), clazz).getResultList();
    }

    @Override
    public void insert(T entity) {
        em.persist(entity);
    }

    @Override
    public void insertAll(Collection<T> entities) {
        for (T entity : entities) {
            em.persist(entity);
        }
    }

    @Override
    public void delete(T entities) {
        em.remove(entities);
    }

    @Override
    public void update(ID id, String propertyName, Object propertyValue) {
        // TODO:: Must be implement by student
    }

    @Override
    public List<T> findByProperty(String propertyName, Object propertyValue) {
        // TODO:: Must be implement by student
        return null;
    }

}
