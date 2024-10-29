package com.whatamovie.hall.repository;

import com.whatamovie.hall.model.Hall;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HallRepository extends JpaRepository<Hall, Long> {
    List<Hall> findAllByIdIn(List<Long> ids);
    @NonNull
    Page<Hall> findAll(@NonNull Pageable pageable);
}
