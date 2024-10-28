package com.whatamovie.auditorium.controller;

import com.whatamovie.auditorium.model.SeatReservation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/seat/reservation")
public class SeatReservationController {
    @GetMapping
    public List<SeatReservation> getSeatReservations() {
    }
}
