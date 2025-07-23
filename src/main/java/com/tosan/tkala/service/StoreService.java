package com.tosan.tkala.service;

import com.tosan.tkala.domain.Store;
import com.tosan.tkala.domain.StoreOwner;
import org.springframework.stereotype.Service;

@Service
public interface StoreService {

    Store findStoreByName(String name);

    void saveStore(Store storeWithProduct);
}
