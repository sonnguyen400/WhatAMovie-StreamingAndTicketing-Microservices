package com.sonnguyen.snpayment.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sonnguyen.snpayment.infrastructure.constant.ZalopayQueryRefundStatusCode;
import com.sonnguyen.snpayment.infrastructure.constant.ZalopayReturnCode;
import lombok.Data;

@Data
public class ZalopayRefundQueryResponse {
    @JsonProperty("return_code")
    private ZalopayReturnCode returnCode;

    @JsonProperty("return_message")
    private String returnMessage;

    @JsonProperty("sub_return_code")
    private ZalopayQueryRefundStatusCode subReturnCode;

    @JsonProperty("sub_return_message")
    private String subReturnMessage;
}
