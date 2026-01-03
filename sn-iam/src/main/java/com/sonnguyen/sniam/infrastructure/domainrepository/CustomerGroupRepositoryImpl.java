package com.sonnguyen.sniam.infrastructure.domainrepository;

import com.sonnguyen.common.data.persistence.domain.repository.AbstractDomainRepository;
import com.sonnguyen.sniam.domain.CustomerGroup;
import com.sonnguyen.sniam.domain.repository.CustomerGroupRepository;
import com.sonnguyen.sniam.infrastructure.mapper.CustomerGroupEntityMapper;
import com.sonnguyen.sniam.infrastructure.mapper.GroupCustomerCompoundEntityMapper;
import com.sonnguyen.sniam.infrastructure.persistence.entity.CustomerGroupEntity;
import com.sonnguyen.sniam.infrastructure.persistence.repository.CustomerGroupEntityRepository;
import com.sonnguyen.sniam.infrastructure.persistence.repository.GroupCustomerCompoundEntityRepository;

import java.util.UUID;

public class CustomerGroupRepositoryImpl
        extends AbstractDomainRepository<CustomerGroup, CustomerGroupEntity, UUID>
        implements CustomerGroupRepository {

    private final CustomerGroupEntityRepository customerGroupEntityRepository;
    private final CustomerGroupEntityMapper customerGroupEntityMapper;
    private final GroupCustomerCompoundEntityRepository compoundEntityRepository;
    private final GroupCustomerCompoundEntityMapper compoundEntityMapper;


    public CustomerGroupRepositoryImpl(CustomerGroupEntityRepository customerGroupEntityRepository, CustomerGroupEntityMapper customerGroupEntityMapper, GroupCustomerCompoundEntityRepository compoundEntityRepository, GroupCustomerCompoundEntityMapper compoundEntityMapper) {
        super(customerGroupEntityRepository, customerGroupEntityMapper);
        this.customerGroupEntityRepository = customerGroupEntityRepository;
        this.customerGroupEntityMapper = customerGroupEntityMapper;
        this.compoundEntityRepository = compoundEntityRepository;
        this.compoundEntityMapper = compoundEntityMapper;
    }
}
