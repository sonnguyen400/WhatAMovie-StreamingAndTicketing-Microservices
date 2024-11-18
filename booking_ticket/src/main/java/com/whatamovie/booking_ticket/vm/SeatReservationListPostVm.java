package com.whatamovie.booking_ticket.vm;

import java.util.List;

public record SeatReservationListPostVm(Long screening_id, List<SeatReservationPostVm> list) {

}
