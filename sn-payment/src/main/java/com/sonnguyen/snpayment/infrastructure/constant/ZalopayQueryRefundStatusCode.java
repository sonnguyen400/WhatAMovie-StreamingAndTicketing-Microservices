package com.sonnguyen.snpayment.infrastructure.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Objects;

public enum ZalopayQueryRefundStatusCode {
    REFUND_TYPE_INVALID(-2, "Đơn hàng không được phép hoàn tiền"),
    REFUND_EXPIRE_TIME(-13, "Đơn hàng vượt quá thời hạn cho phép hoàn tiền"),
    REFUND_AMOUNT_INVALID(-14, "Số tiền yêu cầu hoàn tiền không hợp lệ"),
    INSERT_REFUND_LOG_AR_FAIL(-16, "Đơn hàng đang được xử lý"),
    NOT_SUPPORT_PARTIAL_REFUND_ALT(-32, "Không hỗ trợ hoàn tiền một phần"),
    M_REFUND_ID_NOT_FOUND(-101, "Giá trị m_refund_id không tồn tại"),
    ILLEGAL_DATA_REQUEST_ALT(-401, "Các tham số trong yêu cầu sai định dạng"),
    ILLEGAL_APP_SIGNATURE_REQUEST_ALT(-402, "Xác thực thông tin merchant thất bại"),
    LIMIT_REQUEST_REACH_ALT(-429, "Yêu cầu bị từ chối vì vượt quá tần suất cho phép"),
    SYSTEM_ERROR_QUERY(-500, "Hệ thống gặp sự cố"),
    SYSTEM_MAINTENANCE_QUERY(-999, "Hệ thống đang được bảo trì"),
    REFUND_PENDING(-1, "Hoàn tiền chờ phê duyệt");

    public final int value;
    public final String description;

    ZalopayQueryRefundStatusCode(int value, String description) {
        this.value = value;
        this.description = description;
    }

    @JsonCreator
    public static ZalopayQueryRefundStatusCode fromValue(int value) {
        for (ZalopayQueryRefundStatusCode status : ZalopayQueryRefundStatusCode.values()) {
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
