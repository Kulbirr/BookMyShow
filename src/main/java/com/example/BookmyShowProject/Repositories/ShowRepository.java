package com.example.BookmyShowProject.Repositories;

import com.example.BookmyShowProject.Entity.Movie;
import com.example.BookmyShowProject.Entity.Show;
import com.example.BookmyShowProject.Entity.Theater;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;

public interface ShowRepository extends JpaRepository<Show, Integer> {
    Show findShowByShowDateAndShowTimeAndMovieAndTheater(LocalDate showDate, LocalTime showTime, Movie movie, Theater theater);
}
