package com.example.BookmyShowProject.Controllers;

import com.example.BookmyShowProject.Entity.Show;
import com.example.BookmyShowProject.RequestDTOS.BookTicketRequest;
import com.example.BookmyShowProject.ResponseDto.TicketResponse;
import com.example.BookmyShowProject.Services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @PostMapping("/bookTicket")
    private String bookTicket(@RequestBody BookTicketRequest bookTicketRequest){
        String result = ticketService.bookTicket(bookTicketRequest);
        return result;
    }

    @GetMapping("findRightShow")
    public Show findRightShow(@RequestParam("bookTicketRequest") BookTicketRequest bookTicketRequest){
        return ticketService.findRightShow(bookTicketRequest);
    }

    @GetMapping("generateTicket")
    public TicketResponse generateTicket(@RequestParam("ticketId") Integer ticketId){
        return ticketService.generateTicket(ticketId);
    }
}
