package com.sonnguyen.sniam.domain.repository;

import com.sonnguyen.common.data.jpa.domain.repository.DomainRepository;
import com.sonnguyen.sniam.domain.Client;

import java.util.Optional;
import java.util.UUID;

public interface ClientRepository extends DomainRepository<Client, UUID> {
    Optional<Client> findByClientId(String clientId);
}
