package com.example.BookmyShowProject.Services;

import com.example.BookmyShowProject.Entity.Movie;
import com.example.BookmyShowProject.Repositories.MovieRepository;
import com.example.BookmyShowProject.RequestDTOS.AddMovieRequest;
import com.example.BookmyShowProject.Transformers.AddMovieTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public String addMovie(AddMovieRequest addMovieRequest) throws Exception{
        Movie movie = AddMovieTransformer.convertAddMovieRequestToEntity(addMovieRequest);

        movieRepository.save(movie);

        return "Movie has been added to the DB Successfully";
    }



}
