package com.tosan.tkala.service;

import com.tosan.tkala.domain.TransactionHistory;

public interface TransactionHistoryService {

    void log(TransactionHistory transactionHistory);
}
