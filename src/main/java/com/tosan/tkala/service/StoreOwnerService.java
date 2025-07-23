package com.tosan.tkala.service;

import com.tosan.tkala.domain.StoreOwner;
import com.tosan.tkala.repository.StoreOwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public interface StoreOwnerService {

    StoreOwner findStoreOwnerByMobileNumber(String mobileNumber);

}
