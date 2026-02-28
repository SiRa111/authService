package com.sample.project.services;

import com.sample.project.dto.loginRequest;
import com.sample.project.exceptions.EmailAlreadyExists;
import com.sample.project.models.user;
import com.sample.project.repository.user_repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class user_service implements UserDetailsService {

    @Autowired
    user_repo repo;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2B, 12);

    public boolean doesEmailExist(String email) throws EmailAlreadyExists {
        boolean ans = repo.existsByEmail(email);
        if (ans) {
            throw new EmailAlreadyExists("This email already exists! ");
        }
        return ans;
    }

    public String createUser(user user_data) {
        boolean check = doesEmailExist(user_data.getEmail());
        user_data.setPassword(encoder.encode(user_data.getPassword()));
        repo.save(user_data);
        return "User created successfully!";

    }

    @Override
    public UserDetails loadUserByUsername(String username_to_check) {

        com.sample.project.models.user user_details = repo.findByUsername(username_to_check);
        System.out.println("Looking for: " + username_to_check);
        System.out.println("Found: " + user_details);

        if (user_details == null) {
            throw new UsernameNotFoundException("Username doesnt exist.");
        }
        return user_details;
    }

    public UserDetails authenticate(String username, String plainPassword) {
        UserDetails user = loadUserByUsername(username);
        String hashedPass = user.getPassword();

        if (encoder.matches(plainPassword, hashedPass)) {
            return user;
        } else {
            throw new BadCredentialsException("Wrong Password.");
        }
    }
}
