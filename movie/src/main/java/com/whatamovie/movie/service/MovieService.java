package com.whatamovie.movie.service;

import com.whatamovie.movie.model.Movie;
import com.whatamovie.movie.repository.MovieRepository;
import com.whatamovie.movie.viewmodel.MovieRequest;
import jakarta.ws.rs.NotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MovieService {
    MovieRepository movieRepository;
    public Movie findById(Long id){
        return movieRepository.findById(id).orElse(null);
    }
    public Page<Movie> findAll(Pageable pageable){
        return  movieRepository.findAll(pageable);
    }
    public Movie insert(MovieRequest movie){
        return movieRepository.save(movie.toMovie());
    }
    public Movie updateById(Long id,MovieRequest movie){
        Movie movie1=movie.toMovie();
        movie1.setId(id);
        return movieRepository.save(movie1);
    }

}
