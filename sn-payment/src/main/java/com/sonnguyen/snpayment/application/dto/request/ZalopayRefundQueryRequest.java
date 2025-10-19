package com.sonnguyen.snpayment.application.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sonnguyen.common.model.application.request.Request;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class ZalopayRefundQueryRequest extends Request {
    @JsonProperty("app_id")
    private int appId;

    @JsonProperty("m_refund_id")
    private String appTransId;

    @JsonProperty("timestamp")
    private Long timestamp;

    @JsonProperty("mac")
    private String mac;
}
