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
@Table(name = "country")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CountryEntity extends AuditingEntity {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "code", length = 10)
    private String code;

    @Column(name = "phone_code", length = 10)
    private String phoneCode;

    @Column(name = "deleted")
    private Boolean deleted;
}
