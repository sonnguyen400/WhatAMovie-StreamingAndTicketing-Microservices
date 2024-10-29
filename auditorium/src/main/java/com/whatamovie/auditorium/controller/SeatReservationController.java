package com.whatamovie.auditorium.controller;

import com.whatamovie.auditorium.model.SeatReservation;
import com.whatamovie.auditorium.service.SeatReservationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/seat/reservation")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
public class SeatReservationController {
    SeatReservationService seatReservationService;
    @GetMapping
    public List<SeatReservation> getSeatReservations(
            @RequestParam(required = false) Long screening,
            @RequestParam Long auditorium
    ) {
        return seatReservationService.findAllByScreeningAndAuditorium(screening, auditorium);
    }
}
