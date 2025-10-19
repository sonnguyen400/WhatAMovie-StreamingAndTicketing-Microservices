package com.sonnguyen.snpayment.infrastructure.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Objects;

public enum ZalopayReturnCode {
    SUCCESS(1),
    FAIL(2),
    PROCESSING(3);

    public final int value;

    ZalopayReturnCode(int value) {
        this.value = value;
    }

    @JsonCreator
    public static ZalopayReturnCode fromValue(int value) {
        for (ZalopayReturnCode status : ZalopayReturnCode.values()) {
            if (Objects.equals(status.value, value)) {
                return status;
            }
        }
        return null;
    }

    @JsonValue
    public int toValue() {
        return this.value;
    }
}
