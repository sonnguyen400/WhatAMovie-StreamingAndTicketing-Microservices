//package com.whatamovie.booking_ticket.repository;
//
//import com.whatamovie.booking_ticket.model.SeatReservation;
//import org.springframework.data.mongodb.repository.MongoRepository;
//
//import java.util.List;
//
//public interface SeatReservationRepository extends MongoRepository<SeatReservation,Long> {
//    List<SeatReservation> findAllByAuditorium_idAndScreening_id(Long auditorium_id, Long screening_id);
//}
