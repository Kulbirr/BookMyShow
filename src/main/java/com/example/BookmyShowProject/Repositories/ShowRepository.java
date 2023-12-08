package com.example.BookmyShowProject.Repositories;

import com.example.BookmyShowProject.Entity.Show;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowRepository extends JpaRepository<Show, Integer> {
}
