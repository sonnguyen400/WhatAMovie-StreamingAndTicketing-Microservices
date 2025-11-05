package com.sonnguyen.snpayment.application.dto.mapper;

import com.sonnguyen.snpayment.application.dto.response.PaymentCreateResponse;
import com.sonnguyen.snpayment.domain.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PaymentDTOMapper {
    PaymentCreateResponse from(Payment payment);
}
