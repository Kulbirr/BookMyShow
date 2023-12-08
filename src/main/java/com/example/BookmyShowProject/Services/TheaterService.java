package com.example.BookmyShowProject.Services;


import com.example.BookmyShowProject.Entity.Theater;
import com.example.BookmyShowProject.Entity.TheaterSeat;
import com.example.BookmyShowProject.Enum.SeatType;
import com.example.BookmyShowProject.Repositories.TheaterRepository;
import com.example.BookmyShowProject.RequestDTOS.AddTheaterRequest;
import com.example.BookmyShowProject.Transformers.AddTheaterTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheaterService {
    @Autowired
    private TheaterRepository theaterRepository;

    public String addTheater(AddTheaterRequest addTheaterRequest){
        Theater theater = AddTheaterTransformer.convertTheaterRequestToTheater(addTheaterRequest);
//        calling createtheaterseat to create seats
        createTheaterSeats(theater, addTheaterRequest);

        return "Theater and its seats have been saved to DB.";
    }

    public void createTheaterSeats(Theater theater, AddTheaterRequest addTheaterRequest){
        int noOfClassicSeats = addTheaterRequest.getNoOfClassicSeats();
        int noOfPremiumSeats = addTheaterRequest.getNoOfPremiumSeats();
        int seatsPerRow = addTheaterRequest.getNoOfSeatsPerRow();

        List<TheaterSeat> theaterSeatList = new ArrayList<>();

        char ch = 'A';
        int row = 0;

        for(int i = 1; i <= noOfClassicSeats; i++){
            if(i%seatsPerRow == 1){
                row++;
                ch = 'A';
            }
            String seatNo = row+""+ch;
            ch++;

            TheaterSeat theaterSeatObj = TheaterSeat.builder()
                    .seatNo(seatNo)
                    .seatType(SeatType.CLASSIC)
                    .theater(theater)
                    .build();

            theaterSeatList.add(theaterSeatObj);
        }

        ch = 'A';
        for(int i = 1; i <= noOfPremiumSeats; i++){
            if(i%seatsPerRow == 0){
                row++;
                ch = 'A';
            }
            String seatNo = row+""+ch;
            ch++;
            TheaterSeat theaterSeatObj = TheaterSeat.builder()
                    .theater(theater)
                    .seatNo(seatNo)
                    .seatType(SeatType.PREMIUM)
                    .build();

            theaterSeatList.add(theaterSeatObj);
        }
        theater.setTheaterSeatList(theaterSeatList);
        theaterRepository.save(theater);
    }
}
