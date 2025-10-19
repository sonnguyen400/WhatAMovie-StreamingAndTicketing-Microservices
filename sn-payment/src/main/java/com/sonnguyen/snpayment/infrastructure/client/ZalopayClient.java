package com.sonnguyen.snpayment.infrastructure.client;

import com.sonnguyen.snpayment.application.dto.request.ZalopayOrderCreateRequest;
import com.sonnguyen.snpayment.application.dto.request.ZalopayOrderQueryRequest;
import com.sonnguyen.snpayment.application.dto.request.ZalopayRefundRequest;
import com.sonnguyen.snpayment.application.dto.response.ZalopayOrderCreateResponse;
import com.sonnguyen.snpayment.application.dto.response.ZalopayOrderResponse;
import com.sonnguyen.snpayment.application.dto.response.ZalopayRefundQueryResponse;
import com.sonnguyen.snpayment.application.dto.response.ZalopayRefundResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "zalopay-client")
public interface ZalopayClient {
    @PostMapping("/v2/create")
    ZalopayOrderCreateResponse createOrder(ZalopayOrderCreateRequest request);

    @GetMapping("/v2/query")
    ZalopayOrderResponse queryOrder(ZalopayOrderQueryRequest request);

    @GetMapping("/v2/refund")
    ZalopayRefundResponse refundOrder(ZalopayRefundRequest request);

    @GetMapping("/v2/query-refund")
    ZalopayRefundQueryResponse queryRefund(ZalopayOrderQueryRequest request);
}
