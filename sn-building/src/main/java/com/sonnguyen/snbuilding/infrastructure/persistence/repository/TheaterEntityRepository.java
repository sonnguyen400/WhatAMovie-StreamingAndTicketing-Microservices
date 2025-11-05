package com.sonnguyen.snbuilding.infrastructure.persistence.repository;

import com.sonnguyen.snbuilding.infrastructure.persistence.entity.TheaterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TheaterEntityRepository extends JpaRepository<TheaterEntity, UUID> {
}
