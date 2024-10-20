package com.whatamovie.payment.constant;

public enum PaymentMethod {
    ZALOPAY("zalopay");
    private String value;
    PaymentMethod(String value) {
        this.value=value;
    }
}
