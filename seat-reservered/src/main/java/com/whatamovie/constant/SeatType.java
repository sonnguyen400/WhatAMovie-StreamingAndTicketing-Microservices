package com.whatamovie.constant;

public enum SeatType {
    SINGLE(1),
    DOUBLE(2);
    public final int value;
    SeatType(int value) {
        this.value = value;
    }
}
