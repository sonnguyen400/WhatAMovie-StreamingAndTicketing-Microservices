package com.sonnguyen.snpayment.application.dto.mapper;

import com.sonnguyen.snpayment.application.dto.response.PaymentCreateResponse;
import com.sonnguyen.snpayment.domain.Payment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentDTOMapper {
    PaymentCreateResponse from(Payment payment);
}
