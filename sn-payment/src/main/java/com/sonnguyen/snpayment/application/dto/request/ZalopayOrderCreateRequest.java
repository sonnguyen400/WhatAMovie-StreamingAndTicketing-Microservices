package com.sonnguyen.snpayment.application.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sonnguyen.common.model.application.request.Request;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Data
@SuperBuilder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ZalopayOrderCreateRequest extends Request {
    /**
     * required
     */
    @JsonProperty("app_id")
    private int appId;

    /**
     * required
     * Thông tin của người dùng thanh toán đơn hàng: id/username của user.
     * Nếu không định danh được có thể dùng thông tin mặc định, chằng hạn như tên ứng dụng và không được để trống.
     */
    @JsonProperty("app_user")
    private String appUser;

    /**
     * required
     * Mã giao dịch Merchant gửi qua hệ thống Zalopay để user thực hiện thanh toán (TXID của giao dịch đơn hàng). Mã giao dịch phải bắt đầu theo format yymmdd của ngày hiện tại.
     * Theo format yymmdd_Mã đơn hàng thanh toán. Ví dụ: 250210_OrderID.
     * Merchant dùng giá trị này cho việc tra cứu thông tin trạng thái giao dịch và đối soát, yymmdd phải đúng TimeZone Vietnam (GMT+7) (Vì các giao dịch đối soát theo ngày giờ Việt Nam).
     */
    @JsonProperty("app_trans_id")
    private String appTransId;

    /**
     * required
     * Thời gian tạo đơn hàng (unix timestamp in milisecond). Thời gian tính đến milisecond, lấy theo giờ hiện hành.
     */
    @JsonProperty("app_time")
    private long appTime;

    @JsonProperty("expire_duration_seconds")
    private int expireDurationSeconds;

    /**
     * required
     */
    @JsonProperty("amount")
    private long amount;

    /**
     * required
     */
    @JsonProperty("description")
    private String description;

    /**
     * URL để Zalopay callback kết quả thanh toán về Merchant.
     */
    @JsonProperty("callback_url")
    private String callbackUrl;

    @JsonProperty("sub_app_id")
    private String subAppId;

    @JsonProperty("item")
    @Builder.Default
    private List<String> items = new ArrayList<>();

    @JsonProperty("embed_data")
    @Builder.Default
    private ZalopayOrderEmbedDataCreateRequest embedData = new ZalopayOrderEmbedDataCreateRequest();

    /**
     * required
     */
    @JsonProperty("mac")
    private String mac;

    @JsonProperty("bank_code")
    private String bankCode;
}
