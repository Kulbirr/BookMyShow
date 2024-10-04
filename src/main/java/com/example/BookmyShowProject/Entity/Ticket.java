package com.example.BookmyShowProject.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer ticketid;

    private int totalPrice;

    private String bookedSeats;

    private String movieName;

    private LocalDate showDate;

    private LocalTime showTime;

    private String theaterAddress;
    @ManyToOne
    @JoinColumn
    private Show show;

    @ManyToOne
    @JoinColumn
    private User user;


}
