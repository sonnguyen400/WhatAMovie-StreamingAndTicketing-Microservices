package com.sonnguyen.snpayment.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sonnguyen.snpayment.infrastructure.constant.ZalopayReturnCode;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ZalopayOrderStatusUpdateResponse {
    @JsonProperty("return_code")
    private ZalopayReturnCode returnCode;

    @JsonProperty("return_message")
    private String returnMessage;
}
