package com.whatamovie.payment.zalopay.service;

import com.whatamovie.payment.config.ZalopayConfig;
import com.whatamovie.payment.utils.ResponseUtils;
import com.whatamovie.payment.zalopay.model.EmbedData;
import com.whatamovie.payment.zalopay.model.OrderInfo;
import com.whatamovie.payment.zalopay.model.OrderPurchaseInfo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class ZalopayService {
    ZalopayConfig zalopayConfig;
    public OrderPurchaseInfo purchaseZalo(Integer orderId){
        int rand=new Random().nextInt(100000);
        try(CloseableHttpClient client = HttpClients.createDefault()) {
            OrderInfo orderInfom = OrderInfo.builder()
                    .app_id(zalopayConfig.appid())
                    .app_user("test")
                    .app_trans_id(rand+"orderId")
                    .amount(34567L)
                    .description("description")
                    .bank_code("zalopayapp")
                    .item("[]")
                    .embed_data(EmbedData.builder().redirecturl("https://hub.gadgetsource.click/zalopay/result").toString())
                    .key1(zalopayConfig.key1())
                    .callback_url("https://gadgetsource.click/api/v1/purchase/zalopay/callback")
                    .build();
            Map<String,Object> mapParams=orderInfom.toMap();
            HttpPost post = new HttpPost(zalopayConfig.paymentEndpoint());
            List<NameValuePair> params = new ArrayList<>();
            for (Map.Entry<String, Object> e : mapParams.entrySet()) {
                if(e.getValue()==null) continue;
                params.add(new BasicNameValuePair(e.getKey(), e.getValue().toString()));
            }
            post.setEntity(new UrlEncodedFormEntity(params));
            CloseableHttpResponse res = client.execute(post);
//            updateOrderStatus(orderInfom.getApp_trans_id(),2);
            OrderPurchaseInfo orderPurchaseInfo= ResponseUtils.parseObject(res, OrderPurchaseInfo.class);
            orderPurchaseInfo.setApp_trans_id(orderInfom.getApp_trans_id());
            return orderPurchaseInfo;
        } catch (   IOException e) {
            throw new RuntimeException(e);
        }
    }
}
