package com.sonnguyen.sniam.domain.repository;

import com.sonnguyen.common.data.core.DomainRepository;
import com.sonnguyen.sniam.domain.Customer;

import java.util.UUID;

public interface CustomerRepository extends DomainRepository<Customer, UUID> {
}
