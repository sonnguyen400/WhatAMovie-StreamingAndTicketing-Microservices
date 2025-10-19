package com.sonnguyen.snpayment.application.service.impl;

import com.sonnguyen.snpayment.application.dto.mapper.PaymentDTOMapper;
import com.sonnguyen.snpayment.application.dto.request.PaymentCreateRequest;
import com.sonnguyen.snpayment.application.dto.response.PaymentCreateResponse;
import com.sonnguyen.snpayment.application.mapper.PaymentCommandMapper;
import com.sonnguyen.snpayment.application.service.PaymentService;
import com.sonnguyen.snpayment.domain.MerchantAccount;
import com.sonnguyen.snpayment.domain.Payment;
import com.sonnguyen.snpayment.domain.command.PaymentCreateCommand;
import com.sonnguyen.snpayment.domain.repository.MerchantAccountRepository;
import com.sonnguyen.snpayment.domain.repository.PaymentRepository;
import com.sonnguyen.snpayment.domain.repository.TransactionRepository;
import com.sonnguyen.snpayment.infrastructure.adapter.impl.PaymentGatewayFactory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class PaymentServiceImpl implements PaymentService {
    PaymentCommandMapper paymentCommandMapper;
    PaymentRepository paymentRepository;
    TransactionRepository transactionRepository;
    MerchantAccountRepository merchantAccountRepository;
    PaymentDTOMapper paymentDTOMapper;
    PaymentGatewayFactory paymentGatewayFactory;

    @Override
    public PaymentCreateResponse createPayment(PaymentCreateRequest request) {
        Optional<Payment> existedPaymentIdempotencyKey = this.paymentRepository.findByIdempotencyKey(request.getIdempotencyKey());
        if (existedPaymentIdempotencyKey.isPresent()) {
            return this.paymentDTOMapper.from(existedPaymentIdempotencyKey.get());
        }
        PaymentCreateCommand paymentCreateCommand = this.paymentCommandMapper.from(request);
        MerchantAccount merchantAccount = merchantAccountRepository.getById(paymentCreateCommand.getMerchantAccountId());
        Payment payment = new Payment(paymentCreateCommand, merchantAccount);
        paymentGatewayFactory.getProvider(merchantAccount.getPaymentGatewayType()).createPayment(payment);
        return this.paymentDTOMapper.from(payment);
    }
}
