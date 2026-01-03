package com.sonnguyen.snbuilding.infrastructure.persistence.repository;

import com.sonnguyen.snbuilding.infrastructure.persistence.entity.SeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SeatEntityRepository extends JpaRepository<SeatEntity, UUID> {
}
