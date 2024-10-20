package com.whatamovie.payment.zalopay.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.whatamovie.payment.utils.Mapable;
import com.whatamovie.payment.zalopay.utils.Utils;
import com.whatamovie.payment.zalopay.utils.crypto.HMACUtil;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@ToString
public class OrderInfo implements Mapable {
    //(REQUIRED) Định danh cho ứng dụng đã được cấp khi đăng ký ứng dụng với ZaloPay
    private int app_id;
    //(REQUIRED) Định danh user (Tên , id,..., có thể dùng thông tin mặc định, chằng hạn như tên ứng dụng )
    private String app_user;
    //(REQUIRED) Mã giao dịch phải bắt đầu theo format yymmdd của ngày hiện tại (Timezone VN) và nên theo format yymmddMã đơn hàng thanh toán
    private String app_trans_id;
    //(REQUIRED) Thời gian tạo đơn hàng (milisecond) không quá 15P so với thời điểm thanh toán
    private Long app_time;
    //Thời gian hết hạn của đơn hàng. Thời gian tính bằng giây (giá trị nhỏ nhất: 300, giá trị lớn nhất: 2592000)
    private Long expire_duration_seconds;

    //(REQUIRED)
    private Long amount;

    //(REQUIRED)Item  hàng (tự định nghĩa )
    private String item;

    // (REQUIRED)(Description)
    private String description;

    //Json string Dữ liệu riêng của đơn hàng. Dữ liệu này sẽ được callback lại cho AppServer khi thanh toán thành công (Nếu không có thì để chuỗi rỗng)
    private String embed_data;

    //(REQUIRED) * Mã ngân hàng
    private String bank_code;

    //(REQUIRED)mac Thông tin chứng thực của đơn hàng
    private String mac;

    private String callback_url;

    private String device_info;

    //Ch app dụng với đôối tác dđặc biêệt
    private String sub_app_id;

    private String title;
    private String currency;
    private String phone;
    private String email;
    private String address;

    @Builder
    public OrderInfo(int app_id, @NonNull String app_user,@NonNull String app_trans_id,@NonNull Long amount,@NonNull String description,@NonNull String bank_code,@NonNull String item,@NonNull String embed_data,@NonNull String key1,@NonNull String callback_url,@NonNull String title) {
        this.expire_duration_seconds= 900L;
        this.app_id = app_id;
        this.app_user = app_user;
        this.app_trans_id = Utils.getCurrentTimeString("yyMMdd")+"_"+app_trans_id;
        this.app_time = System.currentTimeMillis();
        this.amount = amount;
        this.description = description;
        this.bank_code=bank_code;
        this.item = item;
        this.embed_data = embed_data;
        this.callback_url = callback_url;
        this.title = title;
        this.app_time=System.currentTimeMillis();
        String hmacInput=this.app_id+"|"+this.app_trans_id+"|"+this.app_user+"|"+this.amount+"|"+this.app_time+"|"+this.embed_data+"|"+this.item;
        this.mac = HMACUtil.HMacHexStringEncode(HMACUtil.HMACSHA256,key1.trim(),hmacInput);
    }

}
