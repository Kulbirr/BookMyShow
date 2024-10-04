package com.example.BookmyShowProject.Services;

import com.example.BookmyShowProject.Entity.*;
import com.example.BookmyShowProject.Repositories.*;
import com.example.BookmyShowProject.RequestDTOS.BookTicketRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private TheaterRepository theaterRepository;

    @Autowired
    private MovieRepository movieRepository;

    public String bookTicket(BookTicketRequest bookTicketRequest){
        Show show = findRightShow(bookTicketRequest);

        List<ShowSeats> showSeatList = show.getShowSeatsList();

        int totalPrice = 0;

        for(ShowSeats showSeat : showSeatList){
            if(bookTicketRequest.getSeatNumbers().contains(showSeat.getSeatNo())){
                showSeat.setAvailable(false);
                totalPrice += showSeat.getPrice();
            }
        }

        User user = userRepository.findById(bookTicketRequest.getUserId()).get();

        Ticket ticket = Ticket.builder()
                .bookedSeats(bookTicketRequest.getSeatNumbers().toString())
                .movieName(show.getMovie().getMovieName())
                .showDate(show.getShowDate())
                .showTime(show.getShowTime())
                .theaterAddress(show.getTheater().getAddress())
                .totalPrice(totalPrice)
                .show(show)
                .user(user)
                .build();

        show.getTicketList().add(ticket);
        user.getTicketList().add(ticket);

        ticketRepository.save(ticket);

        return "Ticket Booked Successfully";
    }

    private Show findRightShow(BookTicketRequest bookTicketRequest){
        Movie movie = movieRepository.findMovieByMovieName(bookTicketRequest.getMovieName());
        Theater theater = theaterRepository.findById(bookTicketRequest.getTheaterId()).get();

        Show show = showRepository.findShowByShowDateAndShowTimeAndMovieAndTheater(bookTicketRequest.getShowDate(), bookTicketRequest.getShowTime(), movie, theater);

        return show;
    }
}
