package com.example.BookmyShowProject.RequestDTOS;

import com.example.BookmyShowProject.Enum.SeatType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddShowSeatRequest {
    private Integer showId;

    private Integer priceOfClassicSeats;

    private Integer priceOfPremiumSeats;
}
