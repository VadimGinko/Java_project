package com.example.students.forms;

import com.example.students.model.Faculty;
import com.example.students.model.User;
import lombok.Getter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class RegistrationStudentModel {

    @NotBlank(message = "Username cannot be empty")
    @Length(min = 8, max = 40, message = "Username length must be between 8 and 40 characters")
    private String username;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Wrong email address")
    private String email;

    @NotBlank(message = "Password cannot be empty")
    @Length(min = 8, max = 16, message = "Password length must be between 8 and 40 characters")
    private String password;

    @NotBlank(message = "First Name cannot be empty")
    private String firstName;

    @NotBlank(message = "Last Name cannot be empty")
    private String lastName;

    @NotBlank(message = "Middle Name cannot be empty")
    private String middleName;

    private Faculty facultyName;

    private Integer userCourse;

    private Integer userGroup;

    public User ToUser(){
        return new User(
                this.getUsername(),
                this.getFirstName(),
                this.getLastName(),
                this.getMiddleName(),
                this.getEmail(),
                this.getPassword(),
                this.getFacultyName(),
                this.getUserCourse(),
                this.getUserGroup()
        );
    }
}

