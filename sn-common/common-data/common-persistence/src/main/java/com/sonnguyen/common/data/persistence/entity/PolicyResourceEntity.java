package com.sonnguyen.common.data.persistence.entity;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@MappedSuperclass
public abstract class PolicyResourceEntity extends AuditingEntity {
}
