package com.whatamovie.auditorium.service;

import com.whatamovie.auditorium.model.SeatReservation;
import com.whatamovie.auditorium.repository.SeatReservationRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
public class SeatReservationService {
    SeatReservationRepository seatReservationRepository;
    public List<SeatReservation> findAllByScreeningAndAuditorium(Long screeningId,Long auditoriumId) {
        return seatReservationRepository.findAllByScreeningAndAuditorium(screeningId,auditoriumId);
    }
}
