package com.sonnguyen.snstorage.infrastructure.persistence.entity;

import com.sonnguyen.common.data.persistence.entity.AuditingEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "file_entity")
public class FileEntity extends AuditingEntity {
    @Id
    private UUID id;

    @Column(name = "original_name")
    private String originalName;

    @Column(name = "name")
    private String name;

    @Column(name = "access_counter")
    private Integer accessCounter;
}
