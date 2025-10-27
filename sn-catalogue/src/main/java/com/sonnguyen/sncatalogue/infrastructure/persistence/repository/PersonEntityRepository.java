package com.sonnguyen.sncatalogue.infrastructure.persistence.repository;

import com.sonnguyen.sncatalogue.infrastructure.persistence.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PersonEntityRepository extends JpaRepository<PersonEntity, UUID> {
}
