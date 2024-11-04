package com.whatamovie.auditorium.repository;

import com.whatamovie.auditorium.model.Auditorium;
import com.whatamovie.auditorium.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat,Long> {
    List<Seat> findAllByAuditorium_Id(Long auditorium_id);
    List<Seat> findAllByIdIn(List<Long> ids);
    List<Seat> findAllByAuditorium_IdAndIdIn(Long auditorium_id, List<Long> ids);
}
