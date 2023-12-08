package com.example.BookmyShowProject.RequestDTOS;

import com.example.BookmyShowProject.Enum.Genre;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AddMovieRequest {

    private String movieName;

    private double rating;

    private Genre genre;

    private LocalDate releaseDate;
}
