package com.tosan.tkala.repository;

import java.util.Collection;
import java.util.List;

public interface BaseRepository<T, ID> {

    T findById(ID id);

    List<T> findAll();

    void insert(T entity);

    void insertAll(Collection<T> entities);

    void delete(T entity);

    void update(ID id, String propertyName, Object propertyValue);

    List<T> findByProperty(String propertyName, Object propertyValue);

}
