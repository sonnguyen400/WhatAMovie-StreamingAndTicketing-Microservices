package com.sonnguyen.snpayment.application.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sonnguyen.common.model.application.request.Request;
import com.sonnguyen.snpayment.infrastructure.constant.ZalopayPaymentMethodSupport;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class ZalopayOrderEmbedDataCreateRequest extends Request {
    @JsonProperty("preferred_payment_method")
    private List<ZalopayPaymentMethodSupport> preferredPaymentMethod;

    @JsonProperty("redirect_url")
    private String redirectUrl;

    @JsonProperty("columninfo")
    private String columnInfo;

    /**
     * Mã thông tin thanh toán.
     * Chỉ truyền khi đối tác cần nhận tiền đối soát về nhiều tài khoản khác nhau.
     * Hệ thống Zalopay sẽ tạo ra một mã Thanh toán (tương ứng với mỗi Tài khoản ngân hàng đối tác cung cấp) và gởi lại cho đối tác thiết lập.
     */
    @JsonProperty("zlppaymentid")
    private String zalopayPaymentId;
}
