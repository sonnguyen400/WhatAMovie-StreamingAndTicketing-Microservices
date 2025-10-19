package com.sonnguyen.snpayment.application.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sonnguyen.common.model.application.request.Request;
import lombok.Data;

@Data
//@SuperBuilder
public class ZalopayOrderQueryRequest extends Request {
    @JsonProperty("app_id")
    private int appId;

    @JsonProperty("app_trans_id")
    private String appTransId;

    @JsonProperty("mac")
    private String mac;
}
