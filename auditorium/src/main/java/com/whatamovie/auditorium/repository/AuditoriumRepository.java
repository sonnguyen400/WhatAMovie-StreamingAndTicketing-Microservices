package com.whatamovie.auditorium.repository;

import com.whatamovie.auditorium.model.Auditorium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuditoriumRepository extends JpaRepository<Auditorium, Long> {
    List<Auditorium> findByIdIn(List<Long> ids);
}
