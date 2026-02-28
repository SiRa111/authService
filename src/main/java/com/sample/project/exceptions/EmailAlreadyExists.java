package com.sample.project.exceptions;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


public class EmailAlreadyExists extends RuntimeException{

    public EmailAlreadyExists(String message) {
        super(message);
    }
}
