package com.sonnguyen.snpayment.application.service;

import com.sonnguyen.snpayment.application.dto.request.PaymentCreateRequest;
import com.sonnguyen.snpayment.application.dto.response.PaymentCreateResponse;

public interface PaymentService {
    PaymentCreateResponse createPayment(PaymentCreateRequest request);
}
