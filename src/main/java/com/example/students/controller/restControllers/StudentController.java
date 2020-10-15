package com.example.students.controller.restControllers;

import com.example.students.exceptions.StudentNotFoundException;
import com.example.students.model.Student;
import com.example.students.services.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    private com.example.students.services.StudentService studentService;

    @GetMapping("/students")
    List<Student> getAllStudents() {
        throw new StudentNotFoundException(2);
        //return studentService.findAll();
    }

    @GetMapping("/students2")
    List<Student> getUsers() {
        return studentService.findAll();
    }
}
