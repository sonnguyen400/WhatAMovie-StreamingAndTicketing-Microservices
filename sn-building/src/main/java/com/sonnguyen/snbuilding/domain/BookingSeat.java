package com.sonnguyen.snbuilding.domain;

import com.sonnguyen.snbuilding.infrastructure.support.enums.BookingSeatStatus;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * DTO for {@link com.sonnguyen.snbuilding.infrastructure.mongo.document.BookingSeatDocument}
 */
public class BookingSeat {
    private UUID id;
    private UUID seatId;
    private UUID screeningId;
    private UUID roomId;
    private UUID theaterId;
    private BigDecimal price;
    private Float taxes;
    private BookingSeatStatus status;
}
