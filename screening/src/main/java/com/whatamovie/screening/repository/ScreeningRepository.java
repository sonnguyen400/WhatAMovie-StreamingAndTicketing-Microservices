package com.whatamovie.screening.repository;

import com.whatamovie.screening.model.Screening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScreeningRepository extends JpaRepository<Screening,Long> {
    @Query(value = "select q from screening q where q.movie_id= :movie_id",nativeQuery = true)
    List<Screening> findByMovieId(@Param("movie_id") Long movieId);
}
