//package com.whatamovie.booking_ticket.listener;
//
//import com.whatamovie.booking_ticket.model.AbstractAuditEntity;
//import jakarta.persistence.PrePersist;
//import jakarta.persistence.PreUpdate;
//import lombok.NonNull;
//import org.springframework.beans.factory.ObjectFactory;
//import org.springframework.beans.factory.annotation.Configurable;
//import org.springframework.data.auditing.AuditingHandler;
//import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//
//@Configurable
//public class AuditEntityListener extends AuditingEntityListener {
//    public AuditEntityListener(ObjectFactory<AuditingHandler> handler) {
//        super.setAuditingHandler(handler);
//    }
//
//    @PrePersist
//    public void touchForCreate(@NonNull Object target) {
//        AbstractAuditEntity abstractAuditEntity = (AbstractAuditEntity) target;
//        if (abstractAuditEntity.getCreateBy() == null) {
//        } else {
//            if (abstractAuditEntity.getLastModifiedBy() == null) {
//                abstractAuditEntity.setLastModifiedBy(abstractAuditEntity.getCreateBy());
//            }
//        }
//    }
//
//    @Override
//    @PreUpdate
//    public void touchForUpdate(Object target) {
//        AbstractAuditEntity entity = (AbstractAuditEntity) target;
//        if (entity.getLastModifiedBy() == null) {
//            super.touchForUpdate(target);
//        }
//    }
//}
