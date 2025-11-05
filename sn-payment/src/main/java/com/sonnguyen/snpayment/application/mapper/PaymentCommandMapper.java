package com.sonnguyen.snpayment.application.mapper;

import com.sonnguyen.snpayment.application.dto.request.PaymentCreateRequest;
import com.sonnguyen.snpayment.domain.command.PaymentCreateCommand;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PaymentCommandMapper {
    PaymentCreateCommand from(PaymentCreateRequest request);
}
