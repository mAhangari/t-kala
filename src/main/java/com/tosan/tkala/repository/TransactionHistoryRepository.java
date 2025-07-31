package com.tosan.tkala.repository;

import com.tosan.tkala.domain.TransactionHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionHistoryRepository extends CrudRepository<TransactionHistory, Long> {
}
