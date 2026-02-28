package com.sample.project.controller;

import com.sample.project.dto.loginRequest;
import com.sample.project.exceptions.EmailAlreadyExists;
import com.sample.project.models.user;
import com.sample.project.services.user_service;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class homeController {

    @Autowired
    user_service service;
    loginRequest loginData;

    @PostMapping  ("/register")
    public String createUser(@RequestBody user newuser) {
        try {
           return service.createUser(newuser);
        }
        catch (EmailAlreadyExists e){
            return  "Registeration failed: " + e.getMessage();
        }
    }

    @PostMapping ("/login")
    public String login(@RequestBody loginRequest loginData) {
        String username = loginData.getUsername();
        String plainPassword = loginData.getPassword();

        service.authenticate(username, plainPassword);
        return "Login complete.";
    }

}
