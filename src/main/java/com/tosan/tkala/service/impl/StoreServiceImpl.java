package com.tosan.tkala.service.impl;

import com.tosan.tkala.domain.Store;
import com.tosan.tkala.repository.StoreRepository;
import com.tosan.tkala.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    @Transactional
    public void saveStore(Store storeWithProduct) {
        storeRepository.save(storeWithProduct);
    }

    @Override
    public void saveAllStore(List<Store> storeWithProduct) {
        storeRepository.saveAll(storeWithProduct);
    }


}
