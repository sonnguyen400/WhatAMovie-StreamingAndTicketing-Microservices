package com.sonnguyen.sniam.infrastructure.domainrepository;

import com.sonnguyen.common.data.persistence.domain.repository.AbstractDomainRepository;
import com.sonnguyen.sniam.domain.Permission;
import com.sonnguyen.sniam.domain.repository.PermissionRepository;
import com.sonnguyen.sniam.infrastructure.mapper.PermissionEntityMapper;
import com.sonnguyen.sniam.infrastructure.persistence.entity.PermissionEntity;
import com.sonnguyen.sniam.infrastructure.persistence.repository.PermissionEntityRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class PermissionRepositoryImpl extends AbstractDomainRepository<Permission, PermissionEntity, UUID>
        implements PermissionRepository {

    public PermissionRepositoryImpl(PermissionEntityRepository repository,
                                    PermissionEntityMapper mapper) {
        super(repository, mapper);
    }
}
