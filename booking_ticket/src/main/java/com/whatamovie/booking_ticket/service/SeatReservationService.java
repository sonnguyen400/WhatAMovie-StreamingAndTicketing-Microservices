package com.whatamovie.booking_ticket.service;

import com.whatamovie.booking_ticket.model.SeatReservation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "auditorium-service")
public interface SeatReservationService {
    @GetMapping
    List<SeatReservation> findAllByAuditoriumAnScreening(@RequestParam Long auditorium_id,@RequestParam Long screening_id);
}
