package com.example.BookmyShowProject.Entity;

import com.example.BookmyShowProject.Enum.SeatType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TheaterSeat {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer theatreSeatId;

    private String seatNo;

    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;

    @ManyToOne
    @JoinColumn
    private Theater theater;

}
