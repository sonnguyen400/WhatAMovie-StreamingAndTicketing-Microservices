package com.sonnguyen.common.data.persistence.repository;

import com.sonnguyen.common.data.persistence.entity.MessageLocaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MessageLocaleEntityRepository extends JpaRepository<MessageLocaleEntity, UUID> {
}
