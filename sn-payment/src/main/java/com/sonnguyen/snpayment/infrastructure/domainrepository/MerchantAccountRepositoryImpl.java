package com.sonnguyen.snpayment.infrastructure.domainrepository;

import com.sonnguyen.common.data.persistence.domain.repository.AbstractDomainRepository;
import com.sonnguyen.snpayment.domain.MerchantAccount;
import com.sonnguyen.snpayment.domain.repository.MerchantAccountRepository;
import com.sonnguyen.snpayment.infrastructure.persistence.entity.MerchantAccountEntity;
import com.sonnguyen.snpayment.infrastructure.persistence.mapper.MerchantAccountEntityMapper;
import com.sonnguyen.snpayment.infrastructure.persistence.repository.MerchantAccountEntityRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class MerchantAccountRepositoryImpl extends AbstractDomainRepository<MerchantAccount, MerchantAccountEntity, UUID>
        implements MerchantAccountRepository {

    public MerchantAccountEntityMapper merchantAccountEntityMapper;
    private MerchantAccountEntityRepository merchantAccountEntityRepository;

    public MerchantAccountRepositoryImpl(MerchantAccountEntityRepository merchantAccountEntityRepository,
                                         MerchantAccountEntityMapper merchantAccountEntityMapper) {
        super(merchantAccountEntityRepository, merchantAccountEntityMapper);
        this.merchantAccountEntityRepository = merchantAccountEntityRepository;
        this.merchantAccountEntityMapper = merchantAccountEntityMapper;
    }
}
