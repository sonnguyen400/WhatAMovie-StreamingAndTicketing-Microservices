package com.sonnguyen.sniam.domain.repository;

import com.sonnguyen.common.data.jpa.domain.repository.DomainRepository;
import com.sonnguyen.sniam.domain.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends DomainRepository<User, UUID> {
    Optional<User> findByUsername(String username);
}
