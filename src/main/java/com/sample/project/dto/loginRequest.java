package com.sample.project.dto;

import org.springframework.web.bind.annotation.RequestBody;

public class loginRequest {

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String username;
    private String password;

    public void login(@RequestBody loginRequest loginData) {
        String username = loginData.getUsername();
        String password = loginData.getPassword();
    }
}
