package com.example.students.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
public class Student {
    public Student(){
    }

    public Student(Integer studentId, String studentName, String studentSurname, String studentMiddleName, String password, Faculty facultyName, String course, int groupOfStudent) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentSurname = studentSurname;
        this.studentMiddleName = studentMiddleName;
        this.password = password;
        this.facultyName = facultyName;
        this.course = course;
        this.groupOfStudent = groupOfStudent;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer studentId;
    private String studentName;
    private String studentSurname;
    private String studentMiddleName;
    private String password;
    @OneToOne
    private Faculty facultyName;
    private String course;
    private int groupOfStudent;


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getGroupOfStudent() {
        return groupOfStudent;
    }

    public void setGroupOfStudent(int groupOfStudent) {
        this.groupOfStudent = groupOfStudent;
    }
    public void setGroup(int group) {
        this.groupOfStudent = group;
    }

    public Integer getGroup() {
        return groupOfStudent;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentSurname() {
        return studentSurname;
    }

    public void setStudentSurname(String studentSurname) {
        this.studentSurname = studentSurname;
    }

    public String getStudentMiddleName() {
        return studentMiddleName;
    }

    public void setStudentMiddleName(String studentMiddleName) {
        this.studentMiddleName = studentMiddleName;
    }

    public Faculty getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(Faculty facultyName) {
        this.facultyName = facultyName;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }


}
