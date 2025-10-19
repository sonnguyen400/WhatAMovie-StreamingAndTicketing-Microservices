package com.sonnguyen.snpayment.infrastructure.domainrepository;

import com.sonnguyen.common.data.persistence.domain.repository.AbstractDomainRepository;
import com.sonnguyen.snpayment.domain.Transaction;
import com.sonnguyen.snpayment.domain.repository.TransactionRepository;
import com.sonnguyen.snpayment.infrastructure.persistence.entity.TransactionEntity;
import com.sonnguyen.snpayment.infrastructure.persistence.mapper.TransactionEntityMapper;
import com.sonnguyen.snpayment.infrastructure.persistence.repository.TransactionEntityRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class TransactionRepositoryImpl extends AbstractDomainRepository<Transaction, TransactionEntity, UUID>
        implements TransactionRepository {
    private TransactionEntityRepository transactionEntityRepository;
    private TransactionEntityMapper transactionEntityMapper;

    public TransactionRepositoryImpl(TransactionEntityRepository transactionEntityRepository,
                                     TransactionEntityMapper transactionEntityMapper) {
        super(transactionEntityRepository, transactionEntityMapper);
        this.transactionEntityRepository = transactionEntityRepository;
        this.transactionEntityMapper = transactionEntityMapper;
    }
}
