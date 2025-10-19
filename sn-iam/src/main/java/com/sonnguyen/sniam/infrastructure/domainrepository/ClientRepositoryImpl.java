package com.sonnguyen.sniam.infrastructure.domainrepository;

import com.sonnguyen.common.data.persistence.domain.repository.AbstractDomainRepository;
import com.sonnguyen.sniam.domain.Client;
import com.sonnguyen.sniam.domain.repository.ClientRepository;
import com.sonnguyen.sniam.infrastructure.mapper.ClientEntityMapper;
import com.sonnguyen.sniam.infrastructure.persistence.entity.ClientEntity;
import com.sonnguyen.sniam.infrastructure.persistence.repository.ClientEntityRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class ClientRepositoryImpl extends AbstractDomainRepository<Client, ClientEntity, UUID>
        implements ClientRepository {

    private final ClientEntityRepository clientEntityRepository;
    private final ClientEntityMapper clientEntityMapper;

    public ClientRepositoryImpl(ClientEntityRepository clientEntityRepository,
                                ClientEntityMapper clientEntityMapper) {
        super(clientEntityRepository, clientEntityMapper);
        this.clientEntityRepository = clientEntityRepository;
        this.clientEntityMapper = clientEntityMapper;
    }

    @Override
    public Optional<Client> findByClientId(String clientId) {
        return this.clientEntityRepository.findByClientId(clientId)
                .map(this.clientEntityMapper::toDomain);
    }
}
