package com.example.BookmyShowProject.Entity;

import com.example.BookmyShowProject.Enum.Genre;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Movie {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer movieId;

    @Column(unique = true)
    private String movieName;

    private float duration;

    private double rating;

    private LocalDate releaseDate;

    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    @OneToMany(mappedBy = "movie" , cascade = CascadeType.ALL)
    private List<Show> showList = new ArrayList<>();
}
