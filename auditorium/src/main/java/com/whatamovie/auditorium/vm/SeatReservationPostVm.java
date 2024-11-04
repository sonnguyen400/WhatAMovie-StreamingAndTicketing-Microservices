package com.whatamovie.auditorium.vm;

import com.whatamovie.auditorium.model.SeatReservation;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record SeatReservationPostVm(@NotNull Long seat_id, @NotNull BigDecimal price) {
    public SeatReservation toEntity(){
        return SeatReservation.builder()
                .seat_id(seat_id)
                .price(price)
                .build();
    }
}
