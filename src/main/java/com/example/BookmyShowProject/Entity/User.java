package com.example.BookmyShowProject.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
@Builder
public class User {
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Id
        private Integer id;

        private String name;

        @Column(unique = true)
        private String email;

        private String mobileNo;

        private Integer age;

        @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
        private List<Ticket> ticketList = new ArrayList<>();


}
