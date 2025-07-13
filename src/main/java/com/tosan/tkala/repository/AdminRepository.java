package com.tosan.tkala.repository;

import com.tosan.tkala.Domain.Admin;
import com.tosan.tkala.Domain.Customer;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends UserRepository<Admin, Long> {
}
