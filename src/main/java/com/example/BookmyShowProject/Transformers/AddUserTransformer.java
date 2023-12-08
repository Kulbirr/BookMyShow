package com.example.BookmyShowProject.Transformers;

import com.example.BookmyShowProject.Entity.User;
import com.example.BookmyShowProject.RequestDTOS.AddUserRequest;

public class AddUserTransformer {

    public static User convertAddUserRequestToUser(AddUserRequest userRequest){
        User userObj = User.builder()
                .age(userRequest.getAge())
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .mobileNo(userRequest.getMobileNo())
                .build();

        return userObj;
    }
}
