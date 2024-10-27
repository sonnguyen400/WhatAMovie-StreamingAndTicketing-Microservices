package com.whatamovie.payment.controller;

import com.whatamovie.payment.zalopay.model.OrderPurchaseInfo;
import com.whatamovie.payment.zalopay.service.ZalopayService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
public class PaymentController {
    ZalopayService zalopayService;
    @GetMapping(value = "/zalopay/{orderId}")
    public OrderPurchaseInfo purchase(@PathVariable Integer orderId) {
        return  zalopayService.purchaseZalo(orderId);
    }
}
