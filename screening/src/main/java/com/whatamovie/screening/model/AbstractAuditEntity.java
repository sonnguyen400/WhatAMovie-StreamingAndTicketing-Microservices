package com.whatamovie.screening.model;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.ZonedDateTime;

@MappedSuperclass
@Data
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractAuditEntity {
    @CreatedBy
    private String createBy;
    @LastModifiedBy
    private String lastModifiedBy;
    @CreatedDate
    private ZonedDateTime createdDate;
    @LastModifiedDate
    private ZonedDateTime lastModifiedDate;
}
