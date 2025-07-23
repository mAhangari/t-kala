package com.tosan.tkala.repository;

import com.tosan.tkala.domain.StoreOwner;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreOwnerRepository extends UserRepository<StoreOwner, Long> {

    StoreOwner findByMobileNumber(String mobileNumber);
}
