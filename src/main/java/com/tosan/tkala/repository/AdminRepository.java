package com.tosan.tkala.repository;

import com.tosan.tkala.domain.Admin;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends UserRepository<Admin, Long> {
}
