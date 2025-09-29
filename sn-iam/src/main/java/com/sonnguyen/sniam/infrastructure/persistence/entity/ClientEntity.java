package com.sonnguyen.sniam.infrastructure.persistence.entity;

import com.sonnguyen.common.data.jpa.entity.AuditingEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "client")
public class ClientEntity extends AuditingEntity {
    @Id
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "client_id")
    private String clientId;

    @Column(name = "client_secret")
    private String clientSecret;

    @Column(name = "provider")
    private String provider;

    @Column(name = "enabled")
    private Boolean enabled;

    @Column(name = "deleted")
    private Boolean deleted;
}
