package com.sonnguyen.sniam.infrastructure.domainrepository;

import com.sonnguyen.common.data.persistence.domain.repository.AbstractDomainRepository;
import com.sonnguyen.sniam.domain.Customer;
import com.sonnguyen.sniam.domain.User;
import com.sonnguyen.sniam.domain.UserRole;
import com.sonnguyen.sniam.domain.repository.UserRepository;
import com.sonnguyen.sniam.infrastructure.mapper.CustomerEntityMapper;
import com.sonnguyen.sniam.infrastructure.mapper.UserEntityMapper;
import com.sonnguyen.sniam.infrastructure.mapper.UserRoleEntityMapper;
import com.sonnguyen.sniam.infrastructure.persistence.entity.CustomerEntity;
import com.sonnguyen.sniam.infrastructure.persistence.entity.UserEntity;
import com.sonnguyen.sniam.infrastructure.persistence.entity.UserRoleEntity;
import com.sonnguyen.sniam.infrastructure.persistence.repository.CustomerEntityRepository;
import com.sonnguyen.sniam.infrastructure.persistence.repository.UserEntityRepository;
import com.sonnguyen.sniam.infrastructure.persistence.repository.UserRoleEntityRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class UserRepositoryImpl extends AbstractDomainRepository<User, UserEntity, UUID>
        implements UserRepository {
    private final UserEntityRepository userEntityRepository;
    private final UserEntityMapper userEntityMapper;
    private final UserRoleEntityRepository userRoleEntityRepository;
    private final UserRoleEntityMapper userRoleEntityMapper;
    private final CustomerEntityRepository customerEntityRepository;
    private final CustomerEntityMapper customerEntityMapper;

    public UserRepositoryImpl(UserEntityRepository userEntityRepository,
                              UserEntityMapper userEntityMapper,
                              UserRoleEntityRepository userRoleEntityRepository,
                              UserRoleEntityMapper userRoleEntityMapper,
                              CustomerEntityRepository customerEntityRepository,
                              CustomerEntityMapper customerEntityMapper) {
        super(userEntityRepository, userEntityMapper);
        this.userEntityRepository = userEntityRepository;
        this.userEntityMapper = userEntityMapper;
        this.userRoleEntityRepository = userRoleEntityRepository;
        this.userRoleEntityMapper = userRoleEntityMapper;
        this.customerEntityRepository = customerEntityRepository;
        this.customerEntityMapper = customerEntityMapper;
    }

    @Override
    public Collection<User> saveAll(Collection<User> domains) {
        List<Customer> customers = domains.stream().map(User::getAssignedCustomer).filter(Objects::nonNull).toList();
        List<UserRole> roles = domains.stream().flatMap(it -> it.getRoles().stream()).toList();
        List<CustomerEntity> customerEntities = this.customerEntityMapper.toEntity(customers);
        List<UserRoleEntity> userRoleEntities = this.userRoleEntityMapper.toEntity(roles);
        super.saveAll(domains);
        this.customerEntityRepository.saveAll(customerEntities);
        this.userRoleEntityRepository.saveAll(userRoleEntities);
        return domains;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return this.userEntityRepository.findByUsername(username).map(this.userEntityMapper::toDomain);
    }

    @Override
    public Collection<User> enrichAll(Collection<User> domains) {
        List<UUID> userIds = domains.stream().map(User::getId).toList();
        List<UserRoleEntity> userRoleEntities = this.userRoleEntityRepository.findByUserIds(userIds);
        Map<UUID, List<UserRole>> userRoles = this.userRoleEntityMapper.toDomain(userRoleEntities)
                .stream()
                .collect(Collectors.groupingBy(UserRole::getUserId));
        for (User user : domains) {
            List<UserRole> roles = userRoles.get(user.getId());
            user.enrichRoles(roles);
        }
        return domains;
    }
}
