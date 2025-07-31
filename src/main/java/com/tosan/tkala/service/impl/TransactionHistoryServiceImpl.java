package com.tosan.tkala.service.impl;

import com.tosan.tkala.domain.TransactionHistory;
import com.tosan.tkala.repository.TransactionHistoryRepository;
import com.tosan.tkala.service.TransactionHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TransactionHistoryServiceImpl implements TransactionHistoryService {

    private final TransactionHistoryRepository historyRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void log(TransactionHistory transactionHistory) {
        historyRepository.save(transactionHistory);
    }
}
