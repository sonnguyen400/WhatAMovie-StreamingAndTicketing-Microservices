package com.sonnguyen.snnotification.infrastructure.mongo.repository;

import com.sonnguyen.snnotification.infrastructure.mongo.document.NotificationDeliveryDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface NotificationDeliveryDocumentRepository extends MongoRepository<NotificationDeliveryDocument, UUID> {
}
