package com.tosan.tkala.service.impl;

import com.tosan.tkala.domain.Store;
import com.tosan.tkala.domain.StoreOwner;
import com.tosan.tkala.repository.StoreOwnerRepository;
import com.tosan.tkala.repository.StoreRepository;
import com.tosan.tkala.service.StoreOwnerService;
import com.tosan.tkala.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;


    @Override
    @Transactional
    public Store findStoreByName(String name) {
        Store store = storeRepository.findStoreByName(name);

        return store;
    }

    @Override
    public void saveStore(Store storeWithProduct) {
        storeRepository.save(storeWithProduct);
    }
}
