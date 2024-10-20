package com.whatamovie.payment.zalopay.model;
import com.whatamovie.payment.utils.Jsonable;
import lombok.Builder;

@Builder
public class EmbedData implements Jsonable {
    private String[] preferred_payment_method;
    private String redirecturl;
    // Json
    private String columninfo;
    //Json (watch Campaign Code)
    private String promotioninfo;
    //Json {"zlppaymentid": "P4201372"}
    private String zlppaymentid;

}
