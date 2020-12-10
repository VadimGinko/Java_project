package com.example.students.rest;

import com.example.students.Validator.StudentValidator;
import com.example.students.Validator.TeacherValidator;
import com.example.students.dto.AuthenticationRequestDto;
import com.example.students.exceptions.UserValidationException;
import com.example.students.model.*;
import com.example.students.forms.RegistrationStudentModel;
import com.example.students.forms.RegistrationTeacherModel;
import com.example.students.security.jwt.JwtTokenProvider;
import com.example.students.services.FacultyService;
import com.example.students.services.SubjectService;
import com.example.students.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/auth")
public class AuthenticationRestControllerV1 {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    private final FacultyService facultyService;
    private final SubjectService subjectService;
    private final StudentValidator studentValidator;
    private final TeacherValidator teacherValidator;

    @Autowired
    public AuthenticationRestControllerV1(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService, FacultyService facultyService, SubjectService subjectService, StudentValidator studentValidator, TeacherValidator teacherValidator) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.facultyService = facultyService;
        this.subjectService = subjectService;
        this.studentValidator = studentValidator;
        this.teacherValidator = teacherValidator;
    }
    @RequestMapping(value = "/registerStudent", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> Register(@Valid @RequestBody RegistrationStudentModel userDetails, BindingResult errors) throws MethodArgumentNotValidException {

        studentValidator.validate(userDetails, errors);

        if(errors.hasErrors()){
            throw new UserValidationException(errors);
        }

        User user = userDetails.ToUser();
        userService.register(user);
        log.info("Get request : /api/v1/auth/registerStudent");
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/registerTeacher", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> RegisterTeacher(@Valid @RequestBody RegistrationTeacherModel teacherDetails, BindingResult errors) throws MethodArgumentNotValidException {

        teacherValidator.validate(teacherDetails, errors);

        if(errors.hasErrors()){
            throw new UserValidationException(errors);
        }

        User user = teacherDetails.ToUser();
        userService.register(user);
        log.info("Get request : /api/v1/auth/registerTeacher");
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto) {
        try {
            String username = requestDto.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            User user = userService.findByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            }

            String token = jwtTokenProvider.createToken(username, user.getRoles());

            Map<Object, Object> response = new HashMap<>();
            response.put("token", token);
            log.info("Get request : /api/v1/auth/login");
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            log.info("Get request : /api/v1/auth/login ---- Invalid username or password");
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @GetMapping(value = {"/faculties"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Faculty>> facultyList() {
        log.info("Get request : /api/v1/auth/faculties");
        return new ResponseEntity<>(facultyService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = {"/subjects"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Subject>> subjectList() {
        return new ResponseEntity<>(subjectService.findAll()
                                                    .stream()
                                                    .filter(i -> i.getStatus() != Status.DELETED)
                                                    .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping(value = {"/userinfo"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity GetUsername(HttpServletRequest request) {
        String token = jwtTokenProvider.resolveToken(request);
        String username = jwtTokenProvider.getUsername(token);
        User user = userService.findByUsername(username);
        Role role = user.getRoles().get(0);
        Map<Object, Object> response = new HashMap<>();
        response.put("username", username);
        response.put("role", role.getName());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
