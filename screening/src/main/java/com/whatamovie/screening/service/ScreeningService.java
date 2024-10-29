package com.whatamovie.screening.service;

import com.whatamovie.common_component.exception.ResourceNotFoundException;
import com.whatamovie.screening.model.Screening;
import com.whatamovie.screening.repository.ScreeningRepository;
import com.whatamovie.screening.viewmodel.ScreeningRequestVm;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ScreeningService {
    ScreeningRepository screeningRepository;
    AuditoriumService auditoriumService;
    MovieService movieService;
    public List<Screening> findAllByMovieId(Long movieId) {
        return  screeningRepository.findByMovieId(movieId);
    }
    public Screening insert(ScreeningRequestVm screening) {
        if(movieService.findById(screening.movie_id())!=null|| auditoriumService.findAllByIds(List.of(screening.auditorium_id())).isEmpty()){
            return  screeningRepository.save(screening.toScreening());
        }
        throw new ResourceNotFoundException("Movie not found");

    }
}
