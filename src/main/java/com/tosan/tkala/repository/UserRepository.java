package com.tosan.tkala.repository;

import com.tosan.tkala.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository<T extends User, ID> extends CrudRepository<T, ID> {
}
