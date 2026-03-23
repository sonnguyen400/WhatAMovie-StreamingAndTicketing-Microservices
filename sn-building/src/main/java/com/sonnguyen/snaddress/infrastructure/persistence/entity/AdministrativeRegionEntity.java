package com.sonnguyen.snaddress.infrastructure.persistence.entity;

import com.sonnguyen.common.data.persistence.entity.AuditingEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "administrative_region")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdministrativeRegionEntity extends AuditingEntity {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "country_id")
    private UUID countryId;

    @Column(name = "parent_id")
    private UUID parentId;

    @Column(name = "name")
    private String name;

    @Column(name = "code", length = 50)
    private String code;

    @Column(name = "type", length = 50)
    private String type;

    @Column(name = "level")
    private Integer level;

    @Column(name = "deleted")
    private Boolean deleted;
}
