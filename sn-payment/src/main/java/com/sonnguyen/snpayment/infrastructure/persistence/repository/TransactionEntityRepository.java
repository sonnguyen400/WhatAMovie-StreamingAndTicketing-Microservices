package com.sonnguyen.snpayment.infrastructure.persistence.repository;

import com.sonnguyen.snpayment.infrastructure.constant.TransactionDomainType;
import com.sonnguyen.snpayment.infrastructure.persistence.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionEntityRepository extends JpaRepository<TransactionEntity, UUID> {
    @Query("FROM TransactionEntity T WHERE T.deleted = false AND T.domainId in :domainIds and T.domainType = :transactionDomainType")
    Collection<TransactionEntity> findAllByDomainKeys(List<UUID> domainIds, TransactionDomainType transactionDomainType);
}
