package com.example.BookmyShowProject.Controllers;

import com.example.BookmyShowProject.RequestDTOS.AddMovieRequest;
import com.example.BookmyShowProject.Services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("movie/")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @PostMapping("add")
    public ResponseEntity addMovie(AddMovieRequest addMovieRequest) {
        try {
            String result = movieService.addMovie(addMovieRequest);
            return new ResponseEntity(result, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
