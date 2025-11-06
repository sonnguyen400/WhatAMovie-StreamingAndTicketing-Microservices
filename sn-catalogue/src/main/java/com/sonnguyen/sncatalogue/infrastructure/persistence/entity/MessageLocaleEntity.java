package com.sonnguyen.sncatalogue.infrastructure.persistence.entity;

import com.sonnguyen.common.data.persistence.entity.AuditingEntity;
import com.sonnguyen.common.model.infrastructure.support.enums.DomainType;
import com.sonnguyen.common.model.infrastructure.support.enums.LocaleCode;
import com.sonnguyen.common.util.Validator;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@Entity
@MappedSuperclass
public class MessageLocaleEntity extends AuditingEntity {
    @Id
    private UUID id;

    @Column(name = "locale", length = Validator.Length.ENUM_MAX_LENGTH, nullable = false)
    @Enumerated(EnumType.STRING)
    private LocaleCode locale;

    @Column(name = "domain_id", nullable = false)
    private UUID domainId;

    @Column(name = "domain_type", length = Validator.Length.ENUM_MAX_LENGTH, nullable = false)
    @Enumerated(EnumType.STRING)
    private DomainType domainType;

    @Column(name = "properties")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, String> properties;
}
