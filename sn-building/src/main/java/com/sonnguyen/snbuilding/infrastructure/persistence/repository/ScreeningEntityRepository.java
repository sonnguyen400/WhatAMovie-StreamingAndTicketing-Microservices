package com.sonnguyen.snbuilding.infrastructure.persistence.repository;

import com.sonnguyen.snbuilding.infrastructure.persistence.entity.ScreeningEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ScreeningEntityRepository extends JpaRepository<ScreeningEntity, UUID> {
}
