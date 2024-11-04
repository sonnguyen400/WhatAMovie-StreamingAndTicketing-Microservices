package com.whatamovie.auditorium.controller;

import com.whatamovie.auditorium.model.SeatReservation;
import com.whatamovie.auditorium.service.SeatReservationService;
import com.whatamovie.auditorium.vm.ListSeatReservationPostVm;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/auditorium/seat/reserve")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
public class SeatReservationController {
    SeatReservationService seatReservationService;
    @GetMapping("/{id}")
    public SeatReservation getSeatReservation(@PathVariable("id") Long id) {
        return seatReservationService.findById(id);
    }
    @GetMapping
    public List<SeatReservation> findAllByAuditoriumAnScreening(@RequestParam Long auditorium_id,@RequestParam Long screening_id) {
        return seatReservationService.findAllByScreeningAndAuditorium(screening_id, auditorium_id);
    }
    @PostMapping
    public List<SeatReservation> createSeatReservationsForScreening(
            @RequestBody ListSeatReservationPostVm listSeatReservationPostVm
    ){
        return seatReservationService.createSeatReservationsForScreening(listSeatReservationPostVm);
    }

}
