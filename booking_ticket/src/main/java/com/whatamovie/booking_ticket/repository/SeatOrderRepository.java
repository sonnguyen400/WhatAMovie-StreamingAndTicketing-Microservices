package com.whatamovie.booking_ticket.repository;

import com.whatamovie.booking_ticket.model.SeatOrder;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatOrderRepository extends MongoRepository<SeatOrder,Long> {
    List<SeatOrder> findAllByScreening_id(Long id);
}
