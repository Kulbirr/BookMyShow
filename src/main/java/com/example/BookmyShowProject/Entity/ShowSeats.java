package com.example.BookmyShowProject.Entity;

import com.example.BookmyShowProject.Enum.SeatType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShowSeats {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer showSeatsId;

    private String seatNo;

    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;

    private int price;

    private boolean isAvailable;

    private boolean isFoodAttached;

    @ManyToOne
    @JoinColumn
    private Show show;
}
