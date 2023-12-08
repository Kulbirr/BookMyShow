package com.example.BookmyShowProject.RequestDTOS;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddUserRequest {
    private String name;

    private String email;

    private String mobileNo;

    private Integer age;
}
