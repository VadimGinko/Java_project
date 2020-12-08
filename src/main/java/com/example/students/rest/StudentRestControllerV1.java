package com.example.students.rest;

import com.example.students.dto.StudentDto;
import com.example.students.model.Subject;
import com.example.students.model.User;
import com.example.students.services.SubjectService;
import com.example.students.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping(value = "/api/v1/students/")
public class StudentRestControllerV1 {
    private final UserService userService;
    private final SubjectService subjectService;

    @Autowired
    public StudentRestControllerV1(UserService userService, SubjectService subjectService) {
        this.userService = userService;
        this.subjectService = subjectService;
    }

    @GetMapping(value = "{username}")
    public ResponseEntity<StudentDto> getStudentByUsername(@PathVariable(name = "username") String username){
        User user = userService.findByUsername(username);

        if(user == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        StudentDto result = StudentDto.fromUser(user);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("addSubject")
    public ResponseEntity addSubject(RequestEntity<Subject> subject) {
        subjectService.addSubject(Objects.requireNonNull(subject.getBody()).getSubject());
        return new ResponseEntity<>(Objects.requireNonNull(subject.getBody()).getSubject(), HttpStatus.CREATED);
    }

    @PutMapping("deActivateSubject")
    public ResponseEntity deActivateSubject(RequestEntity<Subject> subject) {
        subjectService.deleteSubjectByName(Objects.requireNonNull(subject.getBody()).getSubject());
        return new ResponseEntity<>(Objects.requireNonNull(subject.getBody()).getSubject(), HttpStatus.CREATED);
    }
}
