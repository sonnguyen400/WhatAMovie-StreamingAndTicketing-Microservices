package com.whatamovie.booking_ticket.repository;

import com.whatamovie.booking_ticket.model.SeatOrder;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatOrderRepository extends MongoRepository<SeatOrder,Long> {
}
