package com.example.students.rest;

import com.example.students.dto.RateDto;
import com.example.students.dto.StudentDto;
import com.example.students.model.AcademicPerformance;
import com.example.students.model.Role;
import com.example.students.model.Subject;
import com.example.students.model.User;
import com.example.students.repositories.IPerfomanceRepository;
import com.example.students.repositories.ISubjectRepository;
import com.example.students.repositories.IUserRepository;
import com.example.students.services.PerfomanceService;
import com.example.students.services.TeacherToGroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/teachers")
public class TeacherRestControllerV1 {
    private final TeacherToGroupService teacherToGroupService;
    private final PerfomanceService perfomanceService;
    private final IPerfomanceRepository iPerfomanceRepository;
    private final ISubjectRepository iSubjectRepository;
    private final IUserRepository iUserRepository;
    @Autowired
    public TeacherRestControllerV1(TeacherToGroupService teacherToGroupService, PerfomanceService perfomanceService, IPerfomanceRepository iPerfomanceRepository, ISubjectRepository iSubjectRepository, IUserRepository iUserRepository) {
        this.teacherToGroupService = teacherToGroupService;
        this.perfomanceService = perfomanceService;
        this.iPerfomanceRepository = iPerfomanceRepository;
        this.iSubjectRepository = iSubjectRepository;
        this.iUserRepository = iUserRepository;
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

    @GetMapping(value = "/getStudentsAll")
    public ResponseEntity<List<StudentDto>> getStudentsAll(){
        var students = iUserRepository.findAll();
        List<StudentDto> studentDtoList2 = new ArrayList<>();
        if(students != null){
            for(var i: students){
                studentDtoList2.add(StudentDto.fromUser(i));
            }
        }
        log.info("Get request : /api/v1/auth/getStudentsAll");
        return new ResponseEntity<>(studentDtoList2, HttpStatus.OK);
    }

    @GetMapping(value = "getStudentMarksAll")
    public Page<AcademicPerformance> getStudentMarksAll(@RequestParam Map<String, String> mapParam ){
        var username = mapParam.get("username");
        Optional<Integer> page = Optional.empty();
        if(mapParam.get("page") != null)
            page = Optional.of(Integer.parseInt(mapParam.get("page")));
        User user1 = iUserRepository.findByUsername(username);
        var q = iPerfomanceRepository.findByUser(user1, PageRequest.of(page.orElse(0),2));
        for(var i:q){
            i.getUser().setRoles(List.of(new Role()));
        }
        return q;
    }

    @PostMapping("/rateStudent")
    public ResponseEntity addTeacherToGroupRecord(@RequestBody RateDto rateDto) {
        perfomanceService.rate(rateDto);
        log.info("Get request : /api/v1/auth/rateStudent");
        return new ResponseEntity<>(Objects.requireNonNull(rateDto), HttpStatus.CREATED);
    }
}