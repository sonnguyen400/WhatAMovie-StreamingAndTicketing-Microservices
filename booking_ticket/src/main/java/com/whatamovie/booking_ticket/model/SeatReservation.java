package com.whatamovie.booking_ticket.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SeatReservation{
    private Long id;
    private BigDecimal price;
    private Long auditorium_id;
    private Long screening_id;
    private Seat seat;
}
