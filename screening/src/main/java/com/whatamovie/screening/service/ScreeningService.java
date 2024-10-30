package com.whatamovie.screening.service;

import com.whatamovie.common_component.exception.ResourceNotFoundException;
import com.whatamovie.screening.exception.ConflictScreeningTimeException;
import com.whatamovie.screening.model.Auditorium;
import com.whatamovie.screening.model.Movie;
import com.whatamovie.screening.model.Screening;
import com.whatamovie.screening.repository.ScreeningRepository;
import com.whatamovie.screening.viewmodel.ScreeningRequestVm;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ScreeningService {
    ScreeningRepository screeningRepository;
    AuditoriumService auditoriumService;
    MovieService movieService;

    public List<Screening> findAllByMovieId(Long movieId) {
        return screeningRepository.findByMovieId(movieId);
    }

    public Screening insert(ScreeningRequestVm screeningVm) {
        Movie movie = movieService.findById(screeningVm.movie_id());
        Auditorium auditorium = auditoriumService.findById(screeningVm.auditorium_id());
        Screening screening = screeningVm.toScreening();

        if (movie != null && auditorium != null) {
            List<Screening> screenings = screeningRepository.findByApproximatelyTime(screening.getStartTime());
            screenings.sort(Comparator.comparing(Screening::getStartTime));
            if(!screenings.isEmpty()){
                if (screenings.size() == 1) {
                    if(screening.getStartTime().isAfter(screenings.getFirst().getStartTime())) screenings.addLast(null);
                    else if(screening.getStartTime().isBefore(screenings.getFirst().getStartTime())) screenings.addFirst(null);
                    else throw new ConflictScreeningTimeException("Two screenings have the same start time");
                }
                if(screenings.getFirst()!=null){
                    Movie lastMovie=movieService.findById(screenings.getFirst().getMovie_id());
                    ZonedDateTime completedTimeOfLastScreening = screenings.getFirst().getStartTime().plusMinutes(lastMovie.getDuration()+screenings.getFirst().getLeisure_time_after());
                    if(!completedTimeOfLastScreening.isBefore(screening.getStartTime())) throw new ConflictScreeningTimeException(String.format("A movie with id{%s} is shown at{%s} is conflict to this movie with start time at %s", lastMovie.getId(), screenings.getFirst().getStartTime(),screening.getStartTime()));
                }
                if(screenings.getLast()!=null){
                    ZonedDateTime completedTimeOfThisScreening=screening.getStartTime().plusMinutes(movie.getDuration()+screening.getLeisure_time_after());
                    if(!completedTimeOfThisScreening.isBefore(screenings.getLast().getStartTime())) throw new ConflictScreeningTimeException(String.format("A movie with id{%s} is shown at{%s} is conflict to this movie with end time at %s", screenings.getLast().getMovie_id(), screenings.getLast().getStartTime(),completedTimeOfThisScreening));
                }
            }
            return screeningRepository.save(screening);
        }
        throw new ResourceNotFoundException("Check out auditorium and movie information");
    }
    public void deleteById(Long id) {
        screeningRepository.deleteById(id);
    }
}
