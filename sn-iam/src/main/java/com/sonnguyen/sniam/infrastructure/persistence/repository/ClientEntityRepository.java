package com.sonnguyen.sniam.infrastructure.persistence.repository;

import com.sonnguyen.sniam.infrastructure.persistence.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClientEntityRepository extends JpaRepository<ClientEntity, UUID> {


}
