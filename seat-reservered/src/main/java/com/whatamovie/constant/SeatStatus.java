package com.whatamovie.constant;

public enum SeatStatus {
    VACANT(1),
    ORDERED(2),
    PENDING(3),
    FIXING(4),
    UNAVAILABLE(5);
    public final int value;
    SeatStatus(int value) {
        this.value = value;
    }
}
