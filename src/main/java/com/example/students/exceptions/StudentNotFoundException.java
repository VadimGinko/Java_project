package com.example.students.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(int id) {
        super("Could not find user " + id);
    }
}