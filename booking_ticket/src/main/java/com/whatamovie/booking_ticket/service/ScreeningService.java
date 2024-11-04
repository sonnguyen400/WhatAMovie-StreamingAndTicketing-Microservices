package com.whatamovie.booking_ticket.service;

import com.whatamovie.booking_ticket.model.Screening;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "screening-service")
public interface ScreeningService {
    @GetMapping("/api/v1/screening/ids")
    List<Screening> findAllIdIns(@RequestParam List<Long> ids);
}
