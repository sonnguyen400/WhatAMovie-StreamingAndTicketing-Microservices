package com.sonnguyen.snpayment.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sonnguyen.snpayment.infrastructure.constant.ZalopayQueryOrderStatusCode;
import com.sonnguyen.snpayment.infrastructure.constant.ZalopayReturnCode;
import lombok.Data;

@Data
public class ZalopayOrderResponse {
    @JsonProperty("return_code")
    private ZalopayReturnCode returnCode;

    @JsonProperty("return_message")
    private String returnMessage;

    @JsonProperty("sub_return_code")
    private ZalopayQueryOrderStatusCode subReturnCode;

    @JsonProperty("is_processing")
    private Boolean isProcessing;

    @JsonProperty("amount")
    private Long amount;

    @JsonProperty("zp_trans_id")
    private Integer zpTransId;

    @JsonProperty("server_time")
    private Long serverTime;

    @JsonProperty("discount_amount")
    private Long discountAmount;
}
