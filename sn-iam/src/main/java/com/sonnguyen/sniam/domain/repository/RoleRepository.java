package com.sonnguyen.sniam.domain.repository;

import com.sonnguyen.common.data.jpa.domain.repository.DomainRepository;
import com.sonnguyen.sniam.domain.Role;

import java.util.UUID;

public interface RoleRepository extends DomainRepository<Role, UUID> {
}
