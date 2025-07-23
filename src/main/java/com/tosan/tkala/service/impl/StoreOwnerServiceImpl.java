package com.tosan.tkala.service.impl;

import com.tosan.tkala.domain.Store;
import com.tosan.tkala.domain.StoreOwner;
import com.tosan.tkala.repository.StoreOwnerRepository;
import com.tosan.tkala.service.StoreOwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StoreOwnerServiceImpl implements StoreOwnerService {

    private final StoreOwnerRepository storeOwnerRepository;


    @Override
    @Transactional
    public StoreOwner findStoreOwnerByMobileNumber(String mobileNumber) {

        StoreOwner storeOwner = storeOwnerRepository.findByMobileNumber(mobileNumber);
        Store store = new Store();
        store.setName("Tabriz Store");

        storeOwner.getStores().add(store);

        return storeOwner;
    }
}
