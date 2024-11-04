package com.whatamovie.booking_ticket.vm;

import jakarta.validation.constraints.NotNull;

public record SeatOrderPostVm(@NotNull Long screening_id,@NotNull Long seat_reservation_id) {
}
