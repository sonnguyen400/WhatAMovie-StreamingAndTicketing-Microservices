package com.whatamovie.booking_ticket.service;

import com.whatamovie.booking_ticket.model.Seat;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "auditorium-service")
public interface SeatService {
    @GetMapping(name = "/api/v1/auditorium/seat/ids")
    List<Seat> findAllIdsIn(@RequestParam List<Integer> ids);


}
