package com.example.students.rest;

import com.example.students.dto.RateDto;
import com.example.students.dto.StudentDto;
import com.example.students.services.PerfomanceService;
import com.example.students.services.TeacherToGroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/teachers")
public class TeacherRestControllerV1 {
    private final TeacherToGroupService teacherToGroupService;
    private final PerfomanceService perfomanceService;


    @Autowired
    public TeacherRestControllerV1(TeacherToGroupService teacherToGroupService, PerfomanceService perfomanceService) {
        this.teacherToGroupService = teacherToGroupService;
        this.perfomanceService = perfomanceService;
    }

    @GetMapping(value = "/getStudents")
    public ResponseEntity<List<StudentDto>> getStudentByUsername(@RequestParam Map<String, String> mapParam){
        var facultyName = mapParam.get("faculty");
        var subjectName = mapParam.get("subjectName");
        var group = Integer.parseInt(mapParam.get("userCourse"));
        var course = Integer.parseInt(mapParam.get("userGroup"));
        var username = mapParam.get("userName");

        var userList = teacherToGroupService.getStudents(facultyName, subjectName, group, course, username);
        List<StudentDto> studentDtoList = new ArrayList<>();
        if(userList != null){
            for(var i: userList){
                studentDtoList.add(StudentDto.fromUser(i));
            }
        }
        log.info("Get request : /api/v1/auth/getStudents");
        return new ResponseEntity<>(studentDtoList, HttpStatus.OK);
    }

    @PostMapping("/rateStudent")
    public ResponseEntity addTeacherToGroupRecord(@RequestBody RateDto rateDto) {
        perfomanceService.rate(rateDto);
        log.info("Get request : /api/v1/auth/rateStudent");
        return new ResponseEntity<>(Objects.requireNonNull(rateDto), HttpStatus.CREATED);
    }
}