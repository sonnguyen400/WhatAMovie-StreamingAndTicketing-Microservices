package com.whatamovie.auditorium.constant;

public enum SeatType {
    SINGLE(1),
    DUPLEX(2);
    final int value;
    SeatType(int value) {
        this.value = value;
    }
}
