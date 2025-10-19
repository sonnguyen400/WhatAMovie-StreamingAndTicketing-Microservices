package com.sonnguyen.snpayment.infrastructure.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Objects;

public enum ZalopayQueryOrderStatusCode {
    TIME_INVALID(-54, "Đơn hàng đã hết hạn thanh toán"),
    ZPW_BALANCE_NOT_ENOUGH(-63, "Tài khoản người dùng không đủ tiền"),
    APPTRANSID_INVALID(-92, "Giá trị app_trans_id trong yêu cầu không đúng định dạng"),
    ORDER_NOT_EXIST(-101, "Giá trị app_trans_id không tồn tại"),
    BANK_ERROR(-217, "Lỗi từ hệ thống Ngân hàng phát hành thẻ"),
    PROMOTION_ERROR(-332, "Đơn hàng thanh toán thất bại do lỗi khuyến mãi"),
    PROMOTION_ERROR_ALT(-333, "Đơn hàng thanh toán thất bại do lỗi khuyến mãi"),
    ILLEGAL_DATA_REQUEST(-401, "Các tham số trong yêu cầu sai định dạng"),
    ILLEGAL_APP_SIGNATURE_REQUEST(-402, "Xác thực thông tin merchant thất bại"),
    LIMIT_REQUEST_REACH(-429, "Yêu cầu bị từ chối vì vượt quá tần suất cho phép"),
    SYSTEM_ERROR(-500, "Hệ thống gặp sự cố"),
    SYSTEM_MAINTENANCE(-999, "Hệ thống đang được bảo trì"),
    EXCEED_MAX_FUND_OUT_PER_DAY_1(-1330, "Tài khoản vượt quá hạn mức giao dịch trong ngày"),
    EXCEED_MAX_FUND_OUT_PER_DAY_2(-1331, "Tài khoản vượt quá hạn mức giao dịch trong ngày"),
    EXCEED_MAX_FUND_OUT_PER_DAY_3(-1332, "Tài khoản vượt quá hạn mức giao dịch trong ngày"),
    EXCEED_MAX_FUND_OUT_PER_DAY_4(-1333, "Tài khoản vượt quá hạn mức giao dịch trong ngày"),
    EXCEED_MAX_FUND_OUT_PER_MONTH_1(-1340, "Tài khoản vượt quá hạn mức giao dịch trong tháng"),
    EXCEED_MAX_FUND_OUT_PER_MONTH_2(-1341, "Tài khoản vượt quá hạn mức giao dịch trong tháng"),
    EXCEED_MAX_FUND_OUT_PER_MONTH_3(-1342, "Tài khoản vượt quá hạn mức giao dịch trong tháng"),
    EXCEED_MAX_FUND_OUT_PER_MONTH_4(-1343, "Tài khoản vượt quá hạn mức giao dịch trong tháng");

    public final int value;
    public final String description;

    ZalopayQueryOrderStatusCode(int value, String description) {
        this.value = value;
        this.description = description;
    }

    @JsonCreator
    public static ZalopayQueryOrderStatusCode fromValue(int value) {
        for (ZalopayQueryOrderStatusCode status : ZalopayQueryOrderStatusCode.values()) {
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
