package com.example.students.model;


import lombok.Data;
import lombok.Getter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@Getter
public class User extends BaseEntity{
    public User(){
    }

    @Column(name = "username")
    private String username;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<Role> roles;

    @ManyToOne
    private Faculty facultyName;
    @Column(name = "userCourse")
    private Integer userCourse;
    @Column(name = "userGroup")
    private Integer userGroup;

    @ManyToMany()
    @JoinTable(
            name = "users_to_subjects",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id"))
    @Column(name = "subjects")
    private List<Subject> subjects;

    public User(String username, String firstName, String lastName, String middleName, String email, String password, List<Role> roles, Faculty facultyName, Integer userCourse, Integer userGroup, List<Subject> subjects) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.facultyName = facultyName;
        this.userCourse = userCourse;
        this.userGroup = userGroup;
        this.subjects = subjects;
    }
    public User(String username, String firstName, String lastName, String middleName, String email, String password, Faculty facultyName, Integer userCourse, Integer userGroup) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.email = email;
        this.password = password;
        this.facultyName = facultyName;
        this.userCourse = userCourse;
        this.userGroup = userGroup;
    }

    public User(String username, String firstName, String lastName, String middleName, String password,  List<Subject> subjects) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.subjects = subjects;
    }

    public User(String username, String firstName, String lastName, String middleName, String password) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
    }
}
