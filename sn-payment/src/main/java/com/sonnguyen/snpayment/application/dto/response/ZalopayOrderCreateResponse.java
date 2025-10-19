package com.sonnguyen.snpayment.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sonnguyen.snpayment.infrastructure.constant.ZalopayCreateOrderStatusCode;
import com.sonnguyen.snpayment.infrastructure.constant.ZalopayReturnCode;
import lombok.Data;

@Data
public class ZalopayOrderCreateResponse {
    @JsonProperty("return_code")
    private ZalopayReturnCode returnCode;

    @JsonProperty("return_message")
    private String returnMessage;

    @JsonProperty("sub_return_code")
    private ZalopayCreateOrderStatusCode subReturnCode;

    /**
     * Token của giao dịch.
     * Trong trường hợp tích hợp theo mô hình AppToApp, dùng giá trị này để mở ứng dụng Zalopay trên thiết bị của người dùng để người dùng thực hiện thanh toán.
     * Trong trường hợp thanh toán token, dùng giá trị này để gọi API Pay by token.
     */
    @JsonProperty("zp_trans_token")
    private String zpTransToken;

    @JsonProperty("order_token")
    private String orderToken;

    @JsonProperty("order_url")
    private String orderUrl;

    @JsonProperty("qr_code")
    private String qrCode;
}
