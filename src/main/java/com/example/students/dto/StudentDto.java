package com.example.students.dto;

import com.example.students.model.Faculty;
import com.example.students.model.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentDto {
    private String username;
    private String firstName;
    private String lastName;
    private String email;

    private String middleName;
    private Faculty facultyName;
    private Integer userCourse;
    private Integer userGroup;
    public User toUser(){
        User user = new User();
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setMiddleName(middleName);
        user.setFacultyName(facultyName);
        user.setUserCourse(userCourse);
        user.setUserGroup(userGroup);
        return user;
    }

    public static StudentDto fromUser(User user) {
        StudentDto studentDto = new StudentDto();
        studentDto.setUsername(user.getUsername());
        studentDto.setFirstName(user.getFirstName());
        studentDto.setLastName(user.getLastName());
        studentDto.setEmail(user.getEmail());
        studentDto.setMiddleName(user.getMiddleName());
        studentDto.setFacultyName(user.getFacultyName());
        studentDto.setUserCourse(user.getUserCourse());
        studentDto.setUserGroup(user.getUserGroup());
        return studentDto;
    }
}
