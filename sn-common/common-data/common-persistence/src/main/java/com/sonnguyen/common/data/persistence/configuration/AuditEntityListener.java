package com.sonnguyen.common.data.persistence.configuration;

import com.sonnguyen.common.data.persistence.entity.AuditingEntity;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.auditing.AuditingHandler;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.Instant;

@Configurable
public class AuditEntityListener extends AuditingEntityListener {
    public AuditEntityListener(ObjectFactory<AuditingHandler> handler) {
        super.setAuditingHandler(handler);
    }

    public AuditEntityListener() {

    }

    @Override
    public void touchForCreate(Object target) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        AuditingEntity entity = (AuditingEntity) target;
        entity.setCreatedAt(Instant.now());
        if (entity.getCreatedBy() == null) {
            super.touchForCreate(target);
        } else {
            if (entity.getLastModifiedBy() == null) {
                entity.setLastModifiedBy(entity.getCreatedBy());
            }
        }
    }

    @Override
    public void touchForUpdate(Object target) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        AuditingEntity entity = (AuditingEntity) target;
        entity.setLastModifiedAt(Instant.now());
        if (entity.getCreatedBy() == null) {
            super.touchForCreate(target);
        } else {
            if (entity.getLastModifiedBy() == null) {
                entity.setLastModifiedBy(userName);
            }
        }
    }
}
