package com.whatamovie.booking_ticket.vm;

import jakarta.validation.constraints.NotNull;

public record SeatOrderCancelVm (@NotNull Long seat_reservation_id,@NotNull String session_id){
    @Override
    public String toString() {
        return "SeatOrderCancelVm{" +
                "seat_reservation_id=" + seat_reservation_id +
                ", session_id='" + session_id + '\'' +
                '}';
    }
}
