package com.example.BookmyShowProject.Controllers;

import com.example.BookmyShowProject.Entity.Theater;
import com.example.BookmyShowProject.RequestDTOS.AddShowSeatRequest;
import com.example.BookmyShowProject.RequestDTOS.AddTheaterRequest;
import com.example.BookmyShowProject.Services.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("theater/")
public class TheaterController {
    @Autowired
    private TheaterService theaterService;


    @PostMapping("add")
    public ResponseEntity addTheater(AddTheaterRequest addTheaterRequest){
        String result = theaterService.addTheater((addTheaterRequest));
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @PutMapping("associateSeats")
    public ResponseEntity createTheaterSeats(@RequestBody Theater theater, @RequestBody AddTheaterRequest addTheaterRequest){
        String response = theaterService.createTheaterSeats(theater, addTheaterRequest);
        return new ResponseEntity(response,HttpStatus.OK);
    }
}
