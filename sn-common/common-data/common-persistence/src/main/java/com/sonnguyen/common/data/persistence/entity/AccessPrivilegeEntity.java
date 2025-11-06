package com.sonnguyen.common.data.persistence.entity;

import com.sonnguyen.common.model.domain.TimePolicy;
import com.sonnguyen.common.model.infrastructure.support.enums.DomainType;
import com.sonnguyen.common.model.infrastructure.support.enums.LocaleCode;
import com.sonnguyen.common.model.infrastructure.support.enums.PrivilegeScope;
import com.sonnguyen.common.model.infrastructure.support.enums.ResourceScope;
import com.sonnguyen.common.util.Validator;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Setter
@Getter
@MappedSuperclass
public abstract class AccessPrivilegeEntity extends AuditingEntity {
    @Column(name = "resource_id", nullable = false)
    private UUID resourceId;

    @Column(name = "resource_type", nullable = false, length = Validator.Length.ENUM_MAX_LENGTH)
    @Enumerated(EnumType.STRING)
    private DomainType resourceType;

    @Column(name = "locale", length = Validator.Length.ENUM_MAX_LENGTH)
    @Enumerated(EnumType.STRING)
    private LocaleCode locale;

    @Column(name = "access_domain_id")
    private UUID accessDomainId;

    @Column(name = "domain_type", length = Validator.Length.ENUM_MAX_LENGTH)
    @Enumerated(EnumType.STRING)
    private DomainType domainType;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "time_policy")
    private TimePolicy timePolicy;

    @Column(name = "device_id")
    private UUID deviceId;

    @Column(name = "scope", length = Validator.Length.ENUM_MAX_LENGTH, nullable = false)
    @Enumerated(EnumType.STRING)
    private PrivilegeScope scope;

    @Column(name = "action", length = Validator.Length.ENUM_MAX_LENGTH, nullable = false)
    @Enumerated(EnumType.STRING)
    private ResourceScope action;
}
