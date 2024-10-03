package com.example.BookmyShowProject.Services;

import com.example.BookmyShowProject.Entity.*;
import com.example.BookmyShowProject.Enum.SeatType;
import com.example.BookmyShowProject.Exceptions.ShowNotFoundException;
import com.example.BookmyShowProject.Repositories.MovieRepository;
import com.example.BookmyShowProject.Repositories.ShowRepository;
import com.example.BookmyShowProject.Repositories.TheaterRepository;
import com.example.BookmyShowProject.RequestDTOS.AddShowRequest;
import com.example.BookmyShowProject.RequestDTOS.AddShowSeatRequest;
import com.example.BookmyShowProject.Transformers.ShowTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShowService {
    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private TheaterRepository theaterRepository;

    @Autowired
    private MovieRepository movieRepository;


    public String addShow(AddShowRequest addShowRequest){

        Show show = ShowTransformer.convertAddShowRequestToEntity(addShowRequest);

        Optional<Theater> optionalTheater = theaterRepository.findById(addShowRequest.getTheaterId());

        if(!optionalTheater.isPresent()){
            throw new RuntimeException("Theater with given theaterID doesn't exist");
        }
        Theater theater = optionalTheater.get();
        Movie movie = movieRepository.findMovieByMovieName(addShowRequest.getMovieName());

        // Setting the FK values
        show.setMovie(movie);
        show.setTheater(theater);

//        Setting the bidirectional mapping
        theater.getShowList().add(show);
        movie.getShowList().add(show);

        showRepository.save(show);

        return "show has been saved to the DB with the given show id "+show.getShowId();
    }

    public String enableSeats(AddShowSeatRequest addShowSeatRequest) throws ShowNotFoundException{
        Optional<Show> optionalShow = showRepository.findById(addShowSeatRequest.getShowId());

        if(!optionalShow.isPresent()){
            throw new ShowNotFoundException("No show exists with given showId");
        }

        Show show = optionalShow.get();

        Theater theater = show.getTheater();
        List<TheaterSeat> theaterList = theater.getTheaterSeatList();

        List<ShowSeats> showSeatsList = new ArrayList<>();

        for(TheaterSeat theaterSeat : theaterList){
            ShowSeats showSeatsObj = ShowSeats.builder()
                    .seatNo(theaterSeat.getSeatNo())
                    .isAvailable(true)
                    .isFoodAttached(false)
                    .seatType(theaterSeat.getSeatType())
                    .show(show)
                    .build();

            if(theaterSeat.getSeatType().equals(SeatType.CLASSIC)){
                showSeatsObj.setPrice(addShowSeatRequest.getPriceOfClassicSeats());
            }else{
                showSeatsObj.setPrice(addShowSeatRequest.getPriceOfPremiumSeats());
            }
            showSeatsList.add(showSeatsObj);
        }
        show.setShowSeatsList(showSeatsList);
        return "Seats have been enabled for the show with showId "+show.getShowId();
    }
}
