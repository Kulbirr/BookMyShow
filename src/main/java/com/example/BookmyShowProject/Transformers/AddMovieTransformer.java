package com.example.BookmyShowProject.Transformers;

import com.example.BookmyShowProject.Entity.Movie;
import com.example.BookmyShowProject.RequestDTOS.AddMovieRequest;

public class AddMovieTransformer {
    public static Movie converAddMovieRequestToEntity(AddMovieRequest addMovieRequest){

        Movie movieObj = Movie.builder()
                .movieName(addMovieRequest.getMovieName())
                .genre(addMovieRequest.getGenre())
                .rating(addMovieRequest.getRating())
                .releaseDate(addMovieRequest.getReleaseDate())
                .build();

        return movieObj;
    }
}
