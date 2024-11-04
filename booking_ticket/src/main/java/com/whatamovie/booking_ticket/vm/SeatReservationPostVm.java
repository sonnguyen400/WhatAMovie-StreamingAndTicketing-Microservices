package com.whatamovie.booking_ticket.vm;

import com.whatamovie.booking_ticket.model.SeatReservation;

import java.math.BigDecimal;

public record SeatReservationPostVm(BigDecimal price,Long screening_id,Long seat_id) {
    SeatReservation toEntity(){
        return SeatReservation.builder()
                .price(price)

                .build();
    }
}
