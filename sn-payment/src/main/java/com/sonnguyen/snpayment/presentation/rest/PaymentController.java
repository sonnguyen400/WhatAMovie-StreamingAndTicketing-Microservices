package com.sonnguyen.snpayment.presentation.rest;

import com.sonnguyen.snpayment.application.dto.request.ZalopayOrderStatusUpdateRequest;
import com.sonnguyen.snpayment.application.dto.response.ZalopayOrderStatusUpdateResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Validated
@RequestMapping("/api")
public interface PaymentController {
    @PostMapping("/v1/payments/zalopay/callback")
    ZalopayOrderStatusUpdateResponse handleZalopayCallback(ZalopayOrderStatusUpdateRequest request);
}
