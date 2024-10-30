package com.whatamovie.screening.repository;

import com.whatamovie.screening.model.Screening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface ScreeningRepository extends JpaRepository<Screening,Long> {
    @Query(value = "select * from screening  where screening.movie_id= :movie_id",nativeQuery = true)
    List<Screening> findByMovieId(@Param("movie_id") Long movieId);
    @Query(value = "(select * from lighten_cinema.screening\n" +
            " where screening.start_time<=:time  order by start_time desc limit 1)\n" +
            "union\n" +
            "(select * from lighten_cinema.screening\n" +
            " where screening.start_time>:time order by start_time limit 1)",nativeQuery = true)
    List<Screening> findByApproximatelyTime(@Param("time") ZonedDateTime time);
}
