package com.example.BookmyShowProject.Repositories;

import com.example.BookmyShowProject.Entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
    Movie findMovieByMovieName(String movieName);
}
