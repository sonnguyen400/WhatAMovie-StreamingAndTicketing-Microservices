package com.sonnguyen.snpayment.infrastructure.adapter;

import com.sonnguyen.snpayment.domain.Payment;

public interface PaymentServiceAdapter {
    Payment createPayment(Payment payment);

    Payment refundPayment(Payment payment);
}
