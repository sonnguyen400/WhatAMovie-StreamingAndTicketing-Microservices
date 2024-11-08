package com.whatamovie.booking_ticket.vm;

import com.whatamovie.booking_ticket.model.SeatOrder;
import jakarta.validation.constraints.NotNull;

public record SeatOrderPostVm(@NotNull Long screening_id,@NotNull Long seat_reservation_id) {
    public SeatOrder toEntity(){
        return SeatOrder
                .builder()
                .screening_id(screening_id)
                .seat_reservation_id(seat_reservation_id)
                .build();
    }
}
