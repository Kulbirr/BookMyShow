package com.example.BookmyShowProject.Controllers;

import com.example.BookmyShowProject.Exceptions.ShowNotFoundException;
import com.example.BookmyShowProject.RequestDTOS.AddShowRequest;
import com.example.BookmyShowProject.RequestDTOS.AddShowSeatRequest;
import com.example.BookmyShowProject.Services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("show/")
public class ShowController {
    @Autowired
    private ShowService showService;

    @PostMapping("add")
    public ResponseEntity addShow(@RequestBody AddShowRequest addShowRequest){
        try{
            String result = showService.addShow(addShowRequest);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("createShowSeats")
    public ResponseEntity enableShowSeats(@RequestBody AddShowSeatRequest addShowSeatRequest){
        try{
            String result = showService.enableSeats(addShowSeatRequest);
            return new ResponseEntity(result, HttpStatus.CREATED);
        }catch (ShowNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("getAllShows")
    public ResponseEntity getAllShows(){
        return new ResponseEntity(showService.getAllShows(), HttpStatus.OK);
    }

    @GetMapping("getShow/{showId}")
    public ResponseEntity getShow(@PathVariable int showId){
        try{
            String result = showService.getShow(showId);
            return new ResponseEntity(result, HttpStatus.OK);
        }catch (ShowNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }



}
