package com.example.students.model;


import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;

@Entity
public class User {
    public User(){
    }

    public User(Integer userId, String userName, String userSurname, String userMiddleName, String userPassword, Faculty facultyName, Integer userCourse, Integer userGroup, boolean active, Set<Role> roles) {
        UserId = userId;
        this.userName = userName;
        this.userSurname = userSurname;
        this.userMiddleName = userMiddleName;
        this.userPassword = userPassword;
        this.facultyName = facultyName;
        this.userCourse = userCourse;
        this.userGroup = userGroup;
        this.active = active;
        this.roles = roles;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer UserId;
    private String userName;
    private String userSurname;
    private String userMiddleName;
    private String userPassword;
    @OneToOne
    private Faculty facultyName;
    private Integer userCourse;
    private Integer userGroup;
    private  boolean active;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    public Integer getUserId() {
        return UserId;
    }

    public void setUserId(Integer userId) {
        UserId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public String getUserMiddleName() {
        return userMiddleName;
    }

    public void setUserMiddleName(String userMiddleName) {
        this.userMiddleName = userMiddleName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Faculty getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(Faculty facultyName) {
        this.facultyName = facultyName;
    }

    public Integer getUserCourse() {
        return userCourse;
    }

    public void setUserCourse(Integer userCourse) {
        this.userCourse = userCourse;
    }

    public Integer getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(Integer userGroup) {
        this.userGroup = userGroup;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
