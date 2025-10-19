package com.sonnguyen.snpayment.infrastructure.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Objects;

public enum ZalopayPaymentMethodSupport {
    DOMESTIC_CARD("domestic_card"),
    VIET_QR("viet_qr");

    public final String value;

    ZalopayPaymentMethodSupport(String value) {
        this.value = value;
    }

    @JsonCreator
    public static ZalopayPaymentMethodSupport fromValue(String value) {
        for (ZalopayPaymentMethodSupport status : ZalopayPaymentMethodSupport.values()) {
            if (Objects.equals(status.value, value)) {
                return status;
            }
        }
        return null;
    }

    @JsonValue
    public String toValue() {
        return this.value;
    }
}
