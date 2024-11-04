package com.whatamovie.auditorium.vm;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ListSeatReservationPostVm (@NotNull Long screening_id,@NotNull Long auditorium_id,@NotNull List<SeatReservationPostVm> seats){
}
