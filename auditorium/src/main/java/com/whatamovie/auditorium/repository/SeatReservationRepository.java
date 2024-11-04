package com.whatamovie.auditorium.repository;

import com.whatamovie.auditorium.model.SeatReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatReservationRepository extends JpaRepository<SeatReservation, Long> {
    @Query(value = "select * from seat_reservation where screening_id=?1 and (select auditorium_id from seat where seat.id=seat_reservation.seat_id)=?2",nativeQuery = true)
    List<SeatReservation> findAllByScreeningAndAuditorium(long screeningId, long auditoriumId);
    @Query(value = "select * from seat_reservation where seat_id=?3 and screening_id=?1 (select auditorium_id from seat where seat.id=seat_reservation.seat_id)=?2",nativeQuery = true)
    List<SeatReservation> findAllByScreeningAndAuditoriumAndSeat(long screeningId, long auditoriumId,long seat_id);
}
