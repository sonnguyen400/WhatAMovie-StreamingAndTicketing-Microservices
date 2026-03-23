package com.sonnguyen.sniam.infrastructure.domainrepository;

import com.sonnguyen.common.data.persistence.domain.repository.AbstractDomainRepository;
import com.sonnguyen.sniam.domain.Customer;
import com.sonnguyen.sniam.domain.repository.CustomerRepository;
import com.sonnguyen.sniam.infrastructure.mapper.CustomerEntityMapper;
import com.sonnguyen.sniam.infrastructure.persistence.entity.CustomerEntity;
import com.sonnguyen.sniam.infrastructure.persistence.repository.CustomerEntityRepository;

import java.util.UUID;

public class CustomerRepositoryImpl extends AbstractDomainRepository<Customer, CustomerEntity, UUID>
        implements CustomerRepository {
    private final CustomerEntityRepository customerEntityRepository;
    private final CustomerEntityMapper customerEntityMapper;

    public CustomerRepositoryImpl(CustomerEntityRepository customerEntityRepository,
                                  CustomerEntityMapper customerEntityMapper) {
        super(customerEntityRepository, customerEntityMapper);
        this.customerEntityRepository = customerEntityRepository;
        this.customerEntityMapper = customerEntityMapper;
    }
}
