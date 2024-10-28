package com.whatamovie.auditorium.repository;

import com.whatamovie.auditorium.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat,Integer> {
}
