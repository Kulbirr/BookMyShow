package com.example.BookmyShowProject.Controllers;

import com.example.BookmyShowProject.RequestDTOS.BookTicketRequest;
import com.example.BookmyShowProject.Services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @PostMapping("/bookTicket")
    private String bookTicket(@RequestBody BookTicketRequest bookTicketRequest){
        String result = ticketService.bookTicket(bookTicketRequest);
        return result;
    }
}
