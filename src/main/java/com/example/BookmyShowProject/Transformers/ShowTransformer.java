package com.example.BookmyShowProject.Transformers;

import com.example.BookmyShowProject.Entity.Show;
import com.example.BookmyShowProject.RequestDTOS.AddShowRequest;

public class ShowTransformer {

    public static Show convertAddShowRequestToEntity(AddShowRequest addShowRequest){
        Show showObj = Show.builder()
                .showTime(addShowRequest.getShowTime())
                .showDate(addShowRequest.getShowDate())
                .build();

        return showObj;
    }
}
