package com.sonnguyen.snpayment.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sonnguyen.snpayment.infrastructure.constant.ZalopayCreateRefundStatusCode;
import com.sonnguyen.snpayment.infrastructure.constant.ZalopayReturnCode;
import lombok.Data;

@Data
public class ZalopayRefundResponse {
    @JsonProperty("return_code")
    private ZalopayReturnCode returnCode;

    @JsonProperty("return_message")
    private String returnMessage;

    @JsonProperty("sub_return_code")
    private ZalopayCreateRefundStatusCode subReturnCode;

    @JsonProperty("sub_return_message")
    private String subReturnMessage;

    @JsonProperty("refund_id")
    private Long refundId;
}
