package com.sonnguyen.sncatalogue.infrastructure.persistence.entity;

import com.sonnguyen.common.data.persistence.entity.AuditingEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@MappedSuperclass
public class TagEntity extends AuditingEntity {
    @Id
    private UUID id;

    private String title;
}
