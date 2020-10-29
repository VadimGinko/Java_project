package com.example.students.controller.restControllers;

import com.example.students.model.User;
import com.example.students.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsersController {
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    private com.example.students.services.UserService userService;

    @GetMapping("/students")
    List<User> getAllStudents() {

        //throw new StudentNotFoundException(2);
        return userService.findAll();
    }
}
