package com.sonnguyen.common.model.infrastructure.support.enums;

public enum CurrencyUnit {
    VND("VND"),
    USD("USD"),
    EUR("EUR"),
    JPY("JPY"),
    KRW("KRW"),
    CNY("CNY"),
    GBP("GBP");
    public final String code;

    CurrencyUnit(String code) {
        this.code = code;
    }
}
