package com.sonnguyen.snpayment.infrastructure.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Objects;

public enum ZalopayCreateOrderStatusCode {
    DUPLICATE_APPS_TRANS_ID(-68, "Giá trị app_trans_id của đơn hàng bị trùng"),
    ILLEGAL_DATA_REQUEST(-401, "Các tham số trong yêu cầu sai định dạng"),
    ILLEGAL_APP_SIGNATURE_REQUEST(-402, "Xác thực thông tin merchant thất bại"),
    LIMIT_REQUEST_REACH(-429, "Yêu cầu bị từ chối vì vượt quá giới hạn tần suất"),
    SYSTEM_ERROR(-500, "Hệ thống gặp sự cố"),
    SYSTEM_MAINTENANCE(-999, "Hệ thống đang được bảo trì");

    public final int value;
    public final String description;

    ZalopayCreateOrderStatusCode(int value, String description) {
        this.value = value;
        this.description = description;
    }

    @JsonCreator
    public static ZalopayCreateOrderStatusCode fromValue(int value) {
        for (ZalopayCreateOrderStatusCode status : ZalopayCreateOrderStatusCode.values()) {
            if (Objects.equals(value, status.value)) {
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
