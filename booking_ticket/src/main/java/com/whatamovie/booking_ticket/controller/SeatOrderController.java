package com.whatamovie.booking_ticket.controller;

import com.whatamovie.booking_ticket.model.SeatOrder;
import com.whatamovie.booking_ticket.service.SeatOrderedService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
@RequestMapping("/api/v1/ticket ")
public class SeatOrderController {
    SeatOrderedService seatOrderedService;
    @GetMapping
    public List<SeatOrder> findAllByScreening(@RequestParam(name = "screening") Long screening_id){
        return seatOrderedService.findAllByScreeningId(screening_id);
    }
}
