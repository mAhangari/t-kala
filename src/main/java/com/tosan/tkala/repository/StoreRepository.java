package com.tosan.tkala.repository;

import com.tosan.tkala.domain.Store;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends CrudRepository<Store, Long> {

    Store findStoreByName(String name);
}
