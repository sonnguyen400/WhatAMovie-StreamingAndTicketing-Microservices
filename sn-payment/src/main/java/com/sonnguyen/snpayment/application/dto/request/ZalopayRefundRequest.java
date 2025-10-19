package com.sonnguyen.snpayment.application.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sonnguyen.common.model.application.request.Request;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class ZalopayRefundRequest extends Request {
    @JsonProperty("app_id")
    private Integer appId;

    @JsonProperty("m_refund_id")
    private String mRefundId;

    @JsonProperty("zp_trans_id")
    private String zpTransId;

    @JsonProperty("amount")
    @Builder.Default
    private Integer amount = 0;

    @JsonProperty("refund_fee_amount")
    @Builder.Default
    private Integer refundFeeAmount = 0;

    @JsonProperty("timestamp")
    private Long timestamp;

    @JsonProperty("description")
    private String description;

    @JsonProperty("mac")
    private String mac;
}
