package com.sonnguyen.snbuilding.infrastructure.mongo.document;

import com.sonnguyen.common.data.mongo.document.AuditingDocument;
import com.sonnguyen.common.data.persistence.entity.AuditingEntity;
import com.sonnguyen.snbuilding.infrastructure.support.enums.BookingSeatStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Index;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Document(collection = "booking_seat_document")
public class BookingSeatDocument extends AuditingDocument {
    @Field(name = "id")
    private UUID id;

    @Field(name = "seat_id")
    @Indexed
    private UUID seatId;

    @Field(name = "screening_id")
    @Indexed
    private UUID screeningId;

    @Field(name = "room_id")
    @Indexed
    private UUID roomId;

    @Field(name = "theater_id")
    @Indexed
    private UUID theaterId;

    @Field(name = "price")
    private BigDecimal price;

    @Field(name = "taxes")
    private Float taxes;

    @Field(name = "status")
    private BookingSeatStatus status;
}
