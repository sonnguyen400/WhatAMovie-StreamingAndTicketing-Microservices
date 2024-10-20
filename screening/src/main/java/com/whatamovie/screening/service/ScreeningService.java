package com.whatamovie.screening.service;

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
    MovieService movieService;
    public List<Screening> findAllByMovieId(Long movieId) {
        return  screeningRepository.findByMovieId(movieId);
    }
    public Screening insert(ScreeningRequestVm screening) {
        if(movieService.findById(screening.movieId())!=null){
            System.out.print(movieService.findById(screening.movieId()));
            return  screeningRepository.save(screening.toScreening());
        }
        System.out.print("Movie not found");
        return null;

    }
}
