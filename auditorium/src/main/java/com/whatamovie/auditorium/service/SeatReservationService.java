package com.whatamovie.auditorium.service;

import com.whatamovie.auditorium.repository.SeatReservationRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
public class SeatReservationService {
    SeatReservationRepository seatReservationRepository;
}
