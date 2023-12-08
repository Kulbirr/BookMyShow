package com.example.BookmyShowProject.Services;

import com.example.BookmyShowProject.Entity.User;
import com.example.BookmyShowProject.Repositories.UserRepository;
import com.example.BookmyShowProject.RequestDTOS.AddUserRequest;
import com.example.BookmyShowProject.Transformers.AddUserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public String addUser(AddUserRequest addUserRequest) {

        User userObj = AddUserTransformer.convertAddUserRequestToUser(addUserRequest);
        userRepository.save(userObj);

        return "User added Successfully.";
    }
}
