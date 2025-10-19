package com.sonnguyen.snnotification.infrastructure.mongo.repository;

import com.sonnguyen.snnotification.infrastructure.mongo.document.NotificationDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface NotificationDocumentRepository extends MongoRepository<NotificationDocument, UUID> {
}
