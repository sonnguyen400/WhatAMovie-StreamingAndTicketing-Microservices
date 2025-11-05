package com.sonnguyen.snbuilding.infrastructure.mongo.repository;

import com.sonnguyen.snbuilding.infrastructure.mongo.document.BookingSeatDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookingSeatDocumentRepository extends MongoRepository<BookingSeatDocument, UUID> {
}
