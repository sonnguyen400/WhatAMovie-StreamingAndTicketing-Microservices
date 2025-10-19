package com.sonnguyen.common.data.mongo.configuration;

import com.sonnguyen.common.data.mongo.document.AuditingDocument;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class MongoEventListener extends AbstractMongoEventListener<AuditingDocument> {

    @Override
    public void onBeforeConvert(BeforeConvertEvent<AuditingDocument> event) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        AuditingDocument object = event.getSource();
        if (object.getCreatedAt() == null) {
            object.setCreatedAt(Instant.now());
        }
        if (object.getCreatedBy() == null) {
            object.setCreatedBy(userName);
        }
        object.setLastModifiedAt(Instant.now());
        object.setLastModifiedBy(userName);
    }
}
