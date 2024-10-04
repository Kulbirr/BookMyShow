package com.example.BookmyShowProject.RequestDTOS;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Locale;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookTicketRequest {

    private String movieName;

    private List<String> seatNumbers;

    private Integer theaterId;

    private LocalDate showDate;

    private LocalTime showTime;

    private List<String> requestedSeats;

    private Integer userId;
}
