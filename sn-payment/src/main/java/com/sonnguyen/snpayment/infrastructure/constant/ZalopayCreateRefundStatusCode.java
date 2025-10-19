package com.sonnguyen.snpayment.infrastructure.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Objects;

public enum ZalopayCreateRefundStatusCode {
    // ===== Create Refund Order =====
    SYSTEM_ERROR(0, "Hệ thống gặp sự cố"),
    ORDER_REFUND_EXPIRED(-13, "Vượt quá thời hạn cho phép khởi tạo hoàn tiền"),
    ORDER_NOT_FOUND(-101, "Giá trị zp_trans_id không tồn tại"),
    ILLEGAL_DATA_REQUEST(-401, "Các tham số trong yêu cầu sai định dạng"),
    ILLEGAL_APP_SIGNATURE_REQUEST(-402, "Xác thực thông tin merchant thất bại"),
    LIMIT_REQUEST_REACH(-429, "Yêu cầu bị từ chối vì vượt quá tần suất cho phép"),
    SYSTEM_ERROR_ALT(-500, "Hệ thống gặp sự cố"),
    SYSTEM_MAINTENANCE(-999, "Hệ thống đang được bảo trì"),
    NOT_SUPPORT_PARTIAL_REFUND(-32, "Không hỗ trợ hoàn tiền một phần");

    public final int value;
    public final String description;

    ZalopayCreateRefundStatusCode(int value, String description) {
        this.value = value;
        this.description = description;
    }

    @JsonCreator
    public static ZalopayCreateRefundStatusCode fromValue(int value) {
        for (ZalopayCreateRefundStatusCode status : ZalopayCreateRefundStatusCode.values()) {
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
