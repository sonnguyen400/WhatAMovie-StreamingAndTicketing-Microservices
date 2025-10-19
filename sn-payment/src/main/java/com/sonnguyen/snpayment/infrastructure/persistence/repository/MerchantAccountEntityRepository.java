package com.sonnguyen.snpayment.infrastructure.persistence.repository;

import com.sonnguyen.snpayment.infrastructure.persistence.entity.MerchantAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MerchantAccountEntityRepository extends JpaRepository<MerchantAccountEntity, UUID> {
}
