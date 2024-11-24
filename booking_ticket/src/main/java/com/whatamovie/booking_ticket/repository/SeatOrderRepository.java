package com.whatamovie.booking_ticket.repository;

import com.whatamovie.booking_ticket.model.SeatOrder;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatOrderRepository extends MongoRepository<SeatOrder,String> {
    List<SeatOrder> findAllByScreening_id(Long id);
    @Query("{'seat_reservation_id': ?0,$or: [{'status': 'BUSY'},{'status': 'PLACED'}]}")
    List<SeatOrder> findAllBySeatReservation_id(Long id);
    @Query("{session_id: ?0,status: 'BUSY'}")
    @Update("{$set: {status: 'CANCEL'}}")
    Integer cancelSeatOrderBySessionId(String sessionId);
    @Query("{session_id: ?0,seat_reservation_id: ?1,status: 'BUSY'}")
    @Update("{$set: {status: 'CANCEL'}}")
    Integer cancelSeatOrderByReservation(String sessionId,Long seatReservationId);
    @Query("{session_id: ?0,seat_reservation_id: ?1,status: 'BUSY'}")
    List<SeatOrder> findAllBySeatReservationId(Long id);
    @Query("{session_id: ?0,status: 'BUSY'}")
    List<SeatOrder> findAllBySessionId(String sessionId);
}
