package com.example.students.ControllerAdvices;

import com.example.students.exceptions.StudentNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
@ControllerAdvice
    class StudentNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(StudentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ResponseEntity<StudentNotFound> employeeNotFoundHandler(StudentNotFoundException ex) {
        return new ResponseEntity<>(new StudentNotFound(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @Data
    @AllArgsConstructor
    private static class StudentNotFound {
        private String message;
    }
}
