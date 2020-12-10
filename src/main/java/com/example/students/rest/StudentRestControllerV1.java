package com.example.students.rest;

import com.example.students.dto.StudentDto;
import com.example.students.model.User;
import com.example.students.services.PerfomanceService;
import com.example.students.services.SubjectService;
import com.example.students.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/students/")
public class StudentRestControllerV1 {
    private final UserService userService;
    private final PerfomanceService perfomanceService;

    @Autowired
    public StudentRestControllerV1(UserService userService, PerfomanceService perfomanceService) {
        this.userService = userService;
        this.perfomanceService = perfomanceService;
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

    @GetMapping(value = "getUserSubjects")
    public ResponseEntity<List<String>> getStudentByUsername(@RequestParam Map<String, String> mapParam){
        var username = mapParam.get("username");
        return new ResponseEntity<>(perfomanceService.findByUser(username), HttpStatus.OK);
    }
}
