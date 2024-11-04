package com.whatamovie.booking_ticket.constant;


public enum SeatOrderStatus {
    FREE(1),
    PLACED(2),
    BUSY(3);
    public final int value;
    SeatOrderStatus(int value) {
        this.value = value;
    }
}
