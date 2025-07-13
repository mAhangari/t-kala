package com.tosan.tkala.repository;

import com.tosan.tkala.Domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository<T extends User, ID> extends CrudRepository<T, ID> {
}
