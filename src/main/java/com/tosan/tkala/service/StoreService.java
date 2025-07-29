package com.tosan.tkala.service;

import com.tosan.tkala.domain.Store;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StoreService {

    Store findStoreByName(String name);

    void saveStore(Store storeWithProduct);

    void saveAllStore(List<Store> storeWithProduct);
}
