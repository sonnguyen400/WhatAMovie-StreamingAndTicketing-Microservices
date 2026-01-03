package com.sonnguyen.snbuilding.infrastructure.persistence.repository;

import com.sonnguyen.snbuilding.infrastructure.persistence.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoomEntityRepository extends JpaRepository<RoomEntity, UUID> {
}
