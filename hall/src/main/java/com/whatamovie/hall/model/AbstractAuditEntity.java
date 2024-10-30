package com.whatamovie.hall.model;

import com.whatamovie.hall.listener.AuditEntityListener;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditEntityListener.class)
public abstract class AbstractAuditEntity {
    @CreatedBy
    private String createBy;
    @LastModifiedBy
    private String lastModifiedBy;
    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
}
