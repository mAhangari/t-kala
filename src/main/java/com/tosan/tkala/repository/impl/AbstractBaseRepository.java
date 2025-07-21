package com.tosan.tkala.repository.impl;

import com.tosan.tkala.repository.BaseRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public abstract class AbstractBaseRepository<T, ID> implements BaseRepository<T, ID> {

    private EntityManagerFactory emf;
    protected EntityManager em;
    private final Class<T> clazz;

    public AbstractBaseRepository(EntityManagerFactory entityManagerFactory, Class<T> clazz) {
        this.emf = entityManagerFactory;
        this.clazz = clazz;
    }

    @Override
    public T findById(ID id) {
        return getEm().find(clazz, id);
    }

    @Override
    public List<T> findAll() {
        return getEm().createQuery("from " + clazz.getName(), clazz).getResultList();
    }

    @Override
    public void insert(T entity) {
        getEm().persist(entity);
    }

    @Override
    public void delete(T entity) {
        getEm().remove(entity);
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

    @Override
    public EntityManager getEm() {
        if (em != null && em.isOpen())
            return em;

        this.em = emf.createEntityManager();
        return em;
    }
}
