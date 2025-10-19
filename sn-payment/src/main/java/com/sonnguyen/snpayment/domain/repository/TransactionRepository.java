package com.sonnguyen.snpayment.domain.repository;

import com.sonnguyen.common.data.core.DomainRepository;
import com.sonnguyen.snpayment.domain.Transaction;

import java.util.UUID;

public interface TransactionRepository extends DomainRepository<Transaction, UUID> {
}
