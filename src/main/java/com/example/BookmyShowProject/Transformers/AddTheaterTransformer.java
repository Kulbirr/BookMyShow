package com.example.BookmyShowProject.Transformers;

import com.example.BookmyShowProject.Entity.Theater;
import com.example.BookmyShowProject.RequestDTOS.AddTheaterRequest;

public class AddTheaterTransformer {

    public static Theater convertTheaterRequestToTheater(AddTheaterRequest addTheaterRequest){
        Theater theaterObj = Theater.builder()
                .name(addTheaterRequest.getName())
                .address(addTheaterRequest.getAddress())
                .city(addTheaterRequest.getCity())
                .build();
        return theaterObj;
    }
}
