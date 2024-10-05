package com.example.BookmyShowProject.Services;

import com.example.BookmyShowProject.Entity.*;
import com.example.BookmyShowProject.Repositories.*;
import com.example.BookmyShowProject.RequestDTOS.BookTicketRequest;
import com.example.BookmyShowProject.ResponseDto.TicketResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {
    @Autowired
    private JavaMailSender javaMailSender;

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

    public Show findRightShow(BookTicketRequest bookTicketRequest){
        Movie movie = movieRepository.findMovieByMovieName(bookTicketRequest.getMovieName());
        Theater theater = theaterRepository.findById(bookTicketRequest.getTheaterId()).get();

        Show show = showRepository.findShowByShowDateAndShowTimeAndMovieAndTheater(bookTicketRequest.getShowDate(), bookTicketRequest.getShowTime(), movie, theater);

        return show;
    }

    public TicketResponse generateTicket(Integer ticketId){

        Ticket ticket = ticketRepository.findById(ticketId).get();
        User user = ticket.getUser();
        System.out.println(user);

        TicketResponse ticketResponse = TicketResponse.builder()
                .bookedSeats(ticket.getBookedSeats())
                .movieName(ticket.getMovieName())
                .showDate(ticket.getShowDate())
                .showTime(ticket.getShowTime())
                .theaterName(ticket.getTheaterName())
                .totalAmount(ticket.getTotalAmount())
                .build();

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setFrom("manyabhau04@gmail.com");
        mailMessage.setSubject("Booking Confirmation: Your Seats for "+ticketResponse.getMovieName()+" on "+ticketResponse.getShowDate()+" from BookMyShow");

        String body = "Dear " + user.getName() + ",\n\n" +
                "We are pleased to confirm that your seats have been successfully booked for the upcoming show at " + ticketResponse.getTheaterName() +
                " on " + ticketResponse.getShowDate() + " at " + ticketResponse.getShowTime() + ".\n\n" +
                "Please find your booking details below:\n\n" +
                "Show: " + ticketResponse.getMovieName() + "\n" +
                "Venue: " + ticketResponse.getTheaterName() + "\n" +
                "Date: " + ticketResponse.getShowDate() + "\n" +
                "Time: " + ticketResponse.getShowTime() + "\n" +
                "Seat(s): " + ticket.getBookedSeats() + "\n\n" +
                "If you have any questions or require further assistance, please do not hesitate to reach out to us.\n\n" +
                "Thank you for choosing BookMyShow. We look forward to seeing you at the event.\n\n" +
                "Best regards,\n" +
                "The BookMyShow Team";

        mailMessage.setText(body);


        javaMailSender.send(mailMessage);
        return ticketResponse;
    }

}
