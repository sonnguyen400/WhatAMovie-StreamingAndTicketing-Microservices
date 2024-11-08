package com.whatamovie.booking_ticket.constant;

public enum TickingSocketMessageState {
    REGISTER(1),
    CANCEL(2);
    public final int value;
    TickingSocketMessageState(int value) {
        this.value = value;
    }
}
