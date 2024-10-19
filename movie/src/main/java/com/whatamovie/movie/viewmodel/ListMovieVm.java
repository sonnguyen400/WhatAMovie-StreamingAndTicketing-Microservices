package com.whatamovie.movie.viewmodel;

import com.whatamovie.movie.model.Movie;

public record ListMovieVm(Long id, String title, int duration,String picture, String description) {
    public static ListMovieVm fromMovie(Movie movie){
        return new ListMovieVm(movie.getId(),movie.getTitle(),movie.getDuration(),movie.getPicture(),movie.getDescription());
    }
}
