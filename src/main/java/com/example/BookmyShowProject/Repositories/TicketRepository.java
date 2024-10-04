package com.example.BookmyShowProject.Repositories;

import com.example.BookmyShowProject.Entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
}
