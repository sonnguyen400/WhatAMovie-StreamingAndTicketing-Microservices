package com.sonnguyen.snpayment.presentation.rest.impl;

import com.sonnguyen.snpayment.application.dto.request.ZalopayOrderStatusUpdateRequest;
import com.sonnguyen.snpayment.application.dto.response.ZalopayOrderStatusUpdateResponse;
import com.sonnguyen.snpayment.presentation.rest.PaymentController;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class PaymentControllerImpl implements PaymentController {

    @Override
    public ZalopayOrderStatusUpdateResponse handleZalopayCallback(ZalopayOrderStatusUpdateRequest request) {
        return null;
    }
}
