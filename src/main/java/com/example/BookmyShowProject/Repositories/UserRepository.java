package com.example.BookmyShowProject.Repositories;

import com.example.BookmyShowProject.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}

